package pkg28_04_tennivalokezelo2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class FileIO {

    private String fajlNev;

    public FileIO(String fileName) {
        this.fajlNev = fileName;
    }

    public String getFileName() {
        return fajlNev;
    }

    public void setFileName(String fileName) {
        this.fajlNev = fileName;
    }

    public void ment(List<Tennivalo> feladatok) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fajlNev))) {
            for (Tennivalo t : feladatok) {
                pw.println(t.getNev() + ";" + t.getFontossag() + ";" + t.getHatarido() + ";" + t.isElvegzett());
            }
        } catch (IOException ex) {
            throw new IOException("Hiba a " + fajlNev + " fájl olvasása / írása közben.");
        }
    }

    public List<Tennivalo> betolt() throws IOException, FileNotFoundException {
        List<Tennivalo> tennivaloLista = new ArrayList<>();
        String[] beolvasottSor;
        try (BufferedReader br = new BufferedReader(new FileReader(fajlNev))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.startsWith("#")) {
                    beolvasottSor = line.split(";");
                    sorEllenorzo(beolvasottSor);
                    Tennivalo t = new Tennivalo(beolvasottSor[0], Integer.parseInt(beolvasottSor[1]), LocalDate.parse(beolvasottSor[2], DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                    t.setElvegzett(beolvasottSor[3].equals("true"));
                    tennivaloLista.add(t);
                }
            }
        } catch (FileNotFoundException ex) {
            throw new FileNotFoundException("A fájl nem létezik, a program üres listával indul.");
        } catch (IOException ex) {
            throw new IOException("Fájl olvasási hiba: " + ex.getMessage());
        }
        return tennivaloLista;
    }

    public void sorEllenorzo(String[] beolvasottSor) throws IOException {
        nevEllenorzo(beolvasottSor[0]);
        fontossagEllenorzo(beolvasottSor[1]);
        datumEllenorzo(beolvasottSor[2]);
        elintezettsegEllenorzo(beolvasottSor[3]);
    }

    public void datumEllenorzo(String datum) throws IOException {
        try {
            LocalDate.parse(datum, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException ex) {
            throw new IOException("Nem megfelelő formátumú dátum mező.");
        }
    }

    public void nevEllenorzo(String nev) throws IOException {
        if (!nev.matches("[a-zA-Z .áéíöőóúüűÁÉÍÖŐÓÚÜŰ]{1,30}")) {
            throw new IOException("Nem megfelelő formátumú név mező.");
        }
    }

    public void fontossagEllenorzo(String ID) throws IOException {
        if (!ID.matches("[1-3]")) {
            throw new IOException("Nem megfelelő formátumú fontosság mező.");
        }
    }

    public void elintezettsegEllenorzo(String elintezett) throws IOException {
        if (!elintezett.matches("true") && !elintezett.matches("false")) {
            throw new IOException("Nem megfelelő formátumú elintézve mező.");
        }
    }
}
