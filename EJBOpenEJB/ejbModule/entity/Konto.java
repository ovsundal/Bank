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
	@Column(name = "Navn", length = 30)
	private String Navn;
	@Column(name = "Saldo",length = 20)
	private String Saldo;
	@Column(name = "Dato")
	private Date Dato_opprettet;
	
	//add cascade , cascade = CascadeType.ALL
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="OWNER_ID")
	private Person owner;
	
	public Konto() {}

	public Konto(Person p, String navn, String saldo, Date dato) {
		
		setOwner(p);
		setNavn(navn);
		setSaldo(saldo);
		setDato_opprettet(dato);
	}
	
	
	
	@Override
	public String toString() {
		return "Konto [id=" + id + ", Navn=" + Navn + ", Saldo=" + Saldo + ", Dato_opprettet=" + Dato_opprettet
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

	public Date getDato_opprettet() {
		return Dato_opprettet;
	}

	public void setDato_opprettet(Date dato_opprettet) {
		Dato_opprettet = dato_opprettet;
	}
	
	


	
	
	
	
	
}
