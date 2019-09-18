package weather;

import javafx.application.Application;
import javafx.stage.Stage;
import weather.search.SearchController;

public class Main extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        SearchController searchController = new SearchController();
        searchController.initSearchWindow();
    }
}
