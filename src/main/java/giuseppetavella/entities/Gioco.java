package giuseppetavella.entities;

import giuseppetavella.interfaces.CollezioneItem;

public abstract class Gioco implements CollezioneItem {
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

    /**
     * Each Gioco instance must implement getId()
     * because the interface provides it; 
     * therefore, giocoA.getId() == giocoA.getIdGioco()
     */
    @Override
    public long getId() {
        return this.getIdGioco();
    }
    
    @Override
    public double getPrice() {
        return this.getPrezzo();
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

    @Override
    public String toString() {
        return "Gioco{" +
                "idGioco=" + idGioco +
                ", titolo='" + titolo + '\'' +
                ", annoPubblicazione=" + annoPubblicazione +
                ", prezzo=" + prezzo +
                '}';
    }
}
