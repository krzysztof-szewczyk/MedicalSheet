package oprogramowanie;

import javax.swing.table.AbstractTableModel;

import java.util.List;

public class TableModel extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String[] columnNames = { "Imiê i Nazwisko", "P³eæ", "PESEL", "Ubezpieczenie", "Badanie" };

	private List<Patient> list;

	TableModel(List<Patient> list) {
		this.list = list;
	}

	public void addPatient(Patient patient) {
		this.list.add(patient);
		fireTableDataChanged();
	}

	public void removePatient(int rowIndex) {
		this.list.remove(rowIndex);
		fireTableRowsDeleted(rowIndex, rowIndex);
	}

	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

	@Override
	public int getColumnCount() {

		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return list.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Patient patient = this.list.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return patient.getImie() + " " + patient.getNazwisko();
		case 1:
			return patient.getPlec();
		case 2:
			return patient.getPesel();
		case 3:
			return patient.getUbezpieczenie();
		case 4:
			return patient.getHaveCheckUp();
		default:
			return null; // patient
		}
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Patient patient = this.list.get(rowIndex);
		switch (columnIndex) {
		case 0:
			patient.setImie((String) aValue);
		case 1:
			patient.setNazwisko((String) aValue);
		case 2:
			patient.setPlec((String) aValue);
		case 3:
			patient.setPesel((String) aValue);
		case 4:
			patient.setUbezpieczenie((String) aValue);
			break; // musi byæ break by odseparowaæ 5 od reszty (od pierwszego poprawnego warunku
					// leci kaskadowo)
					//nr 0-4 s¹ dodawane po buttonZapisz
					//nr 5 jest dodawane po buttonZapisz2
		case 5:
			patient.setHaveCheckUp((boolean) aValue);

		}
		fireTableRowsUpdated(rowIndex, rowIndex);
	}
}
