package giuseppetavella.programs.test;

import giuseppetavella.entities.Collezione;
import giuseppetavella.entities.Gioco;
import giuseppetavella.entities.GiocoDaTavolo;
import giuseppetavella.entities.Videogioco;
import giuseppetavella.enums.GenereVideogioco;
import giuseppetavella.enums.PiattaformaVideogioco;
import giuseppetavella.exceptions.CollezioneItemIdIsNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // collezioni giochi
        Collezione<Gioco> giochi1 = new Collezione<>("giochi1");
        Collezione<Gioco> giochi2 = new Collezione<>("giochi2");
        
        // tutte collezioni
        List<Collezione<Gioco>> tutteCollezioni = List.of(
                giochi1, giochi2
        );

        // videogiochi
        Videogioco videogioco1 = new Videogioco(1, "signore anelli", 10, 1900, 34, PiattaformaVideogioco.PC, GenereVideogioco.SCIFI);
        Videogioco videogioco2 = new Videogioco(2, "signore anelli", 12, 1900, 34, PiattaformaVideogioco.PC, GenereVideogioco.SCIFI);
        Videogioco videogioco3 = new Videogioco(3, "signore anelli", 15, 1900, 34, PiattaformaVideogioco.PC, GenereVideogioco.SCIFI);

        // giochi da tavolo
        GiocoDaTavolo giocoDaTavolo1 = new GiocoDaTavolo(4, "scarabeo", 40, 1400, 2, 45);
        GiocoDaTavolo giocoDaTavolo2 = new GiocoDaTavolo(5, "risiko", 50, 1400, 2, 2);
        
        // ***** ADD *****
        
        // collezioni giochi 1
        giochi1.addMany(List.of(
                videogioco1
                // videogioco2,
                // videogioco3,
                // giocoDaTavolo1,
                // giocoDaTavolo2
        ));

        // collezioni giochi 2
        giochi2.addMany(List.of(
                videogioco1,
                videogioco2,
                videogioco3,
                giocoDaTavolo1,
                giocoDaTavolo2
        ));

        
        // ***** FIND BY ID *****
        System.out.println();
        System.out.println("************ FIND BY ID ***********");
        
        List<Integer> targetItemIds = new ArrayList<>(List.of(
                1,2,3,4,5,6,7,8
        ));
        // for each collezione
        for(Collezione<?> currCollezione : tutteCollezioni) {
            System.out.println("  COLLECTION: " + currCollezione.getName());
            // for each target item id 
            for (Integer targetItemId : targetItemIds) {
                boolean itemFound = false;
                try {
                    currCollezione.findById(targetItemId);
                    itemFound = true;
                }
                catch(CollezioneItemIdIsNotFoundException ex) {
                    itemFound = false;
                }
                finally {
                    System.out.println("    item (ID:"+targetItemId+") - found: " + itemFound);
                }
            }
        }
        
 
        
        // ***** FIND BY PRICE ****
        
        // 
        
        // ***** PRINT STATS ****
        // giochi1.printStats();
        // giochi2.printStats();
        
        // System.out.println(giochi1);
    }
}
