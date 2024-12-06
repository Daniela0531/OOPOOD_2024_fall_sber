package ru.mipt.bit.platformer;

import com.badlogic.gdx.math.GridPoint2;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Model {
    private GridPoint2 coordinates;
    public Model(GridPoint2 coordinaters) {
        this.coordinates = coordinaters;
    }
}
