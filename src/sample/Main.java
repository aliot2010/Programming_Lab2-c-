package sample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Main extends Application {
    private AnchorPane topAnchorPane, midAnchorPane, butAnchorPane;
    private RadioButton formula1, formula2;
    private RadioButton radioMemory1, radioMemory2, radioMemory3;
    private Label labelMemory1, labelMemory2, labelMemory3;
    private Button buttonMC, buttonMPlus;
    private ImageView formulaView;
    private ToggleGroup groupeOfFormulaButtons, groupeOfMemoryButtons;
    private final String FORMULA_1="formula1";
    private final String FORMULA_2="formula2";

    private String formulaFlag=null, memoryFlag=null;


    @Override
    public void start(Stage primaryStage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Lab1");

        VBox primaryBox = new VBox();
        createTopAnchorPane();
        createMidAnchorPane();





        butAnchorPane = new AnchorPane();
        butAnchorPane.setPrefSize(600, 200);

        primaryBox.getChildren().addAll(topAnchorPane, midAnchorPane, butAnchorPane);
        primaryStage.setScene(new Scene(primaryBox, 600, 500));
        primaryStage.setResizable(false);

        primaryStage.show();
    }

    Button addRectangleButton(String name) {
        Button btn = new Button(name);
        btn.setPrefSize(70, 20);
        return btn;
    }
    Button addSquerButton(String name) {
        Button btn = new Button(name);
        btn.setPrefSize(35, 35);
        return btn;
    }

    void createMidAnchorPane(){
        midAnchorPane = new AnchorPane();
        midAnchorPane.setPrefSize(600, 200);
        createMemoryRadioGroupe();
        createButtonsInMidPane();
        createMemoryLabels();


        setAnchorsToMidPane();
        Line line =new Line(0, 200, 600, 200);
        midAnchorPane.getChildren().setAll(
                radioMemory1, radioMemory2, radioMemory3, line,
                buttonMC, buttonMPlus, labelMemory1, labelMemory2,
                labelMemory3
        );


    }



    void createTopAnchorPane() {
        topAnchorPane = new AnchorPane();
        topAnchorPane.setPrefSize(600, 100);
        createFormulaRadioGroupe();
        formulaView = new ImageView();
        setAnchorsToTopPane();
        Line line =new Line(0, 100, 600, 100);


        topAnchorPane.getChildren().setAll(formula1, formula2, formulaView,line);
    }

    void createButtonsInMidPane(){
        buttonMC=addSquerButton("MC");
        buttonMPlus=addSquerButton("M+");
        buttonMC.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

            }
        });
        buttonMPlus.setOnMouseClicked(event -> {

        });
    }
    void createMemoryLabels(){
        labelMemory1=new Label("0.0");
        labelMemory2=new Label("0.0");
        labelMemory3=new Label("0.0");

    }

    void setAnchorsToTopPane(){
        AnchorPane.setTopAnchor(formula1, 20.0);
        AnchorPane.setLeftAnchor(formula1, 20.0);
        AnchorPane.setLeftAnchor(formula2, 20.0);
        AnchorPane.setBottomAnchor(formula2, 20.0);
        AnchorPane.setRightAnchor(formulaView, 20.0);
        AnchorPane.setTopAnchor(formulaView, 10.0);
    }
    void setAnchorsToMidPane(){
        AnchorPane.setTopAnchor(radioMemory1, 20.0);
        AnchorPane.setTopAnchor(radioMemory2, 20.0);
        AnchorPane.setTopAnchor(radioMemory3, 20.0);
        AnchorPane.setLeftAnchor(radioMemory2, 260.0);
        AnchorPane.setLeftAnchor(radioMemory1, 20.0);
        AnchorPane.setRightAnchor(radioMemory3, 20.0);

        AnchorPane.setTopAnchor(buttonMPlus, 90.0);
        AnchorPane.setTopAnchor(buttonMC, 90.0);
        AnchorPane.setLeftAnchor(buttonMC, 160.0);
        AnchorPane.setRightAnchor(buttonMPlus, 180.0);

        AnchorPane.setBottomAnchor(labelMemory1, 30.0);
        AnchorPane.setBottomAnchor(labelMemory2, 30.0);
        AnchorPane.setBottomAnchor(labelMemory3, 30.0);
        AnchorPane.setLeftAnchor(labelMemory1, 30.0);
        AnchorPane.setLeftAnchor(labelMemory2,280.0);
        AnchorPane.setRightAnchor(labelMemory3, 30.0);


    }
    void createFormulaRadioGroupe(){

        groupeOfFormulaButtons = new ToggleGroup();
        formula1 = new RadioButton("Формула 1");
        formula2 = new RadioButton("Формула 2");
        formula1.setUserData("formula1");
        formula2.setUserData("formula2");
        formula1.setToggleGroup(groupeOfFormulaButtons);
        formula2.setToggleGroup(groupeOfFormulaButtons);



        onRadioButtonChange();
    }
    void createMemoryRadioGroupe(){
        groupeOfMemoryButtons = new ToggleGroup();
        radioMemory1= new RadioButton("Ячейка 1");
        radioMemory2= new RadioButton("Ячейка 2");
        radioMemory3= new RadioButton("Ячейка 3");
        radioMemory1.setUserData("Memory1");
        radioMemory2.setUserData("Memory2");
        radioMemory3.setUserData("Memory3");
        radioMemory1.setToggleGroup(groupeOfMemoryButtons);
        radioMemory2.setToggleGroup(groupeOfMemoryButtons);
        radioMemory3.setToggleGroup(groupeOfMemoryButtons);

        onMemoryRadioGroupeChange();

    }
    void onMemoryRadioGroupeChange(){
            groupeOfMemoryButtons.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                formulaFlag=(String)groupeOfMemoryButtons.getSelectedToggle().getUserData();

            }
        });
    }

    void onRadioButtonChange( ){
        groupeOfFormulaButtons.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                Image image =new Image(getClass().getResourceAsStream(groupeOfFormulaButtons.getSelectedToggle().getUserData()+".png"));
                formulaFlag=(String)groupeOfFormulaButtons.getSelectedToggle().getUserData();
                formulaView.setImage(image);
                formulaView.setFitHeight(80);
                formulaView.setFitWidth(400);
            }
        });
    }
    public static void main(String[] args) {
        launch(args);
    }
}
