package com.wang.configurtion;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Redis的session管理
 */
@Configuration
@EnableRedisHttpSession
public class RedisSessionManager {
}
