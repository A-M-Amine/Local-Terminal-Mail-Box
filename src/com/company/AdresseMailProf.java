package com.company;

import java.util.Objects;
import java.util.Scanner;

public class AdresseMailProf extends AdresseMail {


    private String nomEntreprise;
    private String domaine;

    public AdresseMailProf(String pseudo, String site, Profil p, String motDePasse, String nomEntreprise, String domaine) {
        super(pseudo, site, p, motDePasse);
        this.nomEntreprise = nomEntreprise;
        this.domaine = domaine;
    }

    public AdresseMailProf() {}

    // Les accesseurs

    public String getNomEntreprise() {
        return nomEntreprise;
    }

    public void setNomEntreprise(String nomEntreprise) {
        this.nomEntreprise = nomEntreprise;
    }

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }




    public void saisir() {

        Scanner scan = new Scanner(System.in);

        super.saisir();

        System.out.print("Nom D'entreprise : ");
        nomEntreprise = scan.next();
        while (!verifStringLettreUni(nomEntreprise)) {
            System.out.println("Nom D'entreprise non valide ! ");
            System.out.print("Nom D'entreprise : ");
            nomEntreprise = scan.next();
        }

        System.out.print("Domaine : ");
        domaine = scan.next();
        while (!verifStringLettreUni(domaine)) {
            System.out.println("Domaine non Valide ! ");
            System.out.print("Domaine : ");
            domaine = scan.next();
        }

    }


    boolean verifStringLettreUni(String S) {     // methode de validation de nom Entreprise , Domaine
        if (S.matches("[A-Za-z]+")) return true;
        else return false;
    }

    // * * * * * * *  les méthodes de la modification

    public void modifierEntr(){

        Scanner scan = new Scanner(System.in);
        System.out.print("Nom D'entreprise : ");
        String nomEntrepris = scan.next();
        while (!verifStringLettreUni(nomEntrepris))
        {
            System.out.println("Nom D'entreprise non valide ! ");
            System.out.print("Nom D'entreprise : ");
            nomEntrepris = scan.next();
        } ;
        nomEntreprise = nomEntrepris;
    }

    public  void modifierDomaine()
    {   Scanner scan = new Scanner(System.in);
        System.out.print("Domaine : ");
        String domain = scan.next();
        while (!verifStringLettreUni(domain)) {
            System.out.println("Domaine non Valide ! ");
            System.out.print("Domaine : ");
            domain = scan.next();
        }
        domaine = domain;
    }

    // * * * * * * * * * *


    @Override
    public String toString() {
        return super.toString()+ " Entreprise " + nomEntreprise + " Domaine " + domaine;
    }


    public void  afficher() { System.out.println(this); }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AdresseMailProf)) return false;
        if (!super.equals(o)) return false;
        AdresseMailProf that = (AdresseMailProf) o;
        return Objects.equals(nomEntreprise, that.nomEntreprise) &&
                Objects.equals(domaine, that.domaine);
    }


    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nomEntreprise, domaine);
    }


}
