package com.company;

import java.util.*;

public class MessageAttach extends  Message{
    private HashSet<PieceJointe> Ensemble = new HashSet<>()  ; // On ajoute l’ensemble des pièces jointes (sans répétition)

    // constructeur
    public MessageAttach(String Objet, String contenu , Date date , float Taille , HashSet<PieceJointe> Ensemble   )
    {
        super(Objet,contenu , date, Taille);
        this.Ensemble = Ensemble;

    }

    // constructeur vide
    public MessageAttach() {}


    // Méthode de saisie d'un ensemble des pièce jointe
    public void Piece()
    {
        System.out.println("* Ajouter une piece : ");

        Scanner scan = new Scanner(System.in);

        PieceJointe P ;
        int x;
        do
        {
            P = new PieceJointe();
            P.saisie();
            this.Ensemble.add(P);
            setTaille(getTaille()+P.getTaille());
            System.out.println(" Attacher ENCORE ? ");
            System.out.println("(1)-OUI");
            System.out.println("(2)-NON");
            x=scan.nextInt();
        }while(x==1);


    }

    @Override
    public void saisir() throws Exception{

        super.saisir();
        Piece();

    }

    @Override
    public void affiche() {

        String msgLu;
        if (getLu()) msgLu = "(Lu)";
        else msgLu = "(NON LU)";
        System.out.println("MSG AVEC ATTACHEMENT " + msgLu + " - - - " + getEtat() + " - - - ");
        System.out.println("(OBJ) "+getObjet());
        System.out.println("| " + getContenu());
        System.out.println("| ATTACHEMENTS : ");
        ArrayList<PieceJointe> p = new ArrayList<>(Ensemble);
        for (int i = 0; i < Ensemble.size(); i++) {
            System.out.print("| ");
            p.get(i).affiche();
        }
        System.out.println("|                          Date : " + getDate());
        System.out.println("| _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ |");
    }

    @Override
    public String toString() {
        return (super.toString() + "\n"  +" L'Ensemble : " +  Ensemble );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MessageAttach)) return false;
        if (!super.equals(o)) return false;
        MessageAttach that = (MessageAttach) o;
        return Objects.equals(Ensemble, that.Ensemble);
    }

}


