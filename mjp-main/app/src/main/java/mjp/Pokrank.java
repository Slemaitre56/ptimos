package mjp;

public class Pokrank extends Ptimos{

    private static Pokrank pok;

    // Je crée un construteur 
    public Pokrank(String nomPtimos) {
        super(nomPtimos);
        this.pts_Stress = 50;
        this.pts_Dominance = 50;
    }

    // Création d'un singleton
    public static Pokrank get_instance(){
        if(pok == null) pok = new Pokrank("Pokrank");
        return pok;
    }    


    public void attaque_mag_commune() {
        double coefficient = Math.random();
        int coup = (int) (20 * coefficient);
        perso.setVie(perso.getVie()- coup);
        System.out.println(this.nomPtimos + " utilise sa magie et vous donne des ampoules aux pieds !");
    }


    public void attaque_magique_cartes(Ptimos mos, LesBois bois){
        // Creer un jeu de carte style poker 
        // si tatata alors tototo
        Partie game = new Partie();
        game.distribuer();
        game.afficher_resultat(mos,bois); 
        
    }

    // Attaque si full alors je libére les Ptimos
    public void attaque_Full(){
        System.out.println("Pokrank vous assomme et libére tous vos Pitmos..... NyaK NYAKKK");
        perso.getCage_Ptimos().clear();
    }
    
}
