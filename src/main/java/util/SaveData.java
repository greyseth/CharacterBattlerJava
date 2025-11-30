package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.Character;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class SaveData {
    public static void saveCharacters() {
        String savePath = System.getProperty("user.home")+"\\Documents";

        try (Writer writer = new FileWriter(savePath+"\\characterbattlersave.json")) {
            new GsonBuilder().create().toJson(Character.characters, writer);
        }catch(Exception e) {
            e.printStackTrace();
            System.out.println("NOTICE: Failed to save character data");
        }
    }

    public static void loadCharacters() {
        Gson gson = new Gson();
        String savePath = System.getProperty("user.home")+"\\Documents";

        try {
            File saveDataCheck = new File(savePath+"\\characterbattlersave.json");
            if (!saveDataCheck.exists() || saveDataCheck.isDirectory()) return;

            BufferedReader bufferedReader = new BufferedReader(new FileReader(savePath+"\\characterbattlersave.json"));
            Character.characters = new LinkedList<>(gson.fromJson(bufferedReader, new TypeToken<List<Character>>(){}.getType()));
        }catch(Exception e) {
            System.out.println("NOTICE: Failed to load character data");
            e.printStackTrace();
        }
    }
}
