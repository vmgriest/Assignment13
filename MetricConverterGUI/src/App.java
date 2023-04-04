import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class App extends Application 
{
    private TextField fromField;
    private TextField toField;

    @Override
    public void start(Stage primaryStage) 
    {
        Label fromLabel = new Label("From: (ex: # kg, # gr, # km, # mm)");
        fromField = new TextField();
        Label toLabel = new Label("To: (ex: lb, oz, mi, in)");
        toField = new TextField();
        Button convertButton = new Button("Convert");
        convertButton.setOnAction(event -> convert());

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.add(fromLabel, 0, 0);
        gridPane.add(fromField, 1, 0);
        gridPane.add(toLabel, 0, 1);
        gridPane.add(toField, 1, 1);
        gridPane.add(convertButton, 1, 2);

        Scene scene = new Scene(gridPane, 400, 250);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Metric Converter GUI");
        primaryStage.show();
    }
    private void convert() 
    {
        try
        {
            String fromString = fromField.getText();
            double fromValue = Double.parseDouble(fromString.substring(0, fromString.length() - 2));
            String fromUnit = fromString.substring(fromString.length() - 2);

            double baseValue;
            switch (fromUnit) 
            {
                case "kg":
                    baseValue = fromValue * 1000;
                    break;
                case "gr":
                    baseValue = fromValue;
                    break;
                case "km":
                    baseValue = fromValue * 1000;
                    break;
                case "mm":
                    baseValue = fromValue / 1000;
                    break;
                default:
                    toField.setText("Invalid unit");
                    return;
            }
            String toUnit = toField.getText().substring(toField.getText().length() - 2);
            double toValue;
            switch (toUnit) 
            {
                case "lb":
                    toValue = baseValue * 2.20462;
                    break;
                case "oz":
                    toValue = baseValue * 0.035274;
                    break;
                case "mi":
                    toValue = baseValue * 0.000621371;
                    break;
                case "in":
                    toValue = baseValue * 39.3701;
                    break;
                default:
                    toField.setText("Invalid unit");
                    return;
            }
            toField.setText(String.format("%.2f", toValue) + " " + toUnit);
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) 
        {
            toField.setText("Invalid input");
        }
    }

    public static void main(String[] args) 
    {
        launch(args);
    }
}

