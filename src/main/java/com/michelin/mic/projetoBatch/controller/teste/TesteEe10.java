package com.michelin.mic.projetoBatch.controller.teste;

import java.util.List;

import com.michelin.mic.projetoBatch.model.dao.AtoNcmDAO;
import com.michelin.mic.projetoBatch.model.dao.Ee03DAO;
import com.michelin.mic.projetoBatch.model.dao.OracleAtoNcmDAO;
import com.michelin.mic.projetoBatch.model.dao.OracleEe03DAO;
import com.michelin.mic.projetoBatch.model.vo.Ee03;
import com.michelin.mic.projetoBatch.model.vo.Ee10;

public class TesteEe10 {

	public static void main(String[] args) {
		Ee10 ee10;
		try {
			List<Ee03> listEe03;
			Ee03DAO ee03DAO = new OracleEe03DAO();
			listEe03 = ee03DAO.findEe03();
			
			for(Ee03 ee03 : listEe03) {
				System.out.println("EE03 NCM: " + ee03.getCodNcm());
				System.out.println("EE03 LJFORN: " + ee03.getLjforn());
				ee10 = new Ee10();
				ee10.setPro(ee03.getNumPed());
				ee10.setPdv(ee03.getNumPed());
				ee10.setIte(ee03.getCdProd());
				ee10.setQtd(ee03.getQtde());
				ee10.setZcia(ee03.getZcia());
				ee10.setZtpped(ee03.getZtpped());
				
				AtoNcmDAO atoNcmDAO = new OracleAtoNcmDAO();
				String cdAto = atoNcmDAO.findAtoByNcm(ee03.getCodNcm());
				ee10.setRej(cdAto);
				
				String ljforn = ee03.getLjforn();
				if("01".equals(ljforn) || "1 ".equals(ljforn) || " 1".equals(ljforn)) {
					ee10.setCfop(null);
				}  else {
					if(cdAto != null) {
						ee10.setCfop("7127");
					} else {
						ee10.setCfop("7101");
					}
				}
				System.out.println("EE10 ATO: " + ee10.getRej() + "\nEE10 CFOP: " + ee10.getCfop() + "\nEE10 PRO: " + ee10.getPro() + "\nEE10 PDV: " + ee10.getPdv() + "\nEE10 ITE: " + ee10.getIte() + "\nEE10 QTD: " + ee10.getQtd() + "\nEE10 ZCIA: " + ee10.getZcia() + "\nEE10 ZTPPED: " + ee10.getZtpped() + "\n");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
}
