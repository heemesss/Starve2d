package com.heem.Starve2d.components;

import com.badlogic.ashley.core.Component;

import java.util.ArrayList;
import java.util.Vector;

public class FloorComponent implements Component {
    public int x, y;
    public int[][] map;

    public FloorComponent(int x, int y, int[][] map){
        this.x = x;
        this.y = y;
        this.map = map;
    }
}
