package com.zxb.prjms.dao;

import com.zxb.prjms.bean.Module;
import com.zxb.prjms.bean.ModuleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ModuleMapper {
    long countByExample(ModuleExample example);

    int deleteByExample(ModuleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Module record);

    int insertSelective(Module record);

    List<Module> selectByExampleWithBLOBs(ModuleExample example);

    List<Module> selectByExample(ModuleExample example);

    Module selectByPrimaryKey(Integer id);
    
    List<Module> selectByExampleWithBLOBsWithPrj(ModuleExample example);
    
    List<Module> selectByExampleWithPrj(ModuleExample example);

    Module selectByPrimaryKeyWithPrj(Integer id);

    int updateByExampleSelective(@Param("record") Module record, @Param("example") ModuleExample example);

    int updateByExampleWithBLOBs(@Param("record") Module record, @Param("example") ModuleExample example);

    int updateByExample(@Param("record") Module record, @Param("example") ModuleExample example);

    int updateByPrimaryKeySelective(Module record);

    int updateByPrimaryKeyWithBLOBs(Module record);

    int updateByPrimaryKey(Module record);
}