package com.example.initial_frame.common.thread;

public class Test_Thread {
    public static void main(String[] args) {
        // 创建 MyThread 测试类对象
        MyThread myThread = new MyThread();
        // 调用 start() 方法启动线程
        myThread.start();
    }

    static class MyThread extends Thread{ // 继承 Thread 类
        // 子类覆盖 Thread 类中的 run() 方法
        @Override
        public void run() {
            System.out.println("线程已启动！！！");
        }
    }
}
