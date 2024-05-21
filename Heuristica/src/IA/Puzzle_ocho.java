package IA;
import java.util.Scanner;



public class Puzzle_ocho {

    public static String estadoInicialEasy = "123456 78";
    public static String estadoInicialHard = "234 56718";
    public static String estadoFinal = "12345678 ";
    private static Scanner Leer;

    public static void main(String[] args) {
        Leer = new Scanner(System.in);

        int opcion = 0;
        do {
            System.out.println("-- Dificultad:");
            System.out.println("-- 1 Fácil");
            System.out.println("-- 2 Dificil");
            System.out.println("-- 3 Salir");
            int raizOp = Leer.nextInt();
            switch (raizOp) {
                case 1: opcion = menu(estadoInicialEasy); break;
                case 2: opcion = menu(estadoInicialHard); break;
                case 3: return;
                default: System.out.println("opcion no valida");
            }
        }while(opcion >= 0);

        Leer.close();
    }

    private static int menu(String estado) 
    {

        ArbolBusqueda a = new ArbolBusqueda(new Nodo(estado), estadoFinal);

        System.out.println("-- Metodo de busqueda");
        System.out.println("-- 1 Anchura");
        System.out.println("-- 2 Profundidad");
        System.out.println("-- 3 Heuristica(Distancia Manhatan)");
        System.out.println("-- 4 Salir");
        int opcion = Leer.nextInt();

        switch (opcion) 
        {
            case 1:
            	a.busquedaPorAnchura();
                break;
            case 2:
                a.busquedaPorProfundidad();
                break;
            case 3:
            	a.busquedaConHeuristica();
            	break;
            case 4: break;
            default: System.out.println("Opcion no valida");
        }
        return opcion;
    }
}