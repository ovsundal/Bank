package test;

import java.util.List;
import java.util.Properties;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;

import entity.Account;
import entity.Card;
import entity.Person;
import junit.framework.TestCase;
import sessionsBeans.AccountsRemote;
import sessionsBeans.CardsRemote;
import sessionsBeans.PersonsRemote;

public class CardTest extends TestCase {
	Context context;
	@EJB
	CardsRemote cards;
	AccountsRemote accounts;

	public void setUp() throws Exception {
		Properties p = new Properties();
		p.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.openejb.client.RemoteInitialContextFactory");
		p.put("BankDBDatasource", "new://Resource?type=DataSource");
		p.put("BankDBDatasource.JdbcDriver", "org.hsqldb.jdbcDriver");
		p.put("BankDBDatasource.JdbcUrl", "jdbc:hsqldb:file:data/bankdb/hsqldb");
		p.put("Unmanaged_BankDBDataSource", "new://Resource?type=DataSource");
		p.put("Unmanaged_BankDBDataSource.JdbcDriver", "org.hsqldb.jdbcDriver");
		p.put("Unmanaged_BankDBDataSource.JdbcUrl", "jdbc:hsqldb:file:data/bankdb/hsqldb");
		p.put("Unmanaged_BankDBDataSource.JtaManaged", "false");
		context = new InitialContext(p);
		accounts = (AccountsRemote) context.lookup("AccountsRemote");
		cards = (CardsRemote) context.lookup("CardsRemote");
	}

	@Override
	public void tearDown() throws Exception {
	}

	public void addCard() {
		try {
			List<Account> list = accounts.list();

			Account acc = list.get(0);
			Account acc2 = list.get(3);
			
			  
			Card c = new Card(acc);		
			Card c2 = new Card(acc2);		
			 	
			 cards.add(c);		
			 cards.add(c2);
			
			//todo:
			//generate card number
			
	
			//make card with these
			
			//return number and pin to user


		} catch (Exception e) {
			System.out.println("ERROR, could not add card to account");
			e.printStackTrace();
		}
	}

	public void list() throws Exception {
		List<Card> list = cards.list();
		for (Card c : list) {
			System.out.println("Listing : " + c.toString());
		}
	}

	public void remove() {
		try {
			List<Card> list = cards.list();
			for (Card c : list) {
				if (c.getId() > 0) {
					cards.remove(c);
					System.out.println("Deleting ... : " + cards.toString());
				} else {
					System.out.println("Delete cards failed ... : " + cards.toString());
				}
			}
		} catch (Exception e) {
			System.out.println("ERROR, could not remove cards from db");
			e.printStackTrace();
		}

	}

}
