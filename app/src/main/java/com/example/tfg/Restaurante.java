package com.example.tfg;

public class Restaurante {

    private String name;
    private String address;
    private String province;
    private int telephone;
    private double lat;
    private double lng;
    private double distance;

    // Constructor
    public Restaurante(String name, String address, String province, int telephone, double lat, double lng, double distance) {
        this.name = name;
        this.address = address;
        this.province = province;
        this.telephone = telephone;
        this.lat = lat;
        this.lng = lng;
        this.distance = distance;
    }

    // Getters y setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getDistance() {
        return distance;
    }

    public String getDistanciaKm() {
        return String.format("%.2f km", distance);
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Restaurante{" +
                "nombre='" + name + '\'' +
                ", direccion='" + address + '\'' +
                ", provincia='" + province + '\'' +
                ", telefono=" + telephone +
                ", latitud=" + lat +
                ", longitud=" + lng +
                ", distancia=" + distance +
                '}';
    }
}
