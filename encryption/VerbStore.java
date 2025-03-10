import java.io.IOException;

public class VerbStore extends WordStore {

    //extends WordStore without files
    public VerbStore() {
        super();
    }

    //extends WordStore for files
    public VerbStore(String fileName) throws IOException {
        super(fileName);
    }

    //new random item method
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
        //adds ing to verb
        return verb + "ing";
    }
}

