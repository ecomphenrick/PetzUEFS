package Controller;

import Model.Animal;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaAnimal {

    private static final String CAMINHO_ARQUIVO = "animal.json";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

    public void salvarAnimal(Animal novoAnimal) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new JsonSerializer<LocalDate>() {
                    @Override
                    public JsonElement serialize(LocalDate src, Type typeOfSrc, JsonSerializationContext context) {
                        return new JsonPrimitive(src.format(FORMATTER));
                    }
                })
                .registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
                    @Override
                    public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        return LocalDate.parse(json.getAsString(), FORMATTER);
                    }
                })
                .create();

        File arquivo = new File(CAMINHO_ARQUIVO);
        List<Animal> listaAnimais = new ArrayList<>();

        // 🔹 Ler animais existentes
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

        // 🔹 Adicionar novo animal
        listaAnimais.add(novoAnimal);

        // 🔹 Salvar lista atualizada
        try (FileWriter writer = new FileWriter(arquivo)) {
            gson.toJson(listaAnimais, writer);
            System.out.println("✅ Animal salvo com sucesso!");
        } catch (Exception e) {
            System.out.println("❌ Erro ao salvar arquivo: " + e.getMessage());
        }

        System.out.println("📁 Caminho do arquivo: " + arquivo.getAbsolutePath());
    }
}



