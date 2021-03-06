package controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import model.DagligFast;
import model.DagligSkaev;
import model.Laegemiddel;
import model.Ordination;
import model.PN;
import model.Patient;
import storage.Storage;

public class Controller {
	private Storage storage;
	private static Controller service;

	private Controller() {
		storage = new Storage();
	}

	public static Controller getService() {
		if (service == null) {
			service = new Controller();
		}
		return service;
	}

	public static Controller getTestService() {
		return new Controller();
	}

	/**
	 * Hvis startDato er efter slutDato kastes en IllegalArgumentException og
	 * ordinationen oprettes ikke Pre: startDen, slutDen, patient og laegemiddel er
	 * ikke null
	 *
	 * @return opretter og returnerer en PN ordination.
	 */
	public PN opretPNOrdination(LocalDate startDen, LocalDate slutDen, Patient patient, Laegemiddel laegemiddel,
			double antal) {
		if (startDen.isBefore(slutDen) || startDen.isEqual(slutDen)) {
			PN pn = new PN(startDen, slutDen, patient, antal); // Kan her godt vaere samme dato
			pn.setLaegemiddel(laegemiddel);
			return pn;
		} else {
			throw new IllegalArgumentException("Slutdato er foer startdato");
		}

	}

	/**
	 * Opretter og returnerer en DagligFast ordination. Hvis startDato er efter
	 * slutDato kastes en IllegalArgumentException og ordinationen oprettes ikke
	 * Pre: startDen, slutDen, patient og laegemiddel er ikke null
	 */
	public DagligFast opretDagligFastOrdination(LocalDate startDen, LocalDate slutDen, Patient patient,
			Laegemiddel laegemiddel, double morgenAntal, double middagAntal, double aftenAntal, double natAntal) {
		if (startDen.isAfter(slutDen)) {
			throw new IllegalArgumentException("Startdato må ikke være efter slutdato");
		} else {
			DagligFast dagligFast = new DagligFast(startDen, slutDen, patient);
			dagligFast.setLaegemiddel(laegemiddel);
			dagligFast.createDosis(LocalTime.of(8, 0), morgenAntal);
			dagligFast.createDosis(LocalTime.of(12, 0), middagAntal);
			dagligFast.createDosis(LocalTime.of(18, 0), aftenAntal);
			dagligFast.createDosis(LocalTime.of(22, 0), natAntal);
			return dagligFast;
		}
	}

	/**
	 * Opretter og returnerer en DagligSkæv ordination. Hvis startDato er efter
	 * slutDato kastes en IllegalArgumentException og ordinationen oprettes ikke.
	 * Hvis antallet af elementer i klokkeSlet og antalEnheder er forskellige kastes
	 * ogsÃ¥ en IllegalArgumentException.
	 *
	 * Pre: startDen, slutDen, patient og laegemiddel er ikke null
	 */
	public DagligSkaev opretDagligSkaevOrdination(LocalDate startDen, LocalDate slutDen, Patient patient,
			Laegemiddel laegemiddel, LocalTime[] klokkeSlet, double[] antalEnheder) {
		if (startDen.isBefore(slutDen) || startDen.isEqual(slutDen) && klokkeSlet.length == antalEnheder.length) {
			DagligSkaev dagligSkaev = new DagligSkaev(startDen, slutDen, patient);
			dagligSkaev.setLaegemiddel(laegemiddel);
			for (int i = 0; i < klokkeSlet.length; i++) {
				dagligSkaev.createDosis(klokkeSlet[i], antalEnheder[i]);
			}
			return dagligSkaev;
		} else {
			throw new IllegalArgumentException(
					"Startdato skal være før slutdato, klokkeslet og antalEnheder skal have samme længde");
		}
	}

	/**
	 * En dato for hvornaar ordinationen anvendes tilfoejes ordinationen. Hvis
	 * datoen ikke er indenfor ordinationens gyldighedsperiode kastes en
	 * IllegalArgumentException Pre: ordination og dato er ikke null
	 */
	public void ordinationPNAnvendt(PN ordination, LocalDate dato) {
		boolean test = ordination.givDosis(dato);
		if (!test) {
			throw new IllegalArgumentException("Dato er invalid");
		}

	}

	/**
	 * Den anbefalede dosis for den pÃ¥gældende patient (der skal tages hensyn til
	 * patientens vægt). Det er en forskellig enheds faktor der skal anvendes, og
	 * den er afhængig af patientens vægt. Pre: patient og lægemiddel er ikke null
	 */
	public double anbefaletDosisPrDoegn(Patient patient, Laegemiddel laegemiddel) {
		double result;
		if (patient.getVaegt() < 25) {
			result = patient.getVaegt() * laegemiddel.getEnhedPrKgPrDoegnLet();
		} else if (patient.getVaegt() > 120) {
			result = patient.getVaegt() * laegemiddel.getEnhedPrKgPrDoegnTung();
		} else {
			if (patient.getVaegt() > 0) {
				result = patient.getVaegt() * laegemiddel.getEnhedPrKgPrDoegnNormal();
			} else {
				throw new IllegalArgumentException("You are not made out of antimatter");
			}

		}
		return result;
	}

