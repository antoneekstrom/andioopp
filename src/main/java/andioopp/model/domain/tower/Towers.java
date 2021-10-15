package andioopp.model.domain.tower;

import andioopp.model.domain.tower.towers.*;


public class Towers {

    public static Tower mario() {
        return new Mario();
    }
    public static Tower toad() {
        return new Toad();
    }
    public static Tower luigi() {
        return new Luigi();
    }
    public static Tower yoshi() {
        return new Yoshi();
    }
    public static Tower rosalina() {
        return new Rosalina();
    }
    public static Tower bobomb() {
        return new Bobomb();
    }
}
