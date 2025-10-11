package View.MenusCadastro;

import Controller.PersistenciaTutor;
import Model.Animal;
import Model.PessoaTutora;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CadastroTutor {
    private static final String CAMINHO_ARQUIVO = "tutor.json";

    public void CadastroTutor (){
        Scanner sc = new Scanner(System.in);
        System.out.println("CADASTRANDO PESSOA TUTORA\n");
        boolean validateID = false;
        String iD;
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        List<PessoaTutora> listaTutores = new ArrayList<>();

        try (FileReader reader = new FileReader(CAMINHO_ARQUIVO)) {
            Type tipoLista = new TypeToken<List<PessoaTutora>>() {}.getType();
            listaTutores = gson.fromJson(reader, tipoLista);
            if (listaTutores == null) {
                listaTutores = new ArrayList<>();
            }
        } catch (Exception e) {
            System.out.println("❌ Erro ao ler o arquivo: " + e.getMessage());
            return;
        }


        do {
            System.out.println("Digite o ID (Ex: A001): ");
            String regex = "^T\\d{3}$";
            iD = sc.nextLine();

            if (iD.matches(regex)) {
                validateID = true;
                for (PessoaTutora t : listaTutores) {
                    if (t.getiD().equalsIgnoreCase(iD)) {
                        System.out.println("Esse ID já existe! Digite outro.");
                        validateID = false;
                        break;
                    }
                }

            } else {
                System.out.println("Formato inválido, o formato correto é 'T' seguido de 3 números! Ex: T001 ou T002...");
                validateID = false;
            }

        } while (!validateID);
        System.out.println("ID OK!");

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

        PessoaTutora pessoaTutora = new PessoaTutora(iD, nome, endereco, telefone, email);
        PersistenciaTutor persistenciaTutor = new PersistenciaTutor();
        persistenciaTutor.salvarTutor(pessoaTutora);
        System.out.println("Tutor cadastrado com sucesso! ");
    }
}
