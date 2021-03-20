public class Position {
    private int x;
    private int y;
    public Position(int x, int y){
        this.x=x;
        this.y=y;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public boolean equals(Position o) {
        if (this == o) return true;
        return x == o.x && y == o.y;
    }
}
