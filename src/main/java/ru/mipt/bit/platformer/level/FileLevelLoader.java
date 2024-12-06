package ru.mipt.bit.platformer.level;

import com.badlogic.gdx.math.GridPoint2;
import lombok.Getter;
import lombok.Setter;
import ru.mipt.bit.platformer.Model;
import ru.mipt.bit.platformer.Obstacle;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
public class FileLevelLoader implements LevelLoader {
//    private static final double TREE_PROBABILITY = 0.3;
//    private final GridPoint2 upperBorder;
//    private final Set<Model> models = new HashSet<>();
//    private final Set<Obstacle> obstacles = new HashSet<>();
//    private final Set<GridPoint2> vacantCoords;

    private PreProcessedLevelFromFile futureNewLevel;
    private final String filePath;

//    public FileLevelLouder(GridPoint2 upperBorder) {
//        this.upperBorder = upperBorder;
//        this.vacantCoords = this.generateCoords(upperBorder);
//    }

    public FileLevelLoader() {
        this.filePath = null;
        futureNewLevel = new PreProcessedLevelFromFile();
    }

    @Override
    public void load(String filePath) {
        try (
                InputStream stream = getStream(filePath);
                BufferedReader br = new BufferedReader(new InputStreamReader(stream)
                )) {
//            Level level = new Level();
            int width = getWidth();
            int height = getHeight();
            fillModelsAndObstacles(br, height);
//            return level;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File with description of level not found!");
        } catch (IOException e) {
            throw new RuntimeException("Error while parsing file with description of level:" + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private InputStream getStream(String filePath) {
        return Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(filePath));
    }

    private void fillModelsAndObstacles(BufferedReader br, int height) throws Exception {
        String line;
        int currentLineNumber = 0;
        while ((line = br.readLine()) != null) {
            ++currentLineNumber;
            processLine(line, currentLineNumber, height);
        }
    }

    private void processLine(String line, int rowNumber, int maxRow) throws Exception {
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            switch (c) {
                case 'T':
                    futureNewLevel.getObstacles().add(new Obstacle(new GridPoint2(i, maxRow - rowNumber)));
                    break;
                case 'X':
                    futureNewLevel.getModels().add(new Model(new GridPoint2(i, maxRow - rowNumber)));
                    break;
                case '_':
                    break;
                default:
                    throw new RuntimeException("Not supported character in level file!");
            }
        }
    }

    private int getWidth() throws Exception {
        String firstLine = Files.lines(Paths.get(filePath), Charset.defaultCharset()).findFirst().orElse("");
        String[] parts = firstLine.split("\\s+");
        if (parts.length > 0) {
            return Integer.parseInt(parts[0]);
        } else {
            throw new RuntimeException("Invalid file format!");
        }
    }

    public int getHeight() throws IOException {
        return (int) Files.lines(Paths.get(filePath), Charset.defaultCharset()).count();
    }

    private Set<GridPoint2> generateCoords(GridPoint2 upperBorder) {
        Set<GridPoint2> coords = new HashSet<>();
        for (int i = 0; i < upperBorder.x; i++) {
            for (int j = 0; j < upperBorder.y; j++) {
                coords.add(new GridPoint2(i, j));
            }
        }
        return coords;
    }

//    public Level load() {
//        Random random = new Random();
//        addPlayers(random);
//        addObstacles(random);
//        return new Level(models, obstacles, upperBorder);
//    }

//    private void addObstacles(Random random) {
//        for (int i = 0; i < vacantCoords.size(); i++) {
//            if (random.nextDouble() < TREE_PROBABILITY) {
//                obstacles.add(new Obstacle(getRandomCoordinates(random)));
//            }
//        }
//    }

//    private void addPlayers(Random random) {
//        models.add(new Model(getRandomCoordinates(random), PlayerTypes.PLAYER));
////        models.add(new Tank(getRandomCoordinates(random), PlayerTypes.SIMPLE_AI));
////        models.add(new Tank(getRandomCoordinates(random), PlayerTypes.SIMPLE_AI));
//    }

//    private GridPoint2 getRandomCoordinates(Random random) {
//        if (vacantCoords.isEmpty()) return null;
//
//        Iterator<GridPoint2> iterator = vacantCoords.iterator();
//        int randomIndex = random.nextInt(vacantCoords.size());
//        for (int i = 0; i < randomIndex; i++) {
//            iterator.next();
//        }
//        GridPoint2 randomCoords = iterator.next();
//        iterator.remove();
//        return randomCoords;
//    }

}
