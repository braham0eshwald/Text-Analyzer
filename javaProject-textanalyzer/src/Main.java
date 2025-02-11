import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void getWords(List<String> lines, List<String> words){
        for (String line : lines) {
            line = line.replaceAll("[^a-zA-Zа-яА-Я0-9\\s-`]", "");
            if(!line.isEmpty()){
                for(String word : line.trim().split("\\s+")){
                    words.add(word.toLowerCase());
                }
            }
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

    public static int sentenceCount(List<String> lines){
        List<String> sentences = new ArrayList<>();
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
    public static void findPalindromes(List<String> words){
        for(String word : words){
            String reversed = new StringBuilder(word).reverse().toString();
            if(word.equals(reversed) && word.length() > 2){
                System.out.println(word);
            }
        }
    }

    public static void userInterface(int choice, List<String> words, List<String> lines, Scanner scanner){
        switch (choice){
            case 1:
                System.out.println("В тексте " + words.size() + " слов.");
                break;
            case 2:
                System.out.println("Введите слово для поиска.");
                if (scanner.hasNextLine()) scanner.nextLine();
                String uWord = scanner.nextLine();
                System.out.println("Слово \"" + uWord + "\" встречается " + uniqueWordCount(words, uWord) + " раз.");
                break;
            case 3:
                System.out.println("Средняя длина слов: " + averageLength(words));
                break;
            case 4:
                System.out.println("В тексте предложений: " + sentenceCount(lines));
                break;
            case 5:
                System.out.println("Введите количество слов на вывод.");
                int limit = scanner.nextInt();
                frequentWordCount(words, limit);
                break;
            case 6:
                System.out.println("Самое длинное слово - " + minMax(words, "max"));
                break;
            case 7:
                System.out.println("Самое короткое слово - " + minMax(words, "min"));
                break;
            case 8:
                System.out.println("Палиндромы: ");
                findPalindromes(words);
                break;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> words = new ArrayList<>();
        List<String> lines;
        while (true){
            System.out.println("Введите путь к файлу...");
            String path = scanner.nextLine();
            try {
                lines = Files.readAllLines(Paths.get(path));
                break;
            }
            catch (IOException e){
                System.out.println("Путь неверен или файла не существует.");
            }
        }
        getWords(lines, words);
        int choice = 100;
        while(choice != 0){
            System.out.println("Выбирете действие с текстом...");
            System.out.print("1 - Вычислить количество слов в тексте.\n" + "2 - Вычислить количество определённого слова в текста.\n" +
                    "3 - Вычислить cреднюю длину слов.\n" + "4 - Вычислить количество предложений в тексте.\n" +
                    "5 - Найти наиболее часто встречающиеся слова.\n" + "6 - Найти самое длинное слово.\n" +
                    "7 = Найти самое короткое слово.\n" + "8 - Найти палиндромы.\n");
            choice = scanner.nextInt();
            userInterface(choice, words, lines, scanner);
            System.out.println("Если хотите произвести новую операцию нажмите 1, для выхода нажмите 0");
            choice = scanner.nextInt();
        }
        scanner.close();
    }
}
