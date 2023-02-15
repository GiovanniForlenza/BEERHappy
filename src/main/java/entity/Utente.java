package entity;

import java.util.ArrayList;

public class Utente {

    int id;
    String email;
    String password;
    String nome;
    String cognome;
    ArrayList<Carta> carte;
    ArrayList<Indirizzo> indirizzi;
    ArrayList<Ordine> ordini;
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
    public void removeIndirizzo(int indirizzoID){
        for(int i=0; i<indirizzi.size(); i++){
            if(indirizzi.get(i).getID()==indirizzoID){
                indirizzi.remove(i);
                System.out.println("IndirizzoRimosso");
            }
        }

    }

    public void removeCarta(int cartaID){
        for(int i=0; i<carte.size(); i++){
            if(carte.get(i).getId()==cartaID){
                carte.remove(i);
                System.out.println("IndirizzoRimosso");
            }
        }
    }

    public void addCarta(Carta carta) {
        if (this.carte == null)
            this.carte = new ArrayList<>();

        this.carte.add(carta);

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

	public void addOrdine(Ordine ordine) {
        if(this.ordini == null) {
            this.ordini = new ArrayList<>();
        }

        this.ordini.add(ordine);
	}

    public ArrayList<Ordine> getOrdini() {
        return ordini;
    }

    public void setOrdini(ArrayList<Ordine> ordini) {
        this.ordini = ordini;
    }

    public void annullaOrdine(int idOrdine){
        for(int i=0; i<ordini.size(); i++){
            if(ordini.get(i).getIdOrdine()==idOrdine){
                ordini.get(i).setStato(Stato.annullato);
            }
        }

    }


}
