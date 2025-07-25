package com.heem.Starve2d.worlds;

import com.badlogic.ashley.core.Engine;
import com.heem.Starve2d.managers.EntityFactory;
import com.heem.Starve2d.managers.FloorFactory;
import com.heem.Starve2d.managers.ItemsFactory;
import com.heem.Starve2d.systems.FloorSystem;
import com.heem.Starve2d.systems.PlayerSystem;
import com.heem.Starve2d.systems.RenderSystem;

public class GameWorld {
    private Engine engine; // движое

    private RenderSystem renderSystem; // рендер

    public GameWorld(){
        new FloorFactory(); // эта фигня загружает картинки пола
        new ItemsFactory(); // картинки предметов
        addSystems(); // здесь добавляем системы, чтобы красиво было
        addEntities(); // здесь сущности
    }

    private void addSystems() {
        engine = new Engine(); // создание
        renderSystem = new RenderSystem();
        engine.addSystem(renderSystem);
        engine.addSystem(new PlayerSystem(renderSystem.camera));
        engine.addSystem(new FloorSystem(renderSystem.camera.position));
    }

    private void addEntities() {
        engine.addEntity(EntityFactory.createPlayer());
        EntityFactory.createFloor(engine);
        EntityFactory.createItemsForHand(engine);
    }

    public void render(float delta){
        engine.update(delta);
    } // update
    public void resize(int width, int height) {
        renderSystem.resize(width, height);
    }
}
