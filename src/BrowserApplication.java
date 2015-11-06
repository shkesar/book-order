import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.sql.SQLException;

public class BrowserApplication extends Application {
    private String username = "shubham";
    private Database database = new Database();

    @Override
    public void start(Stage primaryStage) throws Exception {
        // components
        WebView bookBrowser = new WebView();
        WebEngine bookBrowserEngine = bookBrowser.getEngine();

        TextField urlField = new TextField();
        Button goButton = new Button("Go");
        Button rentButton = new Button("Rent");
        Label priceLabel = new Label("100");

        bookBrowserEngine.locationProperty().addListener(url -> {
            urlField.setText(bookBrowserEngine.getLocation());
            try {
                priceLabel.setText("Rs " + database.getBookPrice(bookBrowserEngine.getLocation()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        String homeURL = "http://www.goodreads.com";
        bookBrowserEngine.load(homeURL);
        urlField.setText(homeURL);

        goButton.setOnAction(ae -> bookBrowserEngine.load(urlField.getText().startsWith("http://") ?
                urlField.getText() : "http://" + urlField.getText()));
        urlField.setOnAction(ae -> goButton.fire());
        rentButton.setOnAction(ae -> database.insertOrder(new Order(urlField.getText(), username)));

        // layout
        HBox navigationToolbar = new HBox(10) {{
            getChildren().addAll(urlField, goButton, rentButton, priceLabel);
        }};
        VBox contentPane = new VBox(20) {{
            getChildren().addAll(navigationToolbar, bookBrowser);
        }};
        Scene scene = new Scene(contentPane, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
