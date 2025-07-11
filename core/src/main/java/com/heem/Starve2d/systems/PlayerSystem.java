package com.heem.Starve2d.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.heem.Starve2d.components.ItemComponent;
import com.heem.Starve2d.components.PlayerComponent;
import com.heem.Starve2d.components.SpriteComponent;

import java.util.Objects;

public class PlayerSystem extends EntitySystem implements EntityListener {
    private Entity player;
    private PlayerComponent playerComponent;
    private SpriteComponent spriteComponent;
    private OrthographicCamera camera;
    private float stateTime = 0;
    private String side = "Side";

    private boolean flipX = false;
    private Entity rightHand;
    private Entity leftHand;

    private float timeRight = -1f;

    public PlayerSystem(OrthographicCamera camera) {
        this.camera = camera;
    }

    @Override
    public void update(float deltaTime) {
        stateTime += deltaTime;
        Vector2 move = new Vector2();

        // for anim
        boolean idle = true;
        if (Gdx.input.isKeyPressed(Keys.A)) {
            move.x = -1;
            flipX = true;
            side = "Side";
            idle = false;
        }
        if (Gdx.input.isKeyPressed(Keys.D)) {
            move.x = 1;
            flipX = false;
            side = "Side";
            idle = false;
        }
        if (Gdx.input.isKeyPressed(Keys.W)) {
            move.y = 1;
            side = "Up";
            idle = false;
        }
        if (Gdx.input.isKeyPressed(Keys.S)) {
            move.y = -1;
            side = "Down";
            idle = false;

        }
        //

        if (Gdx.input.isTouched()){
            useRightHand();
        }

        if (timeRight >= 0){
            right(deltaTime);
        } else {
            rightHand.getComponent(SpriteComponent.class).sprite.setPosition((float) -1e9, (float) -1e9);
        }

        // move
        move.setLength(playerComponent.velocity);
        float x = spriteComponent.sprite.getX() + move.x;
        float y = spriteComponent.sprite.getY() + move.y;
        spriteComponent.sprite.setX(x);
        spriteComponent.sprite.setY(y);
        //

        // move camera
        camera.position.lerp(new Vector3(x + spriteComponent.sprite.getWidth() / 2f,
            y + spriteComponent.sprite.getHeight() / 2f, 0), 0.05f);
        //

        // choice anim
        if (idle){
            if (Objects.equals(side, "Side"))
                spriteComponent.sprite.setRegion(playerComponent.idleSide.getKeyFrame(stateTime, true));
            else if (Objects.equals(side, "Up"))
                spriteComponent.sprite.setRegion(playerComponent.idleUp.getKeyFrame(stateTime, true));
            else if (Objects.equals(side, "Down")) {
                spriteComponent.sprite.setRegion(playerComponent.idle.getKeyFrame(stateTime, true));
            }
        } else {
            if (Objects.equals(side, "Side"))
                spriteComponent.sprite.setRegion(playerComponent.walk.getKeyFrame(stateTime, true));
            else if (Objects.equals(side, "Up"))
                spriteComponent.sprite.setRegion(playerComponent.walkUp.getKeyFrame(stateTime, true));
            else if (Objects.equals(side, "Down")) {
                spriteComponent.sprite.setRegion(playerComponent.walkDown.getKeyFrame(stateTime, true));
            }
        }
        spriteComponent.sprite.setFlip(flipX, false);
        //
    }

    private void right(float delta){
        rightHand.getComponent(SpriteComponent.class).sprite.setPosition(
            spriteComponent.sprite.getX() + spriteComponent.sprite.getWidth() / 2,
            spriteComponent.sprite.getY() + spriteComponent.sprite.getHeight() / 4);

        rightHand.getComponent(SpriteComponent.class).sprite.rotate(-180 * delta);
        timeRight -= delta;
    }

    private void useRightHand(){
        if (timeRight < 0){

            if (Objects.equals(side, "Side") && flipX){
                rightHand.getComponent(SpriteComponent.class).sprite.setRotation(180);
            } else if (Objects.equals(side, "Up")){
                rightHand.getComponent(SpriteComponent.class).sprite.setRotation(90);
            } else if (Objects.equals(side, "Side") && !flipX){
                rightHand.getComponent(SpriteComponent.class).sprite.setRotation(0);
            } else if (Objects.equals(side, "Down")) {
                rightHand.getComponent(SpriteComponent.class).sprite.setRotation(-90);
            }
            timeRight = 0.5f;
        }
    }

    @Override
    public void addedToEngine(Engine engine) {
        engine.addEntityListener(Family.all(PlayerComponent.class).get(), this);
        engine.addEntityListener(Family.all(ItemComponent.class).get(), this);
    }

    @Override
    public void entityAdded(Entity entity) {
        if (entity.getComponent(PlayerComponent.class) != null){
            player = entity;
            playerComponent = player.getComponent(PlayerComponent.class);
            spriteComponent = player.getComponent(SpriteComponent.class);
        }
        else {
            if (entity.getComponent(ItemComponent.class).hand){
                rightHand = entity;
            } else {
                leftHand = entity;
            }
        }
    }

    @Override
    public void entityRemoved(Entity entity) {

    }
}
