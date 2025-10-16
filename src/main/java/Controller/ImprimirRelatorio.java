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

public class ImprimirRelatorio {
    private static final String CAMINHO_ANIMAL = "animal.json";
    private static final String CAMINHO_TUTOR = "tutor.json";
    private static final String CAMINHO_SETOR = "setor.json";

    public void ImprimirAnimal() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Animal> listaAnimais = carregarLista(CAMINHO_ANIMAL, new TypeToken<List<Animal>>() {}.getType());

        if (listaAnimais == null || listaAnimais.isEmpty()) {
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

    public void ImprimirSetor() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<SetorResponsavel> listaSetores = carregarLista(CAMINHO_SETOR, new TypeToken<List<SetorResponsavel>>() {}.getType());

        if (listaSetores == null || listaSetores.isEmpty()) {
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

    public void ImprimirTutor() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<PessoaTutora> listaTutores = carregarLista(CAMINHO_TUTOR, new TypeToken<List<PessoaTutora>>() {}.getType());

        if (listaTutores == null || listaTutores.isEmpty()) {
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
