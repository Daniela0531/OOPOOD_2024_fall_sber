package ru.mipt.bit.platformer.logic_objects;

public interface LivableModel {
    public NodeType getType();

    int getHealth();
    void damage(int damage);

}
