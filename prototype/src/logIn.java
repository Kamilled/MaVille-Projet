import java.util.Scanner;

public class logIn {
    public static void process(String[] args){

        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenue, souhaité vous vous connecter en tant que résident ou intervenant?");
        String nom = scanner.nextLine();

        String username = "";
        while (username.isEmpty()) {
            System.out.println("Entrer un nom d'utilisateur valide");
            username = scanner.nextLine();
        }

        String password = "";
        while (password.isEmpty()) {
            System.out.println("Entrer un mot de passe valide");
            password = scanner.nextLine();
        }

        System.out.println("Bienvenue, vous êtes maintenant connecté(e)");

        // il reste a faire apparaitre les elements du menu
        System.exit(0);
    }
}
