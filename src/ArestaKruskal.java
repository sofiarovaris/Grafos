import java.io.Serializable;

public class ArestaKruskal implements Comparable<ArestaKruskal>, Serializable{
	private static final long serialVersionUID = 1L;
    private Integer verticeU;
    private Integer verticeV;
    private Integer peso;

    public ArestaKruskal(Integer u, Integer v, Integer peso){
        this.verticeU = u;
        this.verticeV = v;
        this.peso = peso;
    }

    public int compareTo(ArestaKruskal o){
        if(this.peso == o.peso){
            return 0;
        }else if(this.peso < o.peso){
            return -1;
        }
        return 1;
    } 

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
    
}
