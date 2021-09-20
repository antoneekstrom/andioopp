package andioopp.model;

import java.util.LinkedList;
import java.util.Queue;

public class WaveQueue {

    private final Queue<Wave> queue;

    public WaveQueue() {
        queue = new LinkedList<>();
    }

    public Queue<Wave> getWaves() {
        return queue;
    }

}
