package net.wrovira.encryption;

import static java.lang.System.currentTimeMillis;

public class InstrumentedEncryptionStrategy implements EncryptionStrategy {

    private final EncryptionStrategy encryptionStrategy;
    private long sum;
    private int count;
    private long latest;

    public InstrumentedEncryptionStrategy(final EncryptionStrategy encryptionStrategy) {
        this.encryptionStrategy = encryptionStrategy;
        this.sum = 0;
    }

    @Override
    public synchronized String encrypt(final String clearText) {
        final long startTime = currentTimeMillis();
        final String cryptedText = encryptionStrategy.encrypt(clearText);
        final long endTime = currentTimeMillis();
        final long timeTaken = endTime - startTime;
        sum += timeTaken;
        count++;
        latest = timeTaken;
        return cryptedText;
    }

    public long getLatestTime() {
        return latest;
    }

    public long avg() {
        return count > 0? sum / count : 0;
    }
}
