import java.util.Scanner;

public class logIn {
    public static void process(String[] args){

        Scanner scanner = new Scanner(System.in);

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

        String type = "";
        System.out.println("Souhaiteriez-vous vous connecter en tant que résident ou intervenant?");
        while (type.isEmpty() || (!type.equals("R") && !type.equals("I"))) { 
            System.out.println("Tapez R pour résident et I pour intervenant");
            type = scanner.nextLine();
        }    

        if (type.equals("R")) {
            user = new Resident(firstName, lastName, email, password);
        } else {
            user = new Intervenant(firstName, lastName, email, password);
        }
        
        System.out.println("Bienvenue, vous êtes maintenant connecté(e)");

        user.displayMenu();

        System.exit(0);
    }
}
