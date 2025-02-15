package ru.mipt.bit.platformer.game.model.objects;

import com.badlogic.gdx.math.GridPoint2;
import lombok.Getter;
import lombok.Setter;

//@Entity
//@Table(name = "stories")
@Getter
@Setter
public class Direction {
    private GridPoint2 vector;

}
