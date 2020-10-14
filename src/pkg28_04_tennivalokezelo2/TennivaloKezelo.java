package pkg28_04_tennivalokezelo2;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class TennivaloKezelo {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String menuValaszto = "";
        Tennivalok feladatLista = new Tennivalok();
        FileIO adatKezelo = new FileIO("tennivalok.csv");
        try {
            feladatLista.setTennivaloLista(adatKezelo.betolt());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            System.out.println("");
        } finally {
            while (!menuValaszto.equals("g")) {
                tennivalokKiir(feladatLista);
                menu();
                menuValaszto = tevekenyseg(menuValaszto, feladatLista, sc);
            }
            try {
                adatKezelo.ment(feladatLista.getTennivaloLista());
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static String tevekenyseg(String menuValaszto, Tennivalok feladatLista, Scanner sc) {

        System.out.print("Mit szeretnél csinálni? ");
        menuValaszto = sc.nextLine();
        switch (menuValaszto) {
            case "a":
                System.out.print("Mi a tennivaló? ");
                String nev = sc.nextLine();
                System.out.print("Meddig kell elvégezni? (ÉÉÉÉ.HH.NN.) ");
                String datum = sc.nextLine();
                if (idoBeallito(datum) == null) {
                    menuValaszto = "g";
                    break;
                }
                System.out.print("Mennyire fontos? (1 - kicsit, 2 - közepesen 3 - nagyon) ");
                int fontossag = sc.nextInt();
                if (fontossag < 1 || fontossag > 3) {
                    System.out.println("A fontosságnak 1 és 3 között kell lennie!");
                    menuValaszto = "g";
                    break;
                }
                sc.nextLine();
                feladatLista.felvesz(new Tennivalo(nev, fontossag, idoBeallito(datum)));
                System.out.println("");
                break;
            case "b":
                System.out.print("Melyik feladatot végezted el? ");
                int feladatSorszama = sc.nextInt();
                feladatLista.elvegez(feladatSorszama);
                sc.nextLine();
                System.out.println("");
                break;
            case "c":
                feladatLista.fontossagSorrend();
                feladatLista.getHatralevoFeladatok().sort(feladatLista.getComparator());
                System.out.println("");
                break;
            case "d":
                feladatLista.hataridoSorrend();
                feladatLista.getHatralevoFeladatok().sort(feladatLista.getComparator());
                System.out.println("");
                break;
            case "e":
                feladatLista.abcSorrend();
                feladatLista.getHatralevoFeladatok().sort(feladatLista.getComparator());
                System.out.println("");
                break;
            case "f":
                elvegzettTennivalokKiir(feladatLista);
                break;
            case "g":
                System.out.print("Köszi, szia!");
                break;
            default:
                System.out.print("Hibás menüpont!");
                menuValaszto = "g";
                break;
        }
        return menuValaszto;
    }

    public static LocalDate idoBeallito(String datum) {
        try {
            return LocalDate.parse(datum, DateTimeFormatter.ofPattern("yyyy.MM.dd."));
        } catch (DateTimeParseException ex) {
            System.out.println("Nem megfelelő formátum");
        }
        return null;
    }

    public static void tennivalokKiir(Tennivalok feladatLista) {
        System.out.printf("\t\t\t%s\n\n", "E L I N T É Z E N D Ő   F E L A D A T O K");
        System.out.printf("%-4s| " + "%-12s| " + "%-17s| " + "%-40s| " + "%s" + "\n\n", "#", "Határidő", "Fontosság", "Feladat", "Elintézve");
        for (int i = 0; i < feladatLista.getHatralevoFeladatok().size(); i++) {
            Tennivalo f = (Tennivalo) feladatLista.getHatralevoFeladatok().get(i);
            System.out.printf("%-4s| " + "%-12s| " + "%-17s| " + "%-40s| " + "%s" + "\n", f.getSorszam(), DateTimeFormatter.ofPattern("yyyy.MM.dd.").format(f.getHatarido()), fontossagToString(f.getFontossag()), f.getNev(), elvegzettToString(f.isElvegzett()));
        }   
        if (!feladatLista.getHatralevoFeladatok().isEmpty()) {
            System.out.println("");
        }        
    }

    public static void elvegzettTennivalokKiir(Tennivalok feladatLista) {
        System.out.println("");
        System.out.printf("\t\t\t%s\n\n", "E L V É G Z E T T   F E L A D A T O K");
        System.out.printf("%-4s| " + "%-12s| " + "%-17s| " + "%-40s| " + "%s" + "\n\n", "#", "Határidő", "Fontosság", "Feladat", "Elintézve");
        for (int i = 0; i < feladatLista.getElvegzettFeladatok().size(); i++) {
            Tennivalo f = (Tennivalo) feladatLista.getElvegzettFeladatok().get(i);
            System.out.printf("%-4s| " + "%-12s| " + "%-17s| " + "%-40s| " + "%s" + "\n", f.getSorszam(), DateTimeFormatter.ofPattern("yyyy.MM.dd.").format(f.getHatarido()), fontossagToString(f.getFontossag()), f.getNev(), elvegzettToString(f.isElvegzett()));
        }
        if (!feladatLista.getElvegzettFeladatok().isEmpty()) {
            System.out.println("");
        } 
    }

    public static String fontossagToString(int fontossag) {
        switch (fontossag) {
            case 1:
                return "kicsit fontos";
            case 2:
                return "közepesen fontos";
            case 3:
                return "nagyon fontos";
            default:
                return null;
        }
    }

    public static String elvegzettToString(boolean b) {
        String s = b ? "igen" : "nem";
        return s;
    }

    public static void menu() {
        System.out.println("Menü:");
        System.out.printf("%-4s| " + "%s" + "\n", "(a)", "Tennivaló felvitele");
        System.out.printf("%-4s| " + "%s" + "\n", "(b)", "Tennivaló elvégzése");
        System.out.printf("%-4s| " + "%s" + "\n", "(c)", "Rendezés fontosság szerint, csökkenő sorrendbe");
        System.out.printf("%-4s| " + "%s" + "\n", "(d)", "Rendezés határidő szerint, növekvő sorrendbe");
        System.out.printf("%-4s| " + "%s" + "\n", "(e)", "Rendezés név (ABC) szerint, növekvő sorrendbe");
        System.out.printf("%-4s| " + "%s" + "\n", "(f)", "Elvégzett feladatok megjelenítése a listában");
        System.out.printf("%-4s| " + "%s" + "\n", "(g)", "Kilépés");
    }
}
