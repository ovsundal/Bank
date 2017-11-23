package sessionsBeans;

import java.util.List;

import javax.ejb.Remote;

import entity.Account;
import entity.Card;

@Remote
public interface CardsRemote {

	public void add(Card c) throws Exception;
	public List <Card> list() throws Exception;
	public void remove(Card c);
	public boolean validateCardNumber(String cardNumber);
	public boolean validatePin(String cardNumber, String pin);
	
}
