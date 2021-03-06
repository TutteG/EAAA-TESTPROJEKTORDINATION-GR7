package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class DagligSkaev extends Ordination {
	private ArrayList<Dosis> dosiser;

	public DagligSkaev(LocalDate startDen, LocalDate slutDen, Patient patient) {
		super(startDen, slutDen, patient);
		// TODO Auto-generated constructor stub
		dosiser = new ArrayList<Dosis>();
	}

	public Dosis createDosis(LocalTime tid, double antal) {
		if (antal <= 0) {
			throw new RuntimeException("Antal skal være et positivt tal");
		} else {
			Dosis dosis = new Dosis(tid, antal);
			dosiser.add(dosis);
			return dosis;
		}

	}

	public ArrayList<Dosis> getDoser() {
		return dosiser;
	}

	@Override
	public double samletDosis() {
		if (ChronoUnit.DAYS.between(getStartDen(), getSlutDen()) < 0) {
			throw new RuntimeException("No es possiblé");
		} else {

			return doegnDosis() * ChronoUnit.DAYS.between(getStartDen(), getSlutDen());
		}
	}

	@Override
	public double doegnDosis() {
		double antal = 0;
		for (Dosis dosis : dosiser) {
			antal += dosis.getAntal();
		}
		return antal;
	}

	@Override
	public String getType() {

		return getLaegemiddel().getEnhed();
	}
}
