package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class DagligFast extends Ordination {
	Dosis[] dosiser;

	public DagligFast(LocalDate startDen, LocalDate slutDen, Patient patient) {
		super(startDen, slutDen, patient);
		dosiser = new Dosis[4];
	}

	public Dosis[] getDoser() {
		return dosiser;
	}

	public void createDosis(LocalTime tid, double antal) {
		Dosis dosis = new Dosis(tid, antal);
		boolean found = false;
		for (int i = 0; i < dosiser.length && found != true; i++) {
			if (dosiser[i] == null) {
				dosiser[i] = dosis;
				found = true;
			}
		}
	}

	@Override
	public double samletDosis() {
		return doegnDosis() * ChronoUnit.DAYS.between(getStartDen(), getSlutDen());
	}

	@Override
	public double doegnDosis() {
		double antal = 0;
		for (int i = 0; i < dosiser.length; i++) {
			if (dosiser[i] != null) {
				antal += dosiser[i].getAntal();
			}
		}
		return antal;
	}

	@Override
	public String getType() {
		return getLaegemiddel().getEnhed();
	}
}
