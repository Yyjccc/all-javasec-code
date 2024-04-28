package com.yyjccc.jdbc.c3p0;

import com.mchange.v2.c3p0.JndiRefConnectionPoolDataSource;

import java.beans.PropertyVetoException;
import java.sql.SQLException;

public class JNDI {

	public static void main(String[] args) {
		JndiRefConnectionPoolDataSource jndiRefConnectionPoolDataSource = new JndiRefConnectionPoolDataSource();
		try {
			jndiRefConnectionPoolDataSource.setJndiName("rmi://127.0.0.1:8085/RFKMphyY");
			jndiRefConnectionPoolDataSource.setLoginTimeout(2);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (PropertyVetoException e) {
			throw new RuntimeException(e);
		}

	}
}
