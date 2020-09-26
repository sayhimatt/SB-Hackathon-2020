package com.fourstooges.quickclips.database;

import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class ClipItems {
    public static List<ClipItem> ITEMS = new LinkedList<>();

    public static void clear() {
        ITEMS.clear();
    }

    public static void setItems(List<ClipItem> items) {
        ITEMS = items;
    }

    public static void addItem(ClipItem item) {
        ITEMS.add(item);
    }

    public static void removeItem(int index) {
        ITEMS.remove(index);
    }

    public static ClipItem getItem(int index) {
        return ITEMS.get(index);
    }

    public static class ClipItem implements Serializable {
        private String title;
        private String category;
        private String text;
        private String id;
        private boolean isPublic;

        public ClipItem(String title, String category, String text, boolean isPublic) {
            this.title = title;
            this.category = category;
            this.text = text;
            this.isPublic = isPublic;
        }

        public ClipItem() {

        }

        public boolean isPublic() {
            return isPublic;
        }

        public void setPublic(boolean isPublic) {
            this.isPublic = isPublic;
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

        public void setId(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }
    }

}
