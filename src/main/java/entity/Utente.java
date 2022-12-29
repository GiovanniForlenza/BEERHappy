package entity;

import java.util.List;

public class Utente {

    int id;
    String email;
    String password;
    String nome;
    String cognome;
    List<Carta> carte;
    List <Indirizzo> indirizzi;
    Carrello carrello;

    public Utente(){}
    public Utente(int id, String email, String password, String nome, String cognome, List<Carta> carte, List<Indirizzo> indirizzi, Carrello carrello) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.carte = carte;
        this.indirizzi = indirizzi;
        this.carrello = carrello;
    }

    public int getId() {
        return id;
    }
    public String getEmail() {return email;}
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
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
    public List<Carta> getCarte() {
        return carte;
    }
    public void setCarte(List<Carta> carte) {
        this.carte = carte;
    }
    public List<Indirizzo> getIndirizzi() {
        return indirizzi;
    }
    public void setIndirizzi(List<Indirizzo> indirizzi) {
        this.indirizzi = indirizzi;
    }
    public Carrello getCarrello() {
        return carrello;
    }
    public void setCarrello(Carrello carrello) {
        this.carrello = carrello;
    }

    @Override
    public String toString() {
        return "Utente{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", carte=" + carte +
                ", indirizzi=" + indirizzi +
                ", carrello=" + carrello +
                '}';
    }
}
