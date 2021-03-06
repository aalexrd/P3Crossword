package crossword.view;

import crossword.controller.GridController;
import crossword.model.Model;
import crossword.model.Word;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.ArrayList;

public class GridPanel extends JPanel
{

	private int rows;
	private int cols;
	private Model model;

	/**
	 * Creates new form GridPanel
	 *
	 * @param model the model for the grid
	 */
	public GridPanel(Model model)
	{
		this.model = model;
		rows = model.getRows();
		cols = model.getCols();
		initComponents();
		initGrid();
	}

	/**
	 * Fills panel with cells
	 */
	private void initGrid()
	{
		GridController gridController = new GridController(model);
		GridBagConstraints gbc = new GridBagConstraints();
		CellPanel[][] gridC = model.getGrid();
		for (int i = 0; i < rows; i++)
			for (int j = 0; j < cols; j++)
			{
				gbc.gridx = j;
				gbc.gridy = i;

				CellPanel cellPanel = new CellPanel();
				Border border;
				if (i < rows - 1)
				{
					if (j < cols - 1)
						border = new MatteBorder(1, 1, 0, 0, Color.GRAY);
					else
						border = new MatteBorder(1, 1, 0, 1, Color.GRAY);
				}
				else if (j < cols - 1)
					border = new MatteBorder(1, 1, 1, 0, Color.GRAY);
				else
					border = new MatteBorder(1, 1, 1, 1, Color.GRAY);
				cellPanel.setBorder(border);
				cellPanel.addMouseListener(gridController);
				cellPanel.addKeyListener(gridController);
				cellPanel.setI(i);
				cellPanel.setJ(j);
				gridC[i][j] = cellPanel;
				add(cellPanel, gbc);
			}
		enableCells();
	}

	private void enableCells()
	{
		CellPanel[][] gridC = model.getGrid();
		ArrayList<Word> wordsList = model.getWordsList();
		for (int i = 0; i < wordsList.size(); ++i)
		{
			Word word = wordsList.get(i);
			int x = word.getCol();
			int y = word.getRow();
			if (x >= cols || y >= rows) // Skip word otherwise face an exception
				continue;
			if (x == cols / 2 && y == rows / 2) // If is in the middle skip
				continue;
			if (word.isDirection()) // Horizontal
			{
				for (int j = 0; j < word.getTerm().length(); ++j)
				{
					if (j == 0)
						gridC[y][x].setNumber(i + 1);
					gridC[y][x].setAnswer(word.getTerm().charAt(j));
					gridC[y][x++].setAvailable(true);
				}
			}
			else // Vertical
			{
				for (int j = 0; j < word.getTerm().length(); ++j)
				{
					if (j == 0)
						gridC[y][x].setNumber(i + 1);
					gridC[y][x].setAnswer(word.getTerm().charAt(j));
					gridC[y++][x].setAvailable(true);
				}
			}
		}
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        setLayout(new java.awt.GridBagLayout());
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
