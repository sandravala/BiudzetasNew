import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Irasas {
    private LocalDateTime data;
    private DateTimeFormatter formatas = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private DateTimeFormatter formatasSuLaiku = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private double suma;
    private String kategorija;
    private String papildomaInfo;
    private String gryniejiBankas;
    private String irasoNr;

    public Irasas(double suma, String kategorija, String papildomaInfo, String gryniejiBankas) {
        this.suma = suma;
        this.kategorija = kategorija;
        this.papildomaInfo = papildomaInfo;
        this.gryniejiBankas = gryniejiBankas;
        data = LocalDateTime.now();

    }

    public Irasas(double suma, String kategorija, String papildomaInfo, String gryniejiBankas, String irasoNr, LocalDateTime dataNuskaityta) {
        this.suma = suma;
        this.kategorija = kategorija;
        this.papildomaInfo = papildomaInfo;
        this.gryniejiBankas = gryniejiBankas;
        this.irasoNr = irasoNr;
        data = dataNuskaityta;
    }

    public String getGryniejiBankas() {
        return gryniejiBankas;
    }
    public void setGryniejiBankas(String gryniejiBankas) {
        this.gryniejiBankas = gryniejiBankas;
    }

    public String getKategorija() {
        return kategorija;
    }

    public void setKategorija(String kategorija) {
        this.kategorija = kategorija;
    }

    public LocalDateTime getData() {
        return data;
    }

    public DateTimeFormatter getFormatas() {
        return formatas;
    }

    public DateTimeFormatter getFormatasSuLaiku() {
        return formatasSuLaiku;
    }

    public String getPapildomaInfo() {
        return papildomaInfo;
    }

    public void setPapildomaInfo(String papildomaInfo) {
        this.papildomaInfo = papildomaInfo;
    }

    public double getSuma() {
        return suma;
    }

    public void setSuma(double suma) {
        this.suma = suma;
    }

    public String getIrasoNr() {
        return irasoNr;
    }

    public void setIrasoNr(String irasoNr) {
        this.irasoNr = irasoNr;
    }

    public String dataFormatuota() {
        return data.format(formatas);
    }

    public String dataSuLaiku() {
        return data.format(formatasSuLaiku);
    }
}
