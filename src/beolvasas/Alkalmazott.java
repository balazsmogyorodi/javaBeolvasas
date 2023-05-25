package beolvasas;

class Alkalmazott {

    private String nev;
    private int kor;
    private String cim;
    private int fizetes;

    public Alkalmazott(String sor) {
        String[] s = sor.split(";");
        this.nev = s[0];
        this.kor = Integer.parseInt(s[1]);
        this.cim = s[2];
        this.fizetes = Integer.parseInt(s[3]);
    }

    public Alkalmazott(String nev, int kor, String cim, int fizetes) {
        this.nev = nev;
        this.kor = kor;
        this.cim = cim;
        this.fizetes = fizetes;
    }

    public String getNev() {
        return nev;
    }

    public int getKor() {
        return kor;
    }

    public String getCim() {
        return cim;
    }

    public int getFizetes() {
        return fizetes;
    }

    @Override
    public String toString() {
        return "Alkalmazott{" + "nev=" + nev + ", kor=" + kor + ", cim=" + cim + ", fizetes=" + fizetes + '}';
    }

}
