package weather.search;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import weather.search.display.DisplayController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SearchController implements Initializable {
    private static int viewState = 0; // determines what will be shown to the user depending on what the number is
    private static final SearchModel SEARCH_MODEL = new SearchModel(); // model used for the data entered by the user
    private static final Stage SEARCH_STAGE = new Stage();
    public static WeatherData weatherData;


    @FXML
    private Button leftArrowButton;
    @FXML
    private Button rightArrowButton;

    // Data inside text fields depends on the viewState value

    // 0 - City Name, 1 - City Name, 2 - City ID, 3 - Latitude, 4 - Zip Code, 5 - Zip Code
    @FXML
    private TextField primaryTextField = null;

    // 0 - (none), 1 - Country Code, 2 - (none), 3 - Longitude, 4 - Country Code, 5 - (none)
    @FXML
    private TextField secondaryTextField = null;

                                    /* ------- Initializable Methods ------- */

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

                                    /* ------- public methods ------- */



                                    /* ------- JavaFX Search View Methods ------- */

    //changes the view state on left arrow click
    @FXML
    private void onLeftArrowClick(ActionEvent actionEvent) {
        if (viewState == 0) {
            viewState = 5;
        } else viewState--;
        setTextFieldViewState();
    }

    //changes the view state on right arrow click
    @FXML
    private void onRightArrowClick(ActionEvent actionEvent) {
        if (viewState == 5) {
            viewState = 0;
        } else viewState++;
        setTextFieldViewState();

    }

    // next two methods simply set the data for the model
    @FXML
    private void onPrimaryTextFieldInput(ActionEvent actionEvent) {
        setModelData();
    }

    @FXML
    private void onSecondaryTextFieldInput(ActionEvent actionEvent) {
        setModelData();
    }

                                    /* ------- Search Controller Methods ------- */

    // Determines what is shown on the textfields or if they are shown at all depending on the viewState
    private void setTextFieldViewState() {
        switch (viewState) {
            case 0: {
                setTextFieldVisibility(true, false);
                primaryTextField.promptTextProperty().setValue("City Name");
                setTextFieldEmpty();
                break;
            }
            case 1: {
                setTextFieldVisibility(true, true);
                primaryTextField.promptTextProperty().setValue("City Name");
                secondaryTextField.promptTextProperty().setValue("Country Code");
                setTextFieldEmpty();
                break;
            }
            case 2: {
                setTextFieldVisibility(true, false);
                primaryTextField.promptTextProperty().setValue("City ID");
                setTextFieldEmpty();
                break;
            }
            case 3: {
                setTextFieldVisibility(true, true);
                primaryTextField.promptTextProperty().setValue("Latitude");
                secondaryTextField.promptTextProperty().setValue("Longitude");
                setTextFieldEmpty();
                break;
            }
            case 4: {
                setTextFieldVisibility(true, true);
                primaryTextField.promptTextProperty().setValue("Zip Code");
                secondaryTextField.promptTextProperty().setValue("Country Code");
                setTextFieldEmpty();
                break;
            }
            case 5: {
                setTextFieldVisibility(true, false);
                primaryTextField.promptTextProperty().setValue("Zip Code");
                setTextFieldEmpty();
                break;
            }
        }
    }

    // makes sure the text field does not have any user input in them
    private void setTextFieldEmpty() {
        primaryTextField.setText("");
        secondaryTextField.setText("");
    }

    // used to set the whether a text field is visible or not
    private void setTextFieldVisibility(boolean isPrimaryTextFieldVisible, boolean isSecondaryTextFieldVisible) {
        primaryTextField.visibleProperty().setValue(isPrimaryTextFieldVisible);
        secondaryTextField.visibleProperty().setValue(isSecondaryTextFieldVisible);
    }

    // sets data for the model depending on the view state so it can make the appropriate request to the api.
    private void setModelData() {
        SEARCH_MODEL.nullModelData();
        switch (viewState) {
            case 0: {
                SEARCH_MODEL.setCityName(primaryTextField.getText());
                break;
            }
            case 1: {
                if (secondaryTextField == null) {
                    SEARCH_MODEL.setCityName(primaryTextField.getText());
                } else {
                    SEARCH_MODEL.setCityName(primaryTextField.getText());
                    SEARCH_MODEL.setCountryCode(secondaryTextField.getText());
                }
                break;
            }
            case 2: {
                SEARCH_MODEL.setCityID(primaryTextField.getText());
                break;
            }
            case 3: {
                SEARCH_MODEL.setLatitude(primaryTextField.getText());
                SEARCH_MODEL.setLongitude(secondaryTextField.getText());
                break;
            }
            case 4: {
                if (secondaryTextField == null) {
                    SEARCH_MODEL.setZipCode(primaryTextField.getText());
                } else {
                    SEARCH_MODEL.setZipCode(primaryTextField.getText());
                    SEARCH_MODEL.setCountryCode(secondaryTextField.getText());
                }
                break;
            }
            case 5: {
                SEARCH_MODEL.setZipCode(primaryTextField.getText());
                break;
            }
        }
        weatherData = new WeatherData(SEARCH_MODEL.initWeatherData());
        SEARCH_STAGE.close();
        displayWeatherDataWindow();
    }

    // opens new window to show weather
    public void displayWeatherDataWindow() {
        DisplayController displayController = new DisplayController();
        try {
            displayController.initDisplayWindow();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initSearchWindow() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResource("SearchView.fxml"));

        Scene scene = new Scene(root);

        SEARCH_STAGE.setTitle("Weather");
        SEARCH_STAGE.setScene(scene);
        SEARCH_STAGE.show();
    }
                                    /* ------- Getters ------- */

    public WeatherData getWeatherData() {
        return weatherData;
    }

}