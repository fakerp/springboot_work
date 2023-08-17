package com.sdx.Service.imp;

import com.sdx.Mapper.DepMapper;
import com.sdx.Mapper.DeptLogMapper;
import com.sdx.Mapper.EmpMapper;
import com.sdx.Service.DepService;
import com.sdx.Service.DeptLogService;
import com.sdx.Service.EmpService;
import com.sdx.my_class.Dept;
import com.sdx.my_class.DeptLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DepImp implements DepService {
    @Autowired
    DepMapper depMapper;
    @Autowired
    EmpService empService;
    @Autowired
    DeptLogService deptLogService;

    @Override
    public List<Dept> list() {
        return depMapper.selectAll();
    }

//    Transactional事务控制，rollbackFor表示事物回滚的触发条件，不设置的话只有运行时异常才会触发，其他异常不会触发
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Integer id) {
        try {
            depMapper.delete(id);
//            int i =1/0;
            empService.delete_dpid(id);
        } finally {
            DeptLog deptLog = new DeptLog();
            deptLog.setCreateTime(LocalDateTime.now());
            deptLog.setDescription("已经成功插入日志");
//            deptLogService.insert(deptLog);
        }

    }

    @Override
    public void insert(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        depMapper.insert(dept);
    }

    @Override
    public Dept select_id(Integer id) {
        return depMapper.select_id(id);
    }

    @Override
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        depMapper.update(dept);
    }
}
