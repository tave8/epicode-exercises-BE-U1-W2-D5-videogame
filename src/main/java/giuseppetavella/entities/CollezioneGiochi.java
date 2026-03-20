package giuseppetavella.entities;

import giuseppetavella.interfaces.GiocoConNumeroGiocatori;

import java.util.List;

public class CollezioneGiochi extends Collezione<Gioco> {
    
    public CollezioneGiochi(String name) {
        super(name);
    }
    
    public CollezioneGiochi() {
        super();
    }

    /**
     * Find items where numero giocatori == input numero giocatori.
     * EQUAL
     *
     * Assumption: types that have "numero giocatori" are:
     *      GiocoDaTavolo
     */
    public List<Gioco> findWhereNumeroGiocatoriEQ(long targetNumeroGiocatori) {
        // get the collection items of type GiocoDaTavolo
        // filter by numero giocatori
        return this.filterBy(item -> {
            // filter out collection items that are not GiocoDaTavolo
            if(!(item instanceof GiocoConNumeroGiocatori)) {
                return false;
            }
            GiocoConNumeroGiocatori giocoConNumeriGiocatori = (GiocoConNumeroGiocatori) item;
            // filter in giochi da tavolo that have the same numero giocatori
            return giocoConNumeriGiocatori.getNumeroGiocatori() == targetNumeroGiocatori;
        });
    }


}
