package com.mygdx.game;

import com.mygdx.game.units.BaseHero;
import java.util.ArrayList;
import java.util.PriorityQueue;

public interface GameInterface {
    void step(ArrayList<BaseHero> enemies, ArrayList<BaseHero> friends, PriorityQueue<BaseHero> allTeam);
    char getInfo();
    float getHp();
}
