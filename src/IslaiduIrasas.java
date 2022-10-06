public class IslaiduIrasas extends Biudzetas{

	private double suma;
	private String kategorija;
	private String atsiskaitymoBudas; // sitas 
	private String papildomaInfo;
	private static int numeris;
	private String irasoNr;
	
	public String getIrasoNr() {
		return irasoNr;
	}
	
	public double getSuma() {
		return suma;
	}

	public String getKategorija() {
		return kategorija;
	}

	public String getAtsiskaitymoBudas() {
		return atsiskaitymoBudas;
	}

	public String getPapildomaInfo() {
		return papildomaInfo;
	}

	public static int getNumeris() {
		return numeris;
	}

	public IslaiduIrasas(double suma, String kategorija, String atsiskaitymoBudas, String papildomaInfo) {
		super();
		this.suma = suma;
		this.kategorija = kategorija;
		this.atsiskaitymoBudas = atsiskaitymoBudas;
		this.papildomaInfo = papildomaInfo;
		numeris++;
		irasoNr = "I"+numeris;
	}

	@Override
	public String toString() {
		return "Islaidu irasas Nr " + irasoNr + ", " + dataSuLaiku() + "  [suma=" + suma + ", kategorija=" + kategorija + ", atsiskaitymoBudas=" + atsiskaitymoBudas
				+ ", papildomaInfo=" + papildomaInfo + "]";
	}
	
	
	
}
