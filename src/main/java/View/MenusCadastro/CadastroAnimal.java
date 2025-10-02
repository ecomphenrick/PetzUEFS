package View.MenusCadastro;

import Model.Animal;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Scanner;

public class CadastroAnimal {
    public void CadastroAnimal (){
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
            System.out.println("Mes: ");
            int mes = sc.nextInt();
            sc.nextLine();
            System.out.println("Ano: ");
            int ano = sc.nextInt();
            sc.nextLine();
            System.out.println("Digite o sexo: ");
            String sexo = sc.nextLine();
            System.out.println("Situação Atual: ");
            String situacao = "";
            System.out.println("0 - Em observação");
            System.out.println("1 - Disponível para adoção");
            System.out.println("2 - Em tratamento");
            int aux = sc.nextInt();
            sc.nextLine();
            switch (aux){
                case 0:
                    situacao = "Em observação";
                    break;
                case 1:
                    situacao = "Disponivel para adoção";
                    break;
                case 2:
                    situacao = "Em tratamento";
                    break;
                default:
                    System.out.println("Opção invalida");
                    break;
            }
            LocalDate dataNascimento  = LocalDate.of(ano, mes, 1);
            Animal animal = new Animal(iD, nome, especie, raca, dataNascimento, sexo, situacao);
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
    }
}

