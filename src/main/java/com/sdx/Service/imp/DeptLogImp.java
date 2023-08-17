package com.sdx.Service.imp;

import com.sdx.Mapper.DeptLogMapper;
import com.sdx.Mapper.EmpMapper;
import com.sdx.Service.DeptLogService;
import com.sdx.my_class.DeptLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeptLogImp implements DeptLogService {
    @Autowired
    DeptLogMapper deptLogMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void insert(DeptLog deptLog) {
        deptLogMapper.insert(deptLog);
    }
}
