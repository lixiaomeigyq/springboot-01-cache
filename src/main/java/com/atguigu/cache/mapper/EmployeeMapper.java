package com.atguigu.cache.mapper;

import com.atguigu.cache.bean.Employee;
import org.apache.ibatis.annotations.*;

/**
 * Created by Administrator on 2019/9/4.
 */
@Mapper
public interface EmployeeMapper {

    @Select("Select * from employee where id=#{id}")
    public Employee getEmployee(Integer id);

    @Insert("insert into employee(lastName,email,gender,d_id) values(#{lastName},#{email},#{gender},#{dId})")
    public void insertEmployee(Employee employee);

    @Delete("delete  from employee where id=#{id}")
    public Employee deleteEmployee(Integer id);

    @Update("update employee set lastName=#{lastName},email=#{email},gender=#{gender},d_id=#{dId}")
    public Employee updateEmployee(Employee employee);

    @Select("Select * from employee where lastName=#{lastName}")
    Employee getEmpByLastName(String lastName);
}
