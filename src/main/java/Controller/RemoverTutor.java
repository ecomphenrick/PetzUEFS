package Controller;

import Model.PessoaTutora;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class RemoverTutor {
    private static final String CAMINHO_ARQUIVO = "tutor.json";
    public void excluirTutor(String nomeExcluir) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        List<PessoaTutora> listaTutores = new ArrayList<>();


        try (FileReader reader = new FileReader(CAMINHO_ARQUIVO)) {
            Type tipoLista = new TypeToken<List<PessoaTutora>>() {}.getType();
            listaTutores = gson.fromJson(reader, tipoLista);
            if (listaTutores == null) listaTutores = new ArrayList<>();
        } catch (Exception e) {
            System.out.println("❌ Erro ao ler o arquivo: " + e.getMessage());
            return;
        }

        boolean removido = listaTutores.removeIf(a -> a.getiD().equalsIgnoreCase(nomeExcluir));

        if (removido) {
            try (FileWriter writer = new FileWriter(CAMINHO_ARQUIVO)) {
                gson.toJson(listaTutores, writer);
                System.out.println("🗑️ Tutor removido com sucesso!");
            } catch (Exception e) {
                System.out.println("❌ Erro ao salvar o arquivo: " + e.getMessage());
            }
        } else {
            System.out.println("⚠️ Nenhum tutor foi encontrado para remoção.");
        }
    }
}
