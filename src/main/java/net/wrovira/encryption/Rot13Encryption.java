package net.wrovira.encryption;

public class Rot13Encryption implements EncryptionStrategy {

    @Override
    public String encrypt(final String clearText) {
        final StringBuilder cryptText = new StringBuilder();
        for (int i = 0; i < clearText.length(); i++) {
            char c = clearText.charAt(i);
            if (c >= 'a' && c <= 'm' || c >= 'A' && c <= 'M') {
                c += 13;
            } else if (c >= 'n' && c <= 'z' || c >= 'N' && c <= 'Z') {
                c -= 13;
            }
            cryptText.append(c);
        }
        return cryptText.toString();
    }

}
