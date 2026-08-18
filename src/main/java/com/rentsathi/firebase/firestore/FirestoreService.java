package com.rentsathi.firebase.firestore;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.rentsathi.firebase.FirebaseConfig;
import com.rentsathi.firebase.authentication.FirebaseSession;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class FirestoreService {

    private static final HttpClient CLIENT =
            HttpClient.newHttpClient();

    private static final Gson GSON =
            new Gson();

    // ============================================================
    // CREATE DOCUMENT
    // ============================================================

    public static boolean createDocument(
            String collection,
            String documentId,
            JsonObject fields) {

        if (!FirebaseSession.isLoggedIn()) {
            System.out.println("User is not logged in.");
            return false;
        }

        try {

            String url =
                    FirebaseConfig.FIRESTORE_BASE_URL
                            + "/projects/"
                            + FirebaseConfig.PROJECT_ID
                            + "/databases/(default)/documents/"
                            + collection
                            + "/"
                            + documentId;

            JsonObject body = new JsonObject();

            body.add("fields", fields);

            HttpRequest request =
                    HttpRequest.newBuilder()
                            .uri(URI.create(url))
                            .header(
                                    "Content-Type",
                                    "application/json"
                            )
                            .header(
                                    "Authorization",
                                    "Bearer "
                                            + FirebaseSession.getIdToken()
                            )
                            .PUT(
                                    HttpRequest.BodyPublishers.ofString(
                                            GSON.toJson(body)
                                    )
                            )
                            .build();

            HttpResponse<String> response =
                    CLIENT.send(
                            request,
                            HttpResponse.BodyHandlers.ofString()
                    );

            if (response.statusCode() == 200) {

                System.out.println(
                        "Document created successfully."
                );

                return true;
            }

            System.out.println(
                    "Firestore Create Error:"
            );

            System.out.println(
                    response.body()
            );

            return false;

        } catch (IOException | InterruptedException e) {

            e.printStackTrace();

            return false;
        }
    }

    // ============================================================
    // GET DOCUMENT
    // ============================================================

    public static JsonObject getDocument(
            String collection,
            String documentId) {

        if (!FirebaseSession.isLoggedIn()) {
            System.out.println("User is not logged in.");
            return null;
        }

        try {

            String url =
                    FirebaseConfig.FIRESTORE_BASE_URL
                            + "/projects/"
                            + FirebaseConfig.PROJECT_ID
                            + "/databases/(default)/documents/"
                            + collection
                            + "/"
                            + documentId;

            HttpRequest request =
                    HttpRequest.newBuilder()
                            .uri(URI.create(url))
                            .header(
                                    "Authorization",
                                    "Bearer "
                                            + FirebaseSession.getIdToken()
                            )
                            .GET()
                            .build();

            HttpResponse<String> response =
                    CLIENT.send(
                            request,
                            HttpResponse.BodyHandlers.ofString()
                    );

            if (response.statusCode() == 200) {

                return JsonParser
                        .parseString(response.body())
                        .getAsJsonObject();
            }

            System.out.println(
                    "Firestore Get Error:"
            );

            System.out.println(
                    response.body()
            );

            return null;

        } catch (IOException | InterruptedException e) {

            e.printStackTrace();

            return null;
        }
    }

    // ============================================================
    // UPDATE DOCUMENT
    // ============================================================

    public static boolean updateDocument(
            String collection,
            String documentId,
            JsonObject fields) {

        if (!FirebaseSession.isLoggedIn()) {
            System.out.println("User is not logged in.");
            return false;
        }

        try {

            String url =
                    FirebaseConfig.FIRESTORE_BASE_URL
                            + "/projects/"
                            + FirebaseConfig.PROJECT_ID
                            + "/databases/(default)/documents/"
                            + collection
                            + "/"
                            + documentId;

        JsonObject body = new JsonObject();

        body.add("fields", fields);

        HttpRequest request =
                HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header(
                        "Content-Type",
                        "application/json"
                )
                .header(
                        "Authorization",
                        "Bearer " + FirebaseSession.getIdToken()
                )
                .method(
                        "PATCH",
                        HttpRequest.BodyPublishers.ofString(
                                GSON.toJson(body)
                        )
                )
                .build();

            HttpResponse<String> response =
                    CLIENT.send(
                            request,
                            HttpResponse.BodyHandlers.ofString()
                    );

            if (response.statusCode() == 200) {

                System.out.println(
                        "Document updated successfully."
                );

                return true;
            }

            System.out.println(
                    "Firestore Update Error:"
            );

            System.out.println(
                    response.body()
            );

            return false;

        } catch (IOException | InterruptedException e) {

            e.printStackTrace();

            return false;
        }
    }

    // ============================================================
    // DELETE DOCUMENT
    // ============================================================

    public static boolean deleteDocument(
            String collection,
            String documentId) {

        if (!FirebaseSession.isLoggedIn()) {
            System.out.println("User is not logged in.");
            return false;
        }

        try {

            String url =
                    FirebaseConfig.FIRESTORE_BASE_URL
                            + "/projects/"
                            + FirebaseConfig.PROJECT_ID
                            + "/databases/(default)/documents/"
                            + collection
                            + "/"
                            + documentId;

            HttpRequest request =
                    HttpRequest.newBuilder()
                            .uri(URI.create(url))
                            .header(
                                    "Authorization",
                                    "Bearer "
                                            + FirebaseSession.getIdToken()
                            )
                            .DELETE()
                            .build();

            HttpResponse<String> response =
                    CLIENT.send(
                            request,
                            HttpResponse.BodyHandlers.ofString()
                    );

            if (response.statusCode() == 200) {

                System.out.println(
                        "Document deleted successfully."
                );

                return true;
            }

            System.out.println(
                    "Firestore Delete Error:"
            );

            System.out.println(
                    response.body()
            );

            return false;

        } catch (IOException | InterruptedException e) {

            e.printStackTrace();

            return false;
        }
    }

    // ============================================================
    // STRING FIELD HELPER
    // ============================================================

    public static JsonObject stringField(
            String value) {

        JsonObject field = new JsonObject();

        field.addProperty(
                "stringValue",
                value
        );

        return field;
    }

    // ============================================================
    // INTEGER FIELD HELPER
    // ============================================================

    public static JsonObject integerField(
            int value) {

        JsonObject field = new JsonObject();

        field.addProperty(
                "integerValue",
                String.valueOf(value)
        );

        return field;
    }

    // ============================================================
    // DOUBLE FIELD HELPER
    // ============================================================

    public static JsonObject doubleField(
            double value) {

        JsonObject field = new JsonObject();

        field.addProperty(
                "doubleValue",
                value
        );

        return field;
    }

    // ============================================================
    // BOOLEAN FIELD HELPER
    // ============================================================

    public static JsonObject booleanField(
            boolean value) {

        JsonObject field = new JsonObject();

        field.addProperty(
                "booleanValue",
                value
        );

        return field;
    }

    // ============================================================
    // PREVENT OBJECT CREATION
    // ============================================================

    private FirestoreService() {
    }
}