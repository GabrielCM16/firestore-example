package com.example;

import com.google.cloud.firestore.GeoPoint;
import com.google.cloud.Timestamp;

public class Robot {

    private String id;
    private double temperature;
    private double magnitude;
    private GeoPoint location;
    private Timestamp lastUpdated;
    private double humidity;
    private double energyConsumption;

    // Construtor para inicializar um robô com um ID
    public Robot(String id) {
        this.id = id;
    }

    public double getHumidity(){
        return humidity;
    }

    public void setHumidity(double humidity){
        this.humidity = humidity;
    }

    public double getEnergyConsumption(){
        return energyConsumption;
    }

    public void setEnergyConsumption(double energyConsumption){
        this.energyConsumption = energyConsumption;
    }

    // Getters e Setters para os atributos do robô
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(double magnitude) {
        this.magnitude = magnitude;
    }

    public GeoPoint getLocation() {
        return location;
    }

    public void setLocation(GeoPoint location) {
        this.location = location;
    }

    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    // Exibe as informações do robô
    public void displayRobotInfo() {
        System.out.println("\n\n");
        System.out.println("ID do robô: " + id);
        System.out.println("Temperatura atual: " + temperature);
        System.out.println("Magnitude do terremoto: " + magnitude);
        System.out.println("Umidade: " + humidity);
        System.out.println("Energia consumida (W): " + energyConsumption);
        
        if (location != null) {
            System.out.println("Localização: " + location.getLatitude() + ", " + location.getLongitude());
        }
        if (lastUpdated != null) {
            System.out.println("Última atualização: " + lastUpdated.toDate());
        }
    }

    // Verifica se a temperatura está em um nível crítico
    public void checkTemperature() {
        if (temperature > 80) {
            System.out.println("ALERTA: Temperatura crítica detectada no robô " + id + "!");
        }
    }

    // Verifica se houve detecção de terremoto
    public void checkEarthquake() {
        if (magnitude > 0) {
            System.out.println("ALERTA: Terremoto detectado com magnitude " + magnitude + " no robô " + id + "!");
        }
    }
}
