package EJ6;

import com.google.gson.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArchCliente {
    private String nomArch; 
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public ArchCliente(String nomArch) {
        this.nomArch = nomArch;
    }
    
    public void guardar(List<Cliente> clientes) {
        try (Writer writer = Files.newBufferedWriter(Paths.get(nomArch))) {
            gson.toJson(clientes, writer);
            System.out.println("Guardado exitoso en " + nomArch);
        } catch (IOException e) {
            System.err.println("Error al guardar " + nomArch + ": " + e.getMessage());
        }
    }

    public ArrayList<Cliente> leer() {
        try (Reader reader = Files.newBufferedReader(Paths.get(nomArch))) {
            Cliente[] array = gson.fromJson(reader, Cliente[].class);
            return array != null ? new ArrayList<>(Arrays.asList(array)) : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        } catch (JsonSyntaxException e) {
            System.err.println("Error al parsear JSON en " + nomArch + ": " + e.getMessage());
            return new ArrayList<>();
        }
    }
}