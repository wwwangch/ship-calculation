package com.iscas.common.tools.thread;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/1/30 15:50
 * @since jdk1.8
 */
public class CompletableFutrueTests {
    private static ThreadPoolExecutor threadPoolExecutor = null;
    private static AtomicInteger atomicInteger = new AtomicInteger(1);

    @BeforeAll
    public static void before() {
        threadPoolExecutor = new ThreadPoolExecutor(2, 2, 0, TimeUnit.MILLISECONDS,
                new SynchronousQueue<Runnable>(), new ThreadFactory() {
            @Override
            public Thread newThread(@NotNull Runnable r) {
                return new Thread(r, "线程-" + atomicInteger.getAndIncrement());
            }
        });
    }


    /**
     * 测试使用CompletableFuture无返回值
     * */
    @Test
    public void test() {
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            System.out.println("没有返回值");
        }, threadPoolExecutor);
    }

    /**
     * 测试使用CompletableFuture有返回值
     * */
    @Test
    public void test2() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("有返回值");
            return "没返回值";
        }, threadPoolExecutor);
        String s = completableFuture.get();
        System.out.println("执行完了");
    }

    /**
     * 测试使用completableFutrue的whenComplete方法
     * */
    @Test
    public void test3() throws InterruptedException {
        CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("测试whenComplete");
            return 1;
        }).whenComplete((r, e) -> {
            System.out.println("执行完了");
        });
        System.out.println("任务提交");
        Thread.currentThread().join();
    }

    /**
     * 测试使用CompleteableFuture的exceptionally方法
     * */
    @Test
    public void test4() throws InterruptedException {
        CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("测试exceptionally");
            throw new RuntimeException("异常");
        }).exceptionally(e -> {
            System.out.println("出现了异常");
            e.printStackTrace();
            return 2;
        });
        System.out.println("任务提交");
        Thread.currentThread().join();
    }

    /**
     * 测试使用CompleteableFuture的exceptionally方法和whenComplete方法同时使用
     * 谁在前，谁先执行，而且不管有没有异常，whenComplete都会执行
     * */
    @Test
    public void test5() throws InterruptedException {
        CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("测试whenComplete和exceptionally混合使用");
            throw new RuntimeException("111");
        }).exceptionally(e -> {
            System.out.println("出现了异常");
            return 2;
        }).whenComplete((r, e) -> {
            System.out.println("结束了");
        });
        System.out.println("任务提交");
        Thread.currentThread().join();
    }

    /**
     * 测试使用CompleteableFuture的whenCompleteAsync方法,
     * 此方法让结果给不同得线程执行
     * */
    @Test
    public void test6() throws InterruptedException {
        CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
            return "测试whenCompleteAsync";
        }, threadPoolExecutor).whenCompleteAsync((r, e) -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println("完成了");
        }, threadPoolExecutor);
        System.out.println("任务提交");
        Thread.currentThread().join();
    }

    /**
     * 测试使用CompleteableFuture的thenApply方法,
     * 此方法适用于某个线程依赖另一个线程的执行结果,
     * 两个线程使用一个线程池
     * */
    @Test
    public void test7() throws InterruptedException {
        CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
            return 4;
        }, threadPoolExecutor).thenApply(r -> r + 1).whenComplete((r, e) -> {
            System.out.println(r);
            System.out.println("完成了");
        });
        System.out.println("任务提交");
        Thread.currentThread().join();
    }

    /**
     * 测试使用CompleteableFuture的thenApplyAsync方法,
     * 此方法适用于某个线程依赖另一个线程的执行结果,
     * 两个线程可以使用不同的线程池
     * */
    @Test
    public void test8() throws InterruptedException {
        CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
            return 4;
        }, threadPoolExecutor).thenApplyAsync(r -> {
            System.out.println(Thread.currentThread().getName());
            return r + 1;
        }, threadPoolExecutor).whenComplete((r, e) -> {
            System.out.println(r);
            System.out.println("完成了");
        });
        System.out.println("任务提交");
        Thread.currentThread().join();
    }

    /**
     * 测试使用CompleteableFuture的handle方法,
     * 此方法适用于某个线程依赖另一个线程的执行结果,可以处理异常
     * 两个线程使用一个线程池
     * */
    @Test
    public void test9() throws InterruptedException {
        CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
            return 4;
        }, threadPoolExecutor).handle((r, e) -> r + 1).whenComplete((r, e) -> {
            System.out.println(r);
            System.out.println("完成了");
        });
        System.out.println("任务提交");
        Thread.currentThread().join();
    }

    /**
     * 测试使用CompleteableFuture的thenApplyAsync方法,
     * 此方法适用于某个线程依赖另一个线程的执行结果,
     * 两个线程可以使用不同的线程池
     * */
    @Test
    public void test10() throws InterruptedException {
        CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
            throw new RuntimeException("111");
        }, threadPoolExecutor).handleAsync((r, e) -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println(e);
            if (e == null) {
                return 1;
            } else {
                return -1;
            }
        }, threadPoolExecutor).whenComplete((r, e) -> {
            System.out.println(r);
            System.out.println("完成了");
        });
        System.out.println("任务提交");
        Thread.currentThread().join();
    }

    /**
     * 测试使用CompleteableFuture的thenCombine方法,
     * 此方法适用于两个future的结果合并,
     * 两个线程用同一个线程池
     * */
    @Test
    public void test11() throws InterruptedException {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
            throw new RuntimeException("111");
        }, threadPoolExecutor).handleAsync((r, e) -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println(e);
            if (e == null) {
                return 1;
            } else {
                return -1;
            }
        }, threadPoolExecutor).whenComplete((r, e) -> {
            System.out.println(r);
            System.out.println("完成了");
        });
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            return 1;
        });
        future1.thenCombine(future2, (r1, r2) -> {
            return r1 + r2;
        }).whenComplete((r, e) -> {
            System.out.println("结果为:" + r);
        });
        System.out.println("任务提交");
        Thread.currentThread().join();
    }

    /**
     * 测试使用CompleteableFuture的thenCombineAsync方法,
     * 此方法适用于两个future的结果合并,
     * 两个线程用同一个线程池
     * */
    @Test
    public void test12() throws InterruptedException {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
            throw new RuntimeException("111");
        }, threadPoolExecutor).handleAsync((r, e) -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println(e);
            if (e == null) {
                return 1;
            } else {
                return -1;
            }
        }, threadPoolExecutor).whenComplete((r, e) -> {
            System.out.println(r);
            System.out.println("完成了");
        });
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            return 1;
        });
        future1.thenCombineAsync(future2, (r1, r2) -> {
            return r1 + r2;
        }, threadPoolExecutor).whenComplete((r, e) -> {
            System.out.println("结果为:" + r);
        });
        System.out.println("任务提交");
        Thread.currentThread().join();
    }

    /**
     * 测试使用CompleteableFuture的thenAcceptBoth方法,
     * 此方法适用于两个future的结果合并,与thenCombine的区别是没有返回值
     * 两个线程用同一个线程池, 如果是用不同谢娜城池使用thenAcceptBothAsync，不单独写测试了
     * */
    @Test
    public void test13() throws InterruptedException {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
            throw new RuntimeException("111");
        }, threadPoolExecutor).handleAsync((r, e) -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println(e);
            if (e == null) {
                return 1;
            } else {
                return -1;
            }
        }, threadPoolExecutor).whenComplete((r, e) -> {
            System.out.println(r);
            System.out.println("完成了");
        });
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            return 1;
        });
        future1.thenAcceptBoth(future2, (r1, r2) -> {
            System.out.println("结果为:" + (r1 + r2));
        }).whenComplete((r, e) -> {
            System.out.println("结果为:" + r);
        });
        System.out.println("任务提交");
        Thread.currentThread().join();
    }

    /**
     * 测试使用CompleteableFuture的allOf方法,
     * 此方法适用于等待多个future的执行结果
     *
     * */
    @Test
    public void test14() throws InterruptedException {
        int[] count = new int[1];
        CompletableFuture[] completableFutures = Arrays.asList(1, 1, 12, 34, -1, 5, -13).stream().map(i ->
                CompletableFuture.supplyAsync(() -> i).whenComplete((r, e) -> {
                    try {
                        TimeUnit.MILLISECONDS.sleep(200);
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                    System.out.println("一个线程完成，结果为:" + r);
                    count[0] += r;
                })).toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(completableFutures).whenCompleteAsync((r, e) -> {
            System.out.println("全部完成，结果为：" + count[0]);
        });

        System.out.println("任务提交");
        Thread.currentThread().join();
    }

}
