package nossafirma.com.br.meuapp.model;

public class Store {

    private Integer id;
    private String name;
    private Region region;
    private Beer beer;
    private Double value;

    public Store(){}

    public Store(Integer id, String name,
                 Region region, Beer beer,
                 Double value){
        this.setId(id);
        this.setName(name);
        this.setRegion(region);
        this.setBeer(beer);
        this.setValue(value);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Beer getBeer() {
        return beer;
    }

    public void setBeer(Beer beer) {
        this.beer = beer;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}