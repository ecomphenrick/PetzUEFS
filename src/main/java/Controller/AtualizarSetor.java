package Controller;

import Model.SetorResponsavel;
import Model.Animal;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AtualizarSetor {
    private static final String CAMINHO_SETOR = "setor.json";
    private static final String CAMINHO_ANIMAL = "animal.json";

    public void atualizarSetor(String nomeBuscado) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Scanner sc = new Scanner(System.in);

        List<SetorResponsavel> listaSetores = lerListaSetores(gson);
        if (listaSetores.isEmpty()) return;

        SetorResponsavel setorEncontrado = null;
        for (SetorResponsavel s : listaSetores) {
            if (s.getNome().equalsIgnoreCase(nomeBuscado)) {
                setorEncontrado = s;
                break;
            }
        }

        if (setorEncontrado == null) {
            System.out.println("Nenhum setor encontrado com esse nome!");
            return;
        }

        String nomeAntigo = setorEncontrado.getNome();

        System.out.println("Setor encontrado: " + nomeAntigo);
        System.out.println("Escolha a informação que deseja atualizar:");
        System.out.println("1 - Nome");
        System.out.println("2 - Endereço");

        int opcao = sc.nextInt();
        sc.nextLine();

        switch (opcao) {
            case 1:
                System.out.print("Novo nome: ");
                setorEncontrado.setNome(sc.nextLine());
                break;
            case 2:
                System.out.print("Novo endereço: ");
                setorEncontrado.setEndereco(sc.nextLine());
                break;
            default:
                return;
        }

        salvarListaSetores(listaSetores, gson);

        // Atualiza o nome do setor nos animais
        if (opcao == 1) {
            atualizarNomeSetorEmAnimais(nomeAntigo, setorEncontrado.getNome(), gson);
        }

        System.out.println("Setor atualizado com sucesso!");
    }

    private List<SetorResponsavel> lerListaSetores(Gson gson) {
        List<SetorResponsavel> lista = new ArrayList<>();
        try (FileReader reader = new FileReader(CAMINHO_SETOR)) {
            Type tipoLista = new TypeToken<List<SetorResponsavel>>() {}.getType();
            lista = gson.fromJson(reader, tipoLista);
            if (lista == null) lista = new ArrayList<>();
        } catch (Exception e) {}
        return lista;
    }

    private void salvarListaSetores(List<SetorResponsavel> lista, Gson gson) {
        try (FileWriter writer = new FileWriter(CAMINHO_SETOR)) {
            gson.toJson(lista, writer);
        } catch (Exception e) {}
    }

    private void atualizarNomeSetorEmAnimais(String nomeAntigo, String nomeNovo, Gson gson) {
        List<Animal> animais = new ArrayList<>();
        try (FileReader reader = new FileReader(CAMINHO_ANIMAL)) {
            Type tipoLista = new TypeToken<List<Animal>>() {}.getType();
            animais = gson.fromJson(reader, tipoLista);
            if (animais == null) animais = new ArrayList<>();
        } catch (Exception e) { return; }

        for (Animal a : animais) {
            if (a.getNomeSetor().equalsIgnoreCase(nomeAntigo)) {
                a.setNomeSetor(nomeNovo);
            }
        }

        try (FileWriter writer = new FileWriter(CAMINHO_ANIMAL)) {
            gson.toJson(animais, writer);
        } catch (Exception e) {}
    }
}
