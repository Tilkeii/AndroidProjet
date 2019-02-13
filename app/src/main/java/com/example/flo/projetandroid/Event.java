package com.example.flo.projetandroid;

import com.google.firebase.Timestamp;

import java.time.LocalDateTime;

public class Event {
    private String titre;
    private String sport;
    private String lieu;
    private Timestamp date;
    private Timestamp dateLimit;

    public Event(){ } // Needed for Firebase

    public Event(String id, String titre, String sport, String lieu, Timestamp date, Timestamp dateLimit) {
        this.titre = titre;
        this.sport = sport;
        this.lieu = lieu;
        this.date = date;
        this.dateLimit = dateLimit;
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

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Timestamp getDateLimit() {
        return dateLimit;
    }

    public void setDateLimit(Timestamp dateLimit) {
        this.dateLimit = dateLimit;
    }

    @Override
    public String toString() {
        return "Event{" +
                ", titre='" + titre + '\'' +
                ", sport='" + sport + '\'' +
                ", lieu='" + lieu + '\'' +
                ", date=" + date +
                ", dateLimit=" + dateLimit +
                '}';
    }
}
