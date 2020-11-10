package model;
//this class is responsible for creating the cards that are private variables.
public class CardDeckCreator {
	private static int numCards = 4;
	private static String[] cardTypes = {"Turn Left", "Step Forward", "Turn Right", "Bug"};
	private static String[] cardColours = {"Yellow", "Blue", "Purple", "Brown"};
	private static Card[] cardDeck = new Card[numCards];
	
	public CardDeckCreator()
	{
		
	}
	public Card[] createCardDeck()
	{
		for (int i = 0; i < cardTypes.length; i++)
		{
			Card c = new Card(cardTypes[0], cardColours[0]);
			cardDeck[i] = c;
			
		}
		return cardDeck;
	}
}
