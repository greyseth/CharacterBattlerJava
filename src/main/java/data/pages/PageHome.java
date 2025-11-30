package data.pages;

import data.PageData;
import data.RunningData;
import model.Page;

public class PageHome extends Page {
    public PageHome(String name) {
        super(name);
    }

    @Override
    public void pageRun(String parameter) {
        super.pageRun(parameter);

        addAction("Character List", () -> PageData.setPage("charlist"));
        addAction("Battle", () -> PageData.setPage("battle"));
        addAction("Exit", () ->{
            RunningData.appRunning = false;
        });
    }
}
