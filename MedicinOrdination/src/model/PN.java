package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class PN extends Ordination {

	private double antalEnheder;
	private int antalGangeGivet;

	public PN(LocalDate startDen, LocalDate slutDen, Patient patient, double antalEnheder) {
		super(startDen, slutDen, patient);
		this.antalEnheder = antalEnheder;
		this.antalGangeGivet = 0;
	}

	/**
	 * Registrerer at der er givet en dosis paa dagen givesDen Returnerer true hvis
	 * givesDen er inden for ordinationens gyldighedsperiode og datoen huskes
	 * Retrurner false ellers og datoen givesDen ignoreres
	 *
	 * @param givesDen
	 * @return
	 */
	public boolean givDosis(LocalDate givesDen) {
		if (antalEnheder > 0) {
			if ((givesDen.isAfter(getStartDen()) || givesDen.equals(getStartDen()))
					&& (givesDen.isBefore(getSlutDen()) || givesDen.isEqual(getSlutDen()))) {
				antalGangeGivet++;
				return true;
			}
		}
		return false;
	}

	@Override
	public double doegnDosis() {
		try {
			return (antalGangeGivet * antalEnheder) / ChronoUnit.DAYS.between(getStartDen(), getSlutDen()); // LocalDate.now()?
		} catch (ArithmeticException e) {
			return -1;
		}
	}

	@Override
	public double samletDosis() {
		return this.antalEnheder * this.antalGangeGivet;
	}

	/**
	 * Returnerer antal gange ordinationen er anvendt
	 *
	 * @return
	 */
	public int getAntalGangeGivet() {
		return antalGangeGivet;
	}

	public double getAntalEnheder() {
		return antalEnheder;
	}

	@Override
	public String getType() {
		return getLaegemiddel().getEnhed();
	}
}
