package net.wrovira.data;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public abstract class ActiveObject<T> extends Thread {

    private final BlockingQueue<T> queue = new ArrayBlockingQueue<T>(99999);

    public void startup () {
        start();
    }

    public void send(T message) {
        queue.add(message);
    }

    public void shutdown() {
        interrupt();
    }

    @Override
    public void run() {
        while (true) {
            if (!queue.isEmpty()) {
                initialize();
                final T message = queue.poll();
                process(message);
            }
        }
    }

    @Override
    public void interrupt() {
        super.interrupt();
        cleanup();
    }

    abstract protected void initialize();
    abstract protected void process(T t);
    abstract protected void cleanup();

}
