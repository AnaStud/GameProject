package heroes;

import java.util.ArrayList;

public class Coordinate {
    int x;
    int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double calculateDistance(int otherX, int otherY) {
        return Math.sqrt(Math.pow(this.x - otherX, 2) + Math.pow(this.y - otherY, 2));
    }

    public void stepX(boolean direction, ArrayList<BaseHero> friends, ArrayList<BaseHero> opponents) {
        int newX = direction ? this.x + 1 : this.x - 1;
        if (newX > -1 && newX < 10 && isNoOccupied(newX, this.y, friends, opponents)) {
            this.x = newX;
        }
    }

    public void stepY(boolean direction, ArrayList<BaseHero> friends, ArrayList<BaseHero> opponents) {
        int newY = direction ? this.y + 1 : this.y - 1;
        if (newY > -1 && newY < 10 && isNoOccupied(this.x, newY, friends, opponents)) {
            this.y = newY;
        }
    }

    public boolean isNoOccupied(int newX, int newY, ArrayList<BaseHero> friends, ArrayList<BaseHero> opponents) {
        for (BaseHero friend : friends) {
            if (friend.coordinates.x == newX && friend.coordinates.y == newY) {
                return false;
            }
        }
        for (BaseHero opponent : opponents) {
            if (opponent.coordinates.x == newX && opponent.coordinates.y == newY) {
                return false;
            }
        }
        return true;
    }
}
