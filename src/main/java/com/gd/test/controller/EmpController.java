package com.gd.test.controller;

import com.alibaba.fastjson.JSON;
import com.gd.test.dao.DepartmentDao;
import com.gd.test.dao.EmployeeDao;
import com.gd.test.entities.Department;
import com.gd.test.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.util.Collection;



/**
 * Created by Administrator on 2020/1/11.
 */
@Controller
public class EmpController {
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private DepartmentDao departmentDao;
/*    @Autowired
    DataSource dataSource*/;

    private Collection<Employee> all;
    private Collection<Department> departments;
    private String string;
    private Employee employee;
    private Collection<Department> departments1;
    //员工列表
    @GetMapping("/emp")
    public String list(Model model){
        all = employeeDao.getAll();
        /*for(Employee emp:all){
            System.out.println(emp);
        }*/
        //System.out.println(dataSource);
        model.addAttribute("emplist",all);
        return "emp/list";
    }
    //增加员工信息
    @PostMapping("/addEmps")
    public String addEmps(Employee employee, Model model){
        //System.out.println(employee);
        //forward:转发到某个地址
        employeeDao.save(employee);
        return "redirect:/emp";
    }

    //同步部门
    @GetMapping("/add")
    public String add(Model model){
        departments1 = departmentDao.getDepartments();
        model.addAttribute("departments",departments1);
       // System.out.println("add");
        return "emp/addemp";
        //return "emp/add";
    }
    //异步部门
    @ResponseBody
    @PostMapping("/getDeparts")
    public  String getDeparts(){
        departments = departmentDao.getDepartments();
        string = JSON.toJSONString(departments);
        return string;
    }
    //查看员工详情
    @GetMapping("/changeEmp/{id}")
    public String changeEmp(@PathVariable("id")Integer id,HttpServletRequest request){
        employee = employeeDao.get(id);
       // System.out.println(employee);
        request.setAttribute("employee",employee);
        return "emp/editorEmp";
    }
    //修改员工
    @PutMapping("/editorEmp")
    public String editorEmp(Employee employee){
        System.out.println(employee);
        employeeDao.save(employee);
        return "redirect:/emp";
    }
    //删除员工
    @DeleteMapping("/delEmp/{empId}")
    public String delEmp(@PathVariable("empId")Integer id){
        employeeDao.delete(id);
        System.out.println(id);
        return "redirect:/emp";
    }
}
