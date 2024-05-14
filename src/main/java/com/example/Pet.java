package com.example;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


/*
TODO: Implement queue request for petadoption system. 
 */
interface ReadCSV {
    public void readPetsFromCSV(String filePath) throws IOException;
}

public abstract class Pet {
    static List<Pet> pets = new ArrayList<>();
    static HashMap<Pet, Account> petAdoptionList = new HashMap<>();
    protected String name;
    protected String animalType;
    protected int age;
    protected double weight;
    protected String gender;
    protected String breed;
    protected String adoptionList;

    public Pet(String name, String animalType, int age, double weight, String gender) {
        this.name = name;
        this.animalType = animalType;
        this.age = age;
        this.weight = weight;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAnimalType() {
        return animalType;
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBreed() {
        return breed;
    }
    public void setBreed(String breed) {
        this.breed = breed;
    }
    public abstract void ASCIIArt();

    public void description() {
        System.out.printf("%s %s %s %s %s","Name","Animal Type","Age", "Weight", "Gender");
        System.out.printf("%s %s %d %f %s",name, animalType, age, weight, gender);
    }
}

// Tiap class harus punya method khasnya
class Dog extends Pet implements ReadCSV {
    public Dog(String name, int age, double weight, String gender) {
        super(name, "dog", age, weight, gender);
    }

    @Override
    public void ASCIIArt() {
        System.out.println("\n" +
                "   |\\|\\\n" +
                "  ..    \\       .\n" +
                "o--     \\\\    / @)\n" +
                " v__///\\\\\\\\__/ @\n" +
                "   {           }\n" +
                "    {  } \\\\\\{  }\n" +
                "    <_|      <_|\n");
    }

    public void makeaNoise() {
        System.out.println("woof woof!");
    }

    public void readPetsFromCSV(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                String name = fields[0].trim();
                int age = Integer.parseInt(fields[1].trim());
                double weight = Integer.parseInt(fields[2].trim());
                String gender = fields[3].trim();
                Pet pet = new Dog(name, age, weight, gender);
                pets.add(pet);
            }
        }
    }
}

class Cat extends Pet implements ReadCSV {
    public Cat(String name, int age, double weight, String gender) {
        super(name, "cat", age, weight, gender);
    }

    @Override
    public void ASCIIArt() {
        System.out.println("\n" +
                "    /\\_____/\\\n" +
                "   /  o   o  \\\n" +
                "  ( ==  ^  == )\n" +
                "   )         (\n" +
                "  (           )\n" +
                " ( (  )   (  ) )\n" +
                "(__(__)___(__)__)\n");
    }

    public void makeaNoise() {
        System.out.println("meow");
    }

    public void readPetsFromCSV(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                String name = fields[0].trim();
                int age = Integer.parseInt(fields[1].trim());
                double weight = Integer.parseInt(fields[2].trim());
                String gender = fields[3].trim();
                Pet pet = new Cat(name, age, weight, gender);
                pets.add(pet);
            }
        }
    }
}

class Bird extends Pet implements ReadCSV {
    public Bird(String name, int age, double weight, String gender) {
        super(name, "bird", age, weight, gender);
    }

    @Override
    public void ASCIIArt() {
        System.out.println("\n" +
                "   (\n" +
                "  `-`-.\n" +
                "  '( @ >\n" +
                "   _) (\n" +
                "  /    )\n" +
                " /_,'  / \n" +
                "   \\  / \n" +
                "===m\"\"m===\n");
    }

    public void makeaNoise() {
        System.out.println("chirp chirp!");
    }

    public void readPetsFromCSV(String filePath) throws IOException {
        List<Pet> pets = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                String name = fields[0].trim();
                int age = Integer.parseInt(fields[1].trim());
                double weight = Integer.parseInt(fields[2].trim());
                String gender = fields[3].trim();
                Pet pet = new Bird(name, age, weight, gender);
                pets.add(pet);
            }
        }
    }

}

class Bunny extends Pet implements ReadCSV {
    static final String animalType = "bunny";

    public Bunny(String name, int age, double weight, String gender) {
        super(name, animalType, age, (int) weight, gender);
    }

    @Override
    public void ASCIIArt() {
        System.out.println("\n" +
                "       _\n" +
                "      (\\\\\n" +
                "       \\||\n" +
                "     __(_\";\n" +
                "    /    \\\n" +
                "   {}___)\\)_\n");
    }

    public void makeaNoise() {
        System.out.println("wheee");
    }

    public void readPetsFromCSV(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                String name = fields[0].trim();
                int age = Integer.parseInt(fields[1].trim());
                double weight = Integer.parseInt(fields[2].trim());
                String gender = fields[3].trim();
                Pet pet = new Bunny(name, age, weight, gender);
                pets.add(pet);
            }
        }
    }
}