package com.michelin.mic.projetoBatch.controller;

import java.util.List;

import com.michelin.mic.projetoBatch.model.dao.AtoNcmDAO;
import com.michelin.mic.projetoBatch.model.dao.Ee03DAO;
import com.michelin.mic.projetoBatch.model.dao.Ee10DAO;
import com.michelin.mic.projetoBatch.model.dao.OracleAtoNcmDAO;
import com.michelin.mic.projetoBatch.model.dao.OracleEe03DAO;
import com.michelin.mic.projetoBatch.model.dao.OracleEe10DAO;
import com.michelin.mic.projetoBatch.model.vo.Ee03;
import com.michelin.mic.projetoBatch.model.vo.Ee10;

public class DrawbackController {

	public static void main(String[] args) {
		Ee10 ee10;
		try {
			Ee03DAO ee03Dao = new OracleEe03DAO();
			List<Ee03> listEe03 = ee03Dao.findEe03();

			for (Ee03 ee03 : listEe03) {
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
				if ("01".equals(ljforn) || "1 ".equals(ljforn) || " 1".equals(ljforn)) {
					ee10.setCfop(null);
				} else {
					if (cdAto != null) {
						ee10.setCfop("7127");
					} else {
						ee10.setCfop("7101");
					}
				}
				Ee10DAO ee10DAO = new OracleEe10DAO();
				Integer recnoPk = ee10DAO.selectMaxRecno();
				recnoPk++;
				ee10.setRecno(recnoPk);
				System.out.println(ee10DAO.createEe10(ee10));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
