package com.example;

import org.apache.commons.text.similarity.LevenshteinDistance;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Collections;
import java.util.Set;
import java.util.stream.*;
/*
TODO: 
Implement circular linklist in pet adoption system for navigation
 */
public class Application {
    static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {

        boolean isConnected = false;
        while (!isConnected) {
            System.out.println("Pet Adoption System" +
                    "====================");
            System.out.println("Connect account\n1. Sign up\n2. sign in\n3. Exit");
            switch (keyboard.nextLine()) {
                case "1":
                    if (!signUp()) {
                        return;
                    }
                    break;
                case "2":
                    if (!signIn()) {
                        return;
                    }
                    break;
                case "3":
                    return;
                default:
                    System.err.println("Choose one of the given options");
                    continue;
            }
            break;
        }
        while (true) {
            System.out.println("1. Search pet\n2. Animal care tips and FAQ\n3. Exit");
            switch (keyboard.nextLine()) {
                case "1":
                    /*
                     * System.out.print("Search term: ");
                     * for(Pet p : search(keyboard.nextLine(), Pet.petList)){
                     * System.out.println(p.animalType + " " + p.name);
                     * }
                     * public static List<Pet> search(String term, List<Pet> pets) {
                     * List<Pet> matches = new ArrayList<>();
                     * for (Pet p : pets) {
                     * if (compare(term, p.name)) {
                     * matches.add(p);
                     * }
                     * }
                     * return matches;
                     * }
                     */
                    search();
                    break;
                case "2":
                    FAQ();
                    break;
                case "3":
                    return;
                default:
                    System.err.println("Choose one of the given options");
                    continue;
            }
            break;
        }
    }

    public static boolean signUp() {
        while (true) {
            System.out.print("Enter username (Leave empty to exit):");
            String username = keyboard.nextLine();
            if (username.equals("")) {
                return false;
            }
            if (Account.getAccountMap().containsKey(username)) {
                System.err.println("Username already exists");
                continue;
            }

            while (true) {
                System.out.println("Enter password (Leave empty to edit the username)");
                String password = keyboard.nextLine();
                if (password.equals("")) {
                    break;
                }
                // Check if password is null or less than 8 characters
                if (password == null || password.length() < 8) {
                    System.err.println("Password must contain at least 8 characters.");
                    continue;
                }

                // Regular expressions for lower case, upper case, and special character
                String lowerCaseChars = "(.*[a-z].*)";
                String upperCaseChars = "(.*[A-Z].*)";
                String specialChars = "(.*[!\"#$%&'()+,-./:;<=>?@\\[\\]^_`{|}~].*)";

                if (!(Pattern.compile(lowerCaseChars).matcher(password).matches() &&
                        Pattern.compile(upperCaseChars).matcher(password).matches() &&
                        Pattern.compile(specialChars).matcher(password).matches())) {
                    System.err.println(
                            "Password must contain at least one lowercase letter, one uppercase letter, and one special character.");
                    continue; // Ask for password again
                }

                // If all conditions are met, set the account credentials and return true
                Account.setAccountMap(username, password);
                return true;
            }
        }
    }

    public static boolean signIn() {
        // Less memory used with byte datatype as it is only used for a very small range
        // Place it here to prevent it from being reset to 0 whenever the loop iterates
        byte passwordMisses = 0;

        while (true) {
            System.out.print("Enter username (Leave empty to exit):");
            String username = keyboard.nextLine();
            if (username.equals("")) {
                return false;
            }
            if (!Account.getAccountMap().containsKey(username)) {
                System.err.println("Username does not exist.");
                continue;
            }
            while (true) {
                System.out.println("Enter password (Leave empty to edit the username)");
                String password = keyboard.nextLine();
                if (password.equals("")) {
                    break;
                }

                if (!Account.getAccountMap().get(username).equals(password)) {
                    System.err.println(
                            "Incorrect password. " + passwordMisses + " attempt" + (passwordMisses > 1 ? "s" : "")
                                    + " left.");
                    passwordMisses++;
                    if (passwordMisses == 3) {
                        System.err.println("You have reached the maximum of 3 attempts allowed.");
                        return false;
                    }
                    continue;
                }
            }
            return true;
        }
    }

    static List<Pet> matches = new ArrayList<>(Pet.pets);

