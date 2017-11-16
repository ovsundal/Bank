package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity(name = "Person")
@Table(name = "Person")
public class Person implements Serializable {
	
	@OneToOne(mappedBy="owner")
	private Konto konto;

	@Id
	@GeneratedValue
	private int id;
	@Column(name = "Fodselsdato")
	private Date Fodselsdato;
	@Column(name = "Fodselsnummer", length = 5)
	private String Fodselsnummer;
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
	public Person(int id, Date Fodselsdato, String Fodselsnummer, String Navn,String Adresselinje_1, String Adresselinje_2, String Postnummer,String Poststed) {
		
		setId(id);
		setFodselsdato(Fodselsdato);
		setFodselsnummer(Fodselsnummer);
		setNavn(Navn);
		setAdresselinje_1(Adresselinje_1);
		setAdresselinje_2(Adresselinje_2);
		setPostnummer(Postnummer);
		setPoststed(Poststed);

		}

	public Person(Date Fodselsdato, String Fodselsnummer, String Navn, String Adresselinje_1,String Adresselinje_2, String Postnummer, String Poststed) {
		
		setFodselsdato(Fodselsdato);
		setFodselsnummer(Fodselsnummer);
		setNavn(Navn);
		setAdresselinje_1(Adresselinje_1);
		setAdresselinje_2(Adresselinje_2);
		setPostnummer(Postnummer);
		setPoststed(Poststed);
		}

	public Person () {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFodselsdato() {
		return Fodselsdato;
	}

	public void setFodselsdato(Date fodselsdato) {
		Fodselsdato = fodselsdato;
	}

	public String getFodselsnummer() {
		return Fodselsnummer;
	}

	public void setFodselsnummer(String fodselsnummer) {
		Fodselsnummer = fodselsnummer;
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
}