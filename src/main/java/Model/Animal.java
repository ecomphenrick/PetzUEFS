package Model;

import java.time.LocalDate;
import java.util.List;

public class Animal {
    private String iD;
    private String nome;
    private String especie;
    private String raca;
    private String dataNascimento;
    private String sexo;
    private String situacaoAtual;
    private String nomeSetor;
    private List<String> tutores;

    public Animal(String iD, String nome, String especie, String raca, String dataNascimento, String sexo, String situacaoAtual, String nomeSetor, List<String> tutores){
        this.iD = iD;
        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.situacaoAtual = situacaoAtual;
        this.nomeSetor = nomeSetor;
        this.tutores = tutores;
    }



    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getiD() {
        return iD;
    }

    public void setiD(String iD) {
        this.iD = iD;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getSituacaoAtual() {
        return situacaoAtual;
    }

    public void setSituacaoAtual(String situacaoAtual) {
        this.situacaoAtual = situacaoAtual;
    }

    public String getNomeSetor() {
        return nomeSetor;
    }

    public void setNomeSetor(String nomeSetor) {
        this.nomeSetor = nomeSetor;
    }

    public List<String> getTutores() {
        return tutores;
    }

    public void setTutores(List<String> tutores) {
        this.tutores = tutores;
    }
}
