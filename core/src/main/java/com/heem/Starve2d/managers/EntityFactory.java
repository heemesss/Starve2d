package com.heem.Starve2d.managers;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.heem.Starve2d.components.FloorComponent;
import com.heem.Starve2d.components.ItemComponent;
import com.heem.Starve2d.components.PlayerComponent;
import com.heem.Starve2d.components.SpriteComponent;

public class EntityFactory {

    public static Entity createPlayer(){
        Entity player = new Entity();
        PlayerComponent playerComponent = player.addAndReturn(new PlayerComponent());
        SpriteComponent spriteComponent = player.addAndReturn(new SpriteComponent(new Sprite(new Texture("hero.gif"))));
        spriteComponent.sprite.setBounds(0, 0, 100, 100);


        Array<TextureRegion> walk = new Array<>(8);
        walk.addAll(TextureRegion.split(new Texture("heroanim.gif"), 16, 16)[0]);
        playerComponent.walk = new Animation<>(0.1f, walk, Animation.PlayMode.LOOP);

        Array<TextureRegion> walkUp = new Array<>(4);
        walkUp.addAll(TextureRegion.split(new Texture("walkup.gif"), 16, 16)[0]);
        playerComponent.walkUp = new Animation<>(0.1f, walkUp, Animation.PlayMode.LOOP);

        Array<TextureRegion> walkDown = new Array<>(4);
        walkDown.addAll(TextureRegion.split(new Texture("walkdown.gif"), 16, 16)[0]);
        playerComponent.walkDown = new Animation<>(0.1f, walkDown, Animation.PlayMode.LOOP);

        Array<TextureRegion> idle = new Array<>(4);
        idle.addAll(TextureRegion.split(new Texture("idle.gif"), 16, 16)[0]);
        playerComponent.idle = new Animation<>(0.5f, idle, Animation.PlayMode.LOOP);

        Array<TextureRegion> idleSide = new Array<>(4);
        idleSide.addAll(TextureRegion.split(new Texture("idleside.gif"), 16, 16)[0]);
        playerComponent.idleSide = new Animation<>(0.5f, idleSide, Animation.PlayMode.LOOP);

        Array<TextureRegion> idleUp = new Array<>(4);
        idleUp.addAll(TextureRegion.split(new Texture("idleup.gif"), 16, 16)[0]);
        playerComponent.idleUp = new Animation<>(0.5f, idleUp, Animation.PlayMode.LOOP);

        return player;
    }

    public static void createFloor(Engine engine){
        for (int i = 0; i < FloorFactory.map.length; i++){
            for (int j = 0; j < FloorFactory.map[i].length; j++){
                Entity floor = new Entity();
                floor.add(new FloorComponent(i, j, FloorFactory.map[i][j]));
                engine.addEntity(floor);
            }
        }
    }

    public static void createItemsForHand(Engine engine) {
        Entity entity = new Entity();
        SpriteComponent spriteComponent = entity.addAndReturn(new SpriteComponent(new Sprite(new Texture("pickaxe.gif"))));
        spriteComponent.sprite.setBounds((float) -1e9, (float) -1e9, 100, 100);
        entity.add(new ItemComponent(true));
        engine.addEntity(entity);

        entity = new Entity();
        spriteComponent = entity.addAndReturn(new SpriteComponent(new Sprite(new Texture("axe.gif"))));
        spriteComponent.sprite.setBounds((float) -1e9, (float) -1e9, 100, 100);
        entity.add(new ItemComponent(false));
        engine.addEntity(entity);
    }
}
