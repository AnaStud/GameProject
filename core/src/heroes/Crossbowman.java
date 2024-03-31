package heroes;

import java.util.ArrayList;

//Арбалетчик
public class Crossbowman extends BaseHero implements Attackable {

    public Crossbowman(Coordinate coordinates) {
        super(coordinates);
        this.strength = 80;
        this.weapon = 20;
        this.initiative = 3;
    }

    @Override
    public void step(int direction, ArrayList<BaseHero> friends, ArrayList<BaseHero> opponents) {
        if (this.hp > 0 && this.weapon > 0) {
            BaseHero closestOpponent = findClosestHero(opponents);
            attack(closestOpponent);
        } else {
            this.history =  "";
        }
    }

    @Override
    public void attack(BaseHero opponent) {
        int damage = BaseHero.random.nextInt(10, this.strength);
        opponent.getDamage(damage);
        this.weapon--;
        this.history = "Я выстрелил в " + opponent.getName();
    }

    @Override
    public String getInfo() { return "Арбалетчик " + this.getName() + ": " + this.getHistory(); }

}