    public static void search() {
        System.out.println("Search by (Separate just the number by comma to choose multiple):" +
                "1. Name, 2. Animal Type, 3. Age\n4. Weight, 5. Gender, 6. Breed");

        // Store only distinct values
        Set<String> filter = new HashSet<>();

        for (String key : Arrays.asList("1", "2", "3", "4", "5", "6")) {
            List<String> inputList = Arrays.asList(keyboard.nextLine().trim().split(","));
            if (!inputList.contains(key)) {
                System.err.println("Choose one of the given options");
                continue;
            } else {
                filter.add(key);
            }
        }

        Comparator<Pet> comparator = null;
        for (String key : filter) {

            Comparator<Pet> nextComparator = null;
            switch (key) {
                case "1":
                    System.out.print("Enter pet name: ");
                    nextComparator = Comparator.comparing(pet -> compare(pet, pet.name, keyboard.nextLine()));
                    break;

                case "2":
                    System.out.print("Enter pet type: ");
                    nextComparator = Comparator.comparing(pet -> compare(pet, pet.animalType, keyboard.nextLine()));
                    break;

                case "3":
                    System.out.print("Enter pet age: ");
                    nextComparator = Comparator.comparing(pet -> compare(pet, pet.age, keyboard.nextInt()));
                    break;

                case "4":
                    System.out.print("Enter pet weight: ");
                    nextComparator = Comparator.comparing(pet -> compare(pet, pet.weight, keyboard.nextInt()));
                    break;

                case "5":
                    System.out.print("Enter pet gender: ");
                    nextComparator = Comparator.comparing(pet -> compare(pet, pet.gender, keyboard.nextLine()));
                    break;

                case "6":
                    System.out.print("Enter pet breed: ");
                    nextComparator = Comparator.comparing(pet -> compare(pet, pet.breed, keyboard.nextLine()));
                    break;
            }
            if (comparator == null) {
                comparator = nextComparator;
            } else {
                comparator = comparator.thenComparing(nextComparator);
            }
        }
        Collections.sort(matches, comparator);

        System.out.println("Found " + matches.size() + "relevant result" + (matches.size() > 1 ? "s" : "") + ".");
        for (int i = 0; i < 5; i++) {
            matches.get(i).description();
        }
        if (matches.size() > 5) {
            while (true) {
                System.out.println("Show more result" + (matches.size() > 6 ? "s" : "" + " ? (y/n)"));
                switch (keyboard.nextLine()) {
                    case "y", "Y":
                        System.out.print("Enter number of rows to show: ");
                        int input = 0;
                        try {
                            input = keyboard.nextInt();
                        } catch (InputMismatchException e) {
                            System.err.println("Input must be numeric.");
                        }
                        for (int i = 5; i < input; i++) {
                            matches.get(i).description();
                        }
                        break;
                    case "n", "N":
                        return;
                    default:
                        System.err.println("Choose one of the given options");
                        continue;
                }
            }

        }
    }

    private static final LevenshteinDistance LEVENSHTEIN = new LevenshteinDistance();

    private static int compare(Pet pet, String term, String candidate) {

        int distance = LEVENSHTEIN.apply(term, candidate);
        // Search range tolerance of 30% for string values
        if (distance >= (term.toCharArray().length * 30 / 100)) {
            matches.remove(pet);
        }
        return distance;
    }

    private static int compare(Pet pet, int term, int candidate) {
        // Search range tolerance of 30% for integer values
        if (candidate <= (term * 70 / 100) || candidate >= (term * 130 / 100)) {
            matches.remove(pet);
        }
        return candidate > term ? candidate - term : term - candidate;
    }

    private static double compare(Pet pet, double term, int candidate) {
        // Search range tolerance of 30% for integer values
        if (candidate <= (term * 70 / 100) || candidate >= (term * 130 / 100)) {
            matches.remove(pet);
        }
        return candidate > term ? candidate - term : term - candidate;
    }

    private static Map<String, String> faqDatabase;

    public static void FAQ() {
        initializeFAQs();

        Scanner scanner = new Scanner(System.in);
        String userInput;

        System.out.println("Welcome to the Pet FAQ system!");
        System.out.println("Enter the type of pet you have (cat, dog, bird, bunny) or type 'exit' to quit:");

        while (true) {
            userInput = scanner.nextLine().toLowerCase();
            if (userInput.equals("exit")) {
                System.out.println("Goodbye!");
                break;
            } else if (faqDatabase.containsKey(userInput)) {
                System.out.println(faqDatabase.get(userInput));
            } else {
                System.out.println("Sorry, we don't have FAQs for that pet type. Please try again.");
            }
        }
        scanner.close();
        return;
    }

    private static void initializeFAQs() {
        faqDatabase = new HashMap<>();

        faqDatabase.put("cat", "FAQs about Cats:\n" +
                "Q: What do cats eat?\n" +
                "A: Cats are obligate carnivores, meaning they primarily eat meat.\n" +
                "Q: How often should I take my cat to the vet?\n" +
                "A: Cats should have annual check-ups, and more frequently if they have health issues or are seniors.\n"
                +
                "Q: How long do cats live?" +
                "A: The average lifespan of an indoor cat is 13 to 17 years. ");

        faqDatabase.put("dog", "FAQs about Dogs:\n" +
                "Q: What should I feed my dog?\n" +
                "A: Dogs need a balanced diet of protein, carbohydrates, and fats.\n" +
                "Q: How often should I walk my dog?\n" +
                "A: Dogs generally need at least one walk per day, but it depends on the breed and energy level.");

        faqDatabase.put("bird", "FAQs about Birds:\n" +
                "Q: What do birds eat?\n" +
                "A: Birds eat a variety of seeds, fruits, and insects depending on the species.\n" +
                "Q: How do I train my bird?\n" +
                "A: Birds can be trained using positive reinforcement techniques, and patience is key.");

        faqDatabase.put("bunny", "FAQs about Bunnies:\n" +
                "Q: What do bunnies eat?\n" +
                "A: Bunnies need a diet high in fiber, so they primarily eat hay, vegetables, and some pellets.\n" +
                "Q: How do I litter train my bunny?\n" +
                "A: Bunnies can be litter trained similarly to cats, using a litter box filled with bunny-safe litter.");
    }

}
