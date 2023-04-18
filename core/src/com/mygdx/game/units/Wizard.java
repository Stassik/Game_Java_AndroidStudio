package com.mygdx.game.units;


import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;

public abstract class Wizard extends BaseHero{
    //protected Spells[] spellsBook = new Spells[3];
    public Wizard(int x, int y, float hp, int[] damage, int def, int distance, String className, int reserve, int priority, String status) {
        super(x, y, hp, damage, def, distance, className, reserve, priority, status, '\u2695');
    }
    @Override
    public void step(ArrayList<BaseHero> enemies, ArrayList<BaseHero> friends,PriorityQueue<BaseHero> allTeam) {
        if (!this.status.equals("died")){
            BaseHero unit = findUnit(friends);
            if (unit != null) {
                double dist = Math.sqrt((unit.pos.x - this.pos.x) * (unit.pos.x - this.pos.x) + (unit.pos.y - this.pos.y) * (unit.pos.y - this.pos.y));
                if (dist <= this.distance) {
                    healingUnit(unit);
                } else {
                    this.pos.changePosition(unit, this, allTeam);
                }
            }
        }
    }

    private BaseHero findUnit(ArrayList<BaseHero> heroes) {
        double minDist = 10;//Math.sqrt((enemies.get(0).pos.x - this.pos.x) * (enemies.get(0).pos.x - this.pos.x) + (enemies.get(0).pos.y - this.pos.y) * (enemies.get(0).pos.y - this.pos.y));
        double dist;
        BaseHero closestUnit = null;
        for (BaseHero unit: heroes) {
            if (!unit.status.equals("died")) {
                if ( unit.hp != unit.maxHp) {
                    dist = Math.sqrt((unit.pos.x - this.pos.x) * (unit.pos.x - this.pos.x) + (unit.pos.y - this.pos.y) * (unit.pos.y - this.pos.y));
                    if (dist < minDist) {
                        minDist = dist;
                        closestUnit = unit;
                    }
                }
            }
        }
        if (closestUnit == null) return null;
        return closestUnit;
    }



    private void healingUnit(BaseHero unit) {
        int[] heal = chooseHealing(unit);
        if (heal != null) {
            unit.hp = unit.hp + new Random().nextInt(heal[0], heal[1]);
            if (unit.hp > unit.maxHp) {
                unit.hp = unit.maxHp;
            }
        }
    }

    private int[] chooseHealing (BaseHero unit) {
        if (this.reserve == this.maxReserve) {
            if (unit.hp <= unit.maxHp/2) {
                int[] megaHealing = new int[]{this.damage[0] * 2, this.damage[1] * 2};
                this.reserve -= 10;
                return megaHealing;
            } else {
                this.reserve -= 5;
                return this.damage;
            }
        } else if (this.reserve >= 5) {
            this.reserve -= 5;
            return this.damage;
        }else{
            this.reserve ++;
            return null;
        }
    }
}
