package beolvasas;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {

    private List<String> sorok;
    private ArrayList<Alkalmazott> alkalmazottak;

    public static void main(String[] args) throws IOException {
        /*
        1: ki keresi a legtöbbet?
        2: mennyi az átlag fizetés?
        3: mindenki budapesti?
        4: van 20 év feletti budapesti?
        5: milyen címek vannak eltárolva?
        6: melyik címen hányan laknak?
        7: írd ki a "nemBp.txt" fájlba FEJLÉCCEL a nem budapestiek minden adatát!
         */
        new Main().gyakorloFeladatok();

    }

    private void gyakorloFeladatok() throws IOException {
        beolvasas();

        feladat1();
        feladat2();
        feladat3();
        feladat4();
        feladat5();
        feladat6();
        feladat7();
    }

    private void feladat1() {
        //  1: ki keresi a legtöbbet?
        String nev = "";
        int max = 0;
        for (Alkalmazott dolgozo : alkalmazottak) {
            if (max < dolgozo.getFizetes()) {
                max = dolgozo.getFizetes();
                nev = dolgozo.getNev();
            }
        }
        System.out.print(max);
        System.out.println(" " + nev);
    }

    private void feladat2() {
        int osszeg = 0;
        int ahany = 0;
        sy
        for (Alkalmazott dolgozo : alkalmazottak) {
            osszeg += dolgozo.getFizetes();
            ahany++;
        }
        System.out.println(osszeg + "/" + ahany);
        int eredmeny = osszeg / ahany;
        System.out.println(eredmeny);

    }

    private void feladat3() {
        for (Alkalmazott alkalmazott : alkalmazottak) {
            if (!alkalmazott.getCim().equals("Bp")) {
                System.out.println("Nem mindenki budapesti!");
                return;
            }
        }
        System.out.println("Mindenki Budapesti!");

    }

    private void feladat4() {
        final String UZENET = "20 ev feletti budapesti.";
        boolean van = false;
        for (Alkalmazott alkalmazott : alkalmazottak) {
            if (alkalmazott.getKor() > 20 && alkalmazott.getCim().equals("Bp")) {
                van = true;
                break;
            }
        }
        if (van) {
            System.out.println("Van "+ UZENET);
        } else {
            System.out.println("Nincs "+ UZENET);
        }
    }

    private void feladat5() {

        Set<String> varosok = new HashSet<>();
        for (Alkalmazott alkalmazott : alkalmazottak) {
            varosok.add(alkalmazott.getCim());
        }
        String valasz = "Eltarolt cimek listaja: ";
        for (String varos : varosok) {
            valasz += varos + ", ";
        }
        System.out.println(valasz.substring(0, valasz.length() - 2) + ".");

    }

    private void feladat6() {

        Map<String, Integer> cimLakoMap = new HashMap<>();
        for (Alkalmazott dolgozo : alkalmazottak) {
            String cim = dolgozo.getCim();
            if (cimLakoMap.containsKey(cim)) {
                cimLakoMap.put(cim, cimLakoMap.get(cim) + 1);
            } else {
                cimLakoMap.put(cim, 1);
            }
        }
        System.out.println(cimLakoMap);
    }

    private void feladat7() throws IOException {
        FileWriter nemBp = new FileWriter("nemBp.txt");
        nemBp.write("NÉV;KOR;CÍM;FIZETÉS");
        for (Alkalmazott alkalmazott : alkalmazottak) {
            if (!alkalmazott.getCim().equals("Bp")) {
                nemBp.write("\n" + alkalmazottFileFormatum(alkalmazott));
            }
        }
        nemBp.close();

    }

    private static String alkalmazottFileFormatum(Alkalmazott alkalmazott) {
        return String.format("%s;%s;%s;%s", alkalmazott.getNev(), alkalmazott.getKor(), alkalmazott.getCim(), alkalmazott.getFizetes());
    }

    private void beolvasas() throws IOException {

        sorok = Files.readAllLines(Path.of("alkalmazottak.txt"));
        alkalmazottak = new ArrayList<>();
        for (int i = 1; i < sorok.size(); i++) {
            alkalmazottak.add(i - 1, new Alkalmazott(sorok.get(i)));
        }
    }

}
