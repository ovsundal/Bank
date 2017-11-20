package sessionsBeans;

import java.util.List;

import javax.ejb.Local;

import entity.Account;
import entity.Card;

@Local
public interface CardsLocal {

	public void add(Card c) throws Exception;
	public List <Card> list() throws Exception;
	public void remove(Card c);
}