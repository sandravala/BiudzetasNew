import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileReadWrite {

    // fileName reikia imti pagal pavadinima biudzeto, o biudzeto konstruktoriuj daryti varda final.

    static void writeFile(String fileName, String content) {
        try {
            File theDir = new File(getPath());
            if (!theDir.exists())
                theDir.mkdirs();

            File file = new File(getPath() + fileName);

            // if file doesnt exist, then create it
            if (!file.exists())
                file.createNewFile();

            BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsoluteFile(),true));

            bw.write(content);
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void overWriteFile(String fileName) {
        try {
            File theDir = new File(getPath());
            if (!theDir.exists())
                theDir.mkdirs();

            File file = new File(getPath() + fileName);

            // if file doesnt exist, then create it
            if (!file.exists())
                file.createNewFile();

            BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));

            bw.write("");
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static List<Irasas> readFile(String fileName) {
        String line = null;
        ArrayList<Irasas> nuskaitytiIrasai = new ArrayList<>();
        try {
            File fin = new File(getPath() + fileName);
            FileInputStream fis = null;
            fis = new FileInputStream(fin);

            // Construct BufferedReader from InputStreamReader
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));

            while ((line = br.readLine()) != null) {
                if (!line.isBlank()) {
                    String[] nuskaitytaEilute;
                    nuskaitytaEilute = line.split(",");
                    double suma = Double.parseDouble(nuskaitytaEilute[0]);
                    String kategorija = nuskaitytaEilute[1];
                    String papildomaInfo = nuskaitytaEilute[2];
                    String gryniejiBankas = nuskaitytaEilute[3];
                    String irasoNr = nuskaitytaEilute[4];
                    LocalDateTime dataNuskaityta = LocalDateTime.parse(nuskaitytaEilute[5]);
                    if (irasoNr.contains("P")) {
                        nuskaitytiIrasai.add(new PajamuIrasas(suma, kategorija, papildomaInfo, gryniejiBankas, irasoNr, dataNuskaityta));
                    } else if (irasoNr.contains("I")) {
                        nuskaitytiIrasai.add(new IslaiduIrasas(suma, kategorija, papildomaInfo, gryniejiBankas, irasoNr, dataNuskaityta));
                    }
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return nuskaitytiIrasai;
    }

    static String getPath() {
        String currentPath = null;
        try {
            currentPath = new File(".").getCanonicalPath();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //System.out.println("Current dir:" + currentPath);
        return currentPath;
    }
}
