package com.example.initial_frame.common.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class Test_Callable {
    public static void main(String[] args) {
        // 创建 Callable 实例
        Callable<String> callable = new MyCallable();
        // 通过FutureTask来获取 call() 返回的结果
        FutureTask<String> futureTask = new FutureTask<>(callable);
        new Thread(futureTask).start();
        try {
            // 阻塞
            String temp = futureTask.get();
            System.out.println("返回值为："+ temp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static class MyCallable implements Callable<String>{
        // 重写 call() 方法
        @Override
        public String call() throws Exception {
            // 休眠 3 秒
            Thread.sleep(3000);
            return "Hello World";
        }
    }
}
