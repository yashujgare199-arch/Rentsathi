package com.rentsathi.ui.screens.admin;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.rentsathi.firebase.firestore.FirestoreService;
import com.rentsathi.ui.screens.AdminLoginScreen;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.LinkedHashMap;
import java.util.Map;

public class AdminOwnersScreen {

    private static final String BLUE = "#3657C8";
    private static final String TEXT = "#182235";
    private static final String SECONDARY = "#64708A";
    private static final String BORDER = "#D5DBE8";
    private static final String BACKGROUND = "#F8F9FD";
    private static final String WHITE = "#FFFFFF";

    public static void show(Stage stage) {

        BorderPane root = new BorderPane();

        root.setStyle(
                "-fx-background-color: "
                        + BACKGROUND
                        + ";"
        );

        VBox sidebar =
                createSidebar(stage);

        VBox content =
                createContent();

        ScrollPane scrollPane =
                new ScrollPane(content);

        scrollPane.setFitToWidth(true);

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        scrollPane.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-background: transparent;"
        );

        root.setLeft(sidebar);
        root.setCenter(scrollPane);

        Scene scene =
                new Scene(
                        root,
                        1500,
                        830
                );

        scene.setFill(
                Color.web(BACKGROUND)
        );

        stage.setTitle(
                "RentSathi - Owners"
        );

        stage.setScene(scene);

        stage.setMinWidth(1100);
        stage.setMinHeight(700);

