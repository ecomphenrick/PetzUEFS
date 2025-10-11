package View.MenusCadastro;

import Controller.PersistenciaAnimal;
import Model.Animal;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CadastroAnimal {
    private static final String CAMINHO_ARQUIVO = "animal.json";
    public void CadastroAnimal() {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("CADASTRANDO ANIMAL: ");
            boolean validateID = false;
            String iD;
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();
            List<Animal> listaAnimais = new ArrayList<>();

            try (FileReader reader = new FileReader(CAMINHO_ARQUIVO)) {
                Type tipoLista = new TypeToken<List<Animal>>() {}.getType();
                listaAnimais = gson.fromJson(reader, tipoLista);
                if (listaAnimais == null) {
                    listaAnimais = new ArrayList<>();
                }
            } catch (Exception e) {
                System.out.println("❌ Erro ao ler o arquivo: " + e.getMessage());
                return;
            }


            do {
                System.out.println("Digite o ID (Ex: A001): ");
                String regex = "^A\\d{3}$";
                iD = sc.nextLine();

                if (iD.matches(regex)) {
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

            System.out.println("Data de Nascimento: ");
            System.out.println("Mês: ");
            int mes = sc.nextInt();
            sc.nextLine();
            System.out.println("Ano: ");
            int ano = sc.nextInt();
            sc.nextLine();

            System.out.println("Digite o sexo: ");
            String sexo = sc.nextLine();

            System.out.println("Situação Atual: ");
            System.out.println("0 - Em observação");
            System.out.println("1 - Disponível para adoção");
            System.out.println("2 - Em tratamento");
            int aux = sc.nextInt();
            sc.nextLine();

            String situacao;
            switch (aux) {
                case 0 -> situacao = "Em observação";
                case 1 -> situacao = "Disponível para adoção";
                case 2 -> situacao = "Em tratamento";
                default -> {
                    System.out.println("Opção inválida, definindo 'Em observação'");
                    situacao = "Em observação";
                }
            }

            LocalDate dataNascimento = LocalDate.of(ano, mes, 1);
            String dataStr = dataNascimento.toString();
            System.out.println(dataStr);
            Animal animal = new Animal(iD, nome, especie, raca, dataStr, sexo, situacao);

            PersistenciaAnimal persistenciaAnimal = new PersistenciaAnimal();
            persistenciaAnimal.salvarAnimal(animal);
            System.out.println("Animal cadastrado com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}



