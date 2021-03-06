package model;

import java.time.LocalTime;

public class Dosis {
	private LocalTime tid;
	private double antal;

	public Dosis(LocalTime tid, double antal) {
		this.tid = tid;
		this.antal = antal;
	}

	public double getAntal() {
		return antal;
	}

	public void setAntal(double antal) {
		this.antal = antal;
	}

	public LocalTime getTid() {
		return tid;
	}

	public void setTid(LocalTime tid) {
		this.tid = tid;
	}

	@Override
	public String toString() {
		return "Kl: " + tid + "   antal:  " + antal;
	}

	@Override
	public boolean equals(Object obj) {
		Dosis dosis = (Dosis) obj;
		if (dosis.getTid().equals(getTid()) == true && dosis.getAntal() == getAntal()) {
			return true;
		}else {
			return false;
		}
	}

}
