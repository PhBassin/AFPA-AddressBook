package fiche;

import connexion.Echange;
import exceptions.DataExceptions;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.*;

public class Connaissance {

    protected int type = 0;
    protected String genre = "Connaissance";
    protected int actif;
    protected int ID;

    protected String nom;
    protected String prenom;
    protected String adresse;
    protected String codePostal;
    protected String ville;
    protected String tel;
    protected String mail;

//    public Connaissance(String nom, String prenom, String tel) {
//        this.nom = nom;
//        this.prenom = prenom;
//        this.tel = tel;
//        this.actif = 1;
//
//    }

    public Connaissance() {
        this.actif = 1;

    }
//
//    public Connaissance(String nom, String prenom, String adresse, String codePostal, String ville, String tel, String mail) {
//        this.nom = nom;
//        this.prenom = prenom;
//        this.adresse = adresse;
//        this.codePostal = codePostal;
//        this.ville = ville;
//        this.tel = tel;
//        this.mail = mail;
//
//    }

//    public Connaissance(String nom, String prenom, String adresse, String codePostal, String ville, String tel, String mail, int actif) {
//        this.nom = nom;
//        this.prenom = prenom;
//        this.adresse = adresse;
//        this.codePostal = codePostal;
//        this.ville = ville;
//        this.tel = tel;
//        this.mail = mail;
//        this.actif = actif;
//    }

//    public Connaissance(String nom, String prenom, String adresse, String codePostal, String ville, String tel, String mail, int actif, int ID) {
//        this.nom = nom;
//        this.prenom = prenom;
//        this.adresse = adresse;
//        this.codePostal = codePostal;
//        this.ville = ville;
//        this.tel = tel;
//        this.mail = mail;
//        this.actif = actif;
//        this.ID = ID;
//
//    }

