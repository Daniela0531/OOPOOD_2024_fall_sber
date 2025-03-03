package ru.mipt.bit.platformer.graphics_objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.logic_objects.Model;

//@Component
public interface GraphicsInterface {
     Rectangle getRectangle();

    void dispose();

    void draw(Batch batch, Model model);
}
