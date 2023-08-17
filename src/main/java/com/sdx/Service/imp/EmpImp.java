package com.sdx.Service.imp;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sdx.Mapper.EmpMapper;
import com.sdx.Service.EmpService;
import com.sdx.my_class.Emp;
import com.sdx.my_class.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpImp implements EmpService {
    @Autowired
    EmpMapper empMapper;


    @Override
    public PageBean list(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end) {
        //        代替传统分页操作，使用pagehelper，先用startpage设置好初始变量
        PageHelper.startPage(page,pageSize);
        List<Emp> list = empMapper.select_(name,gender,begin,end);
//        再将正常查询的结果强转为page对象，即可实现分页操作
        Page<Emp> p = (Page<Emp>) list;
//        利用page对象的函数即可实现原本分页操作
        return new PageBean(p.getTotal(),p.getResult());
    }

    @Override
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
    }

    @Override
    public void delete_dpid(Integer did){
        empMapper.delete_did(did);
    }

    @Override
    public void insert(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);
    }

    @Override
    public Emp select_id(Integer id) {
        Emp emp =empMapper.select_id(id);
        return emp;
    }

    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
    }

    @Override
    public Emp select_login(Emp emp) {
        return empMapper.select_login(emp);
    }
}
