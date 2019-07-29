package com.michelin.mic.projetoBatch.controller.teste;

import java.util.List;

import com.michelin.mic.projetoBatch.model.dao.Ee03DAO;
import com.michelin.mic.projetoBatch.model.dao.OracleEe03DAO;
import com.michelin.mic.projetoBatch.model.vo.Ee03;

public class TesteEe03 {

	public static void main (String[] args ) {
		try {
			Ee03DAO ee03DAO = new OracleEe03DAO();
			List<Ee03> listEe03 = ee03DAO.findEe03();
			
			if (listEe03.isEmpty()) {
				System.out.println("Deu ruim, a lista tá vazia...");
			} else {
				for(Ee03 ee03 : listEe03) {
					System.out.println("Código NCM: " + ee03.getCodNcm());
					System.out.println("Código LJFORN: " + ee03.getLjforn() + "\n");
				}
			}
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}
	
}
