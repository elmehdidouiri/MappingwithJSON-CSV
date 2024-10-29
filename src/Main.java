import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        long startTime = System.nanoTime();

        genreCalc("D:/IntelijTPs/TpEMSI/src/actors.csv");
        groupByGuitarType("D://IntelijTPs//TpEMSI//src//guitarists.csv");

        List<Acteur> acteurs = new ArrayList<>();
        List<Guitariste> guitaristes = new ArrayList<>();
        acteurs.add(new Acteur(1, "Ahmed", 2003, "Dramatique", 25));
        acteurs.add(new Acteur(2, "Mohamed", 1997, "Comédie", 15));
        guitaristes.add(new Guitariste(3, "Hassan", 2001, "Rock", 10));
        guitaristes.add(new Guitariste(4, "Mehdi", 1998, "Jazz", 8));

        List<Artiste> artistesRetenus = lesArtiste(acteurs, guitaristes);
        for (Artiste artiste : artistesRetenus) {
            if (artiste instanceof Acteur) {
                Acteur acteur = (Acteur) artiste;
                acteur.nbrFilmes -= 2;
            } else if (artiste instanceof Guitariste) {
                Guitariste guitariste = (Guitariste) artiste;
                guitariste.nbrMorceaux += 3;
            }
        }

        for (Artiste artiste : artistesRetenus) {
            System.out.println("Les artistes après l'opération sont : " + artiste);
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("Temps d'exécution : " + duration + " nanosecondes.");
    }

    public static List<Artiste> lesArtiste(List<Acteur> A1, List<Guitariste> G1) {
        List<Artiste> listeRetenue = new ArrayList<>();
        for (Acteur a : A1) {
            if (a.nbrFilmes > 20) {
                listeRetenue.add(a);
            }
        }
        for (Guitariste g : G1) {
            if (g.anneDebut < 1998) {
                listeRetenue.add(g);
            }
        }
        return listeRetenue;
    }

    public static void genreCalc(String csvFile) {
        Map<String, Long> genreCount = new HashMap<>();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(csvFile))) {
            genreCount = br.lines()
                    .skip(1)
                    .map(line -> line.split(",")[3])
                    .collect(Collectors.groupingBy(genre -> genre, Collectors.counting()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Écriture des résultats dans un fichier JSON
        JSONObject json = new JSONObject(genreCount);
        try (FileWriter file = new FileWriter("actors_genre_count.json")) {
            file.write(json.toString(4)); // Indente avec 4 espaces pour une meilleure lisibilité
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void groupByGuitarType(String csvFile) {
        Map<String, List<String>> guitarTypeMap = new HashMap<>();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(csvFile))) {
            guitarTypeMap = br.lines()
                    .skip(1)
                    .map(line -> line.split(","))
                    .collect(Collectors.groupingBy(line -> line[3],
                            Collectors.mapping(line -> line[1], Collectors.toList())));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Écriture des résultats dans des fichiers JSON, un par type de guitare
        guitarTypeMap.forEach((guitarType, guitarists) -> {
            JSONArray jsonArray = new JSONArray(guitarists);
            try (FileWriter file = new FileWriter("guitarists_" + guitarType + ".json")) {
                file.write(jsonArray.toString(4)); // Indente avec 4 espaces pour une meilleure lisibilité
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}