package threadpool1;

public class ThreadPoolDemo {

	public static void main(String[] args) {
		
		ThreadPool pool = new ThreadPool(10);
		for(int i = 0; i< 15; i++) {
			Task task = new Task(i);
			pool.execute(task);
		}
	}

}
