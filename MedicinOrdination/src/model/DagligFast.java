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

	public Dosis createDosis(LocalTime tid, double antal) {
		if (antal <= 0) {
			return null;
		} else {
			Dosis dosis = new Dosis(tid, antal);
			for (int i = 0; i < dosiser.length; i++) {
				if (dosiser[i] == null) {
					dosiser[i] = dosis;
					return dosis;
				}
			}
			return null;
		}
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
