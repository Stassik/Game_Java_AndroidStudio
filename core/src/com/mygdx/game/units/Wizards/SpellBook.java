package com.mygdx.game.units.Wizards;

public enum SpellBook {
    fireBallOne(1, 2), fireBallTwo(5, 3), fireBallThree(10, 5),
    healingOne(1, -1), healingTwo(2, -5), healingThree(3, -10),
    raiseSpiritOne(1, -1), raiseSpiritTwo(2, -2), raiseSpiritThree(3, -3),
    toxicOne(2, 1), shield(5, 10);
   private final float power, cost;

    SpellBook(float cost, float power) {
        this.cost = cost;
        this.power = power;
    }
    public float getPower() {return power;};
    public float getCost() {return cost;};
}
