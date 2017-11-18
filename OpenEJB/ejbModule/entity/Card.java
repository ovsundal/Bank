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
	private int id;
	@Column(name = "pin", length = 4)
	private String pin;
	@Column(name = "isCardActive")
	private boolean isCardActive;
	
	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	private Account account;
	
	public Card(){}

	public Card(String pin, boolean isCardActive, Account account) {

		setPin(pin);
		setCardActive(isCardActive);
		setAccount(account);
	}

	@Override
	public String toString() {
		return "Card [id=" + id + ", pin=" + pin + ", isCardActive=" + isCardActive + ", account=" + account + "]";
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
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	};
	
}
