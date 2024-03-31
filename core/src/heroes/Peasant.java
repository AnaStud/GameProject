package heroes;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

//Крестьянин
public class Peasant extends BaseHero {

    public Peasant(Coordinate coordinates) {
        super(coordinates);
        this.strength = 10;
        this.weapon = 0;
        this.initiative = 0;
    }

    @Override
    public void step(int direction, ArrayList<BaseHero> friends, ArrayList<BaseHero> opponents) {
        if (this.hp > 0) {

            List<BaseHero> list = new ArrayList<>();

            for (BaseHero friend : friends) {
                if (friend instanceof Crossbowman || friend instanceof Crossbowman) {
                    list.add(friend);
                }
            }

            if (!list.isEmpty()) {
                list.sort(Comparator.comparingInt(BaseHero::getWeapon));
                BaseHero friend = list.getFirst();
                friend.setWeapon(1);
                this.history = "Я пополнил запас снарядов у " + friend.getName();
            } else {
                this.history = "";
            }
        } else {
            this.history = "";
        }
    }

    @Override
    public String getInfo() { return "Крестьянин " + this.getName() + ": " + this.getHistory(); }
}
