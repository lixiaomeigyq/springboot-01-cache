package com.atguigu.cache.controller;

import com.atguigu.cache.bean.Department;
import com.atguigu.cache.bean.Employee;
import com.atguigu.cache.service.DeptService;
import com.atguigu.cache.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2019/9/4.
 */
@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;


    @GetMapping("emp/{id}")
    public Employee getEmployee(@PathVariable("id") String  id){
        return employeeService.getEmp(id);
    }

    @GetMapping("/emp/lastname/{lastName}")
    public Employee getEmployeeByLastName(@PathVariable("lastName") String lastName){
        return employeeService.getEmpByLastName(lastName);
    }

}
