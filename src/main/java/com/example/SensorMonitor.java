package com.example;

import java.util.HashMap;
import java.util.Map;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.EventListener;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreException;
import com.google.cloud.firestore.GeoPoint;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import com.google.cloud.firestore.FieldValue;
import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.Query;

import java.util.List;

public class SensorMonitor {

    public Firestore db;


    
    public Robot fetchRobotData(String IDrobot) {

        Robot robot = new Robot(IDrobot);

        // Busca o documento do Firestore de forma assíncrona
        ApiFuture<DocumentSnapshot> future = db.collection("robots").document(IDrobot).get();
        
        try {
            // Obtenha o resultado da consulta
            DocumentSnapshot snapshot = future.get();
            
            if (snapshot.exists()) {
                // Atualiza os valores do robô com os dados do snapshot
                Double temperature = snapshot.getDouble("temperature");
                Double magnitude = snapshot.getDouble("magnitude");
                GeoPoint location = snapshot.getGeoPoint("location");
                Timestamp lastUpdated = snapshot.getTimestamp("lastUpdated");
                Double humidity = snapshot.getDouble("humidity");
                Double energyConsumption = snapshot.getDouble("energyConsumption");
    
                // Atualiza os atributos do robô
                if (temperature != null) robot.setTemperature(temperature);
                if (magnitude != null) robot.setMagnitude(magnitude);
                if (location != null) robot.setLocation(location);
                if (lastUpdated != null) robot.setLastUpdated(lastUpdated);
                if (humidity != null) robot.setHumidity(humidity);
                if (energyConsumption != null) robot.setEnergyConsumption(energyConsumption);
    
                // Exibe as informações do robô (opcional)
                //robot.displayRobotInfo();
            } else {
                System.out.println("Dados do robô " + IDrobot + " não encontrados.");
            }
        } catch (Exception e) {
            System.err.println("Erro ao buscar os dados do robô: " + e.getMessage());
        }

        return robot;
    }


    



    

