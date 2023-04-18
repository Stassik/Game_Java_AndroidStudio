package com.mygdx.game.units;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;

public abstract class Warrior extends BaseHero{
    public Warrior(int x, int y, float hp, int[] damage, int def, int distance, String className, int reserve, int priority, String status) {
        super(x, y, hp, damage, def, distance, className, reserve, priority, status, '\u2694');
    }
    @Override
    public void step(ArrayList<BaseHero> enemies, ArrayList<BaseHero> friends,PriorityQueue<BaseHero> allTeam) {
        if (!this.status.equals("died")){
            BaseHero enemy = findHero(enemies);
            if (enemy != null) {
                double dist = Math.sqrt((enemy.pos.x - this.pos.x) * (enemy.pos.x - this.pos.x) + (enemy.pos.y - this.pos.y) * (enemy.pos.y - this.pos.y));
                if (dist <= this.distance) {
                    attack(enemy, friends);
                } else {
                    this.pos.changePosition(enemy, this, allTeam);
                }
            }
        }
    }

    private void attack (BaseHero enemy, ArrayList <BaseHero> friends) {
        int[] crash = chooseDamage();
        enemy.hp = enemy.hp * (1 + enemy.def/100) - new Random().nextInt(crash[0], crash[1]);
        if (enemy.hp <= 0) {
            enemy.hp = 0;
            enemy.status = "died";
        }
    }

    private int[] chooseDamage () {

        if (reserve == maxReserve) {
            int[] criticalDamage = new int[] {this.damage[0]*2, this.damage[1]*2};
            this.reserve -= 5;
            return criticalDamage;
        } else {
            this.reserve ++;
            return this.damage;
        }

    }
}
