package Controller;

import Model.SetorResponsavel;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaSetor {

    private static final String CAMINHO_ARQUIVO = "setor.json";

    public void salvarSetor(SetorResponsavel novoSetor) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        File arquivo = new File(CAMINHO_ARQUIVO);
        List<SetorResponsavel> listaSetores = new ArrayList<>();


        if (arquivo.exists()) {
            try (FileReader reader = new FileReader(arquivo)) {
                Type tipoLista = new TypeToken<List<SetorResponsavel>>() {}.getType();
                listaSetores = gson.fromJson(reader, tipoLista);
                if (listaSetores == null) {
                    listaSetores = new ArrayList<>();
                }
            } catch (Exception e) {
                System.out.println("⚠️ Erro ao ler o arquivo existente: " + e.getMessage());
            }
        }

        listaSetores.add(novoSetor);

        try (FileWriter writer = new FileWriter(arquivo)) {
            gson.toJson(listaSetores, writer);
            System.out.println("✅ Setor salvo com sucesso!");
        } catch (Exception e) {
            System.out.println("❌ Erro ao salvar arquivo: " + e.getMessage());
        }

    }
}