        stage.show();
    }

    // =========================================================
    // SIDEBAR
    // =========================================================

    private static VBox createSidebar(
            Stage stage) {

        VBox sidebar =
                new VBox(8);

        sidebar.setPrefWidth(160);
        sidebar.setMinWidth(160);
        sidebar.setMaxWidth(160);

        sidebar.setPadding(
                new Insets(
                        15,
                        10,
                        12,
                        10
                )
        );

        sidebar.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-width: 0 1 0 0;"
        );

        Label brand =
                new Label("RentSathi");

        brand.setStyle(
                "-fx-font-size: 18px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + BLUE + ";"
        );

        Label role =
                new Label("Admin Panel");

        role.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-text-fill: " + SECONDARY + ";"
        );

        VBox brandBox =
                new VBox(
                        2,
                        brand,
                        role
                );

        sidebar.getChildren().add(
                brandBox
        );

        Label customers =
                navigationItem(
                        "♙",
                        "Customers",
                        false
                );

        customers.setOnMouseClicked(
                event -> {
                    AdminCustomersScreen.show(
                            stage
                    );
                }
        );

        Label owners =
                navigationItem(
                        "▣",
                        "Owners",
                        true
                );

        Label delivery =
                navigationItem(
                        "▰",
                        "Delivery Partners",
                        false
                );

        sidebar.getChildren().addAll(
                customers,
                owners,
                delivery
        );

        VBox spacer =
                new VBox();

        VBox.setVgrow(
                spacer,
                Priority.ALWAYS
        );

        sidebar.getChildren().add(
                spacer
        );

        Label help =
                navigationItem(
                        "?",
                        "Help Center",
                        false
                );

        sidebar.getChildren().add(
                help
        );

        Label logout =
                navigationItem(
                        "↪",
                        "Logout",
                        false
                );

        logout.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #D32626;" +
                "-fx-font-size: 13px;" +
                "-fx-padding: 9px 8px;" +
                "-fx-cursor: hand;"
        );

        logout.setOnMouseClicked(
                event -> {
                    AdminLoginScreen.show(
                            stage
                    );
                }
        );

        sidebar.getChildren().add(
                logout
        );

        return sidebar;
    }

    private static Label navigationItem(
            String icon,
            String text,
            boolean selected) {

        Label item =
                new Label(
                        icon + "   " + text
                );

        item.setMaxWidth(
                Double.MAX_VALUE
        );

        if (selected) {

            item.setStyle(
                    "-fx-background-color: #E8EEFF;" +
                    "-fx-text-fill: " + BLUE + ";" +
                    "-fx-font-size: 12px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-padding: 10px 8px;" +
                    "-fx-background-radius: 6px;" +
                    "-fx-cursor: hand;"
            );

        } else {

            item.setStyle(
                    "-fx-background-color: transparent;" +
                    "-fx-text-fill: " + TEXT + ";" +
                    "-fx-font-size: 12px;" +
                    "-fx-padding: 10px 8px;" +
                    "-fx-background-radius: 6px;" +
                    "-fx-cursor: hand;"
            );
        }

        return item;
    }

    // =========================================================
    // CONTENT
    // =========================================================

    private static VBox createContent() {

        VBox content =
                new VBox(18);

        content.setPadding(
                new Insets(
                        28,
                        32,
                        30,
                        32
                )
        );

        Label title =
                new Label(
                        "Owners"
                );

        title.setStyle(
                "-fx-font-size: 30px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        Label subtitle =
                new Label(
                        "View owners and the rental listings they have published."
                );

        subtitle.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: " + SECONDARY + ";"
        );

        VBox heading =
                new VBox(
                        4,
                        title,
                        subtitle
                );

        TextField search =
                new TextField();

        search.setPromptText(
                "Search by Owner ID..."
        );

        search.setPrefHeight(
                40
        );

        search.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 7px;" +
                "-fx-background-radius: 7px;" +
                "-fx-padding: 0 12px;" +
                "-fx-font-size: 13px;"
        );

        VBox table =
                createOwnerTable();

        VBox.setVgrow(
                table,
                Priority.ALWAYS
        );

        content.getChildren().addAll(
                heading,
                search,
                table
        );

        return content;
    }

    // =========================================================
    // OWNER TABLE
    // =========================================================

    private static VBox createOwnerTable() {

        VBox table =
                new VBox();

        table.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 10px;" +
                "-fx-background-radius: 10px;"
        );

        JsonArray documents =
                FirestoreService.getCollectionDocuments(
                        "rentals"
                );

        Map<String, Integer> ownerRentals =
                new LinkedHashMap<>();

        for (int i = 0;
                i < documents.size();
                i++) {

            JsonObject document =
                    documents
                            .get(i)
                            .getAsJsonObject();

            if (!document.has("fields")) {
                continue;
            }

            JsonObject fields =
                    document.getAsJsonObject(
                            "fields"
                    );

            String ownerId =
                    getStringField(
                            fields,
                            "ownerId"
                    );

            if (ownerId.isEmpty()) {
                continue;
            }

            ownerRentals.put(
                    ownerId,
                    ownerRentals.getOrDefault(
                            ownerId,
                            0
                    ) + 1
            );
        }

        HBox header =
                new HBox();

        header.setPadding(
                new Insets(14)
        );

        header.setStyle(
                "-fx-background-color: #F3F5FC;" +
                "-fx-background-radius: 10px 10px 0 0;"
        );

        Label ownerHeader =
                tableLabel(
                        "OWNER ID",
                        true
                );

        Label rentalsHeader =
                tableLabel(
                        "RENTAL LISTINGS",
                        true
                );

        Label statusHeader =
                tableLabel(
                        "STATUS",
                        true
                );

        HBox.setHgrow(
                ownerHeader,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                rentalsHeader,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                statusHeader,
                Priority.ALWAYS
        );

        header.getChildren().addAll(
                ownerHeader,
                rentalsHeader,
                statusHeader
        );

        table.getChildren().add(
                header
        );

        if (ownerRentals.isEmpty()) {

            Label empty =
                    new Label(
                            "No owner rental data available."
                    );

            empty.setStyle(
                    "-fx-font-size: 14px;" +
                    "-fx-text-fill: " + SECONDARY + ";"
            );

            VBox emptyBox =
                    new VBox(empty);

            emptyBox.setAlignment(
                    Pos.CENTER
            );

            emptyBox.setPadding(
                    new Insets(50)
            );

            table.getChildren().add(
                    emptyBox
            );

            return table;
        }

        for (Map.Entry<String, Integer> entry :
                ownerRentals.entrySet()) {

            HBox row =
                    new HBox();

            row.setPadding(
                    new Insets(14)
            );

            row.setStyle(
                    "-fx-border-color: transparent transparent "
                            + BORDER
                            + " transparent;" +
                    "-fx-border-width: 0 0 1 0;"
            );

            Label ownerId =
                    tableLabel(
                            entry.getKey(),
                            false
                    );

            Label rentalCount =
                    tableLabel(
                            String.valueOf(
                                    entry.getValue()
                            ),
                            false
                    );

            Label status =
                    tableLabel(
                            "Published",
                            false
                    );

            HBox.setHgrow(
                    ownerId,
                    Priority.ALWAYS
            );

            HBox.setHgrow(
                    rentalCount,
                    Priority.ALWAYS
            );

            HBox.setHgrow(
                    status,
                    Priority.ALWAYS
            );

            row.getChildren().addAll(
                    ownerId,
                    rentalCount,
                    status
            );

            table.getChildren().add(
                    row
            );
        }

        return table;
    }

    private static Label tableLabel(
            String text,
            boolean header) {

        Label label =
                new Label(text);

        label.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: "
                        + (header
                                ? "bold;"
                                : "normal;") +
                "-fx-text-fill: "
                        + (header
                                ? SECONDARY
                                : TEXT)
                        + ";"
        );

        return label;
    }

    // =========================================================
    // FIRESTORE FIELD HELPER
    // =========================================================

    private static String getStringField(
            JsonObject fields,
            String name) {

        try {

            if (!fields.has(name)) {
                return "";
            }

            JsonObject field =
                    fields.getAsJsonObject(
                            name
                    );

            if (field.has("stringValue")) {

                return field
                        .get("stringValue")
                        .getAsString();
            }

            if (field.has("integerValue")) {

                return field
                        .get("integerValue")
                        .getAsString();
            }

            if (field.has("doubleValue")) {

                return field
                        .get("doubleValue")
                        .getAsString();
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return "";
    }
}
