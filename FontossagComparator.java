package pkg28_04_tennivalokezelo2;

import java.util.Comparator;

public class FontossagComparator implements Comparator<Tennivalo> {

    @Override
    public int compare(Tennivalo egyik, Tennivalo masik) {
        return masik.getFontossag() - egyik.getFontossag();
    }
}
