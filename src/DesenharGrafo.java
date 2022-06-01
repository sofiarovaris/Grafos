import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static guru.nidi.graphviz.model.Factory.mutGraph;
import static guru.nidi.graphviz.model.Factory.mutNode;
import guru.nidi.graphviz.attribute.Label;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Link;
import guru.nidi.graphviz.model.MutableGraph;

public class DesenharGrafo {
    private ArrayList<ArestaKruskal> conjuntoAresta;
    private Boolean direcao;

    public DesenharGrafo(Grafo grafo) {
        this.conjuntoAresta = new ArrayList<ArestaKruskal>();
        this.direcao = false;
        if (grafo.orientado.equals("sim")) {
            this.direcao = true;
        }
    }

    public Boolean existeAresta(ArestaKruskal ak, ArrayList<ArestaKruskal> conjunto) {
        for (ArestaKruskal aresta : conjunto) {
            if ((aresta.getVerticeU() == ak.getVerticeV()) && (aresta.getVerticeV() == ak.getVerticeU())) {
                return false;
            }
        }
        return true;
    }

    public void gerarDesenho(Grafo grafo) {
        MutableGraph g = mutGraph("Grafo").setDirected(direcao).use((gr, ctx) -> {
            for (Vertice v : grafo.getVertices()) {
                mutNode(v.getNumero().toString());
                for (Aresta aresta : v.getArestas()) {
                    ArestaKruskal ak = new ArestaKruskal(v.getNumero(), aresta.getDestino(), aresta.getPeso());
                    if (grafo.orientado.equals("nao")) {
                        if (existeAresta(ak, conjuntoAresta)) {
                            mutNode(aresta.getDestino().toString());
                            Link a = mutNode(aresta.getDestino().toString()).linkTo();
                            a = a.with(Label.of(aresta.getPeso().toString()));
                            mutNode(v.getNumero().toString()).addLink(a);
                        }
                        conjuntoAresta.add(ak);
                    } else {
                        Link a = mutNode(aresta.getDestino().toString()).linkTo();
                        a = a.with(Label.of(aresta.getPeso().toString()));
                        mutNode(v.getNumero().toString()).addLink(a);
                    }

                }
            }
        });
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
            Graphviz.fromGraph(g).width(200).render(Format.PNG)
                    .toFile(new File("src/images/grafo_" + dtf.format(LocalDateTime.now()) + ".png"));
            System.out.println("Imagem gerada com sucesso!");
            System.out.println();
            Scanner in = new Scanner(System.in);
            System.out.println("Precione enter para continuar...");
            in.nextLine();
            Menu menu = new Menu();
            menu.ClearConsole();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
