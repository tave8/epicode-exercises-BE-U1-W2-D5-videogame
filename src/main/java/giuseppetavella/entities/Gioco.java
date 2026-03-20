package giuseppetavella.entities;

public abstract class Gioco {
    private final long idGioco;
    private String titolo;
    private final long annoPubblicazione;
    private double prezzo;
    
    public Gioco(long idGioco, String titolo, double prezzo, long annoPubblicazione) {
        this.idGioco = idGioco;
        this.setTitolo(titolo);
        this.setPrezzo(prezzo);
        this.annoPubblicazione = annoPubblicazione;
    }

    public long getIdGioco() {
        return idGioco;
    }

    public String getTitolo() {
        return titolo;
    }

    public long getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }
}
