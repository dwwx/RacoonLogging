package com.racoon.interfaces;

import java.io.Serializable;
import java.util.Iterator;

public interface RMarker extends Serializable {

    public final String ANY_MARKER = "*";


    public final String ANY_NON_NULL_MARKER = "+";


    public String getName();


    public void add(RMarker reference);


    public boolean remove(RMarker reference);


    public boolean hasChildren();


    public boolean hasReferences();


    public Iterator<RMarker> iterator();


    public boolean contains(RMarker other);


    public boolean contains(String name);


    public boolean equals(Object o);


    public int hashCode();
}
