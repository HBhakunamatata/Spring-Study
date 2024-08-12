package com.HB.dao;

import com.HB.pojo.Employee;
import org.mybatis.spring.SqlSessionTemplate;

public class EmployeeMapperImpl implements EmployeeMapper {

    private SqlSessionTemplate sqlSession;

    public void setSqlSession(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public Employee getEmpById(int empNo) {
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        return mapper.getEmpById(empNo);
    }
}
