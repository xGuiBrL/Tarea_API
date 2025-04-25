package com.example.tarea_api.models;

public class MenuItem {
    private String title;
    private int iconResource;

    public MenuItem(String title, int iconResource) {
        this.title = title;
        this.iconResource = iconResource;
    }

    public String getTitle() { return title; }
    public int getIconResource() { return iconResource; }
}