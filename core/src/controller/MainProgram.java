package controller;

import heroes.*;

import java.util.ArrayList;
import java.util.Random;

public class MainProgram {

    public ArrayList<BaseHero> team1;
    public ArrayList<BaseHero> team2;
    public ArrayList<BaseHero> allTeam;

    public MainProgram() {

        Random random = new Random();

        team1 = createTeam(0, random);
        System.out.println();
        team2 = createTeam(3, random);
        System.out.println();

        allTeam = new ArrayList<>();
        allTeam.addAll(team1);
        allTeam.addAll(team2);

        allTeam.sort((hero1, hero2) -> hero2.initiative - hero1.initiative);

    }

    public static ArrayList<BaseHero> createTeam(int index, Random random) {

        ArrayList<BaseHero> team = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Coordinate coordinates = new Coordinate(index * 3, i);
            team.add(randomHero(index, random, coordinates));
        }

        return team;

    }

    public static BaseHero randomHero(int index, Random random, Coordinate coordinates) {

        int choice = index + random.nextInt(4);

        //если что-то пошло не так, пусть будет крестьянин
        switch (choice) {
            case 0:
                return new Magician(coordinates); //Колдун / Маг
            case 1:
                return new Crossbowman(coordinates); //Арбалетчик / Лучник
            case 2:
                return new Spearman(coordinates); //Копейщик / Пехота
            case 3:
                return new Peasant(coordinates); //Крестьянин
            case 4:
                return new Sniper(coordinates); //Снайпер
            case 5:
                return new Monk(coordinates); //Монах
            case 6:
                return new Outlaw(coordinates); //Разбойник / Вор
            default:
                return new Peasant(coordinates); //Крестьянин
        }
    }

    public boolean step() {

        boolean theEnd = false;

        for (BaseHero hero : allTeam) {
            if (team1.contains(hero)) {
                hero.step(1, team1, team2);
                if (hero.isWinner(team2)) {
                    System.out.println("Команда Green победила!");
                    theEnd = true;
                    break;
                }
            } else {
                hero.step(-1, team2, team1);
                if (hero.isWinner(team1)) {
                    System.out.println("Команда Blue победила!");
                    theEnd = true;
                    break;
                }
            }
        }

        return theEnd;

    }
}