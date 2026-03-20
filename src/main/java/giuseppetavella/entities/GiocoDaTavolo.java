package giuseppetavella.entities;

import giuseppetavella.enums.CollezioneItemType;
import giuseppetavella.exceptions.NumeroGiocatoriOutOfRangeException;

public class GiocoDaTavolo extends Gioco {
    private long numeroGiocatori;
    private long durataMediaPartita;
    
    public GiocoDaTavolo(long idGioco, String titolo, double prezzo, long annoPubblicazione, long numeroGiocatori, long durataMediaPartita) {
        super(idGioco, titolo, CollezioneItemType.GIOCO_DA_TAVOLO, prezzo, annoPubblicazione);
        this.setNumeroGiocatori(numeroGiocatori);
        this.setDurataMediaPartita(durataMediaPartita);
    }

    public long getNumeroGiocatori() {
        return numeroGiocatori;
    }

    public void setNumeroGiocatori(long numeroGiocatori) throws NumeroGiocatoriOutOfRangeException {
        if(!(numeroGiocatori >= 2 && numeroGiocatori <= 10)) {
            throw new NumeroGiocatoriOutOfRangeException("Numero giocatori " + numeroGiocatori + " non è valido.");
        }
        this.numeroGiocatori = numeroGiocatori;
    }

    public long getDurataMediaPartita() {
        return durataMediaPartita;
    }

    public void setDurataMediaPartita(long durataMediaPartita) throws IllegalArgumentException {
        if(durataMediaPartita <= 0) {
            throw new IllegalArgumentException("Durata media partita deve essere > 0. Fornito: " + durataMediaPartita);
        }
        this.durataMediaPartita = durataMediaPartita;
    }
}
