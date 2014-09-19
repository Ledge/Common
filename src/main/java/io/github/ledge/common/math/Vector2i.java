package io.github.ledge.common.math;

import com.google.common.base.Preconditions;

public class Vector2i {

    public int x;
    public int y;

    public Vector2i(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector2i(int[] xy) {
        Preconditions.checkNotNull(xy);
        Preconditions.checkArgument(xy.length == 2);

        this.x = xy[0];
        this.y = xy[1];
    }

    public Vector2i(Vector2i other) {
        this(other.x, other.y);
    }
}
