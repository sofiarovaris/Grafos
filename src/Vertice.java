
import java.util.LinkedList;

public class Vertice {
    private Integer numero;
    private LinkedList<Aresta> arestas;

    public Vertice(int numero){
        this.numero = numero;
        this.arestas = new LinkedList<Aresta>();
    }

    public Vertice(LinkedList<Aresta> arestas){
        this.arestas = arestas;
    }

    public void setAresta(Aresta a){
        this.arestas.add(a);
    }

    public LinkedList<Aresta> getArestas(){
        return this.arestas;
    }

    public Integer getNumero(){
        return this.numero;
    }
}
