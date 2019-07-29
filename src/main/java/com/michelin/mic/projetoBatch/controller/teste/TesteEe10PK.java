package com.michelin.mic.projetoBatch.controller.teste;

import com.michelin.mic.projetoBatch.model.dao.Ee10DAO;
import com.michelin.mic.projetoBatch.model.dao.OracleEe10DAO;

public class TesteEe10PK {

	public static void main(String[] args) {
		try {
			Ee10DAO ee10DAO = new OracleEe10DAO();
			Integer recnoPk = ee10DAO.selectMaxRecno();
			recnoPk++;
			System.out.println("Next PK: " + recnoPk);
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

}
