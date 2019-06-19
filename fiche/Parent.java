package fiche;

import connexion.Echange;
import exceptions.DataExceptions;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parent extends Ami {

    private String dateNaiss;

//    public Parent(String nom, String prenom, String tel) {
//        super(nom, prenom, tel);
//        this.type = 2;
//        this.genre = "Parent";
//    }

    public Parent() {
        this.type = 2;
        this.genre = "Parent";
    }
//
//    public Parent(String nom, String prenom, String adresse, String codePostal, String ville, String tel, String mail, String mobile, String dateNaiss, int actif) {
//        super(nom, prenom, adresse, codePostal, ville, tel, mail, mobile, actif);
//        this.dateNaiss = dateNaiss;
//        this.type = 2;
//        this.genre = "Parent";
//    }
//
//    public Parent(String nom, String prenom, String adresse, String codePostal, String ville, String tel, String mail, String mobile, String dateNaiss, int actif, int ID) {
//        super(nom, prenom, adresse, codePostal, ville, tel, mail, mobile, actif, ID);
//        this.dateNaiss = dateNaiss;
//        this.type = 2;
//        this.genre = "Parent";
//    }

    public void save(Echange echange, String value) {
        Statement stat = null;
        String str = "";

        try {
            stat = echange.getConnexion().createStatement();
            String query = "";
            if (this.ID == 0) {
                query = "INSERT INTO contactAuto "
                        + "(type, nom, prenom, adresse, code, ville, tel, mail,"
                        + " mobile, dateNaiss, actif) VALUES ";
                query += value;
            } else {
                query = "UPDATE contactAuto "
                        + value + " WHERE ID = " + this.ID;
            }

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
        System.out.println(str);
    }

    public String genValue() {
        String str = "";
        if (this.ID == 0) {
            str += "(";
            //str += ID + ", ";
            str += type + ", ";
            str += "'" + nom + "', ";
            str += "'" + prenom + "', ";
            str += "'" + adresse + "', ";
            str += "'" + codePostal + "', ";
            str += "'" + ville + "', ";
            str += "'" + tel + "', ";
            str += "'" + mail;
            str += "', '" + mobile + "', '";
            str += dateNaiss;
            str += "', " + actif + ")";
        } else {
            str += "SET type = " + type + ", ";
            str += " nom = '" + nom + "', ";
            str += " prenom = '" + prenom + "', ";
            str += " adresse = '" + adresse + "', ";
            str += " code = '" + codePostal + "', ";
            str += " ville = '" + ville + "', ";
            str += " tel = '" + tel + "', ";
            str += " mail = '" + mail + "', ";
            str += " mobile = '" + mobile + "', ";
            str += " dateNaiss = '" + dateNaiss + "', ";
            str += " actif = " + actif;
        }

        return str;
    }

//    @Override
//    public String toString() {
//        return super.toString() + "\nDate de Naissance : " + dateNaiss;
//    }
    public String getDateNaiss() {
        if (dateNaiss == null) {
            return "";
        } else {
            return dateNaiss;
        }
    }

    public void setDateNaiss(String dateNaiss) throws DataExceptions {
        if (dateNaiss.isEmpty()) {
            this.dateNaiss = dateNaiss;
        } else {
            Pattern p = Pattern.compile("[0-9/]{10}");
            Matcher m = p.matcher(dateNaiss);
            if (m.matches()) {
                this.dateNaiss = dateNaiss;
            } else {
                throw new DataExceptions(1092, "La date de naissance doit contenir des chiffres "
                        + "et des / au format jj/mm/aaaa", "Date de Naissance");
            }
        }
    }

    public void transform(Connaissance fiche) throws DataExceptions {
        super.transform(fiche);
        if (fiche.getType() == 1) {
            Ami ficheA = (Ami) fiche;
            this.setMobile(ficheA.getMobile());
        }
    }

}
