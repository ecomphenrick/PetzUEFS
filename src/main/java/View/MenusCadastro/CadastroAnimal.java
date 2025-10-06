package View.MenusCadastro;

import Controller.PersistenciaAnimal;
import Model.Animal;
import java.time.LocalDate;
import java.util.Scanner;

public class CadastroAnimal {

    public void CadastroAnimal() {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("CADASTRANDO ANIMAL: ");

            System.out.println("Digite o ID: ");
            String iD = sc.nextLine();

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
            Animal animal = new Animal(iD, nome, especie, raca, dataNascimento, sexo, situacao);

            System.out.println("Animal cadastrado com sucesso!");

            PersistenciaAnimal persistenciaAnimal = new PersistenciaAnimal();
            persistenciaAnimal.salvarAnimal(animal);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}



