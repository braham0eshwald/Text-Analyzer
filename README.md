Project Overview: The project is a text analysis application developed in Java. It processes a given text file and offers various text analysis functionalities, such as counting words, counting specific words, calculating average word length, determining the longest and shortest words, identifying palindromes, and more. The program allows users to interactively choose from several options for processing the text. It provides a console-based user interface to handle different functionalities based on user input.

Design Choices:

Core Functionality: The program is divided into several methods, each responsible for a specific task. Methods like uniqueWordCount(), averageLength(), and sentenceCount() provide the necessary analysis of the text, while others such as minMax() and findPalindromes() focus on specific tasks like finding the longest or shortest words and detecting palindromes, respectively.

Data Structures:

The primary data structure used for storing words is an ArrayList<String>, as it provides dynamic resizing, which is crucial for handling text input of varying sizes.
A HashMap<String, Integer> is used in the frequentWordCount() method to store word frequency, enabling efficient counting and sorting of the most frequent words.
User Interface: A simple text-based menu allows users to choose from various analysis options. The program reads user input using the Scanner class, which is efficient for reading line-by-line and parsing integer values for user choices. The application continuously prompts the user until they decide to exit by pressing 0.

File Handling: The application prompts the user to input the path to a text file. The file is then read using Files.readAllLines(), which allows reading the file into a list of strings. Each line is then processed to extract words, using regular expressions to clean the input and split it into words.

Challenges Encountered:

Handling Text Cleaning: One of the primary challenges was ensuring the text input was properly cleaned and formatted for accurate analysis. This included removing punctuation, handling case sensitivity, and ensuring words were correctly split. I used regular expressions to clean and split the text appropriately. However, certain edge cases (like words with special characters or hyphens) required careful handling.

Word Frequency Counting: In the frequentWordCount() method, counting word frequencies efficiently while ensuring that word comparisons were case-insensitive posed a challenge. To resolve this, I added .toLowerCase() to standardize words before processing them.

Sentence Parsing: The sentence parsing functionality (sentenceCount()) required splitting the text by punctuation marks like periods, exclamation marks, and question marks. A challenge was ensuring the sentences were accurately identified, especially when punctuation marks occurred mid-sentence (such as in abbreviations). The approach taken was to split based on punctuation followed by spaces or line breaks.

User Interaction: Managing user input in a way that prevents errors and prompts the user with meaningful feedback was another challenge. For instance, the program needs to gracefully handle invalid file paths or improper input for menu choices, and I used try-catch blocks and input validation to ensure a smooth user experience.

Edge Cases: Several edge cases were considered, such as empty lines, multiple spaces, and punctuation at the beginning or end of words. Proper handling of these cases was crucial to ensure accurate word counts, frequency counts, and sentence splitting.

Conclusion: This project demonstrates the ability to process and analyze text data in various ways, using simple data structures and techniques in Java. While the design choices were straightforward, the challenges in processing and cleaning text input, counting words efficiently, and handling edge cases required careful attention. Ultimately, the project provides a solid foundation for building more advanced text processing applications and tools.
