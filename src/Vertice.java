
import java.util.LinkedList;

/* Finalidade: Classe que contém a estrutura do vertice.
*/

public class Vertice {
    private Integer numero;
    private LinkedList<Aresta> arestas;

    /*Finalidade: construtor para criar o vertice.
	 * Pré condição: passar o valor do vertice.
	 * Pós condição: Nenhum. */
    public Vertice(int numero){
        this.numero = numero;
        this.arestas = new LinkedList<Aresta>();
    }

    // getters e setter dos atributos da classe
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
    // ---------------------------------------
}
