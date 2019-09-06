package com.atguigu.cache.service;

import com.atguigu.cache.bean.Employee;
import com.atguigu.cache.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2019/9/4.
 */
@CacheConfig(cacheNames="emp"/*,cacheManager = "employeeCacheManager"*/)//抽取缓存的公共配置
@Service
public class EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;

    /**
     * 将方法的运行结果进行缓存，以后再要相同的数据，直接从缓存中获取，不用调用方法；
     * 几个属性：
     *       cacheNames/value:指定缓存组件的名字；
     *       key:缓存数据使用的key;可以用它来指定。默认是使用方法参数的值 1-方法的返回值
     *           编写SpEL；  #id;参数id的值  #a0 #p0 #root.args[0]
     *       keyGenerator:key的生成器；可以自己指定key的生成器的组件id
     *         key/keyGenerator:二选一使用
     *       cacheManager：指定缓存管理器;或者cacheResolver指定缓存解析器，二选一指定
     *       condition：指定符合条件的情况下才缓存；
     *       unless:否定缓存，当unless指定的条件为true，方法的返回值就不会被缓存
     *              eg:unless="result==null"
     *       sync:是否使用异步模式
     *
     * 原理：
     *     1、自动配置类；CacheAutoConfiguration
     *     2、缓存的匹配类
     *        org.springframework.boot.autoconfigure.cache.GenericCacheConfiguration
     *        org.springframework.boot.autoconfigure.cache.JCacheCacheConfiguration
     *        org.springframework.boot.autoconfigure.cache.EhCacheCacheConfiguration
     *        org.springframework.boot.autoconfigure.cache.HazelcastCacheConfiguration
     *        org.springframework.boot.autoconfigure.cache.InfinispanCacheConfiguration
     *        org.springframework.boot.autoconfigure.cache.CouchbaseCacheConfiguration
     *        org.springframework.boot.autoconfigure.cache.RedisCacheConfiguration
     *        org.springframework.boot.autoconfigure.cache.CaffeineCacheConfiguration
     *        org.springframework.boot.autoconfigure.cache.SimpleCacheConfiguration
     *        org.springframework.boot.autoconfigure.cache.NoOpCacheConfiguration
     *
     *     3.哪个配置类默认生效：SimpleCacheConfiguration
     *     4.给容器中注册了一个缓存管理器ConcurrentMapCacheManager
     *     5.可以获取和创建ConcurrentMapCache类型的缓存组件
     *
     *
     *   运行流程：
     *
     *
     *
     *
     * @param id
     * @return
     */
    @Cacheable
    public Employee getEmp(String id){
        System.out.println("查询"+id+"员工");
        return employeeMapper.getEmployee(Integer.parseInt(id));
    };

    //Caching 定义复杂的缓存规则
    @Caching(
            cacheable={
                @Cacheable(value="emp",key="#lastName")
            },
            put = {
                @CachePut(value = "emp",key = "#result.id"),
                @CachePut(value = "emp",key = "#result.email")
            }

    )
    public Employee getEmpByLastName(String lastName){

     return    employeeMapper.getEmpByLastName(lastName);
    }

}
