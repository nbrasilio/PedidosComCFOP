package com.michelin.mic.projetoBatch.model.dao;

import java.sql.Connection;
import java.util.List;

import com.michelin.mic.projetoBatch.model.vo.Ee03;

public interface Ee03DAO {
	
	public Connection createConnection();
	public List<Ee03> findEe03();

}
