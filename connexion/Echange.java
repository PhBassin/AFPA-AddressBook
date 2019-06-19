package connexion;

import carnet.Carnet;
import fiche.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Echange {

    private Connection connexion;
    private String server = "localhost:1433";
    private String databaseName = "CARNET";
    private String connectionUrl = "jdbc:sqlserver://" + server
            + ";databaseName=" + databaseName
            + ";user=sa;password=sa";

    public Echange() {

    }

    public Connection getConnexion() {
        return connexion;
    }


    

    public String wipe() {
        String str = "";
        Statement stat = null;
        try {
            stat = connexion.createStatement();
            String query = "TRUNCATE TABLE contactAuto";

            stat.executeUpdate(query);

        } catch (SQLException ex) {
            str += "\n" + ex.getErrorCode() + " / " + ex.getMessage();
        } finally {
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex) {
                    str += "\n" + ex.getErrorCode() + " / " + ex.getMessage();
                }
            }
        }
        return str;
    }

    public void connect() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException ex) {
            System.err.println("Oups : ClassNotFoundException : " + ex.getMessage());
        }

        try {
            connexion = DriverManager.getConnection(connectionUrl);
        } catch (SQLException ex) {
            System.err.println("Oups : connexion : " + ex.getMessage());
        }
    }

    public String close() {

        String str = "";
        try {
            connexion.close();
        } catch (SQLException ex) {
            str += "\nClose : " + ex.getMessage();
        }

        return str;
    }

//    public String save(String value) {
//        Statement stat = null;
//        String str = "";
//
//        try {
//            stat = connexion.createStatement();
//            String query = "INSERT INTO contact VALUES ";
//            query += value;
//
//            stat.executeUpdate(query);
//
//        } catch (SQLException ex) {
//            str += "\n" + ex.getErrorCode() + " / " + ex.getMessage();
//        } finally {
//            if (stat != null) {
//                try {
//                    stat.close();
//                } catch (SQLException ex) {
//                    str += "\n" + ex.getErrorCode() + " / " + ex.getMessage();
//                }
//            }
//        }
//        return str;
//    }
//
//    public String genValue(Connaissance fiche) {
//        String str = "(";
//        str += fiche.getID() + ", ";
//        str += fiche.getType() + ", ";
//        str += "'" + fiche.getNom() + "', ";
//        str += "'" + fiche.getPrenom() + "', ";
//        str += "'" + fiche.getAdresse() + "', ";
//        str += "'" + fiche.getCodePostal() + "', ";
//        str += "'" + fiche.getVille() + "', ";
//        str += "'" + fiche.getTel() + "', ";
//        str += "'" + fiche.getMail();
//        if (fiche.getType() == 1) {
//            Ami ficheA = (Ami) fiche;
//            str += "', '" + ficheA.getMobile();
//            str += "','', " + ficheA.getActif() + ")";
//        } else if (fiche.getType() == 2) {
//            Parent ficheP = (Parent) fiche;
//            str += "', '" + ficheP.getMobile() + "', '";
//
//            str += ficheP.getDateNaiss();
//            str += "', " + ficheP.getActif() + ")";
//        } else {
//            str += "','' ,'' , " + fiche.getActif() + ")";
//        }
//
//        return str;
//    }
//
//    public void saveAll(Carnet carnet) {
//        for (int i = 0; i < carnet.taille(); i++) {
//
//            save(genValue(carnet.recup(i)));
//        }
//    }

//    public void restore(Carnet carnet) {
//        Statement stat = null;
//
//        try {
//            stat = connexion.createStatement();
//            String query = "SELECT ID, type, nom, prenom, adresse, code, ville"
//                    + ", tel, mail, mobile, dateNaiss, actif FROM contactAuto";
//
//            ResultSet rs = stat.executeQuery(query);
//            while (rs.next()) {
//                System.out.println(rs.getInt("type"));
//                int n = rs.getInt("type");
//                switch (n) {
//                    case 0:
//                        carnet.ajout(new Connaissance(
//                                rs.getString("nom"),
//                                rs.getString("prenom"),
//                                rs.getString("adresse"),
//                                rs.getString("code"),
//                                rs.getString("ville"),
//                                rs.getString("tel"),
//                                rs.getString("mail"),
//                                rs.getInt("actif")
//                                ,rs.getInt("ID")
//                        ));
//                        break;
//
//                    case 1:
//                        carnet.ajout(new Ami(
//                                rs.getString("nom"),
//                                rs.getString("prenom"),
//                                rs.getString("adresse"),
//                                rs.getString("code"),
//                                rs.getString("ville"),
//                                rs.getString("tel"),
//                                rs.getString("mail"),
//                                rs.getString("mobile"),
//                                rs.getInt("actif")
//                                ,rs.getInt("ID")
//                        ));
//                        break;
//
//                    case 2:
//                        carnet.ajout(new Parent(
//                                rs.getString("nom"),
//                                rs.getString("prenom"),
//                                rs.getString("adresse"),
//                                rs.getString("code"),
//                                rs.getString("ville"),
//                                rs.getString("tel"),
//                                rs.getString("mail"),
//                                rs.getString("mobile"),
//                                rs.getString("dateNaiss"),
//                                rs.getInt("actif")
//                                ,rs.getInt("ID")
//                        ));
//                        break;
//                }
//
//            }
//
//        } catch (SQLException ex) {
//            System.err.println("Oups : SQL : " + ex.getErrorCode() + " / " + ex.getMessage());
//        } finally {
//            if (stat != null) {
//                try {
//                    stat.close();
//                } catch (SQLException ex) {
//                    System.out.println("Oups : SQL : " + ex.getErrorCode() + " / " + ex.getMessage());
//                }
//            }
//        }
//    }

}
