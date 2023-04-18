package com.mygdx.game;
import java.util.ArrayList;

import com.mygdx.game.units.*;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static ArrayList<BaseHero> holyTeam;
    public static ArrayList<BaseHero> darkTeam;
    public static PriorityQueue<BaseHero> allTeam;

    public static void main(String[] args) {

        createTeams();

//        Scanner input = new Scanner(System.in);
        while ( BaseHero.checkedTeam(holyTeam) && BaseHero.checkedTeam(darkTeam)) {
//        while (true) {
            allTeam = BaseHero.sortedPriority(darkTeam, holyTeam);
            View.view();
//            input.nextLine();
            for (BaseHero unit : allTeam) {
                if (holyTeam.contains(unit)) {
//                    System.out.println("Игрок: " + unit.toString() + " | Ближайший враг: " + unit.findEnemy(holyTeam).toString());
                    unit.step(darkTeam, holyTeam, allTeam);
                } else {
//                    System.out.println("Игрок: " + unit.toString() + " | Ближайший враг: " + unit.findEnemy(holyTeam).toString());
                    unit.step(holyTeam, darkTeam, allTeam);
                }
            }
            for (BaseHero u: allTeam) {
                if (u.status.equals("busy")) {
                    u.status = "stand";
                }
            }
        }
        if (BaseHero.checkedTeam(holyTeam)) {
            System.out.println("Победила команда Света!");
        } else {
            System.out.println("Победила команда Тьмы!");
        }
    }

    public static void createTeams() {
        holyTeam = BaseHero.generateTeam(1);
        darkTeam = BaseHero.generateTeam(2);
    }
}