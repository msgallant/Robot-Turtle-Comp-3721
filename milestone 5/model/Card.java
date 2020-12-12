package model;
//This class allows you to create cards, Card is a parameter in the turtleMove() method in GameBoardModel
//Depending on what card is passed, the turtle moves a certain way

public class Card
{
  private String cardType, cardColour;
  public Card(String type, String colour)
  {
    cardType = type;
    cardColour = colour;
  }
  public String getCardType()
  {
	  return cardType;
  }
  public String getCardColour()
  {
	  return cardColour;
  }
}