package com.michelin.mic.projetoBatch.controller.teste;

import java.util.List;

import com.michelin.mic.projetoBatch.model.dao.AtoNcmDAO;
import com.michelin.mic.projetoBatch.model.dao.Ee03DAO;
import com.michelin.mic.projetoBatch.model.dao.OracleAtoNcmDAO;
import com.michelin.mic.projetoBatch.model.dao.OracleEe03DAO;
import com.michelin.mic.projetoBatch.model.vo.Ee03;

public class TesteEe03AtoNcm {

	public static void main(String[] args) {
		try {
			Ee03DAO ee03DAO = new OracleEe03DAO();
			List<Ee03> listEe03 = ee03DAO.findEe03();

			if (listEe03.isEmpty()) {
				System.out.println("Lista está vazia.");
			} else {
				for (Ee03 ee03 : listEe03) {
					System.out.println("Código NCM: " + ee03.getCodNcm());
					AtoNcmDAO atoNcmDAO = new OracleAtoNcmDAO();
					String cdAto = atoNcmDAO.findAtoByNcm(ee03.getCodNcm());
					if (cdAto != null) {
						System.out.println("CFOP: 7127" + "\n");
					} else {
						System.out.println("CFOP: 7101" + "\n");
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}

}
