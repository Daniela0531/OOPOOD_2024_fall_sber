package ru.mipt.bit.platformer.game_objects;

public interface LivableModel {
    public NodeType getType();

    float getHealth();
    void damage(float damage);

}
