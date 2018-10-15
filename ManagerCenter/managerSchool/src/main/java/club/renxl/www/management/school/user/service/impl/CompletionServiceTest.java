package club.renxl.www.management.school.user.service.impl;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletionServiceTest {

	static class Task implements Callable<String> {
		private int i;

		public Task(int i) {
			this.i = i;
		}

		@Override
		public String call() throws Exception {
			Thread.sleep(10000);
			return Thread.currentThread().getName() + "执行完任务：" + i;
		}
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		testExecutorCompletionService();
	}

	private static void testExecutorCompletionService() throws InterruptedException, ExecutionException {
		int numThread = 5;
		ExecutorService executor = Executors.newFixedThreadPool(numThread);
		CompletionService<String> completionService = new ExecutorCompletionService<String>(executor);
		for (int i = 0; i < numThread; i++) {
			completionService.submit(new CompletionServiceTest.Task(i));
		}

		for (int i = 0; i < numThread; i++) {
			System.out.println(completionService.take().get());
		}
		 
		// 与此具有相同功能还有
		//		futures = executor.invokeAll(tasks) 但这个的返回集合不是一个队列，存在串行化
		// completionService.take().get()是不存在顺序性的，谁先执行完就取谁

	}
}