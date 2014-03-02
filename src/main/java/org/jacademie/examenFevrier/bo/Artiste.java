package org.jacademie.examenFevrier.bo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Artiste  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9121667835705743460L;
	private Integer codeArtiste;
	private String nom;
	private Set<Album> albums;
	
	public Artiste(){
		super();
		albums = new HashSet<Album>();
	}
	public Artiste(Integer codeArtist, String nom, Set<Album> albums) {
		super();
		this.codeArtiste = codeArtist;
		this.nom = nom;
		this.albums = (albums!=null) ? albums : new HashSet<Album>();
	}
	public Artiste(Integer codeArtist, String nom) {
		this(codeArtist,nom,null);
	}
	
	public Integer getCodeArtiste() {
		return codeArtiste;
	}
	
	public void setCodeArtiste(Integer codeArtiste) {
		this.codeArtiste = codeArtiste;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public Set<Album> getAlbums() {
		return albums;
	}
	
	public void setAlbums(Set<Album> chansons) {
		this.albums = chansons;
	}
	
	public void addAlbum(Album album){
		albums.add(album);
	}
	
	

	@Override
	public String toString() {
		return "Artiste [codeArtist=" + codeArtiste + ", nom=" + nom
				+ ", albums=" + albums + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((albums == null) ? 0 : albums.hashCode());
		result = prime * result
				+ ((codeArtiste == null) ? 0 : codeArtiste.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Artiste other = (Artiste) obj;

		if (albums == null  || albums.size() == 0) {
			if (other.albums != null && other.albums.size() != 0)
				return false;
		} else 
		if (!albums.equals(other.albums))
			return false;
		
		if (codeArtiste == null) {
			if (other.codeArtiste != null)
				return false;
		} else if (!codeArtiste.equals(other.codeArtiste))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}
	
}
