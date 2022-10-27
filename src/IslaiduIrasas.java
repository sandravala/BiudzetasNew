import java.time.LocalDateTime;

public class IslaiduIrasas extends Irasas{
	private static int numeris;
	private String irasoNr;
	
	public String getIrasoNr() {
		return irasoNr;
	}
	public void resetNumeris() {
		numeris = 0;
	}

	public IslaiduIrasas(double suma, String kategorija, String papildomaInfo, String gryniejiBankas) {
		super(suma, kategorija, papildomaInfo, gryniejiBankas);
		super.dataSuLaiku();
		numeris++;
		irasoNr = "I"+numeris;
		super.setIrasoNr(irasoNr);
	}

	public IslaiduIrasas(String s) {
		super(s);
		if (s.equals("nulis")) {
			this.numeris = -1;
		}
	}

	public IslaiduIrasas(double suma, String kategorija, String papildomaInfo, String gryniejiBankas, String irasoNr, LocalDateTime dataNuskaityta) {
		super(suma, kategorija, papildomaInfo, gryniejiBankas, irasoNr, dataNuskaityta);
		this.irasoNr = irasoNr;
		numeris++;
	}
	@Override
	public String toString() {
		String kurGauta = (getGryniejiBankas().equals("bankas")) ? "mokėta pavedimu" : "mokėta grynais";
		String kategorija = (getKategorija() == "") ? "kategorija nenurodyta" : "kategorija: " + getKategorija();
		String papildomaInfo = (getPapildomaInfo() == "") ? "" : ", papildoma info: " + getPapildomaInfo();
		String faileIssaugotas = isSaved() ? ". Faile įrašytas" : ". Faile neįrašytas";
		String islaiduIrasas = String.format("%s Pajamu Irasas Nr %s (suma %.2f EUR, %s, %s%s)%s", dataSuLaiku(), getIrasoNr(), getSuma(), kategorija, kurGauta, papildomaInfo, faileIssaugotas);
		return islaiduIrasas;
	}
	
	
	
}
