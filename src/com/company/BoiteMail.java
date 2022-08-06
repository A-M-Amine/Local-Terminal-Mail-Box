package com.company;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.text.SimpleDateFormat;


public class BoiteMail {


    public static ArrayList<BoiteMail> listeBoite = new ArrayList<>();  // liste de stockage de boites Mail crées
    private Object adresseM = new AdresseMail();
    private ArrayList<Message> recus = new ArrayList<>();
    private ArrayList<Message> envoye = new ArrayList<>();
    private ArrayList<Message> brouillons = new ArrayList<>();
    private ArrayList<Message> archives = new ArrayList<>();
    private ArrayList<Message> corbeille = new ArrayList<>();
    private ArrayList<Message> spam = new ArrayList<>();
    private float capacite;
    private final float MAX_CAP = 100;

    //constructeur
    public BoiteMail() {
    }

    public BoiteMail(AdresseMail adresseM) {
        this.adresseM = adresseM;
    }

    public Object getAdresseM() { return adresseM; }


    public void setAdresseM(AdresseMail adresseM) {
        this.adresseM = adresseM;
    }

    public ArrayList<Message> getEnvoye() { return envoye; }

    public ArrayList<Message> getRecus() {
        return recus;
    }

    public void setRecus(ArrayList<Message> recus) {
        this.recus = recus;
    }

    public float getPourcentageUtilise() {

        return (getCapacite() * 100) / MAX_CAP;

    }




    // Méthode de creation d'une aprés la verification si l'adresse du boite est déja créé ou non
    public void creeBoite(Object nvMail, boolean ajoutBoite) throws Exception{


        if (ajoutBoite) {

            Scanner scan = new Scanner(System.in);

            System.out.println("Choisir le type d'adresse ");
            System.out.println("1- Adresse Mail ");
            System.out.println("2 - Adresse Mail Professionnelle ");
            System.out.print("Votre Choix: ");

            int choix = scan.nextInt();
            if (choix == 1) {
                AdresseMail m = new AdresseMail();
                m.saisir();
                adresseM = m;

            }
            else {

                AdresseMailProf prof = new AdresseMailProf();
                prof.saisir();
                adresseM = prof;
            }

            nvMail = adresseM;
        }

        AdresseMail x = (AdresseMail) nvMail;

        if (boiteExist(x.getadr())) System.out.println("Boite de l'adresse "+x.getadr()+" existe déja");
        else {

            setAdresseM(x);

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date d = null;
            d = dateFormat.parse("2/2/2021");
            Message hello = new Message("Bienvenue", "Hello", d,0.1f);
            hello.setEtat(Message.Etat.recu);
            recus.add(hello);

            capacite = getCapacite();

            listeBoite.add(this);
        }

    }



    // Méthode de creation d'un message selon le type
    public Message creeMsg() throws Exception {

        Scanner scan = new Scanner(System.in);

        int choix = 0;
        while (choix != 1 && choix != 2) {

            System.out.println("Choisir le type de message : ");
            System.out.println("1 - message avec attachement");
            System.out.println("2 - message normal");
            System.out.print("     Votre Choix : ");
            choix = scan.nextInt();
            if (choix != 1 && choix != 2) System.out.println("Choix non valide !");

        }

        Message msg;
        if (choix == 1) {
            msg = new MessageAttach();
        } else {
            msg = new Message();
        }
        msg.saisir();

        float x = getCapacite();
        x = ( x * 100 ) / MAX_CAP;
        if (x >= 80) System.out.println("ALERTE [ Espace utilisé Atteint 80% de la capacité Maximale de la boite Supprimer certains messages! ]");

        return msg;
    }

