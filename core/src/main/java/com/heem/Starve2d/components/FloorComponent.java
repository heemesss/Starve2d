package com.heem.Starve2d.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

public class FloorComponent implements Component {
    public int x, y;
    public Entity[][] map;
    public boolean needDraw = false;

    public FloorComponent(int x, int y, Entity[][] map){
        this.x = x;
        this.y = y;
        this.map = map;
    }
}
