import java.util.ArrayList;

public class PajamuIrasas extends Biudzetas{
	
	private double suma;
	private String kategorija;
	private boolean arGautaIBankoSask; // sitas
	private String papildomaInfo;
	private static int numeris;
	private String irasoNr;
	
	public String getIrasoNr() {
		return irasoNr;
	}
	
	double getSuma() {
		return suma;
	}

	public String getKategorija() {
		return kategorija;
	}

	public boolean isArGautaIBankoSask() {
		return arGautaIBankoSask;
	}

	public String getPapildomaInfo() {
		return papildomaInfo;
	}

	public static int getNumeris() {
		return numeris;
	}

	
	
	public PajamuIrasas(double suma, String kategorija, boolean arGautaIBankoSask,
			String papildomaInfo) {
		super();
		this.suma = suma;
		this.kategorija = kategorija;
		this.arGautaIBankoSask = arGautaIBankoSask;
		this.papildomaInfo = papildomaInfo;
		numeris++;
		irasoNr = "P"+numeris;
	}
		
	
	@Override
	public String toString() {
		return "Pajamu Irasas Nr " + irasoNr + ", " + dataFormatuota() + " [suma=" + suma + ", kategorija=" + kategorija + ", arGautaIBankoSask=" + arGautaIBankoSask
				+ ", papildomaInfo=" + papildomaInfo + "]";
	}
	
	
	
	
}
