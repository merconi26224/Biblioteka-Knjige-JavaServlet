package dzonitest;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Objects;

public class Knjiga implements Externalizable {
	
	private String naziv;
	private String opis;
	
	

	public Knjiga() {
		super();
	}

	public Knjiga(String naziv, String opis) {
		super();
		this.naziv = naziv;
		this.opis = opis;
	}
	
	

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}
	
	

	@Override
	public int hashCode() {
		return Objects.hash(naziv, opis);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Knjiga other = (Knjiga) obj;
		return Objects.equals(naziv, other.naziv) && Objects.equals(opis, other.opis);
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		// TODO Auto-generated method stub
		out.writeObject(this.getNaziv());
		out.writeObject(this.getOpis());
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		this.setNaziv((String) in.readObject());
		this.setOpis((String) in.readObject());
	}

}

