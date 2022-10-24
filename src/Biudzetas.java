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

	public int getMaxIrasu() {
		return maxIrasu;
	}

	public String getFileName() {return fileName;}

	public void setFileName(String fileName) {this.fileName = fileName;}

// methods	

	/**
	 * pridetiPajamuIrasa(double suma, String kategorija, String papildomaInfo, String gryniejiBankas)
	 */

	public void pridetiPajamas(double suma, String kategorija, String papildomaInfo, String gryniejiBankas) {
		if (pajamosIslaidos.size() < maxIrasu) {
			PajamuIrasas naujosPajamos = new PajamuIrasas(suma, kategorija, papildomaInfo, gryniejiBankas);
			pajamosIslaidos.add(naujosPajamos);

			String contentToFile = String.format("%s,%s,%s,%s,%s,%s\n", naujosPajamos.getSuma(), naujosPajamos.getKategorija(), naujosPajamos.getPapildomaInfo(), naujosPajamos.getGryniejiBankas(), naujosPajamos.getIrasoNr(), naujosPajamos.getData());
			writeFile(fileName, contentToFile);
			System.out.println("Pridetas " + pajamosIslaidos.get(pajamosIslaidos.indexOf(naujosPajamos)) + "\n-----------------------\n");
		} else {
			System.out.println("pajamu irasu kiekis pasieke maksimalu (" + maxIrasu + "). Daugiau irasu programa nesaugo.\nGalima istrinti visas ivestas pajamas, naudojant metoda [.istrintiPajamas()]\narba istrinti tam tikrus pajamu irasus, nurodant iraso numeri [.istrintiPajamuIrasa(\"Nr\")]\n-----------------------\n");
		}
	}

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

	/**
	 * pridetiIslaidas(double suma, String kategorija, String papildomaInfo, String gryniejiBankas)
	 */

	public void pridetiIslaidas(double suma, String kategorija, String papildomaInfo, String gryniejiBankas) {
		if (pajamosIslaidos.size() < maxIrasu) {
			Irasas naujosIslaidos = new IslaiduIrasas(suma, kategorija, papildomaInfo, gryniejiBankas);
			pajamosIslaidos.add(naujosIslaidos);

			String contentToFile = String.format("%s,%s,%s,%s,%s,%s\n", naujosIslaidos.getSuma(), naujosIslaidos.getKategorija(), naujosIslaidos.getPapildomaInfo(), naujosIslaidos.getGryniejiBankas(), naujosIslaidos.getIrasoNr(), naujosIslaidos.getData());
			writeFile(fileName, contentToFile);

			System.out.println("Pridetas " + pajamosIslaidos.get(pajamosIslaidos.indexOf(naujosIslaidos)) + "\n-----------------------\n");
		} else {
			System.out.println("pajamu irasu kiekis pasieke maksimalu (" + maxIrasu + "). Daugiau irasu netelpa programos atmintyje.\nGalima istrinti visas ivestas pajamas, naudojant metoda [.istrintiPajamas()]\narba istrinti tam tikrus pajamu irasus, nurodant iraso numeri [.istrintiPajamuIrasa(\"Nr\")]\n-----------------------\n");
		}
	}

	/**
	 * isspausdina pajamu irasa pagal nurodytus duomenis
	 */

	public void gautiIrasa(String kategorija, String data) {
		System.out.println(String.format("Pagal kategorija \"%s\" ir data %s atfiltruoti sie issaugoti irasai:\n", kategorija, data));

		ArrayList<Irasas> atfiltruotiIrasai = new ArrayList<>();

		//susiparsinti data ir per get day month year pasilygint - neparsinau, nes ir siaip naudoju metoda datai gauti, kuris grazina stringa
		// getKategorija == kategorija tikrinu del to, kad leidziu ivesti null reiksme. paskui gal istrinsiu, nes per UI null reiksmiu nebus?
		for (Irasas irasas : pajamosIslaidos) {

			if ((irasas.getKategorija() == kategorija || irasas.getKategorija().equalsIgnoreCase(kategorija)) && irasas.dataFormatuota().equals(data)) {
				atfiltruotiIrasai.add(irasas);
			}
		}
		for (Irasas irasas : atfiltruotiIrasai) {
			String kurGauta = (irasas.getGryniejiBankas().equals("grynieji")) ? "banko sąskaitoj" : "grynais";
			String kategorijaS = (irasas.getKategorija() == null) ? "kategorija nenurodyta" : "kategorija: " + irasas.getKategorija();
			String papildomaInfo = (irasas.getPapildomaInfo() == null) ? "" : ", papildoma info: " + irasas.getPapildomaInfo();
			if (irasas instanceof PajamuIrasas) {
				System.out.println(String.format("%s Pajamu irasas Nr %s (suma %.2f EUR, %s, %s%s)", irasas.dataFormatuota(), irasas.getIrasoNr(), irasas.getSuma(), kategorijaS, kurGauta, papildomaInfo));
			} else {
				System.out.println(String.format("%s Islaidu irasas Nr %s (suma %.2f EUR, %s, %s%s)", irasas.dataFormatuota(), irasas.getIrasoNr(), irasas.getSuma(), kategorijaS, kurGauta, papildomaInfo));
			}
		}
		System.out.println("\n-----------------------\n");
	}

	/**
	 * spausdina pajamu arba islaidu visus irasus
	 */
	public void spausdinti(String pajamasArIslaidas) {
		String kaSpausdinti = pajamasArIslaidas;
		switch (kaSpausdinti) {
			case ("pajamas"):
				System.out.println("Biudzete siuo metu issaugoti pajamu irasai:\n");
				for (Irasas irasas : pajamosIslaidos) {
					if (irasas instanceof PajamuIrasas) {
						String kurGauta = (irasas.getGryniejiBankas().equals("grynieji")) ? "gauta pavedimu" : "gauta grynais";
						String kategorija = (irasas.getKategorija() == null) ? "kategorija nenurodyta" : "kategorija: " + irasas.getKategorija();
						String papildomaInfo = (irasas.getPapildomaInfo() == null) ? "" : ", papildoma info: " + irasas.getPapildomaInfo();
						String vienasIrasas = String.format("%s Pajamu Irasas Nr %s (suma %.2f EUR, %s, %s%s)\n-----------------------\n", irasas.dataSuLaiku(), ((PajamuIrasas) irasas).getIrasoNr(), irasas.getSuma(), kategorija, kurGauta, papildomaInfo);
						System.out.println(vienasIrasas);
					}
				}
				break;

			case ("islaidas"):
				System.out.println("Biudzete siuo metu issaugoti islaidu irasai:\n");
				for (Irasas irasas : pajamosIslaidos) {
					if (irasas instanceof IslaiduIrasas) {
						String atsiskaitymoBudas = (irasas.getGryniejiBankas() == null) ? "atsiskaitymo budas nenurodytas" : "atsiskaitymo budas: " + irasas.getGryniejiBankas();
						String kategorija = (irasas.getKategorija() == null) ? "kategorija nenurodyta" : "kategorija: " + irasas.getKategorija();
						String papildomaInfo = (irasas.getPapildomaInfo() == null) ? "" : ", papildoma info: " + irasas.getPapildomaInfo();
						String vienasIrasas = String.format("%s Islaidu Irasas Nr %s (suma %.2f EUR, %s, %s%s)\n-----------------------\n", irasas.dataSuLaiku(), ((IslaiduIrasas) irasas).getIrasoNr(), irasas.getSuma(), kategorija, atsiskaitymoBudas, papildomaInfo);
						System.out.println(vienasIrasas);
					}
				}
				break;

			default:
				System.out.println("klaida! metode reikia nurodyti, ka spausdinti:\n .spausdinti(\"pajamas\")\n .spausdinti(\"islaidas\")\n");
		}
	}

	public ArrayList spausdintiUI(String pajamasArIslaidas) {
		ArrayList<String> irasai = new ArrayList<>();
		String kaSpausdinti = pajamasArIslaidas;
		if (kaSpausdinti.equals("pajamas")) {
			irasai.add("Biudzete siuo metu issaugoti pajamu irasai:\n");
			for (Irasas irasas : pajamosIslaidos) {
				if (irasas instanceof PajamuIrasas) {
					String kurGauta = (irasas.getGryniejiBankas().equals("grynieji")) ? "gauta pavedimu" : "gauta grynais";
					String kategorija = (irasas.getKategorija() == null) ? "kategorija nenurodyta" : "kategorija: " + irasas.getKategorija();
					String papildomaInfo = (irasas.getPapildomaInfo() == null) ? "" : ", papildoma info: " + irasas.getPapildomaInfo();
					String vienasIrasas = String.format("%s Pajamu Irasas Nr %s (suma %.2f EUR, %s, %s%s)", irasas.dataSuLaiku(), irasas.getIrasoNr(), irasas.getSuma(), kategorija, kurGauta, papildomaInfo);
					irasai.add(vienasIrasas);
				}
			}
			return irasai;
		} else if (kaSpausdinti.equals("islaidas")) {
			irasai.add("Biudzete siuo metu issaugoti islaidu irasai:\n");
			for (Irasas irasas : pajamosIslaidos) {
				if (irasas instanceof IslaiduIrasas) {
					String atsiskaitymoBudas = (irasas.getGryniejiBankas() == null) ? "atsiskaitymo budas nenurodytas" : "atsiskaitymo budas: " + irasas.getGryniejiBankas();
					String kategorija = (irasas.getKategorija() == null) ? "kategorija nenurodyta" : "kategorija: " + irasas.getKategorija();
					String papildomaInfo = (irasas.getPapildomaInfo() == null) ? "" : ", papildoma info: " + irasas.getPapildomaInfo();
					String vienasIrasas = String.format("%s Islaidu Irasas Nr %s (suma %.2f EUR, %s, %s%s)\n-----------------------\n", irasas.dataSuLaiku(), ((IslaiduIrasas) irasas).getIrasoNr(), irasas.getSuma(), kategorija, atsiskaitymoBudas, papildomaInfo);
					irasai.add(vienasIrasas);
				}
			}
			return irasai;
		} else {
			irasai.add("klaida! metode reikia nurodyti, ka spausdinti:\n .spausdinti(\"pajamas\")\n .spausdinti(\"islaidas\")\n");
			return irasai;
		}
	}


	public void spausdinti() {
		System.out.println("klaida! metode reikia nurodyti, ka spausdinti:\n .spausdink(\"pajamas\")\n .spausdink(\"islaidas\")\n");
	}

	public void istrintiIrasa(String nr) {
		if (yraToksIrasas(nr)) {
			overWriteFile(fileName); // cia perrasau faila tokiu paciu pavadinimu, t.y. padarau kad butu tuscias
			for (Irasas irasas : pajamosIslaidos) {
				if (irasas.getIrasoNr().equals(nr)) {
					System.out.println("Istrintas " + pajamosIslaidos.get(pajamosIslaidos.indexOf(irasas)) + "\n-----------------------\n");
					pajamosIslaidos.remove(pajamosIslaidos.indexOf(irasas));
					break;
				}
			}
			for (Irasas irasas : pajamosIslaidos) { // surasau i tuscia faila atnaujinta irasu sarasa jau be to istrinto iraso
				String contentToFile = String.format("%s,%s,%s,%s,%s,%s\n", irasas.getSuma(), irasas.getKategorija(), irasas.getPapildomaInfo(), irasas.getGryniejiBankas(), irasas.getIrasoNr(), irasas.getData());
				writeFile(fileName, contentToFile);
			}
		} else {
			System.out.println("iraso su tokiu numeriu nera arba numeris ivestas su klaida\n-----------------------\n");
		}
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


	//naudoja metodas istrinti PajamuIrasa
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
		return "Visi įrašai iš biudžeto ir failo " + getFileName() + ".csv buvo ištrinti. Galite pradėti pildyti naują biudžetą";
	}

	public double balansas() {
		double balansas = 0;
		for (Irasas vnt : pajamosIslaidos) {
			double balansasTarpinis = vnt instanceof PajamuIrasas ? vnt.getSuma() : -vnt.getSuma();
			balansas += balansasTarpinis;
		}
		System.out.println("biudzeto balansas yra: " + balansas + " eur");
		return balansas;
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
		}
		rezultatasRedaguotas.add("Redaguotas įrašas " + redaguojamas);
		return rezultatasRedaguotas;
	}

	public String saugoti(String name, boolean overWrite) {

		setFileName(name);

		for (Irasas irasas : pajamosIslaidos) {
			String contentToFile = String.format("%s,%s,%s,%s,%s,%s\n", irasas.getSuma(), irasas.getKategorija(), irasas.getPapildomaInfo(), irasas.getGryniejiBankas(), irasas.getIrasoNr(), irasas.getData());
			if (overWrite) {
				overWriteFile(fileName);
				writeFile(fileName, contentToFile);
				irasas.setSaved(true);
			} else if (!irasas.isSaved()) {
				writeFile(fileName, contentToFile);
				irasas.setSaved(true);
			}
		}
		String rezultatas = "Visi įvesti biudžeto duomenys išsaugoti csv formatu čia: " + getPath() + "\\" + fileName + ".csv";
		return rezultatas;
	}

	public String atidarytiFaila(String name) {
		setFileName(name);
		String rezultatas;
		if (new File(getPath() + "/" + fileName + ".csv").exists()) {
			pajamosIslaidos.addAll(readFile(fileName));
			rezultatas = "Užkrauti duomenys iš failo: " + fileName + ".csv. Galite peržiūrėti visus įvestus duomenis";
		} else {
			rezultatas = "Failo " + fileName + ".csv nėra. Bandykite dar kartą arba saugokite įvestus duomenis į failą (Saugoti)";
		}
		return rezultatas;
	}

} // KLASES UZDARYMAS
