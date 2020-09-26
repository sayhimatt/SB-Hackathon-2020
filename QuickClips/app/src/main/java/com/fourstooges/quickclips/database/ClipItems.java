package com.fourstooges.quickclips.database;

import java.util.ArrayList;
import java.util.List;

public class ClipItems {

    public static List<ClipItem> ITEMS = new ArrayList<>();

    public static void setItems(List<ClipItem> items){
        ITEMS = items;
    }
    public static void addItem(ClipItem item){
        ITEMS.add(item);
    }
    public static void removeItem(int index){
        ITEMS.remove(index);
    }
    public static ClipItem getItem(int index){
        return ITEMS.get(index);
    }


    public static class ClipItem {
        private String title;
        private String category;
        private String text;
        public ClipItem(){}
        public ClipItem(String title, String category, String text) {

        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

}
