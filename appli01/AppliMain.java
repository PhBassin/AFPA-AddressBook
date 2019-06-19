package appli01;

import carnet.Carnet;
import connexion.Echange;
import exceptions.DataExceptions;
import fiche.*;

import gestion.Menu;
import gestion.Saisie;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AppliMain {

    public static void main(String[] args) {
//        Connaissance conn01 = new Connaissance("nom11", "prenom111", "555555511");
//        Connaissance conn04 = new Connaissance("nom11", "prenom112", "555555511");
//        Connaissance conn05 = new Connaissance("nom11", "prenom113", "555555511");
//        Connaissance conn02 = new Connaissance("Barry", "Lindon", "5 Main Street", "99999", "London", "555555512","",1);
//        Connaissance conn03 = new Connaissance("nom13", "prenom13", "555555513");
//
//        Ami ami01 = new Ami("nom21", "prenom21", "555555521");
//        Connaissance ami02 = new Ami("nom22", "prenom22", "555555522");
//        Connaissance ami03 = new Ami("Ceinture", "bretelle", "10 rue Monge", "75015", "Paris", "555555523", "rue@monge.com", "0612457836",1);
//
//        Parent parent01 = new Parent("nom31", "prenom31", "555555531");
//        Connaissance parent02 = new Parent("nom32", "prenom32", "555555532");
//        Connaissance parent03 = new Parent("Rou", "doudou", "45 avenue de la paix", "75001", "paris", "555555533", "", "", "12/12/1878",1);

//        Carnet carn = new Carnet();

//        carn.ajout(conn03);
//        carn.ajout(conn02);
//        carn.ajout(conn01);
//        carn.ajout(conn04);
//        carn.ajout(conn05);
//
//        carn.ajout(ami01);
//        carn.ajout(ami03);
//        carn.ajout(ami02);
//
//        carn.ajout(parent02);
//        carn.ajout(parent03);
//        carn.ajout(parent01);
//        String server = "localhost:1433";
//        String databaseName = "CARNET";
//
//        String connectionUrl = "jdbc:sqlserver://" + server
//                + ";databaseName=" + databaseName
//                + ";user=sa;password=sa";
        //Carnet carn = new Carnet();
//        Echange ech = new Echange();
//        Menu menu = new Menu();
//        Gestion gest = new Gestion();
//        Saisie tap = new Saisie();
//
//        ech.connect();
//        carn.restore(ech);
//        ech.close();
//
//        int n = 0;
//        do {
//            n = gest.saisie(menu.accueil(), 0, 6, tap);
//            gest.choixGnl(n, carn, menu, tap, ech);
//        } while (n != 0);

        Connaissance conn = new Connaissance();
        try {
            conn.setNom("AA");
        } catch (DataExceptions ex) {
            ex.msgPrint();
        }
        System.out.println(conn);
        
    }

}
