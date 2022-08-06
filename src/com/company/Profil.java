package com.company;


import java.util.Objects;
import java.util.Scanner;

public class Profil {



    public enum Genre {Feminin, Masculin;}
    //constructeur par défaut
    public Profil()
    {

    }

    private String nom;
    private String prenom;
    Genre genre;
    private int age;
    private String numTEL;
    private String paysR;
    Scanner scan = new Scanner(System.in);

    // les accesseurs

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    void setGenre(int x) {
        if (x == 1) genre = Genre.Feminin;
        else genre = Genre.Masculin;
    }



    public Profil(String nom, String prenom, Genre genre, int age, String numTEL, String paysR) {
        this.nom = nom;
        this.prenom = prenom;
        this.genre = genre;
        this.age = age;
        this.numTEL = numTEL;
        this.paysR = paysR;
    }



    public static Profil saisir() {

        Scanner scan = new Scanner(System.in);


        String name;
        System.out.print("Nom : ");
        name = scan.next();
        while (!verifStringLettreUni(name)) {
            System.out.println("Nom non valide ! ");
            System.out.print("Nom : ");
            name = scan.next();
        }
        String nickName;
        System.out.print("Prenom : ");
        nickName = scan.next();
        while (!verifStringLettreUni(nickName)) {
            System.out.println("Prenom non valide");
            System.out.print("Prenom : ");
            nickName = scan.next();
        }
        int choix;
        System.out.println("Genre: ");
        System.out.println("1 - Feminin");
        System.out.println("2 - Masculin");
        System.out.print("Votre choix : ");
        choix = scan.nextInt();
        Genre g;
        while (choix != 1 && choix != 2) {
            System.out.println("Choix non valide ! ");
            System.out.print("Votre choix : ");
            choix = scan.nextInt();

        }
        if (choix == 1) g = Genre.Feminin;
        else g = Genre.Masculin;

        int age1;
        System.out.print("Age : ");
        age1 = scan.nextInt();
        while (age1 < 6 || age1 > 140) {
            System.out.println("Age non valide !");
            System.out.print("Age : ");
            age1 = scan.nextInt();
        }
        String numT;
        System.out.print("Numéro Télephone : ");
        numT = scan.next();
        while (!veriTel(numT)) {
            System.out.println("Numero Telephone non valide !");
            System.out.print("Numéro Télephone : ");
            numT = scan.next();
        }

        String pays;
        System.out.print("Pays de Résidence : ");
        pays = scan.next();
        while (!verifStringLettreUni(pays)) {
            System.out.println("Pays non Valide ! ");
            System.out.print("Pays de Résidence : ");
            pays = scan.next();
        }

        Profil P = new Profil(name, nickName, g, age1, numT, pays);

        return P;
    }

    // methode de validation de nom, prenom, pays

    static boolean verifStringLettreUni(String S) {
        if (S.matches("[A-Za-z]+")) return true;
        else return false;
    }
    //  methode de validation numero tel
    static boolean veriTel(String S) {

        if (S.matches("[0-9]+") && S.length() >= 10) return true;
        else return false;
    }









    // La méthode qui modifier le nom de profil
    public void modifiernom()
    {

        String Newnom;
        System.out.print("Nom : ");
        Newnom = scan.next();
        while (!verifStringLettreUni(Newnom)) {
            System.out.println("Nom non valide ! ");
            System.out.print("Nom : ");
            Newnom = scan.next();
        }
        nom = Newnom;
    }


    // La méthode qui modifier le genre de profil
    public void modifierlegenre()
    {
        int choix;
        System.out.println("Genre: ");
        System.out.println("1 - Feminin");
        System.out.println("2 - Masculin");
        System.out.println("votre choix : ");
        choix= scan.nextInt();
        Genre gen;
        while (choix != 1 && choix != 2) {
            System.out.println("Choix non valide ! ");
            System.out.println("votre choix : ");
            choix = scan.nextInt();

        }
        if (choix == 1) gen = Genre.Feminin;
        else gen = Genre.Masculin;

        genre =gen;
    }


    // La méthode qui modifier l'age de profil
    public void modifierage()
    {
        int agee;
        System.out.print("Age : ");
        agee = scan.nextInt();
        while (agee < 6 || agee > 140) {
            System.out.println("Age non valide !");
            System.out.print("Age : ");
            agee = scan.nextInt();
        }
        age = agee;
    }
    // La méthode qui modifier le prénom de profil
    public void modifierprenom()
    {
        String Newprenom;
        System.out.print("Prenom : ");
        Newprenom = scan.next();
        while (!verifStringLettreUni(Newprenom)) {
            System.out.println("Prenom non valide");
            System.out.print("Prenom : ");
            Newprenom = scan.next();
        }
        prenom = Newprenom;
    }
    // La méthode qui modifier le  Numéro Telph de profil
    public  void modifierNumTel()
    {
        String numTel;
        System.out.print("Numéro Télephone : ");
        numTel = scan.next();
        while (!veriTel(numTel)) {
            System.out.println("Numero Telephone non valide !");
            System.out.print("Numéro Télephone : ");
            numTel = scan.next();
        }
        numTEL =  numTel;
    }
    // La méthode qui modifier le pays  de profil
    public  void modifierpays()
    {
        String pays2;
        System.out.print("Pays de Résidence : ");
        pays2 = scan.next();
        while (!verifStringLettreUni(pays2)) {
            System.out.println("Pays non Valide ! ");
            System.out.print("Pays de Résidence : ");
            pays2 = scan.next();
        }
        paysR = pays2;
    }


    @Override
    public String toString() {
        return ("Nom : " + nom + "\\ Prenom  : " + prenom + "\\ Genre :  " + genre.toString() + "\\ Age :  " + age + "\\ Num telephone : " + numTEL + "\\ Pays : " + paysR);
    }
    public  void afficher()
    {
        System.out.println(this);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Profil)) return false;
        Profil profil = (Profil) o;
        return age == profil.age &&
                Objects.equals(nom, profil.nom) &&
                Objects.equals(prenom, profil.prenom) &&
                genre == profil.genre &&
                Objects.equals(numTEL, profil.numTEL) &&
                Objects.equals(paysR, profil.paysR);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, prenom, genre, age, numTEL, paysR);
    }

}

