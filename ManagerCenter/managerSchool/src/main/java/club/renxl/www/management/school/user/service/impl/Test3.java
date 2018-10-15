package club.renxl.www.management.school.user.service.impl;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Test3 {
	// 闭锁 countdownLATCH FUTUREtASK future.get为闭锁环节
	// 线程交替打印
	// 竞争一个所资源+线程状态通知彼此，加一个标志位，多少线程标志位多少取值
	public static void main(String[] args) throws InterruptedException {
		System.out.println(1);
		System.out.println(12);
		System.out.println(13);
		int i = 1;
		System.out.println(Thread.currentThread().isInterrupted());
		Thread.currentThread().interrupt();//

		System.out.println(Thread.currentThread().isInterrupted());

		boolean f = Thread.currentThread().interrupted();
		System.out.println(" ====================" + f);

		System.out.println(Thread.currentThread().isInterrupted());
		BlockingQueue<String> bq = new ArrayBlockingQueue<String> (4);
		// 会检测线程是否中断
		bq.put("s");
		System.out.println(121);
	}

}
