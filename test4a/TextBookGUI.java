/* Aaron Horler - s3481341 */

package test4a;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;

@SuppressWarnings("serial")
public class TextBookGUI extends JFrame implements ActionListener{
	//ArrayList
	private ArrayList<TextBook> textbooks;
	
	//GUI components
	private JPanel pnlTextBooks, pnlTextBookNorth, pnlTextBookSouth;
	
	//Textbook panel
	private JLabel lblTitle, lblAuthors, lblYear, lblPrice;
	private JTextField txtTitle, txtAuthors, txtYear, txtPrice;
	private JList<TextBook> listTextBooks;
	private DefaultListModel<TextBook> modelTextBooks;
	private JScrollPane scrollTextBooks;
	private JButton btnAddBook, btnDisplayBooks, btnRemoveRepeats, btnSortByYear, btnSortByPrice;
	
	public TextBookGUI(){
		super("Text Books");
		textbooks = new ArrayList<TextBook>();
		
		//Load data
		TextBook t1 = new TextBook("Geography", "Jane Smith", 2009, 43.95);
		textbooks.add(t1);
		textbooks.add(t1); //intentional duplicate
		TextBook t2 = new TextBook("Law", "John Smith", 2003, 199.99);
		textbooks.add(t2);
		TextBook t3 = new TextBook("Java in Linux", "C. Evans", 2014, 12.99);
		textbooks.add(t3);
		
		//Main
		pnlTextBooks = new JPanel();
		pnlTextBooks.setLayout(new BorderLayout());
		
		//North
		pnlTextBookNorth = new JPanel();
		pnlTextBookNorth.setLayout(new GridLayout(4, 1, 10, 10));
		pnlTextBookNorth.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		lblTitle = new JLabel("Title: ");
		lblAuthors = new JLabel("Authors: ");
		lblYear = new JLabel("Year: ");
		lblPrice = new JLabel("Price: ");
		txtTitle = new JTextField(30);
		txtAuthors = new JTextField(30);
		txtYear = new JTextField(30);
		txtPrice = new JTextField(30);
		pnlTextBookNorth.add(lblTitle);
		pnlTextBookNorth.add(txtTitle);
		pnlTextBookNorth.add(lblAuthors);
		pnlTextBookNorth.add(txtAuthors);
		pnlTextBookNorth.add(lblYear);
		pnlTextBookNorth.add(txtYear);
		pnlTextBookNorth.add(lblPrice);
		pnlTextBookNorth.add(txtPrice);
		pnlTextBooks.add(pnlTextBookNorth, BorderLayout.NORTH);
		
		//South
		pnlTextBookSouth = new JPanel();
		pnlTextBookSouth.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		modelTextBooks = new DefaultListModel<TextBook>();
		listTextBooks = new JList<TextBook>(modelTextBooks);
		listTextBooks.setBorder(new TitledBorder("List of Text Books"));
		scrollTextBooks = new JScrollPane(listTextBooks);
		scrollTextBooks.setPreferredSize(new Dimension(660, 400));
		btnAddBook = new JButton("Add a Book");
		btnDisplayBooks = new JButton("Display Books");
		btnRemoveRepeats = new JButton("Remove repeats");
		btnSortByYear = new JButton("Sort by year");
		btnSortByPrice = new JButton("Sort by price");
		pnlTextBookSouth.add(scrollTextBooks);
		pnlTextBookSouth.add(btnAddBook);
		pnlTextBookSouth.add(btnDisplayBooks);
		pnlTextBookSouth.add(btnRemoveRepeats);
		pnlTextBookSouth.add(btnSortByYear);
		pnlTextBookSouth.add(btnSortByPrice);
		pnlTextBooks.add(pnlTextBookSouth, BorderLayout.CENTER);
		btnAddBook.addActionListener(this);
		btnDisplayBooks.addActionListener(this);
		btnRemoveRepeats.addActionListener(this);
		btnSortByYear.addActionListener(this);
		btnSortByPrice.addActionListener(this);
		
		//Add it all
		add(pnlTextBooks);
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == btnAddBook){
			try{
				String title = txtTitle.getText();
				String authors = txtAuthors.getText();
				int year = Integer.parseInt(txtYear.getText());
				double price = Double.parseDouble(txtPrice.getText());
				
				TextBook tb = new TextBook(title, authors, year, price);
				textbooks.add(tb);
			}
			catch(Exception ex){
				JOptionPane.showMessageDialog(new JFrame(), ex, "Input Error", JOptionPane.WARNING_MESSAGE);
			}
		}
		else if(e.getSource() == btnDisplayBooks){
			modelTextBooks.clear();
			for(TextBook tb: textbooks){
				modelTextBooks.addElement(tb);
			}
		}
		else if(e.getSource() == btnRemoveRepeats){
			/* Here I'm creating a HashSet, clearing the ArrayList, and repopulating it from HashSet. */
			modelTextBooks.clear();
			Set<TextBook> set = new HashSet<TextBook>(textbooks);
			
			textbooks.clear();
			for(TextBook h: set){
				textbooks.add(h);
				modelTextBooks.addElement(h);
			}
		}
		else if(e.getSource() == btnSortByYear){
			modelTextBooks.clear();
			Collections.sort(textbooks, new YearComparator());
			for(TextBook c: textbooks){
				modelTextBooks.addElement(c);
			}
		}
		else if(e.getSource() == btnSortByPrice){
			modelTextBooks.clear();
			Collections.sort(textbooks, new PriceComparator());
			for(TextBook c: textbooks){
				modelTextBooks.addElement(c);
			}
		}
	}
	
	public static void main(String[] args){
		TextBookGUI fr = new TextBookGUI();
		fr.setSize(705, 605);
		fr.setVisible(true);
		fr.setLocationRelativeTo(null);
		fr.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	/* Alternative solution */
	public class PriceComparator implements Comparator<TextBook>{
		public int compare(TextBook t1, TextBook t2){
			return Double.compare(t1.getPrice(), t2.getPrice());
		}
	}
	
	/* Alternative solution */
	public class YearComparator implements Comparator<TextBook>{
		public int compare(TextBook t1, TextBook t2){
			return Double.compare(t1.getYear(), t2.getYear());
		}
	}
}
