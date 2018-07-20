package com.pdstars;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * 启动类.
 * 
 * @ClassName: BootApplication
 * @Description: 用于启动项目
 * @author yujunnan
 * @date 2017年4月30日 下午3:13:37
 *
 */
@SpringBootApplication
// 配置扫描包
@ComponentScan(basePackages = "com.pdstars")
@EnableFeignClients
@EnableDiscoveryClient // 允许发现服务
public class BootApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(BootApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(BootApplication.class);
	}

}
