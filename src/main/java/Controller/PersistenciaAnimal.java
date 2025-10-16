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

/**
 * Classe responsável pela persistência de dados dos animais.
 * <p>
 * Permite salvar novos animais em um arquivo JSON, mantendo os registros existentes.
 * </p>
 */
public class PersistenciaAnimal {

    private static final String CAMINHO_ARQUIVO = "animal.json";

    /**
     * Salva um novo animal no arquivo JSON.
     * <p>
     * Se o arquivo já existir, os animais existentes serão mantidos e o novo será adicionado ao final.
     * Caso o arquivo não exista, será criado um novo.
     * </p>
     *
     * @param novoAnimal Objeto Animal a ser salvo.
     */
    public void salvarAnimal(Animal novoAnimal) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        File arquivo = new File(CAMINHO_ARQUIVO);
        List<Animal> listaAnimais = new ArrayList<>();

        // Ler arquivo existente, se houver
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

        // Adicionar o novo animal à lista
        listaAnimais.add(novoAnimal);

        // Salvar a lista atualizada no arquivo
        try (FileWriter writer = new FileWriter(arquivo)) {
            gson.toJson(listaAnimais, writer);
            System.out.println("✅ Animal salvo com sucesso!");
        } catch (Exception e) {
            System.out.println("❌ Erro ao salvar arquivo: " + e.getMessage());
        }
    }
}




