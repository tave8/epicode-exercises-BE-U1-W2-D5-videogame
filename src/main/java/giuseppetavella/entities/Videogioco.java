package giuseppetavella.entities;

import giuseppetavella.enums.CollezioneItemType;
import giuseppetavella.enums.GenereVideogioco;
import giuseppetavella.enums.PiattaformaVideogioco;

public class Videogioco extends Gioco {
    private PiattaformaVideogioco piattaforma;
    private long durataGioco;
    private GenereVideogioco genere;
    
    public Videogioco(long idGioco, String titolo, double prezzo, long annoPubblicazione, long durataGioco,  PiattaformaVideogioco piattaformaVideogioco, GenereVideogioco genereVideogioco) {
        super(idGioco, titolo, CollezioneItemType.VIDEOGIOCO, prezzo, annoPubblicazione);
        this.setGenere(genereVideogioco);
        this.setPiattaforma(piattaformaVideogioco);
        this.setDurataGioco(durataGioco);
    }

    public PiattaformaVideogioco getPiattaforma() {
        return piattaforma;
    }

    public long getDurataGioco() {
        return durataGioco;
    }

    public GenereVideogioco getGenere() {
        return genere;
    }

    public void setPiattaforma(PiattaformaVideogioco piattaforma) {
        this.piattaforma = piattaforma;
    }

    public void setDurataGioco(long durataGioco) throws IllegalArgumentException {
        if(durataGioco <= 0) {
            throw new IllegalArgumentException("Durata gioco deve essere > 0. Fornito: " + durataGioco);
        }
        this.durataGioco = durataGioco;
    }

    public void setGenere(GenereVideogioco genere) {
        this.genere = genere;
    }
}
