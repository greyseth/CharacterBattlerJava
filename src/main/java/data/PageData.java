package data;

import data.pages.PageBattle;
import data.pages.PageCharDetails;
import data.pages.PageCharacterList;
import data.pages.PageHome;
import model.Page;
import util.Input;

import java.util.*;

public class PageData {
    public static String curPage = "home";
    public static String curPageParameter;
    public static ArrayList<Page> pages = new ArrayList<>();

    public static void initializePages() {
        // Home Page
        Page homePage = new PageHome("home");

        // Character List
        Page charList = new PageCharacterList("charlist");

        // Character Details
        Page charDetails = new PageCharDetails("chardetails");

        // Battle
        Page battle = new PageBattle("battle");

        pages.add(homePage);
        pages.add(charList);
        pages.add(charDetails);
        pages.add(battle);
    }

    public static void setPage(String targetPage) {
        setPage(targetPage, "");
    }
    public static void setPage(String targetPage, String parameter) {
        curPage = targetPage;
        curPageParameter = parameter;
        updatePage();
    }

    public static void updatePage() {
        List<Page> pagesFilter = pages.stream().filter(p -> p.name.equals(curPage)).toList();
        if (pagesFilter.isEmpty()) {
            System.out.println("The page '"+curPage+"' doesn't exist!");
            setPage("home");
            return;
        }

        Page target = pagesFilter.getFirst();
        target.pageRun(curPageParameter);

        if (target.actions.isEmpty()) return;
        for (int i = 0; i < target.actions.size(); i++) {
            System.out.print((i+1)+" - "+target.actions.get(i).label+"\n");
        }

        boolean validInput = false;
        while(!validInput) {
            int pageInput = Input.inputInt();
            if (pageInput > target.actions.size()) {
                System.out.println("Invalid menu input");
                continue;
            }

            System.out.println("");
            validInput = true;
            target.actions.get(pageInput-1).callback.execute();
        }
    }
}