    // Méthode d'envoi d'un msg aprés une verification si le message est stocker dans le dossir Brouillons
    public void envoyeMSG(Message msg, boolean fromBrouillons) throws Exception {

        Scanner scan = new Scanner(System.in);
        ArrayList<String> tmp = new ArrayList<>();
        String adr = "";
        System.out.println("Saisir adresse(s) de(s) destinataire(s) : ");
        while (true) { // boucle pour le cas de multi destinataires
            adr = scan.next();
            if (adr.equals("0")) break;
            if (!boiteExist(adr)) throw new Exception("Adresse Destinataire incorrecte");
            tmp.add(adr);
            System.out.println("saisir \"0\" pour sortir ");
        }

        int choix = 1;

        if (!fromBrouillons) {

            choix = 0;
            while (choix != 1 && choix != 2) {

                System.out.println("1 - Envoyé");
                System.out.println("2 - Stocker comme Broullion ");
                System.out.print("     Votre Choix : ");
                choix = scan.nextInt();
                if (choix != 1 && choix != 2) System.out.println("Choix non valide !");

            }

        }


        if (choix == 1) {
            // l'ajout des messages dans le dossier recu de chaque boite mail destinataire aprés la comparison par adresse string unique
            envoye.add(msg);
            for (int i = 0; i < tmp.size(); i++) {
                for (int j = 0; j < listeBoite.size(); j++) {
                    AdresseMail x = (AdresseMail) listeBoite.get(j).adresseM;
                    if (x.getadr().equals(tmp.get(i))) {
                        msg.setLu(false);
                        msg.setEtat(Message.Etat.recu);
                        listeBoite.get(j).recus.add(msg);
                    }

                }
            }
        } else corbeille.add(msg);

        float x = getCapacite();
        x = ( x * 100 ) / MAX_CAP;
        if (x >= 80) System.out.println("ALERTE [ Espace utilisé Atteint 80% de la capacité Maximale de la boite Supprimer certains messages! ]");

    }

    public void repondreMsg() throws Exception {

        Scanner scan = new Scanner(System.in);
        int index;
        System.out.println("Selectionnez le message auquel vous souhaitez répondre ");
        int i = 0;
        while (i >= 0 && i < recus.size()) {
            recus.get(i).affiche();
            System.out.println("1 - Répondre");
            if (i != recus.size() - 1) System.out.println("2 - suivant");
            if (i != 0) System.out.println("3 - précédent");
            System.out.print("    Votre Choix : ");
            int choix = scan.nextInt();
            if (choix == 1) {
                Message reponse = creeMsg();
                reponse.setRepondu(recus.get(i));
                for (BoiteMail box: listeBoite) {
                    if (box.envoye.contains(recus.get(i))) {
                        ajoutEnvoye(reponse);
                    }
                }
                System.out.println("Répondre a un autre message : ");
                System.out.println("1- Oui  / 2 - Non");
                int choix2 = scan.nextInt();
                if (choix2 != 1) break;
                i = 0;
            } else if (choix == 2) i++;
            else if (choix == 3) i--;
        }
    }

    // Méthode qui vérifie si une boite existe Déja Dans le répertoire
    public boolean boiteExist(String adr) {

        int L = listeBoite.size();
        int j = 0;
        AdresseMail f;
        if (!listeBoite.isEmpty()) {

            while (j < L) {

                f = (AdresseMail) listeBoite.get(j).adresseM;
                if (f.getadr().equals(adr)) return true;
                j++;
            }

        }

        return false;
    }


    public void affichermessage() {

        Scanner scan = new Scanner(System.in);

        System.out.println("Choisir le Dossier contenant message ");
        System.out.println("1 - Dossier Envoyé ");
        System.out.println("2 - Dossier Recu ");
        System.out.println("3 - Dossier Corbeille");
        System.out.println("4 - Dossier Archivés");
        System.out.println("5 - Dossier Brouillons");
        System.out.println("6 - Dossier SPAM");
        System.out.println(" Votre Choix : ");
        int d = scan.nextInt();
        ArrayList<Message> dossier = new ArrayList<>();
        switch (d) {
            case 1:
                dossier = envoye;
                break;
            case 2:
                dossier = recus;
                break;
            case 3:
                dossier = corbeille;
                break;
            case 4:
                dossier = archives;
                break;
            case 5:
                dossier = brouillons;
                break;
            case 6:
                dossier = spam;
                break;
        }


        System.out.println("Choisir un message ");
        if (dossier.isEmpty()) {
            System.out.println("Dossier vide");
        }else {
            for (int i = 0; i < dossier.size(); i++) {
                System.out.println(i + "- " +dossier.get(i).getObjet());
            }
            System.out.println("  Votre choix ");
            int indexmsg = scan.nextInt();
            if (indexmsg >= 0 && indexmsg<dossier.size()) {
                dossier.get(indexmsg).setLu(true);
                dossier.get(indexmsg).affiche();
            }

        }



    }

