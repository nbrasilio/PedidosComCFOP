package com.michelin.mic.projetoBatch.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.michelin.mic.projetoBatch.model.vo.Ee03;

public class OracleEe03DAO implements Ee03DAO {

	private Connection cnn;
	private PreparedStatement pst;

	private String embeddedDriver = "oracle.jdbc.driver.OracleDriver";

	private static final String SQL_ALL_EE03 = "SELECT NUMPED, CDPROD, QTDE, ZCIA, ZTPPED, CODNCM, LJFORN FROM EE03 ORDER BY CODNCM";

	public OracleEe03DAO() {
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
	public List<Ee03> findEe03() {
		List<Ee03> list = new ArrayList<Ee03>();
		Ee03 ee;
		try {
			this.pst = this.cnn.prepareStatement(SQL_ALL_EE03);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				ee = new Ee03();
				ee.setNumPed(rs.getString("NUMPED").replaceAll(" ", ""));
				ee.setCdProd(rs.getString("CDPROD"));
				ee.setQtde(rs.getString("QTDE"));
				ee.setZcia(rs.getString("ZCIA"));
				ee.setZtpped(rs.getString("ZTPPED"));
				ee.setCodNcm(rs.getString("CODNCM"));
				ee.setLjforn(rs.getString("LJFORN"));
				list.add(ee);
			}
			return list;
		} catch (SQLException sql) {
			System.out.println(sql.getMessage());
			return null;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
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
