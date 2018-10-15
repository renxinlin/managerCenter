package club.renxl.www.management.school.user.service.impl;

import java.lang.reflect.Field;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;
import java.util.concurrent.RejectedExecutionException;

public class AbortPolicyDemo {

	private static final int THREADS_SIZE = 2;
	private static final int CAPACITY = 2;

	public static void main(String[] args) throws Exception {

		// 创建线程池。线程池的"最大池大小"和"核心池大小"都为1(THREADS_SIZE)，"线程池"的阻塞队列容量为1(CAPACITY)。
		ThreadPoolExecutor pool = new ThreadPoolExecutor(THREADS_SIZE, THREADS_SIZE, 0, TimeUnit.SECONDS,
				new ArrayBlockingQueue<Runnable>(CAPACITY));
		// 设置线程池的拒绝策略为"抛出异常"
		// 中止策略在exrcute执行之前抛出异常
		pool.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());

		// 新建10个任务，并将它们添加到线程池中。
		for (int i = 0; i < 10; i++) {
			System.out.println(i);
			Runnable myrun = new MyRunnable("task-" + i);
			try {
				pool.execute(myrun);
			} catch (RejectedExecutionException e) {
				System.out.println("--");
				// 关闭线程池
			}
		}
		pool.shutdown();

	}
}

class MyRunnable implements Runnable {
	private String name;

	public MyRunnable(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(200);
			System.out.println(this.name + " is running.");
		} catch (Exception e) {
			System.out.println(this.name + "errror");
		}
	}
}