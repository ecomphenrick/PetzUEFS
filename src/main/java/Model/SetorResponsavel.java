package Model;

import java.util.List;

/**
 * Representa um setor responsável pelo cuidado e gestão de animais,
 * contendo informações sobre os tutores e os animais associados a ele.
 *
 * @author Henrick Ferreira
 */
public class SetorResponsavel {
    private String iD;
    private String nome;
    private String endereco;
    private List<PessoaTutora> pessoaTutoras;
    private List<Animal> animais;

    /**
     * Construtor para criar um novo {@code SetorResponsavel}.
     *
     * @param id Identificador único do setor
     * @param nome Nome do setor responsável
     * @param endereco Endereço do setor
     * @param tutores Lista de pessoas tutoras associadas ao setor
     * @param animais Lista de animais sob responsabilidade do setor
     */
    public SetorResponsavel(String id, String nome, String endereco, List<PessoaTutora> tutores, List<Animal> animais) {
        this.iD = id;
        this.nome = nome;
        this.endereco = endereco;
        this.pessoaTutoras = tutores;
        this.animais = animais;
    }

    /**
     * @return Endereço do setor
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * @param endereco Novo endereço do setor
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    /**
     * @return Identificador único do setor
     */
    public String getiD() {
        return iD;
    }

    /**
     * @param iD Novo identificador único do setor
     */
    public void setiD(String iD) {
        this.iD = iD;
    }

    /**
     * @return Nome do setor
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome Novo nome do setor
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return Lista de pessoas tutoras associadas ao setor
     */
    public List<PessoaTutora> getPessoaTutoras() {
        return pessoaTutoras;
    }

    /**
     * @param pessoaTutoras Nova lista de pessoas tutoras associadas ao setor
     */
    public void setPessoaTutoras(List<PessoaTutora> pessoaTutoras) {
        this.pessoaTutoras = pessoaTutoras;
    }

    /**
     * @return Lista de animais sob responsabilidade do setor
     */
    public List<Animal> getAnimais() {
        return animais;
    }

    /**
     * @param animais Nova lista de animais sob responsabilidade do setor
     */
    public void setAnimais(List<Animal> animais) {
        this.animais = animais;
    }
}