    public void restaurerMsg() {

        Scanner scan = new Scanner(System.in);

        System.out.println("Selectionner le message a restauré ");
        int i = 0;
        while (i >= 0 && i < corbeille.size()) { // utilisation d'une boucle pour parcourir les Messages
            corbeille.get(i).affiche();
            System.out.println("1 - Restaurer");
            if (i != corbeille.size() - 1) System.out.println("2 - suivant");
            if (i != 0) System.out.println("3 - précédent");
            System.out.print("    Votre Choix : ");
            int choix = scan.nextInt();
            if (choix == 1) {
                if (corbeille.get(i).getEtat() == Message.Etat.envoye) {envoye.add(corbeille.get(i));
                }else recus.add(corbeille.get(i));
                corbeille.remove(i);
                System.out.println("Restaurer un autre message : ");
                System.out.println("1- Oui  / 2 - Non");
                int choix2 = scan.nextInt();
                if (choix2 != 1) break;
                i = 0;
            } else if (choix == 2) i++;
            else if (choix == 3) i--;
        }

        if (corbeille.isEmpty()) System.out.println("Corbeille Vide!");


    }

    public void supprVersCorbeille() {

        Scanner scan = new Scanner(System.in);
        ArrayList<Message> tmp;

        System.out.println("Supprimer un message dans le dossier : ");
        System.out.println("1- reçus ");
        System.out.println("2- envoyé ");
        int choix = scan.nextInt();
        if (choix == 1) tmp = recus;
        else tmp = envoye;

        System.out.println("Selectionner le message a supprimé ");
        int i = 0;
        while (i >= 0 && i < tmp.size()) {  // utilisation d'une boucle pour parcourir les Messages
            tmp.get(i).affiche();
            System.out.println("1 - Supprimer ");
            if (i != tmp.size() - 1) System.out.println("2 - suivant");
            if (i != 0) System.out.println("3 - précédent");
            System.out.print("    Votre Choix : ");
            choix = scan.nextInt();
            if (choix == 1) {
                corbeille.add(tmp.get(i));
                tmp.remove(i);
                System.out.println("Supprimer un autre message : ");
                System.out.println("1- Oui  / 2 - Non");
                int choix2 = scan.nextInt();
                if (choix2 != 1) break;
                i = 0;
            } else if (choix == 2) i++;
            else if (choix == 3) i--;

        }


        if (tmp.isEmpty()) System.out.println("Dossier Vide");



    }

    // Méthode du suppression d'un message définitivment
    public void supprimerCorbeille() {

        Scanner scan = new Scanner(System.in);

        System.out.println("Selectionner le message a supprimé ");
        int i = 0;
        while (i >= 0 && i < corbeille.size()) {
            corbeille.get(i).affiche();
            System.out.println("1 - supprimer");
            if (i != corbeille.size() - 1) System.out.println("2 - suivant");
            if (i != 0) System.out.println("3 - précédent");
            System.out.print("    Votre Choix : ");
            int choix = scan.nextInt();
            if (choix == 1) {
                corbeille.remove(i);
                System.out.println("Supprimer un autre message : ");
                System.out.println("1- Oui  / 2 - Non");
                int choix2 = scan.nextInt();
                if (choix2 != 1) break;
                i = 0;
            } else if (choix == 2) i++;
            else if (choix == 3) i--;
        }

        if (corbeille.isEmpty()) System.out.println("Corbeille Vide!");



    }


    public void deplacerSPAM() {

        Scanner scan = new Scanner(System.in);

        System.out.println("Selectionner le message a déplacé");
        int i = 0;
        while (i >= 0 && i < recus.size()) {
            recus.get(i).affiche();
            System.out.println("1 - déplacer vers Spam");
            if (i != recus.size() - 1) System.out.println("2 - suivant");
            if (i != 0) System.out.println("3 - précédent");
            System.out.print("    Votre Choix : ");
            int choix = scan.nextInt();
            if (choix == 1) {
                spam.add(recus.get(i));
                recus.remove(i);
                System.out.println("Déplacer un autre message : ");
                System.out.println("1- Oui  / 2 - Non");
                int choix2 = scan.nextInt();
                if (choix2 != 1) break;
                i = 0;
            } else if (choix == 2) i++;
            else if (choix == 3) i--;
        }

        if (recus.isEmpty()) System.out.println("Dossier Reçus Vide");



    }

