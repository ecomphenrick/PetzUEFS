package Controller;

import Model.Animal;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class RemoverAnimal {
    private static final String CAMINHO_ARQUIVO = "animal.json";
    public void excluirAnimalPorNome(String nomeExcluir) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        List<Animal> listaAnimais = new ArrayList<>();


        try (FileReader reader = new FileReader(CAMINHO_ARQUIVO)) {
            Type tipoLista = new TypeToken<List<Animal>>() {}.getType();
            listaAnimais = gson.fromJson(reader, tipoLista);
            if (listaAnimais == null) listaAnimais = new ArrayList<>();
        } catch (Exception e) {
            System.out.println("❌ Erro ao ler o arquivo: " + e.getMessage());
            return;
        }

        boolean removido = listaAnimais.removeIf(a -> a.getNome().equalsIgnoreCase(nomeExcluir));

        if (removido) {
            try (FileWriter writer = new FileWriter(CAMINHO_ARQUIVO)) {
                gson.toJson(listaAnimais, writer);
                System.out.println("🗑️ Animal(es) com o nome '" + nomeExcluir + "' removido(s) com sucesso!");
            } catch (Exception e) {
                System.out.println("❌ Erro ao salvar o arquivo: " + e.getMessage());
            }
        } else {
            System.out.println("⚠️ Nenhum animal com o nome '" + nomeExcluir + "' foi encontrado para remoção.");
        }
    }
}
