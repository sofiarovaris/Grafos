import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    
    public enum Cores{
        BRANCO, CINZA, PRETO;
    }

    private Cores cor[];
    private Integer pi[];
    private Integer d[];
    private ArrayList<Integer> ordem;
    private Queue<Integer> Q;

    public BFS(Integer qtdVertices){
        this.cor = new Cores[qtdVertices];
        this.pi = new Integer[qtdVertices];
        this.d = new Integer[qtdVertices];
        this.ordem = new ArrayList<Integer>();
        this.Q = new LinkedList<Integer>();
        for(int i=0; i<qtdVertices; i++){
            this.cor[i] = Cores.BRANCO;
            this.pi[i] = -1;
            this.d[i] = -1;
        }
    }

    public void buscaLarguraVisit(Grafo g, int v){
        Vertice vertices[] = g.getVertices();
        this.cor[v] = Cores.CINZA;
        this.d[v] = 0;
        this.Q.add(v);
        while(!this.Q.isEmpty()){
            Integer u = this.Q.element();
            this.ordem.add(u);
            for (Aresta a : vertices[u].getArestas()) {
                if(this.cor[a.getDestino()] == Cores.BRANCO){
                    this.cor[a.getDestino()] = Cores.CINZA;
                    this.d[a.getDestino()] = this.d[u] + 1;
                    this.pi[a.getDestino()] = u;
                    this.Q.add(a.getDestino());
                }
            }
            this.Q.remove();
            this.cor[u] = Cores.PRETO;
        }
    }

    public void imprimeOrdemVisitacao(){
        int tam = this.ordem.size() - 1;
        for(int i=0; i<tam; i++){
            System.out.print(this.ordem.get(i)+" - ");
        }
        System.out.println(this.ordem.get(tam));
    }
    
}
