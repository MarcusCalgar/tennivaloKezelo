package pkg28_04_tennivalokezelo2;

import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public class Tennivalok {

    private List<Tennivalo> tennivaloLista = new ArrayList<>();
    private Comparator<Tennivalo> comp;            

    public Tennivalok() {
    }

    public Tennivalok(List lista, Comparator comparator) {
        tennivaloLista = lista;
        comp = comparator;
    }

    public void setTennivaloLista(List lista) {
        tennivaloLista = lista;
    }

    public List getTennivaloLista() {
        return tennivaloLista;
    }

    public void setComparator(Comparator comparator) {
        comp = comparator;
    }

    public Comparator getComparator() {
        return comp;
    }

    public List getElvegzettFeladatok() {
        List<Tennivalo> ujLista = new ArrayList<>();
        for (Tennivalo tennivalo : tennivaloLista) {
            if (tennivalo.isElvegzett()) {
                ujLista.add(tennivalo);
            }
        }
        return ujLista;
    }

    public List getHatralevoFeladatok() {
        List<Tennivalo> ujLista = new ArrayList<>();
        for (Tennivalo tennivalo : tennivaloLista) {
            if (!tennivalo.isElvegzett()) {
                ujLista.add(tennivalo);
            }
        }
        if (comp != null) {
            ujLista.sort(comp);
        }
        return ujLista;
    }

    public void felvesz(Tennivalo t) {
        tennivaloLista.add(t);
    }

    public void elvegez(int elvegzendo) {
        for (Tennivalo t : tennivaloLista) {
            if (t.getSorszam() == elvegzendo) {
                t.setElvegzett(true);
            }
        }
    }

    public void fontossagSorrend() {
        comp = new FontossagComparator();
    }

    public void hataridoSorrend() {
        comp = new HataridoComparator();
    }

    public void abcSorrend() {
        comp = new ABCComparator();
    }
}
