
import java.util.ArrayList;
import java.util.Scanner;

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

    public void buscaProfundidade(Grafo g) {
        this.timestamp = 0;
        for (int i = 0; i < g.getQtdVertices(); i++) {
            if (this.cor[i] == Cores.BRANCO) {
                buscaProfundidadeVisit(g.getVertices(), i);
            }
        }
    }

    public void buscaProfundidade(Grafo g, Integer inicio) {
        this.timestamp = 0;
        buscaProfundidadeVisit(g.getVertices(), inicio);
    }

    public void imprimeOrdemVisitacao() {
        System.out.println("Busca em profundidade: ");
        int tam = this.ordem.size() - 1;
        for (int i = 0; i < tam; i++) {
            System.out.print(this.ordem.get(i) + " - ");
        }
        System.out.println(this.ordem.get(tam));
        System.out.println();
        System.out.println("Precione enter para continuar...");
        Scanner in = new Scanner(System.in);
        in.nextLine();
        Menu menu = new Menu();
        menu.ClearConsole();

    }

}
