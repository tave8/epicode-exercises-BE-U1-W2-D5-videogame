package giuseppetavella.programs.scanner;

import giuseppetavella.entities.Collezione;
import giuseppetavella.entities.Gioco;
import giuseppetavella.entities.GiocoDaTavolo;
import giuseppetavella.entities.Videogioco;
import giuseppetavella.enums.GenereVideogioco;
import giuseppetavella.enums.PiattaformaVideogioco;

import java.util.List;
import java.util.Scanner;

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

        
        // **** USER SCANNER
        Scanner scanner = new Scanner(System.in);
        

        System.out.println();
        System.out.println("**** SCEGLI COLLEZIONE *****");
        
        for(int i = 0; i < tutteCollezioni.size(); i++) {
            Collezione<?> collezione = tutteCollezioni.get(i);
            String msg = "  " + (i+1) + ") " + collezione.getName();
            System.out.println(msg);
        }
        
        int numeroCollezioneUser;

        while(true) {
            try {
                numeroCollezioneUser = Integer.parseInt(scanner.nextLine());
                if(!(numeroCollezioneUser >= 1 && numeroCollezioneUser <= tutteCollezioni.size())) {
                    throw new IllegalArgumentException();
                }
                break;
            }
            catch(IllegalArgumentException ex) {
                System.out.println("invalid collection. try again.");
            }
        }
        
        Collezione<Gioco> collezioneSceltaDaUtente = tutteCollezioni.get(numeroCollezioneUser-1);

        System.out.println(collezioneSceltaDaUtente);
        
        System.out.println();
        System.out.println("**** RICERCA ITEM CON PREZZO MINORE O UGUALE DI *****");

        double prezzoTargetUser;

        while(true) {
            try {
                prezzoTargetUser = Double.parseDouble(scanner.nextLine());
                if(prezzoTargetUser <= 0) {
                    throw new IllegalArgumentException();
                }
                List<Gioco> outList = collezioneSceltaDaUtente.findWherePriceLTE(prezzoTargetUser);
                if(outList.isEmpty()) {
                    System.out.println("      "+"no item found where target price "+prezzoTargetUser);
                } else {
                    outList.forEach(item -> System.out.println("      "+item) );
                }
                break;
            }
            catch(IllegalArgumentException ex) {
                System.out.println("invalid price. try again.");
            }
        }


        System.out.println();
        System.out.println("**** RICERCA ITEM CON NUMERO GIOCATORI UGUALE A *****");

        int numeroGiocatoriTargetUser;

        while(true) {
            try {
                numeroGiocatoriTargetUser = Integer.parseInt(scanner.nextLine());
                if(numeroGiocatoriTargetUser <= 0) {
                    throw new IllegalArgumentException();
                }
                List<?> outList = collezioneSceltaDaUtente.findWhereNumeroGiocatoriEQ(numeroGiocatoriTargetUser);
                if(outList.isEmpty()) {
                    System.out.println("      "+"no item found where target numero giocatori "+numeroGiocatoriTargetUser);
                } else {
                    outList.forEach(item -> System.out.println("      "+item) );
                }
                break;
            }
            catch(IllegalArgumentException ex) {
                System.out.println("invalid numero giocatori. try again.");
            }
        }
        
        
    }
    
    
}
