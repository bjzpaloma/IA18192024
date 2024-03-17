package IA;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


public class ArbolBusqueda {

    Nodo raiz;
    String objetivo;
    int contador = 0;
    int tiempoTotal = 0;
    

    public ArbolBusqueda(Nodo raiz, String objetivo)
    {
        this.raiz = raiz;
        this.objetivo = objetivo;
    }

    //Busqueda por Anchura
    public String busquedaPorAnchura() {
        Nodo nodoActual = raiz;
        Collection<String> estadosVisitados = new ArrayList<String>();
        LocalDateTime inicio = LocalDateTime.now();
        contador = 0;
        Queue<Nodo> estadosPorVisitar = new LinkedList();
        while (!nodoActual.getEstado().equals(objetivo)) {
            contador++;
            estadosVisitados.add(nodoActual.getEstado());
            // Generar a los Nodos Hijos
            Collection<String> hijos = nodoActual.generaHijos(); // <-- Cada Equipo tiene que ingeniarselas para crear este metodo!
            for (String hijo : hijos) {
                if (!estadosVisitados.contains(hijo)) {
                    // System.out.println("---Metiendo nuevo Nodo");
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
        System.out.println(nodoActual.imprimeSolucion(nodoActual,raiz));
        System.out.println("Cantidad de veces que se entro al ciclo de busqueda: " + contador);
        System.out.println("Tiempo total de busqueda: " + duracion);
		return objetivo;

    }
   
    //Busqueda por Profundidad
    public void busquedaPorProfundidad()
    {
        int contador = 0;
        LocalDateTime inicio = LocalDateTime.now();
        Nodo nodoActual = raiz;
        Collection<String> estadosVisitados = new ArrayList<String>();
        Stack<Nodo> estadosPorVisitar = new Stack<Nodo>();
        while(!nodoActual.getEstado().equals(objetivo))
        {
            contador++;
            estadosVisitados.add(nodoActual.getEstado());
            //Generar a los Nodos Hijos
            Collection<String> hijos = nodoActual.generaHijos();
            //hijos = heuristica1(hijos);

            for (String hijo : hijos) {
                if(!estadosVisitados.contains(hijo))
                {
                    //System.out.println("---Metiendo nuevo Nodo");
                    //Crear nuevo Nodo.
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
        System.out.println(nodoActual.imprimeSolucion(nodoActual,raiz));
        System.out.println("Cantidad de veces que se entro al ciclo de busqueda "+contador);
        System.out.println("Tiempo total de busqueda: " + duracion);

    }
    
    
  
}

