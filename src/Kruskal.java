
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

import static guru.nidi.graphviz.model.Factory.mutGraph;
import static guru.nidi.graphviz.model.Factory.mutNode;

import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Label;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Link;
import guru.nidi.graphviz.model.MutableGraph;


 /* Finalidade: Classe que contém o algoritmo Kruskal.
 * Recebe valores de um grafo e gera a arvore geradora minima de acordo com o algoritmo.
*/
public class Kruskal {
    private LinkedList<ArestaKruskal> A;
    private LinkedList<ArestaKruskal> arestas;
    private ArrayList<ArrayList<Integer>> conjunto;


     /*Finalidade: construtor para inicializar o algoritmo.
	 * Pré condição: passar o grafo.
	 * Pós condição: Nenhum. */
    public Kruskal(Grafo grafo) {
        this.conjunto = new ArrayList<ArrayList<Integer>>();
        this.A = new LinkedList<ArestaKruskal>();
        this.arestas = new LinkedList<ArestaKruskal>();
        for (Vertice vertice : grafo.getVertices()) {
            criaConjunto(vertice.getNumero());
            for (Aresta a : vertice.getArestas()) {
                ArestaKruskal ak = new ArestaKruskal(vertice.getNumero(), a.getDestino(), a.getPeso());
                this.arestas.add(ak);
            }
        }
        Collections.sort((LinkedList<ArestaKruskal>) this.arestas);
    }

     /*Finalidade: Cria um conjunto de vertices.
	 * Pré condição: passar um vertice.
	 * Pós condição: Nenhum. */
    public void criaConjunto(Integer vertice) {
        ArrayList<Integer> v = new ArrayList<Integer>();
        v.add(vertice);
        this.conjunto.add(v);
    }

     /*Finalidade: Une os conjuntos.
	 * Pré condição: passar dois vertices e dois conjuntos.
	 * Pós condição: Nenhum. */
    public void uneConjunto(Integer u, Integer v, ArrayList<Integer> conjU, ArrayList<Integer> conjV) {
        for (Integer conjv : conjV) {
            if (!this.conjunto.get(u).contains(conjv)) {
                this.conjunto.get(u).add(conjv);
            }
        }
        this.conjunto.get(v).clear();
    }

     /*Finalidade: Verifica se o conjunto possui alguma vertice repetido.
	 * Pré condição: passar dois vertices.
	 * Pós condição: Retorna false se existir. */
    public Boolean verificaConjunto(Integer u, Integer v) {
        for (Integer conjuntoU : this.conjunto.get(u)) {
            for (Integer conjuntoV : this.conjunto.get(v)) {
                if (conjuntoU == conjuntoV) {
                    return false;
                }
            }
        }
        return true;
    }

     /*Finalidade: Verifica se existe a aresta.
	 * Pré condição: passar duas arestas.
	 * Pós condição: Retorna false se existir. */
    public Boolean existeAresta(Integer v, Integer u) {
        for (ArestaKruskal ak : this.A) {
            if ((ak.getVerticeU() == v) && (ak.getVerticeV() == u)) {
                return false;
            }
        }
        return true;
    }

     /*Finalidade: Encontra a posicao do vertice no conjunto.
	 * Pré condição: passar um vertice.
	 * Pós condição: Retorna a posicao ou -1 caso nao encontre. */
    public Integer achaPosConjunto(Integer vertice) {
        Integer index = 0;
        for (ArrayList<Integer> conj : this.conjunto) {
            for (Integer c : conj) {
                if (c == vertice) {
                    return index;
                }
            }
            index = index + 1;
        }
        return -1;
    }

     /*Finalidade: Algoritmo da arvore Geradora minima.
	 * Pré condição: Nenhum.
	 * Pós condição: Nenhum. */
    public void arvoreGeradoMinima() {
        for (ArestaKruskal a : this.arestas) {
            if (existeAresta(a.getVerticeV(), a.getVerticeU())) {
                Integer indexU = achaPosConjunto(a.getVerticeU());
                Integer indexV = achaPosConjunto(a.getVerticeV());
                if (verificaConjunto(indexU, indexV)) {
                    this.A.add(a);
                    uneConjunto(indexU, indexV, this.conjunto.get(indexU), this.conjunto.get(indexV));
                }
            }
        }
    }

     /*Finalidade: Imprime a Arvore geradora minima de acordo com o Algoritmo Kruskal.
	 * Pré condição: Nenhum.
	 * Pós condição: Nenhum. */
    public void imprimeArvoreGeradoraMinima() {
        System.out.println("Algoritmo Kruskal: ");
        int pesoTotal = 0;
        for (ArestaKruskal a : this.A) {
            System.out.print("(" + a.getVerticeU() + "," + a.getVerticeV() + ") ");
            pesoTotal = pesoTotal + a.getPeso();
        }
        System.out.println();
        System.out.println("Peso total: " + pesoTotal);
        System.out.println();
    }

      /*Finalidade: Gera a imagem do grafo mostrando o caminho feito pelo algoritmo Kruskal.
	 * Pré condição: passar o grafo.
	 * Pós condição: Nenhum. */
    public void desenharKruskal(Grafo grafo) {
        ArrayList<ArestaKruskal> conjuntoAresta = new ArrayList<ArestaKruskal>();
        MutableGraph g = mutGraph("Grafo").setDirected(false).use((gr, ctx) -> {
            for (Vertice v : grafo.getVertices()) {
                mutNode(v.getNumero().toString());
                for (Aresta aresta : v.getArestas()) {
                    ArestaKruskal ak = new ArestaKruskal(v.getNumero(), aresta.getDestino(), aresta.getPeso());
                    if (existeAresta(ak, conjuntoAresta)) {
                        mutNode(aresta.getDestino().toString());
                        Link a = mutNode(aresta.getDestino().toString()).linkTo();
                        if (participaConjunto(ak)) {
                            a = a.with(Color.RED, Label.of(aresta.getPeso().toString()));
                        } else {
                            a = a.with(Label.of(aresta.getPeso().toString()));
                        }
                        mutNode(v.getNumero().toString()).addLink(a);
                    }
                    conjuntoAresta.add(ak);
                }
            }
        });
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
            Graphviz.fromGraph(g).width(500).render(Format.PNG)
                    .toFile(new File("src/images/kruskal_" + dtf.format(LocalDateTime.now()) + ".png"));
            System.out.println("Imagem gerada com sucesso!");
            System.out.println();
            Scanner in = new Scanner(System.in);
            System.out.println("Pressione enter para continuar...");
            in.nextLine();
            Menu menu = new Menu();
            menu.ClearConsole();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

     /*Finalidade: Verifica se a aresta esta no conjunto.
	 * Pré condição: passar aresta.
	 * Pós condição: Retorna true se existir. */
    public Boolean participaConjunto(ArestaKruskal ak) {
        for (ArestaKruskal a : this.A) {
            if (ak.getVerticeU() == a.getVerticeU() && ak.getVerticeV() == a.getVerticeV()) {
                return true;
            }
        }
        return false;
    }

     /*Finalidade: Verifica se existe a aresta no conjunto.
	 * Pré condição: passar aresta e conjunto.
	 * Pós condição: Retorna false se existir. */
    public Boolean existeAresta(ArestaKruskal ak, ArrayList<ArestaKruskal> conjunto) {
        for (ArestaKruskal aresta : conjunto) {
            if ((aresta.getVerticeU() == ak.getVerticeV()) && (aresta.getVerticeV() == ak.getVerticeU())) {
                return false;
            }
        }
        return true;
    }
}
