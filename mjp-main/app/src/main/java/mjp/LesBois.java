package mjp;

import java.util.Random;
import java.util.Scanner;

public class LesBois {
    
    // Je déclare les attributs pour définir un joueur
    private int distance;
    private Joueurs perso;
    private Pokrank pok;


    // Je crée un construteur et utilise mes singletons
    public LesBois() {
        this.distance = 0;
        perso = Joueurs.get_instance();
        pok = Pokrank.get_instance();
    }


    // Je crée mes getteurs et setteurs car attibuts private
    public int getDistance() {
        return this.distance;
    }

    public void setDistance(int distance) {  
        this.distance = Math.max(distance, 0); // Pour que la distance ne soit pas négative
    }


    // Je crée une méthode pour créer mes Ptimos ( faire une factory plus tard)
    public void etapeOne_game(LesBois bois){
        if(perso.getCage_Ptimos().size() < 11){
            if(perso.getCage_Ptimos().size() > 1){
                Ptimos pok = new Pokrank("Pokrank");
                etapeTwo_game(pok, bois);
            }else{
                if(Math.random() > 0.5){
                Ptimos sac = new Sacbleu("SacBleu");
                etapeTwo_game(sac, bois);
                }else{
                Ptimos pyr = new Pyralia("Pyralia");
                etapeTwo_game(pyr, bois);   
                } 
            }    
        }else{
            System.out.println("Vous n'avez plus de place dans votre sac ! Dommage !");         
        }
    }


    // Je crée une méthode pour démarrer le jeu en choississant de capturer ou non un ptimo
    public void etapeTwo_game(Ptimos mos, LesBois bois) {
        System.out.println("Une fois dans le bois, et vous y voyez un " + mos.nomPtimos + " ! Voulez-vous le capturer ?");
        System.out.println("[O] - Oui");
        System.out.println("[N] - Non\n");
        System.out.println("[Q] - Quitter le jeu");

        Scanner scan = new Scanner(System.in);
        switch (scan.nextLine()) {
            case "Q":
                System.out.println("Ok bon bah à la prochaine !");
                boolean exit = false;
                scan.close();
                break;
            case "O":
                System.out.println("----------------------------------------------------------------\n");
                etapeThree_game(mos, bois);
                break;

            case "N":
                System.out.println("Vous n'attrappez pas ce Ptimo et vous continuez votre chemin.");
                System.out.println("----------------------------------------------------------------\n");
                etapeOne_game(bois);
                break;

            default:
                System.out.println("Je n'ai pas compris !");
                etapeTwo_game(mos, bois);
                break;
        }
    }

    // Je crée une méthode qui donnera les différents choix pour le joueur pour se rapprocher et capturer un ptimo
    public void etapeThree_game(Ptimos mos, LesBois bois) {

        Random random_Distance = new Random();
        this.distance = random_Distance.nextInt(15-8) + 8;
        Scanner scanChoix = new Scanner(System.in);
        boolean exit = true;
        
        while(exit){
            if(Joueurs.get_instance().getVie() > 0){
                 if(this.distance == 0){
                    perso.getCage_Ptimos().add(mos.nomPtimos);
                    System.out.println("Vous avez attapé "+ mos.nomPtimos+" !\n"); 
                    exit = false;
                    etapeOne_game(bois);
                }else if(this.distance > 15) {
                    System.out.println("Le " + mos.nomPtimos + " s'est échappé !\n");      
                    exit = false;
                    etapeOne_game(bois);
                }else if(this.distance < 15 && this.distance != 0){
                    //Affiche les choix du joueur pour capturer le ptimo
                    etapeThree_SysoChoice(mos);
                    //Affiche le switch
                    etapeThree_Switch(mos,bois);
                }                             
            }else{
                exit = false;
                System.out.println("Game Over ! Tu n'as plus de vie et tout les Ptimos se sont enfuis !");
                scanChoix.close();
            }
        System.out.println("----------------------------------------------------------------\n");               
        }       
    }

    public void etapeThree_Switch(Ptimos mos, LesBois bois){
        Scanner scanChoix = new Scanner(System.in); 
        int joueurInput = scanChoix.nextInt();
        boolean exit = true;       
        switch (joueurInput) {
            case 0:
                perso.laissePartir(mos);
                exit = false;
                etapeOne_game(bois);
                break;
            case 1:
                perso.observer(mos);
                attaque_Combo(mos,bois);
                break;
            case 2:
                perso.se_Rappocher(bois);
                attaque_Combo(mos,bois);
                break;
            case 3:
                perso.lance_Friandise(mos);
                attaque_Combo(mos,bois);
                break;
            case 4:
                perso.danse_Impressionnate(mos);
                attaque_Combo(mos,bois);
                break;
            case 5:
                perso.flechette_Endormante(mos, bois);
                attaque_Combo(mos,bois);
                break;
            default:
                System.err.println("Je n'ai pas compris !");
                break;
        }
    }

    public void attaque_Combo(Ptimos mos, LesBois bois){

        if(mos.nomPtimos == "Pokrank") pok.attaque_magique_cartes(mos,bois);
        else{
            mos.rugir(bois);
            mos.attaquer(bois);
            mos.eloigner(bois);
            ((Magie_commune) mos).attaque_mag_commune(); 
        }
    }

    public void etapeThree_SysoChoice(Ptimos mos){
        System.out.println(perso.getNom() + "(" + perso.getVie() + "pv) a capturé " + perso.getCage_Ptimos().size()+" Ptimo(s).\n");
        System.out.println("Vous êtes à " + this.distance + " d'un "+ mos.nomPtimos +", que voulez-vous faire ?\n");
        System.out.println("[1] - Observer");
        System.out.println("[2] - Se Rapprocher");
        System.out.println("[3] - Lancer une friandise (x"+ perso.getNbrFriandise()+")");
        System.out.println("[4] - faire une danse impressionnate");
        System.out.println("[5] - Tirer une flechette endormante (x"+ perso.getFlechette()+")\n");
        System.out.println("[0] - Laisser le "+ mos.nomPtimos+" en liberté");
    }

}
