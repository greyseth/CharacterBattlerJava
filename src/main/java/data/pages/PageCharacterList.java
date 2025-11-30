package data.pages;

import data.PageData;
import model.Character;
import model.InputCheck;
import model.Page;
import util.Input;
import util.SaveData;

import java.util.LinkedList;

public class PageCharacterList extends Page {
    public PageCharacterList(String name) {
        super(name);
    }

    LinkedList<Character> characters = new LinkedList<>();
    boolean newChar = false;

    @Override
    public void pageRun(String parameter) {
        super.pageRun(parameter);

        characters = new LinkedList<>(Character.characters);

        System.out.println("Characters registered: "+characters.size());

        addAction("Register New Character", () -> newChar = true);
        for (int i = 0; i < characters.size(); i++) {
            Character c = Character.characters.get(i);
            addAction(c.name+" - Lvl. "+c.level, () -> {
                PageData.setPage("chardetails", String.valueOf(c.charId));
            });
        }
        addAction("Back", () -> PageData.setPage("home"));

        if (newChar) newCharMenu();
    }

    void newCharMenu() {
        int points = 12;

        System.out.println("Character Name: ");
        String nameInput = Input.inputString();

        System.out.println("You have 12 stat points you can spend towards str, def, and agi.\nPoints left: 12");
        System.out.println("Character str stat: ");
        int strInput = Input.inputInt(new InputCheck() {
            @Override
            public boolean check(int value) {
                if (value > 12) {
                    System.out.println("Stat cannot be higher than 12!");
                    return false;
                }else return true;
            }
        });
        points -= strInput;

        System.out.println("Points left: "+points);
        System.out.println("Character def stat: ");
        int pointsCheck = points;
        int defInput = Input.inputInt(new InputCheck() {
            @Override
            public boolean check(int value) {
                if (value > pointsCheck) {
                    System.out.println("You don't have enough points left!");
                    return false;
                }else return true;
            }
        });
        points -= defInput;

        System.out.println("Character agi stat: ");
        int pointsCheck2 = points;
        int agiInput = Input.inputInt(new InputCheck() {
            @Override
            public boolean check(int value) {
                if (value > pointsCheck2) {
                    System.out.println("You don't have enough points left!");
                    return false;
                }else return true;
            }
        });

        Character.characters.add(new Character(nameInput, strInput, defInput, agiInput));

        SaveData.saveCharacters();

        System.out.println("Registered new character "+nameInput);
        newChar = false;
        pageRun("");
    }
}
