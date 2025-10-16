package Controller;

import Model.SetorResponsavel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por remover setores do sistema.
 * <p>
 * Permite excluir um setor do arquivo "setor.json" apenas se
 * não houver animais ou tutores associados.
 * </p>
 */
public class RemoverSetor {

    private static final String CAMINHO_ARQUIVO = "setor.json";

    /**
     * Exclui um setor pelo seu ID.
     * <p>
     * O setor só será removido se não houver animais ou tutores associados.
     * Caso contrário, uma mensagem de aviso será exibida.
     * </p>
     *
     * @param iDExcluir ID do setor a ser removido.
     */
    public void excluirSetor(String iDExcluir) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<SetorResponsavel> listaSetores = new ArrayList<>();

        // Ler lista de setores do arquivo
        try (FileReader reader = new FileReader(CAMINHO_ARQUIVO)) {
            Type tipoLista = new TypeToken<List<SetorResponsavel>>() {}.getType();
            listaSetores = gson.fromJson(reader, tipoLista);
            if (listaSetores == null) listaSetores = new ArrayList<>();
        } catch (Exception e) {
            System.out.println("❌ Erro ao ler o arquivo: " + e.getMessage());
            return;
        }

        // Encontrar setor a ser removido
        SetorResponsavel setorRemover = null;
        for (SetorResponsavel s : listaSetores) {
            if (s.getiD().equalsIgnoreCase(iDExcluir)) {
                setorRemover = s;
                break;
            }
        }

        if (setorRemover == null) {
            System.out.println("⚠️ Nenhum setor com esse ID foi encontrado para remoção.");
            return;
        }

        // Verificar se o setor possui animais ou tutores
        boolean possuiAnimais = sTemConteudo(setorRemover.getAnimais());
        boolean possuiTutores = sTemConteudo(setorRemover.getPessoaTutoras());

        if (possuiAnimais || possuiTutores) {
            System.out.println("⚠️ Este setor não pode ser removido, pois ainda possui animais e/ou tutores cadastrados.");
            System.out.println("➡️ Remova manualmente todos os animais e tutores associados antes de tentar novamente.");
            return;
        }

        // Remover setor
        listaSetores.remove(setorRemover);

        // Salvar arquivo atualizado
        try (FileWriter writer = new FileWriter(CAMINHO_ARQUIVO)) {
            gson.toJson(listaSetores, writer);
            System.out.println("🗑️ Setor removido com sucesso!");
        } catch (Exception e) {
            System.out.println("❌ Erro ao salvar o arquivo: " + e.getMessage());
        }
    }

    /**
     * Verifica se uma lista possui elementos.
     *
     * @param lista Lista a ser verificada.
     * @return true se a lista não for nula e contiver elementos, false caso contrário.
     */
    private boolean sTemConteudo(List<?> lista) {
        return lista != null && !lista.isEmpty();
    }
}


