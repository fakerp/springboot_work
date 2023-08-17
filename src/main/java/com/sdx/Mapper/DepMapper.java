package com.sdx.Mapper;

import com.sdx.my_class.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DepMapper {

    @Select("select * from course_test1.dept")
    public List<Dept> selectAll();

    @Delete("delete from course_test1.dept where id = #{id}")
    void delete(Integer id);

    @Insert("insert into dept(name, create_time, update_time) values (#{name},#{createTime},#{updateTime})")
    void insert(Dept dept);

    @Select("select * from dept where id=#{id}")
    Dept select_id(Integer id);

    @Update("update dept set name = #{name},update_time=#{updateTime} where id =#{id}")
    void update(Dept dept);

    
}
