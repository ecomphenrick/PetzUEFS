package Model;

import java.time.LocalDate;
import java.util.List;

/**
 * Representa um animal cadastrado no sistema.
 *
 * @author Henrick Ferreira
 */
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

    /**
     * Construtor para criar um novo objeto {@code Animal}.
     *
     * @param iD Identificador único do animal
     * @param nome Nome do animal
     * @param especie Espécie do animal
     * @param raca Raça do animal
     * @param dataNascimento Data de nascimento do animal
     * @param sexo Sexo do animal
     * @param situacaoAtual Situação atual do animal
     * @param nomeSetor Nome do setor em que o animal se encontra
     * @param tutores Lista de tutores associados
     */
    public Animal(String iD, String nome, String especie, String raca, String dataNascimento, String sexo, String situacaoAtual, String nomeSetor, List<String> tutores) {
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

    /**
     * @return Data de nascimento do animal
     */
    public String getDataNascimento() {
        return dataNascimento;
    }

    /**
     * @param dataNascimento Nova data de nascimento
     */
    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    /**
     * @return Espécie do animal
     */
    public String getEspecie() {
        return especie;
    }

    /**
     * @param especie Nova espécie
     */
    public void setEspecie(String especie) {
        this.especie = especie;
    }

    /**
     * @return ID do animal
     */
    public String getiD() {
        return iD;
    }

    /**
     * @param iD Novo ID
     */
    public void setiD(String iD) {
        this.iD = iD;
    }

    /**
     * @return Nome do animal
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome Novo nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return Raça do animal
     */
    public String getRaca() {
        return raca;
    }

    /**
     * @param raca Nova raça
     */
    public void setRaca(String raca) {
        this.raca = raca;
    }

    /**
     * @return Sexo do animal
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * @param sexo Novo sexo
     */
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    /**
     * @return Situação atual do animal
     */
    public String getSituacaoAtual() {
        return situacaoAtual;
    }

    /**
     * @param situacaoAtual Nova situação
     */
    public void setSituacaoAtual(String situacaoAtual) {
        this.situacaoAtual = situacaoAtual;
    }

    /**
     * @return Nome do setor
     */
    public String getNomeSetor() {
        return nomeSetor;
    }

    /**
     * @param nomeSetor Novo nome do setor
     */
    public void setNomeSetor(String nomeSetor) {
        this.nomeSetor = nomeSetor;
    }

    /**
     * @return Lista de tutores
     */
    public List<String> getTutores() {
        return tutores;
    }

    /**
     * @param tutores Nova lista de tutores
     */
    public void setTutores(List<String> tutores) {
        this.tutores = tutores;
    }
}

