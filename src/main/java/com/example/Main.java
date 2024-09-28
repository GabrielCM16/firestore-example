package com.example;

import java.util.Timer;
import java.util.TimerTask;

public class Main {
    private static Firebase firebase;
    private static SensorMonitor sensorMonitor;

    public static void main(String[] args) {
        firebase = new Firebase();
        firebase.inicializar();
        
        // Cadastrar usuários
        firebase.cadastrarUsuario("user1", "Gabriel", "Costa", "gabriel.costa@gmail.com");
        firebase.cadastrarUsuario("user2", "Pedro", "Moraes", "pedro@gmail.com");
        
        // Buscar um usuário
        firebase.buscarUsuario("user2");
        System.out.println("\n\n\n\n");

        // Monitoramento de sensores
        sensorMonitor = new SensorMonitor();
        sensorMonitor.db = firebase.db; // Atribui o banco de dados ao monitor de sensores

        //updateSensor();

        // Exemplo de como usar o método monitorRobot
        //Robot robot1 = new Robot("robot1");
        //sensorMonitor.monitorRobot(robot1);

        // Exibe as informações do robô
        //robot1.displayRobotInfo();


        System.out.println("\n\n\n");
        System.out.println("Consulta do robos com temperatura maior que 70:\n");
        sensorMonitor.queryRobotsWithHighTemperature70();

        System.out.println("\n\n\n");
        System.out.println("Consulta dos 5 robos com as maiores temperaturas:\n");
        sensorMonitor.queryRobotsWithHighTemperatures();
        
    }


    private static void updateSensor() {
        // Atualiza os valores dos sensores a cada 10 segundos
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // Atualiza sensores para 10 robôs diferentes
                for (int i = 1; i <= 10; i++) {
                    String robotId = "robot" + i; // Define o ID do robô (robot1, robot2, ..., robot10)
                    sensorMonitor.updateSensorValues(robotId);
                }
            }
        }, 0, 10000); // A cada 10 segundos
    }
    
}
