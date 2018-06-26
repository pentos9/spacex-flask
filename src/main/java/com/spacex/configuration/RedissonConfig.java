package com.spacex.configuration;

import io.netty.channel.nio.NioEventLoopGroup;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

    @Value("${spring.redisson.address}")
    private String address;
    @Value("${spring.redisson.connectionMinimumIdleSize}")
    private int connectionMinimumIdleSize = 10;

    @Value("${spring.redisson.idleConnectionTimeout}")
    private int idleConnectionTimeout = 10000;

    @Value("${spring.redisson.pingTimeout}")
    private int pingTimeout = 1000;

    @Value("${spring.redisson.connectTimeout}")
    private int connectTimeout = 10000;

    @Value("${spring.redisson.timeout}")
    private int timeout = 3000;

    @Value("${spring.redisson.retryAttempts}")
    private int retryAttempts = 3;

    @Value("${spring.redisson.retryInterval}")
    private int retryInterval = 1500;

    @Value("${spring.redisson.reconnectionTimeout}")
    private int reconnectionTimeout = 3000;

    @Value("${spring.redisson.failedAttempts}")
    private int failedAttempts = 3;

    @Value("$spring.redisson.password")
    private String password = null;

    @Value("${spring.redisson.subscriptionsPerConnection}")
    private int subscriptionsPerConnection = 5;

    private String clientName = null;

    @Value("${spring.redisson.subscriptionConnectionMinimumIdleSize}")
    private int subscriptionConnectionMinimumIdleSize = 1;

    @Value("${spring.redisson.subscriptionConnectionPoolSize}")
    private int subscriptionConnectionPoolSize = 50;

    @Value("${spring.redisson.connectionPoolSize}")
    private int connectionPoolSize = 64;

    private int database = 0;

    @Value("${spring.redisson.dnsMonitoring}")
    private boolean dnsMonitoring = false;

    @Value("${spring.redisson.dnsMonitoringInterval}")
    private int dnsMonitoringInterval = 5000;

    private int thread; //当前处理核数量 * 2

    private String codec = "org.redisson.codec.JsonJacksonCodec";

    @Bean(destroyMethod = "shutdown")
    RedissonClient redisson() throws Exception {
        Config config = new Config();
        //config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        config.useSingleServer().setAddress(address)
                .setConnectionMinimumIdleSize(connectionMinimumIdleSize)
                .setConnectionPoolSize(connectionPoolSize)
                .setDatabase(database)
                .setDnsMonitoring(dnsMonitoring)
                .setDnsMonitoringInterval(dnsMonitoringInterval)
                .setSubscriptionConnectionMinimumIdleSize(subscriptionConnectionMinimumIdleSize)
                .setSubscriptionConnectionPoolSize(subscriptionConnectionPoolSize)
                .setSubscriptionsPerConnection(subscriptionsPerConnection)
                .setClientName(clientName)
                .setFailedAttempts(failedAttempts)
                .setRetryAttempts(retryAttempts)
                .setRetryInterval(retryInterval)
                .setReconnectionTimeout(reconnectionTimeout)
                .setTimeout(timeout)
                .setConnectTimeout(connectTimeout)
                .setIdleConnectionTimeout(idleConnectionTimeout)
                .setPingTimeout(pingTimeout);

        config.setThreads(thread);
        config.setEventLoopGroup(new NioEventLoopGroup());
        config.setUseLinuxNativeEpoll(false);
        return Redisson.create(config);
    }
}
