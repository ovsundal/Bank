package entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name= "Log")
@Table(name = "Log")
public class Log implements Serializable {

	@Id
	@GeneratedValue
	private int id;
	@Column(name = "date", nullable = false)
	private Date date;
	@Column(name = "action", nullable = false)
	private String action;
	@Column(name = "amount", nullable = false)
	private int amount;
	@Column(name = "fromAccount", nullable = false)
	private Account fromAccount;
	
	public Log() {}
	
	public Log(Date date, String action, int amount, Account fromAccount) {
		super();
		this.date = date;
		this.action = action;
		this.amount = amount;
		this.fromAccount = fromAccount;
	}

	@Override
	public String toString() {
		return "Log [id=" + id + ", date=" + date + ", action=" + action + ", amount=" + amount + ", fromAccount="
				+ fromAccount + "]";
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Account getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(Account fromAccount) {
		this.fromAccount = fromAccount;
	}

	
	
	
	
}

