package com.sdx.Controll;

import com.sdx.Service.DepService;
import com.sdx.anno.Log;
import com.sdx.my_class.Dept;
import com.sdx.my_class.Result;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/depts")
@RestController

public class DepControll {
    @Autowired
    DepService depService;


    @GetMapping
    public Result dept(){
        List<Dept> list = depService.list();
        System.out.println("spider man");
        return Result.success(list);
    }
    @Log
    @DeleteMapping("/{id}")
    public Result delete_dept(@PathVariable Integer id){
        depService.delete(id);
        return Result.success();
    }
    @Log
    @PostMapping
    public Result insert(@RequestBody Dept dept){
        System.out.println(dept);
        depService.insert(dept);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result select_id(@PathVariable Integer id){
        Dept dept = depService.select_id(id);
        return Result.success(dept);
    }
    @Log
    @PutMapping
    public Result update(@RequestBody Dept dept){

        depService.update(dept);
        return Result.success();
    }
}
