package crossword.loader;

import crossword.model.CrossWord;
import crossword.model.Word;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static crossword.model.Word.HORIZONTAL;
import static crossword.model.Word.VERTICAL;

public class CrossWordsLoader
{

	private final String input; // Path to xml
	private final ArrayList<CrossWord> list = new ArrayList<>(); // List of CrossWords
	private final boolean okay;

	public CrossWordsLoader(String input)
	{
		this.input = input;
		okay = validate();
		load();
	}

	private void load()
	{

		int col, cols, row, rows;
		String definition, term;
		boolean direction;

		if (okay) // If the input file matches given xsd
			try
			{
				// Open xml
				File inputFile = new File(input);
				DocumentBuilderFactory dbFactory
						= DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(inputFile);
				doc.getDocumentElement().normalize();

				// Get list of crosswords in xml
				NodeList cList = doc.getElementsByTagName("crossword");
				for (int i = 0; i < cList.getLength(); ++i)
				{
					Element current = (Element) cList.item(i); // Save current crossword

					// Get columns and rows of current crossword
					rows = Integer.parseInt(current.getElementsByTagName("rows").item(0).getTextContent());
					cols = Integer.parseInt(current.getElementsByTagName("cols").item(0).getTextContent());

					// List of words in the crossword
					ArrayList<Word> wordsList = new ArrayList<>();

					// Get list of words in the current crossword
					NodeList wList = current.getElementsByTagName("word");
					for (int j = 0; j < wList.getLength(); ++j)
					{
						current = (Element) wList.item(j); // Save current word

						// Get coordinates (col, row) of word
						row = Integer.parseInt(current.getElementsByTagName("row").item(0).getTextContent());
						col = Integer.parseInt(current.getElementsByTagName("col").item(0).getTextContent());

						// Get word direction
						if (current.getElementsByTagName("direction").item(0).getTextContent().equals("H"))
							direction = HORIZONTAL;
						else
							direction = VERTICAL;

						// Get word (term) and its definition
						term = current.getElementsByTagName("term").item(0).getTextContent().toUpperCase();
						definition = current.getElementsByTagName("definition").item(0).getTextContent();

						// Add the word to the list of words
						wordsList.add(new Word(row - 1, col - 1, direction, term, definition));
					}

					// Add the crossword with its properties
					list.add(new CrossWord(wordsList, rows, cols));
				}
			}
			catch (ParserConfigurationException | SAXException | IOException | DOMException | NumberFormatException e)
			{
				e.printStackTrace();
			}
	}

	private boolean validate()
	{
		try
		{
			InputStream xsdStream = CrossWordsLoader.class.getClassLoader()
					.getResourceAsStream("crossword/loader/crossword.xsd");
			StreamSource xsdSource = new StreamSource(xsdStream);
			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = schemaFactory.newSchema(xsdSource);
			Validator validator = schema.newValidator();
			validator.validate(new StreamSource(new File(input)));
			return true;
		}
		catch (SAXException e)
		{
			System.out.println("XML file is not valid");
			System.out.println("Reason: " + e.getLocalizedMessage());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return false;
	}

	public boolean validateCrosswords()
	{
		if (!okay)
			return false;
		for (CrossWord c : list)
		{
			if (c.getCols() < 0 || c.getRows() < 0)
				return false;
			ArrayList<Word> words = c.getWordsList();
			for (int i = 0; i < words.size(); ++i)
			{
				if (words.get(i).getCol() >= c.getCols()
						|| words.get(i).getRow() >= c.getRows()
						|| words.get(i).getCol() < 0
						|| words.get(i).getRow() < 0)
					return false;
				if (words.get(i).isDirection()) // Horizontal
				{
					if (words.get(i).getTerm().length()
							+ words.get(i).getCol() > c.getCols())
						return false;
				}
				else if (words.get(i).getTerm().length()
						+ words.get(i).getRow() > c.getRows())
					return false;
				for (int j = i + 1; j < words.size(); ++j)
				{
					if (words.get(j).isDirection()) // Horizontal
					{
						if (words.get(i).getRow() != words.get(j).getRow())
							continue;
						int length = words.get(j).getCol() + words.get(j).getTerm().length() - 1;
						if (words.get(i).getCol() <= length
								&& words.get(i).getCol() >= words.get(j).getCol())
							return false;
					}
					else // Vertical
					{
						if (words.get(i).getCol() != words.get(j).getCol())
							continue;
						int length = words.get(j).getRow() + words.get(j).getTerm().length() - 1;
						if (words.get(i).getRow() <= length
								&& words.get(i).getRow() >= words.get(j).getRow())
							return false;
					}
				}
			}
		}
		return true;
	}

	public String getInput()
	{
		return input;
	}

	public ArrayList<CrossWord> getList()
	{
		return list;
	}

	public boolean isOkay()
	{
		return okay;
	}

}
