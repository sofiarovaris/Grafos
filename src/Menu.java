
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Menu {

    public void ClearConsole(){
        try{
            String operatingSystem = System.getProperty("os.name");
              
            if(operatingSystem.contains("Windows")){        
                ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
                Process startProcess = pb.inheritIO().start();
                startProcess.waitFor();
            } else {
                ProcessBuilder pb = new ProcessBuilder("clear");
                Process startProcess = pb.inheritIO().start();

                startProcess.waitFor();
            } 
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public static String opcoesMenu() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("O que gostaria de fazer com o grafo inserido?");
        System.out.println("-----------------");
        System.out.println("1 - Busca em profundidade.");
        System.out.println("2 - Busca em largura.");
        System.out.println("3 - Bellman-Ford.");
        System.out.println("4 - Kruskal.");
        System.out.println("5 - Prim.");
        System.out.println("6 - Gerar uma imagem do grafo.");
        System.out.println("0 - Sair.");
        String resposta = br.readLine();
        Menu menu = new Menu();
        menu.ClearConsole();
        return resposta;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Carregar grafo: ");
        String arquivo = br.readLine();
        Grafo grafo  = new Grafo(arquivo);

        Menu menu = new Menu();
        menu.ClearConsole();
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
                    if(grafo.orientado.equals("nao")){
                        Kruskal kruskal = new Kruskal(grafo);
                        kruskal.arvoreGeradoMinima();
                        kruskal.imprimeArvoreGeradoraMinima();
                        kruskal.desenharKruskal(grafo);
                    }else{
                        System.out.println("O grafo é orientado!");
                    }
                    break;
                case 5:
                    if(grafo.orientado.equals("nao")){
                        System.out.println("Vértice de origem:");
                        resposta = br.readLine();
                        Prim prim = new Prim(grafo, Integer.parseInt(resposta));
                        prim.arvoreGeradoMinima(grafo);
                        prim.imprimeArvoreGeradoraMinima(grafo, Integer.parseInt(resposta));
                        prim.desenharPrim(grafo);
                    }else{
                        System.out.println("O grafo é orientado!");
                    }
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
