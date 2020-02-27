package net.wrovira.encryption;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

public class InstrumentedEncryptionStrategyTest {

    private InstrumentedEncryptionStrategy instrumentedEncryptionStrategy;

    @Before
    public void setUp() {
        instrumentedEncryptionStrategy = new InstrumentedEncryptionStrategy(new SimulatedDelayedRot13Encryption(100L));
    }

    @Test
    public void testRot13Encryption_SmallString() {
        final String plainText = "test string";
        final String encryptedText = instrumentedEncryptionStrategy.encrypt(plainText);

        assertThat(encryptedText, is("grfg fgevat"));
        assertThat(instrumentedEncryptionStrategy.getLatestTime(), is(greaterThan(0L)));
    }

}
