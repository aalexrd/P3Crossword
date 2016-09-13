package crossword.view;

import crossword.model.Model;
import crossword.model.Word;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;

public class HintsPanel extends JPanel
{

	private final int height;
	private final ArrayList<Word> wordsList;
	Model model;

	/**
	 * Creates new form HintsPanel
	 *
	 * @param model
	 */
	public HintsPanel(Model model)
	{
		height = model.getRows();
		wordsList = model.getWordsList();
		this.model = model;
		initComponents();
		initLists();
	}

	private void initLists()
	{
		refreshLists();
		model.setHints(this);
	}

	public void refreshLists()
	{
		DefaultListModel across = new DefaultListModel();
		DefaultListModel down = new DefaultListModel();
		for (int i = 0; i < wordsList.size(); ++i)
		{
			Word word = wordsList.get(i);
			if (word.isDirection()) // Horizontal
			{
				if (word.isAnswered())
					across.addElement(((i + 1) + " " + word.getDefinition() + "✓"));
				else
					across.addElement(((i + 1) + " " + word.getDefinition()));
			}
			else if (word.isAnswered()) // Vertical
				down.addElement(((i + 1) + " " + word.getDefinition() + "✓"));
			else
				down.addElement(((i + 1) + " " + word.getDefinition()));
		}
		acrossList.setModel(across);
		downList.setModel(down);
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

        acrossListContainer = new javax.swing.JScrollPane();
        acrossList = new javax.swing.JList<>();
        downListContainer = new javax.swing.JScrollPane();
        downList = new javax.swing.JList<>();
        acrossLabel = new javax.swing.JLabel();
        downLabel = new javax.swing.JLabel();

        setMinimumSize(new Dimension(30, height * 30));
        setOpaque(false);

        acrossListContainer.setBorder(null);
        acrossListContainer.setOpaque(false);

        acrossList.setBackground(new java.awt.Color(240, 240, 240));
        acrossList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        acrossList.setFocusable(false);
        acrossList.setOpaque(false);
        acrossList.setSelectionBackground(new java.awt.Color(240, 240, 240));
        acrossList.setSelectionForeground(new java.awt.Color(0, 0, 0));
        acrossList.setVisibleRowCount(height);
        acrossList.setSelectionInterval(-1, -1);
        acrossListContainer.setViewportView(acrossList);

        downListContainer.setBorder(null);
        downListContainer.setOpaque(false);

        downList.setBackground(new java.awt.Color(240, 240, 240));
        downList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        downList.setFocusable(false);
        downList.setOpaque(false);
        downList.setSelectionBackground(new java.awt.Color(240, 240, 240));
        downList.setSelectionForeground(new java.awt.Color(0, 0, 0));
        downList.setVisibleRowCount(height);
        downList.setSelectionInterval(-1, -1);
        downListContainer.setViewportView(downList);

        acrossLabel.setText("Across");
        acrossLabel.setFocusable(false);

        downLabel.setText("Down");
        downLabel.setFocusable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(acrossListContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acrossLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(downListContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(downLabel))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(acrossLabel)
                    .addComponent(downLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(downListContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acrossListContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel acrossLabel;
    private javax.swing.JList<String> acrossList;
    private javax.swing.JScrollPane acrossListContainer;
    private javax.swing.JLabel downLabel;
    private javax.swing.JList<String> downList;
    private javax.swing.JScrollPane downListContainer;
    // End of variables declaration//GEN-END:variables
}
