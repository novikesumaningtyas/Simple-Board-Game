import java.util.*;

/**
 * Game Class -
 * This class responsible for displaying the menus, accepting user responses, 
 * and performing the requested operations, 
 * It will make use of Player objects and Dice object. 
 * 
 * @author Novi Kesumaningtyas 
 * @version 2 - 13 April 2017
 */

public class Game
{
    /**
     * Attribut definition
     * finalPos : Whoever player get this position first, he will win the game
     * allowedPlayer : Total players allowed to play the game
     * penalty : subtracted value from players' current position if player reaches snake position
     * minimalDiceNumber : The lowest value from the dices' roll result
     * maximalDiceNumber : The highest value from the dices' roll result
     */
    private int finalPos;
    private int allowedPlayer;
    private int penalty;
    private int minimalDiceNumber;
    private int maximalDiceNumber;
    /**
     * Game() - 
     * Constructor for objects of class Game,
     * Default value for game attributes
     */
    public Game()
    {
        penalty = 5;
        finalPos = 50;
        allowedPlayer = 2;
        minimalDiceNumber = 1;
        maximalDiceNumber = 6;
    }
    
    /**
     * Game class constructor (int penaltyValue, int finalValue, int playersTotal, int minDiceValue, int maxDiceValue) -
     * User set the penalty , final position, the total players, minDiceValue, maxDiceValue
     * 
     * @param int penaltyValue for the penalty
     * @param int finalValue for final position of the game
     * @param int playersTotal for total players who play the game
     * @param int minDiceValue for the lowest value from the dices' roll result
     * @param int maxDiceValue for the highest value from the dices' roll result
     */
    public Game(int penaltyValue, int finalValue, int playersTotal, int minDiceValue, int maxDiceValue)
    {
        penalty = penaltyValue;
        finalPos = finalValue;
        allowedPlayer = playersTotal;
        minimalDiceNumber = minDiceValue;
        maximalDiceNumber = maxDiceValue;
    }
    
    /**
     * checkingWinners(ArrayList<Player> players, List<Integer> champion) - 
     * Validate the availability for winners, if there is a game winner there will be notification on the screen and
     *  Players must start a new game if they want to play the game again or they can exit if they want
     * @param ArrayList<Player> players
     */
     public void checkingWinners(ArrayList<Player> players, List<Integer> champion)
    { 
        int playerChoice;
        List<Integer> currents = new ArrayList<Integer>();
        
        if (champion.size() > 0)
        {
            for (Player player : players)
            {
                currents.add(player.getCurrentPosition());
            }
        
            if (currents.stream().anyMatch(current -> current >= finalPos))
            {
                System.out.println();
                System.out.println("\tGame finished. You must start a new game by choosing 1");
                    
                for (Player player : players)
                {   
                    if (currents.stream().allMatch(current -> current >= finalPos))
                    {
                        System.out.println("\t\t**** The last game was a DRAW ****"); 
                        return;
                    } 
                    else
                        if (player.getCurrentPosition() >= finalPos)
                            System.out.println("\t   *****The last game was won by Player " + player.getPlayerName().toUpperCase() + " *****");
                }
                return; 
            }
        }
    }
    
    /**
     * createNewPlayer(ArrayList<Player> players, List<Integer> champion) - 
     * Create new player and set the player position, 
     * there will be validation steps for player(s) before input a new player 
     * 
     * @param ArrayList<Player> players - Player object in array list for the use of set or create the players
     * @param List<Integer> champion - Integer object that contain the numbers which indicate if there is any player has win the game
     */
    public void createNewPlayer(ArrayList<Player> players, List<Integer> champion)
    {
        String playerName;
        Scanner input = new Scanner(System.in);      
        
        if (players.size() != 0)
        {
           while(true)
           { 
               System.out.println();
               System.out.print("Are you sure want to create a new player -- y/n ? ");
               Scanner source = new Scanner (System.in);
               char decision = source.nextLine().charAt(0);
               decision = Character.toLowerCase(decision);
               
               if (decision == 'y')
               {     
                  players.clear();
                  champion.clear();
                  break;
               }
               else
                   if (decision == 'n')
                       return; 
               else
                    System.out.println("please enter your answer again (y/n) ");   
           }
        }
        
        for (int minPlayer = 1; minPlayer <= allowedPlayer;)
        {
           while (true)
           {
               System.out.print("\n::::Enter Player " + minPlayer + " name: ");
               playerName = input.nextLine();
               playerName = playerName.trim();
    
               if (playerName.length() > 0)
                   break;
               else
               {
                   System.out.println();
                   System.out.println("ERROR : Invalid Input. Please enter your name and not a blank space");
                   System.out.println();
               }
           }
          
           Player user = new Player();
           user.setPlayersName(playerName);
           user.setStartPosition(0);
           user.setCurrentPosition(0);
           players.add(user);
           
           minPlayer = minPlayer + 1;
        } 
        
        System.out.println("\n\t :::Hi guys, welcome to the game! Just roll the dice to move your player by pressing 2:::");
    }
    
