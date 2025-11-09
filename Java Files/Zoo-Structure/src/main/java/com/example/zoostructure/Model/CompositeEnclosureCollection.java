package com.example.zoostructure.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * CompositeEnclosureCollection represents a group of enclosures, which
 * can be other composite enclosures or individual enclosures.
 * Implements the EnclosureCollection interface.
 * @author Serena
 */
public class CompositeEnclosureCollection implements EnclosureCollection {

    private final String aName;
    private final List<EnclosureCollection> aEnclosures;

    public CompositeEnclosureCollection(String pName) {
        this.aName = pName;
        this.aEnclosures = new ArrayList<>();
    }

    public String getName() {
        return aName;
    }

    @Override
    public void addCollection(EnclosureCollection pEnclosure) {
        aEnclosures.add(pEnclosure);
    }

    @Override
    public void removeCollection(EnclosureCollection pEnclosure) {
        aEnclosures.remove(pEnclosure);
    }

    @Override
    public void display() {
        display("");
    }

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