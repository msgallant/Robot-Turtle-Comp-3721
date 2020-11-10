package view;

import javax.swing.JButton;

import controller.GameBoardConverter;

public interface DisplayInterface {
	public JButton[][] getTileButtons();
	public JButton[] getCardButtons();
	public JButton getConfirmButton();
	public void setGameInformation(String[] ct);
	
	public void drawCurrentGame(GameBoardConverter gbc);
	public void drawCards();
	public void invalidSelection();
	public void turtleUnableToMove();
	public void finish();
	
	public void changePlayersTurnIndicatorLabel(int i);
	
	public void enableAllCardButtons();
	public void disableConfirmButton();
	public void enableConfirmButton();
	public void disableAllCardButtons();
	public void disableCardButton(int i);
	public void enableCardButton(int i);
	
	
}
