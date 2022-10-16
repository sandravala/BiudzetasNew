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

	public PajamuIrasas(double suma, String kategorija, String papildomaInfo, String gryniejiBankas) {
		super(suma, kategorija, papildomaInfo, gryniejiBankas);
		numeris++;
		irasoNr = "P"+numeris;
		super.setIrasoNr(irasoNr);
	}

	public PajamuIrasas(double suma, String kategorija, String papildomaInfo, String gryniejiBankas, String irasoNr, LocalDateTime dataNuskaityta) {
		super(suma, kategorija, papildomaInfo, gryniejiBankas, irasoNr, dataNuskaityta);
		numeris++;
		this.irasoNr = irasoNr;
	}
		
	
	@Override
	public String toString() {
		return "Pajamu Irasas Nr " + irasoNr + ", " + super.dataFormatuota() + " [suma=" + super.getSuma() + ", kategorija=" + super.getKategorija() + ", arGautaIBankoSask=" + super.getGryniejiBankas()
				+ ", papildomaInfo=" + super.getPapildomaInfo() + "]";
	}
	
	
	
	
}
