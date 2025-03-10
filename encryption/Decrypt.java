import java.util.Base64;

public class Decrypt {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java Decrypt <encryptedText>");
            return;
        }

        StringBuilder encryptedTextBuilder = new StringBuilder();
        for (String word : args) {
            encryptedTextBuilder.append(word).append(" ");
        }
        String encryptedText = encryptedTextBuilder.toString().trim();

        try {
            String decryptedText = EncryptionUtils.decrypt(encryptedText);
            System.out.println("Decrypted text: " + decryptedText);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid encrypted text");
        }
    }
}

class EncryptionUtils {
    public static String decrypt(String encryptedText) {
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedText);
        return new String(decodedBytes);
    }

    public static String encrypt(String plainText) {
        byte[] encodedBytes = Base64.getEncoder().encode(plainText.getBytes());
        return new String(encodedBytes);
    }
}
