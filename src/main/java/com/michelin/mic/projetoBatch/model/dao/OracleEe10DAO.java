package com.michelin.mic.projetoBatch.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.michelin.mic.projetoBatch.model.vo.Ee10;

public class OracleEe10DAO implements Ee10DAO {

	private Connection cnn;
	private PreparedStatement pst;

	private String embeddedDriver = "oracle.jdbc.driver.OracleDriver";

	private static final String SQL_FINDMAX_RECNO = "SELECT MAX(RECNO) AS RECNO FROM EE10";
	private String SQL_ADD_EE10 = "INSERT INTO EE10 (E10PRO, E10PDV, E10ITE, E10LIN, E10QTD, E10VFR, E10VSE, E10PBR, E10ESP, E10QVL, E10REC, E10FLA, E10REJ, E10DTE, E10HOE, E10USE, E10DTI, E10HOI, E10USI, E10CFOP, RECNO, E10ZCIA, E10ZTPPED, E10MSGREJ) VALUES (?, ?, ?, '00000000000000000001', ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, ?, SYSDATE, '000000', 'ADMIN', NULL, NULL, NULL, ?, ?, ?, ?, NULL)";

	public OracleEe10DAO() {
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
	public Integer selectMaxRecno() {
		Integer recnoPk = null;
		try {
			this.pst = this.cnn.prepareStatement(SQL_FINDMAX_RECNO);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				recnoPk = Integer.parseInt(rs.getString("RECNO"));
			}
			return recnoPk;
		} catch (SQLException sql) {
			System.out.println(sql.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return recnoPk;
	}

	@Override
	public Boolean createEe10(Ee10 vo) {
		try {
			this.pst = this.cnn.prepareStatement(SQL_ADD_EE10);
			pst.setString(1, vo.getPro());
			pst.setString(2, vo.getPdv());
			pst.setString(3, vo.getIte());
			pst.setString(4, vo.getQtd());
			pst.setString(5, vo.getRej());
			pst.setString(6, vo.getCfop());
			pst.setInt(7, vo.getRecno());
			pst.setString(8, vo.getZcia());
			pst.setString(9, vo.getZtpped());
			return (pst.executeUpdate() == 1) ? Boolean.TRUE : Boolean.FALSE;
		} catch (SQLException sql) {
			System.out.println(sql.getMessage());
			return Boolean.FALSE;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return Boolean.FALSE;
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

	public String getEmbeddedDriver() {
		return embeddedDriver;
	}

	public void setEmbeddedDriver(String embeddedDriver) {
		this.embeddedDriver = embeddedDriver;
	}

}
