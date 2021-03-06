package entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Class for Account-entity. Has a many(this)-to-one relation with entity class
 * Person, and a one-to-one relation with entity class Card
 * 
 * @author Ove
 *
 */
@Entity(name = "Account")
@Table(name = "Account")
public class Account implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;
	@Column(name = "name", length = 30)
	private String name;
	@Column(name = "balance", length = 20)
	private int balance;
	@Column(name = "dateCreated")
	private Calendar dateCreated;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "OWNER_ID")
	private Person owner;

	@OneToOne(mappedBy = "owner")
	private Card card;

	// constructors
	public Account() {
	}

	public Account(Person p, String navn, int balance, Calendar dato) {

		setOwner(p);
		setName(navn);
		setBalance(balance);
		setDateCreated(dato);
	}

	@Override
	public String toString() {
		return "[id=" + id + ", name=" + name + ", balance=" + balance + ", dateCreated=" + dateCreated.getTime() + "]";
	}

	// getters and setters
	public Person getOwner() {
		return owner;
	}

	public void setOwner(Person owner) {
		this.owner = owner;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public Calendar getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Calendar dateCreated) {
		this.dateCreated = dateCreated;
	}

}
