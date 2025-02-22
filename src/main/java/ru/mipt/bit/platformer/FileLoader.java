package ru.mipt.bit.platformer;

import com.badlogic.gdx.math.GridPoint2;
import org.springframework.stereotype.Component;
import ru.mipt.bit.platformer.level_map.MapNode;
import ru.mipt.bit.platformer.level_map.NodeType;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

@Component
public class FileLoader {
    public LevelMap createMapFromFile(String filePath) throws FileNotFoundException {
        ArrayList<MapNode> obstaclesCoordinates = new ArrayList<>();
        int i = 0;
        int j = 0;
        MapNode player = new MapNode(new GridPoint2(0, 0), NodeType.TANK);
        Scanner scanner = new Scanner(new File(filePath));
        while (scanner.hasNextLine()) {
            for (Character symbol : scanner.nextLine().toCharArray()) {
                if (symbol == 'X') {
                    player.setCoordinates(new GridPoint2(i, j));
                }
                if (symbol == 'T') {
                    obstaclesCoordinates.add(new MapNode(new GridPoint2(i, j), NodeType.TREE));
                }
                ++i;
            }
            ++j;
            i = 0;
        }

        for (MapNode tree : obstaclesCoordinates) {
            tree.setCoordinates(new GridPoint2(tree.getCoordinates().x, j - 1 - tree.getCoordinates().y));
        }
        player.setCoordinates(new GridPoint2(player.getCoordinates().x, j - 1 - player.getCoordinates().y));
        scanner.close();
        return new LevelMap(obstaclesCoordinates, player);
    }
}
