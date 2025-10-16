package Controller;

import Model.Animal;
import Model.PessoaTutora;
import Model.SetorResponsavel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por gerar relatórios de animais, tutores e setores.
 * <p>
 * Cada método exibe informações detalhadas no console, de forma clara e organizada.
 * </p>
 */
public class ImprimirRelatorio {

    private static final String CAMINHO_ANIMAL = "animal.json";
    private static final String CAMINHO_TUTOR = "tutor.json";
    private static final String CAMINHO_SETOR = "setor.json";

    /**
     * Imprime no console todos os animais cadastrados,
     * incluindo dados básicos, idade calculada e tutores associados.
     */
    public void ImprimirAnimal() {
        List<Animal> listaAnimais = carregarLista(CAMINHO_ANIMAL, new TypeToken<List<Animal>>() {}.getType());

        if (listaAnimais.isEmpty()) {
            System.out.println("Não há registro de animais no sistema.");
            return;
        }

        for (Animal a : listaAnimais) {
            System.out.println("Nome: " + a.getNome());
            System.out.println("ID: " + a.getiD());
            System.out.println("Espécie: " + a.getEspecie());
            System.out.println("Raça: " + a.getRaca());
            System.out.println("Sexo: " + a.getSexo());

            LocalDate data = LocalDate.parse(a.getDataNascimento());
            Period idade = Period.between(data, LocalDate.now());
            System.out.println("Idade: " + idade.getYears() + " ano(s) e " + idade.getMonths() + " mês(es)");

            System.out.println("Situação: " + a.getSituacaoAtual());
            System.out.println("Setor: " + a.getNomeSetor());

            if (a.getTutores() == null || a.getTutores().isEmpty()) {
                System.out.println("  Nenhum tutor cadastrado nesse setor.");
            } else {
                System.out.println("  Tutores:");
                for (String nomeTutor : a.getTutores()) {
                    System.out.println("    - " + nomeTutor);
                }
            }
            System.out.println();
        }
    }

    /**
     * Imprime no console todos os setores cadastrados,
     * mostrando informações do setor, tutores e animais associados.
     */
    public void ImprimirSetor() {
        List<SetorResponsavel> listaSetores = carregarLista(CAMINHO_SETOR, new TypeToken<List<SetorResponsavel>>() {}.getType());

        if (listaSetores.isEmpty()) {
            System.out.println("Não há registro de setores no sistema.");
            return;
        }

        for (SetorResponsavel s : listaSetores) {
            System.out.println("Nome: " + s.getNome());
            System.out.println("ID: " + s.getiD());
            System.out.println("Endereço: " + s.getEndereco());

            if (s.getPessoaTutoras() == null || s.getPessoaTutoras().isEmpty()) {
                System.out.println("  Nenhum tutor cadastrado nesse setor.");
            } else {
                System.out.println("  Tutores:");
                for (PessoaTutora p : s.getPessoaTutoras()) {
                    System.out.println("    - " + p.getNome() + " (ID: " + p.getiD() + ")");
                }
            }

            if (s.getAnimais() == null || s.getAnimais().isEmpty()) {
                System.out.println("  Nenhum animal cadastrado nesse setor.");
            } else {
                System.out.println("  Animais:");
                for (Animal a : s.getAnimais()) {
                    System.out.println("    - " + a.getNome() + " (" + a.getEspecie() + ")");
                }
            }
            System.out.println();
        }
    }

    /**
     * Imprime no console todos os tutores cadastrados,
     * incluindo informações pessoais e animais associados.
     */
    public void ImprimirTutor() {
        List<PessoaTutora> listaTutores = carregarLista(CAMINHO_TUTOR, new TypeToken<List<PessoaTutora>>() {}.getType());

        if (listaTutores.isEmpty()) {
            System.out.println("Não há registro de tutores no sistema.");
            return;
        }

        for (PessoaTutora t : listaTutores) {
            System.out.println("Nome: " + t.getNome());
            System.out.println("ID: " + t.getiD());
            System.out.println("Endereço: " + t.getEndereco());
            System.out.println("E-mail: " + t.getEmail());
            System.out.println("Telefone: " + t.getTelefone());

            if (t.getAnimals() == null || t.getAnimals().isEmpty()) {
                System.out.println("  Nenhum animal associado a este tutor.");
            } else {
                System.out.println("  Animais:");
                for (String nomeAnimal : t.getAnimals()) {
                    System.out.println("    - " + nomeAnimal);
                }
            }
            System.out.println();
        }
    }

    /**
     * Carrega uma lista de objetos a partir de um arquivo JSON.
     *
     * @param <T> Tipo dos objetos a serem carregados.
     * @param caminho Caminho do arquivo JSON.
     * @param tipoLista Tipo da lista de objetos (usado pelo Gson).
     * @return Lista de objetos carregados, ou lista vazia em caso de erro ou arquivo vazio.
     */
    private <T> List<T> carregarLista(String caminho, Type tipoLista) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileReader reader = new FileReader(caminho)) {
            List<T> lista = gson.fromJson(reader, tipoLista);
            return (lista != null) ? lista : new ArrayList<>();
        } catch (Exception e) {
            System.out.println("❌ Erro ao ler o arquivo (" + caminho + "): " + e.getMessage());
            return new ArrayList<>();
        }
    }
}

