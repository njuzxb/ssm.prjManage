package com.zxb.prjms.dao;

import com.zxb.prjms.bean.Bug;
import com.zxb.prjms.bean.BugExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BugMapper {
    long countByExample(BugExample example);

    int deleteByExample(BugExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Bug record);

    int insertSelective(Bug record);

    List<Bug> selectByExampleWithBLOBs(BugExample example);

    List<Bug> selectByExample(BugExample example);

    Bug selectByPrimaryKey(Integer id);
    
    List<Bug> selectByExampleWithBLOBsAndPrj(BugExample example);

    List<Bug> selectByExampleWithPrj(BugExample example);

    Bug selectByPrimaryKeyWithPrj(Integer id);

    int updateByExampleSelective(@Param("record") Bug record, @Param("example") BugExample example);

    int updateByExampleWithBLOBs(@Param("record") Bug record, @Param("example") BugExample example);

    int updateByExample(@Param("record") Bug record, @Param("example") BugExample example);

    int updateByPrimaryKeySelective(Bug record);

    int updateByPrimaryKeyWithBLOBs(Bug record);

    int updateByPrimaryKey(Bug record);
}