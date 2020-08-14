package threadpool1;

import java.util.concurrent.LinkedBlockingQueue;

public class ThreadPool {
	
	private final int numOfThreads;
	//private final PoolWorker[] threads;
    private final LinkedBlockingQueue queue;
 
    public ThreadPool(int numOfThreads) {
    	System.out.println("Thread number :"+numOfThreads);
        this.numOfThreads = numOfThreads;
        queue = new LinkedBlockingQueue();
        //threads = new PoolWorker[numOfThreads];
 
        for (int i = 0; i < numOfThreads; i++) {
            //threads[i] = new PoolWorker();
            new Thread() {
            	public void run() {
                    Runnable task; 
                    while (true) {
                        synchronized (queue) {
                            while (queue.isEmpty()) {
                                try {
                                    queue.wait();
                                } catch (InterruptedException e) {
                                    System.out.println("An error occurred while queue is waiting: " + e.getMessage());
                                }
                            }
                            task = (Runnable) queue.poll();
                        }
         
                        try {
                            task.run();
                        } catch (RuntimeException e) {
                            System.out.println("Thread pool is interrupted due to an issue: " + e.getMessage());
                        }
                    }
                }
            }.start();
            //threads[i].start();
        }
    }
 
    public void execute(Runnable task) {
        synchronized (queue) {
        	System.out.println("Queued task :");
            queue.add(task);
            queue.notify();
        }
    }
 
    /*private class PoolWorker extends Thread {
        public void run() {
            Runnable task; 
            while (true) {
                synchronized (queue) {
                    while (queue.isEmpty()) {
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            System.out.println("An error occurred while queue is waiting: " + e.getMessage());
                        }
                    }
                    task = (Runnable) queue.poll();
                }
 
                try {
                    task.run();
                } catch (RuntimeException e) {
                    System.out.println("Thread pool is interrupted due to an issue: " + e.getMessage());
                }
            }
        }
    }*/

}