    public void changeActif(Echange echange) {
        Statement stat = null;
        String str = "";

        try {
            stat = echange.getConnexion().createStatement();
            String query = "";

            query += "UPDATE contactAuto "
                    + "SET actif = 0 WHERE ID = " + this.ID;

            System.out.println(query);
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

            //System.out.println(query);
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
            str = "(";
            //str += ID + ", ";
            str += type + ", ";
            str += "'" + nom + "', ";
            str += "'" + prenom + "', ";
            str += "'" + adresse + "', ";
            str += "'" + codePostal + "', ";
            str += "'" + ville + "', ";
            str += "'" + tel + "', ";
            str += "'" + mail;
            str += "','' ,'' , " + actif + ")";
        } else {
            str += "SET type = " + type + ", ";
            str += " nom = '" + nom + "', ";
            str += " prenom = '" + prenom + "', ";
            str += " adresse = '" + adresse + "', ";
            str += " code = '" + codePostal + "', ";
            str += " ville = '" + ville + "', ";
            str += " tel = '" + tel + "', ";
            str += " mail = '" + mail + "', ";
            str += " actif = " + actif;
        }
        return str;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getType() {
        return type;
    }

    public String getGenre() {
        return genre;
    }

    public int getActif() {
        return actif;
    }

    public void setActif(int actif) {
        this.actif = actif;
    }

    @Override
//    public String toString() {
//        return "\nID : " + ID
//                + "\nType : " + genre + "\nNom : " + nom
//                + "\nPrenom : " + prenom
//                + "\nAdresse : " + this.getAdresse()
//                + "\nCode Postal : " + this.getCodePostal()
//                + "\nVille : " + this.getVille()
//                + "\nTel : " + this.getTel()
//                + "\nMail : " + this.getMail();
//    }

    public String toString() {
        return "nom : " + nom + " || prénom : " + prenom + " || tel : " + tel;
    }

    public String getNom() {
        if (nom == null) {
            return "";
        } else {
            return nom;
        }
    }

    public void setNom(String nom) throws DataExceptions {
        Pattern p = Pattern.compile("[- 'éèêàûôa-zA-Z]{1,100}");
        Matcher m = p.matcher(nom);
        if (nom.isEmpty()) {
            throw new DataExceptions(1011, "Le nom ne peut pas être vide", "Nom");
        } else if (m.matches()) {
            this.nom = nom;
        } else {
            throw new DataExceptions(1012, "Le nom ne peut contenir que des lettres", "Nom");
        }

    }

    public String getPrenom() {
        if (prenom == null) {
            return "";
        } else {
            return prenom;
        }
    }

    public void setPrenom(String prenom) throws DataExceptions {
        Pattern p = Pattern.compile("[- 'éèêàûôa-zA-Z]{1,100}");
        Matcher m = p.matcher(prenom);
        if (prenom.isEmpty()) {
            throw new DataExceptions(1021, "Le prenom ne peut pas être vide", "Prenom");
        } else if (m.matches()) {
            this.prenom = prenom;
        } else {
            throw new DataExceptions(1022, "Le prenom ne peut contenir que des lettres", "Prenom");
        }

    }

    public String getAdresse() {
        if (adresse == null) {
            return "";
        } else {
            return adresse;
        }
    }

    public void setAdresse(String adresse) throws DataExceptions {
        Pattern p = Pattern.compile("[-' /\\éèêàûôa-zA-Z0-9]{0,500}");
        Matcher m = p.matcher(adresse);
        if (m.matches()) {
            this.adresse = adresse;
        } else {
            throw new DataExceptions(1042, "L'adresse ne peut contenir que des lettres, des chiffres ou être vide", "Adresse");
        }
    }

    public String getCodePostal() {
        if (codePostal == null) {
            return "";
        } else {
            return codePostal;
        }
    }

    public void setCodePostal(String codePostal) throws DataExceptions {
        Pattern p = Pattern.compile("[0-9]{0,5}");
        Matcher m = p.matcher(codePostal);
        if (m.matches()) {
            this.codePostal = codePostal;
        } else {
            throw new DataExceptions(1052, "Le code postal ne peut contenir que des chiffres", "Code Postal");
        }
    }

    public String getVille() {
        if (ville == null) {
            return "";
        } else {
            return ville;
        }
    }

    public void setVille(String ville) throws DataExceptions {
        Pattern p = Pattern.compile("[-' /\\éèêàûôa-zA-Z]{0,50}");
        Matcher m = p.matcher(ville);
        if (m.matches()) {
            this.ville = ville;
        } else {
            throw new DataExceptions(1062, "La ville ne peut contenir que des lettres", "Ville");
        }
    }

    public String getTel() {
        if (tel == null) {
            return "";
        } else {
            return tel;
        }
    }

    public void setTel(String tel) throws DataExceptions {
        Pattern p = Pattern.compile("[0-9+][0-9 ]{9,14}");
        Matcher m = p.matcher(tel);
        if (nom.isEmpty()) {
            throw new DataExceptions(1031, "Le tel ne peut pas être vide", "Téléphone");
        } else if (m.matches()) {
            this.tel = tel;
        } else {
            throw new DataExceptions(1032, "Le tel doit contenir des chiffres "
                    + "et/ou des espaces et + pour le format international", "Téléphone");
        }

    }

    public String getMail() {
        if (mail == null) {
            return "";
        } else {
            return mail;
        }
    }

    public void setMail(String mail) throws DataExceptions {
        if (mail.isEmpty()) {
            this.mail = "";
        } else {
            Pattern p = Pattern.compile("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
            Matcher m = p.matcher(mail);
            if (m.matches()) {
                this.mail = mail;
            } else {
                throw new DataExceptions(1072, "L'email doit être valide ", "Mail");
            }
        }

    }

    public void transform(Connaissance fiche) throws DataExceptions {
        this.setNom(fiche.getNom());
        this.setPrenom(fiche.getPrenom());
        this.setAdresse(fiche.getAdresse());
        this.setCodePostal(fiche.getCodePostal());
        this.setVille(fiche.getVille());
        this.setTel(fiche.getTel());
        this.setMail(fiche.getMail());
        this.setID(fiche.getID());

    }

}
