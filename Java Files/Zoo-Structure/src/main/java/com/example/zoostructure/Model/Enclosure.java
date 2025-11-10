package com.example.zoostructure.Model;

import java.util.ArrayList;
import java.util.List;

public class Enclosure implements EnclosureCollection {

        private String name;
        private List<Animal> animals;

        /**
         * Constructs an {@code com.example.zoostructure.Model.Enclosure} with the specified name.
         *
         * @param name the name of the enclosure
         * @throws IllegalArgumentException if name is null or empty
         */
        public Enclosure(String name) {
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("Zoo Enclosure name cannot be null or empty.");
            }
            this.name = name;
            this.animals = new ArrayList<>();
        }
    /**
     * Returns the name of the enclosure.
     * * @return the name of the enclosure.
     */
        public String getName() {
            return name;
        }

    /**
     * Returns a safe, unmodifiable copy of the list of animals currently in the enclosure.
     * * @return a {@code List} of {@link Animal} objects.
     */
        public List<Animal> getAnimals() {
            return new ArrayList<>(animals);
        }

    /**
     * Adds an {@link Animal} to this enclosure.
     * * @param animal the animal to be added.
     * @throws IllegalArgumentException if {@code animal} is null.
     */
        public void addAnimal(Animal animal) {
            if (animal == null) {
                throw new IllegalArgumentException("Animal cannot be null.");
            }
            animals.add(animal);
        }

    /**
     * Removes an animal from the enclosure by its name (case-insensitive).
     * * @param name the name of the animal to remove.
     * @return {@code true} if an animal was found and removed; {@code false} otherwise.
     */
        public boolean removeAnimal(String name) {
            return animals.removeIf(a -> a.getName().equalsIgnoreCase(name));
        }

    /**
     * **Unsupported Operation for a Leaf Component.**
     * An individual enclosure cannot contain other collections (sections or enclosures).
     *
     * @param enclosure the collection to add (ignored).
     * @throws UnsupportedOperationException always, as this is a leaf component.
     */
    @Override
    public void addCollection(EnclosureCollection enclosure) {

        throw new UnsupportedOperationException("Cannot add a collection to an individual Enclosure.");
    }

    /**
     * **Unsupported Operation for a Leaf Component.**
     * An individual enclosure cannot remove other collections (sections or enclosures).
     *
     * @param enclosure the collection to remove (ignored).
     * @throws UnsupportedOperationException always, as this is a leaf component.
     */
    @Override
    public void removeCollection(EnclosureCollection enclosure) {

        throw new UnsupportedOperationException("Cannot remove a collection from an individual Enclosure.");
    }

    /**
     * Displays the name of the enclosure and lists all the animals it contains.
     * This fulfills the contract of the {@link EnclosureCollection} interface.
     */
        @Override
        public void display() {
            System.out.println("Zoo Enclosure: " + name);
            for (Animal animal : animals) {
                System.out.println("  - " + animal.getName() + " (" + animal.getAge() + " years)");
            }
        }
    }

