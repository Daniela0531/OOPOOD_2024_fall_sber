package ru.mipt.bit.platformer;

import com.badlogic.gdx.math.GridPoint2;

public class Direction {
//   public enum Trend {
//      UP,
//      DOWN,
//      RIGHT,
//      LEFT
//   }
   GridPoint2 vector;
   private float rotation;
//   private Trend trend;

   public Direction(GridPoint2 vector, float rotation) {
//      this.trend = trend;
      this.vector = vector;
      this.rotation = rotation;
   }

   public float getRotation() {
      return rotation;
   }

   public GridPoint2 getVector() {
      return vector;
   }

   public void setRotation(float rotation) {
      this.rotation = rotation;
   }

   public void setVector(GridPoint2 vector) {
      this.vector = vector;
   }
}
