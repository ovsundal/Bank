package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity(name = "Person")
@Table(name = "Person")
public class Person implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy="owner", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Collection<Account> accounts = new ArrayList<>();

	@Id
	@GeneratedValue
	private int id;
	@Column (name = "personId", length = 11)
	private String personId;
	@Column(name = "name", length = 30)
	private String name;
	@Column(name = "address_1", length = 30)
	private String address_1;
	@Column(name = "address_2", length = 30)
	private String address_2;
	@Column(name = "areaCode", length = 4)
	private String areaCode;
	@Column(name = "area", length = 35)
	private String area;

	/*** Constructors*/
	public Person () {}

	public Person(String Personnummer, String Navn, String Adresselinje_1,String Adresselinje_2, String Postnummer, String Poststed) {
		
		setPersonId(Personnummer);
		setName(Navn);
		setAddress_1(Adresselinje_1);
		setAddress_2(Adresselinje_2);
		setAreaCode(Postnummer);
		setArea(Poststed);
		}

	@Override
	public String toString() {
		return "Person [accounts=" + accounts.size() + ", id=" + id + ", personId=" + personId + ", name=" + name
				+ ", address_1=" + address_1 + ", address_2=" + address_2 + ", areaCode=" + areaCode + ", area=" + area
				+ "]";
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
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

	public String getAddress_1() {
		return address_1;
	}

	public void setAddress_1(String address_1) {
		this.address_1 = address_1;
	}

	public String getAddress_2() {
		return address_2;
	}

	public void setAddress_2(String address_2) {
		this.address_2 = address_2;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Collection<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> account) {
		this.accounts = account;
	}
	
	
}