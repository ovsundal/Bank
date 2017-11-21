package entity;

import java.io.Serializable;
import java.util.Random;

import javax.persistence.*;

/**
 * Class for Account-entity
 * @author Ove
 *
 */
@Entity(name= "Card")
@Table(name = "Card")
public class Card implements Serializable {

	@Id
	@GeneratedValue
	private int id;
	@Column(name = "pin", length = 4)
	private String pin;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="CARD_ID")
	private Account owner;
	
	public Card(){}

	public Card(Account account) {

		//generate random pin
		Random rand = new Random();
		String pin = String.valueOf(rand.nextInt(10000));
		setPin(pin);

		setAccount(account);
	}

	@Override
	public String toString() {
		return "Card [id=" + id + ", pin=" + pin + ", owner=" + owner.getId() + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public Account getAccount() {
		return owner;
	}

	public void setAccount(Account account) {
		this.owner = account;
	};
	
}
