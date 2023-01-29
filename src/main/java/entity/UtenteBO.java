package entity;

public class UtenteBO {
    String email;
    String password;
    int ruolo;

    public UtenteBO(){
    }
    public UtenteBO(String email, String password, int ruolo){
        this.email=email;
        this.password=password;
        this.ruolo=ruolo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRuolo() {
        return ruolo;
    }

    public void setRuolo(int ruolo) {
        this.ruolo = ruolo;
    }
}
