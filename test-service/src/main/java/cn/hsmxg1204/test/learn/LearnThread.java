package cn.hsmxg1204.test.learn;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.*;

/**
 * TODO
 *
 * @author gxming
 * @description
 * @date 2021-06-07 14:19
 */
public class LearnThread {
}

/**
 * 中断线程
 * 在其他线程中对目标线程调用interrupt()方法，
 * 目标线程需要反复检测自身状态是否是interrupted状态，
 * 如果是，就立刻结束运行。
 */
class InterruptThread {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new InterruptThreadTest();
        t.start();
        Thread.sleep(1); // 暂停1毫秒
        t.interrupt(); // 中断t线程
        t.join(); // 等待t线程结束
        System.out.println("end");
    }
}

class InterruptThreadTest extends Thread {
    public void run() {
        int n = 0;
        while (! isInterrupted()) {
            n ++;
            System.out.println(n + " hello!");
        }
    }
}


class HelloThread {
    public static void main(String[] args)  throws InterruptedException {
        HelloThreadTest t = new HelloThreadTest();
        t.start();
        Thread.sleep(1);
        t.running = false; // 标志位置为false
    }
}

class HelloThreadTest extends Thread {
    public volatile boolean running = true;
    public void run() {
        int n = 0;
        while (running) {
            n ++;
            System.out.println(n + " hello!");
        }
        System.out.println("end!");
    }
}
// 线程同步
class synThread{
    public static void main(String[] args) throws InterruptedException {
        Thread add = new AddThread();
        Thread dec = new DecThread();
        add.start();
        dec.start();
        add.join();
        dec.join();
        System.out.println(Counter.count);
    }
}

class Counter{
    public static int count = 0;
}

class AddThread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            synchronized (this) {
                Counter.count += 1;
            }
        }
    }
}

class DecThread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            synchronized(this) {
                Counter.count -= 1;
            }
        }
    }
}

/**
 *  使用wait 和 notify
 */
class TaskQueue{
    public static void main(String[] args) throws InterruptedException {
        Task task = new Task();
        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(() -> {
                while (true){
                    try {
                        String s = task.getTask();
                        System.out.println("execute task : " + s);
                    } catch (InterruptedException e) {
                        return ;
                    }
                }
            });
            thread.start();
            threads.add(thread);
        }

        Thread add = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                String s = "t-" + Math.random();
                System.out.println(" and task : "+ s);
                task.addTask(s);
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e){

                }
            }
        });
        add.start();
        add.join();

        Thread.sleep(1000);

        for (Thread t:threads){
            t.interrupt();
        }

    }
}
class Task{
    Queue<String> queue = new LinkedList<>();

    public synchronized void addTask(String s){
        this.queue.add(s);
        this.notify();
    }

    public synchronized String getTask() throws InterruptedException {
        while(queue.isEmpty()){
            this.wait();
        }
        return queue.remove();
    }
}

class ThreadOfReentrantLock{
    class Counter{
        private final Lock lock = new ReentrantLock();
        private int count;

        public void add(int n){
            lock.lock();
            try{
                count += n;
            }finally {
                lock.unlock();
            }
        }
    }
}

/**
 * ReentrantLock 锁
 * 使用Condition 实现wait() 和 notify()
 * synchronized 是java语言层面提供的语法，不需要考虑异常
 * ReentrantLock 是java代码实现的锁，需要先获取锁，在finally中正确释放锁。
 */
class ConditionTaskQueue{
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private Queue<String> queue = new LinkedList<>();

    public void addTask(String s){
        lock.lock();
        try{
            queue.add(s);
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }

    public String getTask(){
        lock.lock();
        try{
            while (queue.isEmpty()){
                condition.await();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return queue.remove();
    }

}

/**
 * 使用ReadWriteLock
 */
class ReadWriteLockCounter{
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    private final Lock rLock = rwLock.readLock();
    private final Lock wLock = rwLock.writeLock();
    private int[] counts = new int[0];

    public void inc(int index){
        wLock.lock(); // 加写锁
        try{
            counts[index] += 1;
        }finally {
            wLock.unlock(); // 释放锁
        }
    }

    public int[] get(){
        rLock.lock();// 加读锁
        try{
            return Arrays.copyOf(counts,counts.length);
        }finally {
            rLock.unlock(); // 释放读锁
        }
    }
}

class Point{
    private  final StampedLock stampedLock = new StampedLock();
    private double x;
    private double y;

    public void move(double deltaX,double deltaY){
        // 获取写锁
        long stamp = stampedLock.writeLock();
        try{
            x += deltaX;
            y += deltaY;
            System.out.println("x : "+x+"\t y: " + y);
        }finally {
            // 释放写锁
            stampedLock.unlockWrite(stamp);
        }
    }

    public double distanceFromOrigin(){
        // 获取乐观读锁
        long read = stampedLock.tryOptimisticRead();
        double currentX = x;
        double currentY = y;
        // 检查乐观读锁后时候有其他写锁发生
        if(!stampedLock.validate(read)){
            // 获取一个悲观读锁
            read = stampedLock.readLock();
            try{
                currentX = x;
                currentY = y;
                System.out.println("currentX : "+currentX+"\t currentY : "+currentY);
            }finally {
                stampedLock.unlockRead(read);
            }
        }
        return Math.sqrt(currentX * currentX + currentY * currentY);
    }

    public static void main(String[] args) throws InterruptedException {
        Point p= new Point();
        Point.MyTestThreadForWrite t = p.new MyTestThreadForWrite();
        Point.MyTestThreadForRead w = p.new MyTestThreadForRead();
        for (int i = 0; i < 10; i++) {
            t.start();
            t.join();
            w.start();
        }
    }

    class MyTestThreadForWrite extends Thread{

        @Override
        public void run() {
            synchronized (this){
                Point point = new Point();
                point.move(200,300);
            }
        }
    }

    class MyTestThreadForRead extends Thread{

        @Override
        public void run() {
            synchronized (this){
                Point point = new Point();
                point.distanceFromOrigin();
            }
        }
    }
}