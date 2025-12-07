package EJ6;

import com.google.gson.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArchLibro {
    private String nomArch;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public ArchLibro(String nomArch) {
        this.nomArch = nomArch;
    }
    
    public void guardar(List<Libro> libros) {
        try (Writer writer = Files.newBufferedWriter(Paths.get(nomArch))) {
            gson.toJson(libros, writer);
            System.out.println("Guardado exitoso en " + nomArch);
        } catch (IOException e) {
            System.err.println("Error al guardar " + nomArch + ": " + e.getMessage());
        }
    }

    public ArrayList<Libro> leer() {
        try (Reader reader = Files.newBufferedReader(Paths.get(nomArch))) {
            Libro[] array = gson.fromJson(reader, Libro[].class);
            return array != null ? new ArrayList<>(Arrays.asList(array)) : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        } catch (JsonSyntaxException e) {
            System.err.println("Error al parsear JSON en " + nomArch + ": " + e.getMessage());
            return new ArrayList<>();
        }
    }
}