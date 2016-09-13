package crossword.model;

import crossword.view.CellPanel;
import crossword.view.HintsPanel;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Observable;

public class Model extends Observable
{

	private CellPanel current;
	private boolean assisted;
	private boolean typeDirection;
	private boolean panelClicked;
	private final int rows;
	private final int cols;

	public CellPanel[][] grid;
	public HintsPanel hints;
	public ArrayList<Word> wordsList;

	public Model(CrossWord c)
	{
		wordsList = new ArrayList<>();
		wordsList = c.getWordsList();
		rows = c.getRows();
		cols = c.getCols();
		grid = new CellPanel[rows][cols];
		assisted = false;
	}

	public void check()
	{
		for (int i = 0; i < wordsList.size(); ++i)
		{
			Word word = wordsList.get(i);
			if (word.isDirection()) // Horizontal
			{
				for (int j = word.getCol(), k = word.getRow(); j < word.getTerm().length(); ++j)
				{
					if (grid[k][j].getAnswer() != grid[k][j].getChar())
						break;
					if (j == word.getTerm().length() - 1)
						word.setAnswered(true);
				}
			}
			else
			{
				for (int j = word.getRow(), k = word.getCol(); j < word.getTerm().length(); ++j)
				{
					if (grid[j][k].getAnswer() != grid[j][k].getChar())
						break;
					if (j == word.getTerm().length() - 1)
						word.setAnswered(true);
				}
			}
		}
		hints.refreshLists();
		update();
	}

	public void solve()
	{
		for (int i = 0; i < rows; ++i)
			for (int j = 0; j < cols; ++j)
			{
				if (!grid[i][j].isAvailable())
					continue;
				grid[i][j].setChar(grid[i][j].getAnswer());
			}
		update();
	}

	public void panelSelected(CellPanel panel)
	{
		if (panel.isAvailable())
		{
			if (current == null)
				current = panel;
			if (panel.getBackground().equals(Color.GREEN))
				panel.setBackground(Color.WHITE);
			else
				panel.setBackground(Color.GREEN);
			if (!current.equals(panel))
			{
				current.setBackground(Color.WHITE);
				current = panel;
			}
			panel.requestFocus();
			update();
		}
	}

	public void setPanelChar(char c)
	{
		if (!Character.isAlphabetic(c))
			return;
		if (panelClicked)
		{
			current.setChar(c);
			panelClicked = false;
		}
		else if (typeDirection) // Horizontal
		{
			if (current.getJ() + 1 < cols)
				panelSelected(grid[current.getI()][current.getJ() + 1]);
			current.setChar(c);
		}
		else // Vertical
		{
			if (current.getI() + 1 < rows)
				panelSelected(grid[current.getI() + 1][current.getJ()]);
			current.setChar(c);
		}
		if (assisted)
		{
			for (int i = 0; i < rows; ++i)
				for (int j = 0; j < cols; ++j)
					if (grid[i][j].getAnswer() == c)
						grid[i][j].setChar(c);
		}
		update();
	}

	public void navigateKey(char c)
	{
		switch (c)
		{
			case 'U':
				if (current.getI() - 1 >= 0)
					panelSelected(grid[current.getI() - 1][current.getJ()]);
				break;
			case 'D':
				if (current.getI() + 1 < rows)
					panelSelected(grid[current.getI() + 1][current.getJ()]);
				break;
			case 'L':
				if (current.getJ() - 1 >= 0)
					panelSelected(grid[current.getI()][current.getJ() - 1]);
				break;
			case 'R':
				if (current.getJ() + 1 < cols)
					panelSelected(grid[current.getI()][current.getJ() + 1]);
				break;
		}
	}

	public CellPanel[][] getGrid()
	{
		return grid;
	}

	public void setHints(HintsPanel hints)
	{
		this.hints = hints;
	}

	public ArrayList<Word> getWordsList()
	{
		return wordsList;
	}

	public int getRows()
	{
		return rows;
	}

	public int getCols()
	{
		return cols;
	}

	public void setAssisted(boolean assisted)
	{
		this.assisted = assisted;
	}

	public void setCurrent(CellPanel current)
	{
		this.current = current;
	}

	public void setTypeDirection(boolean typeDirection)
	{
		this.typeDirection = typeDirection;
	}

	public void setPanelClicked(boolean panelClicked)
	{
		this.panelClicked = panelClicked;
	}

	private void update()
	{
		setChanged();
		notifyObservers(null);
	}

}
