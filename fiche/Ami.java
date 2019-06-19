package fiche;

import connexion.Echange;
import exceptions.DataExceptions;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ami extends Connaissance {

    protected String mobile;

    public Ami() {
        this.type = 1;
        this.genre = "Ami";
    }

//    public Ami(String nom, String prenom, String tel) {
//        super(nom, prenom, tel);
//        this.type = 1;
//        this.genre = "Ami";
//    }

//    public Ami(String nom, String prenom, String adresse, String codePostal, String ville, String tel, String mail, String mobile, int actif) {
//        super(nom, prenom, adresse, codePostal, ville, tel, mail, actif);
//        this.mobile = mobile;
//        this.type = 1;
//        this.genre = "Ami";
//    }
//
//    public Ami(String nom, String prenom, String adresse, String codePostal, String ville, String tel, String mail, String mobile, int actif, int ID) {
//        super(nom, prenom, adresse, codePostal, ville, tel, mail, actif, ID);
//        this.mobile = mobile;
//
//        this.type = 1;
//        this.genre = "Ami";
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
            str += "', '" + mobile;
            str += "','', " + actif + ")";
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
            str += " actif = " + actif;
        }

        return str;
    }

//    @Override
//    public String toString() {
//        return super.toString() + "\nMobile : " + mobile;
//    }
    public String getMobile() {
        if (mobile == null) {
            return "";
        } else {
            return mobile;
        }
    }

    public void setMobile(String mobile) throws DataExceptions {
        if (mobile.isEmpty()) {
            this.mobile = mobile;
        } else {
            Pattern p = Pattern.compile("[0-9+][0-9 ]{9,14}");
            Matcher m = p.matcher(mobile);
            if (m.matches()) {
                this.mobile = mobile;
            } else {
                throw new DataExceptions(1082, "Le mobile doit contenir des chiffres "
                        + "et/ou des espaces et + pour le format international", "Mobile");
            }
        }
    }

    public void transform(Connaissance fiche) throws DataExceptions {
        super.transform(fiche);
        if (fiche.getType() == 2) {
            Parent ficheP = (Parent) fiche;
            this.setMobile(ficheP.getMobile());
        }

    }
}
