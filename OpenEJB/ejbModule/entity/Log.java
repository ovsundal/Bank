package entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Log")
@Table(name = "Log")
public class Log implements Serializable {

	@Id
	@GeneratedValue
	private int id;
	@Column(name = "date", nullable = false)
	private Calendar date;
	@Column(name = "action", nullable = false)
	private String action;
	@Column(name = "amount", nullable = false)
	private int amount;
	@Column(name = "balance", nullable = false)
	private int balance;
	@Column(name = "thisAccountId", nullable = false)
	private int thisAccountId;
	@Column(name = "otherAccount", nullable = true)
	private int otherAccountId;

	public Log() {
	}

	//used for deposit and withdrawal
	public Log(Calendar date, String action, int amount, int balance, int thisAccountId) {
		this.date = date;
		this.action = action;
		this.amount = amount;
		this.thisAccountId = thisAccountId;
		this.balance = balance;
	}
	
	//used for transfers
	public Log(Calendar date, String action, int amount, int balance, int thisAccountId, int otherAccountId) {
		this.date = date;
		this.action = action;
		this.amount = amount;
		this.thisAccountId = thisAccountId;
		this.otherAccountId = otherAccountId;
		this.balance = balance;
	}

	@Override
	public String toString() {
	
		if("deposit".equals(action)) {
			return "(logId: " + id + " - From account: " + thisAccountId + " ) Date: " + date.getTime() + " - Deposited " + amount + " - Balance of account after transaction: " + balance;
		} else if("withdraw".equals(action)) {
			return "(logId: " + id + " - From account: " + thisAccountId + ") Date: " + date.getTime() + " - Withdrew " + amount + " - Balance of account after transaction: " + balance;
		} else if("transferFrom".equals(action)){
			return "(logId: " + id + " - From account: " + thisAccountId + ") Date: " + date.getTime() + " - Transfer " + amount + " - Balance of account after transaction: " + balance + " from this account: " + thisAccountId + " to this account: " + otherAccountId;
		} else if("transferTo".equals(action)) {
			return "(logId: " + id + " - From account: " + thisAccountId + ") Date: " + date.getTime() + " - Transfer " + amount + " - Balance of account after transaction: " + balance + " to this account: " + thisAccountId + " from this account: " + otherAccountId;
		} else if("create".equals(action)) {
			return "(logId: " + id + " - From account: " + thisAccountId + ") ACCOUNT CREATED - Date: " + date.getTime() + " - Balance of account: " + balance;
		}
		else {
			return "ERROR, unclassified log event displayed";
		}
	}
	
	public String getAccountBalance() {
	
		return "Date: " + date + " - balance: " + balance;
	}
	

	

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
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

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public int getFromAccount() {
		return thisAccountId;
	}

	public void setFromAccount(int thisAccountId) {
		this.thisAccountId = thisAccountId;
	}

}
