package IA;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class ArbolBusqueda 
{

    Nodo raiz;
    String objetivo;
    int contador = 0;

    public ArbolBusqueda(Nodo raiz, String objetivo) 
    {
        this.raiz = raiz;
        this.objetivo = objetivo;
    }

    // Busqueda por Anchura
    public String busquedaPorAnchura() 
    {
        Nodo nodoActual = raiz;
        Collection<String> estadosVisitados = new ArrayList<>();
        LocalDateTime inicio = LocalDateTime.now();
        contador = 0;
        Queue<Nodo> estadosPorVisitar = new LinkedList<>();
        while (!nodoActual.getEstado().equals(objetivo)) 
        {
            contador++;
            estadosVisitados.add(nodoActual.getEstado());
            // Generar a los Nodos Hijos
            Collection<String> hijos = nodoActual.generaHijos(); // <-- Cada Equipo tiene que ingeniarselas para crear este metodo!
            for (String hijo : hijos) 
            {
                if (!estadosVisitados.contains(hijo)) 
                {
                    // Crear nuevo Nodo.
                    Nodo nHijo = new Nodo(hijo);
                    nHijo.setPadre(nodoActual);
                    estadosPorVisitar.add(nHijo);
                }
            }
            nodoActual = estadosPorVisitar.poll();
        }
        LocalDateTime fin = LocalDateTime.now();
        Duration duracion = Duration.between(inicio, fin);

        System.out.println("YA SE ENCONTRO EL NODO OBJETIVO");
        System.out.println(nodoActual.imprimeSolucion(nodoActual, raiz));
        System.out.println("Cantidad de veces que se entro al ciclo de busqueda: " + contador);
        System.out.println("Tiempo total de busqueda: " + duracion);
        return objetivo;
    }

    // Busqueda por Profundidad
    public void busquedaPorProfundidad() 
    {
        int contador = 0;
        LocalDateTime inicio = LocalDateTime.now();
        Nodo nodoActual = raiz;
        Collection<String> estadosVisitados = new ArrayList<>();
        Stack<Nodo> estadosPorVisitar = new Stack<>();
        while (!nodoActual.getEstado().equals(objetivo)) 
        {
            contador++;
            estadosVisitados.add(nodoActual.getEstado());
            // Generar a los Nodos Hijos
            Collection<String> hijos = nodoActual.generaHijos();
            for (String hijo : hijos) 
            {
                if (!estadosVisitados.contains(hijo)) 
                {
                    // Crear nuevo Nodo.
                    Nodo nHijo = new Nodo(hijo);
                    nHijo.setPadre(nodoActual);
                    estadosPorVisitar.add(nHijo);
                }
            }
            nodoActual = estadosPorVisitar.pop();
        }
        LocalDateTime fin = LocalDateTime.now();
        Duration duracion = Duration.between(inicio, fin);

        System.out.println("YA SE ENCONTRO EL NODO OBJETIVO");
        System.out.println(nodoActual.imprimeSolucion(nodoActual, raiz));
        System.out.println("Cantidad de veces que se entro al ciclo de busqueda " + contador);
        System.out.println("Tiempo total de busqueda: " + duracion);
    }

    // Busqueda con Heuristica Distancia Manhatan
    public void busquedaConHeuristica() 
    {
        Nodo nodoActual = raiz;
        Collection<String> estadosVisitados = new ArrayList<>();
        PriorityQueue<Nodo> estadosPorVisitar = new PriorityQueue<>(Heuristica());
        LocalDateTime inicio = LocalDateTime.now();
        contador = 0;
        while (!nodoActual.getEstado().equals(objetivo)) 
        {
            contador++;
            estadosVisitados.add(nodoActual.getEstado());
            // Generar a los Nodos Hijos
            Collection<String> hijos = nodoActual.generaHijos();
            for (String hijo : hijos) 
            {
                if (!estadosVisitados.contains(hijo)) 
                {
                    // Crear nuevo Nodo.
                    Nodo nHijo = new Nodo(hijo);
                    nHijo.setPadre(nodoActual);
                    estadosPorVisitar.add(nHijo);
                }
            }
            nodoActual = estadosPorVisitar.poll();
        }
        LocalDateTime fin = LocalDateTime.now();
        Duration duracion = Duration.between(inicio, fin);

        System.out.println("YA SE ENCONTRO EL NODO OBJETIVO");
        System.out.println(nodoActual.imprimeSolucion(nodoActual, raiz));
        System.out.println("Cantidad de veces que se entro al ciclo de busqueda: " + contador);
        System.out.println("Tiempo total de busqueda: " + duracion);
    }

    public int costo(String estado) 
    {
        int espaciosM = 0;
        for (int i = 0; i < objetivo.length(); i++) 
        {
            if (estado.charAt(i) != objetivo.charAt(i) && ((estado.charAt(i) != ' ') && (objetivo.charAt(i) != ' '))) 
            {
                espaciosM++;
            }
        }
        return espaciosM;
    }

    public Comparator<Nodo> Heuristica() 
    {
        return new Comparator<Nodo>() 
        {
            @Override
            public int compare(Nodo o1, Nodo o2) 
            {
                return Integer.compare(costo(o1.getEstado()), costo(o2.getEstado()));
            }
        };
    }
}
