package andioopp.model.domain.tower;

import andioopp.model.domain.tower.towers.*;
import andioopp.model.util.ModelCoordinate;


public class Towers {
    public static Tower mario(ModelCoordinate position) {
        return new Mario(position);
    }
    public static Tower toad(ModelCoordinate position) {
        return new Toad(position);
    }
    public static Tower luigi(ModelCoordinate position) {
        return new Luigi(position);
    }
    public static Tower yoshi(ModelCoordinate position) {
        return new Yoshi(position);
    }
    public static Tower rosalina(ModelCoordinate position) {
        return new Rosalina(position);
    }
    public static Tower bobomb(ModelCoordinate position) {
        return new Bobomb(position);
    }
}
