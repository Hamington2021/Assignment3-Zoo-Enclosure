package com.example.zoostructure.Model;

/**
 * This interface defines what both individual enclosures and
 * composite enclosures should have.
 * @author Serena
 */
public interface EnclosureCollection {
    /**
     * Returns the name of the enclosure.
     * @return the name of this enclosure
     */
    String getName();

//    /**
//     * Adds an enclosure to this collection.
//     * @param enclosure the enclosure to add
//     */
//    void addCollection(EnclosureCollection enclosure);
//
//    void removeCollection(EnclosureCollection enclosure);

    /**
     * Displays the enclosure.
     */
    void display();
}
