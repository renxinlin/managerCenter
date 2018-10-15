package club.renxl.www.management.school.user.service.impl;

import java.math.BigInteger;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class Test6 {
	
	static class Incre{
		public static  AtomicInteger i = new AtomicInteger(0);

	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		int is = 0;
		BigInteger bigI =  BigInteger.ONE;

		while(is<1000) {
			BigInteger nextProbablePrime = bigI.nextProbablePrime();
			bigI = nextProbablePrime;
			System.out.println(nextProbablePrime.intValue());
			is = nextProbablePrime.intValue();
		}
		// 批处理任务
		int numThread = 4;
		ExecutorService executor = Executors.newFixedThreadPool(numThread);
		// 自定义Executors批处理的对象集合获取时候会阻塞

		// 批处理
		// 获取的对象是一个阻塞队列，通过生产者消费者模式降低延迟度
		final String hi = "1，2，3，4，6";
		CompletionService<String> completionService = new ExecutorCompletionService<String>(executor);
		for (int i =0;i<4;i++) {
			
			 Future<String> future = completionService.submit(new Callable<String>( ) {
	
				@Override
				public String call() throws Exception {
					try {
						AtomicInteger i2 = Incre.i;
						int incrementAndGet = i2.incrementAndGet();
						String[] split = hi.split("，");
						Thread.sleep(Integer.parseInt(split[incrementAndGet])*1000);
						System.out.println("shui jiao "+split[incrementAndGet]);
						return "shui jiao "+split[incrementAndGet];
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return "shui jiao ";

					}
				}
				
			});
			 try {
				String string = future.get();
					//String string = future.get(31111,TimeUnit.NANOSECONDS);
				System.out.println("=========="+string);

			} catch (Exception e) {
				System.out.println("==========");
			}
		}
		System.out.println("-sdsdf");
		
		for (int i = 0; i < 4; i++) {
			String string = completionService.take().get();
			System.out.println(string);
			executor.shutdown();
		}
	}

}
