package leetcode.MultiThread;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
//设计阻塞队列

/**
 * 实现一个拥有如下方法的线程安全有限阻塞队列：
 * <p>
 * BoundedBlockingQueue(int capacity) 构造方法初始化队列，其中capacity代表队列长度上限。
 * void enqueue(int element) 在队首增加一个element. 如果队列满，调用线程被阻塞直到队列非满。
 * int dequeue() 返回队尾元素并从队列中将其删除. 如果队列为空，调用线程被阻塞直到队列非空。
 * int size() 返回当前队列元素个数。
 * 你的实现将会被多线程同时访问进行测试。每一个线程要么是一个只调用enqueue方法的生产者线程，要么是一个只调用dequeue方法的消费者线程。size方法将会在每一个测试用例之后进行调用。
 * <p>
 * 解决方法：
 * 就是生产者消费者问题，可以采用synchronized加等待通知机制，这里用lock和等待通知机制实现一下
 */
class BoundedBlockingQueue {

    private LinkedList<Integer> queue = new LinkedList<>();
    private ReentrantLock lock = new ReentrantLock();
    private Condition empty = lock.newCondition();
    private Condition full = lock.newCondition();
    private Integer size = 0;
    private Integer cap = null;

    public BoundedBlockingQueue(int capacity) {
        if (cap == null) {
            lock.lock();
            try {
                if (cap == null) {
                    cap = capacity;
                }
            } finally {
                lock.unlock();
            }
        }
    }

    public void enqueue(int element) throws InterruptedException {
        lock.lock();
        try {
            while (size >= cap) {
                full.await();
            }
            queue.offerFirst(element);
            size += 1;
            empty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public int dequeue() throws InterruptedException {
        lock.lock();
        int res = -1;
        try {
            while (size == 0) {
                empty.await();
            }
            res = queue.pollLast();
            size -= 1;
            full.signalAll();
        } finally {
            lock.unlock();
        }
        return res;
    }

    public int size() {
        return size;
    }
}
