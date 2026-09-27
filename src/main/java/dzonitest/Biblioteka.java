package dzonitest;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Objects;

public class Biblioteka implements Externalizable {
	
	private String naziv;
	private String adresa;
	private String mesto;
	private int zip;
	private String pib;
	
	

	public Biblioteka() {
		super();
	}
	
	

	public Biblioteka(String naziv, String adresa, String mesto, int zip, String pib) {
		super();
		this.naziv = naziv;
		this.adresa = adresa;
		this.mesto = mesto;
		this.zip = zip;
		this.pib = pib;
	}

	


	public String getNaziv() {
		return naziv;
	}



	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}



	public String getAdresa() {
		return adresa;
	}



	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}



	public String getMesto() {
		return mesto;
	}



	public void setMesto(String mesto) {
		this.mesto = mesto;
	}



	public int getZip() {
		return zip;
	}



	public void setZip(int zip) {
		this.zip = zip;
	}



	public String getPib() {
		return pib;
	}



	public void setPib(String pib) {
		this.pib = pib;
	}


	

	@Override
	public int hashCode() {
		return Objects.hash(adresa, mesto, naziv, pib, zip);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Biblioteka other = (Biblioteka) obj;
		return Objects.equals(adresa, other.adresa) && Objects.equals(mesto, other.mesto)
				&& Objects.equals(naziv, other.naziv) && Objects.equals(pib, other.pib) && zip == other.zip;
	}



	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		// TODO Auto-generated method stub
		out.writeObject(this.getNaziv());
		out.writeObject(this.getAdresa());
		out.writeObject(this.getMesto());
		out.writeInt(this.getZip());
		out.writeObject(this.getPib());
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		this.setNaziv((String) in.readObject());
		this.setAdresa((String) in.readObject());
		this.setMesto((String) in.readObject());
		this.setZip(in.readInt());
		this.setPib((String) in.readObject());
	}

}

