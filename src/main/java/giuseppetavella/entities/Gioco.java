package giuseppetavella.entities;

import giuseppetavella.enums.CollezioneItemType;
import giuseppetavella.interfaces.CollezioneItem;

public abstract class Gioco implements CollezioneItem {
    private final int idGioco;
    private final CollezioneItemType itemType;
    private String titolo;
    private final long annoPubblicazione;
    private double prezzo;
    
    
    public Gioco(int idGioco, String titolo, CollezioneItemType itemType, double prezzo, long annoPubblicazione) {
        this.idGioco = idGioco;
        this.itemType = itemType;
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
    public int getId() {
        return this.getIdGioco();
    }
    
    @Override
    public double getPrice() {
        return this.getPrezzo();
    }
    
    @Override
    public CollezioneItemType getCollezioneItemType() {
        return this.itemType;
    }

    @Override
    public void setTitolo(String newTitolo) {
        this.titolo = newTitolo;
    }
    
    @Override
    public String getTitolo() {
        return titolo;
    }

    public int getIdGioco() {
        return idGioco;
    }


    public long getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    public double getPrezzo() {
        return prezzo;
    }



    public void setPrezzo(double prezzo) throws IllegalArgumentException {
        if(prezzo <= 0) {
            throw new IllegalArgumentException("Prezzo deve essere > 0. Fornito: " + prezzo);
        }
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
