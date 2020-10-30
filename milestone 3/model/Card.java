package model;
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