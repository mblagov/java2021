package com.mblagov.collections;

import java.util.*;

public class CollectionsExample {
    public static void main(String[] args) {
        // Describe collection main methods
        // Class hierarchy
        Collection<String> collection = new ArrayList<String>();

        List<String> arrayList = new ArrayList<String>();
        arrayList.add("aba");
        arrayList.add("aba");
        arrayList.add(1,  "a");
        arrayList.add(1,  "qwe");
        arrayList.add(3,  "qwerty");

        System.out.println(Arrays.toString(arrayList.toArray()));

        System.out.println(arrayList.contains("a"));

        // Linked list - queue
        LinkedList<String> linkedList = new LinkedList<>();

        Set<String> mySet = new HashSet<>();
        mySet.add("aba");
        mySet.add("aba");
        System.out.println(Arrays.toString(mySet.toArray()));
    }
}
