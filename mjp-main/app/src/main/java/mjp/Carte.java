package mjp;

public class Carte {

    private String valeur;
    private String motif;
    
    
	public Carte(String valeur, String motif) {
		super();
		this.valeur = valeur;
		this.motif = motif;
	}


	public String getValeur() {
		return valeur;
	}


	public void setValeur(String valeur) {
		this.valeur = valeur;
	}


	public String getMotif() {
		return motif;
	}


	public void setMotif(String motif) {
		this.motif = motif;
	}
    
    public String toString() {
        return valeur + "/" + motif;
    }
}
