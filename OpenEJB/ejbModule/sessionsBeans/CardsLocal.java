package sessionsBeans;

import javax.ejb.Local;

import entity.Card;

@Local
public interface CardsLocal {

	public void add(Card c) throws Exception;
}
