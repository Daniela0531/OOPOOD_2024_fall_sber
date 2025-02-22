package ru.mipt.bit.platformer.graphics_objects;

import ru.mipt.bit.platformer.graphics.HealthBarDecorator;

public interface GraphicsForLivableInterface extends GraphicsInterface {
    HealthBarDecorator getHealthBarDecorator();
}
