package com.zxb.prjms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxb.prjms.bean.Bug;
import com.zxb.prjms.bean.BugExample;
import com.zxb.prjms.dao.BugMapper;

@Service
public class BugService {
	@Autowired
	BugMapper bugmapper;
	
	/**
	 * 查询所有Bug，不带content
	 * @param object
	 * @return
	 */
	public List<Bug> getAll(BugExample bugExample) {
		// TODO Auto-generated method stub
		return bugmapper.selectByExampleWithPrj(bugExample);
	}
	/**
	 * 保存BUG
	 * @param bug
	 */
	public void saveBug(Bug bug) {
		// TODO Auto-generated method stub
		bugmapper.insertSelective(bug);
	}
	/**
	 * 根据id查询bug
	 * @param id
	 * @return
	 */
	public Bug getBug(Integer id) {
		// TODO Auto-generated method stub
		return bugmapper.selectByPrimaryKeyWithPrj(id);
	}
	/**
	 * 更新BUG
	 * @param bug
	 */
	public void updateBug(Bug bug) {
		// TODO Auto-generated method stub
		bugmapper.updateByPrimaryKeySelective(bug);
	}
	/**
	 * 删除BUG
	 * @param id
	 */
	public void deleteBug(Integer id) {
		// TODO Auto-generated method stub
		bugmapper.deleteByPrimaryKey(id);
	}
	
}
