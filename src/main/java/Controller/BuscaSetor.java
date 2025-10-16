package Controller;

import Model.SetorResponsavel;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por realizar buscas de setores no sistema.
 * <p>
 * Permite buscar setores pelo nome no arquivo "setor.json" e retorna
 * uma lista de objetos {@link SetorResponsavel} que correspondam ao critério de busca.
 * </p>
 */
public class BuscaSetor {

    /** Caminho do arquivo JSON que armazena os setores */
    private static final String CAMINHO_ARQUIVO = "setor.json";

    /**
     * Busca todos os setores que tenham o nome informado.
     *
     * @param nome Nome do setor a ser buscado (não diferencia maiúsculas de minúsculas)
     * @return Lista de setores encontrados com o nome informado.
     *         Retorna uma lista vazia caso nenhum setor seja encontrado ou ocorra um erro na leitura do arquivo.
     */
    public List<SetorResponsavel> buscaSetor(String nome) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        List<SetorResponsavel> listaSetores = new ArrayList<>();

        try (FileReader reader = new FileReader(CAMINHO_ARQUIVO)) {
            Type tipoLista = new TypeToken<List<SetorResponsavel>>() {}.getType();
            listaSetores = gson.fromJson(reader, tipoLista);
            if (listaSetores == null) {
                listaSetores = new ArrayList<>();
            }
        } catch (Exception e) {
            System.out.println("❌ Erro ao ler o arquivo: " + e.getMessage());
            return new ArrayList<>();
        }

        List<SetorResponsavel> encontrados = new ArrayList<>();
        for (SetorResponsavel setor : listaSetores) {
            if (setor.getNome().equalsIgnoreCase(nome)) {
                encontrados.add(setor);
            }
        }

        return encontrados;
    }
}

