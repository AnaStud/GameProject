package heroes;

import java.util.ArrayList;

//Снайпер
public class Sniper extends BaseHero implements Attackable {

    public Sniper(Coordinate coordinates) {
        super(coordinates);
        this.strength = 100;
        this.weapon = 10;
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
    public String getInfo() { return "Снайпер " + this.getName() + ": " + this.getHistory(); }

}
