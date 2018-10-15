package club.renxl.www.management.school.user.service.impl;

import java.util.concurrent.ExecutionException;

public class Test7 {
	
	static int i =0,j=0;
	static	int x =0,y=0;
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				i =1;
				x=j;
				
			}}).start(); 
		
		new Thread(new Runnable(){

			@Override
			public void run() {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// TODO Auto-generated method stub
				j=1;
				y=i;
			}}).start(); 
		
		int ii =0;
		while(ii<1000) {
			ii++;
			System.out.println();
		}
		// 本例说明线程启动是符合happen-brfore协议，sleep导致但cpu对内存读写发生同步问题，如果去掉slepp则JVM下肯定是1101的输出，其他语言不一定
		System.out.println(i);

		System.out.println(j);

		System.out.println(x);

		System.out.println(y);
}
}
