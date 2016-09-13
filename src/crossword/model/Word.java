package crossword.model;

public class Word
{

	// Used to define direction
	public static final boolean HORIZONTAL = true;
	public static final boolean VERTICAL = false;

	private final int row;
	private final int col;
	private final boolean direction;
	private final String term;
	private final String definition;
	private boolean answered;

	public Word(int row, int col, boolean direction, String term, String definition)
	{
		this.row = row;
		this.col = col;
		this.direction = direction;
		this.term = term;
		this.definition = definition;
	}

	public int getRow()
	{
		return row;
	}

	public int getCol()
	{
		return col;
	}

	public boolean isDirection()
	{
		return direction;
	}

	public String getTerm()
	{
		return term;
	}

	public String getDefinition()
	{
		return definition;
	}

	public boolean isAnswered()
	{
		return answered;
	}

	public void setAnswered(boolean answered)
	{
		this.answered = answered;
	}

}
