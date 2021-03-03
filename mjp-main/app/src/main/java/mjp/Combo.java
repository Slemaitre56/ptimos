package mjp;

public class Combo {
	
	
	public final static int MAX_CARD = 5;
    private Carte cards[];
    
    
    //Constructeur qui donne 5 cartes par joueur
    public Combo() {
        cards = new Carte[MAX_CARD];
    }
    
    //retoune la main
    public Carte[] getCards() {
        return cards;
    }
    
    //Récupere la position de la carte
    public Carte getCardAtIndex(int index) {
        if (index >= 0 && index < MAX_CARD)
            return cards[index];
        else
            return null;
    }
    
   //Mofifie la position de la carte
    public void setCardAtIndex(Carte c, int index) {
        if(index >= 0 && index < MAX_CARD)
            cards[index] = c;
    }
    
    //Paire ou doubla paire 2 cartes identiques
    public int countPair() {
        int count = 0;
        for (int i = 0; i < cards.length; i++) {
            for (int j = i + 1; j < cards.length; j++) {   	
                if (cards[i].getValeur().equals(cards[j].getValeur())){           	
                    count++;
                }
            }
        }
        return count;
    }

    //Brelan 2 cartes identiques + 3 cartes identiques
    public int countBrelan() {
        int count = 0;
        for (int i = 0; i < cards.length; i++) {
            for (int j = i + 1; j < cards.length; j++) { 
                for(int k = j + 1; k<cards.length; k++)  {
                    if (cards[i].getValeur().equals(cards[j].getValeur()) && cards[i].getValeur().equals(cards[k].getValeur())){           	
                        count++;
                    }
                }	
            }
        }
        return count;
    }

    // Carré 4 cartes identiques
    public int countCarre() {
        int count = 0;
        for (int i = 0; i < cards.length; i++) {
            for (int j = i + 1; j < cards.length; j++) { 
                for(int k = j + 1; k<cards.length; k++)  {
                    for(int x = j + 1; x<cards.length; x++)  {
                        if (cards[i].getValeur().equals(cards[j].getValeur()) && cards[j].getValeur().equals(cards[k].getValeur()) && cards[k].getValeur().equals(cards[x].getValeur())){           	
                        count++;
                        }
                    }
                }	
            }
        }
        return count;
    }

    //Flush : suite de carte
    public boolean isFlush() {
        int count = 0;
        for (int i = 0; i < cards.length; i++) {
            for (int j = i + 1; j < cards.length; j++) {
                if (cards[i].getMotif().equals(cards[j].getMotif())) {
                    count++;
                }
            }
        }
        if(count == 5)
            return true;
        else
            return false;
    }    
}
