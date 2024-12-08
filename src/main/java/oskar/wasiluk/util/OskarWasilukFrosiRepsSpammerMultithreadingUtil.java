package oskar.wasiluk.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class OskarWasilukFrosiRepsSpammerMultithreadingUtil {
    private final ExecutorService executorService;

    /**
     * Constructor to initialize the thread pool with a given number of threads.
     *
     * @param threadCount Number of threads in the pool
     */
    public OskarWasilukFrosiRepsSpammerMultithreadingUtil(int threadCount) {
        this.executorService = Executors.newFixedThreadPool(threadCount);
    }

    /**
     * Submits a single task for execution.
     *
     * @param task The task to execute
     * @return Future representing the task result
     */
    public <T> Future<T> submitTask(Callable<T> task) {
        return executorService.submit(task);
    }

    /**
     * Submits a list of tasks for parallel execution.
     *
     * @param tasks List of tasks to execute
     * @return List of Future objects representing the task results
     */
    public <T> List<Future<T>> submitTasks(List<Callable<T>> tasks) {
        try {
            return executorService.invokeAll(tasks);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Task execution interrupted", e);
        }
    }

    /**
     * Runs a task with a specified number of threads.
     *
     * @param task       The task to execute
     * @param threadCount The number of threads to use
     * @param <T>        The result type of the task
     * @return List of Future objects representing the task results
     */
    public static <T> void runTaskWithCustomThreads(Callable<T> task, int threadCount) {
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);

        // Create a list to hold the Future results
        List<Future<T>> futures = new ArrayList<>();

        // Submit the task multiple times, based on threadCount
        for (int i = 0; i < threadCount; i++) {
            futures.add(executorService.submit(task));
        }

        // Process the results of the tasks
        try {
            for (Future<T> future : futures) {
                System.out.println("Result: " + future.get());  // Wait for the task to finish
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }

    /**
     * Runs a Runnable task with a specified number of threads.
     *
     * @param task       The task to execute
     * @param threadCount The number of threads to use
     */
    public static void runTaskWithCustomThreads(Runnable task, int threadCount) {
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);

        // Submit the task multiple times
        for (int i = 0; i < threadCount; i++) {
            executorService.submit(task);
        }

        // Shutdown the executor after submitting the tasks
        executorService.shutdown();
    }

    /**
     * Shuts down the thread pool gracefully.
     */
    public void shutdown() {
        executorService.shutdown();
    }

    /**
     * Shuts down the thread pool immediately, attempting to halt running tasks.
     */
    public void shutdownNow() {
        executorService.shutdownNow();
    }

}
