package giuseppetavella.programs.test;

import giuseppetavella.entities.*;
import giuseppetavella.enums.GenereVideogioco;
import giuseppetavella.enums.PiattaformaVideogioco;
import giuseppetavella.exceptions.CollezioneItemIdIsNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // collezioni giochi
        CollezioneGiochi giochi1 = new CollezioneGiochi("giochi1");
        CollezioneGiochi giochi2 = new CollezioneGiochi("giochi2");
        
        // tutte collezioni
        List<CollezioneGiochi> tutteCollezioni = List.of(
                giochi1, giochi2
        );

        // videogiochi
        Videogioco videogioco1 = new Videogioco(1, "signore anelli", 10, 1900, 34, PiattaformaVideogioco.PC, GenereVideogioco.SCIFI);
        Videogioco videogioco2 = new Videogioco(2, "signore anelli", 12, 1900, 34, PiattaformaVideogioco.PC, GenereVideogioco.SCIFI);
        Videogioco videogioco3 = new Videogioco(3, "signore anelli", 15, 1900, 34, PiattaformaVideogioco.PC, GenereVideogioco.SCIFI);

        // giochi da tavolo
        GiocoDaTavolo giocoDaTavolo1 = new GiocoDaTavolo(4, "scarabeo", 40, 1400, 2, 45);
        GiocoDaTavolo giocoDaTavolo2 = new GiocoDaTavolo(5, "risiko", 50, 1400, 3, 2);
        
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
        

        runTestUpdateById(tutteCollezioni);

        runTestFindById(tutteCollezioni);

        runTestFindWithPriceLTE(tutteCollezioni);

        runTestFindWhereNumeroGiocatoriEQ(tutteCollezioni);
        
        // ***** PRINT STATS ****
        
        giochi1.printStats();
        giochi2.printStats();

        runTestRemoveById(tutteCollezioni);
        
    }
    
    public static void runTestUpdateById(List<CollezioneGiochi> tutteCollezioni) {
        System.out.println();
        System.out.println("************ TEST: UPDATE BY ID ***********");

        List<Integer> targetItemIds = new ArrayList<>(List.of(
                1,2,3,4,5,6,7,8
        ));
        // for each collezione
        for(CollezioneGiochi currCollezione : tutteCollezioni) {
            System.out.println("  COLLECTION: " + currCollezione.getName());
            // for each target item id 
            for (Integer targetItemId : targetItemIds) {
                boolean itemFound = false;
                try {
                    String newTitolo = "new title: " + targetItemId;
                    currCollezione.updateById(targetItemId, newTitolo);
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
    }
    
    public static void runTestRemoveById(List<CollezioneGiochi> tutteCollezioni) {
        System.out.println();
        System.out.println("************ TEST: REMOVE BY ID ***********");

        List<Integer> targetItemIds = new ArrayList<>(List.of(
                1,2,3,4,5,6,7,8
        ));
        // for each collezione
        for(CollezioneGiochi currCollezione : tutteCollezioni) {
            System.out.println("  COLLECTION: " + currCollezione.getName());
            // for each target item id 
            for (Integer targetItemId : targetItemIds) {
                boolean itemFound = false;
                try {
                    currCollezione.removeById(targetItemId);
                    itemFound = true;
                }
                catch(CollezioneItemIdIsNotFoundException ex) {
                    itemFound = false;
                }
                finally {
                    System.out.println("    item (ID:"+targetItemId+") - removed: " + itemFound);
                }
            }
        }
    }
    
    public static void runTestFindWhereNumeroGiocatoriEQ(List<CollezioneGiochi> tutteCollezioni) {
        System.out.println();
        System.out.println("************ TEST: FIND BY NUMERO GIOCATORI ***********");

        List<Integer> targetNumeroGiocatoriList = new ArrayList<>(List.of(
                0, 1, 2, 3, 5, 7, 10
        ));
        // for each collezione
        for(CollezioneGiochi currCollezione : tutteCollezioni) {
            System.out.println("  COLLECTION: " + currCollezione.getName());
            // for each target numero giocatori
            for (Integer targetNumeroGiocatori : targetNumeroGiocatoriList) {
                System.out.println("    TARGET NUMERO GIOCATORI: " + targetNumeroGiocatori);
                List<?> outList = currCollezione.findWhereNumeroGiocatoriEQ(targetNumeroGiocatori);
                if(outList.isEmpty()) {
                    System.out.println("      "+"no item found where target numero giocatori "+targetNumeroGiocatori);
                } else {
                    outList.forEach(item -> System.out.println("      "+item) );
                }
            }
        }
    }
    
    public static void runTestFindWithPriceLTE(List<CollezioneGiochi> tutteCollezioni) {
        System.out.println();
        System.out.println("************ TEST: FIND WITH PRICE LESS THAN OR EQUAL ***********");

        List<Double> targetPrices = new ArrayList<>(List.of(
                5.0, 10.0, 20.0, 50.0, 100.0, 200.0
        ));
        // for each collezione
        for(CollezioneGiochi currCollezione : tutteCollezioni) {
            System.out.println("  COLLECTION: " + currCollezione.getName());
            // for each target price
            for (Double targetPrice : targetPrices) {
                System.out.println("    TARGET PRICE: " + targetPrice);
                List<?> outList = currCollezione.findWherePriceLTE(targetPrice);
                if(outList.isEmpty()) {
                    System.out.println("      "+"no item found where target price "+targetPrice);
                } else {
                    outList.forEach(item -> System.out.println("      "+item) );
                }
            }
        }
    }
    
    public static void runTestFindById(List<CollezioneGiochi> tutteCollezioni) {
        System.out.println();
        System.out.println("************ TEST: FIND BY ID ***********");

        List<Integer> targetItemIds = new ArrayList<>(List.of(
                1,2,3,4,5,6,7,8, 10, 11
        ));
        // for each collezione
        for(CollezioneGiochi currCollezione : tutteCollezioni) {
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
    }
    
}
