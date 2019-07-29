package com.michelin.mic.projetoBatch.controller.teste;

import com.michelin.mic.projetoBatch.model.dao.AtoNcmDAO;
import com.michelin.mic.projetoBatch.model.dao.OracleAtoNcmDAO;

public class TesteAtoNcm {

	public static void main (String[] args ) {
		try {
			AtoNcmDAO atoNcmDAO = new OracleAtoNcmDAO();
			String cdAto = atoNcmDAO.findAtoByNcm("10000005");
			System.out.println("Código do Ato: " + cdAto);
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
	
}
