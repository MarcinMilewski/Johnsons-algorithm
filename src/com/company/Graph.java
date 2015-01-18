package com.company;

import com.sun.corba.se.impl.encoding.OSFCodeSetRegistry;
import com.sun.java.util.jar.pack.ConstantPool;
import sun.reflect.generics.tree.*;

import java.util.*;


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

    private static final int INF = Integer.MAX_VALUE; // reprezentacja nieskonczonosci

    // wyniki algorytmow
    private TreeMap<Integer,Integer> distanceFordBellman;
    private TreeMap<Integer, Integer> predecessorFordBellman;
    private TreeMap<Integer, Integer> distanceDijkstra;
    private TreeMap<Integer, Integer> predecessorDijkstra;

    public Graph() {
        this.adjacencyList = new TreeMap<Integer, TreeMap<Integer, Integer>>();
    }

    // dodaje krawedz do grafu
    public void addEdge(Integer from, Integer to, Integer weight) {
        if (!(adjacencyList.containsKey(from) && adjacencyList.containsKey(to))) throw new IllegalArgumentException(); // jesli brak wierzcholka from i to w grafie - wyjatek
        TreeMap<Integer, Integer> oldRegistry = adjacencyList.get(from);
        oldRegistry.put(to,weight); // dodanie polaczenia - nowy wpis
        adjacencyList.replace(from, adjacencyList.get(from), oldRegistry);
        edgeCount++;
    }

    public void removeEdge(Integer from, Integer to) {
        if (!(adjacencyList.containsKey(from) && adjacencyList.containsKey(to))) throw new IllegalArgumentException(); // jesli brak wierzcholkow - wyrzuc wyjatek
        TreeMap<Integer, Integer> oldRegistry = adjacencyList.get(from);
        oldRegistry.remove(to);
        adjacencyList.replace(from, adjacencyList.get(from), oldRegistry);
        edgeCount--;
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

    public String toString() {
        String string = new String();

        for (Map.Entry<Integer, TreeMap<Integer, Integer>> node : adjacencyList.entrySet()) { // dla kazdego wierzcholka w grafie
            for (Map.Entry<Integer, Integer> connectionList : node.getValue().entrySet()) {
                string += node.getKey() + " --> " + connectionList.getKey() + " waga: " + connectionList.getValue();
            }
        }
        return string;
    }

    public void fordBellmanAlgorithm(Integer start) {
        if (!adjacencyList.containsKey(start)) throw new IllegalArgumentException(); // jesli brak wierzcholka start

        distanceFordBellman = new TreeMap<Integer, Integer>();
        predecessorFordBellman = new TreeMap<Integer, Integer>();

        //ALGORYTM
        // 1. Inicjalizacja grafu
        for (Map.Entry<Integer, TreeMap<Integer,Integer>> node : adjacencyList.entrySet()) { // dla kazdego wierzcholka w grafie
            if (node.getKey() == start) distanceFordBellman.put(start, 0); // zmiana wartosci - reput
            else distanceFordBellman.put(node.getKey(), INF); // inicjalizuj mape dystansu
            predecessorFordBellman.put(node.getKey(), null);  // inicjalizuj mape poprzednikow
        }

        // 2. Relax edges repeatedly
        for (int i = 1; i <= vertexCount -1; i++) {
            for (Map.Entry<Integer,TreeMap<Integer,Integer>> node : adjacencyList.entrySet()) { // dla kazdego wierzcholka
                for (Map.Entry<Integer, Integer> connectionList : node.getValue().entrySet()) { // dla kazdej krawedzi od niego odchodzacej.
                    if (distanceFordBellman.get(node.getKey()) + connectionList.getValue() < distanceFordBellman.get(connectionList.getKey())) {  // if distance[u] + w < distance[v]:
                        int newDistance = distanceFordBellman.get(node.getKey()) + connectionList.getValue();
                        distanceFordBellman.put(connectionList.getKey(), newDistance); // distance[v] := distance[u] + w
                        predecessorFordBellman.put(connectionList.getKey(),node.getKey()); // predecessor[v] := u
                    }
                }

            }
        }
        // 3. Check for negative-weight cycle
    }
    public int getDistanceTo(int node) {
        if (!adjacencyList.containsKey(node)) throw new IllegalArgumentException(); // jesli brak wierzcholka start
        return distanceFordBellman.get(node);
    }
}

    public String fordBellmanDistanceToString() {
        String toReturn = new String;
        for (Map.Entry<Integer, Integer> distance : distanceFordBellman.entrySet()) {
            toReturn+= "from start to " + distance.getKey() + "distance: " + distance.getValue() + " ,predecessor :" + predecessorFordBellman.get(distance.getKey());
        }
    }

    // znajduje klucz dla ktorego dystnas jest najmniejszy
    private int getMinKeyDijkstraDistanceAndArray() {

        return node;
    }

    public void dijkstraAlgorithm(Integer start) {
        if (!adjacencyList.containsKey(start)) throw new IllegalArgumentException(); // jesli brak wierzcholka start

        distanceDijkstra = new TreeMap<Integer, Integer>();
        predecessorDijkstra = new TreeMap<Integer, Integer>();

        TreeMap<Integer,TreeMap<Integer,Integer>> copyAdjacencyList = adjacencyList;

        TreeSet<Integer> set = new TreeSet<Integer>();
        //ALGORYTM
        // 1. Inicjalizacja grafu
        for (Map.Entry<Integer, TreeMap<Integer,Integer>> node : adjacencyList.entrySet()) { // dla kazdego wierzcholka w grafie
            if (node.getKey() == start) distanceDijkstra.put(start, 0); // zmiana wartosci - reput
            else distanceDijkstra.put(node.getKey(), INF); // inicjalizuj mape dystansu
            predecessorDijkstra.put(node.getKey(), null);  // inicjalizuj mape poprzednikow

            // dodawaj do tablicy
            array.add(node.getKey());

            // while array is not empty:
            while(set.size() > 0) {
                int min = Integer.MAX_VALUE;
                int vertex = 0;
                for (Map.Entry<Integer,Integer> distance :distanceDijkstra.entrySet()) {
                    if (distance.getValue() < min && set.contains(distance.getKey())) {
                        min = distance.getValue();
                        vertex = distance.getKey();
                    }
                }
                // remove vertex from array
                set.remove(vertex);

                TreeMap<Integer, Integer> list = adjacencyList.get(vertex);

                //for each neighbor v of vertex:
                for (Map.Entry<Integer, Integer> entry : list.entrySet()) {
                int alt = distanceDijkstra.get(entry.getKey()) + entry.getValue();
                    if (alt < )
                }
            }
        }



    }


