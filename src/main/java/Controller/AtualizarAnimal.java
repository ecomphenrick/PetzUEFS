package Controller;

import Model.Animal;
import Model.SetorResponsavel;
import Model.PessoaTutora;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AtualizarAnimal {
    private static final String CAMINHO_ANIMAL = "animal.json";
    private static final String CAMINHO_SETOR = "setor.json";
    private static final String CAMINHO_TUTOR = "tutor.json";

    public void atualizarAnimal(String nomeBuscado) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Scanner sc = new Scanner(System.in);

        List<Animal> listaAnimais = lerListaAnimal(gson);
        if (listaAnimais.isEmpty()) return;

        Animal animalEncontrado = null;
        for (Animal a : listaAnimais) {
            if (a.getNome().equalsIgnoreCase(nomeBuscado)) {
                animalEncontrado = a;
                break;
            }
        }

        if (animalEncontrado == null) {
            System.out.println("⚠️ Animal não encontrado.");
            return;
        }

        String nomeAntigo = animalEncontrado.getNome();

        System.out.println("Animal encontrado: " + nomeAntigo);
        System.out.println("Escolha a informação que deseja atualizar:");
        System.out.println("1 - Nome");
        System.out.println("2 - Espécie");
        System.out.println("3 - Raça");
        System.out.println("4 - Data de Nascimento");
        System.out.println("5 - Sexo");
        System.out.println("6 - Situação Atual");

        int opcao = sc.nextInt();
        sc.nextLine();

        switch (opcao) {
            case 1:
                System.out.print("Novo nome: ");
                animalEncontrado.setNome(sc.nextLine());
                break;
            case 2:
                System.out.print("Nova espécie: ");
                animalEncontrado.setEspecie(sc.nextLine());
                break;
            case 3:
                System.out.print("Nova raça: ");
                animalEncontrado.setRaca(sc.nextLine());
                break;
            case 4:
                int mes;
                do {
                    System.out.print("Mês (1-12): ");
                    mes = sc.nextInt();
                    sc.nextLine();
                } while (mes < 1 || mes > 12);
                System.out.print("Ano: ");
                int ano = sc.nextInt();
                sc.nextLine();
                LocalDate dataNascimento = LocalDate.of(ano, mes, 1);
                animalEncontrado.setDataNascimento(dataNascimento.toString());
                break;
            case 5:
                System.out.print("Novo sexo (F ou M): ");
                animalEncontrado.setSexo(sc.nextLine());
                break;
            case 6:
                System.out.println("Situação Atual: ");
                System.out.println("0 - Em observação");
                System.out.println("1 - Disponível para adoção");
                System.out.println("2 - Em tratamento");
                int aux = sc.nextInt();
                sc.nextLine();
                String situacao;
                switch (aux) {
                    case 0: situacao = "Em observação"; break;
                    case 1: situacao = "Disponível para adoção"; break;
                    case 2: situacao = "Em tratamento"; break;
                    default: situacao = "Em observação"; break;
                }
                animalEncontrado.setSituacaoAtual(situacao);
                break;
            default:
                System.out.println("Opção inválida.");
                return;
        }

        salvarListaAnimal(listaAnimais, gson);
        atualizarSetores(animalEncontrado, gson);
        atualizarTutores(animalEncontrado, nomeAntigo, gson);

        System.out.println("✅ Animal atualizado com sucesso!");
    }

    private List<Animal> lerListaAnimal(Gson gson) {
        List<Animal> lista = new ArrayList<>();
        try (FileReader reader = new FileReader(CAMINHO_ANIMAL)) {
            Type tipoLista = new TypeToken<List<Animal>>() {}.getType();
            lista = gson.fromJson(reader, tipoLista);
            if (lista == null) lista = new ArrayList<>();
        } catch (Exception e) {}
        return lista;
    }

    private void salvarListaAnimal(List<Animal> lista, Gson gson) {
        try (FileWriter writer = new FileWriter(CAMINHO_ANIMAL)) {
            gson.toJson(lista, writer);
        } catch (Exception e) {}
    }

    private void atualizarSetores(Animal animal, Gson gson) {
        List<SetorResponsavel> setores = new ArrayList<>();
        try (FileReader reader = new FileReader(CAMINHO_SETOR)) {
            Type tipoLista = new TypeToken<List<SetorResponsavel>>() {}.getType();
            setores = gson.fromJson(reader, tipoLista);
            if (setores == null) setores = new ArrayList<>();
        } catch (Exception e) { return; }

        for (SetorResponsavel s : setores) {
            if (s.getAnimais() != null) {
                for (int i = 0; i < s.getAnimais().size(); i++) {
                    if (s.getAnimais().get(i).getiD().equals(animal.getiD())) {
                        s.getAnimais().set(i, animal);
                    }
                }
            }
        }

        try (FileWriter writer = new FileWriter(CAMINHO_SETOR)) {
            gson.toJson(setores, writer);
        } catch (Exception e) {}
    }

    private void atualizarTutores(Animal animal, String nomeAntigo, Gson gson) {
        List<PessoaTutora> tutores = new ArrayList<>();
        try (FileReader reader = new FileReader(CAMINHO_TUTOR)) {
            Type tipoLista = new TypeToken<List<PessoaTutora>>() {}.getType();
            tutores = gson.fromJson(reader, tipoLista);
            if (tutores == null) tutores = new ArrayList<>();
        } catch (Exception e) { return; }

        for (PessoaTutora t : tutores) {
            if (t.getAnimals() != null) {
                for (int i = 0; i < t.getAnimals().size(); i++) {
                    if (t.getAnimals().get(i).equals(nomeAntigo)) {
                        t.getAnimals().set(i, animal.getNome());
                    }
                }
            }
        }

        try (FileWriter writer = new FileWriter(CAMINHO_TUTOR)) {
            gson.toJson(tutores, writer);
        } catch (Exception e) {}
    }
}
