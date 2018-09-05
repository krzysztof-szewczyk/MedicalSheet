/**
 * 
 */
package oprogramowanie;

import java.util.Arrays;
import java.util.Date;

import javax.swing.JOptionPane;

/**
 * This class <code>CheckUp</code> cannot exist without <code>Patient</code>
 * object, but <code>Patient</code> can exist alone.
 * 
 * @author Krzysztof Szewczyk, Katarzyna Wójcik
 */
public class CheckUp {

	// protected static Object checkUpObject;

	/**
	 * Patient's temperature: XX.X [degrees C]
	 * 
	 */
	private double temperature;
	/**
	 * Patient's pulse [ripples / min]
	 */
	private int pulse;
	/**
	 * Concentration of leucocytes in the patient's blood [number of leucocytes
	 * mm^3]
	 */
	private int leucocytes;
	/**
	 * Date of patient's medical examination.
	 */
	private Date date;

	/**
	 * Constructor of CheckUp
	 * 
	 * @param temperature
	 * @param pulse
	 * @param leucocytes
	 * @param date
	 */
	CheckUp(double temperature, int pulse, int leucocytes, Date date) {
		setTemperature(temperature);
		setPulse(pulse);
		setLeucocytes(leucocytes);
		setDate(date);

		ifSick();
	}

	private void ifSick() {
		// Checking if patient is ill
		// String for commands about patient
		String[] sickness = { "", "", "" };

		// temperature
			if (temperature < 35.5) {
				sickness[0] = "Niska temperatura. ";
			} else if (temperature > 37.2) {
				sickness[0] = "Wysoka temperatura. ";
			}

		// pulse
			if (pulse < 60) {
				sickness[1] = "Niskie têtno. ";
			} else if (pulse > 120) {
				sickness[1] = "Wysokie têtno. ";
			}

		// leucocytes
			if (leucocytes < 4500) {
				sickness[2] = "Niska iloœæ leukocytów. ";
			} else if (leucocytes > 11000) {
				sickness[2] = "Du¿a iloœæ leukocytów. ";
			}

		// printing diagnosis
		String[] empty = { "", "", "" };
		if (Arrays.equals(sickness, empty)) {
			JOptionPane.showMessageDialog(null, "Pacjent jest zdrowy.");
		} else {
			for (int command = 0; command < (sickness.length); command++) {
				if (sickness[command] == null) {
					sickness[command] = "";
				}
			}
			JOptionPane.showMessageDialog(null, "Badanie zosta³o zapisane." + "\n\n"+ "Diagnoza:\n" + String.join("",sickness));
		}

	}

	/**
	 * @return the temperature
	 */
	public double getTemperature() {
		return temperature;
	}

	/**
	 * @param temperature
	 *            the temperature to set
	 */
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	/**
	 * @return the pulse
	 */
	public int getPulse() {
		return pulse;
	}

	/**
	 * @param pulse
	 *            the pulse to set
	 */
	public void setPulse(int pulse) {
		this.pulse = pulse;
	}

	/**
	 * @return the leucocytes
	 */
	public int getLeucocytes() {
		return leucocytes;
	}

	/**
	 * @param leucocytes
	 *            the leucocytes to set
	 */
	public void setLeucocytes(int leucocytes) {
		this.leucocytes = leucocytes;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
}
