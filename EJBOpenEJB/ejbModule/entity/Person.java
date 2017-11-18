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
	
	@OneToMany(mappedBy="owner", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Collection<Konto> konto = new ArrayList<>();

	@Id
	@GeneratedValue
	private int id;
	@Column (name = "Personnummer", length = 11)
	private String Personnummer;
	@Column(name = "Navn", length = 30)
	private String Navn;
	@Column(name = "Adresselinje_1", length = 30)
	private String Adresselinje_1;
	@Column(name = "Adresselinje_2", length = 30)
	private String Adresselinje_2;
	@Column(name = "Postnummer", length = 4)
	private String Postnummer;
	@Column(name = "Poststed", length = 35)
	private String Poststed;

	/*** Konstruktører*/
	public Person () {}

	public Person(String Personnummer, String Navn, String Adresselinje_1,String Adresselinje_2, String Postnummer, String Poststed) {
		
		setPersonnummer(Personnummer);
		setNavn(Navn);
		setAdresselinje_1(Adresselinje_1);
		setAdresselinje_2(Adresselinje_2);
		setPostnummer(Postnummer);
		setPoststed(Poststed);
		}
	
	

	@Override
	public String toString() {
		return "Person [kontoer=" + konto.size() + ", id=" + id + ", Personnummer=" + Personnummer + ", Navn=" + Navn
				+ ", Adresselinje_1=" + Adresselinje_1 + ", Adresselinje_2=" + Adresselinje_2 + ", Postnummer="
				+ Postnummer + ", Poststed=" + Poststed + "]";
	}

	public String getPersonnummer() {
		return Personnummer;
	}

	public void setPersonnummer(String personnummer) {
		Personnummer = personnummer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNavn() {
		return Navn;
	}

	public void setNavn(String navn) {
		Navn = navn;
	}

	public String getAdresselinje_1() {
		return Adresselinje_1;
	}

	public void setAdresselinje_1(String adresselinje_1) {
		Adresselinje_1 = adresselinje_1;
	}

	public String getAdresselinje_2() {
		return Adresselinje_2;
	}

	public void setAdresselinje_2(String adresselinje_2) {
		Adresselinje_2 = adresselinje_2;
	}

	public String getPostnummer() {
		return Postnummer;
	}

	public void setPostnummer(String postnummer) {
		Postnummer = postnummer;
	}

	public String getPoststed() {
		return Poststed;
	}

	public void setPoststed(String poststed) {
		Poststed = poststed;
	}

	public Collection<Konto> getKonto() {
		return konto;
	}

	public void setKonto(List<Konto> konto) {
		this.konto = konto;
	}
	
	
}