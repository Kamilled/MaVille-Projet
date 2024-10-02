public class Resident {
    private String firstName:
    private String lastName;
    private String email;
    private String password;

    
    public Resident(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;

    }

    public String displayMenu(){
        System.out.println("Menu:");
        System.out.println("Rechercher des travaux");
        System.out.println("Consulter les travaux en cours ou à venir");
        System.out.println("Ajouter une plage horaire de travaux");
        System.out.println("Soumettre une requête de travail");
        System.out.println("Barre de recherche");
        System.out.println("Paramètre");
        System.out.println("Signaler un problème à la ville");
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
    
    public String getFirstName() {
        return this.firstName;
    }
    public String getLastName() {
        return this.lastName;
    }
    public String getEmail() {
        return this.email;
    }
    public String getPassword() {
        return this.password;
    }
    
    
}