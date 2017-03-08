package it.unica.euforici.ragazzieuforici;

import java.io.Serializable;

public class Dati implements Serializable {

    private String nome;
    private String cognome;
    private String indirizzo;
    private String telefono;
    private String email;

    public Dati() {

        this.nome = "";
        this.cognome = "";
        this.indirizzo = "";
        this.telefono = "";
        this.email = "";
    }

    public Dati (String nome, String cognome, String indirizzo, String telefono, String email) {
        this.setNome(nome);
        this.setCognome(cognome);
        this.setIndirizzo(indirizzo);
        this.setTelefono(telefono);
        this.setMail(email);
    }

    /* GETter & SETter */

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMail() {
        return email;
    }

    public void setMail(String email) {
        this.email = email;
    }


}