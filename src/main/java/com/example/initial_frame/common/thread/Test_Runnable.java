package com.example.initial_frame.common.thread;

public class Test_Runnable {

    public static void main(String[] args) {
        // 创建 MyRunnable 测试类
        MyRunnable myRunnable = new MyRunnable();
        // 使用参数为Runnable对象的构造方法创建Thread实例,并调用 start() 方法启动线程
        new Thread(myRunnable).start();
    }

    static class MyRunnable implements Runnable{  // 继承 Runnable 接口

        // 重写 run() 方法
        @Override
        public void run() {
            // 获取当前线程名称
            System.out.println("当前线程名称：");
            System.out.println(Thread.currentThread().getName()+": "+"Hello World");
        }
    }
}
