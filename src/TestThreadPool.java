import com.sun.istack.internal.NotNull;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class TestThreadPool {
    public static void main(String[] args) {
        // 创建线程的工厂
        ThreadFactory factory = new ThreadFactory() {
            final AtomicInteger count = new AtomicInteger();

            @Override
            public Thread newThread(@NotNull Runnable r) {
                return new Thread(r, "Thread #" + count.getAndIncrement());
            }
        };

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                // 核心线程数，默认情况下会一直存活，如果设置了allowCoreThreadTimeOut设置为true，由keepAliveTime控制
                3,
                // 线程池最大线程数，达到这个数值后，新任务会被阻塞
                10,
                // 默认指非核心线程的闲置超时时长
                60,
                TimeUnit.SECONDS,
                // 线程池的任务队列，通过线程池的execute方法提交的Runnable对象超过核心线程数后会存储在这个参数中
                new LinkedBlockingQueue<Runnable>(128),
                // 线程工厂
                factory
                // 还有rejectedExecutionHandler
        );


//        executor.shutdown();
//        executor.shutdownNow();

        // 测试1
        for (int i = 0; i < 3; i++) {
            executor.execute(() -> {
                try {
                    while (true) {
                        Thread.sleep(1000);
                        System.out.println("sleep 1000" + Thread.currentThread().getName());

                        // 此时队列中个数为0
                        System.out.println(executor.getQueue().size());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        // 测试2
        for (int i = 0; i < 4; i++) {
            executor.execute(() -> {
                try {
                    while (true) {
                        Thread.sleep(1000);
                        System.out.println("sleep 1000" + Thread.currentThread().getName());

                        // 此时队列中个数为1
                        System.out.println(executor.getQueue().size());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        // 测试3
        for (int i = 0; i < 14; i++) {
            executor.execute(() -> {
                try {
                    while (true) {
                        Thread.sleep(1000);
                        System.out.println("sleep 1000" + Thread.currentThread().getName());

                        // 此时队列中个数为11
                        System.out.println(executor.getQueue().size());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
