package com.rentsathi.ui.screens.customer;

import java.time.LocalDate;
import com.rentsathi.model.rental.RentalModel;
import com.rentsathi.model.rental.RentalRequest;
import com.rentsathi.model.rental.RentalRequestStore;

import com.rentsathi.model.rental.RentalRequest;
import com.rentsathi.model.rental.RentalRequestStore;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;
import org.checkerframework.common.returnsreceiver.qual.This;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import com.rentsathi.ui.screens.customer.BookingConfirmationScreen;

public class CheckoutScreen {

        private static final String BLUE = "#3657C8";
        private static final String DARK = "#111827";
        private static final String TEXT = "#52688C";
        private static final String BG = "#F8F8FD";
        private static final String BORDER = "#C8CBD9";
        private static final String RED = "#D32626";

        private static final StringProperty selectedPaymentMethod = new SimpleStringProperty("");

        public static void show(
                        Stage stage,
                        RentalModel rental,
                        LocalDate startDate,
                        LocalDate endDate,
                        String fulfillmentMethod,
                        TextArea deliveryAddress) {

                System.out.println(
                                "SHOW FULFILLMENT METHOD = ["
                                                + fulfillmentMethod
                                                + "]");

                BorderPane root = new BorderPane();
                root.setStyle("-fx-background-color: " + BG + ";");

                root.setTop(
                                createTopBar(
                                                stage,
                                                rental,
                                                startDate,
                                                endDate,
                                                fulfillmentMethod,
                                                deliveryAddress));

                VBox content = new VBox(20);
                content.setPadding(new Insets(28, 45, 35, 45));

                HBox main = new HBox(28);

                VBox left = new VBox(20);
                VBox booking = createBookingDetails(
                                rental,
                                startDate,
                                endDate);
                VBox payment = createPaymentSection();

                left.getChildren().addAll(booking, payment);

                VBox right = createPriceSummary(
                                stage,
                                rental,
                                startDate,
                                endDate,
                                fulfillmentMethod, deliveryAddress);

                HBox.setHgrow(left, Priority.ALWAYS);
                HBox.setHgrow(right, Priority.SOMETIMES);

                main.getChildren().addAll(left, right);
                content.getChildren().add(main);

                root.setCenter(content);

                Scene scene = new Scene(root, 1500, 830);
                scene.setFill(Color.web(BG));

                stage.setTitle("RentSathi - Checkout");
                stage.setScene(scene);
                stage.setWidth(1500);
                stage.setHeight(830);
                stage.setMinWidth(1000);
                stage.setMinHeight(650);
                stage.show();
        }

        private static HBox createTopBar(
                        Stage stage,
                        RentalModel rental,
                        LocalDate startDate,
                        LocalDate endDate,
                        String fulfillmentMethod,
                        TextArea deliveryAddress) {

                HBox topBar = new HBox();
                topBar.setPrefHeight(45);
                topBar.setPadding(new Insets(0, 20, 0, 25));
                topBar.setAlignment(Pos.CENTER_LEFT);
                topBar.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-width: 0 0 1 0;");

                Label brand = new Label("▣ RentSathi");
                brand.setStyle(
                                "-fx-font-size: 15px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + BLUE + ";");

                Region spacer = new Region();
                HBox.setHgrow(spacer, Priority.ALWAYS);

                Label title = new Label("Checkout");
                title.setStyle(
                                "-fx-font-size: 18px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + DARK + ";");

                Region leftSpacer = new Region();
                HBox.setHgrow(leftSpacer, Priority.ALWAYS);

                Button help = new Button("?");
                help.setMinSize(22, 22);
                help.setMaxSize(22, 22);
                help.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: " + BLUE + ";" +
                                                "-fx-border-width: 1.5px;" +
                                                "-fx-border-radius: 50%;" +
                                                "-fx-background-radius: 50%;" +
                                                "-fx-font-size: 13px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + BLUE + ";" +
                                                "-fx-padding: 0;");

                Button cancel = new Button("Cancel");
                cancel.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-text-fill: " + BLUE + ";" +
                                                "-fx-background-radius: 15px;" +
                                                "-fx-border-radius: 15px;" +
                                                "-fx-padding: 5px 14px;" +
                                                "-fx-cursor: hand;");

                cancel.setOnAction(
                                event -> BookRentalScreen.show(
                                                stage,
                                                rental,
                                                startDate,
                                                endDate,
                                                fulfillmentMethod,
                                                deliveryAddress
                                ));

                topBar.getChildren().addAll(
                                brand,
                                leftSpacer,
                                title,
                                spacer,
                                help,
                                cancel);

                HBox.setMargin(help, new Insets(0, 15, 0, 0));

                return topBar;
        }

