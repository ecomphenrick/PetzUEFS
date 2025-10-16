package View.MenusCadastro;

import Controller.PersistenciaAnimal;
import Model.Animal;
import Model.PessoaTutora;
import Model.SetorResponsavel;
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

/**
 * Classe responsável pelo cadastro de animais no sistema PetzUEFS.
 * Faz a persistência em arquivos JSON de animais, setores e tutores.
 */
public class CadastroAnimal {
    private static final String CAMINHO_ARQUIVO = "animal.json";
    private static final String CAMINHO_ARQUIVO2 = "setor.json";
    private static final String CAMINHO_ARQUIVO3 = "tutor.json";

    /**
     * Realiza o cadastro de um novo animal, associando-o a um setor e tutores.
     * Valida ID único, datas, sexo e situação. Atualiza automaticamente
     * os arquivos setor.json e tutor.json.
     */
    public void CadastroAnimal() {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("CADASTRANDO ANIMAL: ");
            boolean validateID = false;
            String iD;
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            List<Animal> listaAnimais = new ArrayList<>();

            try (FileReader reader = new FileReader(CAMINHO_ARQUIVO)) {
                Type tipoLista = new TypeToken<List<Animal>>() {}.getType();
                listaAnimais = gson.fromJson(reader, tipoLista);
                if (listaAnimais == null) listaAnimais = new ArrayList<>();
            } catch (Exception e) {
                System.out.println("❌ Erro ao ler o arquivo: " + e.getMessage());
                return;
            }

            List<SetorResponsavel> listaSetores = new ArrayList<>();
            try (FileReader reader = new FileReader(CAMINHO_ARQUIVO2)) {
                Type tipoLista2 = new TypeToken<List<SetorResponsavel>>() {}.getType();
                listaSetores = gson.fromJson(reader, tipoLista2);
                if (listaSetores == null) listaSetores = new ArrayList<>();
            } catch (Exception e) {
                System.out.println("❌ Erro ao ler o arquivo: " + e.getMessage());
                return;
            }

            List<PessoaTutora> listaTutores = new ArrayList<>();
            try (FileReader reader = new FileReader(CAMINHO_ARQUIVO3)) {
                Type tipoLista3 = new TypeToken<List<PessoaTutora>>() {}.getType();
                listaTutores = gson.fromJson(reader, tipoLista3);
                if (listaTutores == null) listaTutores = new ArrayList<>();
            } catch (Exception e) {
                System.out.println("❌ Erro ao ler o arquivo: " + e.getMessage());
                return;
            }

            int indiceEscolhido = -1;
            if (listaSetores == null || listaSetores.isEmpty()) {
                System.out.println("⚠️ Nenhum setor cadastrado! Cadastre um setor primeiro.");
                return;
            } else {
                do {
                    System.out.println("Cadastrar em qual setor?");
                    for (int i = 0; i < listaSetores.size(); i++) {
                        SetorResponsavel s = listaSetores.get(i);
                        System.out.println("[" + i + "] " + s.getNome());
                    }

                    System.out.print("Digite o índice do setor: ");
                    indiceEscolhido = sc.nextInt();
                    sc.nextLine();

                    if (indiceEscolhido < 0 || indiceEscolhido >= listaSetores.size()) {
                        System.out.println("❌ Índice inválido! Tente novamente.\n");
                    }

                } while (indiceEscolhido < 0 || indiceEscolhido >= listaSetores.size());
            }

            if (listaSetores.get(indiceEscolhido).getPessoaTutoras() == null || listaSetores.get(indiceEscolhido).getPessoaTutoras().isEmpty()) {
                System.out.println("⚠️ Este setor não possui tutores cadastrados. Cadastre um tutor primeiro.");
                return;
            }

            do {
                System.out.println("Digite o ID (Ex: A001): ");
                String regexiD = "^A\\d{3}$";
                iD = sc.nextLine();

                if (iD.matches(regexiD)) {
                    validateID = true;
                    for (Animal a : listaAnimais) {
                        if (a.getiD().equalsIgnoreCase(iD)) {
                            System.out.println("Esse ID já existe! Digite outro.");
                            validateID = false;
                            break;
                        }
                    }
                } else {
                    System.out.println("Formato inválido, o formato correto é 'A' seguido de 3 números! Ex: A001 ou A002...");
                    validateID = false;
                }
            } while (!validateID);
            System.out.println("ID OK!");

            System.out.println("Digite o nome: ");
            String nome = sc.nextLine();

            System.out.println("Digite a espécie: ");
            String especie = sc.nextLine();

            System.out.println("Digite a raça: ");
            String raca = sc.nextLine();

            System.out.println("Data de Nascimento:\n ");
            int mes;
            do {
                System.out.println("Mês (1-12): ");
                mes = sc.nextInt();
                sc.nextLine();
                if (mes < 1 || mes > 12) System.out.println("Mês inválido! Digite um número entre 1 e 12.\n");
            } while (mes < 1 || mes > 12);

            System.out.println("Ano: ");
            int ano = sc.nextInt();
            sc.nextLine();

            System.out.println("Digite o sexo (F ou M): ");
            String sexo = sc.nextLine();

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

            LocalDate dataNascimento = LocalDate.of(ano, mes, 1);
            String dataStr = dataNascimento.toString();
            String nomeSetor = listaSetores.get(indiceEscolhido).getNome();
            List<String> nomesTutores = new ArrayList<>();
            for (PessoaTutora t : listaSetores.get(indiceEscolhido).getPessoaTutoras()) {
                nomesTutores.add(t.getNome());
            }
            Animal animal = new Animal(iD, nome, especie, raca, dataStr, sexo, situacao, nomeSetor, nomesTutores);

            SetorResponsavel setor = listaSetores.get(indiceEscolhido);
            if (setor.getAnimais() == null) setor.setAnimais(new ArrayList<>());
            setor.getAnimais().add(animal);

            for (PessoaTutora tutor : setor.getPessoaTutoras()) {
                if (tutor.getAnimals() == null) tutor.setAnimals(new ArrayList<>());
                tutor.getAnimals().add(animal.getNome());
                for (PessoaTutora tGlobal : listaTutores) {
                    if (tGlobal.getiD().equals(tutor.getiD())) {
                        if (tGlobal.getAnimals() == null) tGlobal.setAnimals(new ArrayList<>());
                        tGlobal.getAnimals().add(animal.getNome());
                    }
                }
            }

            try (FileWriter writer = new FileWriter(CAMINHO_ARQUIVO2)) {
                gson.toJson(listaSetores, writer);
            } catch (Exception e) {
                System.out.println("❌ Erro ao atualizar setor.json: " + e.getMessage());
            }

            try (FileWriter writer = new FileWriter(CAMINHO_ARQUIVO3)) {
                gson.toJson(listaTutores, writer);
            } catch (Exception e) {
                System.out.println("❌ Erro ao atualizar tutor.json: " + e.getMessage());
            }

            PersistenciaAnimal persistenciaAnimal = new PersistenciaAnimal();
            persistenciaAnimal.salvarAnimal(animal);
            System.out.println("Animal cadastrado com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
