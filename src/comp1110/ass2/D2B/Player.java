package comp1110.ass2.D2B;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Player {
        private String color; // player color
        private int number; // player number
        private int settlersCount; // current settlers count
        //private ArrayList<Resource> resources; // current resources
        //private ArrayList<Piece> pieces; // current villages
        private int score; // player score
        private boolean isCurrentPlayer; // whether the player is currently online




        // Get player color
        //public String getColor(){};

        // Get player number
        //public int getNumber(){};

        // Get current settlers count
        //public int getSettlersCount(){};

        // Set current settlers count
        public void setSettlersCount(int count){};

        // Get current resources


        // Set current resources
        //public void setResources(HashMap<String, Integer> resources){};

        // Get current villages
        //public ArrayList<Village> getVillages(){};

        // Set current villages
        //public void setVillages(ArrayList<Village> villages){};

        // Get player score
        //public int getScore(){};

        // Set player score
        public void setScore(int score){};

        // Check if player is currently online
        //public boolean isCurrentPlayer(){};

        // Set whether the player is currently online
        public void setIsCurrentPlayer(boolean isCurrentPlayer){};

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

    }