        private static VBox createBookingDetails(
                        RentalModel rental,
                        LocalDate startDate,
                        LocalDate endDate) {

                VBox box = createCard();

                long rentalDays = java.time.temporal.ChronoUnit.DAYS.between(
                                startDate,
                                endDate);

                double rentalAmount = rental.getPricePerDay() * rentalDays;

                Label heading = new Label("Booking Details");
                heading.setStyle(
                                "-fx-font-size: 17px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + DARK + ";");

                HBox details = new HBox(20);
                details.setAlignment(Pos.CENTER_LEFT);

                Image image = new Image(
                                "https://images.unsplash.com/photo-1516035069371-29a1b244cc32?w=500",
                                150,
                                120,
                                true,
                                true,
                                true);

                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(110);
                imageView.setFitHeight(110);
                imageView.setPreserveRatio(true);

                VBox imageBox = new VBox(imageView);
                imageBox.setAlignment(Pos.CENTER);
                imageBox.setPrefSize(110, 110);
                imageBox.setStyle(
                                "-fx-background-color: #F4F4F4;" +
                                                "-fx-background-radius: 5px;");

                VBox info = new VBox(6);

                Label name = new Label(rental.getRentalName());
                name.setStyle(
                                "-fx-font-size: 16px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + DARK + ";");

                Label owner = new Label(
                                "♙ Owner: " + rental.getOwnerId());
                owner.setStyle(
                                "-fx-font-size: 12px;" +
                                                "-fx-text-fill: " + TEXT + ";");

                Label location = new Label(
                                "⌖ " + rental.getCity() + ", " + rental.getState());
                location.setStyle(
                                "-fx-font-size: 12px;" +
                                                "-fx-text-fill: " + TEXT + ";");

                HBox smallBoxes = new HBox(12);

                VBox duration = smallInfoBox(
                                "Duration",
                                rentalDays + " days");
                VBox dates = smallInfoBox(
                                "Dates",
                                startDate + " - " + endDate);

                smallBoxes.getChildren().addAll(duration, dates);

                info.getChildren().addAll(
                                name,
                                owner,
                                location,
                                smallBoxes);

                Region spacer = new Region();
                HBox.setHgrow(spacer, Priority.ALWAYS);

                VBox fulfillment = new VBox(3);
                fulfillment.setAlignment(Pos.CENTER_RIGHT);

                Label fulfillmentTitle = new Label("Fulfillment");
                fulfillmentTitle.setStyle(
                                "-fx-font-size: 11px;" +
                                                "-fx-text-fill: " + TEXT + ";");

                Label fulfillmentValue = new Label("♧ Delivery");
                fulfillmentValue.setStyle(
                                "-fx-font-size: 12px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + DARK + ";");

                fulfillment.getChildren().addAll(
                                fulfillmentTitle,
                                fulfillmentValue);

                details.getChildren().addAll(
                                imageBox,
                                info,
                                spacer,
                                fulfillment);

                box.getChildren().addAll(heading, details);

                return box;
        }

        private static VBox createPaymentSection() {

                VBox box = createCard();

                Label heading = new Label("Payment Method");
                heading.setStyle(
                                "-fx-font-size: 17px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + DARK + ";");

                ToggleButton upi = paymentIconButton(
                                FontAwesomeSolid.QRCODE,
                                "UPI");

                ToggleButton card = paymentIconButton(
                                FontAwesomeSolid.CREDIT_CARD,
                                "Card");

                ToggleButton netBanking = paymentIconButton(
                                FontAwesomeSolid.UNIVERSITY,
                                "Net Banking");

                ToggleButton cash = paymentIconButton(
                                FontAwesomeSolid.MONEY_BILL,
                                "Cash on Delivery");

                ToggleGroup group = new ToggleGroup();

                upi.setToggleGroup(group);
                card.setToggleGroup(group);
                netBanking.setToggleGroup(group);
                cash.setToggleGroup(group);

                HBox methods = new HBox(8);

                methods.getChildren().addAll(
                                upi,
                                card,
                                netBanking,
                                cash);

                HBox.setHgrow(
                                upi,
                                Priority.ALWAYS);

                HBox.setHgrow(
                                card,
                                Priority.ALWAYS);

                HBox.setHgrow(
                                netBanking,
                                Priority.ALWAYS);

                HBox.setHgrow(
                                cash,
                                Priority.ALWAYS);

                updatePaymentStyles(
                                upi,
                                card,
                                netBanking,
                                cash);

                /*
                 * Dynamic payment details area.
                 * Initially empty.
                 */
                VBox paymentDetails = new VBox(12);

                /*
                 * UPI button
                 */
                upi.setOnAction(event -> {

                        selectedPaymentMethod.set("UPI");

                        updatePaymentStyles(
                                        upi,
                                        card,
                                        netBanking,
                                        cash);

                        /*
                         * Hide the other three payment boxes.
                         */
                        card.setVisible(false);
                        card.setManaged(false);

                        netBanking.setVisible(false);
                        netBanking.setManaged(false);

                        cash.setVisible(false);
                        cash.setManaged(false);

                        /*
                         * Create UPI details.
                         */
                        VBox upiDetails = new VBox(10);

                        Label upiLabel = new Label("UPI ID *");

                        upiLabel.setStyle(
                                        "-fx-font-size: 12px;" +
                                                        "-fx-font-weight: bold;" +
                                                        "-fx-text-fill: " + DARK + ";");

                        TextField upiField = new TextField();

                        upiField.setPromptText(
                                        "Enter UPI ID");

                        upiField.setPrefHeight(40);

                        styleTextField(upiField);

                        Label example = new Label(
                                        "Example: name@upi");

                        example.setStyle(
                                        "-fx-font-size: 10px;" +
                                                        "-fx-text-fill: " + TEXT + ";");

                        HBox information = new HBox(8);

                        information.setPadding(
                                        new Insets(10));

                        information.setStyle(
                                        "-fx-background-color: #F1F3FF;" +
                                                        "-fx-border-color: " + BORDER + ";" +
                                                        "-fx-border-radius: 5px;" +
                                                        "-fx-background-radius: 5px;");

                        Label infoIcon = new Label("ⓘ");

                        infoIcon.setStyle(
                                        "-fx-text-fill: " + BLUE + ";" +
                                                        "-fx-font-size: 13px;");

                        Label infoText = new Label(
                                        "You will be redirected to your UPI app\n" +
                                                        "to complete the payment.");

                        infoText.setStyle(
                                        "-fx-font-size: 10px;" +
                                                        "-fx-text-fill: " + TEXT + ";");

                        information.getChildren().addAll(
                                        infoIcon,
                                        infoText);

                        Button changeMethod = new Button(
                                        "← Change Payment Method");

                        changeMethod.setStyle(
                                        "-fx-background-color: white;" +
                                                        "-fx-border-color: " + BORDER + ";" +
                                                        "-fx-text-fill: " + BLUE + ";" +
                                                        "-fx-background-radius: 5px;" +
                                                        "-fx-border-radius: 5px;" +
                                                        "-fx-padding: 7px 14px;" +
                                                        "-fx-cursor: hand;");

                        changeMethod.setOnAction(changeEvent -> {

                                /*
                                 * Show all four payment boxes again.
                                 */
                                card.setVisible(true);
                                card.setManaged(true);

                                netBanking.setVisible(true);
                                netBanking.setManaged(true);

                                cash.setVisible(true);
                                cash.setManaged(true);

                                /*
                                 * Clear UPI selection.
                                 */
                                group.selectToggle(null);

                                selectedPaymentMethod.set("");
                                /*
                                 * Remove UPI details.
                                 */
                                paymentDetails.getChildren().clear();

                                /*
                                 * Reset payment button styling.
                                 */
                                updatePaymentStyles(
                                                upi,
                                                card,
                                                netBanking,
                                                cash);
                        });

                        upiDetails.getChildren().addAll(
                                        upiLabel,
                                        upiField,
                                        example,
                                        information,
                                        changeMethod);

                        paymentDetails.getChildren().setAll(
                                        upiDetails);
                });

                /*
                 * Keep the other three buttons
                 * inactive for this step.
                 */
                card.setOnAction(event -> {

                        selectedPaymentMethod.set("Card");

                        updatePaymentStyles(
                                        upi,
                                        card,
                                        netBanking,
                                        cash);

                        // Hide UPI
                        upi.setVisible(false);
                        upi.setManaged(false);

                        // Hide Net Banking
                        netBanking.setVisible(false);
                        netBanking.setManaged(false);

                        // Hide Cash on Delivery
                        cash.setVisible(false);
                        cash.setManaged(false);

                        VBox cardDetails = new VBox(10);

                        Label cardNumberLabel = new Label("Card Number");

                        cardNumberLabel.setStyle(
                                        "-fx-font-size: 12px;" +
                                                        "-fx-font-weight: bold;" +
                                                        "-fx-text-fill: " + DARK + ";");

                        TextField cardNumber = new TextField();

                        cardNumber.setPromptText("Enter Card Number");
                        cardNumber.setPrefHeight(40);

                        styleTextField(cardNumber);

                        Label nameLabel = new Label("Cardholder Name");

                        nameLabel.setStyle(
                                        "-fx-font-size: 12px;" +
                                                        "-fx-font-weight: bold;" +
                                                        "-fx-text-fill: " + DARK + ";");

                        TextField cardholderName = new TextField();

                        cardholderName.setPromptText(
                                        "Enter Cardholder Name");

                        cardholderName.setPrefHeight(40);

                        styleTextField(cardholderName);

                        Label expiryLabel = new Label("Expiry Date");

                        expiryLabel.setStyle(
                                        "-fx-font-size: 12px;" +
                                                        "-fx-font-weight: bold;" +
                                                        "-fx-text-fill: " + DARK + ";");

                        TextField expiry = new TextField();

                        expiry.setPromptText("MM/YY");
                        expiry.setPrefHeight(40);

                        styleTextField(expiry);

                        Label cvvLabel = new Label("CVV");

                        cvvLabel.setStyle(
                                        "-fx-font-size: 12px;" +
                                                        "-fx-font-weight: bold;" +
                                                        "-fx-text-fill: " + DARK + ";");

                        PasswordField cvv = new PasswordField();

                        cvv.setPromptText("CVV");
                        cvv.setPrefHeight(40);

                        styleTextField(cvv);

                        VBox expiryBox = new VBox(
                                        5,
                                        expiryLabel,
                                        expiry);

                        VBox cvvBox = new VBox(
                                        5,
                                        cvvLabel,
                                        cvv);

                        HBox expiryCvv = new HBox(
                                        10,
                                        expiryBox,
                                        cvvBox);

                        HBox.setHgrow(
                                        expiryBox,
                                        Priority.ALWAYS);

                        HBox.setHgrow(
                                        cvvBox,
                                        Priority.ALWAYS);

                        Button changeMethod = new Button(
                                        "← Change Payment Method");

                        changeMethod.setStyle(
                                        "-fx-background-color: white;" +
                                                        "-fx-border-color: " + BORDER + ";" +
                                                        "-fx-text-fill: " + BLUE + ";" +
                                                        "-fx-background-radius: 5px;" +
                                                        "-fx-border-radius: 5px;" +
                                                        "-fx-padding: 7px 14px;" +
                                                        "-fx-cursor: hand;");

                        changeMethod.setOnAction(changeEvent -> {

                                // Show all four payment boxes
                                upi.setVisible(true);
                                upi.setManaged(true);

                                card.setVisible(true);
                                card.setManaged(true);

                                netBanking.setVisible(true);
                                netBanking.setManaged(true);

                                cash.setVisible(true);
                                cash.setManaged(true);

                                // Remove selection
                                group.selectToggle(null);

                                // Remove Card details
                                paymentDetails.getChildren().clear();

                                updatePaymentStyles(
                                                upi,
                                                card,
                                                netBanking,
                                                cash);
                        });

                        cardDetails.getChildren().addAll(
                                        cardNumberLabel,
                                        cardNumber,
                                        nameLabel,
                                        cardholderName,
                                        expiryCvv,
                                        changeMethod);

                        paymentDetails.getChildren().setAll(
                                        cardDetails);
                });

                netBanking.setOnAction(event -> {
                        selectedPaymentMethod.set("Net Banking");

                        updatePaymentStyles(
                                        upi,
                                        card,
                                        netBanking,
                                        cash);

                        // Hide UPI
                        upi.setVisible(false);
                        upi.setManaged(false);

                        // Hide Card
                        card.setVisible(false);
                        card.setManaged(false);

                        // Hide Cash on Delivery
                        cash.setVisible(false);
                        cash.setManaged(false);

                        VBox bankDetails = new VBox(10);

                        Label bankLabel = new Label(
                                        "Select Bank");

                        bankLabel.setStyle(
                                        "-fx-font-size: 12px;" +
                                                        "-fx-font-weight: bold;" +
                                                        "-fx-text-fill: " + DARK + ";");

                        ComboBox<String> bank = new ComboBox<>();

                        bank.setPromptText(
                                        "Select Bank");

                        bank.setPrefHeight(40);

                        bank.setMaxWidth(
                                        Double.MAX_VALUE);

                        bank.getItems().addAll(
                                        "State Bank of India",
                                        "HDFC Bank",
                                        "ICICI Bank",
                                        "Axis Bank",
                                        "Kotak Mahindra Bank");

                        Label userLabel = new Label(
                                        "User ID");

                        userLabel.setStyle(
                                        "-fx-font-size: 12px;" +
                                                        "-fx-font-weight: bold;" +
                                                        "-fx-text-fill: " + DARK + ";");

                        TextField userId = new TextField();

                        userId.setPromptText(
                                        "Enter User ID");

                        userId.setPrefHeight(40);

                        styleTextField(userId);

                        HBox info = new HBox(8);

                        info.setPadding(
                                        new Insets(10));

                        info.setStyle(
                                        "-fx-background-color: #F1F3FF;" +
                                                        "-fx-border-color: " + BORDER + ";" +
                                                        "-fx-border-radius: 5px;" +
                                                        "-fx-background-radius: 5px;");

                        Label infoIcon = new Label("ⓘ");

                        infoIcon.setStyle(
                                        "-fx-text-fill: " + BLUE + ";" +
                                                        "-fx-font-size: 13px;");

                        Label infoText = new Label(
                                        "You will be redirected to your bank's\n" +
                                                        "secure payment page.");

                        infoText.setStyle(
                                        "-fx-font-size: 10px;" +
                                                        "-fx-text-fill: " + TEXT + ";");

                        info.getChildren().addAll(
                                        infoIcon,
                                        infoText);

                        Button changeMethod = new Button(
                                        "← Change Payment Method");

                        changeMethod.setStyle(
                                        "-fx-background-color: white;" +
                                                        "-fx-border-color: " + BORDER + ";" +
                                                        "-fx-text-fill: " + BLUE + ";" +
                                                        "-fx-background-radius: 5px;" +
                                                        "-fx-border-radius: 5px;" +
                                                        "-fx-padding: 7px 14px;" +
                                                        "-fx-cursor: hand;");

                        changeMethod.setOnAction(changeEvent -> {

                                // Show all four payment boxes
                                upi.setVisible(true);
                                upi.setManaged(true);

                                card.setVisible(true);
                                card.setManaged(true);

                                netBanking.setVisible(true);
                                netBanking.setManaged(true);

                                cash.setVisible(true);
                                cash.setManaged(true);

                                // Remove selection
                                group.selectToggle(null);

                                // Remove Net Banking details
                                paymentDetails.getChildren().clear();

                                updatePaymentStyles(
                                                upi,
                                                card,
                                                netBanking,
                                                cash);
                        });

                        bankDetails.getChildren().addAll(
                                        bankLabel,
                                        bank,
                                        userLabel,
                                        userId,
                                        info,
                                        changeMethod);

                        paymentDetails.getChildren().setAll(
                                        bankDetails);
                });

                cash.setOnAction(event -> {
                        selectedPaymentMethod.set("Cash on Delivery");

                        updatePaymentStyles(
                                        upi,
                                        card,
                                        netBanking,
                                        cash);

                        // Hide UPI
                        upi.setVisible(false);
                        upi.setManaged(false);

                        // Hide Card
                        card.setVisible(false);
                        card.setManaged(false);

                        // Hide Net Banking
                        netBanking.setVisible(false);
                        netBanking.setManaged(false);

                        VBox codDetails = new VBox(10);

                        codDetails.setPadding(
                                        new Insets(15));

                        codDetails.setStyle(
                                        "-fx-background-color: #F1F3FF;" +
                                                        "-fx-border-color: " + BORDER + ";" +
                                                        "-fx-border-radius: 6px;" +
                                                        "-fx-background-radius: 6px;");

                        Label title = new Label(
                                        "✓ Cash on Delivery");

                        title.setStyle(
                                        "-fx-font-size: 14px;" +
                                                        "-fx-font-weight: bold;" +
                                                        "-fx-text-fill: " + DARK + ";");

                        Label message = new Label(
                                        "Pay when the rental is delivered.\n" +
                                                        "No online payment is required now.");

                        message.setWrapText(true);

                        message.setStyle(
                                        "-fx-font-size: 11px;" +
                                                        "-fx-text-fill: " + TEXT + ";");

                        Label note = new Label(
                                        "Payment status will remain Pending until payment is received.");

                        note.setWrapText(true);

                        note.setStyle(
                                        "-fx-font-size: 10px;" +
                                                        "-fx-text-fill: " + TEXT + ";");

                        Button changeMethod = new Button(
                                        "← Change Payment Method");

                        changeMethod.setStyle(
                                        "-fx-background-color: white;" +
                                                        "-fx-border-color: " + BORDER + ";" +
                                                        "-fx-text-fill: " + BLUE + ";" +
                                                        "-fx-background-radius: 5px;" +
                                                        "-fx-border-radius: 5px;" +
                                                        "-fx-padding: 7px 14px;" +
                                                        "-fx-cursor: hand;");

                        changeMethod.setOnAction(changeEvent -> {

                                // Show all four payment boxes
                                upi.setVisible(true);
                                upi.setManaged(true);

                                card.setVisible(true);
                                card.setManaged(true);

                                netBanking.setVisible(true);
                                netBanking.setManaged(true);

                                cash.setVisible(true);
                                cash.setManaged(true);

                                // Remove selection
                                group.selectToggle(null);

                                // Remove COD details
                                paymentDetails.getChildren().clear();

                                updatePaymentStyles(
                                                upi,
                                                card,
                                                netBanking,
                                                cash);
                        });

                        codDetails.getChildren().addAll(
                                        title,
                                        message,
                                        note,
                                        changeMethod);

                        paymentDetails.getChildren().setAll(
                                        codDetails);
                });

                box.getChildren().addAll(
                                heading,
                                methods,
                                paymentDetails);

                return box;
        }

        private static VBox createCardFields() {

                VBox box = new VBox(7);

                Label cardNumberLabel = fieldLabel("Card Number *");

                TextField cardNumber = new TextField();
                cardNumber.setPromptText("Enter card number");
                cardNumber.setPrefHeight(36);
                cardNumber.setStyle(
                                "-fx-border-color: " + RED + ";" +
                                                "-fx-border-radius: 5px;" +
                                                "-fx-background-radius: 5px;" +
                                                "-fx-text-fill: " + RED + ";");

                cardNumber.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-radius: 5px;" +
                                                "-fx-background-radius: 5px;" +
                                                "-fx-padding: 7px;");

                Label holderLabel = fieldLabel("Cardholder Name *");

                TextField holder = new TextField();
                holder.setPromptText("e.g. John Doe");
                holder.setPrefHeight(36);
                styleTextField(holder);

                GridPane bottom = new GridPane();
                bottom.setHgap(15);

                Label expiryLabel = fieldLabel("Expiry Date *");
                Label cvvLabel = fieldLabel("CVV *");

                TextField expiry = new TextField();
                expiry.setPromptText("MM/YY");
                expiry.setPrefHeight(36);
                styleTextField(expiry);

                TextField cvv = new TextField();
                cvv.setPromptText("123");
                cvv.setPrefHeight(36);
                styleTextField(cvv);

                VBox expiryBox = new VBox(6, expiryLabel, expiry);
                VBox cvvBox = new VBox(6, cvvLabel, cvv);

                bottom.add(expiryBox, 0, 0);
                bottom.add(cvvBox, 1, 0);

                GridPane.setHgrow(expiryBox, Priority.ALWAYS);
                GridPane.setHgrow(cvvBox, Priority.ALWAYS);

                box.getChildren().addAll(
                                cardNumberLabel,
                                cardNumber,
                                holderLabel,
                                holder,
                                bottom);

                return box;
        }

        private static VBox createPriceSummary(
                        Stage stage,
                        RentalModel rental,
                        LocalDate startDate,
                        LocalDate endDate,
                        String fulfillmentMethod,
                        TextArea deliveryAddress) {

                VBox box = createCard();
                box.setPrefWidth(350);
                box.setMaxWidth(350);
                box.setPadding(new Insets(22));

                long rentalDays = java.time.temporal.ChronoUnit.DAYS.between(
                                startDate,
                                endDate);

                double rentalAmount = rental.getPricePerDay() * rentalDays;

                Label heading = new Label("Price Summary");
                heading.setStyle(
                                "-fx-font-size: 17px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + DARK + ";");

                HBox rentalRow = priceRow(
                                "Rental Price (" + rentalDays + " days)",
                                "₹" + rentalAmount);
                double deliveryFee = 15.00;

                HBox delivery = priceRow(
                                "Delivery Fee",
                                "₹" + deliveryFee);
                HBox deposit = priceRow(
                                "Security Deposit",
                                "₹" + rental.getSecurityDeposit());
                double serviceFee = 10.50;

                HBox service = priceRow(
                                "Service Fee",
                                "₹" + serviceFee);
                double discountAmount = 0.00;

                HBox discount = priceRow(
                                "Discount",
                                "-₹" + discountAmount);

                javafx.scene.control.Separator separator = new javafx.scene.control.Separator();

                double totalAmount = rentalAmount
                                + deliveryFee
                                + rental.getSecurityDeposit()
                                + serviceFee
                                - discountAmount;
                Label total = new Label(
                                "₹" + totalAmount);

                total.setStyle(
                                "-fx-font-size: 17px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + DARK + ";");

                total.setStyle(
                                "-fx-font-size: 19px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + DARK + ";");

                Label totalLabel = new Label("Total Amount");

                totalLabel.setStyle(
                                "-fx-font-size: 17px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + DARK + ";");

                Region totalSpacer = new Region();
                HBox.setHgrow(totalSpacer, Priority.ALWAYS);

                HBox totalRow = new HBox(
                                totalLabel,
                                totalSpacer,
                                total);

                Label couponLabel = new Label("Apply Coupon");
                couponLabel.setStyle(
                                "-fx-font-size: 11px;" +
                                                "-fx-text-fill: " + DARK + ";");

                TextField coupon = new TextField();
                coupon.setPromptText("ENTER CODE");
                coupon.setPrefHeight(34);
                styleTextField(coupon);

                Button apply = new Button("Apply");
                apply.setPrefHeight(34);
                apply.setStyle(
                                "-fx-background-color: #ECECF5;" +
                                                "-fx-text-fill: " + DARK + ";" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-background-radius: 5px;" +
                                                "-fx-border-radius: 5px;" +
                                                "-fx-cursor: hand;");

                HBox couponRow = new HBox(7, coupon, apply);
                HBox.setHgrow(coupon, Priority.ALWAYS);

                HBox protection = new HBox(8);
                protection.setPadding(new Insets(10));
                protection.setStyle(
                                "-fx-background-color: #F1F3FF;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-radius: 5px;" +
                                                "-fx-background-radius: 5px;");

                Label lock = new Label("▣");
                lock.setStyle(
                                "-fx-text-fill: " + BLUE + ";" +
                                                "-fx-font-size: 15px;");

                Label protectedText = new Label(
                                "Your payment is protected by RentSathi\n" +
                                                "secure payment processing.");
                protectedText.setStyle(
                                "-fx-font-size: 10px;" +
                                                "-fx-text-fill: " + TEXT + ";");

                protection.getChildren().addAll(lock, protectedText);

                FontIcon lockIcon = new FontIcon(FontAwesomeSolid.LOCK);

                lockIcon.setIconSize(16);
                lockIcon.setIconColor(
                                javafx.scene.paint.Color.WHITE);

                Button pay = new Button(
                                "Pay & Confirm Booking",
                                lockIcon);

                pay.setMaxWidth(Double.MAX_VALUE);
                pay.setPrefHeight(48);
                pay.setStyle(
                                "-fx-background-color: " + BLUE + ";" +
                                                "-fx-text-fill: white;" +
                                                "-fx-font-size: 13px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-background-radius: 5px;" +
                                                "-fx-cursor: hand;");

                pay.setOnAction(event -> {

                        System.out.println(
                                        "CHECKOUT FULFILLMENT METHOD = ["
                                                        + fulfillmentMethod
                                                        + "]");

                        String paymentMethod = selectedPaymentMethod.get();

                        RentalRequest request = new RentalRequest(
                                        "RS-" + System.currentTimeMillis(),
                                        rental,
                                        "CURRENT_CUSTOMER",
                                        startDate,
                                        endDate,
                                        1,
                                        fulfillmentMethod,
                                        paymentMethod,
                                        totalAmount,
                                        "PENDING",
                                        java.time.LocalDateTime.now().toString());

                        System.out.println(
                                        "CUSTOMER ADDRESS AT CHECKOUT = ["
                                                        + deliveryAddress.getText()
                                                        + "]");

                        request.setCustomerAddress(
                                        deliveryAddress.getText());

                        request.setDeliveryStatus(
                                        "PENDING");

                        RentalRequestStore.addRequest(request);

                        BookingConfirmationScreen.show(
                                        stage,
                                        rental,
                                        startDate,
                                        endDate,
                                        fulfillmentMethod,
                                        paymentMethod);
                });

                Label terms = new Label(
                                "By continuing, you agree to the rental terms and\n" +
                                                "payment policy.");
                terms.setWrapText(true);
                terms.setAlignment(Pos.CENTER);
                terms.setStyle(
                                "-fx-font-size: 10px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + TEXT + ";");

                box.getChildren().addAll(
                                heading,
                                rentalRow,
                                delivery,
                                deposit,
                                service,
                                discount,
                                separator,
                                totalRow,
                                couponLabel,
                                couponRow,
                                protection,
                                pay,
                                terms);

                return box;
        }

        private static ToggleButton paymentIconButton(
                        FontAwesomeSolid icon,
                        String text) {

                ToggleButton button = new ToggleButton();

                VBox content = new VBox(8);
                content.setAlignment(Pos.CENTER);

                FontIcon iconView = new FontIcon(icon);

                iconView.setIconSize(24);
                iconView.setIconColor(
                                javafx.scene.paint.Color.web(BLUE));

                Label textLabel = new Label(text);

                textLabel.setStyle(
                                "-fx-font-size: 11px;" +
                                                "-fx-text-fill: " + BLUE + ";");

                content.getChildren().addAll(
                                iconView,
                                textLabel);

                button.setGraphic(content);

                button.setPrefHeight(100);
                button.setMaxWidth(Double.MAX_VALUE);

                return button;
        }

        private static void updatePaymentStyles(
                        ToggleButton upi,
                        ToggleButton card,
                        ToggleButton netBanking,
                        ToggleButton cash) {

                ToggleButton[] buttons = {
                                upi,
                                card,
                                netBanking,
                                cash
                };

                for (ToggleButton button : buttons) {

                        if (button.isSelected()) {

                                button.setStyle(
                                                "-fx-background-color: #F1F3FF;" +
                                                                "-fx-border-color: " + BLUE + ";" +
                                                                "-fx-border-width: 2px;" +
                                                                "-fx-border-radius: 5px;" +
                                                                "-fx-background-radius: 5px;" +
                                                                "-fx-cursor: hand;");

                        } else {

                                button.setStyle(
                                                "-fx-background-color: transparent;" +
                                                                "-fx-border-color: " + BORDER + ";" +
                                                                "-fx-border-radius: 5px;" +
                                                                "-fx-background-radius: 5px;" +
                                                                "-fx-cursor: hand;");
                        }
                }
        }

        private static VBox createCard() {

                VBox box = new VBox(12);
                box.setPadding(new Insets(18));
                box.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-radius: 7px;" +
                                                "-fx-background-radius: 7px;");

                return box;
        }

        private static VBox smallInfoBox(
                        String title,
                        String value) {

                VBox box = new VBox(2);
                box.setPadding(new Insets(5, 8, 5, 8));
                box.setStyle(
                                "-fx-background-color: #F5F6FC;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-radius: 4px;" +
                                                "-fx-background-radius: 4px;");

                Label titleLabel = new Label(title);
                titleLabel.setStyle(
                                "-fx-font-size: 9px;" +
                                                "-fx-text-fill: " + TEXT + ";");

                Label valueLabel = new Label(value);
                valueLabel.setStyle(
                                "-fx-font-size: 11px;" +
                                                "-fx-text-fill: " + DARK + ";");

                box.getChildren().addAll(
                                titleLabel,
                                valueLabel);

                return box;
        }

        private static Label fieldLabel(String text) {

                Label label = new Label(text);
                label.setStyle(
                                "-fx-font-size: 11px;" +
                                                "-fx-text-fill: " + DARK + ";");

                return label;
        }

        private static void styleTextField(TextField field) {

                field.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-radius: 5px;" +
                                                "-fx-background-radius: 5px;" +
                                                "-fx-padding: 7px;");
        }

        private static HBox priceRow(
                        String leftText,
                        String rightText) {

                Label left = new Label(leftText);
                left.setStyle(
                                "-fx-font-size: 11px;" +
                                                "-fx-text-fill: " + TEXT + ";");

                Label right = new Label(rightText);
                right.setStyle(
                                "-fx-font-size: 11px;" +
                                                "-fx-text-fill: " + DARK + ";");

                Region spacer = new Region();
                HBox.setHgrow(spacer, Priority.ALWAYS);

                return new HBox(
                                8,
                                left,
                                spacer,
                                right);
        }
}
