package com.company;

import java.util.Random;
import java.util.Scanner;

public class Table {
    Point[][] point;
    int n;
    int bombCount;
//Создает таблицу, рандомно заполняет бомбы
    public Table( int n, int countBombs){
        this.bombCount = countBombs;
        this.n = n;
        Random random = new Random();

        Point[][] point;
        point = new Point[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                point[i][j] = new Point();
            }
            }

        //генерация бомб
        //заполнение колличества бомб вокруг в поле countBombAround
        for(int i = 0; i <countBombs; i++) {
            int x = random.nextInt(n);
            int y = random.nextInt(n);

            point[x][y].setBomb(true);

            if ((x - 1 >= 0) & (y - 1 >= 0) & (x - 1 < n) & (y - 1 < n))
                point[x - 1][y - 1].plusCountBombAround();

            if ((x - 1 >= 0) & (y >= 0) & (x - 1 < n) & (y < n))
                point[x - 1][y].plusCountBombAround();

            if ((x - 1 >= 0) & (y + 1 >= 0) & (x - 1 < n) & (y + 1 < n))
                point[x - 1][y + 1].plusCountBombAround();

            if ((x >= 0) & (y - 1 >= 0) & (x < n) & (y - 1 < n))
                point[x][y - 1].plusCountBombAround();

            if ((x >= 0) & (y + 1 >= 0) & (x < n) & (y + 1 < n))
                point[x][y + 1].plusCountBombAround();

            if ((x + 1 >= 0) & (y - 1 >= 0) & (x + 1 < n) & (y - 1 < n))
                point[x + 1][y - 1].plusCountBombAround();

            if ((x + 1 >= 0) & (y >= 0) & (x + 1 < n) & (y < n))
                point[x + 1][y].plusCountBombAround();

            if ((x + 1 >= 0) & (y + 1 >= 0) & (x + 1 < n) & (y + 1 < n))
                point[x + 1][y + 1].plusCountBombAround();
        }
        this.point = point;

    }
//Выводит текущую таблицу для игрока
    public void printTable(){

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!this.point[i][j].isOpen())
                        if (this.point[i][j].isBomb())
                            System.out.print(" x ");
                        else
                            System.out.print(" x ");
                    else {

                        System.out.print(" "+this.point[i][j].getCountBombAround()+" ");

                    }

                }
                System.out.print("\n");
                // write your code here
            }
         }

        // игра
    public void playTable() {
        while (this.bombCount > 0) {
            System.out.println('\n' + '\n' + "Введите координаты" + '\n');
            Scanner scanner = new Scanner(System.in);
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            this.point[x][y].open();
            if (this.point[x][y].isBomb()) {
                System.out.print("Вы проиграли");
                break;
            }

            this.printTable();
        }
        if (this.bombCount == 0)
            System.out.print("Вы выйграли!!!");
    }
    //Выводит всю таблицу для разработчик 1-бомба 0-пустая
    public void printAllTable(){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                    if(this.point[i][j].isBomb())
                        System.out.print(1+" ");
                    else
                        System.out.print(0+" ");


            }
            System.out.print("\n");

        }
    }
}
