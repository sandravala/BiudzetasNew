import java.time.LocalDateTime;

public class IslaiduIrasas extends Irasas{
	private static int numeris;
	private String irasoNr;
	
	public String getIrasoNr() {
		return irasoNr;
	}

	public static int getNumeris() {
		return numeris;
	}

	public IslaiduIrasas(double suma, String kategorija, String papildomaInfo, String gryniejiBankas) {
		super(suma, kategorija, papildomaInfo, gryniejiBankas);
		super.dataSuLaiku();
		numeris++;
		irasoNr = "I"+numeris;
		super.setIrasoNr(irasoNr);
	}

	public IslaiduIrasas(double suma, String kategorija, String papildomaInfo, String gryniejiBankas, String irasoNr, LocalDateTime dataNuskaityta) {
		super(suma, kategorija, papildomaInfo, gryniejiBankas, irasoNr, dataNuskaityta);
		numeris++;
		this.irasoNr = irasoNr;
	}
	@Override
	public String toString() {
		return "Islaidu irasas Nr " + irasoNr + ", " + super.dataSuLaiku() + "  [suma=" + super.getSuma() + ", kategorija=" + super.getKategorija() + ", atsiskaitymoBudas=" + super.getGryniejiBankas()
				+ ", papildomaInfo=" + super.getPapildomaInfo() + "]";
	}
	
	
	
}
