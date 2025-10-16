package Model;

import java.util.List;

public class SetorResponsavel {
    private String iD;
    private String nome;
    private String endereco;
    private List<PessoaTutora> pessoaTutoras;
    private List<Animal> animais;

    public SetorResponsavel(String id, String nome, String endereco, List<PessoaTutora> tutores, List<Animal> animais) {
        this.iD = id;
        this.nome = nome;
        this.endereco = endereco;
        this.pessoaTutoras = tutores;
        this.animais = animais;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
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

    public List<PessoaTutora> getPessoaTutoras() {
        return pessoaTutoras;
    }

    public void setPessoaTutoras(List<PessoaTutora> pessoaTutoras) {
        this.pessoaTutoras = pessoaTutoras;
    }

    public List<Animal> getAnimais() {
        return animais;
    }

    public void setAnimais(List<Animal> animais) {
        this.animais = animais;
    }
}
