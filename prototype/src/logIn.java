import java.util.Scanner;

public class logIn {
    public static void process(String[] args){

        Scanner scanner = new Scanner(System.in);

        // Connection en tant que résident ou intervenant

        String type = "";
        System.out.println("Souhaiteriez-vous vous connecter en tant que résident ou intervenant?");
        while (type.isEmpty() || (!(type.equals("R")||type.equals("r")) && !(type.equals("I")|| type.equals("i")))) {
            System.out.println("Tapez R pour résident et I pour intervenant");
            type = scanner.nextLine();
        }

        // Demande de l'adresse courriel pour la connection 

        String email = "";
        while (email.isEmpty()) {
            System.out.println("Entrer un adresse courriel valide");
            email = scanner.nextLine();
        }

        // Demande du mot de passe pour la connection

        String password = "";
        while (password.isEmpty()) {
            System.out.println("Entrer un mot de passe valide");
            password = scanner.nextLine();
        }

        // Création de l'utilisateur

        Resident user;

        // le nom est précisé à l'inscription donc pour le moment le nom par défault est John Doe

        if (type.equals("R")) {
            user = new Resident("Doe", "John", email, password);     
        } else {
            user = new Intervenant("Doe", "John", email, password);
        }

        // Affichage du message de bienvenue/ menu
        
        System.out.println("Bienvenue " + user.getFirstName() + ", vous êtes maintenant connecté(e)!");

        user.displayMenu();

    }
}

