import java.io.Serializable;

public class Aresta implements Comparable<Aresta>, Serializable{
    private static final long serialVersionUID = 1L;
    private Integer peso;
    private Integer destino;

    public Aresta(Integer peso, Integer destino){
        this.peso = peso;
        this.destino = destino;
    }

    public int compareTo(Aresta o){
        if(this.destino == o.destino){
            return 0;
        }else if(this.destino < o.destino){
            return -1;
        }
        return 1;
    }

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
}
