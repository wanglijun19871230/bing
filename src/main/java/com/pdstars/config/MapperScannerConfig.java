package com.pdstars.config;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;

/**
 * Mapper扫描配置类.
 * 
 * @ClassName: MapperScannerConfig
 * @Description: Mapper扫描配置
 * @author yujunnan
 * @date 2017年4月29日 上午10:01:50
 *
 */
// 标识当前为配置类
@SpringBootConfiguration
// 保证在MyBatisConfig实例化之后再实例化该类
@AutoConfigureAfter(MybatisConfig.class)
public class MapperScannerConfig {
	// mapper接口的扫描器
    @Bean
	public static  MapperScannerConfigurer mapperScannerConfigurer() {
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		mapperScannerConfigurer.setBasePackage("com.pdstars.dal");
		return mapperScannerConfigurer;
	}

}
