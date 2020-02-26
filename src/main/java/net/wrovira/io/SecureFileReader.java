package net.wrovira.io;

import net.wrovira.encryption.EncryptionStrategy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class SecureFileReader {

    public void readAndEncrypt(final String filename, final EncryptionStrategy encryptor) throws IOException {
        String line;
        final List<String> encryptedLines = new ArrayList<>();
        try (final BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            while ((line = bufferedReader.readLine()) != null) {
                encryptedLines.add(encryptor.encrypt(line));
            }
        }
        try (final PrintWriter printWriter = new PrintWriter(new FileWriter(filename))) {
            encryptedLines.forEach(encryptedLine -> {
                printWriter.println(encryptedLine);
            });
        }
    }

}
