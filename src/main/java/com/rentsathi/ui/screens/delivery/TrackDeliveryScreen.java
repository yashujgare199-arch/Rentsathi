package com.rentsathi.ui.screens.delivery;

import com.google.gson.JsonObject;
import com.rentsathi.firebase.firestore.FirestoreService;
import com.rentsathi.model.rental.RentalRequest;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import javafx.embed.swing.SwingNode;

import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import java.awt.Dimension;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;

public class TrackDeliveryScreen {

        private static final String BLUE = "#3657C8";
        private static final String LIGHT_BLUE = "#E8EDFF";
        private static final String BORDER = "#D6DCEB";
        private static final String TEXT = "#101828";
        private static final String MUTED = "#667085";
        private static final String BG = "#F8F9FD";
        private static final String GREEN = "#218739";

        public static void show(RentalRequest request) {

                Stage stage = new Stage();

                BorderPane root = new BorderPane();
                root.setStyle(
                                "-fx-background-color: " + BG + ";");

                // =========================
                // HEADER
                // =========================

                HBox header = new HBox();

                header.setPadding(
                                new Insets(18, 25, 18, 25));

                header.setAlignment(Pos.CENTER_LEFT);

                header.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-width: 0 0 1px 0;");

                Label title = new Label(
                                "Track Delivery");

                title.setStyle(
                                "-fx-font-size: 22px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + TEXT + ";");

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                Label requestLabel = new Label(
                                "Request: " + request.getRequestId());

                requestLabel.setStyle(
                                "-fx-font-size: 12px;" +
                                                "-fx-text-fill: " + MUTED + ";");

                header.getChildren().addAll(
                                title,
                                spacer,
                                requestLabel);

                root.setTop(header);

                // =========================
                // MAIN CONTENT
                // =========================

                VBox content = new VBox(18);

                content.setPadding(
                                new Insets(25));

                // =========================
                // DELIVERY STATUS
                // =========================

                VBox statusCard = new VBox(8);

                statusCard.setPadding(
                                new Insets(18));

                statusCard.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-radius: 10px;" +
                                                "-fx-background-radius: 10px;");

                Label statusTitle = new Label(
                                "Delivery Status");

                statusTitle.setStyle(
                                "-fx-font-size: 15px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + TEXT + ";");

                Label status = new Label(
                                "✓ Delivery Accepted");

                status.setStyle(
                                "-fx-background-color: #E4F7E8;" +
                                                "-fx-text-fill: " + GREEN + ";" +
                                                "-fx-font-size: 13px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-padding: 8px 14px;" +
                                                "-fx-background-radius: 15px;");

                statusCard.getChildren().addAll(
                                statusTitle,
                                status);

                // =========================
                // LOCATIONS
                // =========================

                HBox locations = new HBox(20);

                VBox pickupBox = locationCard(
                                "PICKUP",
                                "Owner Location",
                                "📍");

                String destination = request.getRental() != null
                                ? request.getRental().getAddress()
                                                + ", "
                                                + request.getRental().getCity()
                                                + ", "
                                                + request.getRental().getState()
                                                + " - "
                                                + request.getRental().getPinCode()
                                : "Destination not available";

                VBox destinationBox = locationCard(
                                "DESTINATION",
                                destination,
                                "🏁");

                HBox.setHgrow(
                                pickupBox,
                                Priority.ALWAYS);

                HBox.setHgrow(
                                destinationBox,
                                Priority.ALWAYS);

                locations.getChildren().addAll(
                                pickupBox,
                                destinationBox);

                // =========================
                // MAP AREA
                // =========================

                StackPane map = createMapPlaceholder();

                // =========================
                // ACTIONS
                // =========================

                HBox actions = new HBox(12);

                actions.setAlignment(
                                Pos.CENTER_RIGHT);

                Button reachedPickup = new Button("Reached Pickup");

                reachedPickup.setOnAction(event -> {

                        updateDeliveryStatus(
                                        request,
                                        "REACHED_PICKUP");

                        status.setText(
                                        "✓ Reached Pickup");
                });

                Button pickedUp = new Button("Picked Up");

                pickedUp.setOnAction(event -> {

                        updateDeliveryStatus(
                                        request,
                                        "PICKED_UP");

                        status.setText(
                                        "✓ Picked Up");
                });

                Button startDelivery = new Button("Start Delivery");

                startDelivery.setOnAction(event -> {

                        updateDeliveryStatus(
                                        request,
                                        "ON_THE_WAY");

                        status.setText(
                                        "✓ On the Way");
                });

                Button delivered = new Button("Mark Delivered");

                delivered.setOnAction(event -> {

                        updateDeliveryStatus(
                                        request,
                                        "DELIVERED");

                        status.setText(
                                        "✓ Delivered");
                });

                styleActionButton(
                                reachedPickup);

                styleActionButton(
                                pickedUp);

                styleActionButton(
                                startDelivery);

                styleActionButton(
                                delivered);

                actions.getChildren().addAll(
                                reachedPickup,
                                pickedUp,
                                startDelivery,
                                delivered);

                content.getChildren().addAll(
                                statusCard,
                                locations,
                                map,
                                actions);

                ScrollPane scrollPane = new ScrollPane(content);

                scrollPane.setFitToWidth(true);
                scrollPane.setFitToHeight(false);

                scrollPane.setHbarPolicy(
                                ScrollPane.ScrollBarPolicy.NEVER);

                scrollPane.setVbarPolicy(
                                ScrollPane.ScrollBarPolicy.AS_NEEDED);

                scrollPane.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-background: transparent;");

                root.setCenter(scrollPane);

                // =========================
                // SCENE
                // =========================

                Scene scene = new Scene(
                                root,
                                1100,
                                700);

                stage.setTitle(
                                "RentSathi - Track Delivery");

                stage.setScene(scene);

                stage.setMinWidth(900);
                stage.setMinHeight(600);

                stage.centerOnScreen();

                stage.show();
        }

        // =========================================================
        // LOCATION CARD
        // =========================================================

        private static VBox locationCard(
                        String titleText,
                        String location,
                        String iconText) {

                VBox card = new VBox(7);

                card.setPadding(
                                new Insets(15));

                card.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-radius: 10px;" +
                                                "-fx-background-radius: 10px;");

                Label title = new Label(
                                iconText + "  " + titleText);

                title.setStyle(
                                "-fx-font-size: 12px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + BLUE + ";");

                Label value = new Label(
                                location);

                value.setWrapText(true);

                value.setStyle(
                                "-fx-font-size: 13px;" +
                                                "-fx-text-fill: " + TEXT + ";");

                card.getChildren().addAll(
                                title,
                                value);

                return card;
        }

        // =========================================================
        // MAP PLACEHOLDER
        // =========================================================

        private static StackPane createMapPlaceholder() {

                StackPane container = new StackPane();

                container.setMinHeight(420);
                container.setPrefHeight(420);

                SwingNode swingNode = new SwingNode();

                SwingUtilities.invokeLater(() -> {

                        JXMapViewer mapViewer = new JXMapViewer();

                        // =========================
                        // OPENSTREETMAP
                        // =========================

                        TileFactoryInfo info = new OSMTileFactoryInfo();

                        DefaultTileFactory tileFactory = new DefaultTileFactory(info);

                        mapViewer.setTileFactory(
                                        tileFactory);

                        // =========================
                        // INITIAL LOCATION
                        // =========================

                        GeoPosition pune = new GeoPosition(
                                        18.5204,
                                        73.8567);

                        mapViewer.setAddressLocation(
                                        pune);

                        mapViewer.setZoom(5);

                        // =========================
                        // MAP SIZE
                        // =========================

                        mapViewer.setPreferredSize(
                                        new Dimension(
                                                        1000,
                                                        420));

                        mapViewer.setMinimumSize(
                                        new Dimension(
                                                        800,
                                                        300));

                        // =========================
                        // ADD MAP
                        // =========================

                        swingNode.setContent(
                                        mapViewer);
                });

                container.getChildren().add(
                                swingNode);

                StackPane.setAlignment(
                                swingNode,
                                Pos.CENTER);

                return container;
        }

        // =========================================================
        // BUTTON STYLE
        // =========================================================

        private static void styleActionButton(
                        Button button) {

                button.setPrefHeight(42);

                button.setStyle(
                                "-fx-background-color: " + BLUE + ";" +
                                                "-fx-text-fill: white;" +
                                                "-fx-font-size: 13px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-background-radius: 6px;" +
                                                "-fx-padding: 10px 18px;" +
                                                "-fx-cursor: hand;");
        }

        private static void updateDeliveryStatus(
                        RentalRequest request,
                        String newStatus) {

                request.setDeliveryStatus(
                                newStatus);

                JsonObject fields = new JsonObject();

                fields.add(
                                "deliveryStatus",
                                FirestoreService.stringField(
                                                newStatus));

                boolean success = FirestoreService.updateDocument(
                                "rental_requests",
                                request.getRequestId(),
                                fields);

                if (success) {

                        System.out.println(
                                        "DELIVERY STATUS UPDATED = "
                                                        + newStatus);

                } else {

                        System.out.println(
                                        "FAILED TO UPDATE DELIVERY STATUS = "
                                                        + newStatus);

                }
        }
}
