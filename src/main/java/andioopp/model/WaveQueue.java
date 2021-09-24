package andioopp.model;

import andioopp.common.time.Time;

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
