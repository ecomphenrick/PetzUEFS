package View.MenusCadastro;

import Controller.PersistenciaTutor;
import Model.Animal;
import Model.PessoaTutora;
import Model.SetorResponsavel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CadastroTutor {
    private static final String CAMINHO_ARQUIVO = "tutor.json";
    private static final String CAMINHO_ARQUIVO2 = "setor.json";

    public void CadastroTutor() {
        Scanner sc = new Scanner(System.in);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<PessoaTutora> listaTutores = new ArrayList<>();
        List<SetorResponsavel> listaSetores = new ArrayList<>();

        try (FileReader reader = new FileReader(CAMINHO_ARQUIVO)) {
            Type tipoLista = new TypeToken<List<PessoaTutora>>() {}.getType();
            listaTutores = gson.fromJson(reader, tipoLista);
            if (listaTutores == null) listaTutores = new ArrayList<>();
        } catch (Exception e) {
            System.out.println("❌ Erro ao ler o arquivo: " + e.getMessage());
            return;
        }

        try (FileReader reader = new FileReader(CAMINHO_ARQUIVO2)) {
            Type tipoLista2 = new TypeToken<List<SetorResponsavel>>() {}.getType();
            listaSetores = gson.fromJson(reader, tipoLista2);
            if (listaSetores == null) listaSetores = new ArrayList<>();
        } catch (Exception e) {
            System.out.println("❌ Erro ao ler o arquivo: " + e.getMessage());
            return;
        }

        if (listaSetores == null || listaSetores.isEmpty()) {
            System.out.println("⚠️ Nenhum setor cadastrado! Cadastre um setor primeiro.");
            return;
        }

        System.out.println("CADASTRANDO PESSOA TUTORA\n");
        int indiceEscolhido = -1;
        boolean validateID = false;
        String iD;

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

        do {
            System.out.println("Digite o ID (Ex: T001): ");
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
            if (matcherTel.matches()) break;
            System.out.println("Telefone inválido! Digite apenas 11 números.");
        }

        String regexEmail = "^(?!.*\\.\\.)[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}(\\.[A-Za-z]{2})?$";
        Pattern pattern = Pattern.compile(regexEmail);
        String email;
        while (true) {
            System.out.println("Digite o e-mail: ");
            email = sc.nextLine();
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) break;
            System.out.println("E-mail inválido! Digite novamente.");
        }

        List<Animal> animals = new ArrayList<>();

        PessoaTutora pessoaTutora = new PessoaTutora(iD, nome, endereco, telefone, email, animals);
        listaSetores.get(indiceEscolhido).getPessoaTutoras().add(pessoaTutora);

        try (FileWriter writer = new FileWriter(CAMINHO_ARQUIVO2)) {
            gson.toJson(listaSetores, writer);
            System.out.println("✅ Setor atualizado com novo tutor!");
        } catch (Exception e) {
            System.out.println("❌ Erro ao atualizar setor.json: " + e.getMessage());
        }

        PersistenciaTutor persistenciaTutor = new PersistenciaTutor();
        persistenciaTutor.salvarTutor(pessoaTutora);
        System.out.println("Tutor cadastrado com sucesso!");
    }
}

