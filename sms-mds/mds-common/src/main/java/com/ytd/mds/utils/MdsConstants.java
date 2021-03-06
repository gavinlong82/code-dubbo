package com.ytd.mds.utils;

import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public class MdsConstants {

	// 写库对应的数据源key
	public static final String MASTER_DATASOURCE = "master";
	public static final String SLAVE1_DATASOURCE = "slave1";
	public static final String SLAVE2_DATASOURCE = "slave2";

	// slave模式编号(随机)
	public static final String SLAVE_DATASOURCE_CODE = "replica-set-slave-999999-host";

	// 任务唯一标识
	public static final String TASK_ID = "taskId";
	public static final String PATH_SPLITER = "/"; // 路径分隔符
	public static final String FILE_SUFFIX_SPLITER = "."; // 文件后缀名分隔符

	/*
	 * 发送方式
	 */
	public static final String EMAIL = "email";
	public static final String FTP = "ftp";
	public static final String SFTP = "sftp";

	public static final String OUTER_FILE_PREFIX = "file://"; // 外部文件头标记

	public static final String DEFAULT_ENCODING = "UTF-8"; // 默认编码

	public static final Properties appConfig = new Properties(); // 应用配置项
	
	public static final Properties kafkaConfig = new Properties(); // Kafka配置项

	public static final Queue<ConsumerRecord<String, String>> reConsumeMsgQueue = new ConcurrentLinkedQueue<ConsumerRecord<String, String>>();
}