    public void envoyerBrouillons() throws Exception {

        Scanner scan = new Scanner(System.in);

        System.out.println("Selectionner le message a envoyé");

        int i = 0;
        while (i >= 0 && i < brouillons.size()) {
            brouillons.get(i).affiche();
            System.out.println("1 - envoyer ");
            if (i != brouillons.size() - 1) System.out.println("2 - suivant");
            if (i != 0) System.out.println("3 - précédent");
            System.out.print("    Votre Choix : ");
            int choix = scan.nextInt();
            if (choix == 1) {

                envoyeMSG(brouillons.get(i), true);
                brouillons.remove(i);
                System.out.println("Envoyer un autre message : ");
                System.out.println("1- Oui  / 2 - Non");
                int choix2 = scan.nextInt();
                if (choix2 != 1) break;
                i = 0;
            } else if (choix == 2) i++;
            else if (choix == 3) i--;
        }

        if (brouillons.isEmpty()) System.out.println("Dossier Brouillons Vide");


    }

    public void archive() {

        Scanner scan = new Scanner(System.in);
        ArrayList<Message> List = new ArrayList<>();

        System.out.println("Archiver un message dans le dossier :");
        System.out.println("1 - reçus ");
        System.out.println("2 - envoyé");
        System.out.println("3 - brouillons");
        System.out.println("4 - corbeille");
        System.out.println("5 - Spam");
        System.out.print("     Votre choix : ");
        int choice = scan.nextInt();
        switch (choice) {
            case 1:
                List = recus;
                break;
            case 2:
                List = envoye;
                break;
            case 3:
                List = brouillons;
                break;
            case 4:
                List = corbeille;
                break;
            case 5:
                List = spam;
                break;
        }

        System.out.println("Selectionner le message a archivé");

        int i = 0;
        while (i >= 0 && i < List.size()) {
            List.get(i).affiche();
            System.out.println("1 - Archiver ");
            if (i != List.size() - 1) System.out.println("2 - suivant");
            if (i != 0) System.out.println("3 - précédent");
            System.out.print("    Votre Choix : ");
            int choix = scan.nextInt();
            if (choix == 1) {
                List.get(i).setEtat(Message.Etat.archivé);
                archives.add(List.get(i));
                List.remove(i);
                System.out.println("Archiver un autre message : ");
                System.out.println("1- Oui  / 2 - Non");
                int choix2 = scan.nextInt();
                if (choix2 != 1) break;
                i = 0;
            } else if (choix == 2) i++;
            else if (choix == 3) i--;
        }

        if (List.isEmpty()) System.out.println("Dossier Vide ");




    }


    public void archiveDate() throws Exception {

        ArrayList<Message> tmp;

        Scanner scan = new Scanner(System.in);

        System.out.print("Donner une date (Format dd/MM/yyyy ) : ");
        String date = scan.next();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date madate = null;
        madate = dateFormat.parse(date);


        for (int i = 1; i < 6; i++) {
            if (i == 1) tmp = recus;
            else if (i == 2) tmp = envoye;
            else if (i == 3) tmp = corbeille;
            else if (i == 4) tmp = brouillons;
            else tmp = spam;


            for (int j = 0; j < tmp.size(); j++) {

                if (tmp.get(j).getDate().compareTo(madate) < 0) {
                    tmp.get(j).setEtat(Message.Etat.archivé);
                    archives.add(tmp.get(j));
                    tmp.remove(j);
                    j--;
                }
            }

        }

        System.out.println("Messages avant "+ madate + " Archivés ");

    }

    // méthodes calcul de nombre des messages dans chaque dossier

    public int getNbRecu() {
        return recus.size();
    }

    public int getNbenvoye() { return envoye.size(); }

    public int getNbspam() {
        return spam.size();
    }

    public int getNbarchives() {
        return archives.size();
    }

    public int getNbcorbielle() {
        return corbeille.size();
    }

    public int getNbbrouillons() {
        return brouillons.size();
    }

    public void ajoutRecus(Message msg) {
        recus.add(msg);
        msg.setEtat(Message.Etat.recu);
    }
    public void ajoutEnvoye(Message msg) {
        envoye.add(msg);
        msg.setEtat(Message.Etat.envoye);
    }

    public void ajoutBrouillons(Message msg) {
        brouillons.add(msg);
        msg.setEtat(Message.Etat.stocké);
    }

    public void viderSpam() {
        spam.removeAll(spam);
    }

