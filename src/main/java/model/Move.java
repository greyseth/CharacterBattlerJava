package model;

public class Move {
    public String name;
    public String description;
    public int cost;

    public Move() {}
    public Move(String name, String description, int cost) {
        this.name = name;
        this.description = description;
        this.cost = cost;
    }

    public void onPerform(Character self, Character target) {
        if (self.mp < cost) return;
        self.mp -= cost;
    }

    public void afterPerform(Character self, Character target) {

    }
}
