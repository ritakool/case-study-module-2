package model;

public class Address {
    private String conscious;
    private String district;
    private String commune;
    private String apartmentNumber;

    public Address() {
    }
    public Address(String conscious, String district, String commune, String apartmentNumber) {
        this.conscious = conscious;
        this.district = district;
        this.commune = commune;
        this.apartmentNumber = apartmentNumber;
    }

    public void setConscious(String conscious) {
        this.conscious = conscious;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public String getConscious() {
        return conscious;
    }

    public String getDistrict() {
        return district;
    }

    public String getCommune() {
        return commune;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    @Override
    public String toString() {
        return " Tỉnh: " + conscious  +
                ", Huyện: " + district  +
                ", Xã: " + commune  +
                ", Số Nhà: " + apartmentNumber ;
    }
}
