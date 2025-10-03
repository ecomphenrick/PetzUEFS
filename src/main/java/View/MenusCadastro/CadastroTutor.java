package View.MenusCadastro;

import Model.PessoaTutora;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CadastroTutor {
    public void CadastroTutor (){
        Scanner sc = new Scanner(System.in);
        System.out.println("CADASTRANDO PESSOA TUTORA\n");
        System.out.println("Digite o ID: ");
        String id = sc.nextLine();
        System.out.println("Digite o Nome: ");
        String nome = sc.nextLine();
        System.out.println("Digite o endereço: ");
        String endereco = sc.nextLine();

        String regexTelefone = "^\\d{11}$";
        Pattern patternTel = Pattern.compile(regexTelefone);
        String telefone;
        while (true) {
            System.out.println("Digite o telefone (11 dígitos, apenas números): ");
            telefone = sc.nextLine();
            Matcher matcherTel = patternTel.matcher(telefone);
            if (matcherTel.matches()) {
                break;
            } else {
                System.out.println("Telefone inválido! Digite apenas 11 números.");
            }
        }
        String regexEmail = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(regexEmail);

        String email;
        while (true) {
            System.out.println("Digite o e-mail: ");
            email = sc.nextLine();

            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                break;
            } else {
                System.out.println("E-mail inválido! Digite novamente.");
            }
        }

        PessoaTutora pessoaTutora = new PessoaTutora(id, nome, endereco, telefone, email);
        System.out.println("Tutor cadastrado com sucesso! ");
    }
}
