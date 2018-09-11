package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class PN extends Ordination {

	private double antalEnheder;
	private int antalGangeGivet;

	public PN(LocalDate startDen, LocalDate slutDen, Laegemiddel laegemiddel, Patient patient, double antalEnheder) {
		super(startDen, slutDen, laegemiddel, patient);
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
		if ((givesDen.isAfter(getStartDen()) || givesDen.equals(getStartDen()))
				&& (givesDen.isBefore(getSlutDen()) || givesDen.isEqual(getSlutDen()))) {
			antalGangeGivet++;
			return true;
		}
		return false;
	}

	@Override
	public double doegnDosis() {
		return (antalGangeGivet * antalEnheder) / ChronoUnit.DAYS.between(getStartDen(), getSlutDen()); //LocalDate.now()?
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
