import data.PageData;
import data.RunningData;
import util.SaveData;

public class App {
    public static void main(String[] args) {
        PageData.initializePages();
        SaveData.loadCharacters();

        System.out.println("Welcome to CharacterBattler!");

        while (RunningData.appRunning) {
            PageData.updatePage();
        }
    }
}
