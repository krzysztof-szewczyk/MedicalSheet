
package oprogramowanie;

/**
 * Object <code>Patient</code> represents patient.
 * 
 * @author Krzysztof Szewczyk, Katarzyna Wójcik
 */
public class Patient {

	// attributes of patient
	/**
	 * Patient's Name
	 */
	private String imie;
	/**
	 * Patient's surname.
	 */
	private String nazwisko;
	/**
	 * Patient's pesel (ID number).
	 */
	private String pesel;
	/**
	 * Patient's gender.
	 */
	private String plec;
	/**
	 * Patient's insurance.
	 */
	private String ubezpieczenie;
	/**
	 * Patient's checkUp.
	 */
	CheckUp checkUp;
	
	boolean haveCheckUp = false;

	Patient() {
	}
	Patient(String imie, String nazwisko, String pesel, String plec, String ubezpieczenie) {
		System.out.println("New patient");
		setImie(imie);
		setNazwisko(nazwisko);
		setPesel(pesel);
		setPlec(plec);
		setUbezpieczenie(ubezpieczenie);
	}

	/**
	 * @param imie
	 *            Sets patient's name.
	 */
	void setImie(String imie) {
		this.imie = imie;
	}

	/**
	 * Gets patient's name.
	 * 
	 * @return imie
	 */
	String getImie() {
		return this.imie;
	}

	/**
	 * @param nazwisko
	 *            Sets patient's surname.
	 */
	void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	/**
	 * Gets patient's surname.
	 * 
	 * @return nazwisko
	 */
	String getNazwisko() {
		return this.nazwisko;

	}

	/**
	 * @param plec
	 *            Sets patient's gender.
	 */
	void setPlec(String plec) {
		this.plec = plec;
	}

	/**
	 * Gets patient's gender.
	 * 
	 * @return plec
	 */
	String getPlec() {
		return this.plec;
	}

	/**
	 * @param pesel
	 *            Sets patient's pesel (ID number).
	 */
	void setPesel(String pesel) {
		this.pesel = pesel;

	}

	/**
	 * Gets patient's pesel (ID number).
	 * 
	 * @return pesel
	 */
	String getPesel() {
		return this.pesel;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pesel == null) ? 0 : pesel.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Patient other = (Patient) obj;
		if (pesel == null) {
			if (other.pesel != null)
				return false;
		} else if (!pesel.equals(other.pesel))
			return false;
		return true;
	}

	/**
	 * @param ubezpieczenie
	 *            Sets patient's insurance.
	 */
	void setUbezpieczenie(String ubezpieczenie) {
		this.ubezpieczenie = ubezpieczenie;
	}

	/**
	 * Gets patient's insurance.
	 * 
	 * @return ubezpieczenie
	 */
	String getUbezpieczenie() {
		return this.ubezpieczenie;
	}
	
	/**
	 * Ustawia dane badania pacjenta
	 * @param checkUp
	 */
	public void setCheckUp(CheckUp checkUp){

		this.checkUp = checkUp;
		setHaveCheckUp(true);
	}
	
	/**
	 * 
	 * @return boolean czy ustawiono badanie
	 */
	public boolean getHaveCheckUp() {
		return haveCheckUp;
	}
	
	/**
	 * Ustawia badnie pacjenta (boolean)
	 * @param haveCheckUp boolean
	 */
	public void setHaveCheckUp(boolean haveCheckUp) {
		this.haveCheckUp = haveCheckUp;
	}	

}
