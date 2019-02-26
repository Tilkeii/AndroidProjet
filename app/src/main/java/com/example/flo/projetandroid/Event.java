package com.example.flo.projetandroid;

import com.google.firebase.Timestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class Event implements Serializable {
    private String titre;
    private String sport;
    private String lieu;
    private List<String> users;
    private Date date;
    private Date dateLimite;

    public Event(){ } // Needed for Firebase

    public Event(String titre, String sport, String lieu, List<String> users, Date date, Date dateLimite) {
        this.titre = titre;
        this.sport = sport;
        this.lieu = lieu;
        this.users = users;
        this.date = date;
        this.dateLimite = dateLimite;
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDateLimite() {
        return dateLimite;
    }

    public void setDateLimite(Date dateLimite) {
        this.dateLimite = dateLimite;
    }

    @Override
    public String toString() {
        return "Event{" +
                "titre='" + titre + '\'' +
                ", sport='" + sport + '\'' +
                ", lieu='" + lieu + '\'' +
                ", users=" + users +
                ", date=" + date +
                ", dateLimite=" + dateLimite +
                '}';
    }
}
