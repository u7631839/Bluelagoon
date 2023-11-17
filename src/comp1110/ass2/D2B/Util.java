package comp1110.ass2.D2B;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
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
    public static Set<String> getplayerOccupiedCoordinates(String stateString,String playerId) {
        Set<String> playerOccupied = new HashSet<>();
        Pattern playerPattern = Pattern.compile("p " + playerId + " .*?;");
        Matcher playerMatcher = playerPattern.matcher(stateString);
        if (playerMatcher.find()) {
            String playerStatement = playerMatcher.group();
            Pattern occupiedPattern = Pattern.compile("(S|T) (\\d+,\\d+ ?)+");
            Matcher occupiedMatcher = occupiedPattern.matcher(playerStatement);
            while (occupiedMatcher.find()) {
                String[] coordinates = occupiedMatcher.group().substring(2).split(" ");
                for (String coordinate : coordinates) {
                    playerOccupied.add(coordinate);
                }
            }
        }
        return playerOccupied;
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
                if (Rules.isCoordinate(islandParts[i])) {
                    land.add(islandParts[i]);
                }
            }
        }
        return land;
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
    public static Set<String>sea(String stateString){
        Set<String>sea = new HashSet<>();
        Set<String> seacoor=getSeaCoordinates(stateString,getLandCoordinates(stateString));
        for (String i:sea){
            sea.add("S "+i);
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
        //System.out.println(neighbors);
        return neighbors;
    }

    public static String getMoveType(String moveString) {
        String[] moveParts = moveString.split(" ");
        String pieceType = moveParts[0];
        return pieceType;
    }

    public static String getTargetCoodinate(String moveString) {
        String[] moveParts = moveString.split(" ");
        String targetCoordinate = moveParts[1];
        return targetCoordinate;
    }

    public static String getPlayerStatement(String stateString, String playerId) {
        int playerStatementStartIndex = stateString.indexOf("p " + playerId);
        int playerStatementEndIndex = stateString.indexOf(";", playerStatementStartIndex);
        return stateString.substring(playerStatementStartIndex, playerStatementEndIndex + 1);
    }

    public static String getUnclaimedResource(String stateString) {
        int StartIndex = stateString.indexOf("r ");
        int EndIndex = stateString.indexOf(";", StartIndex);
        return stateString.substring(StartIndex, EndIndex + 1);
    }

    public static Map<String, List<String>> getResourceCoordinates(String unclaimedResourcesAndStatuettesStatement) {
        Map<String, List<String>> resourceCoordinatesMap = new HashMap<>();
        unclaimedResourcesAndStatuettesStatement= unclaimedResourcesAndStatuettesStatement.substring(0, unclaimedResourcesAndStatuettesStatement.length() - 1);
        String[] resourceParts = unclaimedResourcesAndStatuettesStatement.split(" ");
        String currentResourceType = "";
        List<String> currentCoordinates = new ArrayList<>();

        for (String part : resourceParts) {
            if (part.equals("r")) {
                continue;
            } else if (part.equals("C") || part.equals("B") || part.equals("W") || part.equals("P") || part.equals("S")) {
                currentResourceType = part;
                currentCoordinates = new ArrayList<>();
            } else {
                currentCoordinates.add(part);
                resourceCoordinatesMap.put(currentResourceType, currentCoordinates);
            }
        }

        return resourceCoordinatesMap;
    }

    public static String getPhase(String stateString) {
        String phase = null;
        Pattern currentStatePattern = Pattern.compile("c .*?;");
        Matcher currentStateMatcher = currentStatePattern.matcher(stateString);

        if (currentStateMatcher.find()) {
            String[] parts = currentStateMatcher.group().split(" ");
            if (parts.length >= 2) {
                phase = parts[2].substring(0, 1);
            }
        }
        return phase;
    }

    public static Set<String> getStoneCoordinates(String stateString) {
        Set<String> stoneCoordinates = new HashSet<>();

        int stoneStartIndex = stateString.indexOf("s");
        int stoneEndIndex = stateString.indexOf(";", stoneStartIndex);

        String stoneStatement = stateString.substring(stoneStartIndex, stoneEndIndex);

        String[] coordinates = stoneStatement.split(" ");

        for (String coordinate : coordinates) {
            if (coordinate.matches("\\d+,\\d+")) {
                stoneCoordinates.add(coordinate);
            }
        }

        return stoneCoordinates;
    }

}
