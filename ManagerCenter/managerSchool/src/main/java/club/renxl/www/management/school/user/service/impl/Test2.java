package club.renxl.www.management.school.user.service.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Test2 {
	 
	public static void main(String[] args) throws InterruptedException {
		 HashMap a = new HashMap();
		 a.put("1","1");
		 a.put("11","11");
		 a.put("111","111");
		 // shuffle copy ;living alive
		 Map a1 =Collections.unmodifiableMap(a);
		 // deep copy
		 Map a2 = Collections.unmodifiableMap(new HashMap(a));
		 a.put("1111","1111");

		 for(Object i :a1.entrySet()) {
			 System.out.println(i);
		 }			 
		 System.out.println("===============");

		 
		 for(Object i :a2.entrySet()) {
			 System.out.println(i);
		 }
	}
	
}
