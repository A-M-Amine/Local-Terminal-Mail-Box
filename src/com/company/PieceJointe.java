package com.company;

import java.util.Objects;
import java.util.Scanner;

public class PieceJointe {
    private String nom;
    private int taille;

    //constructeur
    public PieceJointe() {

    }

    public PieceJointe(String nom, int taille) {
        this.nom = nom;
        this.taille = taille;
    }

    // Les accesseurs
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public int getTaille() {
        return taille;
    }



    void saisie() {
        Scanner scan = new Scanner(System.in);

        System.out.print("Nom piece jointe : ");
        nom = scan.next();
        System.out.print("Taille piece Jointe en Mo : ");
        taille = scan.nextInt();
        if (taille > 10) throw new ArithmeticException("L’attachement dépasse 10Mo");
    }

    @Override
    public String toString() {
        return "PieceJointe{" + "nom='" + nom + '\'' + ", taille=" + taille + '}';

    }


    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PieceJointe)) return false;
        if (((PieceJointe) o).toString().equals(this.toString())) {
            System.out.println(" Vous essayez d'attacher 2piéces Identique");
            throw new ArithmeticException("Vous essayez d'attacher 2piéces Identique ");
        }
        PieceJointe that = (PieceJointe) o;
        return taille == that.taille &&
                Objects.equals(nom, that.nom);



    }


    public  void affiche () {
        System.out.println(nom + " " + taille+" mb");
    }

    @Override
    public int hashCode() {

        return Objects.hash(nom,taille);
    }}









