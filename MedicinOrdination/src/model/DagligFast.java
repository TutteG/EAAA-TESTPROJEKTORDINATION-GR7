package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DagligFast extends Ordination {
	Dosis[] dosiser;

	public DagligFast(LocalDate startDen, LocalDate slutDen, Laegemiddel laegemiddel) {
		super(startDen, slutDen, laegemiddel);
		dosiser = new Dosis[4];
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
