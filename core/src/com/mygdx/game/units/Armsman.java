package com.mygdx.game.units;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Armsman extends  BaseHero{
    //Непонятно откуда беруться у него стрелы и как он их передает... Он куда-то за ними ходит и потом должен дойти до лучника чтобы передать
    //или есть какой-то запас этих стрел и они передаются "по воздуху"?
    public Armsman(int x, int y) {
        super(x, y, 100, new int[]{1, 3}, 0, 1, "Оруженосец", 0, 0, "stand", '\u2692');
    }

    @Override
    public void step(ArrayList<BaseHero> enemies, ArrayList<BaseHero> friends, PriorityQueue<BaseHero> allTeam) {


    }


}
