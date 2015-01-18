package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public enum Status {
        READ_NODES,
        READ_EDGES,
        RUNNING,
        WRITE,
    }

    public static void main(String[] args) {
        Graph graph = new Graph();
        Status status = Status.READ_NODES;

    // wczytaj wierzcholki i krawedzie
        Scanner scanner = new Scanner(System.in);
        while (status == Status.READ_NODES) {
            int nodeNumber;
            System.out.println("Dodaj wierzcholek (wpisz x aby zakonczyc): ");
            try
            {
                nodeNumber = Integer.parseInt(scanner.nextLine());
                graph.addVertex(nodeNumber);
            }
            catch(NumberFormatException nfe)
            {
                System.out.println("Zakonczyles wprowadzanie wierzcholkow.");
                status = status.READ_EDGES;
                break;
            }
        }

        while (status == Status.READ_EDGES) {
            int from;
            int to;
            int weight;
            System.out.println("Wprowadz krawedzie: (Wpisz x aby zakonczyc)");

            try
            {
                System.out.println("Wprowadz poczatek krawedzi: ");
                from = Integer.parseInt(scanner.nextLine());
                System.out.println("Wprowadz koniec krawedzi: ");
                to = Integer.parseInt(scanner.nextLine());
                System.out.println("Wprowadz wage: ");
                weight = Integer.parseInt(scanner.nextLine());
                graph.addEdge(from,to,weight);
            }
            catch(NumberFormatException nfe)
            {
                System.out.println("Zakonczyles wprowadzanie krawedzi.");
                status = Status.RUNNING;
                break;
            }
        }

        System.out.println("Wprowadziles graf: ");
        System.out.println(graph.toString());

         // algorytm Johnsona
        try {
            if (status == Status.RUNNING) graph.johnsonAlgorithm();
        }
        catch (Exception e) {
            System.out.println("Wystapil blad");
            System.out.println(e.getStackTrace());
        }

        // zapisz do pliku i na ekran
        System.out.println(graph.johnsonDistanceToString());
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("Johnson-output.txt");
            writer.println(graph.johnsonDistanceToString());
            writer.close();
        }
        catch(FileNotFoundException e) {
            System.out.println(e.getStackTrace());
        }
    }

}
