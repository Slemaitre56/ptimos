package mjp;

import java.util.*;

public class Joueurs { //Singleton (permet d'utiliser la Class joueur alias juliette partout tant que l'instancie dans une autres Class)

    //je déclare les attributs pour définir un joueur
    private static Joueurs perso;
    private String nom;
    private int vie;
    private int nbrFriandise;
    private int flechette;

    
    //Je crée un tableau qui sera de 10 max où sera add() le nom des Ptimos
    private ArrayList<String> cage_Ptimos;

    //Je déclare un couteur pour connaitre le nombre de Ptimos
    private int nbr_Ptimos_capturer;
    
    // Création d'un singleton
    public static Joueurs get_instance(){
        if(perso == null){
            perso = new Joueurs("Juliette");
        }
        return perso;
    }

    // Je crrée un construteur 
    private Joueurs(String nom) {
        this.nom = "Juliette";
        this.vie = 100;
        this.cage_Ptimos = new ArrayList<String>();
        this.nbrFriandise = 30;
        this.flechette = 1;
        this.nbr_Ptimos_capturer = cage_Ptimos.size();
    }

    // Je crée mes getteurs et setteurs car attibuts private

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getVie() {
        return this.vie;
    }

    public void setVie(int vie) {
        this.vie = Math.max(vie, 0);
    }

    public int getNbrFriandise() {
        return this.nbrFriandise;
    }

    public void setNbrFriandise(int nbrFriandise) {
        this.nbrFriandise = nbrFriandise;
    }

    public int getFlechette() {
        return this.flechette;
    }

    public void setFlechette(int flechette) {
        this.flechette = flechette;
    }

    public ArrayList<String> getCage_Ptimos() {
        return this.cage_Ptimos;
    }

    public void setCage_Ptimos(ArrayList<String> cage_Ptimos) {
        this.cage_Ptimos = cage_Ptimos;
    }

    public int getNbr_Ptimos_capturer() {
        return this.nbr_Ptimos_capturer;
    }

    public void setNbr_Ptimos_capturer(int nbr_Ptimos_capturer) {
        this.nbr_Ptimos_capturer = nbr_Ptimos_capturer;
    }



    //Je crée les methodes des joueurs

    public String observer(Ptimos mos) {
        /* 
           1/ Juju peut oberverle Ptimo et voir so état
           2/ le jeux recommence du début
        */
        String nv_Stress;
        String nv_Dominance;

        if(mos.pts_Stress > 74) nv_Stress ="paniqué";          
        else if(mos.pts_Stress < 76 && mos.pts_Stress > 50) nv_Stress ="nerveux";    
        else if(mos.pts_Stress < 51 && mos.pts_Stress > 25) nv_Stress = "méfiant";
        else nv_Stress = "détendu"; 
 
        if(mos.pts_Dominance > 74) nv_Dominance ="très dangereux";
        else if(mos.pts_Dominance < 76 && mos.pts_Dominance > 50) nv_Dominance ="très féroce";    
        else if(mos.pts_Dominance < 51 && mos.pts_Dominance > 24) nv_Dominance = "plutôt neutre";
        else nv_Dominance = "plutôt inoffensif"; 

        System.out.println(mos.nomPtimos + " semble assez " + nv_Stress + " et " + nv_Dominance + ".\n");
        return "";
    }

    public void se_Rappocher(LesBois bois){
        /* 
           1/ entre 3 à 8m
           2/ si valeur se_Rapprocher >= distance( juju <-> ptimos) ==> capture le ptimos
        */
        Random random_De_Rapprochement = new Random();
        int distance_Joueur = (int) random_De_Rapprochement.nextInt(8-3) + 3;
        bois.setDistance(bois.getDistance() - distance_Joueur);
        System.out.println("Vous vous rapprochez de "+ distance_Joueur +" métres à vos risques et périls !\n");
        
    }

    public void lance_Friandise(Ptimos mos){
        /* 
           1/ entre 10% 70% de chance que la friandise touche le ptimos
           2/ + juju proche + de chance de toucher
           3/ si touche, ptimo mange et - stress
           4/ 30 max friandises
        */
        if(Math.random() >= 0.1 || Math.random() <= 0.7){
            mos.pts_Stress -= 5;
            this.nbrFriandise -= 1;
           System.out.println(mos.nomPtimos+" a gobé la friandise et semble un peu plus calme !\n"); 
        }else{
            mos.pts_Stress += 5;
            this.nbrFriandise -= 1;
            System.out.println(mos.nomPtimos+" a reçu la friandise en pleine figure ! Il semble perdre son calme !\n"); 
        }
        
    }

    public void danse_Impressionnate(Ptimos mos){
        /* 
           1/ Entre 7-21 pts de dominance en moins
        */
        Random random_Degats_Danse = new Random();
        int degats_Danse = random_Degats_Danse.nextInt(21-7) + 7;
        mos.pts_Dominance -= degats_Danse;
        System.out.println(mos.nomPtimos + " semble imprésionné et beaucoup moins féroce que précédement...\n");
    }

    public void flechette_Endormante(Ptimos mos, LesBois bois){
        /* 
           1/ 50% chance 
           2/ si touche => capture du ptimo
           3/ si raté => plus de flechette
        */
        if(this.flechette == 1){
            if(Math.random() >= 0.5){
                System.out.println(" Vous avez touché votre cible ! " + mos.nomPtimos + " est capturé !\n");
                cage_Ptimos.add(mos.nomPtimos);
                this.flechette = 0;
                bois.etapeOne_game(bois); 
            }else{
                System.out.println("Vous avez raté votre cible ! " + mos.nomPtimos + " n'est pas content !\n");
                mos.pts_Stress += 10;
                mos.pts_Dominance += 10;
                this.flechette = 0; 
            }
        }else{
            System.out.println(" Vous n'avez plus de fléchette ! Tu l'as dans le dada !\n");
        }

    }

    public void laissePartir(Ptimos mos){
        /* 
           1/ Juju peut laisser partir le Ptimo
           2/ le jeux recommence dans les bois
        */
        System.out.println("Vous avez laisser filer "+ mos.nomPtimos + " !\n");
    }

}
