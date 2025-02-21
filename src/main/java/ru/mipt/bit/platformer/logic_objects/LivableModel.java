package ru.mipt.bit.platformer.logic_objects;

public interface LivableModel extends Model {
    public NodeType getType();

    int getHealth();
    void damage(int damage);

    void switchHealthBar();

    boolean isHealthBarRaise();

    float getMaxHealth();
}
