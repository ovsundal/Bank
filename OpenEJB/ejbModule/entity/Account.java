package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.persistence.*;

/**
 * Class for Account-entity
 * @author Ove
 *
 */
@Entity(name= "Account")
@Table(name = "Account")
public class Account implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private int id;
	@Column(name = "name", length = 30)
	private String name;
	@Column(name = "balance",length = 20)
	private int balance;
	@Column(name = "dateCreated")
	private Calendar dateCreated;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="OWNER_ID")
	private Person owner;
	
//	@OneToOne(mappedBy="account", cascade = CascadeType.ALL,
//			fetch = FetchType.LAZY)
//	private Card card = new Card();
	
	
	@OneToOne(mappedBy="owner")
	private Card card;
	
	
	public Account() {}

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
