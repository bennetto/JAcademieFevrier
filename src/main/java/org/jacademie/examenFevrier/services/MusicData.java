package org.jacademie.examenFevrier.services;


public class MusicData {

	private Integer codeArtist;
	private String nomArtiste;
	private Integer codeAlbum;
	private String nomAlbum;
	private Integer numeroChanson;
	private String titreChanson;
	private Integer dureeChanson;
	
	public MusicData(){
	}
	
	public MusicData(Integer codeArtist, String nomArtiste, Integer codeAlbum,
			String nomAlbum, Integer numeroChanson, String titreChanson,
			Integer dureeChanson) {
		super();
		this.codeArtist = codeArtist;
		this.nomArtiste = nomArtiste;
		this.codeAlbum = codeAlbum;
		this.nomAlbum = nomAlbum;
		this.numeroChanson = numeroChanson;
		this.titreChanson = titreChanson;
		this.dureeChanson = dureeChanson;
	}

	public Integer getCodeArtist() {
		return codeArtist;
	}

	public void setCodeArtist(Integer codeArtist) {
		this.codeArtist = codeArtist;
	}

	public String getNomArtiste() {
		return nomArtiste;
	}

	public void setNomArtiste(String nomArtiste) {
		this.nomArtiste = nomArtiste;
	}

	public Integer getCodeAlbum() {
		return codeAlbum;
	}

	public void setCodeAlbum(Integer codeAlbum) {
		this.codeAlbum = codeAlbum;
	}

	public String getNomAlbum() {
		return nomAlbum;
	}

	public void setNomAlbum(String nomAlbum) {
		this.nomAlbum = nomAlbum;
	}

	public Integer getNumeroChanson() {
		return numeroChanson;
	}

	public void setNumeroChanson(Integer numeroChanson) {
		this.numeroChanson = numeroChanson;
	}

	public String getTitreChanson() {
		return titreChanson;
	}

	public void setTitreChanson(String titreChanson) {
		this.titreChanson = titreChanson;
	}

	public Integer getDureeChanson() {
		return dureeChanson;
	}

	public void setDureeChanson(Integer dureeChanson) {
		this.dureeChanson = dureeChanson;
	}

	

}
