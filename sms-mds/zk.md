#!/bin/bash
docker run -it --rm --link test_zookeeper:zookeeper zookeeper zkCli.sh -server zookeeper

# zkCli.sh -server 127.0.0.1:2181

create /appConfig/dubboService/spring.datasource.master.name master
create /appConfig/dubboService/spring.datasource.master.url jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8
create /appConfig/dubboService/spring.datasource.master.username root
create /appConfig/dubboService/spring.datasource.master.password root
create /appConfig/dubboService/spring.datasource.master.driverClassName com.mysql.jdbc.Driver

create /appConfig/dubboService/spring.datasource.slave1.name slave1
create /appConfig/dubboService/spring.datasource.slave1.url jdbc:mysql://localhost:3306/smsmis?useUnicode=true&characterEncoding=utf-8
create /appConfig/dubboService/spring.datasource.slave1.username root
create /appConfig/dubboService/spring.datasource.slave1.password root
create /appConfig/dubboService/spring.datasource.slave1.driverClassName com.mysql.jdbc.Driver

create /appConfig/dubboService/spring.datasource.slave2.name slave2
create /appConfig/dubboService/spring.datasource.slave2.url jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8
create /appConfig/dubboService/spring.datasource.slave2.username root
create /appConfig/dubboService/spring.datasource.slave2.password root
create /appConfig/dubboService/spring.datasource.slave2.driverClassName com.mysql.jdbc.Driver


create /appConfig/dubboService/spring.datasource.master.initialSize 5
create /appConfig/dubboService/spring.datasource.master.maxActive 10
create /appConfig/dubboService/spring.datasource.master.minIdle 3
create /appConfig/dubboService/spring.datasource.master.maxWait 60000
create /appConfig/dubboService/spring.datasource.master.timeBetweenEvictionRunsMillis 60000
create /appConfig/dubboService/spring.datasource.master.minEvictableIdleTimeMillis 300000
create /appConfig/dubboService/spring.datasource.master.validationQuery 
create /appConfig/dubboService/spring.datasource.master.testWhileIdle false
create /appConfig/dubboService/spring.datasource.master.testOnBorrow true
create /appConfig/dubboService/spring.datasource.master.testOnReturn false
create /appConfig/dubboService/spring.datasource.master.poolPreparedStatements false
create /appConfig/dubboService/spring.datasource.master.maxPoolPreparedStatementPerConnectionSize 20
create /appConfig/dubboService/spring.datasource.master.filters 
create /appConfig/dubboService/spring.datasource.master.connectionProperties 
create /appConfig/dubboService/spring.datasource.master.useGlobalDataSourceStat 

create /appConfig/dubboService/spring.pageHelper.dialect com.github.pagehelper.dialect.helper.MySqlDialect
create /appConfig/dubboService/spring.pageHelper.rowBoundsWithCount false
create /appConfig/dubboService/spring.pageHelper.pageSizeZero true
create /appConfig/dubboService/spring.pageHelper.reasonable false
create /appConfig/dubboService/spring.pageHelper.supportMethodsArguments false
create /appConfig/dubboService/spring.pageHelper.offsetAsPageNum false
create /appConfig/dubboService/spring.pageHelper.params 
create /appConfig/dubboService/spring.pageHelper.returnPageInfo none