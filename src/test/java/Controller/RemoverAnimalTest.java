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

class RemoverAnimalTest {

    private static final String CAMINHO_ANIMAL = "animal.json";
    private static final String CAMINHO_TUTOR = "tutor.json";
    private static final String CAMINHO_SETOR = "setor.json";

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private RemoverAnimal remover;

    private Animal animal;
    private PessoaTutora tutor;
    private SetorResponsavel setor;

    @BeforeEach
    void setUp() throws Exception {
        // Limpar arquivos antes do teste
        new File(CAMINHO_ANIMAL).delete();
        new File(CAMINHO_TUTOR).delete();
        new File(CAMINHO_SETOR).delete();

        remover = new RemoverAnimal();

        // Criar dados
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

        tutor = new PessoaTutora(
                "P001",
                "Maria Silva",
                "Rua A, 123",
                "75992658825",
                "maria@gmail.com",
                List.of("Rex")
        );

        setor = new SetorResponsavel(
                "S001",
                "Biblioteca",
                "UEFS",
                List.of(tutor),
                List.of(animal)
        );

        // Salvar nos arquivos JSON
        try (FileWriter writer = new FileWriter(CAMINHO_ANIMAL)) {
            gson.toJson(List.of(animal), writer);
        }

        try (FileWriter writer = new FileWriter(CAMINHO_TUTOR)) {
            gson.toJson(List.of(tutor), writer);
        }

        try (FileWriter writer = new FileWriter(CAMINHO_SETOR)) {
            gson.toJson(List.of(setor), writer);
        }
    }

    @Test
    void testExcluirAnimal() throws Exception {
        // Executar remoção
        remover.excluirAnimalPorNome("A001");

        // Verifica que o animal foi removido do animal.json
        Type tipoAnimal = new TypeToken<List<Animal>>() {}.getType();
        List<Animal> animais;
        try (FileReader reader = new FileReader(CAMINHO_ANIMAL)) {
            animais = gson.fromJson(reader, tipoAnimal);
        }
        assertTrue(animais.isEmpty(), "Animal não foi removido do animal.json");

        // Verifica que o animal foi removido do tutor.json
        Type tipoTutor = new TypeToken<List<PessoaTutora>>() {}.getType();
        List<PessoaTutora> tutores;
        try (FileReader reader = new FileReader(CAMINHO_TUTOR)) {
            tutores = gson.fromJson(reader, tipoTutor);
        }
        assertTrue(tutores.get(0).getAnimals().isEmpty(), "Animal não foi removido do tutor.json");

        // Verifica que o animal foi removido do setor.json
        Type tipoSetor = new TypeToken<List<SetorResponsavel>>() {}.getType();
        List<SetorResponsavel> setores;
        try (FileReader reader = new FileReader(CAMINHO_SETOR)) {
            setores = gson.fromJson(reader, tipoSetor);
        }
        assertTrue(setores.get(0).getAnimais().isEmpty(), "Animal não foi removido do setor.json");
    }
}
