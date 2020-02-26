package net.wrovira.io;

import net.wrovira.encryption.EncryptionStrategy;
import net.wrovira.encryption.Rot13Encryption;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class SecureFileReaderTest {

    private static final String[] FILE_LINES = {
            "tempFile",
            "this should be encrypted",
            "and this"
    };

    @Rule
    public final TemporaryFolder temporaryFolder = new TemporaryFolder();

    private File tempFile;
    private SecureFileReader secureFileReader;
    private EncryptionStrategy encryptionStrategy;

    @Before
    public void setUp() throws Exception {
        tempFile = temporaryFolder.newFile("plainFile.txt");
        try (final PrintWriter printWriter = new PrintWriter(new FileWriter(tempFile))) {
            for (final String line : FILE_LINES) {
                printWriter.println(line);
            }
        }
        encryptionStrategy = new Rot13Encryption();
        secureFileReader = new SecureFileReader();
    }

    @After
    public void tearDown() {
        tempFile.delete();
    }

    @Test
    public void rot13EncryptionTest() throws Exception {
        secureFileReader.readAndEncrypt(tempFile.getAbsolutePath(), encryptionStrategy);

        try (final BufferedReader bufferedReader = new BufferedReader(new FileReader(tempFile.getAbsoluteFile()))) {
            for (int i = 0; i < FILE_LINES.length; i++) {
                final String encryptedLine = bufferedReader.readLine();
                assertThat(encryptedLine, is(notNullValue()));
                assertThat(encryptedLine, is(encryptionStrategy.encrypt(FILE_LINES[i])));
            }
        }
    }

}
