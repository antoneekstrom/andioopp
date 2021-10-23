package andioopp.model.domain.tower;

import andioopp.model.domain.tower.towers.*;
import andioopp.model.util.ModelCoordinate;

/**
 * A factory for creating the different towers.
 */
public class TowerFactory {
    public static Tower createMario(ModelCoordinate position) {
        return new Mario(position);
    }
    public static Tower createToad(ModelCoordinate position) {
        return new Toad(position);
    }
    public static Tower createLuigi(ModelCoordinate position) {
        return new Luigi(position);
    }
    public static Tower createYoshi(ModelCoordinate position) {
        return new Yoshi(position);
    }
    public static Tower createRosalina(ModelCoordinate position) {
        return new Rosalina(position);
    }
    public static Tower createBobomb(ModelCoordinate position) {
        return new Bobomb(position);
    }
}
