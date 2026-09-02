package com.rentsathi.ui.screens;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class WelcomeScreen {

    private static final String BLUE = "#3657C8";
    private static final String DARK_BLUE = "#29476B";
    private static final String PURPLE = "#7166E8";
    private static final String BACKGROUND = "#F8F8FD";
    private static final String ICON_BACKGROUND = "#F1F2FF";
    private static final String BORDER = "#C8CBD9";

    public static void show(Stage stage) {

        BorderPane root = new BorderPane();

        root.setStyle(
                "-fx-background-color: " + BACKGROUND + ";"
        );

        Rectangle outerBorder = new Rectangle();

        outerBorder.setFill(Color.TRANSPARENT);
        outerBorder.setStroke(Color.web(PURPLE));
        outerBorder.setStrokeWidth(5);
        outerBorder.setArcWidth(24);
        outerBorder.setArcHeight(24);
        outerBorder.setMouseTransparent(true);

        outerBorder.widthProperty().bind(root.widthProperty());
        outerBorder.heightProperty().bind(root.heightProperty());

        HBox logoBox = createLogo();

        VBox header = new VBox(
                12,
                logoBox,
                createTitle()
        );

        header.setAlignment(Pos.CENTER);
        header.setPadding(
                new Insets(20, 20, 0, 20)
        );

        GridPane cards = new GridPane();

        cards.setHgap(26);
        cards.setVgap(18);
        cards.setAlignment(Pos.CENTER);

        cards.add(
                createCard(
                        "Customer",
                        "Find and rent what you need. Browse listings, book items, and manage your rentals seamlessly.",
                        createCustomerIcon(),
                        "Select Customer",

                        () -> {
                                System.out.println("CUSTOMER BUTTON CLICKED");
                                CustomerLoginScreen.show(stage);
                        }
                ),
                0,
                0
        );

        cards.add(
                createCard(
                        "Owner",
                        "List and manage your rental items. Track earnings, handle bookings, and grow your rental business.",
                        createOwnerIcon(),
                        "Select Owner",
                        () -> {
                                System.out.println("OWNER BUTTON CLICKED");
                                OwnerLoginScreen.show(stage);
                        }
                ),
                1,
                0
        );

        cards.add(
                createCard(
                        "Delivery Partner",
                        "Manage rental pickups and deliveries. Optimize routes and ensure timely logistics for rentals.",
                        createDeliveryIcon(),
                        "Select Partner",
                        () -> {
                                DeliveryPartnerLoginScreen.show(stage);
                        }
                ),
                0,
                1
        );

        cards.add(
                createCard(
                        "Admin",
                        "Manage the RentSathi platform. Oversee users, handle disputes, and monitor system performance.",
                        createAdminIcon(),
                        "Select Admin",
                        () -> {
                                AdminLoginScreen.show(stage);
                        }
                ),
                1,
                1
        );

        VBox center = new VBox(cards);

        center.setAlignment(Pos.CENTER);
        center.setPadding(
                new Insets(10, 20, 5, 20)
        );

        HBox support = new HBox();

        Label question = new Label("?");

        question.setMinSize(17, 17);
        question.setMaxSize(17, 17);
        question.setAlignment(Pos.CENTER);

        question.setStyle(
                "-fx-border-color: " + DARK_BLUE + ";" +
                "-fx-border-radius: 50%;" +
                "-fx-background-radius: 50%;" +
                "-fx-text-fill: " + DARK_BLUE + ";" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;"
        );

        Label supportText = new Label(
                "Need help? Contact Support"
        );

        supportText.setStyle(
                "-fx-text-fill: " + DARK_BLUE + ";" +
                "-fx-font-size: 16px;" +
                "-fx-font-family: 'Arial';"
        );

        support.setAlignment(Pos.CENTER);
        support.setSpacing(6);

        support.getChildren().addAll(
                question,
                supportText
        );

        support.setPadding(
                new Insets(5, 0, 18, 0)
        );

        root.setTop(header);
        root.setCenter(center);
        root.setBottom(support);

        StackPane finalRoot = new StackPane();

        finalRoot.getChildren().addAll(
                root,
                outerBorder
        );

        Scene scene = new Scene(
                finalRoot,
                1500,
                830
        );

        scene.setFill(
                Color.web(BACKGROUND)
        );

        stage.setTitle("RentSathi");
        stage.setScene(scene);
        stage.setMinWidth(1000);
        stage.setMinHeight(650);
        stage.show();
    }

    private static HBox createLogo() {

        Image logoImage = new Image(
                WelcomeScreen.class
                        .getResource("/images/logo.png")
                        .toExternalForm()
        );

        ImageView logoView = new ImageView(logoImage);

logoView.setFitWidth(300);
logoView.setFitHeight(90);
logoView.setPreserveRatio(true);
logoView.setSmooth(true);


       // Label title = new Label("RentSathi");

        // title.setStyle(
        //         "-fx-font-family: 'Arial';" +
        //         "-fx-font-size: 38px;" +
        //         "-fx-font-weight: bold;" +
        //         "-fx-text-fill: #111827;"
        // );

        HBox box = new HBox(
                8,
                logoView

        );

        box.setAlignment(Pos.CENTER);

        return box;
    }

    private static VBox createTitle() {

        Label title = new Label(
                "Welcome to RentSathi"
        );

        title.setStyle(
                "-fx-font-family: 'Arial';" +
                "-fx-font-size: 36px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #111827;"
        );

        Label subtitle = new Label(
                "Choose your account type to continue"
        );

        subtitle.setStyle(
                "-fx-font-family: 'Arial';" +
                "-fx-font-size: 18px;" +
                "-fx-text-fill: " + DARK_BLUE + ";"
        );

        VBox box = new VBox(
                8,
                title,
                subtitle
        );

        box.setAlignment(Pos.CENTER);

        return box;
    }

    private static VBox createCard(
            String titleText,
            String descriptionText,
            StackPane icon,
            String buttonText,
            Runnable action
    ) {

        Label title = new Label(titleText);

        title.setStyle(
                "-fx-font-family: 'Arial';" +
                "-fx-font-size: 22px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #111827;"
        );

        Label description = new Label(
                descriptionText
        );

        description.setWrapText(true);
        description.setMaxWidth(480);

        description.setStyle(
                "-fx-font-family: 'Arial';" +
                "-fx-font-size: 16px;" +
                "-fx-text-fill: " + DARK_BLUE + ";" +
                "-fx-line-spacing: 4px;"
        );

        Button button = new Button(
                buttonText + "   →"
        );

        button.setOnAction(
                event -> action.run()
        );      

        button.setMouseTransparent(false);

        button.setMaxWidth(
                Double.MAX_VALUE
        );

        button.setPrefHeight(44);

        button.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 8px;" +
                "-fx-background-radius: 8px;" +
                "-fx-text-fill: #111827;" +
                "-fx-font-family: 'Arial';" +
                "-fx-font-size: 16px;" +
                "-fx-cursor: hand;"
        );

        button.setOnMouseEntered(event ->
                button.setStyle(
                        "-fx-background-color: #F4F5FF;" +
                        "-fx-border-color: " + BLUE + ";" +
                        "-fx-border-width: 1px;" +
                        "-fx-border-radius: 8px;" +
                        "-fx-background-radius: 8px;" +
                        "-fx-text-fill: " + BLUE + ";" +
                        "-fx-font-family: 'Arial';" +
                        "-fx-font-size: 16px;" +
                        "-fx-cursor: hand;"
                )
        );

        button.setOnMouseExited(event ->
                button.setStyle(
                        "-fx-background-color: white;" +
                        "-fx-border-color: " + BORDER + ";" +
                        "-fx-border-width: 1px;" +
                        "-fx-border-radius: 8px;" +
                        "-fx-background-radius: 8px;" +
                        "-fx-text-fill: #111827;" +
                        "-fx-font-family: 'Arial';" +
                        "-fx-font-size: 16px;" +
                        "-fx-cursor: hand;"
                )
        );

        VBox content = new VBox(
                15,
                icon,
                title,
                description,
                button
        );

        content.setPadding(
                new Insets(25, 36, 25, 36)
        );

        content.setAlignment(
                Pos.TOP_LEFT
        );

        VBox card = new VBox(content);

        card.setPrefWidth(550);
        card.setPrefHeight(285);

        card.setMaxWidth(550);
        card.setMaxHeight(285);

        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 14px;" +
                "-fx-background-radius: 14px;"
        );

        return card;
    }

    private static StackPane createIconBase() {

        Rectangle background = new Rectangle(
                70,
                70
        );

        background.setArcWidth(14);
        background.setArcHeight(14);

        background.setFill(
                Color.web(ICON_BACKGROUND)
        );

        StackPane pane = new StackPane();

        pane.setMinSize(70, 70);
        pane.setMaxSize(70, 70);

        pane.getChildren().add(
                background
        );

        return pane;
    }

    private static StackPane createCustomerIcon() {

        StackPane pane = createIconBase();

        Rectangle bag = new Rectangle(
                20,
                20
        );

        bag.setFill(Color.TRANSPARENT);
        bag.setStroke(Color.web(DARK_BLUE));
        bag.setStrokeWidth(3);
        bag.setArcWidth(2);
        bag.setArcHeight(2);

        Arc handle = new Arc(
                0,
                0,
                7,
                7,
                0,
                180
        );

        handle.setFill(Color.TRANSPARENT);
        handle.setStroke(Color.web(DARK_BLUE));
        handle.setStrokeWidth(3);
        handle.setType(ArcType.OPEN);
        handle.setTranslateY(-13);

        pane.getChildren().addAll(
                bag,
                handle
        );

        return pane;
    }

    private static StackPane createOwnerIcon() {

        StackPane pane = createIconBase();

        Line roof1 = new Line(
                -15,
                0,
                0,
                -10
        );

        Line roof2 = new Line(
                0,
                -10,
                15,
                0
        );

        Line base = new Line(
                -15,
                0,
                15,
                0
        );

        Rectangle house = new Rectangle(
                20,
                17
        );

        house.setFill(Color.TRANSPARENT);
        house.setStroke(Color.web(DARK_BLUE));
        house.setStrokeWidth(2.5);
        house.setTranslateY(8);

        roof1.setStroke(Color.web(DARK_BLUE));
        roof2.setStroke(Color.web(DARK_BLUE));
        base.setStroke(Color.web(DARK_BLUE));

        roof1.setStrokeWidth(2.5);
        roof2.setStrokeWidth(2.5);
        base.setStrokeWidth(2.5);

        pane.getChildren().addAll(
                house,
                roof1,
                roof2,
                base
        );

        return pane;
    }

    private static StackPane createDeliveryIcon() {

        StackPane pane = createIconBase();

        Rectangle truck = new Rectangle(
                30,
                18
        );

        truck.setFill(Color.TRANSPARENT);
        truck.setStroke(Color.web(DARK_BLUE));
        truck.setStrokeWidth(2.5);
        truck.setTranslateX(-4);

        Rectangle cabin = new Rectangle(
                12,
                13
        );

        cabin.setFill(Color.TRANSPARENT);
        cabin.setStroke(Color.web(DARK_BLUE));
        cabin.setStrokeWidth(2.5);
        cabin.setTranslateX(17);
        cabin.setTranslateY(3);

        Circle wheel1 = new Circle(
                4,
                Color.TRANSPARENT
        );

        wheel1.setStroke(Color.web(DARK_BLUE));
        wheel1.setStrokeWidth(2.5);
        wheel1.setTranslateX(-12);
        wheel1.setTranslateY(12);

        Circle wheel2 = new Circle(
                4,
                Color.TRANSPARENT
        );

        wheel2.setStroke(Color.web(DARK_BLUE));
        wheel2.setStrokeWidth(2.5);
        wheel2.setTranslateX(17);
        wheel2.setTranslateY(12);

        pane.getChildren().addAll(
                truck,
                cabin,
                wheel1,
                wheel2
        );

        return pane;
    }

    private static StackPane createAdminIcon() {

        StackPane pane = createIconBase();

        Arc shield = new Arc(
                0,
                0,
                15,
                18,
                200,
                140
        );

        shield.setFill(Color.TRANSPARENT);
        shield.setStroke(Color.web(DARK_BLUE));
        shield.setStrokeWidth(2.5);
        shield.setType(ArcType.OPEN);

        Circle person = new Circle(
                4,
                Color.TRANSPARENT
        );

        person.setStroke(Color.web(DARK_BLUE));
        person.setStrokeWidth(2);
        person.setTranslateY(4);

        pane.getChildren().addAll(
                shield,
                person
        );

        return pane;
    }
}