package com.mygdx.game.units.Warriors;

import com.mygdx.game.units.Warrior;

public class Spearman extends Warrior {
    // Сила духа равна вначале 0. При нанесении ударов сила духа повышается на количество нанесенного урона. При достижении максимального значения духа - супер удар
    public Spearman(int x, int y) {
        super(x, y, 100, new int[]{6, 11}, 20, 2, "Копейщик", 0, 10, "stand");
        maxReserve = 5;
    }
}
