import static guru.nidi.graphviz.model.Factory.mutGraph;
import static guru.nidi.graphviz.model.Factory.mutNode;

import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Label;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Link;
import guru.nidi.graphviz.model.MutableGraph;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Prim{
    private Integer key[];
    private Integer pi[];
    private Queue<Integer> Q;
    
    public Prim(Grafo grafo, int origem){
        this.key = new Integer[grafo.getQtdVertices()];
        this.pi = new Integer[grafo.getQtdVertices()];
        this.Q = new LinkedList<Integer>();
        for (Vertice vertice : grafo.getVertices()) {
            this.Q.add(vertice.getNumero());
            this.key[vertice.getNumero()] = Integer.MAX_VALUE;
        }
        this.key[origem] = 0;
        this.pi[origem] = -1;
    }

    public void arvoreGeradoMinima(Grafo grafo){
        Vertice vertices[] = grafo.getVertices();
        while(!this.Q.isEmpty()){
            Integer u = minimo();
            this.Q.remove(u);
            for (Aresta a : vertices[u].getArestas()) {
                if(this.Q.contains(a.getDestino()) && (a.getPeso() < this.key[a.getDestino()])){
                    this.pi[a.getDestino()] = u;
                    this.key[a.getDestino()] = a.getPeso();
                }
            }
        }
    }

    public Integer minimo(){
        int min = Integer.MAX_VALUE;
        int index = -1;

        for (Integer q : this.Q) {
            if(min > this.key[q]){
                min = this.key[q];
                index = q;
            }
        }
        return index;
    }

    public void imprimeArvoreGeradoraMinima(Grafo grafo, int origem){
        System.out.println("vértice inicial: "+origem);
        int pesoTotal = 0;
        System.out.print("arestas: ");
        for(int i=0; i<this.pi.length; i++){
            if(this.pi[i]!=-1){
                System.out.print("("+this.pi[i]+","+i+") ");
                pesoTotal = pesoTotal + this.key[i];
            }
        }
        System.out.println();
        System.out.println("peso total: "+pesoTotal);
    }

    public Boolean participaConjunto(ArestaKruskal ak){
        for(int i=0; i<this.pi.length; i++){
            if(this.pi[i]!=-1){
                if((this.pi[i] == ak.getVerticeU() && i == ak.getVerticeV()) || (this.pi[i] == ak.getVerticeV() && i == ak.getVerticeU())){ //se der errado, inverter
                    return true;
                }
            }
        }
        return false;
    }

    public Boolean existeAresta(ArestaKruskal ak, ArrayList<ArestaKruskal> conjunto){
        for (ArestaKruskal aresta : conjunto) {
            if((aresta.getVerticeU() == ak.getVerticeV()) && (aresta.getVerticeV() == ak.getVerticeU())){
                return false;
            }
        }
        return true;
    }

    public void desenharPrim(Grafo grafo){
        ArrayList<ArestaKruskal> conjuntoAresta = new ArrayList<ArestaKruskal>();
        MutableGraph g = mutGraph("Grafo").setDirected(false).use((gr, ctx) -> {
            for (Vertice v : grafo.getVertices()) {
                mutNode(v.getNumero().toString());
                for (Aresta aresta : v.getArestas()) {
                    ArestaKruskal ak = new ArestaKruskal(v.getNumero(), aresta.getDestino(), aresta.getPeso());
                    if(existeAresta(ak, conjuntoAresta)){
                        mutNode(aresta.getDestino().toString());
                        Link a = mutNode(aresta.getDestino().toString()).linkTo();
                        if(participaConjunto(ak)){
                            a = a.with(Color.BLUE,Label.of(aresta.getPeso().toString()));
                        }else{
                            a = a.with(Label.of(aresta.getPeso().toString()));
                        }
                        mutNode(v.getNumero().toString()).addLink(a);
                    }
                    conjuntoAresta.add(ak);
                }
            }
		});
		try {
			Graphviz.fromGraph(g).width(500).render(Format.PNG).toFile(new File("src/images/prim+"+System.currentTimeMillis()+".png"));
            System.out.println("Desenho gerado com sucesso!");
        } catch (IOException e) {
			e.printStackTrace();
		}
    }
}
