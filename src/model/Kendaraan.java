package model;

public class Kendaraan {
    private int id;
    private String merek;
    private String plat;
    private String warna;
    private String tahun;

    public Kendaraan() {}

    public Kendaraan(int id, String merek, String plat, String warna, String tahun) {
        this.id = id;
        this.merek = merek;
        this.plat = plat;
        this.warna = warna;
        this.tahun = tahun;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getMerek() { return merek; }
    public void setMerek(String merek) { this.merek = merek; }

    public String getPlat() { return plat; }
    public void setPlat(String plat) { this.plat = plat; }

    public String getWarna() { return warna; }
    public void setWarna(String warna) { this.warna = warna; }

    public String getTahun() { return tahun; }
    public void setTahun(String tahun) { this.tahun = tahun; }
}
