package com.michelin.mic.projetoBatch.model.dao;

import java.sql.Connection;

public interface AtoNcmDAO {
	
	public Connection createConnection();
	public String findAtoByNcm(String codNcm);
	
}
