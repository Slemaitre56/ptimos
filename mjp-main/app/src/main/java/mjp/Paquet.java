package mjp;

import java.util.Random;

public class Paquet {
	
	
	 private final String valeurs[] = {"As","2","3","4","5","6","7","8","9","10","V","Q","K"};
	 private final String motifs[]={"H","D","C","S"};
	 
	private Carte paquet[];
	private final int TOTAL_CARDS=52;
	private Random randNum;    
	    
	    
	//no-argument constructor fills the deck of cards
	public Paquet(){        
	    paquet = new Carte[TOTAL_CARDS];
	    randNum = new Random();
	    for(int i=0;i<paquet.length;i++) paquet[i] = new Carte(valeurs[i%13],motifs[i/13]);
	}
	    
	//shuffles the deck
	public void shuffle(){
	    for(int i = 0; i<paquet.length; i++){
	        int j = randNum.nextInt(TOTAL_CARDS);	           	            	
	        Carte c = paquet[i];	            
	        paquet[i] = paquet[j];
	        paquet[j] = c;
	    }
	}        

	//returns the individual card in the deck
	public Carte getCard(int index){
	    return paquet[index];
	}
}
