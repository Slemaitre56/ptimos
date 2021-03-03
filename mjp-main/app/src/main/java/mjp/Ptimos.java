package mjp;

import java.util.Random;

class Ptimos implements Magie_commune{


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


    void rugir(LesBois bois){
        /* 1/ pts dom en hause et stress en baisse
            2/ il rugit quand dom trop basse et stress trop haut
            3/ En rugissant si distance -3m il peut lancer attaque + souvent
            4/ En rugissant si distance + 10m il s'éloigne ex: 2m
         */
        if(this.pts_Dominance < 40 && this.pts_Stress > 70){
            this.pts_Stress -= 5;
            this.pts_Chance -= 5;
            this.pts_Dominance += 15;
            if(bois.getDistance() < 3){
                if(Math.random() <= 0.5){
                    attaquer(bois);
                }
            }else if(bois.getDistance() > 10) {
                bois.setDistance(bois.getDistance()+2);
                System.out.println(this.nomPtimos +" s'éloigner un peu !\n");
            }
        }
        if(this.nomPtimos == "Sacbleu") System.out.println(this.nomPtimos +" rugit ! Il semble prendre confiance en lui et pense de plus en plus à évoluer en Cabatrouge !\n");
        if(this.nomPtimos == "Pyralia") System.out.println(this.nomPtimos +" rugit ! Il semble prendre confiance en lui et vient d'acheter des parts chez Windows !\n");          
    }

    void attaquer(LesBois bois){
        /* 1/ + il est dominant + il attaque souvent
           2/ si dominance 0-30 =>peu frequent
           3/ si dominance 31-55 =>assez frequent
           4/ si dominance 56+ =>très frequent
           5/ lance + souvent attaque magique commune si distance trop petite et stress haut
        */
          
            if(this.pts_Dominance > 57) {
                if(Math.random() <= 0.7){
                    attaque_degats();
                }
            }else if (this.pts_Dominance > 30 && this.pts_Dominance < 56) {
                if(Math.random() <= 0.5){
                    attaque_degats();
                }
            }else if (this.pts_Dominance >= 0 && this.pts_Dominance < 32) {
                if(Math.random() <= 0.3){
                    attaque_degats();
                }
            }else if(this.pts_Stress > 70 && bois.getDistance() < 4){  
                this.pts_Chance += 15;
                attaque_mag_commune();
            }
      
    }

    void attaque_degats(){
        perso.setVie(perso.getVie()-7);
        if(this.nomPtimos == "Sacbleu") System.out.println(this.nomPtimos +"  vient de vous donner un coup de sac remplit de briques ! Vous perdez de la vie  !\n");
        if(this.nomPtimos == "Pyralia") System.out.println(this.nomPtimos +" vient de vous mordre à en perdre son dentier ! Vous perdez de la vie !\n");          
 
    }

    void eloigner(LesBois bois){ // Possible Refacto !!
   
        /* 1/ s'éloigne entre 8 à 15m*
           2/ si + 15m il s'enfuit*
           3/ si dominance 31-55 =>assez frequent*
           4/ + il est stressé + il s'éloigne*
           5/ si trop stressé il attaque aussi*
           6/ si stress 0-54 ( jamais )*
           7/ si stress 55-74 ( peu )*
           8/ si stress 75-84 ( assez )*
           9/ si stress 85+ ( jamais, augmente bcp dom, augmente chance attaque mag commune )*
        */
        Random random_Distance = new Random();
        int distance_Ptimos = (int) random_Distance.nextInt(15-8) + 8;
        
        if(this.pts_Dominance > 32 && this.pts_Dominance < 56){
            if(Math.random() <= 0.1){
               bois.setDistance(bois.getDistance() + distance_Ptimos); 
               if(this.nomPtimos == "Sacbleu") System.out.println(this.nomPtimos +" s'éloigne en moon walk de " + distance_Ptimos + "!\n"); 
               if(this.nomPtimos == "Pyralia") System.out.println(this.nomPtimos +" fait un salto arrière " + distance_Ptimos + " !\n"); 
            }
        }

        if(this.pts_Stress > 85){
            this.pts_Dominance +=5;
            this.pts_Chance += 25;
            attaque_mag_commune();
        }else if(this.pts_Stress > 74 && this.pts_Stress < 86){
            this.pts_Chance += 15;
            if(Math.random() <= 0.3){
                attaquer(bois);
            }
        }else if(this.pts_Stress > 54 && this.pts_Stress < 76){
            this.pts_Chance += 15;
            if(Math.random() <= 0.2){
                attaquer(bois);
            }
        }
        
    }

    public void attaque_mag_commune() {

        /* 1/ + la distance diminue + la chance attaque magique augmente
           2/ si dominance = 100 => attaque magique
           3/ aumente les dégats et augmente la dom
        */
        if(this.pts_Dominance > 99 || this.pts_Chance > 60){
            double coefficient = Math.random();
            int coup = (int) (this.pts_Dominance * coefficient);
            perso.setVie(perso.getVie()- coup);
            this.pts_Dominance -= 40;
            this.pts_Chance -= 20;
            if(this.nomPtimos == "Sacbleu") System.out.println(this.nomPtimos + " utilise sa magie et vous empoisonne avec des particules de plastiques !\n");
            if(this.nomPtimos == "Pyralia") System.out.println(this.nomPtimos + " utilise sa magie et vous lance une écaille dans le mollet !\n");
        }

    }


   
}
