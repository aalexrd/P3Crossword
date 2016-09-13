package crossword.controller;

import crossword.view.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuController implements ActionListener
{

	View view;

	public MenuController(View view)
	{
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		switch (e.getActionCommand())
		{
			case "Load":
				view.load();
				break;
			case "Normal":
				view.setMode(false);
				break;
			case "Assisted":
				view.setMode(true);
				break;
			case "Check":
				view.check();
				break;
			case "View Solution":
				view.solve();
				break;
			case "Exit":
				System.exit(0);
		}
	}

}
