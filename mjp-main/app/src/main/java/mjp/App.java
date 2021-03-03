package mjp;
import java.util.Scanner;

public class App {

    // Intro du jeu
    public static void intro_game() {
        Scanner scan = new Scanner(System.in);
        System.out.println("VrrrVrrrr VrrrVrrr VrrrVrrr VrrrVrrr ...");
        System.out.println("Vous vous réveillez en entendant votre PtimosPhone vibrer !");
        System.out.println("Vous attrapez votre PtimosPhone et découvrez que c'est votre patron Sensei Pizza qui vous appelle !");
        System.out.println("\"Ha..Hallo ?! Sensei Pizza ?\"");
        System.out.println("\"Enfin ! J'ai besoin de toi !!! Le bois est infesté de Ptimos sauvages! Je ne peux plus livrer mes pizzas !!\"");
        System.out.println("\"Quelle horreur !! Nous devons faire quelque chose !!\"");
        System.out.println("\"Exactement ! Tu dois ... euh... c'est quoi ton nom déjà ..?\"");
        System.out.println("Ecrire votre nom : ");
        String name = scan.nextLine();
        System.out.println("\"*Soupir*... Mon nom est " + name + " !\"");
        System.out.println("\"Juliette, tu dois tous les attrapper !\"");
        System.out.println("\"Non... mais moi c'est " + name + "... bippp biiippp \"");
        System.out.println("Après avoir raccrocher, vous attrapez vote sac à dos pour vérifier ce dont vous avez besoin !");
        System.out.println("Inventaire : 30 friandises pour amadouer les Ptimos, 10 capsules pour les enfermer, et une seule et unique flechette...");
        System.out.println("C'est parti !!");
        
    }


    public static void main(String[] args) {
        
        // J'instencie mes bois pour labcer le jeu
        LesBois bois = new LesBois();

        // Le jeu commence
        intro_game();
        bois.etapeOne_game(bois);

    }
}
