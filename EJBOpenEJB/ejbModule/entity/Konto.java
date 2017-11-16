package entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Class for Konto-entity
 * @author Ove
 *
 */
@Entity(name= "Konto")
@Table(name = "Konto")
public class Konto implements Serializable {

	@Id
	@GeneratedValue
	private int id;
	@Column(name = "Navn")
	private String Navn;
	@Column(name = "Saldo")
	private String Saldo;
	@Column(name = "Dato")
	private Date Dato;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="OWNER_ID")
	private Person owner;
	
	//Constructors
	public Konto() {
	}

	public Konto(String navn, String saldo, Date dato, Person p) {
		
		setNavn(navn);
		setSaldo(saldo);
		setDato(dato);
		setOwner(p);
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

	public String getNavn() {
		return Navn;
	}

	public void setNavn(String navn) {
		Navn = navn;
	}

	public String getSaldo() {
		return Saldo;
	}

	public void setSaldo(String saldo) {
		Saldo = saldo;
	}

	public Date getDato() {
		return Dato;
	}

	public void setDato(Date dato) {
		Dato = dato;
	}
	
	
	
	
	
}
