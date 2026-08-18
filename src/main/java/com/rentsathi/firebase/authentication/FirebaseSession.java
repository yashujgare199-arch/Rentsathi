package com.rentsathi.firebase.authentication;

public class FirebaseSession {

    // Firebase authentication token
    private static String idToken;

    // Firebase refresh token
    private static String refreshToken;

    // Firebase user's unique ID
    private static String userId;

    // Logged-in user's email
    private static String email;

    private FirebaseSession() {
        // Prevent object creation
    }

    // ============================================================
    // SET LOGIN SESSION
    // ============================================================

    public static void setSession(
            String idToken,
            String refreshToken,
            String userId,
            String email) {

        FirebaseSession.idToken = idToken;
        FirebaseSession.refreshToken = refreshToken;
        FirebaseSession.userId = userId;
        FirebaseSession.email = email;
    }

    // ============================================================
    // GET ID TOKEN
    // ============================================================

    public static String getIdToken() {
        return idToken;
    }

    // ============================================================
    // GET REFRESH TOKEN
    // ============================================================

    public static String getRefreshToken() {
        return refreshToken;
    }

    // ============================================================
    // GET USER ID
    // ============================================================

    public static String getUserId() {
        return userId;
    }

    // ============================================================
    // GET EMAIL
    // ============================================================

    public static String getEmail() {
        return email;
    }

    // ============================================================
    // CHECK LOGIN STATUS
    // ============================================================

    public static boolean isLoggedIn() {

        return idToken != null
                && !idToken.isEmpty();
    }

    // ============================================================
    // LOGOUT
    // ============================================================

    public static void logout() {

        idToken = null;
        refreshToken = null;
        userId = null;
        email = null;
    }
}