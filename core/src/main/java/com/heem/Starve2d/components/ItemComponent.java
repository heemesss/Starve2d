package com.heem.Starve2d.components;

import com.badlogic.ashley.core.Component;

public class ItemComponent implements Component {
    public boolean hand; // right - true : left - false

    public ItemComponent(boolean hand) {
        this.hand = hand;
    }
}
