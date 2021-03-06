package BT_MySQL.Model;

public class CountryModel {
    private String code; //Mã quốc gia
    private String nameCountry;
    private String continent; //Mã lục địa
    private double surfaceArea; //diện tích bề mặt
    private int population; //dân số
    private double gnp; //tổng sản phẩm
    private int cappital; //mã thủ đo

    public CountryModel(String code, String nameCountry, String continent, double surfaceArea, int population, double gnp, int cappital) {
        this.code = code;
        this.nameCountry = nameCountry;
        this.continent = continent;
        this.surfaceArea = surfaceArea;
        this.population = population;
        this.gnp = gnp;
        this.cappital = cappital;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNameCountry() {
        return nameCountry;
    }

    public void setNameCountry(String nameCountry) {
        this.nameCountry = nameCountry;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public double getSurfaceArea() {
        return surfaceArea;
    }

    public void setSurfaceArea(double surfaceArea) {
        this.surfaceArea = surfaceArea;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public double getGnp() {
        return gnp;
    }

    public void setGnp(double gnp) {
        this.gnp = gnp;
    }

    public int getCappital() {
        return cappital;
    }

    public void setCappital(int cappital) {
        this.cappital = cappital;
    }

    @Override
    public String toString(){
        return code + " " + nameCountry + " " + continent + " " + surfaceArea + " " + population + " " + gnp + " " + cappital;
    }
}
