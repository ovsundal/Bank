package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Class for Account-entity
 * @author Ove
 *
 */
@Entity(name= "Card")
@Table(name = "Card")
public class Card implements Serializable {

	@Id
	@GeneratedValue
	private int id;
	@Column(name = "pin", length = 4)
	private String pin;
	
	
}
