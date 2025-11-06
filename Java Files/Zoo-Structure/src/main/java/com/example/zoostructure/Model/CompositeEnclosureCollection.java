package com.example.zoostructure.Model;

import java.util.List;

/**
 * CompositeEnclosureCollection represents a group of enclosures, which
 * can be other composite enclosures or individual enclosures.\
 * @author Serena
 */
public class CompositeEnclosureCollection implements EnclosureCollection {

    /**
     * The name of the enclosure.
     */
    private String aName;

    /**
     * The list of enclosures.
     */
    private List<EnclosureCollection> aEnclosures;

    /**
     * Returns the name of the enclosure.
     * @return the name of this enclosure
     */
    @Override
    public String getName() {
        return aName;
    }

    /**
     * Adds an enclosure to this collection.
     * @param pEnclosure the enclosure to add
     */
    @Override
    public void add(EnclosureCollection pEnclosure) {

    }

    /**
     * Removes an enclosure from this collection.
     * @param pEnclosure the enclosure to remove
     */
    @Override
    public void remove(EnclosureCollection pEnclosure) {

    }

    /**
     * Displays the enclosure.
     */
    @Override
    public void display() {

    }

    /**
     * Returns the list of enclosures.
     * @return the list of enclosures
     */
    public List<EnclosureCollection> getEnclosures() {
        return aEnclosures;
    }
}