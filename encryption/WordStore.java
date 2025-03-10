import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class WordStore {
    private Map<Character, List<String>> store;

    //corstructor for hashmap
    public WordStore() {
        store = new HashMap<>();
    }

    //add to hashmap
    public void add(char key, String item) {
        store.putIfAbsent(key, new ArrayList<>());
        store.get(key).add(item.toLowerCase());
    }

    //get random item from hashmap
    public String getRandomItem(char key) {
        List<String> items = store.get(key);
        if (items == null || items.isEmpty()) {
            return null;
        }
        Random random = new Random();
        return items.get(random.nextInt(items.size()));
    }

    //read from file
    public WordStore(String fileName) throws IOException {
        this();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            //read line by line
            while ((line = reader.readLine()) != null) {
                //if line is not empty
                if (!line.trim().isEmpty()) {
                    //adds comma
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