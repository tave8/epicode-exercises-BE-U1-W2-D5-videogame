package giuseppetavella.programs.test;

import giuseppetavella.entities.Collezione;
import giuseppetavella.entities.Gioco;
import giuseppetavella.entities.Videogioco;
import giuseppetavella.enums.GenereVideogioco;
import giuseppetavella.enums.PiattaformaVideogioco;

public class Main {
    public static void main(String[] args) {
        Collezione<Gioco> giochi1 = new Collezione<>();

        Videogioco videogioco1 = new Videogioco(1, "signore anelli", 10, 1900, 34, PiattaformaVideogioco.PC, GenereVideogioco.SCIFI);
        Videogioco videogioco2 = new Videogioco(2, "signore anelli", 12, 1900, 34, PiattaformaVideogioco.PC, GenereVideogioco.SCIFI);
        
        giochi1.add(videogioco1);
        giochi1.add(videogioco2);
        
        giochi1.printStats();
        
        // System.out.println(giochi1);
    }
}
