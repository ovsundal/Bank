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
	@Column(name = "cardNumber")
	private String cardNumber;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="CARD_ID")
	private Account owner;
	
	public Card(){}

	public Card(Account account) {
		
		//generate random pin
		setPin(generateRandomPin());
		
		//generate random card number
		String cardNumber = RandomCreditCardNumberGenerator.generateMasterCardNumber();
		setCardNumber(cardNumber);

		setAccount(account);
	}

	@Override
	public String toString() {
		return "Card [id=" + id + ", pin=" + pin + ", cardNumber=" + cardNumber + ", owner=" + owner.getId() + "]";
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
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
	}

	public String greet() {
		
		return "New card generated for account " + getAccount().toString() + "\nNew card number: " + cardNumber + "\nNew pin: " + pin;
	};
	
	public static String generateRandomPin() {
		
		Random rand = new Random();
		String pin = String.valueOf(rand.nextInt(10000));
		return pin;
	}
	
}
