package heroes;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public abstract class BaseHero implements Stepable {
    protected static int id;
    protected static Random random;
    protected String name; //имя
    protected int hp; //здоровье
    protected int maxHp; //максимальное здоровье
    protected int strength; //сила
    protected int weapon; //оружие
    protected Coordinate coordinates; //положение на поле (координаты)
    public int initiative; //инициатива
    protected String history;

    static {
        BaseHero.id = 0;
        BaseHero.random = new Random();
    }
    public BaseHero(Coordinate coordinates) {
        this.name = String.format("%s_%d", this.getClass().getSimpleName(), ++BaseHero.id);
        this.hp = 100;
        this.maxHp = 100;
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return getInfo();
    }

    public String getName() {
        return this.name;
    }
    public int getHp() { return this.hp; }
    public int getMaxHp() { return this.maxHp; }
    public int getX() { return this.coordinates.x; }
    public int getY() { return this.coordinates.y; }
    public int getWeapon() { return this.weapon; }
    public String getHistory() { return this.history; }

    public void setHp(int hp) { this.hp = hp; }
    public void setWeapon(int weapon) { this.weapon += weapon; }

    public void getDamage(int damage) {
        if (this.hp - damage > 0) {
            this.hp -= damage;
        } else {
            this.hp = 0;
        }
    }

    public BaseHero findClosestHero(ArrayList<BaseHero> heroes) {

        String myName = this.getName();
        BaseHero closestHero = heroes.getFirst();
        double minDistance = this.coordinates.calculateDistance(closestHero.getX(), closestHero.getY());

        for (BaseHero hero : heroes) {
            if (!(hero.getName().equals(myName)) && (hero.getHp() > 0)) {
                double distance = this.coordinates.calculateDistance(hero.getX(), hero.getY());
                if (distance < minDistance) {
                    minDistance = distance;
                    closestHero = hero;
                }
            }
        }

        return closestHero;
    }

    public BaseHero findPoorestFriend(ArrayList<BaseHero> friends) {

        ArrayList<BaseHero> poorests = new ArrayList<>();

        for (BaseHero friend : friends) {
            if (!(friend instanceof Magician || friend instanceof Monk)) {
                int friendHp = friend.getHp();
                if (friendHp > 0) {
                    poorests.add(friend);
                }
            }
        }

        if (!poorests.isEmpty()) {
            poorests.sort(Comparator.comparingInt(BaseHero::getWeapon));
            return poorests.getFirst();
        } else {
            return friends.getFirst();
        }
    }

    public void goToOpponent(BaseHero closestOpponent, ArrayList<BaseHero> friends, ArrayList<BaseHero> opponents) {
        double dX = closestOpponent.getX() - this.getX();
        double dY = closestOpponent.getY() - this.getY();

        if (Math.abs(dX) > Math.abs(dY)) {
            this.coordinates.stepX(dX > 0, friends, opponents);
        } else {
            this.coordinates.stepY(dY > 0, friends, opponents);
        }
    }

    public void step(int direction, ArrayList<BaseHero> friends, ArrayList<BaseHero> opponents) {
        if (this.hp > 0) {
            this.coordinates.stepX(direction > 0, friends, opponents);
        } else {
            this.history = "";
        }
    }

    public boolean isWinner(ArrayList<BaseHero> opponents) {
        for (BaseHero opponent : opponents) {
            if (opponent.getHp() > 0) {
                return false;
            }
        }
        return true;
    }
}
