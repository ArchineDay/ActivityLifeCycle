package com.example.activityjump.test;

import com.example.activityjump.thread.RunnableDemo;

public class TestThread {
    public static void main(String[] args) {
        RunnableDemo runnableDemo1 = new RunnableDemo("Thread-1");
        runnableDemo1.start();

        RunnableDemo runnableDemo2 = new RunnableDemo("Thread-2");
        runnableDemo2.start();
    }
}