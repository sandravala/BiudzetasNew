import java.time.LocalDateTime;

public class PajamuIrasas extends Irasas{

	private static int numeris;
	private String irasoNr;
	
	public String getIrasoNr() {
		return irasoNr;
	}

	public static int getNumeris() {
		return numeris;
	}

	public static void resetNumeris() { numeris = 0; }

	public PajamuIrasas(double suma, String kategorija, String papildomaInfo, String gryniejiBankas) {
		super(suma, kategorija, papildomaInfo, gryniejiBankas);
		numeris++;
		irasoNr = "P"+numeris;
		super.setIrasoNr(irasoNr);
	}

	//šitą konstruktorių naudoja metodas atidaryti failą
	public PajamuIrasas(double suma, String kategorija, String papildomaInfo, String gryniejiBankas, String irasoNr, LocalDateTime dataNuskaityta) {
		super(suma, kategorija, papildomaInfo, gryniejiBankas, irasoNr, dataNuskaityta);
		this.irasoNr = irasoNr;
	}
		
	
	@Override
	public String toString() {
		String kurGauta = (getGryniejiBankas().equals("bankas")) ? "gauta pavedimu" : "gauta grynais";
		String kategorija = (getKategorija() == "") ? "kategorija nenurodyta" : "kategorija: " + getKategorija();
		String papildomaInfo = (getPapildomaInfo() == "") ? "" : ", papildoma info: " + getPapildomaInfo();
		String faileIssaugotas = isSaved() ? ". Faile įrašytas" : ". Faile neįrašytas";
		String pajamuIrasas = String.format("%s Pajamu Irasas Nr %s (suma %.2f EUR, %s, %s%s)%s", dataSuLaiku(), getIrasoNr(), getSuma(), kategorija, kurGauta, papildomaInfo, faileIssaugotas);
		return pajamuIrasas;
	}
	
	
	
	
}
