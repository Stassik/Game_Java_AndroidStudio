package com.mygdx.game.units;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Position {
    public int x, y;
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    protected void changePosition(BaseHero unit, BaseHero hero, PriorityQueue<BaseHero> allTeam) {
        if (unit.pos.y > hero.pos.y) {
            if (checkingForEmptinessY(hero.pos, allTeam, 1)) {
                hero.pos.y++;
            } else {
                changePositionX(unit, hero, allTeam);
            }
        } else if (unit.pos.y < hero.pos.y) {
            if (checkingForEmptinessY(hero.pos, allTeam, -1)) {
                hero.pos.y--;
            } else {
                changePositionX(unit, hero, allTeam);
            }
        } else {
            changePositionX(unit, hero, allTeam);
        }
    }

    private void changePositionX(BaseHero unit, BaseHero hero, PriorityQueue<BaseHero> allTeam) {
        if (unit.pos.x > hero.pos.x && checkingForEmptinessX(hero.pos, allTeam, 1)) {
            hero.pos.x++;
        } else if (unit.pos.x < hero.pos.x && checkingForEmptinessX(hero.pos, allTeam, -1)) {
            hero.pos.x--;
        }
    }

    private boolean checkingForEmptinessX(Position pos, PriorityQueue<BaseHero> allTeam, int sign) {
        int newPosX = pos.x + sign;;
        for (BaseHero unit: allTeam) {
            if (!unit.status.equals("died")) {
                if ((newPosX == unit.pos.x) && (pos.y == unit.pos.y) ) {
                    return false;
                }
            }
        }
        return true;
    }
    private boolean checkingForEmptinessY(Position pos, PriorityQueue<BaseHero> allTeam, int sign) {
        int newPosY = pos.y + sign;;
        for (BaseHero unit: allTeam) {
            if (unit.hp > 0) {
                if ((newPosY == unit.pos.y) && (pos.x == unit.pos.x)) {
                    return false;
                }
            }
        }
        return true;
    }
}
