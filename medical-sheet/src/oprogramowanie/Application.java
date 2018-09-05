package oprogramowanie;

import java.awt.Color;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;

import com.toedter.calendar.JDateChooser;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * Class limits signs in textBoxes.
 */
class JTextFieldLimit extends PlainDocument {

	/**
	 * The serialization runtime associates with each serializable class a version
	 * number, called a serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	private int limit;

	/**
	 * @param limit
	 *            Limit of signs.
	 */
	JTextFieldLimit(int limit) {
		super();
		this.limit = limit;
	}

	@Override
	public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
		if (str == null) {
			return;
		}

		if ((getLength() + str.length()) <= limit) {
			super.insertString(offset, str, attr);
		}
	}
}

/**
 * @since 2018-04-15
 * @author Krzysztof Szewczyk, Katarzyna Wójcik
 *
 */
public class Application extends JFrame implements ActionListener, KeyListener, ListSelectionListener {

	/**
	 * The serialization runtime associates with each serializable class a version
	 * number, called a serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	// list of patients
	private List<Patient> list = new ArrayList<>();

	/**
	 * Constructor <code>Application()</code> calls a function
	 * <code>initComponents()</code>, which create jFrame.
	 */
	public Application() {

		this.initComponents();
	}

	/**
	 * Create jFrame.
	 */
	public void initComponents() {

		this.setTitle("Rejestracja wyników badañ");

		// Disable editing of anything in the beginning
		this.disableEditing();

		// JMenuBar
		this.setJMenuBar(menuBar);
		this.subMenuZamknij.addActionListener(this);
		this.table.getSelectionModel().addListSelectionListener(this);

		/*
		 * LAYOUTS
		 */
		// JFrame layout
		GroupLayout layout = new GroupLayout(getContentPane());
		this.getContentPane().setLayout(layout);
		// horizontal layout
		layout.setHorizontalGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup().addComponent(
				panelTopLeft).addComponent(panelBottomLeft)).addComponent(panelRight));
		// vertical layout
		layout.setVerticalGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup().addGroup(layout
				.createSequentialGroup().addComponent(panelTopLeft).addComponent(panelBottomLeft)).addComponent(
						panelRight)));

		// panel's frames
		this.panelTopLeft.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(
				BevelBorder.RAISED), "Dane pacjenta", TitledBorder.LEFT, TitledBorder.TOP));
		this.add(panelTopLeft);

		this.panelBottomLeft.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(
				BevelBorder.RAISED), "Badanie", TitledBorder.LEFT, TitledBorder.TOP));
		this.add(panelBottomLeft);

		this.panelRight.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED),
				"Lista pacjentów", TitledBorder.LEFT, TitledBorder.TOP));
		this.add(panelRight);

		/*
		 * PanelTopLeft
		 */
		// radioButtons settings
		this.radioButtons.add(radioKobieta);
		this.radioKobieta.setActionCommand("K");
		this.radioButtons.add(radioMezczyzna);
		this.radioMezczyzna.setActionCommand("M");
		// comboUbezpieczenie settings
		this.comboUbezpieczenie.addItem("NFZ");
		this.comboUbezpieczenie.addItem("Prywatne");
		this.comboUbezpieczenie.addItem("Brak");
		this.comboUbezpieczenie.setBackground(Color.WHITE);
		// adding listeners
		this.textImie.addKeyListener(this);
		this.textNazwisko.addKeyListener(this);
		this.textPesel.addKeyListener(this);
		this.buttonZapisz.addActionListener(this);
		this.buttonAnuluj.addActionListener(this);
		// limit for patient's pesel
		this.textPesel.setDocument(new JTextFieldLimit(11));

		// panelTopLeft layout
		GroupLayout layoutTopLeft = new GroupLayout(panelTopLeft);
		this.panelTopLeft.setLayout(layoutTopLeft);
		// horizontal layout
		layoutTopLeft.setHorizontalGroup(layoutTopLeft.createSequentialGroup().addGroup(layoutTopLeft
				.createParallelGroup().addComponent(labelImie).addComponent(labelNazwisko).addComponent(labelPesel)
				.addComponent(labelPlec).addComponent(labelUbezpieczenie).addGroup(layoutTopLeft.createSequentialGroup()
						.addComponent(buttonZapisz).addComponent(buttonAnuluj))).addGroup(layoutTopLeft
								.createParallelGroup().addComponent(textImie).addComponent(textNazwisko).addComponent(
										textPesel).addGroup(layoutTopLeft.createSequentialGroup().addComponent(
												radioKobieta).addComponent(labelKobieta).addComponent(radioMezczyzna)
												.addComponent(labelMezczyzna)).addComponent(comboUbezpieczenie)));
		// vertical layout
		layoutTopLeft.setVerticalGroup(layoutTopLeft.createSequentialGroup().addGroup(layoutTopLeft
				.createParallelGroup().addComponent(labelImie).addComponent(textImie)).addGroup(layoutTopLeft
						.createParallelGroup().addComponent(labelNazwisko).addComponent(textNazwisko)).addGroup(
								layoutTopLeft.createParallelGroup().addComponent(labelPesel).addComponent(textPesel))
				.addGroup(layoutTopLeft.createParallelGroup().addComponent(labelPlec).addComponent(radioKobieta)
						.addComponent(labelKobieta).addComponent(radioMezczyzna).addComponent(labelMezczyzna)).addGroup(
								layoutTopLeft.createParallelGroup().addComponent(labelUbezpieczenie).addComponent(
										comboUbezpieczenie)).addGroup(layoutTopLeft.createParallelGroup().addComponent(
												buttonZapisz).addComponent(buttonAnuluj)));

		/*
		 * panelBottomLeft
		 */
		// textBoxes limit settings
		this.textParametr1.setDocument(new JTextFieldLimit(4));
		this.textParametr2.setDocument(new JTextFieldLimit(3));
		this.textParametr3.setDocument(new JTextFieldLimit(5));
		// adding listeners
		this.textParametr1.addKeyListener(this);
		this.textParametr2.addKeyListener(this);
		this.textParametr3.addKeyListener(this);
		this.buttonZapisz2.addActionListener(this);
		this.buttonAnuluj2.addActionListener(this);

		// panelBottomLeft layout
		GroupLayout layoutBottomLeft = new GroupLayout(panelBottomLeft);
		this.panelBottomLeft.setLayout(layoutBottomLeft);
		// horizontal layout
		layoutBottomLeft.setHorizontalGroup(layoutBottomLeft.createSequentialGroup().addGroup(layoutBottomLeft
				.createParallelGroup().addComponent(labelData).addComponent(labelParametr1).addComponent(labelParametr2)
				.addComponent(labelParametr3).addGroup(layoutBottomLeft.createSequentialGroup().addComponent(
						buttonZapisz2).addComponent(buttonAnuluj2))).addGroup(layoutBottomLeft.createParallelGroup()
								.addComponent(calendar).addComponent(textParametr1).addComponent(textParametr2)
								.addComponent(textParametr3)));
		// vertical layout
		layoutBottomLeft.setVerticalGroup(layoutBottomLeft.createSequentialGroup().addGroup(layoutBottomLeft
				.createParallelGroup().addComponent(labelData).addComponent(calendar)).addGroup(layoutBottomLeft
						.createParallelGroup().addComponent(labelParametr1).addComponent(textParametr1)).addGroup(
								layoutBottomLeft.createParallelGroup().addComponent(labelParametr2).addComponent(
										textParametr2)).addGroup(layoutBottomLeft.createParallelGroup().addComponent(
												labelParametr3).addComponent(textParametr3)).addGroup(layoutBottomLeft
														.createParallelGroup().addComponent(buttonZapisz2).addComponent(
																buttonAnuluj2)));

		/*
		 * panelRight
		 */
		// JTable settings
		// selecting only one whole row
		this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// disable moving table headers
		this.table.getTableHeader().setReorderingAllowed(false);
		// disable editing cells
		this.table.setDefaultEditor(Object.class, null);
		// table's components
		this.table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		// making checkBox in the last column
		TableColumn tableColumn = table.getColumnModel().getColumn(4);
		tableColumn.setCellEditor(table.getDefaultEditor(Boolean.class));
		tableColumn.setCellRenderer(table.getDefaultRenderer(Boolean.class));

		this.table.setBackground(Color.lightGray);
		this.table.setForeground(Color.BLACK);
		Font font = new Font("", 2, 12);
		this.table.setFont(font);
		this.table.setRowHeight(30);
		this.table.addKeyListener(this);

		// adding listeners
		this.buttonDodaj.addActionListener(this);
		this.buttonUsun.addActionListener(this);

		// panelRight layout
		GroupLayout layoutRight = new GroupLayout(panelRight);
		this.panelRight.setLayout(layoutRight);
		// horizontal layout
		layoutRight.setHorizontalGroup(layoutRight.createSequentialGroup().addGroup(layoutRight.createParallelGroup()
				.addComponent(scrollPaneTable).addGroup(layoutRight.createSequentialGroup().addComponent(buttonDodaj)
						.addComponent(buttonUsun))));
		// vertical layout
		layoutRight.setVerticalGroup(layoutRight.createSequentialGroup().addComponent(scrollPaneTable).addGroup(
				layoutRight.createParallelGroup().addComponent(buttonDodaj).addComponent(buttonUsun)));

		this.pack();
		this.setLocationRelativeTo(null);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	// Components of frame
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menuAplikacja = menuBar.add(new JMenu("Aplikacja"));
	private JMenuItem subMenuZamknij = menuAplikacja.add(new JMenuItem("Zamknij"));
	// Components of panelTopLeft
	private JPanel panelTopLeft = new JPanel();
	private JLabel labelImie = new JLabel("Imiê:");
	private JLabel labelNazwisko = new JLabel("Nazwisko:");
	private JLabel labelPesel = new JLabel("PESEL:");
	private JLabel labelPlec = new JLabel("P³eæ:");
	private JLabel labelUbezpieczenie = new JLabel("Ubezpieczenie:");
	private JTextField textImie = new JTextField();
	private JTextField textNazwisko = new JTextField();
	private JTextField textPesel = new JTextField();
	private JRadioButton radioKobieta = new JRadioButton();
	private JLabel labelKobieta = new JLabel("Kobieta");
	private JRadioButton radioMezczyzna = new JRadioButton();
	private JLabel labelMezczyzna = new JLabel("Mê¿czyzna");
	private ButtonGroup radioButtons = new ButtonGroup();
	private JComboBox<String> comboUbezpieczenie = new JComboBox<String>();
	private JButton buttonZapisz = new JButton("Zapisz");
	private JButton buttonAnuluj = new JButton("Anuluj");
	// Components of panelBottomLeft
	private JPanel panelBottomLeft = new JPanel();
	private final JLabel labelData = new JLabel("Data:");
	private final JLabel labelParametr1 = new JLabel("Temperatura cia³a [st. C]:");
	private final JLabel labelParametr2 = new JLabel("Têtno [têtnienia/min]:");
	private final JLabel labelParametr3 = new JLabel("Liczba leukocytów [leukocyty/mm^3]:");
	private JDateChooser calendar = new JDateChooser();
	private JTextField textParametr1 = new JTextField();
	private JTextField textParametr2 = new JTextField();
	private JTextField textParametr3 = new JTextField();
	private JButton buttonZapisz2 = new JButton("Zapisz");
	private JButton buttonAnuluj2 = new JButton("Anuluj");
	// Components of panelRight
	private JPanel panelRight = new JPanel();
	private JButton buttonDodaj = new JButton("Dodaj");
	private JButton buttonUsun = new JButton("Usuñ");
	// tableModel
	private TableModel tableModel = new TableModel(list);
	private JTable table = new JTable(tableModel);
	private JScrollPane scrollPaneTable = new JScrollPane(table);

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Application().setVisible(true);
			}
		});
	}

	/*
	 * Methods
	 */
	/**
	 * @param sign
	 *            Checks, if <code>char</code> is a letter (inclusive polish
	 *            letters: ifLettersPolish)
	 * @return <code>true</code> if char is a letter
	 */
	public boolean ifLetters(char sign) {
		if (!((sign >= 'A' && sign <= 'Z') || (sign >= 'a' && sign <= 'z') || sign == '-' || ifLettersPolish(sign))) {
			return false;
		}
		return true;
	}

	/**
	 * Seek for: ¥ ¹ Ê ê £ ³ ¯ ¿  Ÿ Ó ó Æ æ Œ æ Ñ ñ
	 * 
	 * @param sign
	 *            Checks, if <code>char</code> is a polish letter.
	 * @return <code>true</code> if char is a polish letter
	 */
	private boolean ifLettersPolish(char sign) {
		int[] polishLetters = { 260, 262, 280, 321, 323, 211, 346, 377, 379, 261, 263, 281, 322, 324, 243, 347, 378,
				380 };
		for (int i : polishLetters) {
			if (i == sign) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @param sign
	 *            Checks, if <code>char</code> is a number.
	 * @return <code>true</code> if char is a number
	 */
	private boolean ifNumbers(char sign) {
		if (!(sign >= '0' && sign <= '9')) {
			return false;
		}
		return true;
	}

	/**
	 * Disables left panels.
	 */
	private void disableEditing() {
		this.textImie.setEnabled(false);
		this.textNazwisko.setEnabled(false);
		this.textPesel.setEnabled(false);
		this.radioKobieta.setEnabled(false);
		this.radioMezczyzna.setEnabled(false);
		this.comboUbezpieczenie.setEnabled(false);
		this.buttonZapisz.setEnabled(false);
		this.buttonAnuluj.setEnabled(false);
		this.calendar.setEnabled(false);
		this.textParametr1.setEnabled(false);
		this.textParametr2.setEnabled(false);
		this.textParametr3.setEnabled(false);
		this.buttonZapisz2.setEnabled(false);
		this.buttonAnuluj2.setEnabled(false);
	}

	/**
	 * Enables left top panel.
	 */
	private void enableEditingPanelTopLeft() {
		this.textImie.setEnabled(true);
		this.textNazwisko.setEnabled(true);
		this.textPesel.setEnabled(true);
		this.radioKobieta.setEnabled(true);
		this.radioMezczyzna.setEnabled(true);
		this.comboUbezpieczenie.setEnabled(true);
		this.buttonZapisz.setEnabled(true);
		this.buttonAnuluj.setEnabled(true);
	}

	/**
	 * Enables left bottom panel.
	 */
	private void enableEditingPanelBottomLeft() {
		this.calendar.setEnabled(true);
		this.textParametr1.setEnabled(true);
		this.textParametr2.setEnabled(true);
		this.textParametr3.setEnabled(true);
		this.buttonZapisz2.setEnabled(true);
		this.buttonAnuluj2.setEnabled(true);
	}

	// ActionListener
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.subMenuZamknij) {
			System.exit(0); // or dispose();?
		}
		if (e.getSource() == this.buttonZapisz) {
			if (!textImie.getText().equals("") && !textNazwisko.getText().equals("") && textPesel.getText()
					.length() == 11 && (radioKobieta.isSelected() || radioMezczyzna.isSelected())) {

				String imie = this.textImie.getText();
				String nazwisko = this.textNazwisko.getText();
				String pesel = this.textPesel.getText();
				String plec = this.radioButtons.getSelection().getActionCommand();
				String ubezpieczenie = this.comboUbezpieczenie.getSelectedItem().toString();

				int selectedRow = this.table.getSelectedRow();
				System.out.println(selectedRow);
				Patient patient;
				Patient p = new Patient(); // testowy pacjent (pesel)
				p.setPesel(pesel);
				
					if (selectedRow == -1 && !list.contains(p)) {
						// robimy nowego pacjenta
						patient = new Patient(imie, nazwisko, pesel, plec, ubezpieczenie);
						this.tableModel.addPatient(patient);
						// i zaznaczamy go
						this.table.getSelectionModel().setSelectionInterval(list.size() - 1, list.size() - 1);
						// enableEditingPanelBottomLeft() jest w valueChanged().
					} else if (selectedRow > -1 && (!list.contains(p) || p.getPesel().equals(this.list.get(selectedRow).getPesel()))){
						// edytujemy istniej¹cego pacjenta
						patient = this.list.get(selectedRow);
						tableModel.setValueAt(imie, selectedRow, 0);
						tableModel.setValueAt(nazwisko, selectedRow, 1);
						tableModel.setValueAt(plec, selectedRow, 2);
						tableModel.setValueAt(pesel, selectedRow, 3);
						tableModel.setValueAt(ubezpieczenie, selectedRow, 4);

						JOptionPane.showMessageDialog(null, "Poprawnie wprowadzono zmianê danych.");
					} else {
					JOptionPane.showMessageDialog(null, "Podany numer PESEL ju¿ istnieje w bazie.");
				}

			} else if (!textImie.getText().equals("") && !textNazwisko.getText().equals("") && (radioKobieta
					.isSelected() || radioMezczyzna.isSelected())) {
				JOptionPane.showMessageDialog(null, "Uzupe³nij numer PESEL.");
			} else {
				JOptionPane.showMessageDialog(null, "Uzupe³nij wszystkie dane.");
			}
		}

		if (e.getSource() == this.buttonAnuluj) {
			// sets default values of components
			this.textImie.setText("");
			this.textNazwisko.setText("");
			this.textPesel.setText("");
			this.radioButtons.clearSelection();
			this.comboUbezpieczenie.setSelectedItem("NFZ");
		}

		if (e.getSource() == this.buttonDodaj) {

			this.table.getSelectionModel().clearSelection();

			// and cleans textBoxes
			this.buttonAnuluj.doClick(0);
			this.buttonAnuluj2.doClick(0);
			this.disableEditing();
			this.enableEditingPanelTopLeft();
		}

		if (e.getSource() == this.buttonUsun) {
			int selectedRowIndex = this.table.getSelectedRow();
			// deleting patient
			if (selectedRowIndex >= 0) {
				// delete selected patient
				tableModel.removePatient(selectedRowIndex);
			} else {
				System.out.println("End of the list.");
			}
			// clean forms
			this.buttonAnuluj.doClick(0);
			this.buttonAnuluj2.doClick(0);
			// disable editing textBoxes and other components
			disableEditing();
		}

		if (e.getSource() == this.buttonZapisz2) {
			if (!(this.textParametr1.getText().equals("") || this.textParametr2.getText().equals("")
					|| this.textParametr3.getText().equals(""))) {
				if (this.textParametr1.getText().charAt(0) == '.') {
					// set 0 before dot
					this.textParametr1.setText("0" + this.textParametr1.getText());
				}
				if (this.calendar.getDate() != null) {

					double temperatura = Double.parseDouble(textParametr1.getText());
					int puls = Integer.parseInt(textParametr2.getText());
					int leukocyty = Integer.parseInt(textParametr3.getText());
					int selectedRowIndex = this.table.getSelectedRow();

					// sets new examination of the patient
					Patient patient = this.list.get(selectedRowIndex);
					CheckUp checkUp = new CheckUp(temperatura, puls, leukocyty, this.calendar.getDate());
					patient.setCheckUp(checkUp);
					tableModel.setValueAt(patient.getHaveCheckUp(), selectedRowIndex, 5);
				} else {
					JOptionPane.showMessageDialog(null, "Wpisz datê badania.");
				}
			} else {
				JOptionPane.showMessageDialog(null, "Wpisz wszystkie wyniki badañ.");
			}
		}

		if (e.getSource() == buttonAnuluj2) {
			// sets default values in panelBottomLeft
			calendar.setCalendar(null);
			textParametr1.setText("");
			textParametr2.setText("");
			textParametr3.setText("");
		}
	}

	// KeyListener
	@Override
	public void keyPressed(KeyEvent e) {
		// Checking flavor on pasting data.
		System.out.println("Obs³uga wklejania liter.");
		if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_V) {
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			DataFlavor flavor = DataFlavor.stringFlavor;
			String schowek = ""; // empty String
			try {
				schowek = (String) clipboard.getData(flavor);
			} catch (UnsupportedFlavorException ex) {
				System.out.println("Wklejony zosta³ z³y format.");
			} catch (IOException ex) {
				System.out.println("B³¹d wejœcia/wyjœcia.");
			}
			// if String, check numbers/letters
			for (int i = 0; i < schowek.length(); i++) {
				if (e.getSource() == this.textImie || e.getSource() == this.textNazwisko) {
					if (!ifLetters(schowek.charAt(i))) {
						System.out.println("Wklejanie cyfr jest na tym polu niedozwolone.");
						e.consume();
						break;
					}
				} else if (e.getSource() == this.textPesel || e.getSource() == this.textParametr1 || e
						.getSource() == this.textParametr2 || e.getSource() == this.textParametr3) {
					if (!ifNumbers(schowek.charAt(i))) {
						System.out.println("Wklejanie liter jest na tym polu niedozwolone. ");
						e.consume();
						break;
					}
				}
			}

		}
		if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE && e.getSource() == this.textParametr1) {
			this.textParametr1.setText("");
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// check if letters
		if (e.getSource() == this.textImie || e.getSource() == this.textNazwisko) {
			if (!(ifLetters(e.getKeyChar()))) {
				e.consume();
			}
		}

		// check if numberq
		if (e.getSource() == this.textPesel || e.getSource() == this.textParametr2 || e
				.getSource() == this.textParametr3) {
			if (!(ifNumbers(e.getKeyChar()))) {
				e.consume();
			}
			// check if number or dot ('.') (dot cannot be in the beginning)
			// cannot exist more than one dot
		} else if (e.getSource() == this.textParametr1) {
			if (!ifNumbers(e.getKeyChar())) {
				e.consume();
			} else {
				if (this.textParametr1.getText().length() == 2) {
					this.textParametr1.setText(this.textParametr1.getText() + ".");
				}
			}

		}

	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		int selectedRow = this.table.getSelectedRow();
		if (selectedRow > -1) {
			Patient patient = this.list.get(selectedRow);
			this.buttonAnuluj2.doClick(0);
			// set data to the forms
			this.textImie.setText(patient.getImie());
			this.textNazwisko.setText(patient.getNazwisko());
			this.textPesel.setText(patient.getPesel());
			// gender
			this.radioKobieta.setSelected(patient.getPlec().equals("K"));
			this.radioMezczyzna.setSelected(patient.getPlec().equals("M"));
			// insurance
			this.comboUbezpieczenie.setSelectedItem(patient.getUbezpieczenie());
			// Fills panelBottomLeft
			if (patient.checkUp != null) {
				this.calendar.setDate(patient.checkUp.getDate());
				this.textParametr1.setText(patient.checkUp.getTemperature() + "");
				this.textParametr2.setText(patient.checkUp.getPulse() + "");
				this.textParametr3.setText(patient.checkUp.getLeucocytes() + "");
			}
			// enable editing
			this.enableEditingPanelTopLeft();
			this.enableEditingPanelBottomLeft();
		}
	}

}