	/**
	 * For et givent vægtinterval og et givent lægemiddel, hentes antallet af
	 * ordinationer. Pre: laegemiddel er ikke null
	 */
	public int antalOrdinationerPrVægtPrLægemiddel(double vaegtStart, double vaegtSlut, Laegemiddel laegemiddel) {
		int antal = 0;
//		if (vaegtSlut > vaegtStart) {
//			double temp = vaegtSlut;
//			vaegtSlut = vaegtStart;
//			vaegtStart = temp;
//		}
		for (Patient p : storage.getAllPatienter()) {
			if (p.getVaegt() >= vaegtStart && p.getVaegt() <= vaegtSlut) {
				for (Ordination o : p.getOrdinationer()) {
					if (o.getLaegemiddel().equals(laegemiddel)) {
						antal++;
					}
				}
			}
		}
		return antal;
	}

	public List<Patient> getAllPatienter() {
		return storage.getAllPatienter();
	}

	public List<Laegemiddel> getAllLaegemidler() {
		return storage.getAllLaegemidler();
	}

	/**
	 * Metode der kan bruges til at checke at en startDato ligger fÃ¸r en slutDato.
	 *
	 * @return true hvis startDato er fÃ¸r slutDato, false ellers.
	 */
	private boolean checkStartFoerSlut(LocalDate startDato, LocalDate slutDato) {
		boolean result = true;
		if (slutDato.compareTo(startDato) < 0) {
			result = false;
		}
		return result;
	}

	public Patient opretPatient(String cpr, String navn, double vaegt) {
		if (vaegt > 0) {
			Patient p = new Patient(cpr, navn, vaegt);
			storage.addPatient(p);
			return p;
		} else {
			throw new IllegalArgumentException("You should fatten up");
		}
	}

	public Laegemiddel opretLaegemiddel(String navn, double enhedPrKgPrDoegnLet, double enhedPrKgPrDoegnNormal,
			double enhedPrKgPrDoegnTung, String enhed) {
		if (enhedPrKgPrDoegnLet > 0 && enhedPrKgPrDoegnNormal > 0 && enhedPrKgPrDoegnTung > 0) {
			Laegemiddel lm = new Laegemiddel(navn, enhedPrKgPrDoegnLet, enhedPrKgPrDoegnNormal, enhedPrKgPrDoegnTung,
					enhed);
			storage.addLaegemiddel(lm);
			return lm;
		} else {
			throw new IllegalArgumentException("Dont feed your patients antimatter, we dont know the consequences!");
		}
	}

	public void createSomeObjects() {
		opretPatient("121256-0512", "Jane Jensen", 63.4);
		opretPatient("070985-1153", "Finn Madsen", 83.2);
		opretPatient("050972-1233", "Hans JÃ¸rgensen", 89.4);
		opretPatient("011064-1522", "Ulla Nielsen", 59.9);
		opretPatient("090149-2529", "Ib Hansen", 87.7);

		opretLaegemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");
		opretLaegemiddel("Paracetamol", 1, 1.5, 2, "Ml");
		opretLaegemiddel("Fucidin", 0.025, 0.025, 0.025, "Styk");
		opretLaegemiddel("Methotrexat", 0.01, 0.015, 0.02, "Styk");

		opretPNOrdination(LocalDate.of(2015, 1, 1), LocalDate.of(2015, 1, 12), storage.getAllPatienter().get(0),
				storage.getAllLaegemidler().get(1), 123);

		opretPNOrdination(LocalDate.of(2015, 2, 12), LocalDate.of(2015, 2, 14), storage.getAllPatienter().get(0),
				storage.getAllLaegemidler().get(0), 3);

		opretPNOrdination(LocalDate.of(2015, 1, 20), LocalDate.of(2015, 1, 25), storage.getAllPatienter().get(3),
				storage.getAllLaegemidler().get(2), 5);

		opretPNOrdination(LocalDate.of(2015, 1, 1), LocalDate.of(2015, 1, 12), storage.getAllPatienter().get(0),
				storage.getAllLaegemidler().get(1), 123);

		opretDagligFastOrdination(LocalDate.of(2015, 1, 10), LocalDate.of(2015, 1, 12),
				storage.getAllPatienter().get(1), storage.getAllLaegemidler().get(1), 2, -1, 1, -1);

		LocalTime[] kl = { LocalTime.of(12, 0), LocalTime.of(12, 40), LocalTime.of(16, 0), LocalTime.of(18, 45) };
		double[] an = { 0.5, 1, 2.5, 3 };

		opretDagligSkaevOrdination(LocalDate.of(2015, 1, 23), LocalDate.of(2015, 1, 24),
				storage.getAllPatienter().get(1), storage.getAllLaegemidler().get(2), kl, an);
	}

}
