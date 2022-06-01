public class BellmanFord {
    private Integer d[];
    private Integer pi[];
    
    public BellmanFord(Grafo g, int origem){
        this.d = new Integer[g.getQtdVertices()];
        this.pi = new Integer[g.getQtdVertices()];
        for(int i=0; i<g.getVertices().length; i++){
            this.d[i] = Integer.MAX_VALUE;
            this.pi[i] = -1;
        }
        this.d[origem] = 0;
    }

    public Boolean menorCaminho(Grafo g){
        for(int i=0; i<g.getQtdVertices()-2; i++){
            for (Vertice vertice : g.getVertices()) {
                for (Aresta a : vertice.getArestas()) {
                    relaxacao(vertice.getNumero(), a.getDestino(), a.getPeso());
                }
            }
        }
        for (Vertice vertice : g.getVertices()) {
            for (Aresta a : vertice.getArestas()) {
                if(this.d[a.getDestino()] > (this.d[vertice.getNumero()] + a.getPeso())){
                    return false;
                }
            }
        }
        return true;
    }

    public void relaxacao(int u, int v, int w){
        if((this.d[v] > (this.d[u] + w) && (this.d[u] != Integer.MAX_VALUE))){
            this.d[v] = this.d[u] + w;
            this.pi[v] = u;
        }
    }

    public void imprimeMenorCaminho(Vertice vertices[], int origem){
        System.out.println("origem: "+origem);
        for (Vertice v : vertices) {
            System.out.print(" destino: "+v.getNumero());
            System.out.print(" dist.: "+this.d[v.getNumero()]);
            System.out.print(" caminho: ");
            System.out.print(origem);
            imprimeCaminho(v.getNumero(), origem);
            System.out.println();
        }
        
    }

    public void imprimeCaminho(int destino, int origem){
        if(origem != destino){
            imprimeCaminho(this.pi[destino], origem);
            System.out.print(" - "+destino);
        }
    }
}
