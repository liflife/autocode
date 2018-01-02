package com.autocode.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.autocode.domain.TableField;

/**
 * 
 * UserDaoImpl
 * @author 
 * @version
 */
public class DataBaseAcessDao extends CommonDao  {
	private  String FIND_TABLENAME_BYNAME_MYSQL = "findDataByNameMysql";
	private  String SELECT_ALL_TABLENAME_MYSQL = "selectAllDataMysql";
	private  String FIND_TABLENAME_BYNAME_ORACLE = "findDataByNameOracle";
	private  String SELECT_ALL_TABLENAME_ORACLE = "selectAllDataOracle";
	public List<TableField> findDataByIdMysql(String tablename) {
		Tablename tn=new Tablename();
		tn.setTablename(tablename);
		List<TableField> selectList = sqlSessionTemplate.selectList(FIND_TABLENAME_BYNAME_MYSQL, tn);
		return selectList;
	}


	public List findAllMysql() {
		return sqlSessionTemplate.selectList(SELECT_ALL_TABLENAME_MYSQL);
	}
	public List<TableField> findDataByIdOracle(String tablename) {
		Tablename tn=new Tablename();
		tn.setTablename(tablename);
		List<TableField> selectList = sqlSessionTemplate.selectList(FIND_TABLENAME_BYNAME_ORACLE, tn);
		return selectList;
	}


	public List findAllOracle() {
		return sqlSessionTemplate.selectList(SELECT_ALL_TABLENAME_ORACLE);
	}
	
}
