package gestion;

import fiche.Connaissance;
import java.util.Vector;

public class Menu {

    public String accueil() {
        String str = "\nBienvenue dans votre carnet d'adresse";
        str += "\nQuelle action voulez vous effectuer ?";
        str += "\n\t1 lister tous les contacts";
        str += "\n\t2 lister un type de contact";
        str += "\n\t3 ajouter un contact";
        str += "\n\t4 modifier un contact";
        str += "\n\t5 modifier le type d'un contact";
        str += "\n\t6 supprimer un contact";
        str += "\n\t0 quitter\n";

        return str;
    }

    public String type() {
        String str = "\nQuel type de contact voulez vous afficher ?";
        str += "\n\t1 Connaissance";
        str += "\n\t2 Ami";
        str += "\n\t3 Famille";
        str += "\n\t0 Quitter\n";

        return str;
    }

    public String modif(int i) {
        String str = "\nQuel est le champs que vous souhaitez modifier ?";
        str += "\n\t1 Nom";
        str += "\n\t2 Prenom";
        str += "\n\t3 Adresse";
        str += "\n\t4 Code Postal";
        str += "\n\t5 Ville";
        str += "\n\t6 Téléphone";
        str += "\n\t7 Email";
        if (i == 1 || i == 2) {
            str += "\n\t8 Mobile";
        }
        if (i == 2) {
            str += "\n\t9 Date de Naissance";
        }
        str += "\n\t0 Quitter\n";

        return str;
    }

    public String liste() {
        String str = "\nComment voulez vous afficher les contacts ?";
        str += "\n\t1 Trier par nom";
        str += "\n\t2 Trier par code postal";
        str += "\n\t0 Quitter\n";

        return str;
    }

    public String transf() {
        String str = "\nQuel changement voulez vous apporter ?";
        str += "\n\t1 Connaissance vers Ami";
        str += "\n\t2 Ami vers Parent";
        str += "\n\t0 Quitter\n";

        return str;
    }

    public String qui(Vector<Connaissance> contact) {
        String str = "";
        int i = 1;
        for (Connaissance cont : contact) {

            str += "\n\t" + i + " nom : " + cont.getNom() + " Prenom : " + cont.getPrenom() + " Tel : " + cont.getTel();
            i++;
        }
        str += "\n";
        return str;
    }
}
