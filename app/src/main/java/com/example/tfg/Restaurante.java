package com.example.tfg;

/**
 * Clase que representa un restaurante con información básica como nombre,
 * dirección, ubicación, teléfono y distancia desde una posición de referencia.
 */
public class Restaurante {

    private String name;
    private String address;
    private String province;
    private int telephone;
    private double lat;
    private double lng;
    private double distance;

    /**
     * Constructor que inicializa todos los campos del restaurante.
     *
     * @param name      Nombre del restaurante.
     * @param address   Dirección del restaurante.
     * @param province  Provincia del restaurante.
     * @param telephone Número de teléfono del restaurante.
     * @param lat       Latitud del restaurante.
     * @param lng       Longitud del restaurante.
     * @param distance  Distancia desde la posición actual o referencia.
     */
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

    /**
     * Obtiene el nombre del restaurante.
     *
     * @return Nombre del restaurante.
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre del restaurante.
     *
     * @param name Nuevo nombre.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene la dirección del restaurante.
     *
     * @return Dirección del restaurante.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Establece la dirección del restaurante.
     *
     * @param address Nueva dirección.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Obtiene la provincia del restaurante.
     *
     * @return Provincia del restaurante.
     */
    public String getProvince() {
        return province;
    }

    /**
     * Establece la provincia del restaurante.
     *
     * @param province Nueva provincia.
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * Obtiene el número de teléfono del restaurante.
     *
     * @return Teléfono del restaurante.
     */
    public int getTelephone() {
        return telephone;
    }

    /**
     * Establece el número de teléfono del restaurante.
     *
     * @param telephone Nuevo teléfono.
     */
    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    /**
     * Obtiene la latitud del restaurante.
     *
     * @return Latitud geográfica.
     */
    public double getLat() {
        return lat;
    }


    /**
     * Establece la latitud del restaurante.
     *
     * @param lat Nueva latitud.
     */
    public void setLat(double lat) {
        this.lat = lat;
    }

    /**
     * Obtiene la longitud del restaurante.
     *
     * @return Longitud geográfica.
     */
    public double getLng() {
        return lng;
    }

    /**
     * Establece la longitud del restaurante.
     *
     * @param lng Nueva longitud.
     */
    public void setLng(double lng) {
        this.lng = lng;
    }

    /**
     * Obtiene la distancia desde la posición actual al restaurante.
     *
     * @return Distancia en kilómetros.
     */
    public double getDistance() {
        return distance;
    }

    /**
     * Devuelve la distancia formateada como texto con dos decimales y la unidad "km".
     *
     * @return Distancia formateada, por ejemplo: "3.50 km".
     */
    public String getDistanciaKm() {
        return String.format("%.2f km", distance);
    }

    /**
     * Establece la distancia desde la posición actual al restaurante.
     *
     * @param distance Nueva distancia en kilómetros.
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }

    /**
     * Devuelve una representación en texto del objeto Restaurante.
     *
     * @return Cadena con todos los campos del restaurante.
     */
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
