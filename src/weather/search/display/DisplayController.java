package weather.search.display;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import weather.search.SearchController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DisplayController implements Initializable {
    @FXML
    private Label weatherLocationLabel;
    @FXML
    private ImageView weatherImageView;
    @FXML
    private Label weatherDescriptionLabel;
    @FXML
    private Label weatherTempLabel;

    private static final Stage DISPLAY_STAGE = new Stage(); // stage the DisplayView is shown on

                                    /* ------- Initializable Methods ------- */

    // This method runs as soon as this class is initialized.
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nullWeatherDisplay();
        setWeatherData();
        centerLabel();
    }
                                    /* ------- JavaFX Main View Methods ------- */

    // Opens new window asking for users desired location to retrieve weather data from
    @FXML
    private void onSearchButtonClick(ActionEvent actionEvent) {
        displaySearchWindow();
    }

    // Closes program when File > close menu item is clicked
    @FXML
    private void onMenuItemCloseAction(ActionEvent actionEvent) {
        System.exit(0);
    }

                                    /* ------- Controller Methods ------- */

    private void centerLabel() {
        System.out.println(weatherLocationLabel.getWidth());
    }

    private void nullWeatherDisplay() {
        weatherLocationLabel.textProperty().setValue(null);
        weatherImageView.imageProperty().setValue(null);
        weatherDescriptionLabel.textProperty().setValue(null);
        weatherTempLabel.textProperty().setValue(null);
    }

    private void setWeatherData() {
        weatherLocationLabel.textProperty().setValue(SearchController.weatherData.getWeatherLocationName());
        weatherImageView.imageProperty().setValue(new Image("http://openweathermap.org/img/w/" + SearchController.weatherData.getWeatherIcon() + ".png"));
        weatherDescriptionLabel.textProperty().setValue(SearchController.weatherData.getWeatherDescription());
        weatherTempLabel.textProperty().setValue(String.valueOf(SearchController.weatherData.getWeatherTempFahrenheit()));
    }

    // opens new window to show search window
    public void displaySearchWindow() {
        SearchController searchController = new SearchController();
        try {
            searchController.initSearchWindow();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // This method is used to open/load the display window by clicking the display button in the main window.
    public void initDisplayWindow() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("DisplayView.fxml"));
        DISPLAY_STAGE.setScene(new Scene(root));
        if (!DISPLAY_STAGE.isShowing()) {
            DISPLAY_STAGE.setTitle("Weather");
            DISPLAY_STAGE.show();
        }
    }
}
