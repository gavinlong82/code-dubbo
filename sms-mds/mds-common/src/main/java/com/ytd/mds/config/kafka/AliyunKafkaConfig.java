package com.ytd.mds.config.kafka;

import java.util.Properties;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.config.SslConfigs;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ytd.mds.utils.MdsConstants;
import com.ytd.mds.utils.PropertiesUtil;

/**
 * 基于阿里云的kafka消息中间件的配置
 * 
 * @description
 * @project: mds-common
 * @Date:2018年4月13日
 * @version 1.0
 * @Company: YTD
 * @author gavinlong
 */
@Configuration
public class AliyunKafkaConfig {
	@Bean("kafkaProducer")
	public Producer<String, String> kafkaProducer() throws Exception {
		Properties kafkaProperties = getProperties();
		return new KafkaProducer<String, String>(kafkaProperties);
	}

	private Properties getProperties() {
		// SASL验证配置
		PropertiesUtil.configureSasl();

		Properties kafkaProperties = new Properties();
		kafkaProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
				MdsConstants.kafkaConfig.getProperty("bootstrap.servers"));
		kafkaProperties.put("topic", MdsConstants.kafkaConfig.getProperty("topic"));
		kafkaProperties.put(ConsumerConfig.GROUP_ID_CONFIG, MdsConstants.kafkaConfig.getProperty("group.id"));

		// 消息确认模式-producer kafkaProperties.put("acks",
		// MdsConstants.appConfig.getProperty("acks"));
		// 重试次数-producer kafkaProperties.put("retries",
		// MdsConstants.appConfig.getProperty("retries"));
		// 批次大小-producer kafkaProperties.put("batch.size",
		// MdsConstants.appConfig.getProperty("batch.size"));
		// 请求队列大小-producer kafkaProperties.put("linger.ms",
		// MdsConstants.appConfig.getProperty("linger.ms"));
		// 消息缓冲池大小-producer kafkaProperties.put("buffer.memory",
		// MdsConstants.appConfig.getProperty("buffer.memory"));
		// key序列化器-producer
		kafkaProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
				MdsConstants.kafkaConfig.getProperty("key.serializer"));
		// value序列化器-producer
		kafkaProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
				MdsConstants.kafkaConfig.getProperty("value.serializer"));
		// 根证书 store 的密码，保持不变
		kafkaProperties.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, "KafkaOnsClient");
		// 接入协议，目前支持使用 SASL_SSL 协议接入
		kafkaProperties.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_SSL");
		// SASL 鉴权方式，保持不变
		kafkaProperties.put(SaslConfigs.SASL_MECHANISM, "ONS");
		// 请求的最长等待时间
		kafkaProperties.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, MdsConstants.kafkaConfig.getProperty("max.block.ms"));

		/**
		 * ssl 根证书的路径，demo中有，请拷贝到自己的某个目录下，不能被打包到jar中
		 * 这里假设您的目录为/home/admin，请记得修改为自己的实际目录
		 */
		kafkaProperties.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG,
				MdsConstants.kafkaConfig.getProperty("ssl.truststore.location"));
		return kafkaProperties;
	}
}
