package carnet;

import connexion.Echange;
import exceptions.DataExceptions;
import java.util.Vector;
import fiche.*;
import gestion.Saisie;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Comparator;
import javax.swing.JComboBox;

public class Carnet {

    Vector<Connaissance> list;

    public Carnet() {
        this.list = new Vector();
    }

    public Vector<Connaissance> getList() {
        return list;
    }

    public void ajout(Connaissance fiche) {
        list.add(fiche);
    }

    public void suppr(Connaissance fiche) {
        list.remove(fiche);
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getActif() == 1) {
                str += "\nContact n°" + (i + 1) + list.get(i).toString() + "\n";
            }
        }
        return "Carnet : \n" + str;
    }

    public void updateC(JComboBox jcb) {
        Vector<Connaissance> v = new Vector();
        for (int i = 0; i < jcb.getItemCount(); i++) {
            v.add((Connaissance) jcb.getItemAt(i));
            //System.out.println((Connaissance) jcb.getItemAt(i));
        }
        this.list = v;
    }

    public void triNom() {
        list.sort(new Comparator<Connaissance>() {

            @Override
            public int compare(Connaissance o1, Connaissance o2) {
                return o1.getNom().compareToIgnoreCase(o2.getNom());
            }
        });

    }

    public void triCP() {
        list.sort(new Comparator<Connaissance>() {

            @Override
            public int compare(Connaissance o1, Connaissance o2) {
                if (o1.getCodePostal() == null) {
                    return -1;
                }
                if (o2.getCodePostal() == null) {
                    return 1;
                }
                return o1.getCodePostal().compareToIgnoreCase(o2.getCodePostal());
            }
        });
    }

    public Carnet affConn() {
        Carnet tmp = new Carnet();
        for (Connaissance list1 : list) {
            if (list1.getType() == 0 && list1.getActif() == 1) {
                tmp.ajout(list1);
            }
        }
        return tmp;
    }

    public Carnet affAmi() {
        Carnet tmp = new Carnet();
        for (Connaissance list1 : list) {
            if (list1.getType() == 1 && list1.getActif() == 1) {
                tmp.ajout(list1);
            }
        }
        return tmp;
    }

    public Carnet affParent() {
        Carnet tmp = new Carnet();
        for (Connaissance list1 : list) {
            if (list1.getType() == 2 && list1.getActif() == 1) {
                tmp.ajout(list1);
            }
        }
        return tmp;
    }

    public Vector search(String nom) {
        Vector<Connaissance> contact = new Vector();
        //int nF = -1;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getNom().equalsIgnoreCase(nom)) {
                contact.add(list.get(i));
            }
        }

        return contact;
    }

    public void transform(Connaissance ficheOld, Ami ficheNew) throws DataExceptions {
        ficheNew.transform(ficheOld);
        this.ajout(ficheNew);
        this.suppr(ficheOld);
    }

    public int taille() {
        return list.size();
    }

    public Connaissance recup(int i) {
        return list.get(i);
    }

    public void save(Echange ech) {
        for (int i = 0; i < list.size(); i++) {

            list.get(i).save(ech, list.get(i).genValue());
        }
    }

    public void restore(Echange ech) throws DataExceptions {
        Statement stat = null;

        try {
            stat = ech.getConnexion().createStatement();
            String query = "SELECT ID, type, nom, prenom, adresse, code, ville,"
                    + " tel,mail, mobile, dateNaiss, actif FROM contactAuto WHERE actif = 1 ORDER BY nom, prenom";

            ResultSet rs = stat.executeQuery(query);
            while (rs.next()) {
                System.out.println(rs.getString("nom"));
                int n = rs.getInt("type");
                switch (n) {
                    case 0:
//                        ajout(new Connaissance(
//                                rs.getString("nom"),
//                                rs.getString("prenom"),
//                                rs.getString("adresse"),
//                                rs.getString("code"),
//                                rs.getString("ville"),
//                                rs.getString("tel"),
//                                rs.getString("mail"),
//                                rs.getInt("actif")
//                        ,rs.getInt("ID")
//                        ));
                        Connaissance conn = new Connaissance();
                        conn.setNom(rs.getString("nom"));
                        conn.setPrenom(rs.getString("prenom"));
                        conn.setAdresse(rs.getString("adresse"));
                        conn.setCodePostal(rs.getString("code"));
                        conn.setVille(rs.getString("ville"));
                        conn.setTel(rs.getString("tel"));
                        conn.setMail(rs.getString("mail"));
                        conn.setActif(rs.getInt("actif"));
                        conn.setID(rs.getInt("ID"));
                        ajout(conn);
                        break;

                    case 1:
//                        ajout(new Ami(
//                                rs.getString("nom"),
//                                rs.getString("prenom"),
//                                rs.getString("adresse"),
//                                rs.getString("code"),
//                                rs.getString("ville"),
//                                rs.getString("tel"),
//                                rs.getString("mail"),
//                                rs.getString("mobile"),
//                                rs.getInt("actif")
//                        ,rs.getInt("ID")
//                        ));
                                                Ami ami = new Ami();
                        ami.setNom(rs.getString("nom"));
                        ami.setPrenom(rs.getString("prenom"));
                        ami.setAdresse(rs.getString("adresse"));
                        ami.setCodePostal(rs.getString("code"));
                        ami.setVille(rs.getString("ville"));
                        ami.setTel(rs.getString("tel"));
                        ami.setMail(rs.getString("mail"));
                        ami.setActif(rs.getInt("actif"));
                        ami.setID(rs.getInt("ID"));
                        ami.setMobile(rs.getString("mobile"));
                        ajout(ami);
                        break;

                    case 2:
//                        ajout(new Parent(
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
//                        ,rs.getInt("ID")
//                        ));
                                                Parent parent = new Parent();
                        parent.setNom(rs.getString("nom"));
                        parent.setPrenom(rs.getString("prenom"));
                        parent.setAdresse(rs.getString("adresse"));
                        parent.setCodePostal(rs.getString("code"));
                        parent.setVille(rs.getString("ville"));
                        parent.setTel(rs.getString("tel"));
                        parent.setMail(rs.getString("mail"));
                        parent.setActif(rs.getInt("actif"));
                        parent.setID(rs.getInt("ID"));
                        parent.setMobile(rs.getString("mobile"));
                        parent.setDateNaiss(rs.getString("dateNaiss"));
                        ajout(parent);
                        break;
                }

            }

        } catch (SQLException ex) {
            System.err.println("Oups : SQL : " + ex.getErrorCode() + " / " + ex.getMessage());
        } finally {
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex) {
                    System.err.println("Oups : SQL : " + ex.getErrorCode() + " / " + ex.getMessage());
                }
            }
        }
    }

//    public Connaissance nouvelFiche(Saisie tap) {
//
//        Connaissance fiche = tap.type();
//
//        fiche.setNom(tap.nom());
//        fiche.setPrenom(tap.prenom());
//        fiche.setTel(tap.tel());
//        fiche.setAdresse(tap.adresse());
//        fiche.setCodePostal(tap.code());
//        fiche.setVille(tap.ville());
//        fiche.setMail(tap.mail());
//
//        switch (fiche.getType()) {
//            case 1:
//                Ami ami = (Ami) fiche;
//                ami.setMobile(tap.mobile());
//                return ami;
//            case 2:
//                Parent parent = (Parent) fiche;
//                parent.setMobile(tap.mobile());
//                parent.setDateNaiss(tap.date());
//                return parent;
//            default:
//                return fiche;
//        }
//    }

}
