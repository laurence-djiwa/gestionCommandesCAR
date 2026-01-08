package entity;

import jakarta.annotation.Generated;
import jakarta.annotation.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Auteur {

	@Id @Generated
	private long id;
	private String nom;
	private String prenom;
}