    /**
     * displayNoPlayer() - 
     * Show error if there is no set up player
     */
    public void displayNoPlayer()
    {
        System.out.println();
        System.out.println("ERROR : Players has not been set up!");
    }
    
    /**
     * displayPlayersPositions(ArrayList<Player> players) - 
     * Show information about the players position during the game
     * @param ArrayList<Player> players - Passing the players array so that it can be displayed for players' position 
     */
    public void displayPlayerPositions(ArrayList<Player> players)
    {
        if (players.size() >= allowedPlayer)
        {
            for (Player player : players)
            {
               System.out.println();
               System.out.println("\tPlayer " + player.getPlayerName() + " is on position === " + player.getCurrentPosition() + " ===");
            }
        }
        else 
            displayNoPlayer();
    }
    
    /**
     * exitGame() - 
     * Stop the program at any time, comes with verification to make sure the players really want to exit the game
     */
    public void exitGame()
    {
        char decision;
        do 
        {
            System.out.print("Are you sure want to exit the game -- y/n : ");
            Scanner source = new Scanner (System.in);
            decision = source.nextLine().charAt(0);
            decision = Character.toLowerCase(decision);
                        
            if (decision == 'y')
            {
                System.out.println("\t------** Thankyou for playing **------");
                System.exit(0);
            }
            else
                if (decision == 'n')
                    return; 
            else
                 System.out.println("Please enter your answer again (y/n) ");   
        }while(decision != 'n' && decision != 'y');
    }
    
    /**
     * gameDraw(ArrayList<Player> players, List<Integer> champion) - 
     * Display the situation if all players reach final position in the same time
     * 
     * @param ArrayList<Player> players - Checking every players position to validate draw situation
     * @param List<Integer> champion - Integer object that contain the numbers which indicate if there is any player has win the game
     */
    public void gameDraw(ArrayList<Player> players, List<Integer> champion)
    {
        if (champion.size() == 0)
        {
            for (Player player : players)
            {
                if (!(player.getCurrentPosition() >= finalPos))
                    return;   
            }
            
            System.out.println();
            System.out.println("\t** Congratulations its a DRAW!! **" );
            champion.add(1);
        }
    }
    
    /**
     * gamePlayed(ArrayList<Player> players, List<Integer> champion) - 
     * All the step needed for the game to proceed in every turn,
     * This method will invoke other method for player to roll the dice and move players position,
     * The method also verified the players' position whether they get snake point or final position
     * @param ArrayList<Player> players - Player object in array list as a validation an passing value for methods inside
     * @param List<Integer> champion - Integer object that contain the numbers which indicate if there is any player has win the game
     */
    public void gamePlayed(ArrayList<Player> players, List<Integer> champion)
    {  
        if (players.size() >= allowedPlayer) 
        {
            checkingWinners(players, champion);
            updatePlayerPosition(players, champion);
            playersGetPenalty(players, champion);
            gameDraw(players, champion);
        }
        else 
            displayNoPlayer();
    }
    
    /**
     * getAllowedPlayer() - 
     * Get the quantity of allowable player 
     * 
     * @return int - the amount of total players
     */
    public int getAllowedPlayer()
    {
        return allowedPlayer;
    }
    
    /**
     * getFinalPosition() -
     * Get the final point for the current game
     * 
     * @return int - return the final location value
     */
    public int getFinalPosition()
    {
        return finalPos;
    }
    
    /**
     * getMaximalDiceNumber()
     * Get the maximum dices' roll value
     */
    public int getMaximalDiceNumber()
    {
        return maximalDiceNumber;
    }
    
    /**
     * getMinimalDiceNumber() -
     * Get the minimum dices' roll value
     */
    public int getMinimalDiceNumber()
    {
        return minimalDiceNumber;
    }
    
    /**
     * getPenalty() -
     * Get the penaltis' value for the recent game
     * 
     * @return int - return the value of penalty
     */
    public int getPenalty()
    {
        return penalty;
    }
    
