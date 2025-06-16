package com.heem.Starve2d.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.heem.Starve2d.components.PlayerComponent;
import com.heem.Starve2d.components.SpriteComponent;

public class PlayerSystem extends EntitySystem implements EntityListener {
    private Entity player;
    private PlayerComponent playerComponent;
    private SpriteComponent spriteComponent;
    private OrthographicCamera camera;

    public PlayerSystem(OrthographicCamera camera) {
        this.camera = camera;
    }

    @Override
    public void update(float deltaTime) {
        Vector2 move = new Vector2();

        if (Gdx.input.isKeyPressed(Keys.A)) {
            move.x -= 1;
        }
        if (Gdx.input.isKeyPressed(Keys.D)) {
            move.x += 1;
        }
        if (Gdx.input.isKeyPressed(Keys.W)) {
            move.y += 1;
        }
        if (Gdx.input.isKeyPressed(Keys.S)) {
            move.y -= 1;
        }

        move.setLength(playerComponent.velocity);
        float x = spriteComponent.sprite.getX() + move.x;
        float y = spriteComponent.sprite.getY() + move.y;
        spriteComponent.sprite.setX(x);
        spriteComponent.sprite.setY(y);

        camera.position.lerp(new Vector3(x + spriteComponent.sprite.getWidth() / 2f,
            y + spriteComponent.sprite.getHeight() / 2f, 0), 0.05f);
    }

    @Override
    public void addedToEngine(Engine engine) {
        engine.addEntityListener(Family.all(PlayerComponent.class).get(), this);
    }

    @Override
    public void entityAdded(Entity entity) {
        player = entity;
        playerComponent = player.getComponent(PlayerComponent.class);
        spriteComponent = player.getComponent(SpriteComponent.class);
    }

    @Override
    public void entityRemoved(Entity entity) {

    }
}
