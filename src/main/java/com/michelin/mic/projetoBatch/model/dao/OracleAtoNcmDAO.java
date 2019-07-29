package com.michelin.mic.projetoBatch.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OracleAtoNcmDAO implements AtoNcmDAO {

	private Connection cnn;
	private PreparedStatement pst;

	private String embeddedDriver = "oracle.jdbc.driver.OracleDriver";

	private String SQL_FIND_CD_NCM = "SELECT CD_ATO FROM ATO_NCM WHERE CD_NCM=? AND (DT_FIM_VIG IS NULL OR DT_FIM_VIG >= CURRENT_DATE)";

	public OracleAtoNcmDAO() {
		this.cnn = createConnection();
	}

	@Override
	public Connection createConnection() {
		try {
			Class.forName(this.embeddedDriver);
			String url = "jdbc:oracle:thin:SYSTEM/admin@localhost:1521:xe";
			this.cnn = DriverManager.getConnection(url);
			return cnn;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public String findAtoByNcm(String codNcm) {
		String cdNcm = null;
		try {
			this.pst = this.cnn.prepareStatement(SQL_FIND_CD_NCM);
			pst.setString(1, codNcm);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				cdNcm = rs.getString("CD_ATO");
			}
			return cdNcm;
		} catch (SQLException sql) {
			System.out.println(sql.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return cdNcm;
	}

	public Connection getCnn() {
		return cnn;
	}

	public void setCnn(Connection cnn) {
		this.cnn = cnn;
	}

	public PreparedStatement getPst() {
		return pst;
	}

	public void setPst(PreparedStatement pst) {
		this.pst = pst;
	}

	public String getEmbeddedDrver() {
		return embeddedDriver;
	}

	public void setEmbeddedDrver(String embeddedDrver) {
		this.embeddedDriver = embeddedDrver;
	}

}
