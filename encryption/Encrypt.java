import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Encrypt {
    private final WordStore adjectives;
    private final WordStore adverbs;
    private final WordStore nouns;
    private final VerbStore verbs;

    //constructor for encrypt, makes word stores for adjectives, adverbs, nouns, and verbs
    public Encrypt() throws IOException {
        adjectives = new WordStore("adjectives.txt");
        adverbs = new WordStore("adverbs.txt");
        nouns = new WordStore("nouns.txt");
        verbs = new VerbStore("verbs.txt");
    }

    public List<String> encrypt(String input) {
        List<String> words_encrypted = new ArrayList<>();
        Queue<Character> letters = new LinkedList<>();

        for (char c : input.toCharArray()) {
            letters.add(c);
        }

        if (!letters.isEmpty()) {
            words_encrypted.add(nouns.getRandomItem(letters.poll()));
        }
        if (!letters.isEmpty()) {
            words_encrypted.add(adjectives.getRandomItem(letters.poll()));
        }

        while (!letters.isEmpty()) {
            words_encrypted.add(verbs.getRandomItem(letters.poll()));
            if (!letters.isEmpty()) {
                words_encrypted.add(adverbs.getRandomItem(letters.poll()));
            }
        }

        Collections.reverse(words_encrypted);
        return words_encrypted;
    }

    //main method
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java Encrypt <word>");
            return;
        }

        try {
            Encrypt encryptor = new Encrypt();
            List<String> encryptedSentence = encryptor.encrypt(args[0]);

            for (String word : encryptedSentence) {
                System.out.println(word);
            }
        } catch (IOException e) {
            System.err.println("Error loading word lists: " + e.getMessage());
        }
    }
}