    public void viderEnvoye() {
        envoye.removeAll(envoye);
    }


    // Méthode du Tri
    public void triDossier() {

        Scanner scan = new Scanner(System.in);

        System.out.println("Choisir le type de Tri ");
        System.out.println("1 - Tri par Date ");
        System.out.println("2 - Tri par Objet (Ordre Alphabétique) ");
        System.out.print("Votre Choix : ");
        int choix = scan.nextInt();


        System.out.println("Choisir le Dossier contenant message ");
        System.out.println("1 - Dossier Envoyé ");
        System.out.println("2 - Dossier Recu ");
        System.out.println("3 - Dossier Corbeille");
        System.out.println("4 - Dossier Archivés");
        System.out.println("5 - Dossier Brouillons");
        System.out.println("6 - Dossier SPAM");
        System.out.println(" Votre Choix : ");
        int d = scan.nextInt();
        ArrayList<Message> dossier = new ArrayList<>();
        switch (d) {
            case 1:
                dossier = envoye;
                break;
            case 2:
                dossier = recus;
                break;
            case 3:
                dossier = corbeille;
                break;
            case 4:
                dossier = archives;
                break;
            case 5:
                dossier = brouillons;
                break;
            case 6:
                dossier = spam;
                break;
        }

        if (choix == 1) {
            Collections.sort(dossier,Collections.reverseOrder(Message.COMPARE_BY_DATE));
        }else {
            Collections.sort(dossier,Message.COMPARE_BY_OBJET);
        }

    }


    public float getCapEnvoye() {
        float cap = 0;
        for (int i = 0; i < envoye.size(); i++) cap  += envoye.get(i).getTaille();

        return cap;
    }

    // Méthode de calcul de espace utilisé
    public float getCapacite() {

        ArrayList<Message> tmp;

        float cap = 0;

        for (int i = 1; i < 7; i++) {
            if (i == 1) tmp = recus;
            else if (i == 2) tmp = envoye;
            else if (i == 3) tmp = corbeille;
            else if (i == 4) tmp = brouillons;
            else if (i == 5) tmp = archives;
            else tmp = spam;


            for (int j = 0; j < tmp.size(); j++) cap += tmp.get(j).getTaille();

        }

        return cap;
    }

    // Méthode de calcul de pourcentage d'utilisation site par un certain groupe d'age
    public static float pourcentageSite(String site) {

        int utilisationSite = 0; // variable de nombre totale des boites sous un site donné
        int utilAge = 0;
        for (BoiteMail box: listeBoite) {
            AdresseMail x = (AdresseMail) box.getAdresseM();
            if (x.getSite().equals(site)) {
                utilisationSite++;
                if (x.getP().getAge() >= 18 && x.getP().getAge() <= 35) utilAge++;
            }
        }
        if (utilisationSite == 0) return 0;
        else return (utilAge * 100) / (float) utilisationSite;


    }


    public float stockageRestant() throws Exception{
        float sr = MAX_CAP - getCapacite();
        if (sr < 0) throw new Exception("Capacité de Stockage Dépassé ");
        return sr;
    }


    public void afficheMsgAttach() { // méthode d'affichage des messages avec attachement(s) d'une boite

        ArrayList<Message> tmp;


        for (int i = 1; i < 7; i++) {
            if (i == 1) tmp = recus;
            else if (i == 2) tmp = envoye;
            else if (i == 3) tmp = corbeille;
            else if (i == 4) tmp = brouillons;
            else if (i == 5) tmp = archives;
            else tmp = spam;


            for (int j = 0; j < tmp.size(); j++) if (tmp.get(j) instanceof MessageAttach) tmp.get(j).affiche();

        }

    }

    public void recherchemotcle(String mot) { // méthode de recherche d'un msg par un mot clé

        ArrayList<Message> tmp;
        boolean dansBoite = false;

        for (int i = 1; i < 7; i++) {
            if (i == 1) tmp = recus;
            else if (i == 2) tmp = envoye;
            else if (i == 3) tmp = corbeille;
            else if (i == 4) tmp = brouillons;
            else if (i == 5) tmp = archives;
            else tmp = spam;


            for (int j = 0; j < tmp.size(); j++) if (tmp.get(j).getObjet().contains(mot) || tmp.get(j).getContenu().contains(mot)) {
                if(!dansBoite) {
                    AdresseMail x = (AdresseMail) adresseM;
                    System.out.println("Trouvé Dans la boite " + x.getadr() + " : ");
                    dansBoite = true;
                }
                tmp.get(j).affiche();
            }


        }

    }

