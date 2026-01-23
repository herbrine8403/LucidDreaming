package com.luciddreaming.utils;

import com.luciddreaming.LucidDreaming;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.FMLCommonHandler;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Direct main thread executor that uses Minecraft's own scheduling mechanism
 * for more reliable main thread execution
 */
public class DirectMainThreadExecutor {
    private static final Minecraft mc = Minecraft.getMinecraft();

    /**
     * Execute a task on the main thread and wait for it to complete
     */
    public static <T> T executeOnMainThread(Callable<T> task) throws Exception {
        if (isMainThread()) {
            // Already on main thread, execute directly
            return task.call();
        }

        final CountDownLatch latch = new CountDownLatch(1);
        final AtomicReference<T> result = new AtomicReference<>();
        final AtomicReference<Exception> exception = new AtomicReference<>();

        // Use Minecraft's scheduler for reliable main thread execution
        FMLCommonHandler.instance().getFMLLogger().info("Scheduling screenshot task on main thread");
        
        // Schedule task to run on next client tick
        mc.addScheduledTask(() -> {
            try {
                result.set(task.call());
            } catch (Exception e) {
                exception.set(e);
                LucidDreaming.LOGGER.error("Error in main thread task", e);
            } finally {
                latch.countDown();
            }
        });

        // Wait for the task to complete
        latch.await();

        // Rethrow any exception that occurred
        if (exception.get() != null) {
            throw exception.get();
        }

        return result.get();
    }

    /**
     * Check if current thread is the main Minecraft thread
     */
    public static boolean isMainThread() {
        return Thread.currentThread().getName().equals("Client thread");
    }
}