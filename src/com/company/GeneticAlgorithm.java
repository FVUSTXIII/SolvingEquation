package com.company;

public class GeneticAlgorithm {
    public static final int POPULATION_SIZE = 6;
    public static final int[] TARGET_CHROMOSOME = {1,0,1,0};
    private static final double MUTATION_RATE = 0.5;
    public static final int NUMB_OF_ELITE_CHROMOSOMES = 1;
    public static final int TOURNAMENT_SELECTION_SIZE = 4;
    public Population evolve(Population population) {
        return mutatePopulation(crossoverPopulation(population));
    }
    private Population crossoverPopulation(Population population) {
        Population crossover_population = new Population((population.getChromosomes().length));
        for (int x = 0; x < NUMB_OF_ELITE_CHROMOSOMES; x++) {
            crossover_population.getChromosomes()[x] = population.getChromosomes()[x];
        }
        for (int x = NUMB_OF_ELITE_CHROMOSOMES; x < population.getChromosomes().length; x++) {
            Chromosome chromosome_1 = selectTournamentPopulation(population).getChromosomes()[0];
            Chromosome chromosome_2 = selectTournamentPopulation(population).getChromosomes()[0];
            crossover_population.getChromosomes()[x] = crossoverChromosome(chromosome_1, chromosome_2);
        }
        return crossover_population;
    }
    private Population mutatePopulation(Population population) {
        Population mutated_population = new Population(population.getChromosomes().length);
        for (int x = 0; x < NUMB_OF_ELITE_CHROMOSOMES; x++) {
            mutated_population.getChromosomes()[x] = population.getChromosomes()[x];
        }
        for (int x = NUMB_OF_ELITE_CHROMOSOMES; x < population.getChromosomes().length; x++) {
            mutated_population.getChromosomes()[x] = mutateChromosome(population.getChromosomes()[x]);
        }
        return mutated_population;
    }
    private Chromosome mutateChromosome(Chromosome chromosome) {
        Chromosome mutated_chromosome = new Chromosome(TARGET_CHROMOSOME.length);
        for (int x = 0; x < chromosome.getGenes().length; x++) {
            if (Math.random() < MUTATION_RATE) {
                if (Math.random() < 0.5) {
                    mutated_chromosome.getGenes()[x] = 1;
                } else {
                    mutated_chromosome.getGenes()[x] = 0;
                }
            } else {
                mutated_chromosome.getGenes()[x] = chromosome.getGenes()[x];
            }
        }
        return mutated_chromosome;
    }
    private Chromosome crossoverChromosome(Chromosome chromosome_1, Chromosome chromosome_2) {
        Chromosome crossover_chromosome = new Chromosome(TARGET_CHROMOSOME.length);
        for (int x = 0; x < chromosome_1.getGenes().length; x++) {
            if (Math.random() < 0.95) {
                crossover_chromosome.getGenes()[x] = chromosome_1.getGenes()[x];
            } else {
                crossover_chromosome.getGenes()[x] = chromosome_2.getGenes()[x];
            }
        }
        return crossover_chromosome;
    }
    private Population selectTournamentPopulation(Population population) {
        Population tournament_population = new Population(TOURNAMENT_SELECTION_SIZE);
        for (int x = 0; x < TOURNAMENT_SELECTION_SIZE; x++) {
            tournament_population.getChromosomes()[x] = population.getChromosomes()[(int)(Math.random()*population.getChromosomes().length)];
        }
        tournament_population.sortChromosomesByFitness();
        return  tournament_population;
    }
}
