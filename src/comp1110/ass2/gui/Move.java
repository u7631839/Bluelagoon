package comp1110.ass2.gui;

import javafx.application.Application;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Move {
    public static Set<String> getOccupiedCoordinates(String stateString) {
        Set<String> occupied = new HashSet<>();
        Pattern playerPattern = Pattern.compile("p .*?;");
        Matcher playerMatcher = playerPattern.matcher(stateString);

        while (playerMatcher.find()) {
            String playerStatement = playerMatcher.group();
            Pattern occupiedPattern = Pattern.compile("(S|T) (\\d+,\\d+ ?)+");
            Matcher occupiedMatcher = occupiedPattern.matcher(playerStatement);
            while (occupiedMatcher.find()) {
                String[] coordinates = occupiedMatcher.group().substring(2).split(" ");
                for (String coordinate : coordinates) {
                    occupied.add(coordinate);
                }
            }
        }
        return occupied;
    }


    public static Set<String> getCurrentOccupiedCoordinates(String stateString) {
        Set<String> currentOccupied = new HashSet<>();
        String playerId = getCurrentPlayerId(stateString);


        Pattern playerPattern = Pattern.compile("p " + playerId + " .*?;");
        Matcher playerMatcher = playerPattern.matcher(stateString);
        if (playerMatcher.find()) {
            String playerStatement = playerMatcher.group();
            Pattern occupiedPattern = Pattern.compile("(S|T) (\\d+,\\d+ ?)+");
            Matcher occupiedMatcher = occupiedPattern.matcher(playerStatement);
            while (occupiedMatcher.find()) {
                String[] coordinates = occupiedMatcher.group().substring(2).split(" ");
                for (String coordinate : coordinates) {
                    currentOccupied.add(coordinate);
                }
            }
        }
        return currentOccupied;
    }

    public static String getCurrentPlayerId(String stateString) {
        String currentPlayerId = null;
        Pattern currentStatePattern = Pattern.compile("c .*?;");
        Matcher currentStateMatcher = currentStatePattern.matcher(stateString);

        if (currentStateMatcher.find()) {
            String[] parts = currentStateMatcher.group().split(" ");
            if (parts.length >= 2) {
                currentPlayerId = parts[1];
            }
        }
        return currentPlayerId;
    }

    public static Set<String> getLandCoordinates(String stateString) {
        Set<String> land = new HashSet<>();
        Pattern islandPattern = Pattern.compile("i .*?;");
        Matcher islandMatcher = islandPattern.matcher(stateString);

        while (islandMatcher.find()) {
            String islandStatement = islandMatcher.group().replace(";", "");
            String[] islandParts = islandStatement.split(" ");

            for (int i = 1; i < islandParts.length; i++) {
                if (isCoordinate(islandParts[i])) {
                    land.add(islandParts[i]);
                }
            }
        }
        return land;
    }

    public static boolean isCoordinate(String s) {
        return s.matches("\\d+,\\d+");
    }

    public static Set<String> getSeaCoordinates(String stateString, Set<String> land) {
        Set<String> sea = new HashSet<>();
        int boardHeight = getBoardHeight(stateString);

        for (int y = 0; y < boardHeight; y++) {
            int maxColumn = (y % 2 == 0) ? 11 : 12;
            for (int x = 0; x <= maxColumn; x++) {
                String coordinate = y + "," + x;
                if (!land.contains(coordinate)) {
                    sea.add(coordinate);
                }
            }
        }
        return sea;
    }

    public static int getBoardHeight(String stateString) {
        int boardHeight = -1;
        Pattern gameArrangementPattern = Pattern.compile("a .*?;");
        Matcher gameArrangementMatcher = gameArrangementPattern.matcher(stateString);

        if (gameArrangementMatcher.find()) {
            String[] parts = gameArrangementMatcher.group().split(" ");
            if (parts.length >= 2) {
                try {
                    boardHeight = Integer.parseInt(parts[1]);
                } catch (NumberFormatException e) {

                }
            }
        }
        return boardHeight;
    }

    public static int getNumPlayers(String stateString) {
        int numPlayers = -1;
        Pattern gameArrangementPattern = Pattern.compile("a .*?;");
        Matcher gameArrangementMatcher = gameArrangementPattern.matcher(stateString);

        if (gameArrangementMatcher.find()) {
            String[] parts = gameArrangementMatcher.group().substring(0, gameArrangementMatcher.group().length() - 1).split(" ");
            if (parts.length >= 3) {
                try {
                    numPlayers = Integer.parseInt(parts[2]);
                } catch (NumberFormatException e) {
                }
            }
        }
        return numPlayers;
    }

    public static int getRemainingSettlers(String stateString, int currentPlayerId, int numPlayers) {
        int maxSettlers = 0;
        switch (numPlayers) {
            case 2:
                maxSettlers = 30;
                break;
            case 3:
                maxSettlers = 25;
                break;
            case 4:
                maxSettlers = 20;
                break;
            default:
                return -1;
        }
        int usedSettlers = getPlayerSettlers(stateString, currentPlayerId).size();
        return maxSettlers - usedSettlers;
    }

    public static int getRemainingVillages(String stateString, int currentPlayerId) {
        int maxVillages = 5;
        int usedVillages = getPlayerVillages(stateString, currentPlayerId);
        return maxVillages - usedVillages;
    }

    public static Set<String> getPlayerSettlers(String stateString, int playerId) {
        Set<String> settlers = new HashSet<>();
        Pattern playerPattern = Pattern.compile("p " + playerId + " .*?;");
        Matcher playerMatcher = playerPattern.matcher(stateString);

        if (playerMatcher.find()) {
            Pattern settlerPattern = Pattern.compile("S( \\d+,\\d+)+");
            Matcher settlerMatcher = settlerPattern.matcher(playerMatcher.group());
            if (settlerMatcher.find()) {
                String[] coordinates = settlerMatcher.group().substring(2).split(" ");
                for (String coordinate : coordinates) {
                    settlers.add(coordinate);
                }
            }
        }
        return settlers;
    }

    public static int getPlayerVillages(String stateString, int playerId) {
        int villageCount = 0;
        Pattern playerPattern = Pattern.compile("p " + playerId + " .*?;");
        Matcher playerMatcher = playerPattern.matcher(stateString);

        if (playerMatcher.find()) {
            String playerStatement = playerMatcher.group();
            Pattern villagePattern = Pattern.compile("T( \\d+,\\d+)+");
            Matcher villageMatcher = villagePattern.matcher(playerStatement);

            if (villageMatcher.find()) {
                String villages = villageMatcher.group().substring(1).trim();
                String[] villageArray = villages.split(" ");
                villageCount = villageArray.length;
            }
        }
        return villageCount;
    }

    public static List<String> getNeighbors(int a, int b, Set<String> currentOccupied) {
        List<String> neighbors = new ArrayList<>();
        int[][] offsets;

        if (a % 2 == 0) {
            offsets = new int[][] {{-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, 0}, {1, 1}};
        } else {
            offsets = new int[][] {{-1, -1}, {-1, 0}, {0, -1}, {0, 1}, {1, -1}, {1, 0}};
        }

        for (int[] offset : offsets) {
            int newA = a + offset[0];
            int newB = b + offset[1];
            String neighbor = newA + "," + newB;
            if (currentOccupied.contains(neighbor)) {
                neighbors.add(neighbor);
            }
        }
        return neighbors;
    }

}
