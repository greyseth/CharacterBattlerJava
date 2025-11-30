package data.moves;

import model.Character;
import model.Move;

import java.util.Random;

public class LightAttack extends Move {
    public LightAttack() {
        super("Light Attack", "Basic light attack", 0);
    }

    @Override
    public void onPerform(Character self, Character target) {
        super.onPerform(self, target);

        // .5 + (a.agi / (a.agi+b.agi)) * .5 (basic hit rate formula)
        // a.atk * 4 - b.def * 2 (basic attack formula)

        System.out.println(self.name+" throws light attack!");

        Random random = new Random();

        // Calculates hit chance
        float hitRate = .5f + ((float) self.agi / (self.agi+target.agi)) * .5f;
        if (random.nextFloat()*100 <= hitRate*100) {
            float critMultiplier = 1.0f;

            // Calculates critical hit chance
            float critChance = (float) self.agi / 20;
            if (random.nextFloat()*100 <= critChance*100) critMultiplier = 1.5f;

            // Calculates damage based on stat
            int damage = (int) ((self.str*critMultiplier)*4 - target.def*2);
            target.hp -= damage;

            System.out.println("-"+damage+" HP!"+(critMultiplier>1?" Critical Strike!":""));
        }else {
            System.out.println("Attack missed!");
        }
    }

    @Override
    public void afterPerform(Character self, Character target) {
        super.afterPerform(self, target);
    }
}
