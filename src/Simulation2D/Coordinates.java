package Package;

import java.util.Objects;

public class Coordinates {
    private int x;
    private int y;
    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public String getStringCoordinates(){
        return "{" + getX() + ", " + getY() + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates coordinates = (Coordinates) o;
        return x == coordinates.x && y == coordinates.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
