package com.atguigu.cache;

import com.atguigu.cache.bean.Employee;
import com.atguigu.cache.mapper.EmployeeMapper;
import com.atguigu.cache.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot01CacheApplicationTests {

	/*@Autowired
	EmployeeMapper empoyeeMapper;*/

	@Autowired
	EmployeeService employeeService;

	@Autowired
	StringRedisTemplate stringRedisTemplate;//操作k-v都是字符串

	@Autowired
	RedisTemplate redisTemplate;//k-v都是对象的

	@Autowired
	RedisTemplate<Object, Employee> empRedisTemplate;

	/**
	 * String(字符串) 、List(列表)、Set（集合）、Hash(散列)、ZSet(有序集合)
	 * stringRedisTemplate.opsForList();
	 * stringRedisTemplate.opsForValue();
	 * stringRedisTemplate.opsForHash();
	 * stringRedisTemplate.opsForSet();
	 * stringRedisTemplate.opsForZSet();
	 * redisTemplate.opsForSet();
	 * redisTemplate.opsForHash();
	 * redisTemplate.opsForZSet();
	 * redisTemplate.opsForList();
	 * redisTemplate.opsForValue();
	 *
	 *
	 */
    @Test
    public void test01(){
		/*stringRedisTemplate.opsForValue().append("msg1","hello");
		String msg = stringRedisTemplate.opsForValue().get("msg1");
		System.out.println(msg)*/;

		//stringRedisTemplate.opsForList().leftPush("mylist","1");
		//stringRedisTemplate.opsForList().leftPush("mylist","2");

	}

	@Test
	public void test02(){
		//Employee employee = employeeService.getEmp(1);
		//默认如果保存对象，使用jdk序列化后的数据保存到redis中
		//redisTemplate.opsForValue().set("emp-01",employee);
		//1.将数据以json的方式保存
		//(1).用转换工具自己将对象转为json
		//(2).redisTemplate默认的序列化规则;改变默认的序列化规则
		//empRedisTemplate.opsForValue().set("emp-01",employee);
	}


	/*@Test
	public void contextLoads() {
	 Employee employee = employeeMapper.getEmployee(1);
		System.out.println(employee);
	}*/


}
