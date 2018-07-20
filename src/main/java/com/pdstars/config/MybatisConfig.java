package com.pdstars.config;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.github.pagehelper.PageHelper;
import com.pdstars.manager.interceptor.DBInterceptor;

/**
 * Mybatis配置.
 * 
 * @ClassName: MybatisConfig
 * @Description: 配置Mybatis
 * @author yujunnan
 * @date 2017年4月30日 下午3:31:29
 *
 */
@SpringBootConfiguration
public class MybatisConfig {

	@Autowired
	private DataSource dataSource;

	@Value("${dbinterceptor.enable}")
	int isEnableDBInterceptor;

	@Bean
	@ConditionalOnMissingBean
	// 当容器里没有指定的Bean的情况下创建该对象
	public SqlSessionFactoryBean sqlSessionFactory(DBInterceptor dbInterceptor) {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		// 设置数据源
		sqlSessionFactoryBean.setDataSource(dataSource);
		// 设置mybatis的主配置文件
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

		if (isEnableDBInterceptor == 1) {
			Interceptor[] plugins = new Interceptor[] { pageHelper(), dbInterceptor };
			sqlSessionFactoryBean.setPlugins(plugins);
		} else {
			Interceptor[] plugins = new Interceptor[] { pageHelper() };
			sqlSessionFactoryBean.setPlugins(plugins);
		}

		Resource mybatisConfigXml = resolver.getResource("classpath:mybatis/mybatis-config.xml");
		sqlSessionFactoryBean.setConfigLocation(mybatisConfigXml);
		// 设置别名包
		sqlSessionFactoryBean.setTypeAliasesPackage("com.pdstars.dal.dataobject");

		// 设置映射mapper位置
		try {
			sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:sqlmap/*.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sqlSessionFactoryBean;
	}

	@Bean
	public PageHelper pageHelper() {
		System.out.println("MyBatisConfiguration.pageHelper()");
		PageHelper pageHelper = new PageHelper();
		Properties p = new Properties();
		p.setProperty("offsetAsPageNum", "true");
		p.setProperty("rowBoundsWithCount", "true");
		p.setProperty("reasonable", "true");
		pageHelper.setProperties(p);
		return pageHelper;
	}
}
