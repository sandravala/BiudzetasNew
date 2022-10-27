import java.io.File;
import java.util.ArrayList;

public class Biudzetas extends FileReadWrite {

	private ArrayList<Irasas> pajamosIslaidos = new ArrayList<>();
	private int maxIrasu = 30;

	private String fileName;

	// constructor
	public Biudzetas() {

		fileName = "biudzetas";
	}

// getters

	public String getFileName() {return fileName;}

	public void setFileName(String fileName) {this.fileName = fileName;}

// methods	

	public String pridetiPajamasUI(double suma, String kategorija, String papildomaInfo, String gryniejiBankas) {
		if (pajamosIslaidos.size() < maxIrasu) {
			PajamuIrasas naujosPajamos = new PajamuIrasas(suma, kategorija, papildomaInfo, gryniejiBankas);
			pajamosIslaidos.add(naujosPajamos);
			String pridetasIrasas = "Pridetas " + pajamosIslaidos.get(pajamosIslaidos.indexOf(naujosPajamos));
			return pridetasIrasas;
		} else {
			String klaida = "Įrašų kiekis pasiekė maksimalų (" + maxIrasu + "). Daugiau įrašų programa nesaugo.";
			return klaida;
		}
	}

	public String pridetiIslaidasUI(double suma, String kategorija, String papildomaInfo, String gryniejiBankas) {
		if (pajamosIslaidos.size() < maxIrasu) {
			IslaiduIrasas naujosIslaidos = new IslaiduIrasas(suma, kategorija, papildomaInfo, gryniejiBankas);
			pajamosIslaidos.add(naujosIslaidos);
			String pridetasIrasas = "Pridetas " + pajamosIslaidos.get(pajamosIslaidos.indexOf(naujosIslaidos));
			return pridetasIrasas;
		} else {
			String klaida = "Įrašų kiekis pasiekė maksimalų (" + maxIrasu + "). Daugiau įrašų programa nesaugo.";
			return klaida;
		}
	}

	public ArrayList spausdintiUI() {
		ArrayList<String> irasai = new ArrayList<>();
			irasai.add("Biudzete siuo metu issaugoti pajamu irasai:");
			irasai.add(" ");
			for (Irasas irasas : pajamosIslaidos) {
				if (irasas instanceof PajamuIrasas) {
					irasai.add(irasas.toString());
				}
			}
			irasai.add(" ");
			irasai.add("Biudzete siuo metu issaugoti islaidu irasai:");
			irasai.add(" ");
			for (Irasas irasas : pajamosIslaidos) {
				if (irasas instanceof IslaiduIrasas) {
					irasai.add(irasas.toString());
				}
			}
			return irasai;
	}

	public String istrintiIrasaUI(Irasas trinamas) {
		String rezultatas = "Ištrintas " + trinamas.toString();
		boolean arIssaugotas = trinamas.isSaved();
		pajamosIslaidos.remove(pajamosIslaidos.indexOf(trinamas));
		if (arIssaugotas) {
			overWriteFile(fileName);
			for (Irasas irasas : pajamosIslaidos) {
				String contentToFile = String.format("%s,%s,%s,%s,%s,%s\n", irasas.getSuma(), irasas.getKategorija(), irasas.getPapildomaInfo(), irasas.getGryniejiBankas(), irasas.getIrasoNr(), irasas.getData());
				writeFile(fileName, contentToFile);
			}
		}
		return rezultatas;
	}

	public boolean yraToksIrasas(String nr) {
		boolean yraToksIrasas = false;
		for (Irasas irasas : pajamosIslaidos) {
			if (irasas.getIrasoNr().equals(nr)) {
				yraToksIrasas = true;
				break;
			}
		}
		return yraToksIrasas;
	}

	public Irasas rastiIrasa(String numeris) {
		Irasas rastasIrasas = null;
		if (yraToksIrasas(numeris)) {
			for (Irasas irasas : pajamosIslaidos) {
				if (irasas.getIrasoNr().equals(numeris)) {
					rastasIrasas = pajamosIslaidos.get(pajamosIslaidos.indexOf(irasas));
				}
			}
		}
		return rastasIrasas;
	}

