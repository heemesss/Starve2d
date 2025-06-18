package com.heem.Starve2d.managers;

import com.badlogic.gdx.graphics.Texture;

import java.util.Vector;

public class FloorFactory {
    private static Vector<Texture> textureList = new Vector<>();
    public static int[][][][] map;

    public FloorFactory(){
        textureList.add(new Texture("sand.gif"));
        map = new int[10][10][10][10];
    }

    public static Texture getTextureOfIndex(int index){
        return textureList.get(index);
    }


}
