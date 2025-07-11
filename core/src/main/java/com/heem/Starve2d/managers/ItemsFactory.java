package com.heem.Starve2d.managers;

import com.badlogic.gdx.graphics.Texture;

import java.util.Vector;

public class ItemsFactory {
    private static Vector<Texture> textureList = new Vector<>();

    public ItemsFactory(){
        textureList.add(new Texture("axe.gif"));
    }

    public static Texture getTextureOfIndex(int index){
        return textureList.get(index);
    }
}
