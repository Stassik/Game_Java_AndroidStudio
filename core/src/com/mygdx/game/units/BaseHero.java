package com.mygdx.game.units;

import com.mygdx.game.GameInterface;
import com.mygdx.game.Names;
import com.mygdx.game.units.Shooters.Arbalester;
import com.mygdx.game.units.Shooters.Sniper;
import com.mygdx.game.units.Warriors.Robber;
import com.mygdx.game.units.Warriors.Spearman;
import com.mygdx.game.units.Wizards.Mage;
import com.mygdx.game.units.Wizards.Monk;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

public abstract class BaseHero implements GameInterface {
    public String name;       //Имя
    public String className;   // Имя класса
    public Position pos;           // Координаты
    protected float hp, maxHp;             // Запас здоровья / Максимальный (начальный) запас здоровьz
    protected int[] damage;       // Минимальная и максимальная сила удара
    protected int def;            // Броня
    protected int distance;       // Дальность удара (воздействия)

    int priority; // Приоритет

    public String status;
    public char mark;

    protected int reserve, maxReserve;        // Думаю можно объединить стрелы (для арбалетчика и снайпера), ману (для мага и монаха) и боевой дух (для вора и копейщика)
    public int[] getCoords() {return new int[]{pos.x, pos.y};}

    public BaseHero (int x, int y, float hp, int[] damage, int def, int distance, String className, int reserve, int priority, String status, char mark) {
        this.name = getName();
        this.hp = this.maxHp = hp;
        this.damage = damage;
        this.def = def;
        this.distance = distance;
        this.className = className;
        this.reserve = this.maxReserve = reserve;
        this.priority = priority;
        this.status = status;
        this.mark = mark;
        pos = new Position(x, y);


    }
    @Override
    public char getInfo() {
        return this.mark;
    }

    @Override
    public String toString() {
        String str = className + " " + name + " Здоровье: " + hp + " Запас: " + reserve+ " Инициатива: " + priority + " Статус: " + status+ " Позиция: " + pos.x + " " + pos.y;
        return str;
    }
    @Override
    public float getHp() {
        return this.hp;
    }


    private String getName() {
        return Names.values()[new Random().nextInt(Names.values().length)].toString();
    }
    public static ArrayList<BaseHero> generateTeam(int team) {
        ArrayList<BaseHero> units = new ArrayList<>();
        int posY;
        if (team == 1) {
            posY = 1;

        } else {
            posY = 10;
        }
        for (int i = 1; i < 11; i++ ) {
            switch (new Random().nextInt(7)) {
                case 0:
                    units.add(new Armsman(i, posY));
                    break;
                case 1:
                    units.add(new Mage(i, posY));
                    break;
                case 2:
                    units.add(new Monk(i, posY));
                    break;
                case 3:
                    units.add(new Arbalester(i, posY));
                    break;
                case 4:
                    units.add(new Sniper(i, posY));
                    break;
                case 5:
                    units.add(new Robber(i, posY));
                    break;
                default:
                    units.add(new Spearman(i, posY));
            }
        }
        return units;
    }


    protected BaseHero findHero(ArrayList<BaseHero> heroes) {
        double minDist = 10;//Math.sqrt((enemies.get(0).pos.x - this.pos.x) * (enemies.get(0).pos.x - this.pos.x) + (enemies.get(0).pos.y - this.pos.y) * (enemies.get(0).pos.y - this.pos.y));
        double dist;
        BaseHero closestUnit = null;
        for (BaseHero unit: heroes) {
            if (!unit.status.equals("died")) {
                dist = Math.sqrt((unit.pos.x - this.pos.x) * (unit.pos.x - this.pos.x) + (unit.pos.y - this.pos.y) * (unit.pos.y - this.pos.y));
                if (dist < minDist) {
                    minDist = dist;
                    closestUnit = unit;
                }
            }
        }

        if (closestUnit == null) return null;
        return closestUnit;
    }

    public static PriorityQueue<BaseHero> sortedPriority(ArrayList<BaseHero> enemies, ArrayList<BaseHero> friends) {
        PriorityQueue<BaseHero> sortedList = new PriorityQueue<>(new Comparator<BaseHero>() {
            @Override
            public int compare(BaseHero o1, BaseHero o2) {
                if (o1.priority == o2.priority) {
                    int per2 = (int) (o2.hp * 100 / (o2.maxHp));
                    int per1 = (int) (o1.hp * 100 / (o1.maxHp));
                    return per2 - per1;
                } else {
                    return o2.priority - o1.priority;
                }
            }
        });


        sortedList.addAll(enemies);
        sortedList.addAll(friends);

        return sortedList;
    }

    public static boolean checkedTeam (ArrayList<BaseHero> team) {
        for (BaseHero unit: team) {
            if (!unit.status.equals("died")) return true;
        }
        return false;
    }
}
