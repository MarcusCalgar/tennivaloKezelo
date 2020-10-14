package pkg28_04_tennivalokezelo2;

import java.time.LocalDate;

public class Tennivalo {

    private String nev;
    private int fontossag;
    private LocalDate hatarido;
    private boolean elvegzett;
    public static int ID = 1;
    private int sorszam;

    public Tennivalo(String nev, int fontossag, LocalDate hatarido) {
        this.nev = nev;
        this.fontossag = fontossag;
        this.hatarido = hatarido;
        sorszam = ID;
        ID++;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public LocalDate getHatarido() {
        return hatarido;
    }

    public void setHatarido(LocalDate datum) {
        hatarido = datum;
    }

    public int getFontossag() {
        return fontossag;
    }    

    public void setFontossag(int fontossag) {
        this.fontossag = fontossag;
    }

    public boolean isElvegzett() {
        return elvegzett;
    }

    public void setElvegzett(boolean elvegzett) {
        this.elvegzett = elvegzett;
    }

    public int getID() {
        return ID;
    }

    public int getSorszam() {
        return sorszam;
    }
}
