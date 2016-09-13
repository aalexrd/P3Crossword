package crossword.controller;

import crossword.model.Model;
import crossword.view.CellPanel;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GridController implements MouseListener, KeyListener
{

	Model model;

	public GridController(Model model)
	{
		this.model = model;
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		model.setTypeDirection(SwingUtilities.isRightMouseButton(e));
		model.panelSelected((CellPanel) e.getSource());
		model.setPanelClicked(true);
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		model.setPanelChar(Character.toUpperCase(e.getKeyChar()));
		e.consume();
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		switch (e.getKeyCode())
		{
			case KeyEvent.VK_UP:
				model.navigateKey('U');
				break;
			case KeyEvent.VK_DOWN:
				model.navigateKey('D');
				break;
			case KeyEvent.VK_LEFT:
				model.navigateKey('L');
				break;
			case KeyEvent.VK_RIGHT:
				model.navigateKey('R');
				break;
		}
		e.consume();
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
	}

}
