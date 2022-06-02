import java.io.Serializable;


/* Finalidade: Classe que contém a estrutura da aresta do Kruskal, utilizada pra armezenar as arestas do grafo.
 * Com suas ligaçõees e seu determinado peso.
*/
public class ArestaKruskal implements Comparable<ArestaKruskal>, Serializable{
	private static final long serialVersionUID = 1L;
    private Integer verticeU;
    private Integer verticeV;
    private Integer peso;


    /*Finalidade: construtor para criar a aresta do Kruskal.
	 * Pré condição: passar o peso e seu destino.
	 * Pós condição: Aresta com seu destino e peso. */
    public ArestaKruskal(Integer u, Integer v, Integer peso){
        this.verticeU = u;
        this.verticeV = v;
        this.peso = peso;
    }

     /*Finalidade: Ordenar aresta do Kruskal.
	 * Pré condição: passar a aresta.
	 * Pós condição: Ordena a lista de aresta do Kruskal. */
    public int compareTo(ArestaKruskal o){
        if(this.peso == o.peso){
            return 0;
        }else if(this.peso < o.peso){
            return -1;
        }
        return 1;
    } 

    // getters e setter dos atributos da classe
    public void setVerticeU(Integer verticeU){
        this.verticeU = verticeU;
    }

    public Integer getVerticeU(){
        return this.verticeU;
    }

    public void setVerticeV(Integer verticeV){
        this.verticeV = verticeV;
    }

    public Integer getVerticeV(){
        return this.verticeV;
    }

    public void setPeso(Integer peso){
        this.peso = peso;
    }

    public Integer getPeso(){
        return this.peso;
    }
    // ---------------------------------------
}
