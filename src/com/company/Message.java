package com.company;

import java.util.Comparator;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

public class Message {
    // les attributs
    private  String Objet ;
    private String contenu ;
    private Date date = new  Date ();
    private  Etat etat ;
    private float Taille ;
    private boolean Lu;
    private Message repondu;
    enum Etat { cree , envoye , recu , encours , stocké, archivé}

    // Les accesseurs

    public Message getRepondu() { return repondu; }

    public void setRepondu(Message repondu) { this.repondu = repondu; }

    public void setLu(boolean lu) { Lu = lu; }

    public boolean getLu() { return Lu; }

    public Date getDate() {
        return date;
    }

    public Etat getEtat() {
        return etat;
    }

    public String getContenu() {
        return contenu;
    }

    public String getObjet() {
        return Objet;
    }

    public float getTaille() {
        return Taille;
    }

    public void setTaille(float taille) {
        Taille = taille;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }
    public void setObjet(String objet) {
        Objet = objet;
    }



    // constructeur  par défaut
    public Message ()
    {
        setEtat(Etat.cree);  // un état « cree » initialement donc c pour ça derto f l constructeur vide
        setDate(date);
        Lu = false;

    }
    //constructeur
    public Message(String Objet, String contenu, Date date, float Taille) {

        this.Objet = Objet;
        this.contenu = contenu;
        this.date = date;
        this.Taille= Taille;
        this.etat = Etat.cree;
    }


    // La Méthode Saisir
    public void saisir() throws Exception{


        Scanner e = new Scanner(System.in);
        Date  date = new Date();
        System.out.print( " L'objet  : ");
        setObjet(e.nextLine());
        System.out.println(" Le Message : ");
        String msg = e.nextLine();
        if (msg.equals("")) throw new Exception("ERROR Message Vide !");
        setContenu(msg);
        setTaille(0.1f);



    }


    // Méthode Afffiche
    public void affiche()
    {
        String msgLu;
        if (getLu()) msgLu = "(Lu)";
        else msgLu = "(NON LU)";
        System.out.println("MSG " + msgLu + " - - - " + getEtat() + " - - - - - - - - - - - - ");
        System.out.println("(OBJ) " + Objet);
        System.out.println("| " + contenu);
        System.out.println("|                          Date : " + getDate());
        System.out.println("| _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ |");
    }




    @Override
    public String toString() {
        return  ("Objet : " + Objet + "\n"  +" Contenu  : " + contenu + "\n" +   " Date : " + date  + "\n" + " Etat : " + etat );

    }
    // La redéfinition de la méthode equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;
        Message message = (Message) o;
        return Objects.equals(Objet, message.Objet) &&
                Objects.equals(contenu, message.contenu) &&
                Objects.equals(date, message.date) &&
                etat == message.etat;
    }

    public void modifier (String newObjet, String newContenu)

    { Objet  = newObjet ; contenu = newContenu ; }


    public static Comparator COMPARE_BY_DATE = new Comparator<Message>() {
        @Override
        public int compare(Message o1, Message o2) {
            return o1.getDate().compareTo(o2.getDate());
        }

    };

    public static Comparator COMPARE_BY_OBJET = new Comparator<Message>() {
        @Override
        public int compare(Message o1, Message o2) {
            return o1.getObjet().compareTo(o2.getObjet());
        }

    };


}
