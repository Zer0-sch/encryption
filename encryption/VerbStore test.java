import java.io.IOException;

public class VerbStore test extends WordStore {

    public VerbStore() {
        super();
    }

    public VerbStore(String fileName) throws IOException {
        super(fileName);
    }

    @Override
    public String getRandomItem(char key) {
        String verb = super.getRandomItem(key);
        if (verb == null) {
            return null;
        }
        return convertToPresentContinuous(verb);
    }

    private String convertToPresentContinuous(String verb) {
        if (verb.endsWith("e")) {
            verb = verb.substring(0, verb.length() - 1);
        }
        return verb + "ing";
    }
}
