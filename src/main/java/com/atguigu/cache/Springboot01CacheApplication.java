package com.atguigu.cache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 一、搭建基本环境
 * 1、导入数据库文件，创建出表
 * 2、创建表对应的Javabean实体类
 * 3、整合Mybaitis操作数据库
 *    1）配置数据源信息和开启驼峰命名匹配规则
 *    2）使用注解版的Mybaitis
 *        1）@MapperScan指定需要扫描的mapper接口所在的包
 *        2）service中调用mapper接口
 *        3）controller中调用service
 * 二、快速体验缓存
 *	   步骤：
 *	       1、开启基于注解的缓存@EnableCaching
 *	       2、标注缓存注解即可
 *	          @Cacheable
 *	          @CacheEvict
 *			  @CachePut
 *		      @EnableCaching
 *
 * 默认使用的是ConcurrentMapCacheManager==ConcurrentMapCache;将数据保存在ConcurrentMap<Objiect,Object>
 *开发中使用缓存中间件：redis、memcached、ehcache;
 * 整合redis作为缓存
 * 1.安装redis：使用docker安装
 * 2.引入redis的starter
 * 3.配置redis
 * 4.测试缓存
 *      原理CacheManager==Cache 缓存组件来实际给缓存中存取数据
 *      1）、引入redis的starter,容器中保存的是RedisCacheManager;
 *      2)、RedisCacheManager帮我们创建RedisCache来作为缓存组件；RedisCache通过操作redis缓存数据的
 *		3）、默认保存数据k-v都是Object；利用序列化保存；
 *	          如何保存json?
  *	          1、引入redis的starter,cacheManager变为RedisCacheManager;
 *	          2、默认创建的RedisCacheManager操作redis的时候使用的是RedisTemplate<Object, Object> redisTemplate
 *	          3、RedisTemplate<Object, Object> 是RedisAutoConfiguration创建的，RedisTemplate默认使用jdk的序列化机制
 *       4）、自定义CacheManager；
 *
 */
@MapperScan(basePackages="com.atguigu.cache.mapper")
@SpringBootApplication
@EnableCaching
public class Springboot01CacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot01CacheApplication.class, args);
	}

}
