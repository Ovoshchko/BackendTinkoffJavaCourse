package edu.hw4;

public record Animal(
    String name,
    Type type,
    Sex sex,
    int age,
    int height,
    int weight,
    boolean bites
) {

    enum Type {
        CAT(4), DOG(4), BIRD(2), FISH(0), SPIDER(8);

        private final int paws;

        Type(int paws) {
            this.paws = paws;
        }

        public int getPaws() {
            return paws;
        }
    }

    enum Sex {
        M, F
    }

}
