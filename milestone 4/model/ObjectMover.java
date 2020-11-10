package model;
//this class is responsibile for moving the turtle correctly, not moving the turtle if its not possible
//moving crates if they were pushed by a turtle
import model_gameboard.CratePositionUpdater;
import model_gameboard.GameBoard;
import model_gameboard.GameBoardMoveChecker;
import model_gameboard.RobotTurtlePositionUpdater;
import model_tiles.CratePositionCalculator;
import model_turtle.RobotTurtle;
import model_turtle.RobotTurtlePositionCalculator;

public class ObjectMover {
	private Card[] cardDeck;
	private GameBoard gameBoard;
	public ObjectMover(Card[] c, GameBoard gb)
	{
		cardDeck = c;
		gameBoard = gb;
	}
	public boolean moveTurtle(RobotTurtle rt, Card c)
	  {
		  //need to pass to moveTurtle in turtle class and gameboard
		  //need to check if able to move
		  int[] turtleOldPos = {rt.getXPos(), rt.getYPos()};
		  boolean turtleMoved = true;
		  
		  //updates the turtles position in the object itself
		  if (c.equals(cardDeck[1])) //if card = step forward
		  {
			  RobotTurtlePositionCalculator rtpc;
			  rtpc = new RobotTurtlePositionCalculator(rt.getXPos(), rt.getYPos(), rt.getDirection());
			  
			  if (rtpc.checkIfTileAheadExists()) //making sure robot turtle isnt trying to go off the gameboard
			  {
				  GameBoardMoveChecker moveChecker = new GameBoardMoveChecker(gameBoard.getTileList(), gameBoard.getTileAttacher());
				  if (moveChecker.checkIfTurtleCanMove(rtpc.calculateNewPosition(), rt.getDirection())) //checking if obstacle blocking turtle's path
				  {
					  int turtleNewPos[] = rtpc.calculateNewPosition();
					  int direction = rt.getDirection();
					  if (moveChecker.checkIfTurtlePushedACrate(turtleNewPos[0], turtleNewPos[1]))
					  {
						  	CratePositionCalculator cpc = new CratePositionCalculator();
						  	int[] crateNewPos = cpc.findNewCratePosition(turtleNewPos, direction); //crate's old position and direction pushes are the parameters.
						  	CratePositionUpdater crateUpdater = new CratePositionUpdater(gameBoard.getTileAttacher(), gameBoard.getTileList());
						  	//updates position in crate object itself, then updates position of crate on the board
						  	crateUpdater.updateCratePosition(turtleNewPos, crateNewPos);
						  	crateUpdater.updateCratePositionOnBoard(turtleNewPos, crateNewPos); //take in crate's old position and new position
						  	
					  }
					  
					  RobotTurtlePositionUpdater turtleUpdater = new RobotTurtlePositionUpdater(gameBoard.getTileList());
					  turtleUpdater.updateTurtlePosition(turtleNewPos[0], turtleNewPos[1], direction, rt); //update turtle x, y coordinates depending on what direction the turtle is facing
					  turtleUpdater.updateTurtlePositionOnBoard(turtleNewPos, turtleOldPos, rt); 
					  
					  
					  //update gameboard that new position is occupied with a turtle and old position is not 
					  //and stores the robot turtle that's on the tile in a Tile private variable and 
					  //sets the old tile robot turtle var to false
				  }
				  else
				  {
					  turtleMoved = false;
				  }
				                                                        
			  }
			  else
			  {
				  turtleMoved = false;
			  }
		  }
		  else if (c.equals(cardDeck[0]))// turn left card
		  {
			  RobotTurtlePositionCalculator rtpc;
			  rtpc = new RobotTurtlePositionCalculator(rt.getDirection());
			  int newDir = rtpc.directionShiftLeft();
			  rt.setDirection(newDir);
		  }
		  else if (c.equals(cardDeck[2])) //turn right card
		  {
			  RobotTurtlePositionCalculator rtpc;
			  rtpc = new RobotTurtlePositionCalculator(rt.getDirection());
			  int newDir = rtpc.directionShiftRight();
			  rt.setDirection(newDir);
		  }
		  return turtleMoved;
		  
		  
	  }
}
