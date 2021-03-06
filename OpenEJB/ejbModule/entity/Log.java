package entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Log")
@Table(name = "Log")
/**
 * Class for Log-entity. Stores account creation, deposit, withdrawal and
 * transfers between all accounts
 * 
 * @author Ove
 *
 */
public class Log implements Serializable {

	private static final long serialVersionUID = 1L;

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

	//constructors
	public Log() {
	}

	// constructs Log-object used for deposit and withdrawals
	public Log(Calendar date, String action, int amount, int balance, int thisAccountId) {
		this.date = date;
		this.action = action;
		this.amount = amount;
		this.thisAccountId = thisAccountId;
		this.balance = balance;
	}

	// constructs Log-object used for transfers
	public Log(Calendar date, String action, int amount, int balance, int thisAccountId, int otherAccountId) {
		this.date = date;
		this.action = action;
		this.amount = amount;
		this.thisAccountId = thisAccountId;
		this.otherAccountId = otherAccountId;
		this.balance = balance;
	}

	//return appropriate string depending on what type of transaction event that was done
	@Override
	public String toString() {

		if ("deposit".equals(action)) {
			return "(logId: " + id + " - From account: " + thisAccountId + " ) Date: " + date.getTime()
					+ " - Deposited " + amount + " - Balance of account after transaction: " + balance;
		} else if ("withdraw".equals(action)) {
			return "(logId: " + id + " - From account: " + thisAccountId + ") Date: " + date.getTime() + " - Withdrew "
					+ amount + " - Balance of account after transaction: " + balance;
		} else if ("transferFrom".equals(action)) {
			return "(logId: " + id + " - From account: " + thisAccountId + ") Date: " + date.getTime() + " - Transfer from this account "
					+ amount  + " (Transfer from this account: "
					+ thisAccountId + " to account: " + otherAccountId + ")" + " - Balance of account after transaction: " + balance;
		} else if ("transferTo".equals(action)) {
			return "(logId: " + id + " - From account: " + thisAccountId + ") Date: " + date.getTime() + " - Transfer to this account "
					+ amount  + " (Transfer to this account: " + thisAccountId + " from account: " + otherAccountId + ")" + 
					" - Balance of account after transaction: " + balance;
		} else if ("create".equals(action)) {
			return "(logId: " + id + " - From account: " + thisAccountId + ") ACCOUNT CREATED - Date: " + date.getTime()
					+ " - Balance of account: " + balance;
		} else {
			return "ERROR, unclassified log event displayed";
		}
	}

	//getters and setters
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

	public int getThisAccountId() {
		return thisAccountId;
	}

	public void setThisAccountId(int thisAccountId) {
		this.thisAccountId = thisAccountId;
	}


}
