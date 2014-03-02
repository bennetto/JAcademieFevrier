package org.jacademie.examenFevrier.bo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Album  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4732497392208294192L;
	private Integer codeAlbum;
	private String nom;
	private Set<Chanson> chansons;
	
	public Album()
	{
		super();
	}
	
	public Album(Integer codeAlbum, String nom, Set<Chanson> chansons) {
		super();
		this.codeAlbum = codeAlbum;
		this.nom = nom;
		this.chansons = (chansons != null)? chansons : new HashSet<Chanson>();
	}
	
	public Album(Integer codeAlbum, String nom) {
		this(codeAlbum, nom, null);
	}

	public Integer getCodeAlbum() {
		return codeAlbum;
	}
	
	public void setCodeAlbum(Integer codeAlbum) {
		this.codeAlbum = codeAlbum;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public Set<Chanson> getChansons() {
		return chansons;
	}
	
	public void setChansons(Set<Chanson> chansons) {
		this.chansons = chansons;
	}
	
	public void addChanson(Chanson chanson){
		chansons.add(chanson);
		
	}
	

	@Override
	public String toString() {
		return "Album [codeAlbum=" + codeAlbum + ", nom=" + nom + ", chansons="
				+ chansons + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass() )
			return false;
		Album other = (Album) obj;
		
		if (chansons == null || chansons.size() == 0) {
			if (other.chansons != null  && other.chansons.size() != 0)
				return false;
		}
		else if (!chansons.equals(other.chansons))
			return false;
		if (codeAlbum == null) {
			if (other.codeAlbum != null)
				return false;
		} else if (!codeAlbum.equals(other.codeAlbum))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}

}
