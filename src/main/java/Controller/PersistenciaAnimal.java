package Controller;

import Model.Animal;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaAnimal {

    private static final String CAMINHO_ARQUIVO = "animal.json";

    public void salvarAnimal(Animal novoAnimal) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        File arquivo = new File(CAMINHO_ARQUIVO);
        List<Animal> listaAnimais = new ArrayList<>();

        if (arquivo.exists()) {
            try (FileReader reader = new FileReader(arquivo)) {
                Type tipoLista = new TypeToken<List<Animal>>() {}.getType();
                listaAnimais = gson.fromJson(reader, tipoLista);
                if (listaAnimais == null) {
                    listaAnimais = new ArrayList<>();
                }
            } catch (Exception e) {
                System.out.println("⚠️ Erro ao ler o arquivo existente: " + e.getMessage());
            }
        }

        listaAnimais.add(novoAnimal);

        try (FileWriter writer = new FileWriter(arquivo)) {
            gson.toJson(listaAnimais, writer);
            System.out.println("✅ Animal salvo com sucesso!");
        } catch (Exception e) {
            System.out.println("❌ Erro ao salvar arquivo: " + e.getMessage());
        }

        System.out.println("📁 Caminho do arquivo: " + arquivo.getAbsolutePath());
    }
}



