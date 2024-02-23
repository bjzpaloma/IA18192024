package ia;

class Arbol 
{

    public static void main(String[] args) 
    {
    	//inserciones
        Arbol arbol = new Arbol();
        arbol.add("Luis");
        arbol.add("Sara");
        arbol.add("Jesus");
        arbol.add("Maria");
        arbol.add("Elena");

        if(arbol.empty())
        	System.out.println("Árbol vacío");
        else
        {
        Nodo nodoEncontrado = arbol.buscarNodo("Juan"); //Nodo a buscar
        if (nodoEncontrado != null) 
            System.out.println("Encontrado: " + nodoEncontrado.nombre);
        else 
        	System.out.println("No se encuentra");
        
        arbol.imprimir();
        }
    }

    Nodo raiz;

    public Arbol() 
    {
        this.raiz = null;
    }

    public boolean empty() 
    {
        return raiz == null;
    }

    public Nodo buscarNodo(String nombre) 
    {
        return buscarNodoAux(raiz, nombre);
    }

    private Nodo buscarNodoAux(Nodo nodo, String nombre) 
    {
        if (nodo == null || nodo.nombre.equals(nombre)) 
            return nodo;
        if (nombre.compareTo(nodo.nombre) < 0) 
            return buscarNodoAux(nodo.izq, nombre);
        else 
            return buscarNodoAux(nodo.der, nombre);
    }

    public void add(String nombre) 
    {
        raiz = insert(raiz, nombre);
    }

    private Nodo insert(Nodo nodo, String nombre) 
    {
        if (nodo == null)
            return new Nodo(nombre);

        if (nombre.compareTo(nodo.nombre) < 0) 
            nodo.izq = insert(nodo.izq, nombre);
        else
            nodo.der = insert(nodo.der, nombre);


        return nodo;
    }

    public void imprimir() 
    {
    	System.out.println("Árbol: ");
    	imprimirArbol(raiz);
    }

    private void imprimirArbol(Nodo nodo) 
    {
        if (nodo != null) 
        {
        	imprimirArbol(nodo.izq);
            System.out.println(nodo.nombre);
            imprimirArbol(nodo.der);
        }
    }
}