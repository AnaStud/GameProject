package heroes;

import java.util.ArrayList;
import java.util.stream.Collectors;

//Колдун / Чародей
public class Magician extends BaseHero implements Healable {

    public Magician(Coordinate coordinates) {
        super(coordinates);
        this.strength = 50;
        this.weapon = 0;
        this.initiative = 1;
    }

    @Override
    public void step(int direction, ArrayList<BaseHero> friends, ArrayList<BaseHero> opponents) {
        if (this.hp > 0) {
            if (this.strength >= 50 && (deadCount(friends) > 3)) {
                try {
                    BaseHero lucky = friends.stream()
                            .filter(n -> n.getHp() == 0)
                            .sorted((n1, n2) -> n2.initiative - n1.initiative)
                            .collect(Collectors.toList())
                            .getFirst();

                    revival(lucky);

                } catch (Exception e) {
                    if (this.strength >= 10) {
                        BaseHero friend = findPoorestFriend(friends);
                        heal(friend);
                    }
                }
            } else {
                if (this.strength >= 10) {
                    BaseHero friend = findPoorestFriend(friends);
                    heal(friend);
                } else {
                    this.strength = 50;
                    this.history = "Я восстановил силы";
                }
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

    public void revival(BaseHero friend) {
        friend.setHp(friend.getMaxHp());
        this.strength -= 50;
        this.history = "Я возродил " + friend.getName();
    }

    public int deadCount(ArrayList<BaseHero> friends) {
        return (int) friends.stream().filter(n -> n.getHp() == 0).count();
    }

    @Override
    public String getInfo() {
        return "Чародей " + this.getName() + ": " + this.getHistory();
    }
}
