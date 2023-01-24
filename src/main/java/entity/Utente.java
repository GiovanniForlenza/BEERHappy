package entity;

import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.ArrayList;
import java.util.List;

public class Utente {

    int id;
    String email;
    String password;
    String nome;
    String cognome;
    ArrayList<Carta> carte;
    ArrayList<Indirizzo> indirizzi;
    Carrello carrello;

    public Utente(){}
    public Utente(int id, String email, String password, String nome, String cognome, ArrayList<Carta> carte, ArrayList<Indirizzo> indirizzi, Carrello carrello) {
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
    public ArrayList<Carta> getCarte() {
        return carte;
    }
    public void setCarte(ArrayList<Carta> carte) {
        this.carte = carte;
    }
    public ArrayList<Indirizzo> getIndirizzi() {
        return indirizzi;
    }
    public void setIndirizzi(ArrayList<Indirizzo> indirizzi) {
        this.indirizzi = indirizzi;
    }

    public void addIndirizzo(Indirizzo indirizzo) {
        if (this.indirizzi == null)
			this.indirizzi = new ArrayList<>();

		this.indirizzi.add(indirizzo);

    }

    public void remove(Indirizzo indirizzo) {
        for(int i=0; i < indirizzi.size(); i++) {
            if (indirizzi.get(i).equals(indirizzo)){
                this.indirizzi.remove(indirizzi.get(i));
            }
        }

    }

    public void printIndirizzi(){
        for(int i=0; i < indirizzi.size(); i++)
            System.out.println(indirizzi.get(i).toString());
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
