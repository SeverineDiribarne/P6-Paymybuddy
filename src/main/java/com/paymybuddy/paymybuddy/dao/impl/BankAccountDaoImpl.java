package com.paymybuddy.paymybuddy.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.springframework.stereotype.Repository;
import com.paymybuddy.paymybuddy.dao.contract.BankAccountDao;

@Repository
public class BankAccountDaoImpl implements BankAccountDao{

	@Override
	public void getBankAccount() {
		 try
	     {
			 Class.forName("com.mysql.jdbc.Driver");
	         Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db-paymybuddy","root","rootroot");
	         Statement statement = connection.createStatement();  
	         ResultSet result = statement.executeQuery("SELECT * FROM bank_account");
	         System.out.println("Connected");  
	         
	         while(result.next()) {
	        	 System.out.println(result.getString(2)+" "+ result.getString(3) +" "+ result.getString(4)+" "+ result.getString(5));
	         }
	         connection.close();
	     }
	     catch(Exception e)
	     {
	         System.out.println(e);
	     }
		
	}

}
