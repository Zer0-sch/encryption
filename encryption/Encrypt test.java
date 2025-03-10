import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Encrypt {
    private WordStore adjectives;
    private WordStore adverbs;
    private WordStore nouns;
    private WordStore verbs;

    public Encrypt(String adjectivesFile, String adverbsFile, String nounsFile, String verbsFile) throws IOException {
        adjectives = new WordStore(adjectivesFile);
        adverbs = new WordStore(adverbsFile);
        nouns = new WordStore(nounsFile);
        verbs = new WordStore(verbsFile);
    }

    public String encryptWord(String word, char type) {
        switch (type) {
            case 'a':
                return adjectives.getRandomItem(word.charAt(0));
            case 'b':
                return adverbs.getRandomItem(word.charAt(0));
            case 'n':
                return nouns.getRandomItem(word.charAt(0));
            case 'v':
                return verbs.getRandomItem(word.charAt(0));
            default:
                return null;
        }
    }

    public List<String> encrypt(String input) {
        List<String> result = new ArrayList<>();
        List<Character> chars = new ArrayList<>();
        for (char c : input.toCharArray()) {
            chars.add(c);
        }

        if (!chars.isEmpty()) {
            char lastChar = chars.remove(chars.size() - 1);
            String noun = encryptWord(String.valueOf(lastChar), 'n');
            if (noun != null) {
                result.add(noun);
            }
        }

        if (!chars.isEmpty()) {
            char secondLastChar = chars.remove(chars.size() - 1);
            String adjective = encryptWord(String.valueOf(secondLastChar), 'a');
            if (adjective != null) {
                result.add(0, adjective);
            }
        }

        boolean useAdverb = true;
        while (!chars.isEmpty()) {
            char currentChar = chars.remove(0);
            String word = encryptWord(String.valueOf(currentChar), useAdverb ? 'b' : 'v');
            if (word != null) {
                result.add(word);
            }
            useAdverb = !useAdverb;
        }

        return result;
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please provide a word as the first command line argument.");
            return;
        }

        String inputWord = args[0];
        try {
            Encrypt encrypt = new Encrypt("adjectives.txt", "adverbs.txt", "nouns.txt", "verbs.txt");
            List<String> encryptedWords = encrypt.encrypt(inputWord);
            for (String word : encryptedWords) {
                System.out.println(word);
            }
        } catch (IOException e) {
            System.err.println("Error loading word files: " + e.getMessage());
        }
    }
}
