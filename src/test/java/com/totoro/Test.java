package com.totoro;

public class Test {
    public static void main(String[] args) {

        double profit = 1000;
        double[] list = new double[10];

        for (int i = 0; i < 2; i++) {
            System.out.println((i+1)+"年总利润=" + profit);
            if (i == 0){
                list[i] = profit;
            } else {

                double fenzou = 0;

                for (int v = 0; v < list.length-1; v++) {
                    if (list[v] == 0){
                        continue;
                    }
                    double v1 = list[v] / 10;
                    System.out.println((v+1)+"的第" + i+2 + "年利润" + v1);
                    fenzou = fenzou+v1;
                }
                list[i] = profit - fenzou;
            }

            System.out.println("list"+i+"=" + list[i]);

            profit = profit * 1.1;

        }

        for (double v : list) {
            System.out.println(v);
        }

    }
}
