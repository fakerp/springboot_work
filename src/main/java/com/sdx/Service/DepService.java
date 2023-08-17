package com.sdx.Service;

import com.sdx.anno.Log;
import com.sdx.my_class.Dept;

import java.util.List;

public interface DepService {
    List<Dept> list();

    void delete(Integer id);


    void insert(Dept dept);

    Dept select_id(Integer id);


    void update(Dept dept);
}
