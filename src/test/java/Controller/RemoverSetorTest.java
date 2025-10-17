package Controller;

import Model.Animal;
import Model.PessoaTutora;
import Model.SetorResponsavel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RemoverSetorTest {

    private static final String CAMINHO_ARQUIVO = "setor.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private RemoverSetor remover;

    private SetorResponsavel setorComConteudo;
    private SetorResponsavel setorVazio;

    @BeforeEach
    void setUp() throws Exception {
        // Limpar arquivo antes do teste
        new File(CAMINHO_ARQUIVO).delete();
        remover = new RemoverSetor();

        // Setor com animais e tutores
        Animal animal = new Animal("A001", "Rex", "Cão", "Labrador",
                "2020-10-14", "M", "Disponível", "Modulo 1", List.of("Maria Silva"));
        PessoaTutora tutor = new PessoaTutora("P001", "Maria Silva",
                "Rua A, 123", "75992658825", "maria@gmail.com", List.of("Rex"));
        setorComConteudo = new SetorResponsavel("S001", "Modulo 1",
                "Rua Principal, 100", List.of(tutor), List.of(animal));

        // Setor vazio
        setorVazio = new SetorResponsavel("S002", "Vacinação",
                "Rua Secundária, 200", List.of(), List.of());

        // Salvar os setores no arquivo
        try (FileWriter writer = new FileWriter(CAMINHO_ARQUIVO)) {
            gson.toJson(List.of(setorComConteudo, setorVazio), writer);
        }
    }

    @Test
    void testNaoRemoveSetorComConteudo() throws Exception {
        remover.excluirSetor("S001");

        // Ler arquivo
        Type tipoLista = new TypeToken<List<SetorResponsavel>>() {}.getType();
        List<SetorResponsavel> setores;
        try (FileReader reader = new FileReader(CAMINHO_ARQUIVO)) {
            setores = gson.fromJson(reader, tipoLista);
        }

        // Setor com conteúdo não deve ser removido
        assertTrue(setores.stream().anyMatch(s -> s.getiD().equals("S001")),
                "Setor com conteúdo não deve ser removido");
    }

    @Test
    void testRemoveSetorVazio() throws Exception {
        remover.excluirSetor("S002");

        // Ler arquivo
        Type tipoLista = new TypeToken<List<SetorResponsavel>>() {}.getType();
        List<SetorResponsavel> setores;
        try (FileReader reader = new FileReader(CAMINHO_ARQUIVO)) {
            setores = gson.fromJson(reader, tipoLista);
        }

        // Setor vazio deve ser removido
        assertFalse(setores.stream().anyMatch(s -> s.getiD().equals("S002")),
                "Setor vazio deve ser removido");
    }
}
