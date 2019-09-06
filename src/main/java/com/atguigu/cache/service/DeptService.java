package com.atguigu.cache.service;

import com.atguigu.cache.bean.Department;
import com.atguigu.cache.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;

import javax.swing.plaf.PanelUI;

/**
 * Created by Administrator on 2019/9/6.
 */
@CacheConfig(cacheNames="depts"/*,cacheManager = "deptCacheManager"*/)//抽取缓存的公共配置
@Service
public class DeptService {
    @Autowired
    DepartmentMapper departmentMapper;

  /*  @Qualifier("deptCacheManager")
    @Autowired
    RedisCacheManager deptCacheManager;*/


    /**
     * 缓存的数据能存入redis；
     * 第二次从缓存中查询就不能反序列化回来；
     * 存的是dept的json数据；CacheManager默认使用 RedisTemplate<Object, Employee> 操作redis
     *
     * @param id
     * @return
     */
    //方法一：注解方式

    @Cacheable
    public Department getDeptById(String  id){
        System.out.println("查询"+id+"号部门");
         Department department = departmentMapper.getDeptById(Integer.parseInt(id));
         return department;
    }

    //方法二：编码方式
    //使用缓存管理器得到缓存，进行api调用
/*    public Department getDeptById(Integer id){
        System.out.println("查询"+id+"号部门");
        Department department = departmentMapper.getDeptById(id);

        //获取某个缓存
       Cache cache = deptCacheManager.getCache("dept");
       //放到缓存中
       cache.put("dept:"+id,department);

        return department;
    }*/
}
