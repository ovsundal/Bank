package entity;

import java.io.Serializable;
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
	private Date date;
	@Column(name = "action", nullable = false)
	private String action;
	@Column(name = "amount", nullable = false)
	private int amount;
	@Column(name = "thisAccountId", nullable = false)
	private int thisAccountId;
	@Column(name = "otherAccount", nullable = true)
	private int otherAccountId;

	public Log() {
	}

	//used for deposit and withdrawal
	public Log(Date date, String action, int amount, int thisAccountId) {
		this.date = date;
		this.action = action;
		this.amount = amount;
		this.thisAccountId = thisAccountId;
	}
	
	//used for transfers
	public Log(Date date, String action, int amount, int thisAccountId, int otherAccountId) {
		this.date = date;
		this.action = action;
		this.amount = amount;
		this.thisAccountId = thisAccountId;
		this.otherAccountId = otherAccountId;
	}

	@Override
	public String toString() {
	
		if("deposit".equals(action)) {
			return "(logId: " + id + ") Date: " + date + " - Deposited " + amount;
		} else if("withdraw".equals(action)) {
			return "(logId: " + id + ") Date: " + date + " - Withdrew " + amount;
		} else if("transferFrom".equals(action)){
			return "(logId: " + id + ") Date: " + date + " - Transfer " + amount + " from this account: " + thisAccountId + " to this account: " + otherAccountId;
		} else if("transferTo".equals(action)) {
			return "(logId: " + id + ") Date: " + date + " - Transfer " + amount + " to this account: " + thisAccountId + " from this account: " + otherAccountId;
		} else {
			return "ERROR, unclassified log event displayed";
		}
		
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

	public int getFromAccount() {
		return thisAccountId;
	}

	public void setFromAccount(int thisAccountId) {
		this.thisAccountId = thisAccountId;
	}

}
