package data.moves;

import model.Character;
import model.Move;

public class Guard extends Move {
    public Guard() {
        super("Guard", "Decreases incoming damage on next turn", 0);
    }

    int prevDef = 0;
    @Override
    public void onPerform(Character self, Character target) {
        super.onPerform(self, target);

        prevDef = self.def;
        self.def += (int) (self.def*.8f);

        System.out.println(self.name+" braces for impact! (DEF:"+self.def+")");
    }

    @Override
    public void afterPerform(Character self, Character target) {
        super.afterPerform(self, target);

        self.def = prevDef;
    }
}
