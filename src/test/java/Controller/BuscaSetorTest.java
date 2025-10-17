package Controller;

import Model.Animal;
import Model.PessoaTutora;
import Model.SetorResponsavel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de teste para {@link BuscaSetor}.
 * Testa a funcionalidade de busca de setores pelo nome em um arquivo JSON.
 *
 * @author Henrick Ferreira
 */
class BuscaSetorTest {

    private static final String CAMINHO_ARQUIVO = "setor.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private PessoaTutora tutor;
    private Animal animal;
    private SetorResponsavel setor;

    @BeforeEach
    void setUp() {
        // Criando tutor
        tutor = new PessoaTutora(
                "P001",
                "Maria Silva",
                "Rua A, 123",
                "75992658825",
                "maria@gmail.com",
                List.of("Pitoco")
        );

        // Criando animal
        animal = new Animal(
                "A001",
                "Rex",
                "Cão",
                "Labrador",
                "2020-10-14",
                "M",
                "Disponível para adoção",
                "Biblioteca",
                List.of("Maria Silva")
        );

        // Criando setor
        setor = new SetorResponsavel(
                "S001",
                "Biblioteca",
                "Rua Principal, 100",
                List.of(tutor),
                List.of(animal)
        );

        // Salvando em JSON
        List<SetorResponsavel> setores = new ArrayList<>();
        setores.add(setor);

        try (FileWriter writer = new FileWriter(CAMINHO_ARQUIVO)) {
            gson.toJson(setores, writer);
        } catch (IOException e) {
            fail("Erro ao criar arquivo de teste: " + e.getMessage());
        }
    }

    @Test
    void testBuscaSetorExistente() {
        BuscaSetor busca = new BuscaSetor();
        List<SetorResponsavel> resultado = busca.buscaSetor("Biblioteca");

        assertFalse(resultado.isEmpty());
        assertEquals("Biblioteca", resultado.get(0).getNome());
        assertEquals(1, resultado.get(0).getPessoaTutoras().size());
        assertEquals(1, resultado.get(0).getAnimais().size());
    }

    @Test
    void testBuscaSetorInexistente() {
        BuscaSetor busca = new BuscaSetor();
        List<SetorResponsavel> resultado = busca.buscaSetor("Veterinário");

        assertTrue(resultado.isEmpty());
    }

    @Test
    void testArquivoInexistente() {
        // Apaga o arquivo para simular erro
        java.io.File arquivo = new java.io.File(CAMINHO_ARQUIVO);
        if (arquivo.exists()) {
            arquivo.delete();
        }

        BuscaSetor busca = new BuscaSetor();
        List<SetorResponsavel> resultado = busca.buscaSetor("Biblioteca");

        assertTrue(resultado.isEmpty());
    }
}
