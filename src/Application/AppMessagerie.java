package Application;

import com.company.*;

import java.util.*;

public class AppMessagerie {

    //attributs

    AdresseMail adresseMail = new AdresseMail();
    AdresseMailProf adresseMailProf = new AdresseMailProf();
    ArrayList<AdresseMail> mailCree = new ArrayList<>();

    ArrayList<BoiteMail> boitePublic = new ArrayList<>();
    ArrayList<BoiteMail> boiteProf = new ArrayList<>();


    Scanner e = new Scanner(System.in);




    // MENU

    public void menu() throws Exception {

        // Creation des boites  * * * * * * *

        Profil p = new Profil("AHM", "Amine", Profil.Genre.Masculin, 21, "0688888888", "Algerie");
        AdresseMail pp = new AdresseMail("exampm", "gmail.com", p, "pooPooo+123");

        Profil g = new Profil("SEN", "Im", Profil.Genre.Feminin, 20, "0677777777", "Algerie");
        AdresseMailProf gg = new AdresseMailProf("exam.s", "yahoo.com", g, "pooPass+321", "USTHB", "INFO");

        Profil f = new Profil("MUSK", "Elon", Profil.Genre.Masculin, 49, "2025550484", "états unis");
        AdresseMailProf ff = new AdresseMailProf("musk", "gmail.com", f, "pooPass+9595", "Tesla", "TECHNOLOGIE");

        Profil o = new Profil("ABI","Nesri", Profil.Genre.Feminin,29,"0582469575","France");
        AdresseMail oo = new AdresseMail("abd","yahoo.com",o,"pooPass+456");

        Profil l = new Profil("RAd","Isa", Profil.Genre.Masculin,31,"0458754872","France");
        AdresseMailProf ll = new AdresseMailProf("isa89", "gmail.com", l, "pooPass+654", "Tesla", "Electronic");

        Profil k = new Profil("BONNEVILLE","Isabelle", Profil.Genre.Feminin,28,"0985487524","Brésil");
        AdresseMailProf kk = new AdresseMailProf("isab", "gmail.com", k, "pooPass+584", "BIOSPACE", "Science");

        Profil u = new Profil("FOREST","Henry", Profil.Genre.Masculin,55,"0554875648","Algerie");
        AdresseMail uu = new AdresseMail("forhen","gmail.com",p,"pooPass+975");

        mailCree.add(pp);
        mailCree.add(gg);
        mailCree.add(ff);
        mailCree.add(oo);
        mailCree.add(ll);
        mailCree.add(kk);
        mailCree.add(uu);

        // * * * * * * * *
        int a;

        do {
            System.out.println("= = = = = = = = = = = = = = = = = = = = = = = = ");
            System.out.println(" - - - - - Bienvenu - - - - - - :");
            menu:
            System.out.println("1-Création des adresses e-mail (avec les profils)");
            System.out.println("2-Ajouter/suppr/modifier une adresse");
            System.out.println("3-Affichage des adresses créées (par catégorie)");
            System.out.println("4-Création des boites e-mail correspondantes");
            System.out.println("5- Ajouter une boite");
            System.out.println("6-Affichage des boites e-mails avec leur contenu");
            System.out.println("7-Gestion des boites e-mails");
            System.out.println("8- Quitter");
            System.out.print("Votre Choix ==> ");
            a = e.nextInt();
            System.out.println("= = = = = = = = = = = = = = = = = = = = = = = = ");
            switch (a) {
                case 1:
                    //Création des adresses e-mail
                    int b = 0;
                    while (b != 3) {

                        System.out.println("1-Creation d'un adresse e-mail ");
                        System.out.println("2-Creation d'un adresse e-mail Professionnelle ");
                        System.out.println("3-Allez vers le menu précédent");
                        System.out.print("Votre Choix : ");
                        b = e.nextInt();
                        switch (b) {
                            case 1:


                                adresseMail.saisir();
                                mailCree.add(adresseMail);

                                break;
                            case 2:


                                adresseMailProf.saisir();
                                mailCree.add(adresseMailProf);


                                break;

                        }


                    }


                    break;


                case 2:

                    System.out.println("1-Ajouter une adresse e-mail");
                    System.out.println("2-Supprimer une adresse e-mail");
                    System.out.println("3-Modifier une adresse e-mail ");
                    System.out.print("Votre Choix : ");
                    int choix = e.nextInt();

                    if (choix == 1) {
                        System.out.println("1-Creation d'un adresse e-mail ");
                        System.out.println("2-Creation d'un adresse e-mail Professionnelle ");
                        System.out.print("      Votre Choix: ");
                        int choix1 = e.nextInt();
                        switch (choix1) {
                            case 1:

                                adresseMail.saisir();
                                adresseMail.afficher();
                                mailCree.add(adresseMail);

                                break;
                            case 2:

                                adresseMailProf.saisir();
                                adresseMailProf.afficher();
                                mailCree.add(adresseMailProf);

                                break;

                        }
                    } else {
                        System.out.println("choisir l'adresse ");
                        for (int i = 0; i < mailCree.size(); i++) {
                            System.out.println(i + "-  " + mailCree.get(i).getPseudo() + "@" + mailCree.get(i).getSite());
                        }
                        System.out.print("Votre choix : ");
                        int indexADR = e.nextInt();

                        if (indexADR >= 0 && indexADR < mailCree.size()) {
                            switch (choix) {
                                case 2:

                                    mailCree.remove(indexADR);
                                    System.out.println("Adresse supprimé");

                                    break;
                                case 3:

                                    System.out.println("1-Modifie  le pseudo d'adresse e-mail ");
                                    System.out.println("2-Modifier  le mot de passe d' adresse e-mail");
                                    System.out.println("3-Modifier Le profil ");
                                    if (mailCree.get(indexADR) instanceof AdresseMailProf) {
                                        System.out.println("4- Modier Le nom D'Entreprise ");
                                        System.out.println("5- Modifier Le Domaine ");
                                    }
                                    int choix2 = e.nextInt();
                                    switch (choix2) {

                                        case 1:
                                            mailCree.get(indexADR).modfierPseudo();

                                            break;
                                        case 2:
                                            mailCree.get(indexADR).modifierMotDePasse();

                                            break;

                                        case 3:
                                            System.out.println("1-Modifie  le Nom ");
                                            System.out.println("2-Modifier  Le prénom");
                                            System.out.println("3-Modifier L'age ");
                                            System.out.println("4-Modifier le genre ");
                                            System.out.println("5-Modifier le pays ");
                                            System.out.println("6-Modifier  Le Numéro d Téléphone");
                                            System.out.print("Votre Choix : ");
                                            int choix3 = e.nextInt();
                                            switch (choix3) {
                                                case 1:
                                                    mailCree.get(indexADR).getP().modifiernom();
                                                    break;
                                                case 2:
                                                    mailCree.get(indexADR).getP().modifierprenom();
                                                    break;
                                                case 3:
                                                    mailCree.get(indexADR).getP().modifierage();
                                                    break;
                                                case 4:
                                                    mailCree.get(indexADR).getP().modifierlegenre();
                                                    break;
                                                case 5:
                                                    mailCree.get(indexADR).getP().modifierpays();
                                                    break;
                                                case 6:
                                                    mailCree.get(indexADR).getP().modifierNumTel();
                                                    break;


                                            }

                                            break;

                                        case 4:

                                            if (mailCree.get(indexADR) instanceof AdresseMailProf) {
                                                AdresseMailProf mail = (AdresseMailProf) mailCree.get(indexADR);
                                                mail.modifierEntr();
                                            }
                                            break;

                                        case 5:
                                            if (mailCree.get(indexADR) instanceof AdresseMailProf) {
                                                AdresseMailProf mail = (AdresseMailProf) mailCree.get(indexADR);
                                                mail.modifierDomaine();
                                            }
                                            break;

                                    }


                                    break;

                            }

                        }

                    }


                    break;

                //Affichage des adresse creees (par catégorie)

                case 3:
                    System.out.println("1- Affichage par site ");
                    System.out.println("2-Affichage par Type professionnel ou non");
                    System.out.print("Votre Choix : ");
                    int G = e.nextInt();
                    switch (G) {   //Affichage par site
                        case 1:
                            System.out.print("Saisir Le Site :");
                            String site = e.next();
                            for (int i = 0; i < mailCree.size(); i++) {
                                if (mailCree.get(i).getSite().equals(site)) System.out.println(mailCree.get(i));
                            }

                            break;
                        case 2: //Affichage par Type

                            System.out.println("1-Affichage des adresses e-mail");
                            System.out.println("2-Afficher des adresses e-mail Professionnelles");
                            System.out.print("Votre Choix : ");
                            int H = e.nextInt();
                            switch (H) {
                                case 1: //par Type
                                    for (int i = 0; i < mailCree.size(); i++) {
                                        if (!(mailCree.get(i) instanceof AdresseMailProf))
                                            System.out.println(mailCree.get(i));
                                    }

                                    break;
                                case 2: //sans Type
                                    for (int i = 0; i < mailCree.size(); i++) {
                                        if ((mailCree.get(i) instanceof AdresseMailProf))
                                            System.out.println(mailCree.get(i));
                                    }
                                    break;


                            }
                            break;

                    }
                    break;

                case 4: //Creation des boites mail correspondantes

                    System.out.println("Boites Créées");

                    for (int i = 0; i < mailCree.size(); i++) {

                        BoiteMail boite = new BoiteMail();
                        boite.creeBoite(mailCree.get(i), false);

                    }

                    break;

                case 5: // Ajouter une boite

                    AdresseMail tmp = new AdresseMail();
                    BoiteMail boite = new BoiteMail();
                    boite.creeBoite(tmp, true);

                    break;
                case 6: //afficher le contenu des boites

                    for (int i = 0; i < BoiteMail.listeBoite.size(); i++) {
                        System.out.println("Boite " + (i + 1) + " - - - - - - - - - ");
                        System.out.println(BoiteMail.listeBoite.get(i).toString());
                        System.out.println(" - - - - - - - - - - - - - - - - - - - ");
                    }

                    break;
                case 7:     ////Gestion des boites mail sous menu
                    sousmenu();
                    break;
                case 8:// quitter

                    break;


                default:
                    System.out.println(" ---ERREUR ---  Choix Non Valide !");
            }
        }
        while (a != 8);


    }


