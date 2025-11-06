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
    private final String aName;

    /**
     * The list of enclosures.
     */
    private final List<EnclosureCollection> aEnclosures;

    /**
     * Returns the name of the enclosure.
     *
     * @return the name of this enclosure
     */
    public String getName() {
        return aName;
    }

    public CompositeEnclosureCollection(String pName, List<EnclosureCollection> pEnclosures) {
        this.aName = pName;
        this.aEnclosures = pEnclosures;
    }

    /**
     * Adds an enclosure to this collection.
     *
     * @param pEnclosure the enclosure to add
     */
    @Override
    public void addCollection(EnclosureCollection pEnclosure) {
        aEnclosures.add(pEnclosure);
    }

    /**
     * Removes an enclosure from this collection.
     *
     * @param pEnclosure the enclosure to remove
     */
    @Override
    public void removeCollection(EnclosureCollection pEnclosure) {
        aEnclosures.remove(pEnclosure);
    }

    /**
     * Displays the enclosure.
     */
    @Override
    public void display() {
        display("");
    }

    /**
     * Recursively displays the enclosure hierarchy with indentation.
     *
     * @param pIndent the indentation prefix for nested levels
     */
    public void display(String pIndent) {
        System.out.println(pIndent + aName);
        for (EnclosureCollection enclosure : aEnclosures) {
            if (enclosure instanceof CompositeEnclosureCollection) {
                ((CompositeEnclosureCollection) enclosure).display(pIndent + "  ");
            } else {
                enclosure.display(); // assumes leaf nodes handle their own display
            }
        }
    }

}