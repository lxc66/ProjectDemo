package com.jzsoft.demo.dao.auto;

import java.util.List;

import com.jzsoft.demo.model.TBDept;
import com.jzsoft.platform.core.mybatis.SqlMapper;
@SqlMapper
public interface TBDeptDAO {
    
    public void save(TBDept dept);  
      
    public void update(TBDept dept);  
      
    public void delete(String id);  
      
    public TBDept get(String id);  
    
    public List<TBDept> getAll();  
}
