package org.example.config;

import io.lettuce.core.ReadFrom;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.RedisStaticMasterReplicaConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "redis")
public class RedisConfiguration {


    private RedisInstance master;
    private List<RedisInstance> slaves;
    private List<RedisInstance> sentinels;

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
                .readFrom(ReadFrom.ANY)
                .build();

        RedisStaticMasterReplicaConfiguration staticMasterReplicaConfiguration = new RedisStaticMasterReplicaConfiguration(this.getMaster().getHost(), this.getMaster().getPort());
        this.getSlaves().forEach(slave -> staticMasterReplicaConfiguration.addNode(slave.getHost(), slave.getPort()));
        this.getSentinels().forEach(sentinel -> staticMasterReplicaConfiguration.addNode(sentinel.getHost(),sentinel.getPort()));
        System.out.println(master.getHost());
        System.out.println(master.getPort());
        for (RedisInstance slave : slaves) {
            System.out.println(slave.getHost());
            System.out.println(slave.getPort());
        }
        return new LettuceConnectionFactory(staticMasterReplicaConfiguration, clientConfig);
    }

    private static class RedisInstance {
        private String host;
        private int port;

        String getHost(){ return this.host; }
        int getPort() {
            return port;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public void setPort(int port) {
            this.port = port;
        }
    }

    RedisInstance getMaster() {
        return master;
    }

    void setMaster(RedisInstance master) {
        this.master = master;
    }

    List<RedisInstance> getSlaves() {
        return slaves;
    }

    void setSlaves(List<RedisInstance> slaves) {
        this.slaves = slaves;
    }

    public List<RedisInstance> getSentinels() {
        return sentinels;
    }

    public void setSentinels(List<RedisInstance> sentinels) {
        this.sentinels = sentinels;
    }

}
