package sample;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Hugo on 11/01/2015.
 */
public class FileFTP {

    private SimpleStringProperty tipo;
    private StringProperty nome;
    private SimpleStringProperty tamanho;
    private StringProperty nomeModificador;
    private StringProperty modificado;

    public FileFTP(String response) {
        String[] partes = response.split("\\s+");
        this.tipo = new SimpleStringProperty(partes[1]);
        this.nome = new SimpleStringProperty(partes[8]);
        this.tamanho = new SimpleStringProperty(partes[4]);
        this.nomeModificador = new SimpleStringProperty(partes[2]);
        this.modificado = new SimpleStringProperty(partes[5]+" "+partes[6]+" "+partes[7]);
    }


    public FileFTP(String tipo, String nome, String tamanho,String nomeModificador, String modificado) {
        this.tipo = new SimpleStringProperty(tipo);
        this.nome = new SimpleStringProperty(nome);
        this.tamanho = new SimpleStringProperty(tamanho);
        this.nomeModificador = new SimpleStringProperty(nomeModificador);
        this.modificado = new SimpleStringProperty(modificado);
    }

    /*
    public FileFTP(File file) {
        this.tipo = file.isFile() ? 2 : 1;
        this.nome = file.getName();
        this.tamanho = (int) file.getTotalSpace();
        this.modificado = "";
    }
    */

    public String getNomeModificador() {
        return nomeModificador.get();
    }

    public StringProperty nomeModificadorProperty() {
        return nomeModificador;
    }

    public void setNomeModificador(String nomeModificador) {
        this.nomeModificador.set(nomeModificador);
    }

    public String getTipo() {
        return tipo.get();
    }

    public SimpleStringProperty tipoProperty() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo.set(tipo);
    }

    public String getTamanho() {
        return tamanho.get();
    }

    public SimpleStringProperty tamanhoProperty() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho.set(tamanho);
    }

    public String getNome() {
        return nome.get();
    }

    public StringProperty nomeProperty() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public String getModificado() {
        return modificado.get();
    }

    public StringProperty modificadoProperty() {
        return modificado;
    }

    public void setModificado(String modificado) {
        this.modificado.set(modificado);
    }

    @Override
    public String toString() {
        return "FileFTP{" +
                "tipo=" + tipo +
                ", nome='" + nome + '\'' +
                ", tamanho=" + tamanho +
                ", modificado='" + modificado + '\'' +
                '}';
    }
}