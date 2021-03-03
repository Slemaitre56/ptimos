package mjp;


public class Sacbleu extends Ptimos {

    
    // Je crrée un construteur 
    public Sacbleu(String nomPtimos) {
        super(nomPtimos);
        this.pts_Stress = 70;
        this.pts_Dominance = 50;
    }
    
}