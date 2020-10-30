package model;

/* 
 * COMP 3721 - Object Oriented Design and Methodology
 * Term Project - Milestone 3
 * Code Version 3
 * Marcia Gallant & Loryn Losier
 * October 30th, 2020
 */

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