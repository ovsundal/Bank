package entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name= "Log")
@Table(name = "Log")
public class Log {

	@Id
	@GeneratedValue
	private int id;
	@Column(name = "date", nullable = false)
	private Date date;
	@Column(name = "action", nullable = false)
	private String action;
	@Column(name = "fromAccount", nullable = false)
	private Account fromAccount;
	
	public Log() {}

	@Override
	public String toString() {
		return "Log [id=" + id + ", date=" + date + ", action=" + action + ", fromAccount=" + fromAccount + "]";
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

