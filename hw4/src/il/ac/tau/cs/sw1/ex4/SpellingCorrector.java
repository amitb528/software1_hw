package il.ac.tau.cs.sw1.ex4;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class SpellingCorrector {

    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            throw new Exception("[ERROR] Missing command line argument: vocabulary file path. Please review HW4.");
        }

        Scanner vocabScanner = new Scanner(new File(args[0]));
        String[] vocabulary = scanVocabulary(vocabScanner);
        vocabScanner.close();
        printReadVocabulary(args[0], vocabulary.length);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            printEnterYourSentence();
            String sentence = scanner.nextLine().trim();

            if(sentence.equals("quit")){
                break;
            }

            String[] words = splitSentence(sentence);
            int correctionsCount = 0;

            for (int i = 0; i < words.length; i++) {
                String[][] similarWords = findSimilarWords(words[i], vocabulary);
                if (similarWords[0].length == 0) {
                    printWordIncorrect(words[i]);
                    printNumOfCorrections(similarWords[1].length, 1);
                    printNumOfCorrections(similarWords[2].length, 2);

                    int j = 0;
                    for (; j < Math.min(similarWords[1].length, 8); j++) {
                        printCorrectionOption(j + 1, similarWords[1][j]);
                    }
                    for (; j < Math.min(similarWords[1].length + similarWords[2].length, 8); j++) {
                        printCorrectionOption(j + 1, similarWords[2][j-similarWords[1].length]);
                    }

                    printEnterYourChoice();
                    while(true) {
                        int choice = scanner.nextInt() - 1;

                        if (choice < similarWords[1].length) {
                            words[i] = similarWords[1][choice];
                            break;
                        } else if (choice < j) {
                            words[i] = similarWords[2][choice - similarWords[1].length];
                            break;
                        } else {
                            printInvalidChoice();
                        }
                    }
                    scanner.nextLine();

                    correctionsCount++;
                }
            }

            printCorrectSentence(buildSentence(words));
            printNumOfCorrectedWords(correctionsCount);
        }

        scanner.close();
    }

    public static String[] scanVocabulary(Scanner scanner) {
        String[] words = new String[3000];

        int count = 0;
        while (count < words.length && scanner.hasNext()) {
            String word = scanner.next().toLowerCase();

            if (word.isEmpty()) {
                continue;
            }

            boolean repeat = false;
            for (int i = 0; i < count; i++) {
                if (words[i].equals(word)) {
                    repeat = true;
                    break;
                }
            }
            if (repeat) {
                continue;
            }

            words[count] = word;
            count++;
        }

        String[] result = Arrays.copyOf(words, count);
        Arrays.sort(result);

        return result;
    }

    public static int calcHammingDistance(String word1, String word2) {
        int distance = 0;
        int i = 0;

        for (; i < Math.min(word1.length(), word2.length()); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                distance++;
            }
        }

        for (; i < Math.max(word1.length(), word2.length()); i++) {
            distance++;
        }

        return distance;
    }

    public static String[][] findSimilarWords(String word, String[] vocabulary) {
        boolean hasWord = false;
        int dis1Count = 0, dis2Count = 0;
        String[] dis1Words = new String[vocabulary.length];
        String[] dis2Words = new String[vocabulary.length];

        for (String w : vocabulary) {
            int dis = calcHammingDistance(w, word);
            switch (dis) {
                case 2:
                    dis2Words[dis2Count] = w;
                    dis2Count++;
                    break;
                case 1:
                    dis1Words[dis1Count] = w;
                    dis1Count++;
                    break;
                case 0:
                    hasWord = true;
                    break;
            }
        }

        String[][] result = new String[3][];
        result[0] = hasWord ? new String[]{word} : new String[]{};
        result[1] = Arrays.copyOf(dis1Words, dis1Count);
        result[2] = Arrays.copyOf(dis2Words, dis2Count);
        return result;
    }

    public static String[] splitSentence(String sentence) {
        return sentence.trim().toLowerCase().split("\\s+");
    }

    public static String buildSentence(String[] words) {
        if (words.length == 0) {
            return "";
        }

        String sentence = words[0];
        for (int i = 1; i < words.length; i++) {
            sentence += " " + words[i];
        }

        return sentence;
    }

    public static boolean isInVocabulary(String[] vocabulary, String word) {
        for (String s : vocabulary) {
            if (s.equals(word)) {
                return true;
            }
        }

        return false;
    }

    /****
     * use these method to print all your output messages
     ****/
    public static void printWordIncorrect(String word) {
        System.out.println("the word " + word + " is incorrect");
    }

    public static void printReadVocabulary(String vocabularyFileName, int numOfWords) {
        System.out.println("Read " + numOfWords + " words from " + vocabularyFileName);
    }

    public static void printNumOfCorrections(int num, int distance) {
        System.out.println(num + " corrections of distance " + distance);
    }

    public static void printEnterYourSentence() {
        System.out.println("Enter your sentence:");
    }

    public static void printEnterYourChoice() {
        System.out.println("Enter your choice:");
    }

    public static void printCorrectionOption(int i, String correction) {
        System.out.println(i + ". " + correction);
    }

    public static void printInvalidChoice() {
        System.out.println("[WARNING] Invalid choice, try again.");
    }

    public static void printCorrectSentence(String sentence) {
        System.out.println("The correct sentence is: " + sentence);
    }

    public static void printNumOfCorrectedWords(int numOfWords) {
        System.out.println("Corrected " + numOfWords + " words.");
    }
}
