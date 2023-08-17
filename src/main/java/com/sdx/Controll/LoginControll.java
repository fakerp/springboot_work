package com.sdx.Controll;

import com.sdx.Mapper.EmpMapper;
import com.sdx.Service.EmpService;
import com.sdx.my_class.Emp;
import com.sdx.my_class.Result;
import com.sdx.my_function.jwt_tool;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.impl.DefaultJwtBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class LoginControll {
    @Autowired
    EmpService empService;

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp){
        Map map = new HashMap();
        Emp e;
        if ((e=empService.select_login(emp))!=null){
            map.put("name",e.getName());
            map.put("id",e.getId());
            map.put("password",e.getPassword());
            return Result.success(jwt_tool.createJwt(map));
        }else
            return Result.error("该用户不存在");
    }
}
