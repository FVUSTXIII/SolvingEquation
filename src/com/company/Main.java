package com.company;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Population population = new Population(GeneticAlgorithm.POPULATION_SIZE).initializePopulation();
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm();
        boolean flag = false;
        int x = 0;
        System.out.println("---------------------------------------------");
        System.out.println("Generation # " + x);
        function(printPopulation(population, "a + 2b - 3c + d + 4e + f = 30"));
        while (!flag) {
            x++;
            System.out.println("---------------------------------------------");
            System.out.println("Generation # " + x);
            population = geneticAlgorithm.evolve(population);
            population.sortChromosomesByFitness();
            flag = function(printPopulation(population, "a + 2b - 3c + d + 4e + f = 30"));
        }
    }
    public static int[] printPopulation(Population population, String heading) {
        int[] population_values = new int[GeneticAlgorithm.POPULATION_SIZE];
        System.out.println(heading);
        System.out.println("---------------------------------------------");
        for(int x = 0; x < population.getChromosomes().length; x++) {
            System.out.println("Chromosome # " + x + "   :   "
                    + Arrays.toString(population.getChromosomes()[x].getGenes()) +
                    "\t Value: " + ConvertBinToDec(population.getChromosomes()[x].getGenes()));
            population_values[x] = ConvertBinToDec(population.getChromosomes()[x].getGenes());
        }
        return population_values;
    }
    public static int ConvertBinToDec(int[] arr) {
        char[] decimal_convertion = new char[arr.length];
        int i = 0;
        for(int a: arr) {
            decimal_convertion[i] = Integer.toString(a).charAt(0);
            i++;
        }
        String decimal_convertion_s = new String(decimal_convertion);
        return Integer.parseInt(decimal_convertion_s, 2);
    }
    public static boolean function (int[] v) {
        System.out.println("a + 2b - 3vc + d + 4e + f = " + Math.abs(function(v, false)));
        return (100 - (Math.abs(function(v, false)) - 30) == 100);
    }
    public static int function(int[] v, boolean a) {
        return (v[0]) + (2*v[1]) - (3*v[2]) + (v[3]) + (4*v[4]) + (v[5]);
    }
}
