package sessionBeans;

import java.util.List;

import javax.ejb.Local;

import entity.Account;
import entity.Card;

@Local
public interface CardsLocal {
	/**
	 * Add a card to an existing account
	 * 
	 * @param c
	 * @throws Exception
	 */
	public void add(Card c) throws Exception;

	public boolean validateCardNumber(String cardNumber);

	public boolean validatePin(String cardNumber, String pin);

	/**
	 * Finds the account belonging to card number
	 */
	public int getAccountId(String cardNumber);

	/**
	 * Lists all cards in db. Only used for testing
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Card> list() throws Exception;

	/**
	 * Removes a card. Only used for testing
	 * 
	 * @param c
	 */
	public void remove(Card c);
}