    public static void piratagesite(String site) {

        ArrayList<Message> tmp;

        for (BoiteMail box: listeBoite) {

            AdresseMail x = (AdresseMail) box.getAdresseM();

            if (x.site.equals(site)) {

                for (int i = 1; i < 7; i++) {
                    if (i == 1) tmp = box.recus;
                    else if (i == 2) tmp = box.envoye;
                    else if (i == 3) tmp = box.corbeille;
                    else if (i == 4) tmp = box.brouillons;
                    else if (i == 5) tmp = box.archives;
                    else tmp = box.spam;


                    for (int j = 0; j < tmp.size(); j++) tmp.clear();

                }
            }

        }

    }

    public void afficheboite() throws Exception{

        AdresseMail x = (AdresseMail)adresseM;

        System.out.println("Adresse Mail : " + x.getadr());

        System.out.print("** Les msgs dans le dossier Reçu : ");
        if (recus.isEmpty()) System.out.println("<VIDE>");
        else System.out.println();
        for (Message s : recus) s.affiche();
        System.out.print("** Les msgs dans la corbielle : ");
        if (corbeille.isEmpty()) System.out.println("<VIDE>");
        else System.out.println();
        for (Message j : corbeille) j.affiche();
        System.out.print("** Les msgs dans le dossier Brouillons : ");
        if (brouillons.isEmpty()) System.out.println("<VIDE>");
        else System.out.println();
        for (Message j : brouillons) j.affiche();
        System.out.print("** Les msgs dans le dossier Envoyé : ");
        if (envoye.isEmpty())System.out.println("<VIDE>");
        else System.out.println();
        for (Message j : envoye) j.affiche();
        System.out.print("** Les msgs dans le dossier Archivés : ");
        if (archives.isEmpty()) System.out.println("<VIDE>");
        else System.out.println();
        for (Message j : archives) j.affiche();
        System.out.print("** Les msgs dans le dossier SPAM : ");
        if (spam.isEmpty()) System.out.println("<VIDE>");
        else System.out.println();
        for (Message j : spam) j.affiche();

        System.out.println("Capacité De Boite : " + MAX_CAP + " mb");
        System.out.println("Espace utilisé : " + getCapacite() +" mb");
        System.out.println("Espace Restant : " + stockageRestant() + " mb");

    }

    // Méthode pour sélectionner une boite (utilisé dans le AppMessagerie)
    public static int selectionnerB() {

        Scanner scan = new Scanner(System.in);
        int index;
        do {
            System.out.println("choisir une boite ");
            for (int i = 0; i < listeBoite.size(); i++) {
                AdresseMail x = (AdresseMail) listeBoite.get(i).getAdresseM();
                System.out.println(i +"- " + x.getPseudo()+"@" + x.getSite());
            }
            System.out.println("Votre Choix : ");
            index = scan.nextInt();
            if (index<0 || index>= listeBoite.size()) System.out.println("Choix non valide ! ");

        }while (index<0 || index>= listeBoite.size());

        return index;

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoiteMail boiteMail = (BoiteMail) o;
        return Float.compare(boiteMail.capacite, capacite) == 0 &&
                Float.compare(boiteMail.MAX_CAP, MAX_CAP) == 0 &&
                Objects.equals(adresseM, boiteMail.adresseM) &&
                Objects.equals(recus, boiteMail.recus) &&
                Objects.equals(envoye, boiteMail.envoye) &&
                Objects.equals(brouillons, boiteMail.brouillons) &&
                Objects.equals(archives, boiteMail.archives) &&
                Objects.equals(corbeille, boiteMail.corbeille) &&
                Objects.equals(spam, boiteMail.spam);
    }

    @Override
    public String toString() {
        return "BoiteMail{ \n" +
                "- adresseM = " + adresseM +
                "\n- recus = " + recus +
                "\n- envoye = " + envoye +
                "\n- brouillons = " + brouillons +
                "\n- archives = " + archives +
                "\n- corbeille = " + corbeille +
                "\n- spam = " + spam +
                "\n- capacite = " + capacite +
                "\n- MAX_CAP = " + MAX_CAP + " }";
    }
}
