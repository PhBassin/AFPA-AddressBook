package gestion;

import fiche.*;
import java.util.Scanner;

public class Saisie {

    public Saisie() {

    }
    Scanner sc = new Scanner(System.in);

    public void afficher(String msg) {
        System.out.print(msg);
    }

    public void afficherErr(String msg) {
        System.err.print(msg);
    }

    public String nom() {

        String str = "";
        while (str.isEmpty()) {
            afficher("Veuillez saisir le nom de la personne : ");
            str = sc.nextLine();
        }
        return str;
    }

    public String prenom() {
        String str = "";
        while (str.isEmpty()) {
            afficher("Veuillez saisir le prenom de la personne : ");
            str = sc.nextLine();
        }
        return str;
    }

    public String adresse() {
        afficher("Veuillez saisir le numéro et la rue de la personne : ");
        return sc.nextLine();
    }

    public String code() {
        afficher("Veuillez saisir le code postal de la personne : ");
        return sc.nextLine();
    }

    public String ville() {
        afficher("Veuillez saisir la ville de la personne : ");
        return sc.nextLine();
    }

    public String tel() {
        String str = "";
        while (str.isEmpty() || str.length() != 10) {
            afficher("Veuillez saisir le numéro de téléphone de la personne : ");
            str = sc.nextLine();

            try {
                int n = Integer.parseInt(str);

            } catch (NumberFormatException e) {
                afficher("Veuillez saisir des entiers\n");
            }

        }
        return str;
    }

    public String mail() {
        String str = "";
        int n;
        do {
            afficher("Veuillez saisir l'adresse email de la personne : ");
            str = sc.nextLine();
            String[] split = str.split("@");
            n = split.length;
            if (!str.isEmpty() && n != 2) {
                afficher("Veuillez saisir une adresse mail valide (@)\n");
            }
        } while (n != 2 && !str.isEmpty());
        return str;
    }

    public String mobile() {

            afficher("Veuillez saisir le numéro de mobile de la personne : ");
            return sc.nextLine();

    }

    public String date() {
        afficher("Veuillez saisir la date de naissance de la personne : ");
        return sc.nextLine();
    }

    public Connaissance type() {
        afficher("\nVeuillez choisir le type de contact  : \n\t1 pour Connaissance\n\t2 pour Ami\n\t3 pour Famille\n");
        int t = Integer.parseInt(sc.nextLine());
        if (t == 2) {
            return new Ami();
        } else if (t == 3) {
            return new Parent();
        } else {
            return new Connaissance();
        }

    }

}
