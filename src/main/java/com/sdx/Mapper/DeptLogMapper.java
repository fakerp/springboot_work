package com.sdx.Mapper;

import com.sdx.my_class.DeptLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;


@Mapper
public interface DeptLogMapper {
    @Insert("insert into dept_log(create_time, description) values (#{createTime},#{description})")
    void insert(DeptLog deptLog);
}
