package entity;

import java.io.Serializable;
import java.util.Random;

import javax.persistence.*;

/**
 * Class for Card-entity. Has a one-to-one relation with Account-entity class
 * 
 * @author Ove
 *
 */
@Entity(name = "Card")
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
	@JoinColumn(name = "CARD_ID")
	private Account owner;

	// constructors
	public Card() {
	}

	public Card(Account account) {

		// generate random pin
		setPin(generateRandomPin());

		// generate random card number
		String cardNumber = RandomCreditCardNumberGenerator.generateMasterCardNumber();
		setCardNumber(cardNumber);

		setAccount(account);
	}

	@Override
	public String toString() {
		return "Card [id=" + id + ", pin=" + pin + ", cardNumber=" + cardNumber + ", owner=" + owner.getId() + "]";
	}

	// getters and setters
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

	// greeting to publish card and pin number when a new card has been made
	public String greet() {

		return "New card generated for account (" + getAccount().getId() + " - " + getAccount().getName()
				+ ") Card number: " + cardNumber + " PIN: " + pin;
	};

	public static String generateRandomPin() {

		Random rand = new Random();
		String pin = String.format("%04d", rand.nextInt(10000));
		return pin;
	}

}
