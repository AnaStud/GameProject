package heroes;

import java.util.ArrayList;

//Монах
public class Monk extends BaseHero implements Healable {

    public Monk(Coordinate coordinates) {
        super(coordinates);
        this.strength = 20;
        this.weapon = 0;
        this.initiative = 0;
    }

    @Override
    public void step(int direction, ArrayList<BaseHero> friends, ArrayList<BaseHero> opponents) {
        if (this.hp > 0) {
            if (this.strength >= 10) {
                BaseHero friend = findPoorestFriend(friends);
                heal(friend);
            } else {
                this.strength = 20;
                this.history = "Я восстановил силы";
            }
        } else {
            this.history = "";
        }
    }
    @Override
    public void heal(BaseHero friend) {
        friend.setHp(Math.min(friend.getHp() + this.strength, friend.getMaxHp()));
        this.strength -= 10;
        this.history = "Я вылечил " + friend.getName();
    }

    @Override
    public String getInfo() {
        return "Монах " + this.getName() + ": " + this.getHistory();
    }
}
