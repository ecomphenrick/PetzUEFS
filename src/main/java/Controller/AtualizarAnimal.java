package Controller;

import Model.Animal;
import View.MenusCadastro.CadastroAnimal;
import View.MenusCadastro.CadastroTutor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AtualizarAnimal {
    private static final String CAMINHO_ARQUIVO = "animal.json";

    public void AtualizaAnimal (String nomeBuscado){
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
            return;
        }

        for (Animal a : listaAnimais){
            if (a.getNome().equalsIgnoreCase(nomeBuscado)){
                RemoverAnimal removerAnimal = new RemoverAnimal();
                removerAnimal.excluirAnimalPorNome(nomeBuscado);
                System.out.println("Cadastre-o novamente! ");
                CadastroAnimal cadastroAnimal = new CadastroAnimal();
                cadastroAnimal.CadastroAnimal();
                System.out.println("Animal atualizado com sucesso");

            }else {
                System.out.println("Nenhum animal encontrado com esse nome! ");
            }
        }
    }
}
