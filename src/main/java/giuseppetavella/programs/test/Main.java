package giuseppetavella.programs.test;

import giuseppetavella.entities.Collezione;
import giuseppetavella.entities.Gioco;

public class Main {
    public static void main(String[] args) {
        Collezione<Gioco> giochi1 = new Collezione<>();
        
        
        // giochi1.add();
        
        giochi1.printStats();
        
        // System.out.println(giochi1);
    }
}
