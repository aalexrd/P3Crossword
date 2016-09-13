package crossword.view;

import javax.swing.*;
import java.awt.*;

public class CellPanel extends JPanel
{

	boolean available;
	char answer;
	int i;
	int j;

	/**
	 * Creates new form CellPanel
	 */
	public CellPanel()
	{
		initComponents();
		available = false;
	}

	public char getAnswer()
	{
		return answer;
	}

	public void setAnswer(char answer)
	{
		this.answer = answer;
	}

	public boolean isAvailable()
	{
		return available;
	}

	public void setAvailable(boolean available)
	{
		this.available = available;
		if (available)
			setBackground(Color.WHITE);
	}

	public char getChar()
	{
		return letter.getText().equals("") ? '\u0000' : letter.getText().charAt(0);
	}

	public void setChar(char c)
	{
		letter.setText(new StringBuilder().append(c).toString());
	}

	public int getI()
	{
		return i;
	}

	public void setI(int i)
	{
		this.i = i;
	}

	public int getJ()
	{
		return j;
	}

	public void setJ(int j)
	{
		this.j = j;
	}

	public int getNumber()
	{
		return Integer.valueOf(number.getText());
	}

	public void setNumber(int number)
	{
		this.number.setText(String.valueOf(number));
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

        number = new javax.swing.JLabel();
        letter = new javax.swing.JLabel();

        setBackground(new Color(0, 0, 0));
        setMinimumSize(new java.awt.Dimension(30, 30));
        setPreferredSize(new java.awt.Dimension(30, 30));

        number.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        number.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(number)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(letter)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(number)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(letter)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel letter;
    private javax.swing.JLabel number;
    // End of variables declaration//GEN-END:variables
}