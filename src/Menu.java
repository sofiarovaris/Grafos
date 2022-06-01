
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Menu {

    public static String opcoesMenu() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("1 - Busca em profundidade.");
        System.out.println("2 - Busca em largura.");
        System.out.println("3 - Bellman-Ford.");
        System.out.println("4 - Kruskal.");
        System.out.println("5 - Prim.");
        System.out.println("6 - Desenhar Grafo.");
        System.out.println("0 - Sair.");
        System.out.println("O que deseja fazer?");
        String resposta = br.readLine();
        return resposta;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Carregar grafo: ");
        String arquivo = br.readLine();
        Grafo grafo  = new Grafo(arquivo);

        String resposta = opcoesMenu();
        
        while (Integer.parseInt(resposta) != 0) {
            switch (Integer.parseInt(resposta)) {
                case 1:
                    DFS dfs = new DFS(grafo.getQtdVertices());
                    dfs.buscaProfundidade(grafo);
                    dfs.imprimeOrdemVisitacao();
                    break;
                case 2:
                    BFS bfs = new BFS(grafo.getQtdVertices());
                    System.out.println("Vértice de origem:");
                    resposta = br.readLine();
                    bfs.buscaLarguraVisit(grafo, Integer.parseInt(resposta));
                    bfs.imprimeOrdemVisitacao();
                    break;
                case 3:
                    if(grafo.orientado.equals("sim")){
                        System.out.println("Vértice de origem:");
                        resposta = br.readLine();
                        BellmanFord bf = new BellmanFord(grafo, Integer.parseInt(resposta));
                        if(bf.menorCaminho(grafo)){
                            bf.imprimeMenorCaminho(grafo.getVertices(), Integer.parseInt(resposta));
                        }else{
                            System.out.println("O grafo possui ciclo de peso negativo");
                        }
                    }else{
                        System.out.println("O grafo não é orientado!");
                    }
                    break;
                case 4:
                    Kruskal kruskal = new Kruskal(grafo);
                    kruskal.arvoreGeradoMinima();
                    kruskal.imprimeArvoreGeradoraMinima();
                    kruskal.desenharKruskal(grafo);
                    break;
                case 5:
                    System.out.println("Vértice de origem:");
                    resposta = br.readLine();
                    Prim prim = new Prim(grafo, Integer.parseInt(resposta));
                    prim.arvoreGeradoMinima(grafo);
                    prim.imprimeArvoreGeradoraMinima(grafo, Integer.parseInt(resposta));
                    prim.desenharPrim(grafo);
                    break;
                case 6:
                    DesenharGrafo dg = new DesenharGrafo(grafo);
                    dg.gerarDesenho(grafo);
                    break;
                default:
                    break;
            }
            resposta = opcoesMenu();
        }
    }
}
