package com.sdx.Config_;

import com.sdx.Controll.DepControll;
import com.sdx.my_function.jwt_tool;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
//表明这是一个配置项，初始化时需要完成。
public class BeanConfig {

//    如果你使用了第三方库里面的对象，可以用@Bean给他放入ioc容器里面去管理,bean的名字就是函数名
//    返回值将作为一个bean对象被ioc容器管理
//    如果说这个bean对象里面需要用到其他bean对象，也就是需要依赖注入，直接在参数种传入bean类即可，会自动装载.
    @Bean
    public jwt_tool testclass(DepControll depControll){
        System.out.println(depControll);
        return new jwt_tool();
    }
}
