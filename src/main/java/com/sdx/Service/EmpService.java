package com.sdx.Service;

import com.sdx.anno.Log;
import com.sdx.my_class.Emp;
import com.sdx.my_class.PageBean;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
    PageBean list(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);


    void delete(List<Integer> ids);


    void delete_dpid(Integer did);


    void insert(Emp emp);

    Emp select_id(Integer id);


    void update(Emp emp);

    Emp select_login(Emp emp);
}
