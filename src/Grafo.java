import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Collections;
import java.util.LinkedList;

/* Finalidade: Classe que contém a coleta do grafo a partir de um arquivo txt.
 * Recebe valores de um arquivo.
*/

public class Grafo {
    Integer qtdVertices;
    String orientado;
    Vertice[] vertices;

    /*Finalidade: construtor para pegar os dados do arquivo.
	 * Pré condição: passar o nome do arquivo.
	 * Pós condição: Nenhum. */
    public Grafo(String nomeArq){
        try {
            BufferedReader arq = new BufferedReader(new FileReader(new File(nomeArq)));
            String linha = arq.readLine();
            int cont = 0;
            String[] palavra;
            while(linha != null){
                if(cont == 0){ //Primeira linha do arquivo
                    palavra = linha.split("=");
                    this.orientado = palavra[1];
                }else if(cont == 1){ //Segunda linha do arquivo
                    palavra = linha.split("=");
                    this.qtdVertices = Integer.valueOf(palavra[1]);
                    this.vertices = new Vertice[this.qtdVertices];
                    for(int i=0; i<this.qtdVertices; i++){
                        Vertice v = new Vertice(i);
                        this.vertices[i] = v;
                    }
                }else{
                    palavra = linha.split("[(),:]");
                    //para cada aresta
                    Aresta a = new Aresta(Integer.valueOf(palavra[4]), Integer.valueOf(palavra[2]));
                    this.vertices[Integer.valueOf(palavra[1])].setAresta(a);
                    if(this.orientado.equals("nao")){
                        Aresta b = new Aresta(Integer.valueOf(palavra[4]), Integer.valueOf(palavra[1]));
                        this.vertices[Integer.valueOf(palavra[2])].setAresta(b);
                    }
                }
                cont++;
                linha = arq.readLine();
            }
        } catch (Exception e) {
            System.out.println("Erro ao abrir o arquivo: "+e);
        }
        this.ordenarArestas();
    }

    /*Finalidade: Ordenar as arestas.
	 * Pré condição: Nenhum.
	 * Pós condição: Nenhum. */
    public void ordenarArestas(){
        for(int i=0; i<this.qtdVertices; i++){
            Collections.sort( (LinkedList<Aresta>) this.vertices[i].getArestas());
        }
    }

    // getters e setter dos atributos da classe
    public Integer getQtdVertices(){
        return this.qtdVertices;
    }

    public void setQtdVertices(Integer qtdVertices){
        this.qtdVertices = qtdVertices;
    }

    public String getOrientado(){
        return this.orientado;
    }

    public void setOrientado(String orientado){
        this.orientado = orientado;
    }
    
    public Vertice[] getVertices(){
        return this.vertices;
    }

    public void setVertices(Vertice[] vertices){
        this.vertices = vertices;
    }
    // ---------------------------------------
}
