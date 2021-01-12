package leetcode.MultiThread;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

/**
 * 1114. 按序打印
 * <p>
 * 我们提供了一个类：
 * <p>
 * public class Foo {
 *   public void first() { print("first"); }
 *   public void second() { print("second"); }
 *   public void third() { print("third"); }
 * }
 * 三个不同的线程将会共用一个 Foo 实例。
 * <p>
 * 线程 A 将会调用 first() 方法
 * 线程 B 将会调用 second() 方法
 * 线程 C 将会调用 third() 方法
 * 请设计修改程序，以确保 second() 方法在 first() 方法之后被执行，third() 方法在 second() 方法之后被执行。
 * <p>
 * <p>
 * 示例 1:
 * 输入: [1,2,3]
 * 输出: "firstsecondthird"
 * 解释:
 * 有三个线程会被异步启动。
 * 输入 [1,2,3] 表示线程 A 将会调用 first() 方法，线程 B 将会调用 second() 方法，线程 C 将会调用 third() 方法。
 * 正确的输出是 "firstsecondthird"。
 * 示例 2:
 * <p>
 * 输入: [1,3,2]
 * 输出: "firstsecondthird"
 * 解释:
 * 输入 [1,3,2] 表示线程 A 将会调用 first() 方法，线程 B 将会调用 third() 方法，线程 C 将会调用 second() 方法。
 * 正确的输出是 "firstsecondthird"。
 *  
 * 提示：
 * 尽管输入中的数字似乎暗示了顺序，但是我们并不保证线程在操作系统中的调度顺序。
 * 你看到的输入格式主要是为了确保测试的全面性。
 */
class Foo {

    public Foo() {

    }

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
    }

    public void second(Runnable printSecond) throws InterruptedException {

        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
    }

    public void third(Runnable printThird) throws InterruptedException {

        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}

/**
 * 方法1：使用锁
 * 构造两道屏障
 */
class Foo1 {


    private boolean firstFinished;
    private boolean secondFinished;
    private Object lock = new Object();


    public Foo1() {

    }

    public void first(Runnable printFirst) throws InterruptedException {

        synchronized (lock) {
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            firstFinished = true;
            lock.notifyAll();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {


        synchronized (lock) {
            while (!firstFinished) {
                lock.wait();
            }
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            secondFinished = true;
            lock.notifyAll();
        }
    }

    public void third(Runnable printThird) throws InterruptedException {


        synchronized (lock) {
            while (!secondFinished) {
                lock.wait();
            }
            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
        }
    }

}

/**
 * 方法2 通过信号量题解
 */
class Foo2 {

    Semaphore A;
    Semaphore B;
    Semaphore C;

    public Foo2() {
        // 同步关键类，构造方法传入的数字是多少，则同一个时刻，只运行多少个进程同时运行制定代码
        A = new Semaphore(1);
        B = new Semaphore(0);
        C = new Semaphore(0);

    }

    public void first(Runnable printFirst) throws InterruptedException {

        /**
         * 在 semaphore.acquire() 和 semaphore.release()之间的代码，同一时刻只允许制定个数的线程进入，
         * 因为semaphore的构造方法是1，则同一时刻只允许一个线程进入，其他线程只能等待。
         * */
        A.acquire();
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        B.release();
    }

    public void second(Runnable printSecond) throws InterruptedException {

        B.acquire();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        C.release();
    }

    public void third(Runnable printThird) throws InterruptedException {

        C.acquire();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}

/**
 * 方法3
 * 使用 CountDownLatch 题解
 */
class Foo3 {

    CountDownLatch latch1;
    CountDownLatch latch2;

    public Foo3() {

        latch1 = new CountDownLatch(1);
        latch2 = new CountDownLatch(2);
    }

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        //将count值减1
        latch1.countDown();
        latch2.countDown();
    }

    public void second(Runnable printSecond) throws InterruptedException {
       //调用await()方法的线程会被挂起，它会等待直到count值为0才继续执行
        latch1.await();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        latch2.countDown();
    }

    public void third(Runnable printThird) throws InterruptedException {

        latch2.await();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}


class RunnableTest {
    public static void main(String[] args) throws InterruptedException {

        PrintFirst printFirst = new PrintFirst();
        new Thread(printFirst).start();
        PrintSecond printSecond = new PrintSecond();
        new Thread(printSecond).start();
        PrintThird printThird = new PrintThird();
        new Thread(printThird).start();

        Foo foo = new Foo();
        foo.second(printSecond);
        foo.third(printThird);
        foo.first(printFirst);

    }
}

class PrintFirst implements Runnable {

    @Override
    public void run() {

        System.out.println("打印第1个");
    }

}

class PrintSecond implements Runnable {

    @Override
    public void run() {

        System.out.println("打印第2个");
    }

}

class PrintThird implements Runnable {

    @Override
    public void run() {

        System.out.println("打印第3个");
    }

}