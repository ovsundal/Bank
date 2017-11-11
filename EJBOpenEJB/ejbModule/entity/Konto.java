package entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Class for Konto-entity
 * @author Ove
 *
 */
@Entity
@Table(name = "Konto")
public class Konto implements Serializable {
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	@Column(name = "Navn", length = 30)
	private String Navn;
	@Column(name = "Saldo", length = 30)
	private String Saldo;
	@Column(name = "Dato", length = 30)
	private String Dato;
	
	//Constructors
	public Konto() {
	}
	
	public Konto(int id, String navn, String saldo, String dato) {
		setId(id);
		setNavn(navn);
		setSaldo(saldo);
		setDato(dato);
	}
	
	public Konto(String navn, String saldo, String dato) {
		setNavn(navn);
		setSaldo(saldo);
		setDato(dato);
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

	public String getDato() {
		return Dato;
	}

	public void setDato(String dato) {
		Dato = dato;
	}
	
	
	
	
	
}
