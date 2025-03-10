import java.util.*;

public class WordStore {
    private Map<Character, List<String>> store;

    public WordStore() {
        store = new HashMap<>();
    }

    public void add(char key, String item) {
        store.putIfAbsent(key, new ArrayList<>());
        store.get(key).add(item.toLowerCase());
    }

    public String getRandomItem(char key) {
        List<String> items = store.get(key);
        if (items == null || items.isEmpty()) {
            return null;
        }
        Random random = new Random();
        return items.get(random.nextInt(items.size()));
    }

    public WordStore(String fileName) throws IOException {
        this();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String[] parts = line.split(",");
                    if (parts.length == 2) {
                        String word = parts[0];
                        char letter = parts[1].charAt(0);
                        add(letter, word);
                    }
                }
            }
        }
    }
}

