package mjp;

abstract class Ptimos {


    //je déclare les attributs pour définir un Ptimo
    protected String nomPtimos;
    protected int pts_Stress;
    protected int pts_Dominance;
    protected int pts_Chance = 0;
    protected Joueurs perso;

    // Je crrée un construteur 
    public Ptimos(String nomPtimos) {
        this.nomPtimos = nomPtimos;
        perso = Joueurs.get_instance();
    }


    // Methodes astraites 
    abstract void rugir(LesBois bois);
    abstract void attaquer(LesBois bois);
    abstract void eloigner(LesBois bois);
    // void attaque_mag_commune(); ===>> création de l'attaque magique en interface
   
}
