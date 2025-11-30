package model;

import data.moves.Guard;
import data.moves.HeavyAttack;
import data.moves.LightAttack;

import java.util.LinkedList;
import java.util.Random;

public class Character {
    public static LinkedList<Character> characters = new LinkedList<>();

    public int charId;
    public String name;
    public int level;
    public int exp;
    public int str;
    public int def;
    public int agi;

    public int hp;
    public int mp;

    public LinkedList<String> states = new LinkedList<>();

    public Move[] moves;

    public Character(String name, int str, int def, int agi) {
        this.name = name;
        this.level = 1;
        this.exp = 0;
        this.str = str;
        this.def = def;
        this.agi = agi;

        this.charId = characters.size()+1;

        this.hp = 100;
        this.mp = 50;   
        this.moves = new Move[]{new LightAttack(), new HeavyAttack(), new Guard()};
    }

    public void addExp() {
        Random random = new Random();
        int expGain = random.nextInt(100, 300+1);

        exp += expGain;
        System.out.println("EXP GAINED: "+expGain);

        if (exp >= 1000) {
            level++;
            System.out.println("LEVEL UP! Level "+level);

            exp -= 1000;
        }
        System.out.println("["+exp+"/1000] to next level");
    }
}
