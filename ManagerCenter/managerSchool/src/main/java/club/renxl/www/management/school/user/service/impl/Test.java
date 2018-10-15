package club.renxl.www.management.school.user.service.impl;

public class Test {
	private static boolean ready;
	private static  int num;
	public static class ts extends Thread{
		public void run() {
			while(!ready) {
				Thread.yield();
				System.out.println("---");
			}
			System.out.println(num);
		}
	}
	public static void main(String[] args) throws InterruptedException {
		new ts().start();
		num = 77;
		ready = true;
	}
	
}
