package ru.mipt.bit.platformer.level;

import ru.mipt.bit.platformer.Model;
import ru.mipt.bit.platformer.Obstacle;

import java.util.Set;

public interface PreProcessedLevel {
    public Set<Obstacle> getObstacles();
    public Set<Model> getModels();
}
