package model;
//this class is responsible for creating the cards that are private variables.
public class CardDeckCreator {
	private static int numCards = 6;
	private static String[] cardTypes = {"Turn Left", "Step Forward", "Turn Right", "Bug", "Laser", "Function Frog"};
	private static String[] cardColours = {"Yellow", "Blue", "Purple", "Brown", "Red", "Green"};
	private static Card[] cardDeck = new Card[numCards];
	
	public CardDeckCreator()
	{
		
	}
	public Card[] createCardDeck()
	{
		for (int i = 0; i < cardTypes.length; i++)
		{
			Card c = new Card(cardTypes[i], cardColours[i]);
			cardDeck[i] = c;
			
		}
		return cardDeck;
	}
	public String[] getCardTypes()
	{
		return cardTypes;
	}
}
