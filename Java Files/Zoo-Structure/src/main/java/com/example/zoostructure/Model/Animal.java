package com.example.zoostructure.Model;

/**
 * The {@code Animal} class represents an animal with a name and age.
 * It provides methods to access and modify these properties.
 * <p>
 * This class ensures that the age of the animal cannot be negative.
 * </p>
 *
 * @author Matt
 * @version 1.0
 */
public class Animal {

    /**
     * The name of the animal.
     */
    private String aName;

    /**
     * The age of the animal in years.
     */
    private double aAge;

    /**
     * Constructs a new {@code Animal} with the specified name and age.
     *
     * @param pName the name of the animal
     * @param pAge  the age of the animal in years; must be non-negative
     * @throws IllegalArgumentException if {@code pAge} is negative
     */
    public Animal(String pName, double pAge) {
        this.setName(pName);
        this.setAge(pAge);
    }

    /**
     * Returns the name of the animal.
     *
     * @return the name of the animal
     */
    public String getName() {
        return aName;
    }

    /**
     * Sets the name of the animal.
     * <p>
     * The name must not be {@code null} or empty.
     * </p>
     *
     * @param pName the new name of the animal
     * @throws IllegalArgumentException if {@code pName} is {@code null} or empty
     */

    public void setName(String pName) {
        if (pName == null || pName.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        this.aName = pName;
    }

    /**
     * Returns the age of the animal.
     *
     * @return the age of the animal in years
     */
    public double getAge() {
        return aAge;
    }

    /**
     * Sets the age of the animal.
     * <p>
     * The age must be non-negative. If a negative value is provided,
     * an {@code IllegalArgumentException} is thrown.
     * </p>
     *
     * @param pAge the new age of the animal in years
     * @throws IllegalArgumentException if {@code pAge} is negative
     */
    public void setAge(double pAge) {
        if (pAge < 0)
            throw new IllegalArgumentException("Age cannot be smaller than 0!");
        this.aAge = pAge;
    }

    /**
     * Returns a string representation of the animal.
     * <p>
     * This implementation returns the name of the animal.
     * </p>
     *
     * @return the name of the animal
     */
    @Override
    public String toString() {
        return this.getName();
    }
}