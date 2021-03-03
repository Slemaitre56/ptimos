package mjp;

public class Partie {

    private Combo[] players;
    private Paquet deck;
    private Joueurs perso;
    private Pokrank pok;

    //Constructeur pour créerun nouveau paquet de carte qui sera mélangé
    public Partie() {
        perso = Joueurs.get_instance();
        pok = Pokrank.get_instance();
        deck = new Paquet();
        players = new Combo[1];
        players[0] = new Combo();
        deck.shuffle();
    }

    //Distribue 5 cartes aux joueurs
    public void distribuer() {
        int count = 0;
        for (int i = 0; i < players[0].getCards().length; i++) {
            for (int j = 0; j < players.length; j++) {
                players[j].setCardAtIndex(deck.getCard(count++), i);
            }
        }
    }

    //Résultats affichés
    public void afficher_resultat(Ptimos mos, LesBois bois) {
        for (int i = 0; i < players.length; i++) {
            System.out.print("Pokrank fait apparaître 5 cartes : ");
            // afficher mes 5 cartes
            for (int j = 0; j < players[0].getCards().length; j++) {
                System.out.print("{" + players[i].getCardAtIndex(j).toString()+"}");
            }
            // affiche les combos

            if(players[i].countBrelan()== 1 && players[i].countPair() == 1){
              System.out.print("\nFULL ! Et vous donne un coup de boule !");
              pok.attaque_Full();
            } 

            if(players[i].countCarre()== 1){
                System.out.print("\nCARRE ! Et vous donne un coup de boule !");
                pok.attaque_Full();
            } 

            if(players[i].isFlush()){
              System.out.print("FLUSH!! Il s'éloigne et s'éloigne ...\n");  
              bois.setDistance(bois.getDistance() + 15);
            } 

            if(players[i].countBrelan() == 1){
               System.out.print("\nBRELAN ! Il s'éloigne et s'éloigne ...\n"); 
               bois.setDistance(bois.getDistance() + 15);
            } 

            if(players[i].countPair() == 2){
              System.out.print("\nDOUBLE PAIR ! Il semble se concentrer et ...\n");  
              ((Magie_commune) mos).attaque_mag_commune(); 
            }  

            if(players[i].countPair() == 1){
                System.out.print("\nPAIR ! Il semble se concentrer et ...\n");
                ((Magie_commune) mos).attaque_mag_commune();
            } 
            
            if(players[i].countPair() < 0 && players[i].countBrelan() < 0 && players[i].isFlush() == false && players[i].countCarre()< 0){
                System.out.print("\n Pokrank a raté son attaque magique ! \n");
                pok.pts_Dominance -= 10;
            } 
            

        }
    }



}
