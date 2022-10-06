import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Biudzetas {

	private LocalDateTime data = LocalDateTime.now();
	private DateTimeFormatter formatas = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private DateTimeFormatter formatasSuLaiku = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	private ArrayList<PajamuIrasas> pajamos = new ArrayList<>();
	private ArrayList<IslaiduIrasas> islaidos = new ArrayList<>();
	private int maxIrasu = 5;
	
// getters

	public LocalDateTime getData() {
		return data;
	}

	public DateTimeFormatter getFormatas() {
		return formatas;
	}

	public DateTimeFormatter getFormatasSuLaiku() {
		return formatasSuLaiku;
	}

	ArrayList<PajamuIrasas> getPajamos() {
		return pajamos;
	}

	ArrayList<IslaiduIrasas> getIslaidos() {
		return islaidos;
	}

	public int getMaxIrasu() {
		return maxIrasu;
	}

// methods	
	
	public String dataFormatuota() {
		return data.format(formatas);
	}
	
	public String dataSuLaiku() {
		return data.format(formatasSuLaiku);
	}
	
/** pridetiPajamuIrasa(double suma, String kategorija, boolean arGautaIBankoSask, String papildomaInfo) */
	
	public void pridetiPajamas(double suma, String kategorija, boolean arGautaIBankoSask, String papildomaInfo) {
		if (pajamos.size() < maxIrasu) {
			PajamuIrasas naujosPajamos = new PajamuIrasas(suma, kategorija, arGautaIBankoSask, papildomaInfo);
			pajamos.add(naujosPajamos);
		System.out.println("Pridetas " + pajamos.get(pajamos.indexOf(naujosPajamos)) + "\n-----------------------\n");
		} else {
		System.out.println("pajamu irasu kiekis pasieke maksimalu (" + maxIrasu + "). Daugiau irasu programa nesaugo.\nGalima istrinti visas ivestas pajamas, naudojant metoda [.istrintiPajamas()]\narba istrinti tam tikrus pajamu irasus, nurodant iraso numeri [.istrintiPajamuIrasa(\"Nr\")]\n-----------------------\n");
		}
	}
	
/** pridetiIslaiduIrasa(double suma, String kategorija, String atsiskaitymoBudas, String papildomaInfo) */
	
	public void pridetiIslaidas(double suma, String kategorija, String atsiskaitymoBudas, String papildomaInfo) {
		if (islaidos.size() < maxIrasu) {
			IslaiduIrasas naujosIslaidos = new IslaiduIrasas(suma, kategorija, atsiskaitymoBudas, papildomaInfo);
			islaidos.add(naujosIslaidos);
		System.out.println("Pridetas " + islaidos.get(islaidos.indexOf(naujosIslaidos)) + "\n-----------------------\n");
		} else {
		System.out.println("pajamu irasu kiekis pasieke maksimalu (" + maxIrasu + "). Daugiau irasu netelpa programos atmintyje.\nGalima istrinti visas ivestas pajamas, naudojant metoda [.istrintiPajamas()]\narba istrinti tam tikrus pajamu irasus, nurodant iraso numeri [.istrintiPajamuIrasa(\"Nr\")]\n-----------------------\n");
		}
	}

/** isspausdina pajamu irasa pagal nurodytus duomenis */
	
	public void gautiPajamuIrasa(String kategorija, String data) {
		System.out.println(String.format("Pagal kategorija \"%s\" ir data %s atfiltruoti sie issaugoti pajamu irasai:\n", kategorija, data));
		
		ArrayList<PajamuIrasas> atfiltruotosPajamos = new ArrayList<>();
		
	
		
		//susiparsinti data ir per get day month year pasilygint
		// getKategorija == kategorija tikrinu del to, kad leidziu ivesti null reiksme
		for (PajamuIrasas irasas : pajamos) {

			if ((irasas.getKategorija() == kategorija || irasas.getKategorija().equalsIgnoreCase(kategorija)) && irasas.dataFormatuota().equals(data)) {
				atfiltruotosPajamos.add(irasas);
			}
		}
		for (PajamuIrasas irasas : atfiltruotosPajamos) {
			String kurGauta = (irasas.isArGautaIBankoSask()) ? "gauta pavedimu" : "gauta grynais";
			String kategorijaS = (irasas.getKategorija() == null) ? "kategorija nenurodyta" : "kategorija: " + irasas.getKategorija();
			String papildomaInfo = (irasas.getPapildomaInfo() == null) ? "" : ", papildoma info: " + irasas.getPapildomaInfo();
			String vienasIrasas = String.format("%s Pajamu Irasas Nr %s (suma %.2f EUR, %s, %s%s)", dataFormatuota(), irasas.getIrasoNr(), irasas.getSuma(), kategorijaS, kurGauta, papildomaInfo);
			System.out.println(vienasIrasas);
		}
		System.out.println("\n-----------------------\n");
	}
	
	/** spausdina pajamu arba islaidu visus irasus
	 */
	public void spausdinti(String pajamasArIslaidas) {
		String kaSpausdinti = pajamasArIslaidas;
		switch (kaSpausdinti) {
		case("pajamas"):
		System.out.println("Biudzete siuo metu issaugoti pajamu irasai:\n");
		for (PajamuIrasas irasas : pajamos) {
			String kurGauta = (irasas.isArGautaIBankoSask()) ? "gauta pavedimu" : "gauta grynais";
			String kategorija = (irasas.getKategorija() == null) ? "kategorija nenurodyta" : "kategorija: " + irasas.getKategorija();
			String papildomaInfo = (irasas.getPapildomaInfo() == null) ? "" : ", papildoma info: " + irasas.getPapildomaInfo();
			String vienasIrasas = String.format("%s Pajamu Irasas Nr %s (suma %.2f EUR, %s, %s%s)\n-----------------------\n", dataFormatuota(), irasas.getIrasoNr(), irasas.getSuma(), kategorija, kurGauta, papildomaInfo);
			System.out.println(vienasIrasas);
		}
		break;
		
		case ("islaidas"):
		System.out.println("Biudzete siuo metu issaugoti islaidu irasai:\n");
		for (IslaiduIrasas irasas : islaidos) {
			String atsiskaitymoBudas = (irasas.getAtsiskaitymoBudas() == null) ? "atsiskaitymo budas nenurodytas" : "atsiskaitymo budas: " + irasas.getAtsiskaitymoBudas();
			String kategorija = (irasas.getKategorija() == null) ? "kategorija nenurodyta" : "kategorija: " + irasas.getKategorija();
			String papildomaInfo = (irasas.getPapildomaInfo() == null) ? "" : ", papildoma info: " + irasas.getPapildomaInfo();
			String vienasIrasas = String.format("%s Islaidu Irasas Nr %s (suma %.2f EUR, %s, %s%s)\n-----------------------\n", dataSuLaiku(), irasas.getIrasoNr(), irasas.getSuma(), kategorija, atsiskaitymoBudas, papildomaInfo);
			System.out.println(vienasIrasas);
		}
		break;
		
		default:
			System.out.println("klaida! metode reikia nurodyti, ka spausdinti:\n .spausdinti(\"pajamas\")\n .spausdinti(\"islaidas\")\n");
		}
	}

	// tegu grazina i loopa, kad ivestu teisingai
	// cia kai nenurodo ka spausdinti
	public void spausdinti() {
		System.out.println("klaida! metode reikia nurodyti, ka spausdinti:\n .spausdink(\"pajamas\")\n .spausdink(\"islaidas\")\n");
	}
	
	public void istrintiPajamuIrasa(String nr) {		
		
		if (yraToksIrasas(nr)) {
			for(PajamuIrasas irasas : pajamos) {
				if (irasas.getIrasoNr().equals(nr)) {
					System.out.println("Istrintas " + pajamos.get(pajamos.indexOf(irasas)) + "\n-----------------------\n");
					pajamos.remove(pajamos.indexOf(irasas));
					break;
				} 
			} 
		} else {
		System.out.println("iraso su tokiu numeriu nera arba numeris ivestas su klaida\n-----------------------\n");
		}
	}
	
	//naudoja metodas istrinti PajamuIrasa
	public boolean yraToksIrasas(String nr) {
		boolean yraToksIrasas = false;
		for(PajamuIrasas irasas : pajamos) {
			if (irasas.getIrasoNr().equals(nr)) {
				yraToksIrasas = true;
				break;
			}
		}
		return yraToksIrasas;
	}
		
//	public void istrintiPajamas () {
//		pajamos.clear();
//	}

	public double balansas() {
		double balansas = 0;
		for(PajamuIrasas vnt : getPajamos()) {
			balansas += vnt.getSuma();
		}
		for(IslaiduIrasas vnt : getIslaidos()) {
			balansas -= vnt.getSuma();
		}
		System.out.println("biudzeto balansas yra: " + balansas + " eur");
		return balansas;
	}
	
}
