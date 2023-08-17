package com.sdx.Controll;

import com.sdx.Service.EmpService;
import com.sdx.anno.Log;
import com.sdx.my_class.Emp;
import com.sdx.my_class.PageBean;
import com.sdx.my_class.Result;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/emps")

public class EmpControll {
    @Autowired
    EmpService empService;

    @GetMapping
//    RequestParam设置默认值
    public Result select(@RequestParam(defaultValue = "1") Integer page , @RequestParam(defaultValue = "10") Integer pageSize
    ,String name, Short gender , @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate begin ,@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
        System.out.println(page);
        PageBean list = empService.list(page,pageSize,name,gender,begin,end);
        return Result.success(list);
    }
    @Log
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        empService.delete(ids);
        return Result.success();
    }
    @Log
    @PostMapping
    public Result insert(@RequestBody Emp emp){

        empService.insert(emp);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result select_id(@PathVariable Integer id){
        Emp emp = empService.select_id(id);
        return Result.success(emp);
    }
    @Log
    @PutMapping
    public Result update(@RequestBody Emp emp){
        empService.update(emp);
        return Result.success();
    }
}
