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

    /**
     * Adds an enclosure to the collection.
     * @param pEnclosure the enclosure to add
     */

    void addCollection(EnclosureCollection pEnclosure);

    /**
     * Removes an enclosure from the collection.
     * @param pEnclosure the enclosure to remove
     */

    void removeCollection(EnclosureCollection pEnclosure);

    /**
     * Displays the enclosure.
     */
    void display();
}
