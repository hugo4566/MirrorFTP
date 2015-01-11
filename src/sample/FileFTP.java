package sample;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Hugo on 11/01/2015.
 */
public class FileFTP {

    private IntegerProperty tipo;
    private StringProperty nome;
    private IntegerProperty tamanho;
    private StringProperty modificado;

    public FileFTP(String response) {
        String[] partes = response.split("\\s+");
        this.tipo = new SimpleIntegerProperty(Integer.valueOf(partes[1]));
        this.nome = new SimpleStringProperty(partes[8]);
        this.tamanho = new SimpleIntegerProperty(Integer.valueOf(partes[4]));
        this.modificado = new SimpleStringProperty(partes[5]+" "+partes[6]+" "+partes[7]);
    }


    public FileFTP(int tipo, String nome, int tamanho, String modificado) {
        this.tipo = new SimpleIntegerProperty(tipo);
        this.nome = new SimpleStringProperty(nome);
        this.tamanho = new SimpleIntegerProperty(tamanho);
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

    public int getTipo() {
        return tipo.get();
    }

    public IntegerProperty tipoProperty() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo.set(tipo);
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

    public int getTamanho() {
        return tamanho.get();
    }

    public IntegerProperty tamanhoProperty() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho.set(tamanho);
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