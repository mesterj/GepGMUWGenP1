package com.kite.joco.gepgmuwgenp1;

import java.util.ArrayList;

/**
 * Created by Mester József on 2017. 10. 06..
 */


public class AlfaNumSorGenerator {

    String[] elemek = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O",  "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    ArrayList<String> elemlista = new ArrayList<>();

    public AlfaNumSorGenerator() {
        for (int elem = 0; elem < elemek.length; elem++) {
            elemlista.add(elemek[elem]);
        }
    }

    public StringBuffer getNext(StringBuffer aktsorszam) {
        int hossz = aktsorszam.length();
        int akthely = hossz - 1;
        StringBuffer vegestring = new StringBuffer();

        for (int i = hossz; i > 0; i--) {
            if (aktsorszam.substring(i - 1, i).equals("Z") && akthely > 0) {
                akthely--;
                vegestring.append("0");

            } else if (akthely < 0) {
                throw new ArrayIndexOutOfBoundsException("Nincs több kiadható sorszám");
            } else {
                break;
            }
        }

        int kovindex = elemlista.indexOf(aktsorszam.substring(akthely, akthely + 1)) + 1;
        if (kovindex < elemlista.size()) {
            String kovstring = elemlista.get(kovindex);
            if (vegestring.length() == 0) {
                aktsorszam.replace(akthely, hossz, kovstring);
            } else {
                StringBuilder ujvege = new StringBuilder(kovstring);
                ujvege.append(vegestring);
                aktsorszam.replace(akthely, hossz, ujvege.toString());
            }

            return aktsorszam;
        } else {
            throw new ArrayIndexOutOfBoundsException("Nem lehet több sorszámot kiosztani");
        }
    }
}