    /**
     * multiPlayersWin(ArrayList<Player>, players List<Integer> champion) -
     * Doing check for the player(s), - it could be more than one player, but not all players - reach the final position in the same time,
     * and display it to the screen
     * 
     * @param ArrayList<Player> players - Passing this value so that this method have an array to do the validation
     * @param List<Integer> champion - Integer object that contain the numbers which indicate if there is any player has win the game
     */
    public void multiPlayersWin(ArrayList<Player> players, List<Integer> champion)
    {  
        if (champion.size() == 0)
        {
            for (Player player : players)
            {
                if (player.getCurrentPosition() >= finalPos)
                {
                    System.out.println();
                    System.out.println("\t** Congratulations, " + player.getPlayerName().toUpperCase() + " have WON this game. Well done!**" );
                    champion.add(1);
                }   
            }
        }
    }
    
    /**
     * newGame() -
     * The main method, players will interface with this method and choose the sufficient option, 
     * It comes with validation input for the players 
     */
    public void newGame()
    {
        int playerOption;
        ArrayList<Player> players;
        List<Integer> champion;
        
        players = new ArrayList<Player>();
        champion = new ArrayList<Integer>();
        printIntroduction();
        
        do 
        {
            printMenu();
            Scanner console = new Scanner(System.in);
            
            while (true)
            {
                if (console.hasNextInt())
                {
                    playerOption = console.nextInt();
                    break;  
                }
                else
                    console.next();
                    
                System.out.println();
                System.out.println("\tERROR : Invalid Input. Please enter a valid number between 0 and 6 ");
                System.out.println();
                printMenu();
            }
            
            switch (playerOption)
            {
                case 1:
                    createNewPlayer(players, champion);break;
               
                case 2:
                    gamePlayed(players, champion);break;
                    
                case 3:
                    displayPlayerPositions(players);break;
                    
                case 4:
                   printHelp();break;
                
                case 5:
                   exitGame();break;
                   
                default:
                    System.out.println("\n\tERROR : Invalid Input. Please enter a valid number between 0 and 6 \n ");
            }
        }while(true);
    }
    
