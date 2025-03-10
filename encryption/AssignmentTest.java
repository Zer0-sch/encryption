import java.io.IOException;
import java.util.List;

public class AssignmentTest {
    public static void main(String[] args) {
        //calls constructor
        WordStore store = new WordStore();
        //adds items to  wordstore
        store.add('a', "apple");
        store.add('b', "banana");
        try {
            //tests getrandomitem method
            assert store.getRandomItem('a').equals("apple");
            assert store.getRandomItem('b').equals("banana");
            System.out.println("All tests passed");
        } catch (AssertionError e) {
            //prints error message if test fails
            System.out.println("test failed");
        }

        try {
            //tests for encrypt
            Encrypt encrypt = new Encrypt();
            String[] testWords = {"OOP", "JAVA", "ENCRYPTION"};
            for (String testWord : testWords) {
                List<String> result = encrypt.encrypt(testWord);
                System.out.println("Input: " + testWord + " | Output length: " + result.size());
                for (String word : result) {
                    System.out.println(word);
                }
                assert result.size() == testWord.length();
            }
            System.out.println("Encrypt tests passed");
        } catch (IOException e) {
            //error for not loading files
            System.err.println("Error loading word files: " + e.getMessage());
        } catch (AssertionError e) {
            //error for encrypt class failing
            System.out.println("Encrypt test failed");
        }

        try {
            // tests for decrypt
            String encryptedText = EncryptionUtils.encrypt("password");
            String[] encryptedWords = encryptedText.split(" ");
            Decrypt.main(encryptedWords);
            String decryptedText = EncryptionUtils.decrypt(encryptedText);
            assert decryptedText.equals("password");
            System.out.println("Decrypt tests passed");
        } catch (AssertionError e) {
            // error for decrypt class failing
            System.out.println("Decrypt test failed");
        }
    }
}