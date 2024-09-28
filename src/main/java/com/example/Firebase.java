package com.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

public class Firebase {
    public Firestore db;

    public void inicializar() {
        try {
            // Carregar o arquivo JSON com as credenciais
            FileInputStream serviceAccount = new FileInputStream("src/main/resources/teste-firebase-adminsdk.json");
            // Configurar as opções do Firebase
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://teste-38055.firebaseio.com") // URL do Firestore
                    .build();

            // Inicializar o Firebase
            try {
                FirebaseApp.initializeApp(options);
                // Obter uma instância do Firestore
                db = FirestoreClient.getFirestore();
                System.out.println("Firebase inicializado com sucesso!");
            } catch (Exception e) {
                System.out.println("Falha ao inicializar o Firebase: " + e.getMessage());
                return; // Encerra o programa se falhar ao inicializar
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar as credenciais: " + e.getMessage());
        }
    }

    // Método para cadastrar um usuário
    public void cadastrarUsuario(String documentId, String firstName, String lastName, String email) {
        Map<String, Object> user = new HashMap<>();
        user.put("firstName", firstName);
        user.put("lastName", lastName);
        user.put("email", email);

        try {
            db.collection("users").document(documentId).set(user).get(); // .get() espera a operação ser concluída
            System.out.println("Usuário cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Falha ao cadastrar o usuário: " + e.getMessage());
        }
    }

    // Método para buscar um usuário
    public void buscarUsuario(String documentId) {
        try {
            DocumentSnapshot document = db.collection("users").document(documentId).get().get(); // Busca o usuário
            
            if (document.exists()) {
                System.out.println("Usuário encontrado:");
                System.out.println("Nome: " + document.getString("firstName") + " " + document.getString("lastName"));
                System.out.println("Email: " + document.getString("email"));
            } else {
                System.out.println("Usuário não encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Falha ao buscar o usuário: " + e.getMessage());
        }
    }

    public void fechar() {
        if (db != null) {
            try {
                db.close(); // Fecha o Firestore
                System.out.println("Firestore fechado com sucesso.");
            } catch (Exception e) {
                System.out.println("Erro ao fechar o Firestore: " + e.getMessage());
            }
        }
    }
}
