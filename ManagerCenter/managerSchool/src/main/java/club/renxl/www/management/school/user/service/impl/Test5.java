package club.renxl.www.management.school.user.service.impl;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Test5 {
	// 闭锁 countdownLATCH FUTUREtASK future.get为闭锁环节
	// 线程交替打印
	// 竞争一个所资源+线程状态通知彼此，加一个标志位，多少线程标志位多少取值
	public static void main(String[] args)  {
		ExecutorService es = Executors.newCachedThreadPool();
		// 闭锁的一种实现方式
		Future<String> resultLatch = es.submit(new Callable<String>() {
			// 工作队列
			@Override
			public String call() throws Exception {
				// TODO Auto-generated method stub
				
				int i = 2;
				if (i == 1)
					throw new InterruptedException();
				
				int x = i/0;
				
				return "test InterruptedException AND TIME OUT ";
			}

		});
		
		
		try {
			Object object = resultLatch.get();
			// 平滑取消任务
			resultLatch.cancel(false);
			System.out.println(object);
		} catch (InterruptedException e) {
			System.out.println(e.getCause());
			System.out.println("线程调度中断");
		} catch (ExecutionException e) {
			String message = e.getMessage();
			System.out.println("线程调度异常"+message);

		}
		//  jvm will closed util no daemon progress 
		es.shutdown();
	}

}
