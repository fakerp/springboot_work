package com.sdx.Mapper;

import com.sdx.my_class.Emp;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {

    public List<Emp> select_(@Param("name") String name,@Param("gender") Short gender,@Param("begin") LocalDate begin,@Param("end") LocalDate end);

    void delete(@Param("ids") List<Integer> ids);

    @Insert("insert into emp(username, name, gender, image, job, entrydate, dept_id, create_time, update_time)" +
            "values (#{username},#{name},#{gender},#{image},#{job},#{entrydate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);

    @Select("select * from emp where id = #{id}")
    Emp select_id(Integer id);

    void update(Emp emp);

    @Select("select * from emp where username=#{username} and password = #{password};")
    Emp select_login(Emp emp);

    @Delete("delete from emp where dept_id = #{did}")
    void delete_did(Integer did);
}
