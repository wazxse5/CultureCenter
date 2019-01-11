package client.task;

import client.ThreadClient;
import message.ImageEventTypeMessage;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

public class ImageRequestingTask implements Runnable {
    private ThreadClient threadClient;
    private Semaphore semaphore;
    private final BlockingQueue<ImageEventTypeMessage> queue;

    public ImageRequestingTask(ThreadClient threadClient, Semaphore semaphore, BlockingQueue<ImageEventTypeMessage> queue) {
        this.threadClient = threadClient;
        this.semaphore = semaphore;
        this.queue = queue;
    }

    @Override public void run() {
        while (true) {
            try {
                ImageEventTypeMessage message = queue.take();
                threadClient.sendImageEventTypeRequestDirectly(message);
            } catch (InterruptedException e) {
                break;
            }
        }

    }
}
