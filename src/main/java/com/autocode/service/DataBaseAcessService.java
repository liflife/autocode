package com.autocode.service;

import java.util.List;

import com.autocode.dao.DataBaseAcessDao;
import com.autocode.domain.TableField;

public class DataBaseAcessService {
	private DataBaseAcessDao dataBaseAcessDao;
		
	public DataBaseAcessDao getDataBaseAcessDao() {
		return dataBaseAcessDao;
	}


	public void setDataBaseAcessDao(DataBaseAcessDao dataBaseAcessDao) {
		this.dataBaseAcessDao = dataBaseAcessDao;
	}


	public List<TableField> findDataByIdMysql(String tablename) {
		return dataBaseAcessDao.findDataByIdMysql(tablename);
	}


	public List findAllMysql() {
		 return dataBaseAcessDao.findAllMysql();
	}
	public List<TableField> findDataByIdOracle(String tablename) {
		 return dataBaseAcessDao.findDataByIdOracle(tablename);
	}


	public List findAllOracle() {
		 return dataBaseAcessDao.findAllOracle();
	}
}
