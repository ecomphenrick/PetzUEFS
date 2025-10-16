package Controller;

import Model.Animal;
import Model.PessoaTutora;
import Model.SetorResponsavel;
import View.MenusCadastro.CadastroTutor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class AtualizarTutor {
    private static final String CAMINHO_TUTOR = "tutor.json";
    private static final String CAMINHO_SETOR = "setor.json";
    private static final String CAMINHO_ANIMAL = "animal.json";

    public void atualizarTutor(String nomeBuscado) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Scanner sc = new Scanner(System.in);

        List<PessoaTutora> listaTutores = lerListaTutores(gson);
        if (listaTutores.isEmpty()) return;

        PessoaTutora tutorEncontrado = null;
        for (PessoaTutora t : listaTutores) {
            if (t.getNome().equalsIgnoreCase(nomeBuscado)) {
                tutorEncontrado = t;
                break;
            }
        }

        if (tutorEncontrado == null) {
            System.out.println("Nenhum tutor encontrado com esse nome!");
            return;
        }

        String nomeAntigo = tutorEncontrado.getNome();

        System.out.println("Tutor encontrado: " + nomeAntigo);
        System.out.println("Escolha a informação que deseja atualizar:");
        System.out.println("1 - Nome");
        System.out.println("2 - Endereço");
        System.out.println("3 - E-mail");
        System.out.println("4 - Telefone");

        int opcao = sc.nextInt();
        sc.nextLine();

        switch (opcao) {
            case 1:
                System.out.print("Novo nome: ");
                tutorEncontrado.setNome(sc.nextLine());
                break;
            case 2:
                System.out.print("Novo endereço: ");
                tutorEncontrado.setEndereco(sc.nextLine());
                break;
            case 3:
                String regexEmail = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
                Pattern patternEmail = Pattern.compile(regexEmail);
                String email;
                while (true) {
                    System.out.println("Digite o e-mail: ");
                    email = sc.nextLine();
                    Matcher matcherEmail = patternEmail.matcher(email);
                    if (matcherEmail.matches()) break;
                    System.out.println("E-mail inválido! Digite novamente.");
                }
                tutorEncontrado.setEmail(email);
                break;
            case 4:
                String regexTelefone = "\\d{11}";
                Pattern patternTel = Pattern.compile(regexTelefone);
                String telefone;
                while (true) {
                    System.out.println("Digite o telefone (11 dígitos, apenas números): ");
                    telefone = sc.nextLine();
                    Matcher matcherTel = patternTel.matcher(telefone);
                    if (matcherTel.matches()) break;
                    System.out.println("Telefone inválido! Digite apenas 11 números.");
                }
                tutorEncontrado.setTelefone(telefone);
                break;
            default:
                return;
        }

        salvarListaTutores(listaTutores, gson);
        atualizarTutoresNosSetores(nomeAntigo, tutorEncontrado.getNome(), gson);
        atualizarTutoresNosAnimais(nomeAntigo, tutorEncontrado.getNome(), gson);

        System.out.println("Tutor atualizado com sucesso!");
    }

    private List<PessoaTutora> lerListaTutores(Gson gson) {
        List<PessoaTutora> lista = new ArrayList<>();
        try (FileReader reader = new FileReader(CAMINHO_TUTOR)) {
            Type tipoLista = new TypeToken<List<PessoaTutora>>() {}.getType();
            lista = gson.fromJson(reader, tipoLista);
            if (lista == null) lista = new ArrayList<>();
        } catch (Exception e) {}
        return lista;
    }

    private void salvarListaTutores(List<PessoaTutora> lista, Gson gson) {
        try (FileWriter writer = new FileWriter(CAMINHO_TUTOR)) {
            gson.toJson(lista, writer);
        } catch (Exception e) {}
    }

    private void atualizarTutoresNosSetores(String nomeAntigo, String nomeNovo, Gson gson) {
        List<SetorResponsavel> setores = new ArrayList<>();
        try (FileReader reader = new FileReader(CAMINHO_SETOR)) {
            Type tipoLista = new TypeToken<List<SetorResponsavel>>() {}.getType();
            setores = gson.fromJson(reader, tipoLista);
            if (setores == null) setores = new ArrayList<>();
        } catch (Exception e) { return; }

        for (SetorResponsavel s : setores) {
            if (s.getPessoaTutoras() != null) {
                for (int i = 0; i < s.getPessoaTutoras().size(); i++) {
                    if (s.getPessoaTutoras().get(i).getNome().equals(nomeAntigo)) {
                        s.getPessoaTutoras().get(i).setNome(nomeNovo);
                    }
                }
            }
        }

        try (FileWriter writer = new FileWriter(CAMINHO_SETOR)) {
            gson.toJson(setores, writer);
        } catch (Exception e) {}
    }

    private void atualizarTutoresNosAnimais(String nomeAntigo, String nomeNovo, Gson gson) {
        List<Animal> animais = new ArrayList<>();
        try (FileReader reader = new FileReader(CAMINHO_ANIMAL)) {
            Type tipoLista = new TypeToken<List<Animal>>() {}.getType();
            animais = gson.fromJson(reader, tipoLista);
            if (animais == null) animais = new ArrayList<>();
        } catch (Exception e) { return; }

        for (Animal a : animais) {
            List<String> nomesTutores = a.getTutores();
            if (nomesTutores != null) {
                for (int i = 0; i < nomesTutores.size(); i++) {
                    if (nomesTutores.get(i).equals(nomeAntigo)) {
                        nomesTutores.set(i, nomeNovo);
                    }
                }
            }
        }

        try (FileWriter writer = new FileWriter(CAMINHO_ANIMAL)) {
            gson.toJson(animais, writer);
        } catch (Exception e) {}
    }
}

