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
    private static final String CaminhoAnimal = "animal.json";
    private static final String CaminhoTutor = "tutor.json";
    private static final String CaminhoSetor = "setor.json";

    public void ImprimirAnimal() {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        List<Animal> listaAnimais = new ArrayList<>();

        try (FileReader reader = new FileReader(CaminhoAnimal)) {
            Type tipoLista = new TypeToken<List<Animal>>() {}.getType();
            listaAnimais = gson.fromJson(reader, tipoLista);
            if (listaAnimais == null) {
                listaAnimais = new ArrayList<>();
            }
        } catch (Exception e) {
            System.out.println("❌ Erro ao ler o arquivo: " + e.getMessage());
            return;
        }

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
            LocalDate hoje = LocalDate.now();
            Period idade = Period.between(data, hoje);
            System.out.println("Idade: " + idade.getYears() + " ano(s) e " + idade.getMonths() + " meses");

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

            System.out.println("\n");
        }
    }

    public void ImprimirSetor (){
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        List<SetorResponsavel> listaSetores = new ArrayList<>();

        try (FileReader reader = new FileReader(CaminhoSetor)) {
            Type tipoLista = new TypeToken<List<SetorResponsavel>>() {}.getType();
            listaSetores = gson.fromJson(reader, tipoLista);
            if (listaSetores == null) {
                listaSetores = new ArrayList<>();
            }
        } catch (Exception e) {
            System.out.println("❌ Erro ao ler o arquivo: " + e.getMessage());
            return;
        }
        if (listaSetores.isEmpty() || listaSetores == null){
            System.out.println("Não há registro de setores no sistema. ");
        }else {
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
                    System.out.println("Nenhum animal cadastrado no setor.");
                } else {
                    System.out.println(" Animais:");
                    for (Animal a : s.getAnimais()) {
                        System.out.println("    - " + a.getNome() + " (" + a.getEspecie() + ")");
                    }
                }
            }
        }
    }
    public void ImprimirTutor() {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        List<PessoaTutora> listaTutores = new ArrayList<>();

        try (FileReader reader = new FileReader(CaminhoTutor)) {
            Type tipoLista = new TypeToken<List<PessoaTutora>>() {}.getType();
            listaTutores = gson.fromJson(reader, tipoLista);
            if (listaTutores == null) {
                listaTutores = new ArrayList<>();
            }
        } catch (Exception e) {
            System.out.println("❌ Erro ao ler o arquivo: " + e.getMessage());
            return;
        }

        if (listaTutores.isEmpty() || listaTutores == null) {
            System.out.println("Não há registro de tutores no sistema.");
        } else {
            for (PessoaTutora t : listaTutores) {
                System.out.println("Nome: " + t.getNome());
                System.out.println("ID: " + t.getiD());
                System.out.println("Endereço: " + t.getEndereco());
                System.out.println("E-mail: " + t.getEmail());
                System.out.println("Telefone: " + t.getTelefone());
                System.out.println("Setor: " + t.get);

                if (t.getAnimals() == null || t.getAnimals().isEmpty()) {
                    System.out.println("  Nenhum animal associado a este tutor.");
                } else {
                    System.out.println("  Animais:");
                    for (Animal a : t.getAnimals()) {
                        System.out.println("    - " + a.getNome() + " (" + a.getEspecie() + "), Setor: " + a.getNomeSetor());
                    }
                }
                System.out.println("\n");
            }
        }
    }

}
