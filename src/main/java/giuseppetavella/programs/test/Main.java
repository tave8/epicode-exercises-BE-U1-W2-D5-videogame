package giuseppetavella.programs.test;

import giuseppetavella.entities.Collezione;
import giuseppetavella.entities.Gioco;

public class Main {
    public static void main(String[] args) {
        Collezione<Gioco> giochi = new Collezione<>();

        System.out.println(giochi);
    }
}
