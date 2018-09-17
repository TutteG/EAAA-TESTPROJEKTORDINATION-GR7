package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public abstract class Ordination {
	private LocalDate startDen;
	private LocalDate slutDen;
	private Laegemiddel laegemiddel;
	private Patient patient;

	public Ordination(LocalDate startDen, LocalDate slutDen, Patient patient) {
		this.startDen = startDen;
		this.slutDen = slutDen;
		this.laegemiddel = null;
		this.patient = patient;
		patient.addOrdination(this);
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public void setSlutDen(LocalDate slutDen) {
		this.slutDen = slutDen;
	}

	public Laegemiddel getLaegemiddel() {
		return laegemiddel;
	}

	public void setLaegemiddel(Laegemiddel laegemiddel) {
		this.laegemiddel = laegemiddel;
	}

	public LocalDate getStartDen() {
		return startDen;
	}

	public LocalDate getSlutDen() {
		return slutDen;
	}

	/**
	 * Antal hele dage mellem startdato og slutdato. Begge dage inklusive.
	 *
	 * @return antal dage ordinationen gælder for
	 */
	public int antalDage() {
		return (int) ChronoUnit.DAYS.between(startDen, slutDen) + 1;
	}

	@Override
	public String toString() {
		return startDen.toString();
	}

	public double getAnbefaldetDosis() {
		if (laegemiddel != null) {
			double dosis;
			double vaegt = patient.getVaegt();
			if (vaegt < 25.0) {
				dosis = vaegt * laegemiddel.getEnhedPrKgPrDoegnLet();
			} else if (vaegt > 120.0) {
				dosis = vaegt * laegemiddel.getEnhedPrKgPrDoegnTung();
			} else {
				dosis = vaegt * laegemiddel.getEnhedPrKgPrDoegnNormal();
			}
			return dosis;
		}
		return 0.0;

	}

	/**
	 * Returnerer den totale dosis der er givet i den periode ordinationen er gyldig
	 *
	 * @return
	 */
	public abstract double samletDosis();

	/**
	 * Returnerer den gennemsnitlige dosis givet pr dag i den periode ordinationen
	 * er gyldig
	 *
	 * @return
	 */
	public abstract double doegnDosis();

	/**
	 * Returnerer ordinationstypen som en String
	 *
	 * @return
	 */
	public abstract String getType();
}