    private void sousmenu() throws Exception{

        int choix;
        do {
            System.out.println(" = = = = = = = = = = = = = = = = = = = = = = = Gestion Des Boites E-Mails = = = = = = = = = = = = = = = = = = = = = = = = = ");
            System.out.println("1- Ajout automatique de messages (reçus, envoyés, brouillons) ");
            System.out.println("2- Afficher le contenu d’une boite (en séparant les dossiers, en donnant des infos espace utilisé et espace restant) ");
            System.out.println("3- Envoyer message (une exception doit être générée si une adresse de destinataire n’existe pas dans le répertoire des adresses) ");
            System.out.println("4- Afficher le contenu d’un message (avec l’en-tête) ");
            System.out.println("5- Supprimer un message ");
            System.out.println("6- Archiver un message / ou les messages reçus avant une date d donnée");
            System.out.println("7- Restaurer un message donné (restituer de la corbeille) ");
            System.out.println("8- Répondre à un message ");
            System.out.println("9- Vider un dossier (« spam » ou « envoyés ») ");
            System.out.println("10- Trier les messages par date (le plus récent d’abord), par objet       (ordre alphabétique) ");
            System.out.println("11- Autre requétes");
            System.out.println("12- Quitter");
            System.out.print("Votre Choix ==> ");

            choix = e.nextInt();

            System.out.println(" = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");

            switch (choix) {

                case 1:
                    for (int i = 0; i < 10; i++) {
                        Date d = new Date();
                        if (i < 5) {
                            Message m = new Message("POO"+i,"Contenu de cours ("+i+") ",d,0.1f);
                            if (i < 3) {
                                for (int j = 0; j < BoiteMail.listeBoite.size(); j++) {
                                    BoiteMail.listeBoite.get(j).ajoutRecus(m);
                                }
                            }else {
                                for (int j = 0; j < BoiteMail.listeBoite.size(); j++) {
                                    BoiteMail.listeBoite.get(j).ajoutEnvoye(m);
                                }
                            }
                        }else{
                            HashSet<PieceJointe> e = new HashSet<>();
                            e.add(new PieceJointe("image1.jpg",8));
                            e.add(new PieceJointe("texte.txt",2));
                            MessageAttach ma =new MessageAttach("PYTHON","Contenu de cours ("+i+")",d,10.1f,e);
                            for (int j = 0; j < BoiteMail.listeBoite.size(); j++) BoiteMail.listeBoite.get(j).ajoutBrouillons(ma);

                        }

                    }

                    System.out.println("Messages Créés");


                    break;
                case 2:


                    int indexBoite = BoiteMail.selectionnerB();

                    if (indexBoite >= 0 && indexBoite<BoiteMail.listeBoite.size()) BoiteMail.listeBoite.get(indexBoite).afficheboite();

                    break;
                case 3:

                    System.out.println("Choisir l'adresse expediteur ");
                    for (int i = 0; i < BoiteMail.listeBoite.size(); i++) {
                        AdresseMail x = (AdresseMail) BoiteMail.listeBoite.get(i).getAdresseM();
                        System.out.println(i +"- " + x.getPseudo()+"@" + x.getSite());
                    }
                    System.out.println("Votre Choix : ");
                    indexBoite = e.nextInt();

                    if (indexBoite >= 0 && indexBoite< BoiteMail.listeBoite.size()) {
                        BoiteMail.listeBoite.get(indexBoite).envoyeMSG(BoiteMail.listeBoite.get(indexBoite).creeMsg(),false);
                    }

                    break;
                case 4:

                    indexBoite = BoiteMail.selectionnerB();

                    BoiteMail.listeBoite.get(indexBoite).affichermessage();

                    break;

                case 5:

                    indexBoite = BoiteMail.selectionnerB();

                    System.out.println("1- Supprimer un message vers la Corbeille ");
                    System.out.println("2- Supprimer un message Définitivment ");
                    System.out.print("Votre Choix : ");
                    int u = e.nextInt();

                    if (u == 1) BoiteMail.listeBoite.get(indexBoite).supprVersCorbeille();
                    else BoiteMail.listeBoite.get(indexBoite).supprimerCorbeille();


                    break;

                case 6:

                    indexBoite = BoiteMail.selectionnerB();
                    int archv;
                    do {
                        System.out.println("1 - Archiver un message ");
                        System.out.println("2 - Archiver des messages avant une date donnée ");
                        System.out.print("Votre choix : ");
                        archv =  e.nextInt();
                        if (archv != 1 && archv != 2) System.out.println("Choix non Valide !");
                    }while (archv != 1 && archv != 2);

                    if (archv == 1) {
                        BoiteMail.listeBoite.get(indexBoite).archive();
                    }else BoiteMail.listeBoite.get(indexBoite).archiveDate();



                    break;

                case 7:

                    indexBoite = BoiteMail.selectionnerB();

                    BoiteMail.listeBoite.get(indexBoite).restaurerMsg();

                    break;

                case 8:

                    indexBoite = BoiteMail.selectionnerB();

                    BoiteMail.listeBoite.get(indexBoite).repondreMsg();

                    break;

                case 9:

                    indexBoite = BoiteMail.selectionnerB();

                    int v;
                    do {
                        System.out.println("Choisir le dossier ");
                        System.out.println("1 - Vider Dossier SPAM ");
                        System.out.println("2 - Vider Dossier Envoyés ");
                        System.out.print("Votre Choix : ");
                        v = e.nextInt();
                        if (v != 1 && v != 2) System.out.println("Choix non Valide !");
                    }while (v != 1 && v != 2);

                    if (v == 1) {
                        BoiteMail.listeBoite.get(indexBoite).viderSpam();
                    }else BoiteMail.listeBoite.get(indexBoite).viderEnvoye();


                    break;

                case 10:

                    indexBoite = BoiteMail.selectionnerB();

                    BoiteMail.listeBoite.get(indexBoite).triDossier();

                    break;

                case 11:

                    autreRequetes();

                    break;

                case 12:

                    break;

                default:
                    System.out.println("Choix non valide !");
                    break;

            }

        }while (choix != 12);
    }

