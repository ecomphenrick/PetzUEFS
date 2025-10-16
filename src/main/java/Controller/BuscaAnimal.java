package Controller;

import Model.Animal;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por realizar buscas de animais no sistema.
 * <p>
 * Permite buscar animais pelo nome no arquivo "animal.json" e retorna
 * uma lista de objetos {@link Animal} que correspondam ao critério de busca.
 * </p>
 */
public class BuscaAnimal {

    /** Caminho do arquivo JSON que armazena os animais */
    private static final String CAMINHO_ARQUIVO = "animal.json";

    /**
     * Busca todos os animais que tenham o nome informado.
     *
     * @param nome Nome do animal a ser buscado (não diferencia maiúsculas de minúsculas)
     * @return Lista de animais encontrados com o nome informado.
     *         Retorna uma lista vazia caso nenhum animal seja encontrado ou ocorra um erro na leitura do arquivo.
     */
    public List<Animal> buscaAnimais(String nome) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        List<Animal> listaAnimais = new ArrayList<>();

        try (FileReader reader = new FileReader(CAMINHO_ARQUIVO)) {
            Type tipoLista = new TypeToken<List<Animal>>() {}.getType();
            listaAnimais = gson.fromJson(reader, tipoLista);
            if (listaAnimais == null) {
                listaAnimais = new ArrayList<>();
            }
        } catch (Exception e) {
            System.out.println("❌ Erro ao ler o arquivo: " + e.getMessage());
            return new ArrayList<>();
        }

        List<Animal> encontrados = new ArrayList<>();
        for (Animal animal : listaAnimais) {
            if (animal.getNome().equalsIgnoreCase(nome)) {
                encontrados.add(animal);
            }
        }

        return encontrados;
    }
}