    /**
     * PlayersGetPenalty(ArrayList<Player> players, List<Integer> champion) -
     * Examine for every players' location 
     * Shows the players' position whether they get the snake penalized or going forward without the hitch
     * 
     * @param ArrayList<Player> players - players array value for show the movements for each player, either get the penalized or going forward seamlessly
     * @param List<Integer> champion - Integer object that contain the numbers which indicate if there is any player has win the game
     */
    public void playersGetPenalty(ArrayList<Player> players, List<Integer> champion)
    {
        if (champion.size() == 0)
        {
            for (Player player : players)
            {
                int tempPosition = snakePenalty(player.getCurrentPosition());
                    
                if (tempPosition != player.getCurrentPosition())
                {
                    System.out.println("\t|" + player.getPlayerName() + " rolled a " + player.getRollResult() + ", and moves from position " + player.getStartPosition() + " to " + player.getCurrentPosition() +" |");
                    System.out.println("\t!Watchout " + player.getPlayerName()+ " stop at penalty box" + ", and must going backward to " + tempPosition);
                    System.out.println("\t=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
                    player.setCurrentPosition(tempPosition);
                }
                else
                {   
                    System.out.println("\t|" + player.getPlayerName() + " rolled a " + player.getRollResult() + ", and moves from position " + player.getStartPosition() + " to " + player.getCurrentPosition() + " |");
                    System.out.println("\t=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
                }
            }
        }
    }
    
    /**
     * printHelp() -
     * Display direction how to play the game
     */
    public void printHelp()
    {
        System.out.println();
        System.out.println(":::::::::::::::::::::::::::::::::::::::::::GAME HELP:::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
        System.out.println("DESCRIPTION");
        System.out.println("This is a simple board game, where the players take turn to roll a dice and move their game tokens -");
        System.out.println("based on number the players get from the dice.");
        System.out.println("In this game, if player reaches the special positions - any position that multiplied by 11 (11,22,33,44,etc. -");
        System.out.println("He will be penalised by having " + penalty +" subtracted from his current position.");
        System.out.println("The winner is the one who reaches a final position [" + finalPos +"] on the board first.");
        System.out.println();
        System.out.println("HOW TO PLAY");
        System.out.println("1. Set players name by choosing option (1) in game menu"); 
        System.out.println("2. Start the game by rolling the dice with choosing option(2)");
        System.out.println("3. Continue select option(2) until one of players reach final position (" + finalPos + ")");
        System.out.println("4. After a player reach final position, you must start a new game to play the game again by choosing option(1)");
        System.out.println("5. Choose option(3) to see players name and current position");
        System.out.println("6. Choose option(5) to exit the game");
        System.out.println();
        System.out.println("Have fun and enjoy the game!");
        System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
        System.out.println();        
    }
    
    /**
     * printIntroduction() - 
     * Display the game introduction at the first time the player activate the game
     */
    public void printIntroduction()
    {
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-Welcome to the game! *-*-*-*-*-*-*-*-*-*-*-*-*-*- ");
        System.out.println("This is a simple board game, where the players take turn to roll a dice and");
        System.out.println("move their game tokens based on number the players get from the dice.");
        System.out.println("In this game, if player reaches the special positions, he will get penalty. ");
        System.out.println("Special position is any position that multiplied by 11 (11,22,33,44,etc.)");
        System.out.println("He will be penalised by having " + penalty +" subtracted from his current position.");
        System.out.println("The winner is the one who reaches a final position [" + finalPos +"] on the board first.");
        System.out.println("\t\t\t  Please enjoy the game!");
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
        System.out.println();
    }
    
    /**
     * printMenu() - 
     * Display the option menu for players
     */
    public void printMenu()
    {
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println("||    Welcome to the Simple Board Game        ||");
        System.out.println("================================================");
        System.out.println("(1) Start/Restart a Game \n(2) Play One Round \n(3) Display Players' Positions \n(4) Display Game Help \n(5) Exit Game");
        System.out.println("================================================");
        System.out.print("Please choose an option : ");
    }
        
    /**
     * rollDice() - 
     * Rolling the dice and get the random number from the dice range (range limit included)
     * 
     * @return int - return the roll result value
     */
    public int rollDice()
    {
        Dice dice1 = new Dice(maximalDiceNumber, minimalDiceNumber);
        int roll = dice1.getRolledResult();
         
        return roll;
    }
    
    /**
     * setAllowedPlayer(int playerLimit)
     * Set the allowable player that can play the game
     * 
     * @param int playerLimit - Player insert the total player that can play the game
     */
    public void setAllowedPlayer(int playerLimit)
    {
        allowedPlayer = playerLimit;
    }
    
    /**
     * setFinalPosition(int winPosition) -
     * Set the final position for the game that want to be played
     * 
     * @param int winPosition - Player set the final point
     */
    public void setFinalPosition(int winPosition)
    {
        finalPos = winPosition;
    }
    
    /**
     * setMaximalDiceNumber(int maxDiceValue) -
     * Set the maximum dices' roll value
     * @param int maxDiceValue - user input the maximal value they want to show in dices' roll result
     */
    public void setMaximalDiceNumber(int maxDiceValue)
    {
        maximalDiceNumber = maxDiceValue;
    }
    
    /**
     * setMinimalDiceNumber(int minDiceValue) -
     * Set the minimum value for the dices' roll
     * @param int minDiceValue - user input the lowest value they want to show in dices' roll result
     */
    public void setMinimalDiceNumber(int minDiceValue)
    {
        minimalDiceNumber = minDiceValue;
    }
    
    /**
     * setPenalty(int penaltyValue) -
     * Set the penaltys' value that will be substracted from players' current position, 
     * if they reach the snake point
     * 
     * @param int penaltyValue - Player set the penalty value
     */
    public void setPenalty(int penaltyValue)
    {
        penalty = penaltyValue;
    }
    
    /**
     * snakePenalty (int position) -
     * Check in case the position passed from parameter is the same position with the snakes spot
     * 
     * @param int position - Player position in the board during the game
     * @return int - return the subtracted position if players' position is same with snake point,
     * if is not, the return value will be same with players' current position
     */
    public int snakePenalty(int position)
    { 
        for (int snakePoint = 11; snakePoint < finalPos; snakePoint += 11)
        {
            if (position == snakePoint)
                position = position - penalty;
        }    
        return position;
    }
    
    /**
     * updatePlayerPosition(ArrayList<Player> players , List<Integer> champion) -
     * Update the players' position after rolling the dice,
     * Set the start and current position for the players
     * 
     * @param ArrayList<Player> players - as a value for set every player position in Players array
     * @param List<Integer> champion - Integer object that contain the numbers which indicate if there is any player has win the game
     */
    public void updatePlayerPosition(ArrayList<Player> players, List<Integer> champion)
    {
        if (champion.size() == 0)
        {
            for (Player player : players)
            {           
                player.setStartPosition(player.getCurrentPosition());
                int curPos = player.getCurrentPosition() + rollDice();
                player.setCurrentPosition(curPos);
            }    
        }
    }
}