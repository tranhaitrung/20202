package BT_MySQL.Model;

public class CityModel {
    private int id;
    private String nameCity;
    private int population;
    private String countryCode;

    public CityModel(int id, String nameCity, int population, String countryCode) {
        this.id = id;
        this.nameCity = nameCity;
        this.population = population;
        this.countryCode = countryCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameCity() {
        return nameCity;
    }

    public void setNameCity(String nameCity) {
        this.nameCity = nameCity;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public String toString(){
        return id + " " + nameCity + " " + population + " " + countryCode;
    }
}
