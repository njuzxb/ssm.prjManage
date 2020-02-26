package com.zxb.prjms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxb.prjms.bean.Module;
import com.zxb.prjms.bean.ModuleExample;
import com.zxb.prjms.dao.ModuleMapper;
@Service
public class ModuleService {
	@Autowired
	ModuleMapper moduleMapper;
	
	/**
	 * 查询所有模块
	 * @return
	 */
	public List<Module> getAll(ModuleExample moduleExample) {
		// TODO Auto-generated method stub
		return moduleMapper.selectByExampleWithPrj(moduleExample);
	}
	/**
	 * 保存模块
	 * @param module
	 */
	public void saveMod(Module module) {
		// TODO Auto-generated method stub
		moduleMapper.insertSelective(module);
	}
	/**
	 * 根据主键查询模块
	 * @param id
	 * @return
	 */
	public Module getMod(Integer id) {
		// TODO Auto-generated method stub
		return moduleMapper.selectByPrimaryKeyWithPrj(id);
	}
	/**
	 * 更新模块
	 * @param module
	 */
	public void updateMod(Module module) {
		// TODO Auto-generated method stub
		moduleMapper.updateByPrimaryKeySelective(module);
	}
	/**
	 * 删除模块
	 * @param id
	 */
	public void deleteMod(Integer id) {
		// TODO Auto-generated method stub
		moduleMapper.deleteByPrimaryKey(id);
	}
	
}
