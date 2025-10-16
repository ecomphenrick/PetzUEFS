package Controller;

import Model.Animal;
import Model.SetorResponsavel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class RemoverSetor {
    private static final String CAMINHO_ARQUIVO = "setor.json";
    public void excluirSetor(String iDExcluir) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        List<SetorResponsavel> listaSetores = new ArrayList<>();


        try (FileReader reader = new FileReader(CAMINHO_ARQUIVO)) {
            Type tipoLista = new TypeToken<List<SetorResponsavel>>() {}.getType();
            listaSetores = gson.fromJson(reader, tipoLista);
            if (listaSetores == null) listaSetores = new ArrayList<>();
        } catch (Exception e) {
            System.out.println("❌ Erro ao ler o arquivo: " + e.getMessage());
            return;
        }


        boolean removido = listaSetores.removeIf(a -> a.getiD().equalsIgnoreCase(iDExcluir));

        if (removido) {
            try (FileWriter writer = new FileWriter(CAMINHO_ARQUIVO)) {
                gson.toJson(listaSetores, writer);
                System.out.println("🗑️ Setor removido com sucesso!");
            } catch (Exception e) {
                System.out.println("❌ Erro ao salvar o arquivo: " + e.getMessage());
            }
        } else {
            System.out.println("⚠️ Nenhum setor foi encontrado para remoção.");
        }
    }
}
