package dzonitest;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.Objects;

public class ListaKnjiga implements Externalizable {
	
	private ArrayList<Knjiga> listaKnjiga;
	
	public static boolean listInitialized = false;
	
	public ListaKnjiga() {
		listaKnjiga = new ArrayList<Knjiga>();	
	}

	public ListaKnjiga(ArrayList<Knjiga> listaKnjiga) {
		super();
		listaKnjiga = new ArrayList<Knjiga>();	
	}
	
	
	public ArrayList<Knjiga> getListaKnjiga() {
		return listaKnjiga;
	}

	public void setListaKnjiga(ArrayList<Knjiga> listaKnjiga) {
		this.listaKnjiga = listaKnjiga;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(listaKnjiga);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ListaKnjiga other = (ListaKnjiga) obj;
		return Objects.equals(listaKnjiga, other.listaKnjiga);
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		// TODO Auto-generated method stub
		out.writeObject(this.getListaKnjiga());
	}

	@SuppressWarnings("unchecked")
	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		this.setListaKnjiga((ArrayList<Knjiga>) in.readObject());
	}

}
