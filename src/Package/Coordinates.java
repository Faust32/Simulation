package Package;

import java.util.Objects;

public class Points {
    int x;
    int y;
    public Points(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Points points = (Points) o;
        return x == points.x && y == points.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
