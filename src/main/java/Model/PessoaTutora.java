package Model;

import java.util.List;

/**
 * Representa uma pessoa tutora de animais cadastrada no sistema.
 * Contém informações pessoais e a lista de animais associados.
 *
 * @author Henrick Ferreira
 */
public class PessoaTutora {
    private String iD;
    private String nome;
    private String endereco;
    private String telefone;
    private String email;
    private List<String> animals;

    /**
     * Construtor para criar uma nova {@code PessoaTutora}.
     *
     * @param id Identificador único da pessoa tutora
     * @param nome Nome completo da pessoa tutora
     * @param endereco Endereço residencial
     * @param telefone Número de telefone
     * @param email Endereço de e-mail
     * @param animals Lista de identificadores dos animais tutelados
     */
    public PessoaTutora(String id, String nome, String endereco, String telefone, String email, List<String> animals) {
        this.iD = id;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.animals = animals;
    }

    /**
     * @return Endereço de e-mail da pessoa tutora
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email Novo endereço de e-mail
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return Endereço residencial
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * @param endereco Novo endereço residencial
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    /**
     * @return Identificador único da pessoa tutora
     */
    public String getiD() {
        return iD;
    }

    /**
     * @param iD Novo identificador único
     */
    public void setiD(String iD) {
        this.iD = iD;
    }

    /**
     * @return Número de telefone da pessoa tutora
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * @param telefone Novo número de telefone
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * @return Nome da pessoa tutora
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome Novo nome da pessoa tutora
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return Lista de identificadores dos animais tutelados
     */
    public List<String> getAnimals() {
        return animals;
    }

    /**
     * @param animals Nova lista de identificadores dos animais tutelados
     */
    public void setAnimals(List<String> animals) {
        this.animals = animals;
    }
}
