package com.heem.Starve2d.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.heem.Starve2d.components.FloorComponent;

public class FloorSystem extends EntitySystem {
    private Vector3 position;
    private Vector2 currentFloor = new Vector2(0, 0);
    private ImmutableArray<Entity> floors;

    public FloorSystem(Vector3 position) {
        this.position = position;
    }

    @Override
    public void addedToEngine(Engine engine) {
        floors = engine.getEntitiesFor(Family.all(FloorComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {
        Vector2 camPos = new Vector2((int)position.x / 1000, (int)position.y / 1000);
        if (!camPos.equals(currentFloor)){
            currentFloor = camPos.cpy();
        }

        for (int i = 0; i < floors.size(); i++){
            FloorComponent floorComponent = floors.get(i).getComponent(FloorComponent.class);
            floorComponent.needDraw = Math.abs(floorComponent.x - (int)currentFloor.x) < 2 &&
                Math.abs(floorComponent.y - (int)currentFloor.y) < 2;
        }
    }
}
