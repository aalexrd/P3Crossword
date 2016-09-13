package crossword.model;

import java.util.ArrayList;

public class CrossWord
{

	private final ArrayList<Word> wordsList;
	private final int rows;
	private final int cols;

	public CrossWord(ArrayList<Word> wordsList, int rows, int cols)
	{
		this.wordsList = wordsList;
		this.rows = rows;
		this.cols = cols;
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

}
