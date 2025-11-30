package model;

import java.util.*;

public class Page {
    public String name;
    public String label;
    public ArrayList<PageAction> actions = new ArrayList<>();
    public Page(String name) {
        this.name = name;
        this.label = "";
    }

    public void addAction(String label, ActionCallback callback) {
        actions.add(new PageAction(label, callback));
    }

    public void pageRun(String parameter) {
        // pageRun is where you initialize a page's actions
        actions.clear();
    }

    public class PageAction {
        public String label;
        public ActionCallback callback;

        public PageAction(String label, ActionCallback callback) {
            this.label = label;
            this.callback = callback;
        }
    }
}
