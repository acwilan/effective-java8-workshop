package net.wrovira.data;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ActiveObjectTest {

    private final static int N_OF_THREAD = 100;

    @Test
    public void singleThreadTest() throws Exception {
        final AtomicInteger i = new AtomicInteger(0);
        ActiveObject<String> out = new ActiveObject<String>() {
            @Override
            protected void process(String t) {
                i.incrementAndGet();
            }

            @Override
            protected void cleanup() {
                i.incrementAndGet();
            }

            @Override
            protected void initialize() {
                i.incrementAndGet();
            }
        };
        out.startup();
        Thread.sleep(10);
        out.send("Test message");
        Thread.sleep(10);
        out.shutdown();
        Thread.sleep(10);
        assertThat(i.get(), is(3));
    }

    @Test
    public void multiThreadTest() throws Exception {
        CountingTask t = new CountingTask();
        t.startup();
        for (int i = 0; i < N_OF_THREAD; i++) {
            new Produce100(t).start();
        }
        Thread.sleep(2000);
        t.shutdown();
        Thread.sleep(1);
        assertThat(t.readCount(), is(N_OF_THREAD * 100));
    }

    private static class CountingTask extends ActiveObject<String> {
        private Integer i;

        public CountingTask() {
            this.i = 0;
        }

        @Override
        protected void process(String t) {
            this.i = this.i + 1;
        }

        @Override
        protected void cleanup() {
        }

        public int readCount() {
            return this.i;
        }

        @Override
        protected void initialize() {
        }

    }

    private static class Produce100 extends Thread {

        private ActiveObject<String> activeObject;

        public Produce100(ActiveObject<String> t) {
            this.activeObject = t;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                activeObject.send("Test message");
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }


}
