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

	public void opretDosis(LocalTime tid, double antal) {
		// TODO retunere null hvis dosis er 0 eller under
		Dosis dosis = new Dosis(tid, antal);
		dosiser.add(dosis);
	}

	public ArrayList<Dosis> getDoser() {
		return dosiser;
	}

	@Override
	public double samletDosis() {
		// TODO Auto-generated method stub
		return doegnDosis() * ChronoUnit.DAYS.between(getStartDen(), getSlutDen());
	}

	@Override
	public double doegnDosis() {
		// TODO Auto-generated method stub
		double antal = 0;
		for (Dosis dosis : dosiser) {
			antal += dosis.getAntal();
		}
		return antal;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return getLaegemiddel().getEnhed();
	}
}
