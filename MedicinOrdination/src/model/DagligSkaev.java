package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class DagligSkaev extends Ordination {
	private ArrayList<Dosis> dosiser;

	public DagligSkaev(LocalDate startDen, LocalDate slutDen, Laegemiddel laegemiddel) {
		super(startDen, slutDen, laegemiddel);
		// TODO Auto-generated constructor stub
		dosiser = new ArrayList<Dosis>();
	}

	public void opretDosis(LocalTime tid, double antal) {
		// TODO
		Dosis dosis = new Dosis(tid, antal);
		dosiser.add(dosis);
	}

	public ArrayList<Dosis> getDoser() {
		return dosiser;
	}

	@Override
	public double samletDosis() {
		// TODO Auto-generated method stub
		double antal = 0;
		for (Dosis dosis : dosiser) {
			antal += dosis.getAntal();
		}
		return antal;
	}

	@Override
	public double doegnDosis() {
		// TODO Auto-generated method stub
		return doegnDosis() * ChronoUnit.DAYS.between(getStartDen(), getSlutDen());
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return getLaegemiddel().getEnhed();
	}
}
