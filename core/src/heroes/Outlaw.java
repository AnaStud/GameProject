package heroes;

import java.util.ArrayList;

//Разбойник
public class Outlaw extends BaseHero implements Attackable{

    public Outlaw(Coordinate coordinates) {
        super(coordinates);
        this.strength = 50;
        this.weapon = 30;
        this.initiative = 2;
    }

    @Override
    public void step(int direction, ArrayList<BaseHero> friends, ArrayList<BaseHero> opponents) {
        if (this.hp > 0 && this.weapon > 0) {
            BaseHero closestOpponent = findClosestHero(opponents);
            double distance = this.coordinates.calculateDistance(closestOpponent.getX(), closestOpponent.getY());
            if (distance < 2) {
                attack(closestOpponent);
            } else {
                goToOpponent(closestOpponent, friends, opponents);
                this.history = "Я шагнул в сторону " + closestOpponent.getName();
            }
        } else {
            this.history =  "";
        }
    }

    @Override
    public void attack(BaseHero opponent) {
        int damage = BaseHero.random.nextInt(10, this.strength);
        opponent.getDamage(damage);
        this.weapon--;
        this.history = "Я ударил " + opponent.getName();
    }

    @Override
    public String getInfo() { return "Разбойник " + this.getName() + ": " + this.getHistory(); }
}
