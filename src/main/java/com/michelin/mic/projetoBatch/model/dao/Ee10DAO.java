package com.michelin.mic.projetoBatch.model.dao;

import java.sql.Connection;

import com.michelin.mic.projetoBatch.model.vo.Ee10;

public interface Ee10DAO {
	
	public Connection createConnection();
	public Integer selectMaxRecno();
	public Boolean createEe10 (Ee10 vo);

}
