package com.luciddreaming.utils;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class MainThreadTaskQueue {
    private static final ConcurrentLinkedQueue<Runnable> tasks = new ConcurrentLinkedQueue<>();
    private static final ConcurrentLinkedQueue<ResultTask<?>> resultTasks = new ConcurrentLinkedQueue<>();

    /**
     * Submit a task to be executed on the main thread
     */
    public static void submit(Runnable task) {
        tasks.offer(task);
    }

    /**
     * Submit a task that returns a result to be executed on the main thread
     */
    public static <T> void submitWithResult(Supplier<T> task, Consumer<T> callback) {
        resultTasks.offer(new ResultTask<>(task, callback));
    }

    /**
     * Process all pending tasks on the main thread
     */
    public static void processTasks() {
        // Process void tasks
        Runnable task;
        while ((task = tasks.poll()) != null) {
            try {
                task.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Process result tasks
        ResultTask<?> resultTask;
        while ((resultTask = resultTasks.poll()) != null) {
            try {
                resultTask.process();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static class ResultTask<T> {
        final Supplier<T> task;
        final Consumer<T> callback;

        ResultTask(Supplier<T> task, Consumer<T> callback) {
            this.task = task;
            this.callback = callback;
        }
        
        /**
         * Process this task and invoke the callback with the result
         */
        public void process() {
            T result = task.get();
            callback.accept(result);
        }
    }
}
