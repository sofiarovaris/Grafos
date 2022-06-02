import java.io.Serializable;

/* Finalidade: Classe que contém a estrutura da aresta, utilizada pra armezenar as arestas do grafo.
 * Com suas ligações e seu determinado peso.
*/

public class Aresta implements Comparable<Aresta>, Serializable{
    private static final long serialVersionUID = 1L;
    private Integer peso;
    private Integer destino;


    /*Finalidade: construtor para criar a aresta.
	 * Pré condição: passar o peso e seu destino.
	 * Pós condição: Aresta com seu destino e peso. */
    public Aresta(Integer peso, Integer destino){
        this.peso = peso;
        this.destino = destino;
    }

    /*Finalidade: Ordenar aresta.
	 * Pré condição: passar a aresta.
	 * Pós condição: Ordena a lista de aresta. */
    public int compareTo(Aresta o){
        if(this.destino == o.destino){
            return 0;
        }else if(this.destino < o.destino){
            return -1;
        }
        return 1;
    }

    // getters e setter dos atributos da classe
    public Integer getPeso(){
        return this.peso;
    }

    public void setPeso(Integer peso){
        this.peso = peso;
    }

    public Integer getDestino(){
        return this.destino;
    }

    public void setDestino(Integer destino){
        this.destino = destino;
    }
    // ---------------------------------------
}
