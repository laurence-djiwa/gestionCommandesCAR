package com.example.demo.entity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Client {



        @Id
        private String email;

        private String mdp;
        private String nom;
        private String prenom;

        @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
        private List<Commande> commandes = new ArrayList<>();


        public Client(String email, String mdp, String nom, String prenom) {
            this.email = email;
            this.mdp = mdp;
            this.nom = nom;
            this.prenom = prenom;
        }

        public Client() {

        }

        public String getEmail() {
            return email;
        }
        public void setEmail(String email) {
            this.email = email;
        }
        public String getMdp() {
            return mdp;
        }
        public void setMdp(String mdp) {
            this.mdp = mdp;
        }
        public String getNom() {
            return nom;
        }
        public void setNom(String nom) {
            this.nom = nom;
        }
        public String getPrenom() {
            return prenom;
        }
        public void setPrenom(String prenom) {
            this.prenom = prenom;
        }
    }


