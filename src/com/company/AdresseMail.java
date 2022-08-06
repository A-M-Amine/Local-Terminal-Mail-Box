package com.company;
import java.util.Objects;
import java.util.Scanner;

public class AdresseMail { // zdt fiha méthode affiche

    // attribut de adresse
    protected String pseudo;
    protected String site;
    private Profil P = new Profil();
    protected String motDePasse;

   //constructeur avec param
    public AdresseMail(String pseudo, String site, Profil p, String motDePasse) {
        this.pseudo = pseudo;
        this.site = site;
        P = p;
        this.motDePasse = motDePasse;
    }
    //constructeur par defaut

    public AdresseMail() {}

    //les accesseurs

    public Profil getP() {
        return P;
    }

    public void setP(Profil p) {
        P = p;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    // La méthode Saisir
     public void saisir() {
        Scanner scan = new Scanner(System.in);

        String user;
        System.out.print("Nom D'utilistaeur : ");
        user = scan.next();
        while (!verifNom(user)) {
            System.out.println("Nom D'utilisateur non valide !");
            System.out.print("Nom D'utilistaeur : ");
            user = scan.next();
        }
        pseudo = user;
        String nomSite;
        System.out.println("Nom de site ");
        System.out.print(user + "@");
        nomSite = scan.next();
        while (!verifNomSite(nomSite)) {
            System.out.println("Nom de site non Valide !");
            System.out.println("Nom de site ");
            System.out.print(user + "@");
            nomSite = scan.next();
        }
        site = nomSite;
        String password;
        System.out.print("Mot De Passe : ");
        password = scan.next();
        while (!verifMotDePasse(password)) {
            System.out.println("Mot De Passe non valide !");
            System.out.print("Mot De Passe : ");
            password = scan.next();
        }

        motDePasse = password;

        P = Profil.saisir();

    }

    static boolean verifNom(String S) {     // methode de vérification de syntaxe du Nom D'utilisateur

        if (Character.isLetter(S.charAt(0))) {

            if (S.matches("[0-9a-zA-Z._-]+"))   return true;
            else return false;

        }else return false;

    }

    static boolean verifNomSite(String S) { //    methode de vérification de syntaxe du Nom de site
        int cpt = 0;
        int i = 0;
        while (i < S.length() && cpt <= 1) {
            if (S.charAt(i) == '.') cpt++;

            i++;
        }
        if (cpt > 1) return false;
        if (S.charAt(0) == '.' || S.charAt(S.length()-1) == '.') return false;
        if (S.contains(".") && S.matches("[0-9a-zA-Z.]+")) return true;
        else  return false;

    }

    static boolean verifMotDePasse(String S) { //    methode de vérification de syntaxe du mot de passe
        int c1 = 0,c2 = 0,c3 = 0;
        if (S.length()< 8) return false;
        for (int i = 0; i < S.length(); i++) {
            if (Character.isLetter(S.charAt(i))) c1++;
            else if (Character.isDigit(S.charAt(i))) c2++;
            else c3++;
        }
        if (c1 == 0 || c2 == 0 || c3 == 0) return false;

        return true;
    }

    // * * * * * * *  les méthodes de la modification

    public void modifierMotDePasse() {

        Scanner scan = new Scanner(System.in);

        String mdp;
        System.out.print("Saisir l'ancien mot de passe : ");
        mdp = scan.next();
        while (!motDePasse.equals(mdp)) {
            System.out.println("Mot De Passe incorrect !");
            System.out.print("Saisir l'ancien mot de passe : ");
            mdp = scan.next();
        }
        System.out.print("Saisir le nouveau mot de passe : ");
        motDePasse = scan.next();

        while (!verifMotDePasse(motDePasse)) {
            System.out.println("Mot De Passe non valide ! ");
            System.out.print("Saisir le nouveau mot de passe : ");
            motDePasse = scan.next();
        }

        System.out.print("Confirmer le nouveau mot de passe : ");
        String nvmdp = scan.next();

        while (!motDePasse.equals(nvmdp)) {
            System.out.println("Les mots de passe ne correspondant pas !!");
            System.out.print("Confirmer le nouveau mot de passe : ");
            nvmdp = scan.next();
        }


    }
    public  void modfierPseudo( )
    {   String NewNom;
        Scanner scan = new Scanner(System.in);

        System.out.println("Saisir le nouveau Nom :");

        NewNom = scan.next();
        while (!verifNom(NewNom))
        {
            System.out.println("Nom D'utilisateur non valide !");
            System.out.print("Nom D'utilistaeur : ");
             NewNom = scan.next();
        }
        pseudo = NewNom;

    }

    // * * * * * * * * *


    public String getadr() {
        return pseudo+"@"+site;
    } // méthode pour récuperer une adresse unique

    @Override
    public String toString() {

        return  pseudo + "@" + site + "\\ Mot de Passe : " + motDePasse + "\\ Profil "+ P.toString();
    }

    public  void afficher()
    {
        System.out.println(this);
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof AdresseMail)) return false;
        AdresseMail that = (AdresseMail) o;


        return Objects.equals(pseudo, that.pseudo) &&
                Objects.equals(site, that.site) &&
                Objects.equals(P, that.P) &&
                Objects.equals(motDePasse, that.motDePasse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pseudo, site, P, motDePasse);
    }


}
