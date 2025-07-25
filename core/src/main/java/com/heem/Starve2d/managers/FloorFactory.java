package com.heem.Starve2d.managers;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.heem.Starve2d.components.CellComponent;

import java.util.Arrays;
import java.util.Vector;

public class FloorFactory {
    private static Vector<TextureRegion> textureList = new Vector<>();
    public static Entity[][][][] map;

    public FloorFactory(){
        map = new Entity[10][10][10][10];
        textureList.add(new TextureRegion(new Texture("sand.gif")));
        textureList.add(new TextureRegion(new Texture("sand2.gif")));
        for (TextureRegion[] textureRegions : TextureRegion.split(new Texture("grass.gif"), 32, 32)){
            textureList.addAll(Arrays.asList(textureRegions));
        }
//        textureList.add(new Texture("sandright.gif"));
//        textureList.add(new Texture("water.gif"));
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    for (int l = 0; l < 10; l++) {
                        map[i][j][k][l] = new Entity();
                        map[i][j][k][l].add(new CellComponent());
                        map[i][j][k][l].getComponent(CellComponent.class).number = MathUtils.random(2, 17);
                    }
                }
            }
        }
    }

    public static TextureRegion getTextureOfIndex(int index){
        return textureList.get(index);
    }


}
