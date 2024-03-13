import java.math.BigDecimal;
import java.time.LocalDate;

public class Projekt {
    private String nazev;
    private int pocet;
    private BigDecimal naklady;
    private int hodnoceni;
    private LocalDate datum;
    private boolean dokonceno;

    public Projekt(String nazev, int pocet, BigDecimal naklady, int hodnoceni, LocalDate datum, boolean dokonceno) {
        this.nazev = nazev;
        this.pocet = pocet;
        this.naklady = naklady;
        this.hodnoceni = hodnoceni;
        this.datum = datum;
        this.dokonceno = dokonceno;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public int getPocet() {
        return pocet;
    }

    public void setPocet(int pocet) {
        this.pocet = pocet;
    }

    public boolean isDokonceno() {
        return dokonceno;
    }

    public void setDokonceno(boolean dokonceno) {
        this.dokonceno = dokonceno;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public int getHodnoceni() {
        return hodnoceni;
    }

    public void setHodnoceni(int hodnoceni) {
        this.hodnoceni = hodnoceni;
    }

    public BigDecimal getNaklady() {
        return naklady;
    }

    public void setNaklady(BigDecimal naklady) {
        this.naklady = naklady;
    }
}
