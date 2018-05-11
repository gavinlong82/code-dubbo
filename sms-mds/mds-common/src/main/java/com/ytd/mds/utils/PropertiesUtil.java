package com.ytd.mds.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.data.Stat;

public class PropertiesUtil {

	public static void loadPoperties(InputStream is) throws Exception {
		Properties props = new Properties();
		props.load(is); // 将输入流中的配置加载进Properties
		MdsConstants.appConfig.putAll(props);
		String path = props.getProperty("appConfig.path");
		CuratorFramework client = CuratorFrameworkFactory.builder()
				.connectString(props.getProperty("zookeeper.servers"))
				.sessionTimeoutMs(Integer.parseInt(props.getProperty("zookeeper.sessionTimeoutMs")))
				.connectionTimeoutMs(Integer.parseInt(props.getProperty("zookeeper.connectionTimeoutMs")))
				.retryPolicy(
						new ExponentialBackoffRetry(Integer.parseInt(props.getProperty("zookeeper.baseSleepTimeMs")),
								Integer.parseInt(props.getProperty("zookeeper.maxRetries"))))
				.build();

		client.start();
		Stat stat = new Stat();
		List<String> list = client.getChildren().storingStatIn(stat).forPath(path);
		String value = null;
		String childPath = null;
		for (String key : list) {
			childPath = path + "/" + key;
			value = new String(client.getData().forPath(childPath), "UTF-8");
			MdsConstants.appConfig.put(key, value); // 加入配置map中
		}
		Thread.sleep(100);
		client.close();
	}

	/**
	 * kafka.properties
	 * 
	 * @param is
	 * @author gavinlong
	 * @throws IOException
	 */
	public static void loadKafkaProperties(InputStream is) throws IOException {
		Properties props = new Properties();
		props.load(is);
		MdsConstants.kafkaConfig.putAll(props);
		configureSasl();
	}

	/**
	 * SASL验证配置
	 * 
	 * @author gavinlong
	 */
	public static void configureSasl() {
		// 如果用-D或者其它方式设置过，这里不再设置
		if (null == System.getProperty("java.security.auth.login.config")) {
			// 请注意将XXX修改为自己的路径
			// 这个路径必须是一个文件系统可读的路径，不能被打包到jar中
			System.setProperty("java.security.auth.login.config",
					MdsConstants.kafkaConfig.getProperty("java.security.auth.login.config"));
		}
	}
}