    // Método para monitorar a temperatura e a detecção de terremoto de um robô em tempo real
    public void monitorRobot(Robot robot) {
        db.collection("robots").document(robot.getId())
            .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(DocumentSnapshot snapshot, FirestoreException e) {
                    if (e != null) {
                        System.err.println("Erro ao escutar as alterações do robô: " + e.getMessage());
                        return;
                    }

                    if (snapshot != null && snapshot.exists()) {
                        // Atualiza os valores do robô com os dados do snapshot
                        Double temperature = snapshot.getDouble("temperature");
                        Double magnitude = snapshot.getDouble("magnitude");
                        GeoPoint location = snapshot.getGeoPoint("location");
                        Timestamp lastUpdated = snapshot.getTimestamp("lastUpdated");
                        Double humidity = snapshot.getDouble("humidity");
                        Double energyConsumption = snapshot.getDouble("energyConsumption");

                        // Atualiza os atributos do robô
                        if (temperature != null) robot.setTemperature(temperature);
                        if (magnitude != null) robot.setMagnitude(magnitude);
                        if (location != null) robot.setLocation(location);
                        if (lastUpdated != null) robot.setLastUpdated(lastUpdated);
                        if (humidity!= null) robot.setHumidity(humidity);
                        if (energyConsumption!= null) robot.setEnergyConsumption(energyConsumption);

                        // Exibe as informações do robô
                        robot.displayRobotInfo();


                        // Verificações de segurança
                        robot.checkTemperature();
                        robot.checkEarthquake();
                    } else {
                        System.out.println("Dados do robô " + robot.getId() + " não encontrados.");
                    }
                }
            });
    }








    // Método para consultar robôs com temperatura superior a 70
    public void queryRobotsWithHighTemperature70() {
        ApiFuture<QuerySnapshot> future = db.collection("robots")
            .whereGreaterThan("temperature", 70) // Filtro para temperatura maior que 70
            .get(); // Executa a consulta

        try {
            QuerySnapshot querySnapshot = future.get(); // Espera o resultado da consulta
            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();

            if (documents.isEmpty()) {
                System.out.println("Nenhum robô encontrado com temperatura superior a 70.");
            } else {
                for (QueryDocumentSnapshot document : documents) {
                    String robotId = document.getId();
                    Double temperature = document.getDouble("temperature");
                    Double magnitude = document.getDouble("magnitude");
                    GeoPoint location = document.getGeoPoint("location");
                    Timestamp lastUpdated = document.getTimestamp("lastUpdated");
                    Double humidity = document.getDouble("humidity");
                    Double energyConsumption = document.getDouble("energyConsumption");

                    // Exibe os dados dos robôs encontrados
                    System.out.println("ID do robô: " + robotId);
                    System.out.println("Temperatura: " + temperature);
                    System.out.println("Magnitude do terremoto: " + magnitude);
                    System.out.println("Localização: " + location.getLatitude() + ", " + location.getLongitude());
                    System.out.println("Última atualização: " + lastUpdated.toDate());
                    System.out.println("Umidade: " + humidity);
                    System.out.println("Energia consumida (W): " + energyConsumption);
                    System.out.println("\n");
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao consultar robôs: " + e.getMessage());
        }
    }









    // Método para consultar robôs com as maiores temperaturas
    public void queryRobotsWithHighTemperatures() {
        ApiFuture<QuerySnapshot> future = db.collection("robots")
            .orderBy("temperature", Query.Direction.DESCENDING) // Ordena em ordem decrescente
            .limit(5) // Retorna apenas os 5 robôs com a maior temperatura
            .get();          

        try {
            QuerySnapshot querySnapshot = future.get(); // Espera o resultado da consulta
            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();

            if (documents.isEmpty()) {
                System.out.println("Nenhum robô encontrado");
            } else {
                for (QueryDocumentSnapshot document : documents) {
                    String robotId = document.getId();
                    Double temperature = document.getDouble("temperature");
                    Double magnitude = document.getDouble("magnitude");
                    GeoPoint location = document.getGeoPoint("location");
                    Timestamp lastUpdated = document.getTimestamp("lastUpdated");
                    Double humidity = document.getDouble("humidity");
                    Double energyConsumption = document.getDouble("energyConsumption");

                    // Exibe os dados dos robôs encontrados
                    System.out.println("ID do robô: " + robotId);
                    System.out.println("Temperatura: " + temperature);
                    System.out.println("Magnitude do terremoto: " + magnitude);
                    System.out.println("Localização: " + location.getLatitude() + ", " + location.getLongitude());
                    System.out.println("Última atualização: " + lastUpdated.toDate());
                    System.out.println("Umidade: " + humidity);
                    System.out.println("Energia consumida (W): " + energyConsumption);
                    System.out.println("\n");
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao consultar robôs: " + e.getMessage());
        }
    }







    
    // Método para consultar robôs com varias consultas
    public void queryRobotsWithMuitasConsultas(int temperatura, int umidade, int energy) {
        ApiFuture<QuerySnapshot> future = db.collection("robots")
            .whereGreaterThan("temperature", temperatura) // Filtro para temperatura maior que 70
            .whereGreaterThan("humidity", umidade)    // Filtro para umidade maior que 50%
            .whereGreaterThan("energyConsumption", energy) // Filtro para energia consumida maior que 300W    
            .get(); // Executa a consulta

        try {
            QuerySnapshot querySnapshot = future.get(); // Espera o resultado da consulta
            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();

            if (documents.isEmpty()) {
                System.out.println("Nenhum robô encontrado com os critérios de temperatura > " + temperatura + 
                ", umidade > " + umidade +
                " e energia consumida > " + energy);
            } else {
                for (QueryDocumentSnapshot document : documents) {
                    String robotId = document.getId();
                    Double temperature = document.getDouble("temperature");
                    Double magnitude = document.getDouble("magnitude");
                    GeoPoint location = document.getGeoPoint("location");
                    Timestamp lastUpdated = document.getTimestamp("lastUpdated");
                    Double humidity = document.getDouble("humidity");
                    Double energyConsumption = document.getDouble("energyConsumption");

                    // Exibe os dados dos robôs encontrados
                    System.out.println("ID do robô: " + robotId);
                    System.out.println("Temperatura: " + temperature);
                    System.out.println("Magnitude do terremoto: " + magnitude);
                    System.out.println("Localização: " + location.getLatitude() + ", " + location.getLongitude());
                    System.out.println("Última atualização: " + lastUpdated.toDate());
                    System.out.println("Umidade: " + humidity);
                    System.out.println("Energia consumida (W): " + energyConsumption);
                    System.out.println("\n");
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao consultar robôs: " + e.getMessage());
        }
    }







    // Atualiza os valores dos sensores e adiciona timestamp e localização
    public void updateSensorValues(String robotId) {
        // Simula novos valores
        double newTemperature = Math.random() * 100; // Temperatura aleatória
        double magnitude = Math.random() < 0.1 ? Math.random() * 10 : 0; // 10% de chance de terremoto
        GeoPoint newLocation = new GeoPoint(-23.55052, -46.633308); // Exemplo de coordenadas (São Paulo)
        double humidity = Math.random() * 100; // Umidade entre 0 e 100%
        double energyConsumption = Math.random() * 500; // Consumo de energia em watts

        Map<String, Object> updates = new HashMap<>();
        updates.put("temperature", newTemperature);
        updates.put("magnitude", magnitude);
        updates.put("humidity", humidity);
        updates.put("location", newLocation); // Adiciona localização (latitude e longitude)
        updates.put("lastUpdated", FieldValue.serverTimestamp()); // Timestamp do momento da atualização
        updates.put("energyConsumption", energyConsumption);

        try {
            db.collection("robots").document(robotId).set(updates).get(); // Atualiza o documento no Firestore
            System.out.println("Dados do robô atualizados: Temperatura = " + newTemperature + ", Magnitude = " + magnitude);
        } catch (Exception e) {
            System.err.println("Falha ao atualizar os dados do robô: " + e.getMessage());
        }
    }
}