	public String spausdintiIrasa(String numeris, Irasas spausdinamas) {
		String spausdinti = spausdinamas == null ? "Įrašas su nurodytu numeriu " + numeris + " neegzistuoja. Bandykite dar kartą" : spausdinamas.toString();
		return spausdinti;
	}

	public String istrintiVisusIrasus() {
		pajamosIslaidos.clear();
		overWriteFile(fileName);
		return "Visi įrašai iš biudžeto ir iš failo " + getFileName() + ".csv buvo ištrinti. Galite pradėti pildyti naują biudžetą";
	}

	public String balansas() {
		double balansas = 0;
		for (Irasas vnt : pajamosIslaidos) {
			double balansasTarpinis = vnt instanceof PajamuIrasas ? vnt.getSuma() : -vnt.getSuma();
			balansas += balansasTarpinis;
		}
		String suma = String.format("%.2f EUR", balansas);
		String balansoZinute = balansas > 0 ? "Valio! Pinigų dar yra: " + suma : balansas < 0 ? "Oi. Atrodo, kad išlaidos jau viršijo pajamas: " + suma : "Biudžete turim apskritą " + suma;
		return balansoZinute;
	}

	public ArrayList<String> redaguotiIrasa(Irasas redaguojamas, double suma, String kategorija, String info, String gryniejiBankas, String data, String laikas) {
		ArrayList<String> rezultatasRedaguotas = new ArrayList<>();
		rezultatasRedaguotas.add("Pradinis įrašas " + redaguojamas);

		redaguojamas.setSuma(suma);
		redaguojamas.setKategorija(kategorija);
		redaguojamas.setPapildomaInfo(info);
		redaguojamas.setGryniejiBankas(gryniejiBankas);
		redaguojamas.setData(data, laikas);

		if (redaguojamas.isSaved()) {
			saugoti(fileName, true);
		} else {
			saugoti(fileName, false);
		}
		rezultatasRedaguotas.add("Redaguotas įrašas " + redaguojamas);
		return rezultatasRedaguotas;
	}

	public String saugoti(String name, boolean overWrite) {

			setFileName(name);

			if (pajamosIslaidos.size() > 0) {

				for (Irasas irasas : pajamosIslaidos) {
					String contentToFile = String.format("%s,%s,%s,%s,%s,%s\n", irasas.getSuma(), irasas.getKategorija(), irasas.getPapildomaInfo(), irasas.getGryniejiBankas(), irasas.getIrasoNr(), irasas.getData());
					if (overWrite) {
						overWriteFile(fileName);
						writeFile(fileName, contentToFile);
						setFailuSarasas(fileName);
					} else if (!irasas.isSaved()) {
						writeFile(fileName, contentToFile);
						irasas.setSaved(true);
						setFailuSarasas(fileName);
					}
				}
			} else {
				writeFile(fileName, "");
				setFailuSarasas(fileName);
			}

			String rezultatas = getPath() + "\\" + fileName + ".csv";
			return rezultatas;
	}

	public String atidarytiFaila(String name) {
		setFileName(name);
		String rezultatas;
		if (new File(getPath() + "/" + fileName + ".csv").exists()) {
			pajamosIslaidos.clear();
			new PajamuIrasas("nulis");
			new IslaiduIrasas("nulis");
			pajamosIslaidos = readFile(fileName);
			if(readFile(fileName).size() < 1) {
				rezultatas = "Failas " + fileName + ".csv atidarytas, bet yra tuščias";
			} else {
				rezultatas = "Nuskaityti duomenys iš failo: " + fileName + ".csv. Nuskaitytų įrašų kiekis: " + pajamosIslaidos.size() + " vnt. Galite toliau dirbti su duomenimis";
			}
		} else {
			rezultatas = "UPS! failo " + fileName + ".csv nėra. Kažkas tikriausiai jį ištrynė. Pasirinkite kitą failą arba kurkite naują";
		}
		return rezultatas;
	}


} // KLASES UZDARYMAS
