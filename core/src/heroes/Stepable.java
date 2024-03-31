package heroes;

import java.util.ArrayList;

public interface Stepable {
    void step(int direction, ArrayList<BaseHero> friends, ArrayList<BaseHero> opponents);

    String getInfo();
}
