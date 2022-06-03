import java.util.Scanner;


/* Finalidade: Classe que contém o algoritmo BellmanFord.
 * Recebe valores de um grafo e gera o menor Caminho de acordo com o algoritmo.
*/
public class BellmanFord {
    private Integer d[];
    private Integer pi[];


    /*Finalidade: construtor para inicializar o algoritmo.
	 * Pré condição: passar o grafo e o vertice de origem.
	 * Pós condição: Nenhum. */
    public BellmanFord(Grafo g, int origem) {
        this.d = new Integer[g.getQtdVertices()];
        this.pi = new Integer[g.getQtdVertices()];
        for (int i = 0; i < g.getVertices().length; i++) {
            this.d[i] = Integer.MAX_VALUE;
            this.pi[i] = -1;
        }
        this.d[origem] = 0;
    }

    /*Finalidade: Gera o menor caminho.
	 * Pré condição: passar o grafo.
	 * Pós condição: Retorna false se o grafo possui ciclo com aresta com peso negativo. */
    public Boolean menorCaminho(Grafo g) {
        for (int i = 0; i < g.getQtdVertices() - 2; i++) {
            for (Vertice vertice : g.getVertices()) {
                for (Aresta a : vertice.getArestas()) {
                    relaxacao(vertice.getNumero(), a.getDestino(), a.getPeso());
                }
            }
        }
        for (Vertice vertice : g.getVertices()) {
            for (Aresta a : vertice.getArestas()) {
                if (this.d[a.getDestino()] > (this.d[vertice.getNumero()] + a.getPeso())) {
                    return false;
                }
            }
        }
        return true;
    }

    /*Finalidade: Método que diminui o limite superior do peso do menor caminho.
	 * Pré condição: passar dois vertices e um peso da aresta entre eles.
	 * Pós condição: Nenhum. */
    public void relaxacao(int u, int v, int w) {
        if ((this.d[v] > (this.d[u] + w) && (this.d[u] != Integer.MAX_VALUE))) {
            this.d[v] = this.d[u] + w;
            this.pi[v] = u;
        }
    }

    /*Finalidade: Imprime o menor caminho.
	 * Pré condição: passar o vetor de vertices e a origem.
	 * Pós condição: Nenhum. */
    public void imprimeMenorCaminho(Vertice vertices[], int origem) {
        System.out.println("Algoritmo Bellman Ford: ");
        System.out.println("Origem: " + origem);
        System.out.println();
        for (Vertice v : vertices) {
            System.out.print("Destino: " + v.getNumero());
            System.out.print(" Dist.: " + this.d[v.getNumero()]);
            System.out.print(" Caminho: ");
            System.out.print(origem);
            imprimeCaminho(v.getNumero(), origem);
            System.out.println();
        }
        System.out.println();
        Scanner in = new Scanner(System.in);
        System.out.println("Pressione enter para continuar...");
        in.nextLine();
        Menu menu = new Menu();
        menu.ClearConsole();

    }
    
    /*Finalidade: Auxilia a impressao do menor caminho.
	 * Pré condição: passar o vertice de origem e destino.
	 * Pós condição: Nenhum. */
    public void imprimeCaminho(int destino, int origem) {
        if (origem != destino) {
            imprimeCaminho(this.pi[destino], origem);
            System.out.print(" - " + destino);
        }
    }
}
