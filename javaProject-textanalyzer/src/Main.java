import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void getWords(String path, List<String> words){
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            for (String line : lines) {
                line = line.replaceAll("[^a-zA-Zа-яА-Я0-9\\s-`]", "");
                if(!line.isEmpty()){
                    for(String word : line.trim().split("\\s+")){
                        words.add(word.toLowerCase());
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Путь неверен или файла не существует.");
        }
    }

    public static int uniqueWordCount(List<String> words, String uniqueWord){
        int count = 0;
        for(String word : words){
            if(Objects.equals(word, uniqueWord)){
                count++;
            }
        }
        return count;
    }

    public static int averageLength(List<String> words){
        int sum = 0;
        for(String word : words){
            sum += word.length();
        }
        return sum / words.size();
    }

    public static int sentenceCount(String path){
        List<String> sentences = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            for (String line : lines) {
                line = line.replaceAll("[^a-zA-Zа-яА-Я0-9.,!?\\s]", "");
                if(!line.isEmpty()){
                    for(String sentence : line.split("[.!?]+\\s*")){
                        if (!sentence.trim().isEmpty()) {
                            sentences.add(sentence.trim());
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Путь неверен или файла не существует.");
        }
        return sentences.size();
    }

    public static void frequentWordCount(List<String> words, int limit){
        Map<String, Integer> sortWords = new HashMap<>();
        for(int i = 0; i < words.size() - 1; i++){
            sortWords.put(words.get(i), sortWords.getOrDefault(words.get(i), 0) + 1);
        }
        List<Map.Entry<String, Integer>> sortedWords = new ArrayList<>(sortWords.entrySet());
        sortedWords.sort((a, b) -> b.getValue().compareTo(a.getValue()));
        for(int i = 0; i < Math.min(limit, sortedWords.size()); i++){
            System.out.println(sortedWords.get(i).getKey() + " = " + sortedWords.get(i).getValue());
        }
    }
    
    public static String minMax(List<String> words, String determine){
        if(Objects.equals(determine, "max")){
            String maxWord = null;
            int maxLength = Integer.MIN_VALUE;
            for(String word : words){
                if(word.length() > maxLength){
                    maxLength = word.length();
                    maxWord = word;
                }
            }
            return maxWord;
        } else{
            String minWord = null;
            int minLength = Integer.MAX_VALUE;
            for(String word : words){
                if(word.length() < minLength){
                    minLength = word.length();
                    minWord = word;
                }
            }
            return minWord;
        }
    }


    public static void main(String[] args) {
        String aboba = "aboba.txt";
        List<String> words = new ArrayList<>();
        getWords(aboba, words);
        for(String word : words){
            System.out.println(word);
        }
        System.out.println(minMax(words, "max") + "\n" + minMax(words, "min"));
        frequentWordCount(words, 3);
        System.out.println(sentenceCount(aboba));
        System.out.println(averageLength(words));
        System.out.println(uniqueWordCount(words, "осень"));
    }
}
