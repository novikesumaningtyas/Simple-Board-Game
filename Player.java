/**
 * Player Class
 * Stored players name and position
 * 
 * @author Novi Kesumaningtyas 
 * @version 1.2 - 8 April 2017
 */
public class Player
{
    public String name;
    public int currentPosition;
    public int startPosition;
    
    /**
     * Player() -
     * Constructor for objects of class Player,
     * name, currentPosition, and startPosition set in default value
     */
    public Player()
    {
        name = "";
        currentPosition = 0;
        startPosition = 0;
    }
    
     /**
     * Player(String playersName, int lastPosition, int beginPosition) -
     * Constructor player class with parameter,
     * User set the players' name, start position, and the position after
     * @param String playersName - Set the name of the players
     * @param int lastPosition - Set the players' current position 
     * @param int beginPosition - Set the players' start position
     */
    public Player(String playersName, int lastPosition, int beginPosition)
    {
        name = playersName;
        currentPosition = lastPosition;
        startPosition = beginPosition; 
    }
    
    /**
     * getCurrentPosition() -
     * get current position value 
     * @return int the currentPosition value
     */
    public int getCurrentPosition()
    {   
        return currentPosition;
    }
    
     /**
     * getPlayerName() - 
     * get player's name 
     * @return String players'name
     */
    public String getPlayerName()
    {
        return name;
    }
    
    /**
     * getRollResult() -
     * Get roll result from players' displacement
     * @return int roll 
     */
    public int getRollResult()
    {
        int roll = currentPosition - startPosition;
        
        return roll;
    }
    
    /**
     * getStartPosition() -
     * get players' start position before roll the dice
     * @return int start position value
     */
    public int getStartPosition()
    {
        return startPosition;
    }
    
     /**
     * printPlayerDetails Method
     * 
     * Print players name, start position, rolled result, and the position after
     */
    public void printPlayerDetails()
    {
        System.out.println(name + " get " + getRollResult() + " and moves from " + startPosition + " to " + currentPosition); 
    }
    
     /**
     * setCurrentPosition(int position) -
     * Set current position of the players in the game
     * @param int position - set player current position
     */
    public void setCurrentPosition(int position)
    {
        currentPosition = position;
    }
    
    /**
     * setPlayersName(String identity) -
     * Set the the players' name
     * @param String identity - set player name
     */
    public void setPlayersName(String identity)
    {
        name = identity;
    }
    
    /**
     * setStartPosition(int startPos) -
     * Set start position of the players in the game
     * @param int startPos - set players' start position
     */
    public void setStartPosition(int startPos)
    {
       startPosition = startPos; 
    }
}
