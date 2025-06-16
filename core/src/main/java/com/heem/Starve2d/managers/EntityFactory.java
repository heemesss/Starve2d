package com.heem.Starve2d.managers;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.heem.Starve2d.components.FloorComponent;
import com.heem.Starve2d.components.PlayerComponent;
import com.heem.Starve2d.components.SpriteComponent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

public class EntityFactory {

    public static Entity createPlayer(){
        Entity player = new Entity();
        player.add(new PlayerComponent());
        Sprite sprite = new Sprite();
        sprite.setTexture(new Texture("2025-06-11-113928_hyprshot.png"));
        sprite.setBounds(0, 0, 100, 100);
        player.add(new SpriteComponent(sprite));
        return player;
    }

    public static Entity createFloor(int x, int y){
        Entity floor = new Entity();
        int[][] list = new int[][]{
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        };
        floor.add(new FloorComponent(x, y, list));
        return floor;
    }
}
