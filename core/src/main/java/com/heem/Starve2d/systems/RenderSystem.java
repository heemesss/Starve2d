package com.heem.Starve2d.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.heem.Starve2d.Core;
import com.heem.Starve2d.components.CellComponent;
import com.heem.Starve2d.components.FloorComponent;
import com.heem.Starve2d.components.SpriteComponent;
import com.heem.Starve2d.managers.FloorFactory;

import java.util.ArrayList;
import java.util.Vector;

public class RenderSystem extends EntitySystem {
    private SpriteBatch batch;
    public OrthographicCamera camera;
    private ImmutableArray<Entity> entities, floors;
    private FitViewport fitViewport;

    public RenderSystem(){
        camera = new OrthographicCamera();
        camera.viewportWidth = Core.SCREEN_WIDTH;
        camera.viewportHeight = Core.SCREEN_HEIGHT;
        batch = new SpriteBatch();
        fitViewport = new FitViewport(Core.SCREEN_WIDTH, Core.SCREEN_HEIGHT, camera);
        fitViewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(SpriteComponent.class).get());
        floors = engine.getEntitiesFor(Family.all(FloorComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {
        fitViewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        for (int i = 0; i < floors.size(); i++) {
            if (!floors.get(i).getComponent(FloorComponent.class).needDraw)
                continue;
            Entity[][] map = floors.get(i).getComponent(FloorComponent.class).map;
            for (int y = 0; y < map.length; y++){
                for (int x = 0; x < map[y].length; x++) {
                    Texture texture = FloorFactory.getTextureOfIndex(map[y][x].getComponent(CellComponent.class).number);
                    drawFloor(texture, floors.get(i).getComponent(FloorComponent.class).x * 1000 + x * 100,
                        floors.get(i).getComponent(FloorComponent.class).y * 1000 + y * 100);
                }
            }
        }
        for (int i = 0; i < entities.size(); i++) {
            Sprite sprite = entities.get(i).getComponent(SpriteComponent.class).sprite;
            drawSprite(sprite);
        }
        batch.end();
    }

    private void drawSprite(Sprite sprite){
        sprite.draw(batch);
    }

    private void drawFloor(Texture texture, int x, int y) {
        batch.draw(texture, x, y, 100, 100);
    }

    public void resize(int width, int height) {
        camera.viewportHeight = height;
        camera.viewportWidth = width;
    }
}
