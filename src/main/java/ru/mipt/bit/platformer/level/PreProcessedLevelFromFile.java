package ru.mipt.bit.platformer.level;

import lombok.Setter;
import ru.mipt.bit.platformer.Model;
import ru.mipt.bit.platformer.Obstacle;

import java.util.HashSet;
import java.util.Set;

@Setter
public class PreProcessedLevelFromFile implements PreProcessedLevel {
    private Set<Model> models = new HashSet<>();
    private Set<Obstacle> obstacles = new HashSet<>();

    public PreProcessedLevelFromFile() {
        models = new HashSet<>();
        obstacles = new HashSet<>();
    }

    @Override
    public Set<Obstacle> getObstacles() {
        return obstacles;
    }

    @Override
    public Set<Model> getModels() {
        return models;
    }
}
