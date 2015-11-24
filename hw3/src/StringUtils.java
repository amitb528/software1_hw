import java.util.*;

/**
 * Created by Amit on 11/11/2015.
 */
public class StringUtils {
    public static void main(String[] args){
        System.out.println(sortStringWords("To Be Or Not To Be"));
        System.out.println(deleteSubString("If you don’t have dreams, ",
                "If you don’t have dreams, you’ll never make your dreams come true "));
        System.out.println(deleteSubString("It is better to be roughly wrong ",
                "It is better to be roughly right than precisely wrong "));
        System.out.println(deleteSubString("Conversation", " Experience is simply the name we give our mistakes"));
        System.out.println(deleteSubString("s. I am always ",
                "I have the simplest tastes. I am always satisfied with the best"));
        System.out.println(mergeStrings("boy", "girl"));
        System.out.println(mergeStrings("catdog", "boygirl"));
        System.out.println(mergeStrings("abcdefg", "bcgfhi"));
    }

    public static String sortStringWords(String str){
        List<String> words = Arrays.asList(str.split(" "));
        Collections.sort(words);
        Collections.reverse(words);

        if(words.size() == 0){
            return "";
        }

        String result = words.get(0);
        for(int i = 1; i < words.size(); i++){
            result+= " " + words.get(i);
        }
        return result;
    }

    public static String deleteSubString(String sub, String s){
        int index = s.indexOf(sub);
        if(index == -1){
            return s;
        }else{
            return s.substring(0, index) + s.substring(index+sub.length());
        }
    }

    public static String mergeStrings(String a, String b){
        boolean[] isContained = new boolean['z' - 'a' + 1];

        for(int i = 0; i < b.length(); i++){
            isContained[b.charAt(i) - 'a'] = true;
        }

        String result = "";
        for(int i = 0; i < a.length(); i++){
            char c = a.charAt(i);
            if(isContained[c - 'a']){
                result+= c;
            }
        }

        return result;
    }
}
