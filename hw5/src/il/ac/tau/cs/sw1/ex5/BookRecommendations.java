package il.ac.tau.cs.sw1.ex5;


import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class BookRecommendations {

    private static final int NO_RATING = -1;
    private static final int AGE_GROUP_MARGINE_SIZE = 3;


    Book[] books;
    User[] users;
    int[][] ratings;

    /**
     * @param books
     * @param users
     * @param ratings
     * @pre ratings.length == users.length
     * @pre ratings[0].length == books.length
     */
    public BookRecommendations(Book[] books, User[] users, int[][] ratings) {
        this.books = books;
        this.users = users;
        this.ratings = ratings;
    }

    /**
     * @param fileName
     * @param usersArray
     * @param booksArray
     * @return
     * @throws Exception
     * @pre usersArray.length != 0
     * @pre booksArray.length != 0
     * @pre fileName is a legal fileName, the format of the file is as expected
     * @post $ret.length = usersArray.length
     * @post $ret[0].length = booksArray.length
     * @post $res[i][j] == the rating of usersArray[i] to the booksArray[j]
     */
    public static int[][] loadRatingsData(String fileName, User[] usersArray, Book[] booksArray) throws Exception {
        int[][] ratings = new int[usersArray.length][booksArray.length];

        for (int i = 0; i < usersArray.length; i++) {
            Arrays.fill(ratings[i], NO_RATING);
        }

        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        reader.readLine();

        String line;
        while ((line = reader.readLine()) != null) {
            String[] values = line.replace("\"", "").split(";");

            int userID = Integer.parseInt(values[0]);
            String ISBN = values[1];
            int rating = Integer.parseInt(values[2]);

            int userIndex = -1;
            for (int i = 0; i < usersArray.length; i++) {
                if (usersArray[i].getUserID() == userID) {
                    userIndex = i;
                    break;
                }
            }

            int bookIndex = -1;
            for (int i = 0; i < booksArray.length; i++) {
                if (booksArray[i].getISBN().equals(ISBN)) {
                    bookIndex = i;
                    break;
                }
            }

            ratings[userIndex][bookIndex] = rating;
        }

        reader.close();

        return ratings;
    }

    /**
     * @param userIndex
     * @return
     * @pre userIndex >0 0 && userIndex < this.users.length
     * @post $ret = average rating of all the books this.users[userIndex] rated
     */
    public double getAverageRatingForUser(int userIndex) {
        int ratingsCount = 0;
        double sum = 0;
        for (int i = 0; i < ratings[userIndex].length; i++) {
            if (ratings[userIndex][i] != NO_RATING) {
                ratingsCount++;
                sum += ratings[userIndex][i];
            }
        }
        if (ratingsCount == 0) {
            return NO_RATING;
        }
        return sum / ratingsCount;
    }

    /**
     * @param bookIndex
     * @return
     * @pre bookIndex >= 0 && bookIndex < this.books.length
     * @post $ret = NO_RATING if no user had rated this.books[bookIndex]
     * @post otherwise, $ret = average rating of this.books[bookIndex] among all the users who rated it
     */
    public double getAverageRatingForBook(int bookIndex) {
        int ratingsCount = 0;
        double sum = 0;
        for (int i = 0; i < ratings.length; i++) {
            if (ratings[i][bookIndex] != NO_RATING) {
                ratingsCount++;
                sum += ratings[i][bookIndex];
            }
        }
        if (ratingsCount == 0) {
            return NO_RATING;
        }
        return sum / ratingsCount;
    }

    /**
     * @param user
     * @return
     * @pre there exist i s.t. (such that) this.users[i] == user && user.age != NO_AGE
     * @post $ret.lenght = this.users.lenght
     * @post $ret[i] == true <=> this.users[i] in the user group of "user" ( user.age - AGE_GROUP_MARGINE_SIZE  <= this.users.age <= user.age + AGE_GROUP_MARGINE_SIZE
     */
    public boolean[] getUsersInAgeGroup(User user) {
        boolean[] usersInAge = new boolean[users.length]; //default value = false

        for (int i = 0; i < users.length; i++) {
            if (users[i].hasAge()) {
                int age = users[i].getAge();
                usersInAge[i] = age >= user.getAge() - AGE_GROUP_MARGINE_SIZE
                        && age <= user.getAge() + AGE_GROUP_MARGINE_SIZE;
            }
        }

        return usersInAge;
    }

    /**
     * @param bookIndex
     * @param ageGroup
     * @return
     * @pre ageGroup.length == this.users.length
     * @pre bookIndex >= 0 && bookIndex < this.books.length
     * @post $res = NO_RATING if there is no user in the age group that rated book[bookIndex]
     * @post otherwise, $res = average ratings for all users this.users[i] s.t. ageGroup[i] == true
     */
    public double getAverageRatingForBookInAgeGroup(int bookIndex, boolean[] ageGroup) {
        int ratingsCount = 0;
        double sum = 0;
        for (int i = 0; i < ageGroup.length; i++) {
            if (ageGroup[i]) {
                if (ratings[i][bookIndex] != NO_RATING) {
                    ratingsCount++;
                    sum += ratings[i][bookIndex];
                }
            }
        }
        if (ratingsCount == 0) {
            return NO_RATING;
        }
        return sum / ratingsCount;
    }

    /**
     * @param user
     * @return
     * @pre there exist i s.t. this.users[i] == user  && user.age != NO_AGE
     * @pos $res = NO_RATING if there is no user in the age group that rated book[bookIndex]
     * @post $res = this.books[i] s.t. average for book[i] in the age group of user is maximum
     */
    public Book getHighestRatedBookInAgeGroup(User user) {
        boolean[] usersInAge = getUsersInAgeGroup(user);
        double maxAverage = getAverageRatingForBookInAgeGroup(0, usersInAge);
        Book highestRatedBook = books[0];
        for (int i = 1; i < books.length; i++) {
            double average = getAverageRatingForBookInAgeGroup(i, usersInAge);
            if (average > maxAverage) {
                maxAverage = average;
                highestRatedBook = books[i];
            }
        }
        return highestRatedBook;
    }


    /**
     * @param user
     * @param fileName
     * @throws Exception
     * @pre fileName is a legal fileName, the format of the file is as expected
     * @pre there exist i s.t. this.users[i] == user  && user.age != NO_AGE
     */
    public void printRecommendationToFile(User user, String fileName) throws Exception {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

        Book b = getHighestRatedBookInAgeGroup(user);
        writer.write(getRecommendedBookString(b));
        writer.newLine();

        for (int i = 0; i < books.length; i++) {
            if (books[i] == b) {
                boolean[] ageGroup = getUsersInAgeGroup(user);
                double average = getAverageRatingForBookInAgeGroup(i, ageGroup);
                writer.write(getRecommendedBookAverageInUserGroup(average));
                writer.newLine();

                average = getAverageRatingForBook(i);
                writer.write(getRecommendedBookAverageFoAllUsers(average));
                writer.newLine();
                break;
            }
        }



        writer.close();
    }

    private String getRecommendedBookString(Book b) {
        return "The recommended Book for you is: " + b.toString();
    }

    private String getRecommendedBookAverageInUserGroup(double average) {
        return String.format("The book's average rating among its age group is: %.2f", average);
    }

    private String getRecommendedBookAverageFoAllUsers(double average) {
        return String.format("The book's average rating among all the users is: %.2f", average);
    }

}
