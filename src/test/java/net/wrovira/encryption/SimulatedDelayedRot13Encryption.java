package net.wrovira.encryption;

public class SimulatedDelayedRot13Encryption implements EncryptionStrategy {

    private final EncryptionStrategy encryptionStrategy;
    private final long delay;

    public SimulatedDelayedRot13Encryption(final long delay) {
        this.delay = delay;
        this.encryptionStrategy = new Rot13Encryption();
    }

    @Override
    public String encrypt(final String clearText) {
        try {
            Thread.sleep(delay);
        } catch (final InterruptedException ex) { }
        return this.encryptionStrategy.encrypt(clearText);
    }
}
