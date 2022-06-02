
import java.util.ArrayList;
import java.util.Scanner;

/* Finalidade: Classe que contém o algoritmo de busca em profundidade.
 * Recebe valores de um grafo e faz a busca em profundidade do menor caminho.
*/
public class DFS {

    public enum Cores {
        BRANCO, CINZA, PRETO;
    }

    private Cores cor[];
    private Integer pi[];
    private Integer d[];
    private Integer f[];
    private Integer timestamp;
    private ArrayList<Integer> ordem;

    /*Finalidade: construtor para inicializar o algoritmo.
	 * Pré condição: passar o numero de vertices.
	 * Pós condição: Nenhum. */
    public DFS(Integer qtdVertices) {
        this.cor = new Cores[qtdVertices];
        this.pi = new Integer[qtdVertices];
        this.d = new Integer[qtdVertices];
        this.f = new Integer[qtdVertices];
        this.ordem = new ArrayList<Integer>();
        for (int i = 0; i < qtdVertices; i++) {
            this.cor[i] = Cores.BRANCO;
            this.pi[i] = -1;
        }
    }

    /*Finalidade: Algoritmo da busca em profundidade. 
	 * Pré condição: passar grafo.
	 * Pós condição: Nenhum. */
    public void buscaProfundidade(Grafo g) {
        this.timestamp = 0;
        for (int i = 0; i < g.getQtdVertices(); i++) {
            if (this.cor[i] == Cores.BRANCO) {
                buscaProfundidadeVisit(g.getVertices(), i);
            }
        }
    }

      /*Finalidade: Algoritmo que auxilia a busca em profundidade.
	 * Pré condição: passar um vetor de vertice e o vertice para explorar.
	 * Pós condição: Nenhum. */
    public void buscaProfundidadeVisit(Vertice v[], Integer index) {
        this.cor[index] = Cores.CINZA;
        this.timestamp = this.timestamp + 1;
        this.d[index] = this.timestamp;
        this.ordem.add(index);
        for (Aresta a : v[index].getArestas()) {
            if (this.cor[a.getDestino()] == Cores.BRANCO) {
                pi[a.getDestino()] = v[index].getNumero();
                buscaProfundidadeVisit(v, a.getDestino());
            }
        }
        this.cor[index] = Cores.PRETO;
        this.timestamp = this.timestamp + 1;
        this.f[index] = this.timestamp;
    }

    
    /*Finalidade: Imprime a ordem de visitacao do algoritmo.
	 * Pré condição: Nenhum.
	 * Pós condição: Nenhum. */
    public void imprimeOrdemVisitacao() {
        System.out.println("Busca em profundidade: ");
        int tam = this.ordem.size() - 1;
        for (int i = 0; i < tam; i++) {
            System.out.print(this.ordem.get(i) + " - ");
        }
        System.out.println(this.ordem.get(tam));
        System.out.println();
        System.out.println("Pressione enter para continuar...");
        Scanner in = new Scanner(System.in);
        in.nextLine();
        Menu menu = new Menu();
        menu.ClearConsole();

    }

}
