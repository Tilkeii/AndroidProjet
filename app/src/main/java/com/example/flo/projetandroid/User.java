package com.example.flo.projetandroid;

import android.support.v7.widget.RecyclerView;

import java.io.Serializable;

public class User implements Serializable {
    private String userId;
    private String prenom;
    private String nom;
    private String mobile;

    public User(){ } // Needed for Firebase

    public User(String userId, String prenom, String nom, String mobile) {
        this.userId = userId;
        this.prenom = prenom;
        this.nom = nom;
        this.mobile = mobile;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", prenom='" + prenom + '\'' +
                ", nom='" + nom + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}
