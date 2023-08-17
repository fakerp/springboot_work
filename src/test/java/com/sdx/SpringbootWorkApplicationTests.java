package com.sdx;

import com.sdx.my_function.jwt_tool;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@SpringBootTest
class SpringbootWorkApplicationTests {
    @Autowired
    public jwt_tool tool;
//   自动注入Ioc容器，方便取出bean对象
    @Autowired
    public ApplicationContext applicationContext;

    @Test
    void contextLoads() {
        Map map = new HashMap();
        map.put("小明",557);
        map.put("小黄",799);
        String handsomecat = Jwts.builder()
                .setClaims(map)
                .signWith(SignatureAlgorithm.HS256, "handsomecat")
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))
                .compact();
        System.out.println(handsomecat);
    }
//    eyJhbGciOiJIUzI1NiJ9.eyLlsI_mmI4iOjU1NywiZXhwIjoxNjkxNjYxODg2LCLlsI_pu4QiOjc5OX0.BH-r7adtuMPJquysBvi6IXsyJI8vxnBKJeafQHugo-k
    @Test
    void jwtsee(){
        Claims handsomecat = Jwts.parser()
                .setSigningKey("handsomecat")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyLlsI_mmI4iOjU1NywiZXhwIjoxNjkxNjYxODg2LCLlsI_pu4QiOjc5OX0.BH-r7adtuMPJquysBvi6IXsyJI8vxnBKJeafQHugo-k")
                .getBody();
        System.out.println(handsomecat);
    }

    @Test
    void ttoo(){
        System.out.println(tool);
        System.out.println(applicationContext.getBean("testclass"));
    }
}
