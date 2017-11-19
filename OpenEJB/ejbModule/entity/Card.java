package entity;

import java.io.Serializable;

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
	@Column(name = "isCardActive")
	private boolean isCardActive;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="CARD_ID")
	private Account owner;
	
	public Card(){}

	public Card(String pin, boolean isCardActive, Account account) {

		setPin(pin);
		setCardActive(isCardActive);
		setAccount(account);
	}

	@Override
	public String toString() {
		return "Card [id=" + id + ", pin=" + pin + ", isCardActive=" + isCardActive + ", owner=" + owner.getId() + "]";
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

	public boolean isCardActive() {
		return isCardActive;
	}

	public void setCardActive(boolean isCardActive) {
		this.isCardActive = isCardActive;
	}

	public Account getAccount() {
		return owner;
	}

	public void setAccount(Account account) {
		this.owner = account;
	};
	
}
