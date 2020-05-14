package it.polito.tdp.borders.model;

public class Country implements Comparable<Country> {
	
	
	int codice;
	String nome; 
	String abbreviazione;
	int contatore; 

	
	public Country(int codice, String nome, String abbreviazione) {
		
		this.codice = codice;
		this.nome = nome;
		this.abbreviazione = abbreviazione;
	}
	
	
	public int getCodice() {
		return codice;
	}
	public void setCodice(int codice) {
		this.codice = codice;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getAbbreviazione() {
		return abbreviazione;
	}
	public void setAbbreviazione(String abbreviazione) {
		this.abbreviazione = abbreviazione;
	}
	
	public void setContatore(int i) {
		this.contatore=i;
	}
	public int getContatore() {
		return contatore;
	}
	


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codice;
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
		Country other = (Country) obj;
		if (codice != other.codice)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return codice + "   " + nome + "   " + abbreviazione + "  " +contatore;
	}


	@Override
	public int compareTo(Country o) {
		
		return this.codice-o.getCodice();
	} 
	
	
	

}
