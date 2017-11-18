package entity;

import java.io.Serializable;
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
	private String balance;
	@Column(name = "dateCreated")
	private Date dateCreated;
	
	//add cascade , cascade = CascadeType.ALL
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="OWNER_ID")
	private Person owner;
	
	public Account() {}

	public Account(Person p, String navn, String saldo, Date dato) {
		
		setOwner(p);
		setName(navn);
		setBalance(saldo);
		setDateCreated(dato);
	}
	
	
	
	@Override
	public String toString() {
		return "Account [id=" + id + ", name=" + name + ", balance=" + balance + ", dateCreated=" + dateCreated
				+ ", owner=" + owner + "]";
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

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	


	
	
	
	
	
}
