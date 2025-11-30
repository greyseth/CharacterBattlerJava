package data.pages;

import data.PageData;
import model.Character;
import model.Move;
import model.Page;
import util.RandomChar;

import java.util.Random;

public class PageBattle extends Page {
    public PageBattle(String name) {
        super(name);
    }

    Character charBattle = null;
    Character enemy = null;
    boolean playerTurn = true;

    @Override
    public void pageRun(String parameter) {
        super.pageRun(parameter);

        if (charBattle != null) {
            battleFlow();
            return;
        }

        if (Character.characters.isEmpty()) {
            System.out.println("You have to register a character first!");
            PageData.setPage("home");
            return;
        }

        System.out.println("Pick a character to play as:");
        for (int i = 0; i < Character.characters.size(); i++) {
            Character c = Character.characters.get(i);
            addAction(c.name+" - Level "+c.level, () -> {
                charBattle = c;
                charBattle.hp = 100;
                charBattle.mp = 50;

                int[] randomStats = RandomChar.makeStats();
                enemy = new Character(RandomChar.makeName(), randomStats[0], randomStats[1], randomStats[2]);

                System.out.printf("You're fighting %1$s!\nHP:%2$s|MP:%3$s\n(STR:%4$s|DEF:%5$s|AGI:%6$s)\n\n",
                        enemy.name, enemy.hp, enemy.mp, enemy.str, enemy.def, enemy.agi);
            });
        }
        addAction("Back", () -> PageData.setPage("home"));
    }

    Move playerLastMove = null;
    Move enemyLastMove = null;
    void battleFlow() {
        Character turnChar = playerTurn?charBattle:enemy;
        Character targetChar = playerTurn?enemy:charBattle;

        if (turnChar.hp <= 0 || targetChar.hp <= 0) {
            Character lostCharacter = turnChar.hp <= 0 ? turnChar : targetChar;

            System.out.println(lostCharacter.name+" has lost the battle!");
            PageData.setPage("home");

            if (lostCharacter != charBattle) turnChar.addExp();
        }

        System.out.printf("%1$s's turn!\nHP:%2$s|MP:%3$s\n(STR:%4$s|DEF:%5$s|AGI:%6$s)\n",
                turnChar.name, turnChar.hp, turnChar.mp, turnChar.str, turnChar.def, turnChar.agi);
        if (playerTurn) {
            if (playerLastMove != null) playerLastMove.afterPerform(turnChar, targetChar);

            System.out.println("Select your next move!");
            for (int i = 0; i < turnChar.moves.length; i++) {
                Move move = turnChar.moves[i];
                addAction(move.name+" - "+move.description+"\nCost: "+move.cost+" MP", () -> {
                    move.onPerform(turnChar, targetChar);
                    playerLastMove = move;
                    playerTurn = !playerTurn;
                });
            }
        }else {
            if (enemyLastMove != null) enemyLastMove.afterPerform(turnChar, targetChar);

            Random random = new Random();
            Move randomMove = turnChar.moves[random.nextInt(0, turnChar.moves.length)];
            randomMove.onPerform(turnChar, targetChar);
            enemyLastMove = randomMove;

            playerTurn = !playerTurn;
        }

        System.out.println("");
    }
}
