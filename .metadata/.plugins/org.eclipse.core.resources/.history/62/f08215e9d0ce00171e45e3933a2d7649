package sessionsBeans;

import java.util.Date;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import entity.Account;
import entity.Card;
import entity.Person;

/**
 * Session Bean implementation class MiniBank
 */
@Stateless
@LocalBean
@TransactionManagement(value=TransactionManagementType.CONTAINER)
@TransactionAttribute(value= TransactionAttributeType.SUPPORTS)
public class MiniBank implements MiniBankRemote, MiniBankLocal {
	
	@PersistenceContext(unitName = "bankdb-unit", type = PersistenceContextType.TRANSACTION)
	private EntityManager entityManager;
	
	@EJB(name = "Accounts", beanInterface = AccountsLocal.class)
	AccountsLocal accountsBean;
	
	@EJB(name = "Persons", beanInterface = PersonsLocal.class)
	PersonsLocal personsBean;
	
	@EJB(name = "Cards", beanInterface = CardsLocal.class)
	CardsLocal cardsBean;
	
    /**
     * Default constructor. 
     */
    public MiniBank() {}

    /**
     * Used to deposit money into an account
     */
	@Override
	@TransactionAttribute(value= TransactionAttributeType.REQUIRES_NEW)
	public String deposit(int accountId, int amount) {
		
		//check that amount to withdraw is not negative
		if(amount <= 0) {
			return "You cannot deposit a negative amount";
		}
		
		Account acc;
		try {
			acc = accountsBean.get(accountId);
			acc.setBalance(acc.getBalance() + amount);
			entityManager.flush();
			acc = accountsBean.get(accountId);
			
			return "You have deposited " + amount + " into account " + acc.getName();
		} catch (Exception e) {
			System.out.println("ERROR, could not get account from database. Money was not deposited");
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * Used to withdraw money from an account
	 */
	@Override
	@TransactionAttribute(value= TransactionAttributeType.REQUIRES_NEW)
	public String withdraw(int accountId, int amount) {
		
		//check that amount to withdraw is not negative
		if(amount <= 0) {
			return "You cannot withdraw a negative amount";
		}
		
		//check that the amount is dividable by 100
		if(amount % 100 != 0) {
			return "Amount must be a whole 100 (f.e. 100, 1000, 3200)";
		}
		
		try {
			Account acc;
			acc = accountsBean.get(accountId);
			
			//check that there is enough money on the account
			if(acc.getBalance() < amount) {
				return "There is not enough money on the account to perform the transaction";
			}
			
			acc.setBalance(acc.getBalance() - amount);
			return "You have withdrawn " + amount + " from account " + acc.getName();
		} catch(Exception e) {
			System.out.println("Error, could not connect to database, or something else is wrong");
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * Used to get account balance
	 */
	@Override
	@TransactionAttribute(value= TransactionAttributeType.SUPPORTS)
	public String getAccountBalance(int accountId) {
		try {
			
			int balance = accountsBean.getAccountBalance(accountId);
			return "Balance: " + String.valueOf(balance);
		} catch (Exception e) {
			System.out.println("ERROR, could not connect to database, or something else is wrong");
		}
		return "";
	}
	
	@Override
	@TransactionAttribute(value= TransactionAttributeType.REQUIRES_NEW)
	public String transfer(int fromAccId, int toAccId, int amount) {
		
		String response = "Error, cannot transfer a negative amount";
		
		//abort if amount is negative or 0
		if(amount <= 0) {
			return response;
		}
		
		try {
			Account fromAcc = accountsBean.get(fromAccId);
			
			//abort if account does not have enough funds 
			if(fromAcc.getBalance() < amount) {
				response = "Insufficient funds for transfer. Aborted";
				return response;
			}
			
			Account toAcc = accountsBean.get(toAccId);
			
			fromAcc.setBalance(fromAcc.getBalance() - amount);
			//add response to log here
			toAcc.setBalance(toAcc.getBalance() + amount);
			//add response to log here
			response = "Transferred " + amount + " from account: " + fromAcc.getName() + " to account " + toAcc.getName();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}

	@Override
	@TransactionAttribute(value= TransactionAttributeType.REQUIRES_NEW)
	public String createAccount(String personId, String accountName, int initialBalance, Date dateCreated) {
		
		
		try {
			//find person
			Person p = personsBean.get(personId);
			
			//create account
			Account acc = new Account(p, accountName, initialBalance, dateCreated);
			accountsBean.add(acc);
			
			//create card
			Card card = new Card(acc);
			cardsBean.add(card);
			return card.greet();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "Error, could not create account";
	}

	@Override
	@TransactionAttribute(value= TransactionAttributeType.REQUIRES_NEW)
	public boolean validateCardNumber(String cardNumber) {
	
		return cardsBean.validateCardNumber(cardNumber);
	}

	@Override
	public boolean validatePin(String cardNumber, String pin) {
		// TODO Auto-generated method stub
		return cardsBean.validatePin(cardNumber, pin);
	}
}
