package com.company;

import sun.reflect.generics.tree.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;


public class Graph {
    /*
  Graf zaimplementowany jako lista sasiedztwa,
  1. Integer - wezel
  2. Integer - wezel
  3. Integer - waga krawedzi wezel - wezel
   */
    private TreeMap<Integer, TreeMap<Integer, Integer>> adjacencyList;
    private int vertexCount;
    private int edgeCount;


    public Graph() {
        this.adjacencyList = new TreeMap<Integer, TreeMap<Integer, Integer>>();
    }

    // dodaje krawedz do grafu
    public void addEdge(Integer from, Integer to, Integer weight) {
        if (!(adjacencyList.containsKey(from) && adjacencyList.containsKey(to))) throw new IllegalArgumentException(); // jesli brak wierzcholka from i to w grafie - wyjatek
        TreeMap<Integer, Integer> oldRegistry = adjacencyList.get(from);
        oldRegistry.put(to,weight); // dodanie polaczenia - nowy wpis
        adjacencyList.replace(from, adjacencyList.get(from), oldRegistry);
    }

    public void removeEdge(Integer from, Integer to) {
        if (!(adjacencyList.containsKey(from) && adjacencyList.containsKey(to))) throw new IllegalArgumentException(); // jesli brak wierzcholkow - wyrzuc wyjatek
        TreeMap<Integer, Integer> oldRegistry = adjacencyList.get(from);
        oldRegistry.remove(to);
        adjacencyList.replace(from, adjacencyList.get(from), oldRegistry);
    }

    // dodaje odizolowany wierzcholek o indeksie v
    public void addVertex(Integer v) {
        TreeMap<Integer, Integer> temp = new TreeMap<Integer, Integer>();
        adjacencyList.put(v, temp);
        vertexCount++;
    }

    // TODO usuwanie wierzcholka

    // sprawdza czy graf zawiera dany wierzcholek
    public boolean containsVertex(Integer v) {
        return adjacencyList.containsKey(v);
    }

    // sprawdza czy graf zawiera dana krawedz
    public boolean containsEdge(Integer from, Integer to) {
        if (!adjacencyList.containsKey(from)) return false; // brak w. from w grafie - brak krawedzi
        TreeMap<Integer, Integer> vertexList = adjacencyList.get(from);

        if(!vertexList.containsKey(to)) return false;
        else return true;
    }


}
