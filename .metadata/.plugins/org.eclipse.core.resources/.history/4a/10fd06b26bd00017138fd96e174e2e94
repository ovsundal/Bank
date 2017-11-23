package client;

import java.util.Properties;
import java.util.Scanner;

import javax.naming.Context;
import javax.naming.InitialContext;

import sessionsBeans.MiniBankRemote;
import test.CardTest;
import test.KontoTest;
import test.LogTest;
import test.MinibankTest;
import test.PersonTest;

public class Client {

	static MiniBankRemote minibank;
	
	public static void main(String[] args) {
		
		try {
			setUpClient();
		} catch (Exception e) {
			System.out.println("ERROR, could not connect to remote beans");
			e.printStackTrace();
		}
		
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			
			System.out.println("*************************************************************");
			System.out.println("Welcome to HBF MiniBank! Please login using your card number:");
			String cardNumber = sc.nextLine();
			
			if(minibank.validateCardNumber(cardNumber)) {
				System.out.println("Enter PIN");
				String pin = sc.next();
				
					
				
				
				
				
				
				
				
				
				
				
				
				
				
			} else {
				System.out.println("Could not recognize card number, please try again");
				continue;
			}
		}
		
		
		
		
	}
	
	public static void setUpClient() throws Exception {
			
		Properties p = new Properties();
		p.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.openejb.client.RemoteInitialContextFactory");
		p.put("BankDBDatasource", "new://Resource?type=DataSource");
		p.put("BankDBDatasource.JdbcDriver", "org.hsqldb.jdbcDriver");
		p.put("BankDBDatasource.JdbcUrl", "jdbc:hsqldb:file:data/bankdb/hsqldb");
		p.put("Unmanaged_BankDBDataSource", "new://Resource?type=DataSource");
		p.put("Unmanaged_BankDBDataSource.JdbcDriver", "org.hsqldb.jdbcDriver");
		p.put("Unmanaged_BankDBDataSource.JdbcUrl", "jdbc:hsqldb:file:data/bankdb/hsqldb");
		p.put("Unmanaged_BankDBDataSource.JtaManaged", "false");
		Context context = new InitialContext(p);
		minibank = (MiniBankRemote) context.lookup("MiniBankRemote");
		}
}
