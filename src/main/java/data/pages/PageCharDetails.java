package data.pages;

import data.PageData;
import model.Character;
import model.Page;
import util.Input;
import util.SaveData;

public class PageCharDetails extends Page {
    public PageCharDetails(String name) {
        super(name);
    }

    @Override
    public void pageRun(String parameter) {
        super.pageRun(parameter);

        Character c = Character.characters.stream().filter(cc -> cc.charId == Integer.parseInt(parameter)).toList().get(0);
        System.out.println(c.name+" - Level "+c.level);
        System.out.printf("\nSTR: %1$s | DEF: %2$s | AGI: %3$s%n", c.str, c.def, c.agi);

        addAction("Back", () -> PageData.setPage("charlist"));
        addAction("Delete Character", () -> {
            System.out.println("Are you sure you want to delete? (Y/N)");
            String confirmInput = Input.inputString();
            if (confirmInput.equalsIgnoreCase("y")) {
                Character.characters.remove(c);
                PageData.setPage("charlist");

                SaveData.saveCharacters();
            }
            else {
                System.out.println("Cancelled character deletion");
                pageRun("");
            }
        });
    }
}
