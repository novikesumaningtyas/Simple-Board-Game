import java.util.Random;

/**
 *  This Dice class is use for generate random number.
 *  The number will use in the Game class for each player to move.
 *  Players can decice the minimum and the maximum value that will be shown in the dice
 * @author (Novi Kesumaningtyas) 
 * @version (1.0 - 29 March 2017)
 */

public class Dice
{
    private int maxDiceValue;
    private int minDiceValue;

    /**
     * Dice() - 
     * This constructor consist of default value for each attribute,
     * For default, the fields sets as common dice use - 6 sides dice
     */
    public Dice()
    {
        maxDiceValue = 6;
        minDiceValue = 1;
    }
    
    /**
     * Dice(int maxDiceNumber, int minDiceNumber) -
     * User sets the maximum and minimum number for the dice
     * @param int maxDiceNumber for dice maximum value
     * @param int minDiceNumber for dice minimum value
     */
    public Dice(int maxDiceNumber, int minDiceNumber)
    {
        maxDiceValue = maxDiceNumber;
        minDiceValue = minDiceNumber;
    }
    
     /**
     * getMaxDiceValue() -
     * Get the maximum value from the dice 
     * @return int for maximum value of the dice
     */
    public int getMaxDiceValue()
    {
        return maxDiceValue;
    }
    
    /**
     * getMinDiceValue() -
     * Get the minimum value from the dice
     * @return int of minimum value of the dice
     */
    public int getMinDiceValue()
    {
        return minDiceValue;
    }
    
    /**
     * getRolledResult() -
     * Get the random number from the minimum to the maximum limit 
     * @return int value of the value of roll 
     */
    public int getRolledResult()
    {
        Random dice = new Random();
        int roll = dice.nextInt((maxDiceValue - minDiceValue) + 1) + minDiceValue;
        
        return roll;
    }
    
     /**
     * printDiceValue() - 
     * Print the maximum and minimum number limit of the dice
     */
    public void printDiceValue()
    {
        System.out.println("Dice maximum number is: " + maxDiceValue);
        System.out.println("Dice minimum number is: " + minDiceValue);
    }
    
    /**
     * setDiceValue(int maximum, int minimum) -
     * Set new value for maximum and minimum number for the dice
     * @param int maximum for maximum value
     * @param int minimum for minimum value
     */
    public void setDiceValue(int maximum, int minimum)
    {
        maxDiceValue = maximum;
        minDiceValue = minimum;
    }
}
