public class Intervenant extends User {
    public Intervenant(String lastName, String firstName, String email, String password) {
        super(lastName, FirstName, email, password);
    }

    @Override
    public String displayMenu(){
        System.out.println("Menu:");
        System.out.println("Consulter la liste des requêtes de travail");
        System.out.println("Soumettre un nouveau projet de travaux");
        System.out.println("Mise à jour des informations d'un chantier");
        System.out.println("Barre de recherche");
        System.out.println("Paramètre");
        System.out.println("Signaler un problème à la ville");
    
    }
}