package com.ytd.mds.task;

import java.util.Properties;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.config.SslConfigs;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.ytd.mds.callback.impl.DubboConsumerCallback;
import com.ytd.mds.utils.MdsConstants;
import com.ytd.mds.utils.PropertiesUtil;

/**
 * kafka消费组任务
 * 
 * @description
 * @project: mds-common
 * @Date:2018年4月16日
 * @version 1.0
 * @Company: YTD
 * @author gavinlong
 */
@Component("kafkaConsumerGrpTask")
public class ConsumerGroupTask implements InitializingBean {

	@Autowired
	@Qualifier("dubboConsumerCallback")
	private DubboConsumerCallback dubboConsumerCallback;

	@Override
	public void afterPropertiesSet() throws Exception {

		// SASL验证配置
		PropertiesUtil.configureSasl();

		/*
		 * 设置消费者通用Properties属性
		 */
		Properties props = new Properties();
		props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
				MdsConstants.kafkaConfig.getProperty("bootstrap.servers").trim());

		// 设置 SSL 根证书的路径，请记得将 XXX 修改为自己的路径
		// 与 sasl 路径类似，该文件也不能被打包到 jar 中
		props.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG,
				MdsConstants.kafkaConfig.getProperty("ssl.truststore.location"));
		// 根证书 store 的密码，保持不变
		props.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, "KafkaOnsClient");
		// 接入协议，目前支持使用 SASL_SSL 协议接入
		props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_SSL");
		// SASL 鉴权方式，保持不变
		props.put(SaslConfigs.SASL_MECHANISM, "ONS");

		// 两次poll之间的最大允许间隔
		// 请不要改得太大，服务器会掐掉空闲连接，不要超过30000
		props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 25000);

		// 每次poll的最大数量
		// 注意该值不要改得太大，如果poll太多数据，而不能在下次poll之前消费完，则会触发一次负载均衡，产生卡顿
		props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 30);
		props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
				MdsConstants.kafkaConfig.getProperty("key.deserializer").trim());
		props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
				MdsConstants.kafkaConfig.getProperty("value.deserializer").trim());
		props.setProperty(ConsumerConfig.GROUP_ID_CONFIG, MdsConstants.kafkaConfig.getProperty("group.id").trim());

		/*
		 * props.setProperty("enable.auto.commit",
		 * MdsConstants.kafkaConfig.getProperty("enable.auto.commit").trim());
		 * props.setProperty("auto.commit.interval.ms",
		 * MdsConstants.kafkaConfig.getProperty("auto.commit.interval.ms").trim())
		 * ; props.setProperty("dubbo_core.topics",
		 * MdsConstants.kafkaConfig.getProperty("dubbo_core.topics").trim());
		 */

		// 获取消费组topic，并创建消费者组
		ConsumerGroup group = new ConsumerGroup(MdsConstants.kafkaConfig.getProperty("spring.test.producer.topic").trim(),
				dubboConsumerCallback, props);
		group.setTimeout(Integer.parseInt(MdsConstants.kafkaConfig.getProperty("consumer.poll.timeout").trim())); // 设置poll超时
		group.buildAndRunConsumers(Integer.parseInt(MdsConstants.kafkaConfig.getProperty("consumer.thread.nums").trim())); // 传入消费者数，并启动消费者线程
	}
}
