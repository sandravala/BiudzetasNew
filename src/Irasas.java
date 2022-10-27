import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Irasas {
    private LocalDateTime data;
    private DateTimeFormatter formatas = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private DateTimeFormatter formatasSuLaiku = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private DateTimeFormatter laikasBeDatos = DateTimeFormatter.ofPattern("HH:mm");
    private double suma;
    private String kategorija;
    private String papildomaInfo;
    private String gryniejiBankas;
    private String irasoNr;
    private boolean saved;

// constructors

    //pridedant naują įrašą
    public Irasas(double suma, String kategorija, String papildomaInfo, String gryniejiBankas) {
        this.suma = suma;
        this.kategorija = kategorija;
        this.papildomaInfo = papildomaInfo;
        this.gryniejiBankas = gryniejiBankas;
        data = LocalDateTime.now();

    }

    //nuskaitant iš failo
    public Irasas(double suma, String kategorija, String papildomaInfo, String gryniejiBankas, String irasoNr, LocalDateTime dataNuskaityta) {
        this.suma = suma;
        this.kategorija = kategorija;
        this.papildomaInfo = papildomaInfo;
        this.gryniejiBankas = gryniejiBankas;
        this.irasoNr = irasoNr;
        data = dataNuskaityta;
        setSaved(true);
    }

    public Irasas (String s) {
    }

// getters setters

    public double getSuma() {
        return suma;
    }

    public void setSuma(double suma) {
        this.suma = suma;
    }

    public String getKategorija() {
        return kategorija;
    }

    public void setKategorija(String kategorija) {
        this.kategorija = kategorija;
    }

    public String getGryniejiBankas() {
        return gryniejiBankas;
    }
    public void setGryniejiBankas(String gryniejiBankas) {
        this.gryniejiBankas = gryniejiBankas;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(String data, String laikas) {
        this.data = LocalDateTime.parse(data+"T"+laikas+":00.000000000");
    }

    public String getPapildomaInfo() {
        return papildomaInfo;
    }

    public void setPapildomaInfo(String papildomaInfo) {
        this.papildomaInfo = papildomaInfo;
    }

    public String getIrasoNr() {
        return irasoNr;
    }

    public void setIrasoNr(String irasoNr) {
        this.irasoNr = irasoNr;
    }

    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    public String dataFormatuota() {
        return data.format(formatas);
    }

    public String dataSuLaiku() {
        return data.format(formatasSuLaiku);
    }

    public String laikasBeDatos() {
        return data.format(laikasBeDatos);
    }

}
