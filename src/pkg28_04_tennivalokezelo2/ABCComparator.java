package pkg28_04_tennivalokezelo2;

import java.util.Comparator;

public class ABCComparator implements Comparator<Tennivalo> {

    @Override
    public int compare(Tennivalo egyik, Tennivalo masik) {
        return egyik.getNev().compareTo(masik.getNev());
    }
}
