package threadpool1;

public class Task implements Runnable {
	
	int numberOfTasks;
	public Task(int taskNum) {
		System.out.println("Task Number :"+taskNum);
		int numberOfTasks = taskNum;
		System.out.println("numberOfTasks :"+numberOfTasks);
	}
	
	public void run() {
		System.out.println("Running : Task : "+numberOfTasks);
	}

}
