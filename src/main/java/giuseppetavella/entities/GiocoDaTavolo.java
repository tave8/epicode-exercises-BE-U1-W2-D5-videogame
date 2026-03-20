package giuseppetavella.entities;

public class GiocoDaTavolo extends Gioco {
    private long numeroGiocatori;
    private long durataMediaPartita;
    
    public GiocoDaTavolo(long idGioco, String titolo, double prezzo, long annoPubblicazione, long numeroGiocatori, long durataMediaPartita) {
        super(idGioco, titolo, prezzo, annoPubblicazione);
        this.setNumeroGiocatori(numeroGiocatori);
        this.setDurataMediaPartita(durataMediaPartita);
    }

    public long getNumeroGiocatori() {
        return numeroGiocatori;
    }

    public void setNumeroGiocatori(long numeroGiocatori) {
        this.numeroGiocatori = numeroGiocatori;
    }

    public long getDurataMediaPartita() {
        return durataMediaPartita;
    }

    public void setDurataMediaPartita(long durataMediaPartita) {
        this.durataMediaPartita = durataMediaPartita;
    }
}
