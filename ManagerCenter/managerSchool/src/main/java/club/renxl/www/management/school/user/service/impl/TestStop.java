package club.renxl.www.management.school.user.service.impl;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;
public class TestStop {
	private static final int[] array = new int[80000];
	private static final Thread t = new Thread() {
		public void run() {
			try {
				System.out.println("++++++++++++++");
				System.out.println("++++++++++++++"+sort(array));
			} catch (Error err) {
				System.out.println("==============error");
			}
			System.out.println("in thread t");
		}
	};
	
	static {
		Random random = new Random();
		for(int i = 0; i < array.length; i++) {
			array[i] = random.nextInt(i + 1);
		}
	}
	
	private static int sort(int[] array) {
		for (int i = 0; i < array.length-1; i++){
			for(int j = 0 ;j < array.length - i - 1; j++){
				if(array[j] < array[j + 1]){
					int temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
				}
			}
		}
		return array[0];
	}
	
	public static void main(String[] args) throws Exception {
		t.start();
		TimeUnit.SECONDS.sleep(1);
		System.out.println("go to stop thread t");
		t.stop();
		System.out.println("finish main");
		System.exit(0);
	}
}