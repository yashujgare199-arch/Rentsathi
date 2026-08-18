package com.rentsathi.firebase.authentication;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.rentsathi.firebase.FirebaseConfig;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class FirebaseAuthService {

    private static final HttpClient CLIENT =
            HttpClient.newHttpClient();

    private static final Gson GSON =
            new Gson();

    // ============================================================
    // REGISTER USER
    // ============================================================

    public static boolean register(
            String email,
            String password) {

        try {

            String url =
                    FirebaseConfig.AUTH_BASE_URL
                            + "/accounts:signUp?key="
                            + FirebaseConfig.API_KEY;

            JsonObject body = new JsonObject();

            body.addProperty(
                    "email",
                    email
            );

            body.addProperty(
                    "password",
                    password
            );

            body.addProperty(
                    "returnSecureToken",
                    true
            );

            HttpRequest request =
                    HttpRequest.newBuilder()
                            .uri(URI.create(url))
                            .header(
                                    "Content-Type",
                                    "application/json"
                            )
                            .POST(
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

                JsonObject json =
                        JsonParser
                                .parseString(response.body())
                                .getAsJsonObject();

                FirebaseSession.setSession(
                        json.get("idToken").getAsString(),
                        json.get("refreshToken").getAsString(),
                        json.get("localId").getAsString(),
                        json.get("email").getAsString()
                );

                System.out.println(
                        "Firebase registration successful."
                );

                return true;
            }

            System.out.println(
                    "Firebase registration failed:"
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
    // LOGIN USER
    // ============================================================

    public static boolean login(
            String email,
            String password) {

        try {

            String url =
                    FirebaseConfig.AUTH_BASE_URL
                            + "/accounts:signInWithPassword?key="
                            + FirebaseConfig.API_KEY;

            JsonObject body = new JsonObject();

            body.addProperty(
                    "email",
                    email
            );

            body.addProperty(
                    "password",
                    password
            );

            body.addProperty(
                    "returnSecureToken",
                    true
            );

            HttpRequest request =
                    HttpRequest.newBuilder()
                            .uri(URI.create(url))
                            .header(
                                    "Content-Type",
                                    "application/json"
                            )
                            .POST(
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

                JsonObject json =
                        JsonParser
                                .parseString(response.body())
                                .getAsJsonObject();

                FirebaseSession.setSession(
                        json.get("idToken").getAsString(),
                        json.get("refreshToken").getAsString(),
                        json.get("localId").getAsString(),
                        json.get("email").getAsString()
                );

                System.out.println(
                        "Firebase login successful."
                );

                return true;
            }

            System.out.println(
                    "Firebase login failed:"
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
    // LOGOUT
    // ============================================================

    public static void logout() {

        FirebaseSession.logout();

        System.out.println(
                "Firebase session cleared."
        );
    }

    // ============================================================
    // PRIVATE CONSTRUCTOR
    // ============================================================

    private FirebaseAuthService() {
    }
}