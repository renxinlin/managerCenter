package club.renxl.www.management;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

 
@SpringBootApplication
@ServletComponentScan//配置自定义druid页面的servlet扫描
@EnableEurekaClient // 每一个微服务都是一个eureka客户端
@MapperScan("club.renxl.www.management.school.user.dao")
@EnableTransactionManagement
@EnableAsync
public class SchoolManagement {
	public static void main(String[] args) {
		new SpringApplicationBuilder(SchoolManagement.class).web(true).run(args);

	}
}