    void autreRequetes() {


        Scanner scan = new Scanner(System.in);
        int choix;

        do {
            System.out.println(" = = = = = = = = = = = = = = = = = = = Requetes Supplémentaire = = = = = = = = = = = = = = = = = = = = = = = = ");
            System.out.println("1 - Afficher toutes les boites (adresses mail) ayant recu un message donné (on donnera l'objet du message) ");
            System.out.println("2 - Afficher les boites qui sont remplis à plus de 50% de leur capacité ");
            System.out.println("3 - Eclater la colllection des boites en deux Collections : (Boites publique, Boites Professionnelles ");
            System.out.println("4 - Pour un site donné, calculer le pourcentage d'utilisation par la catégorie d'age comprise entre 18 et 35");
            System.out.println("5 - Afficher les messages ayant des piéces jointes (pour une boite)");
            System.out.println("6 - Rchhercher les messages par destinataire, par expéditeur, par mot clé");
            System.out.println("7 - Afficher les noms, prénoms des profiles ayant au moins deux boites de messagerie");
            System.out.println("8 - Vider toutes les boites d'un site donné (piratage de site)");
            System.out.println("9 - Quitter");
            System.out.print("- Votre Choix ==> ");
            choix = scan.nextInt();
            System.out.println(" = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = ");
            switch (choix) {
                case 1:

                    System.out.print("Saisir l'objet de message : ");
                    String obj = scan.next();

                    for (BoiteMail box: BoiteMail.listeBoite) {
                        boolean existe = false;
                        for (Message msg: box.getRecus()) {
                            if (msg.getObjet().equals(obj)) {
                                existe = true;
                                break;
                            }
                        }
                        AdresseMail x = (AdresseMail) box.getAdresseM();
                        if (existe) System.out.println("- " + x.getadr());
                    }

                    break;
                case 2:

                    System.out.println("Les boites remplis > 50 : ");
                    for (BoiteMail box: BoiteMail.listeBoite) {
                        AdresseMail x = (AdresseMail) box.getAdresseM();
                        if (box.getPourcentageUtilise() > 50) System.out.printf("- " + x.getadr() + " Utilisation : %.2f %% \n",box.getPourcentageUtilise());
                    }


                    break;

                case 3:

                    String vide = "";
                    for (BoiteMail box: BoiteMail.listeBoite) {
                        if (box.getAdresseM() instanceof AdresseMailProf) boiteProf.add(box);
                        else boitePublic.add(box);
                    }
                    System.out.println("Boites Eclaté ");
                    if (boitePublic.isEmpty()) vide = "<VIDE>";
                    System.out.println("Les Boites Public : " + vide);
                    vide = "";
                    for (BoiteMail pub: boitePublic) {
                        AdresseMail x = (AdresseMail) pub.getAdresseM();
                        System.out.println("- " + x.getadr());
                    }
                    if (boiteProf.isEmpty()) vide = "<VIDE>";
                    System.out.println("Les boites Professionelles : " + vide);
                    for (BoiteMail pro: boiteProf) {
                        AdresseMail x = (AdresseMail) pro.getAdresseM();
                        System.out.println("- " + x.getadr());
                    }

                    break;

                case 4:
                    String site;
                    System.out.print("Saisir un site : ");
                    site = scan.next();

                    float pourcentage = BoiteMail.pourcentageSite(site);

                    System.out.printf("Pourcentage D'utilisation par l'age entre 18 et 35 est : %.2f %% \n", pourcentage);


                    break;

                case 5:

                    int indexBoite = BoiteMail.selectionnerB();

                    AdresseMail x = (AdresseMail) BoiteMail.listeBoite.get(indexBoite).getAdresseM();
                    System.out.println("Les messages avec Attachment(s) de l'Adresse " + x.getadr() + " : ");
                    BoiteMail.listeBoite.get(indexBoite).afficheMsgAttach();

                    break;

                case 6:

                    int r;
                    boolean existe = false;

                    do {
                        System.out.println("1- Rechercher les messages par un destinataire");
                        System.out.println("2- Rechercher les messages par un expéditeur");
                        System.out.println("3- Rechercher les messages par un mot clé");
                        System.out.print("Votre choix : ");
                        r = scan.nextInt();

                        if (r < 1 || r > 3) System.out.println("Choix non Valide ! ");
                    }while (r < 1 || r > 3);

                    switch (r) {

                        case 1:

                            System.out.print("Saisir l'adresse de destinataire : ");
                            String adr = scan.next();

                            for (BoiteMail box: BoiteMail.listeBoite) {
                                AdresseMail y = (AdresseMail) box.getAdresseM();
                                if (y.getadr().equals(adr)) {
                                    for (Message msg: box.getRecus()) msg.affiche();
                                    existe = true;
                                    break;
                                }
                            }

                            if (!existe) System.out.println("Aucun message trouvé ");


                            break;
                        case 2:

                            existe = false;
                            System.out.print("Saisir l'adresse d'un expéditeur : ");
                            adr = scan.next();

                            for (BoiteMail box: BoiteMail.listeBoite) {
                                AdresseMail y = (AdresseMail) box.getAdresseM();
                                if (y.getadr().equals(adr)) {
                                    for (Message msg: box.getEnvoye()) msg.affiche();
                                    existe = true;
                                    break;
                                }
                            }

                            if (!existe) System.out.println("Aucun message trouvé ");

                            break;
                        case 3:

                            System.out.println("Saisir un mot clé : ");
                            String mot = scan.next();

                            for (BoiteMail box: BoiteMail.listeBoite) box.recherchemotcle(mot);

                            break;
                    }


                    break;

                case 7:

                    System.out.println("Les Profils ayant au moins deux boites : ");

                    Hashtable<Profil,Integer> dictionnaire = new Hashtable<>();

                    for (BoiteMail box: BoiteMail.listeBoite) {
                        x = (AdresseMail) box.getAdresseM();
                        if (!dictionnaire.containsKey(x.getP())) dictionnaire.put(x.getP(),1);
                        else dictionnaire.put(x.getP(), dictionnaire.get(x.getP()) + 1);
                    }

                    Set key = dictionnaire.keySet();
                    Iterator itr = key.iterator();
                    while (itr.hasNext()) {
                        Profil p = (Profil) itr.next();
                        if (dictionnaire.get(p) > 1) System.out.println("- " + p.getNom().toUpperCase() + " " + p.getPrenom());
                    }

                    break;

                case 8:

                    System.out.print("Saisir un site : ");
                    site = scan.next();

                    BoiteMail.piratagesite(site);

                    break;

                case 9:

                    break;

                default:
                    System.out.println("Choix non valide !");
                    break;
            }


        }while (choix != 9);












    }



    public static void main(String[] args) throws Exception {


        AppMessagerie app = new AppMessagerie();
        app.menu();


    }
}