package com.mygdx.game.units.Warriors;

import com.mygdx.game.units.Warrior;

public class Robber extends Warrior {
    public Robber(int x, int y) {
        super(x, y, 100, new int[]{6, 11}, 20, 2, "Разбойник", 0, 10, "stand");
        maxReserve = 5;
    }
}
