package com.ytd.mds;

import java.io.File;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

import com.ytd.mds.task.CheckZKConfig;
import com.ytd.mds.utils.PropertiesUtil;

@ImportResource("classpath:spring/*-dubbo.xml")
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class })
public class UcBootStrap {
	private static final Logger logger = LoggerFactory.getLogger(UcBootStrap.class);

	public static void main(String[] args) {
		try {

			// kafka配置
			InputStream kafkaIs = UcBootStrap.class.getClassLoader().getResourceAsStream("kafka.properties");
			PropertiesUtil.loadKafkaProperties(kafkaIs);
			
			InputStream is = UcBootStrap.class.getClassLoader().getResourceAsStream("application.properties");
			PropertiesUtil.loadPoperties(is);


			SpringApplication application = new SpringApplication(UcBootStrap.class);
			ConfigurableApplicationContext applicationContext = application.run(args);
			CheckZKConfig checkZKConfig = new CheckZKConfig(application, applicationContext); // 设置应用和上下文
			checkZKConfig.check(args); // zookeeper变动校验
			String projectPath = System.getProperty("user.dir"); // 当前工程路径
			String projectName = projectPath.substring(projectPath.lastIndexOf(File.separator) + 1).replace("-", "");
			logger.info("*******************");
			logger.info("Project name:" + projectName + "; APP ctxId:" + applicationContext.getId());
			logger.info("Start success!");
			logger.info("*******************");
			System.in.read();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
