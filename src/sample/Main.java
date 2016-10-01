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

import static java.lang.Math.*;


public class Main extends Application {
    private AnchorPane topAnchorPane, midAnchorPane, botAnchorPane;
    private RadioButton formula1, formula2;
    private RadioButton radioMemory1, radioMemory2, radioMemory3;
    private Label labelMemory1, labelMemory2, labelMemory3, result;
    private Button buttonMC, buttonMPlus, start;
    private TextField textFiledX, textFiledY, textFiledZ;
    private ImageView formulaView;
    private ToggleGroup groupeOfFormulaButtons, groupeOfMemoryButtons;
    private final String FORMULA_1="formula1";
    private final String FORMULA_2="formula2";


    private String formulaFlag="formula1";
    private int memoryFlag=1;
    private double memory1=0.0, memory2=0.0, memory3=0.0;
    private final int MEMORY1=1, MEMORY2=2, MEMORY3=3;
    private static double resultOfCalculate=0.0;


    @Override
    public void start(Stage primaryStage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Lab1");

        VBox primaryBox = new VBox();
        createTopAnchorPane();
        createMidAnchorPane();
        createBotAnchorPane();

        primaryBox.getChildren().addAll(topAnchorPane, midAnchorPane, botAnchorPane);
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
    private void createBotAnchorPane() {
        botAnchorPane = new AnchorPane();
        botAnchorPane.setPrefSize(600, 200);
        createVariablsTextFields();
        start=addRectangleButton("Start");
        setOnStartListener();

        result = new Label("0.0");
        //result.setPrefSize(100, 30);




        setAnchorsToBotPane();
        botAnchorPane.getChildren().setAll(textFiledX, textFiledY, textFiledZ, start, result);

    }

    private void setOnStartListener() {
        start.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(formulaFlag.equals(FORMULA_1)){
                    calculateFormula1();

                }else if(formulaFlag.equals(FORMULA_2)){
                    calculateFormula2();
                }
            }
        });
    }

    private  void calculateFormula1() {
            double x= Double.valueOf( textFiledX.getText());
            double y= Double.valueOf( textFiledY.getText());
            double z= Double.valueOf( textFiledY.getText());
            resultOfCalculate=Math.sin(Math.log(y)+Math.sin(Math.PI*y*y))*
                    Math.sqrt(Math.sqrt(pow(x, 2)+Math.sin(z)+Math.exp(Math.cos(z))));

            result.setText(String.valueOf(resultOfCalculate));
    }

    private  void calculateFormula2() {

        double x= Double.valueOf( textFiledX.getText());
        double y= Double.valueOf( textFiledY.getText());
        double z= Double.valueOf( textFiledY.getText());
        resultOfCalculate=pow(cos(exp(x))+pow(log(1+y), 2)+sqrt(exp(cos(x))+sin(PI*z)*sin(PI*z))+sqrt(1/x)+cos(pow(y,2)), sin(z));
        result.setText(String.valueOf(resultOfCalculate));
    }


    private void setAnchorsToBotPane() {
        AnchorPane.setTopAnchor(textFiledX, 20.0);
        AnchorPane.setTopAnchor(textFiledY, 87.0);
        AnchorPane.setBottomAnchor(textFiledZ, 20.0);
        AnchorPane.setLeftAnchor(textFiledX, 20.0);
        AnchorPane.setLeftAnchor(textFiledY, 20.0);
        AnchorPane.setLeftAnchor(textFiledZ, 20.0);

        AnchorPane.setLeftAnchor(start, 250.0);
        AnchorPane.setTopAnchor(start, 87.0);

        AnchorPane.setRightAnchor(result, 90.0);
        AnchorPane.setTopAnchor(result, 87.0);

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
        Image image=new Image(getClass().getResourceAsStream("formula1.png"));
        formulaView.setImage(image);
        formulaView.setFitHeight(80);
        formulaView.setFitWidth(400);
        setAnchorsToTopPane();
        Line line =new Line(0, 100, 600, 100);


        topAnchorPane.getChildren().setAll(formula1, formula2, formulaView,line);
    }
    private void createVariablsTextFields() {
        textFiledX=new TextField("0.0");
        textFiledY=new TextField("0.0");
        textFiledZ=new TextField("0.0");
        textFiledX.setPrefSize(70, 20);
        textFiledY.setPrefSize(70, 20);
        textFiledZ.setPrefSize(70, 20);
    }

    void createButtonsInMidPane(){
        buttonMC=addSquerButton("MC");
        buttonMPlus=addSquerButton("M+");
        buttonMC.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(memoryFlag==MEMORY1){
                    labelMemory1.setText("0.0");
                    memory1=0.0;
                }else if(memoryFlag==MEMORY2){
                    labelMemory2.setText("0.0");
                    memory2=0.0;
                }else if(memoryFlag==MEMORY3){
                    labelMemory3.setText("0.0");
                    memory3=0.0;
                }
            }
        });
        buttonMPlus.setOnMouseClicked(event -> {
            if(memoryFlag==MEMORY1){

                memory1=memory1+resultOfCalculate;
                labelMemory1.setText(String.valueOf(memory1));
            }else if(memoryFlag==MEMORY2){
                memory2=memory2+resultOfCalculate;
                labelMemory2.setText(String.valueOf(memory2));
            }else if(memoryFlag==MEMORY3){
                memory3=memory3+resultOfCalculate;
                labelMemory3.setText(String.valueOf(memory3));
            }
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
        formula1.setSelected(true);

        onRadioButtonChange();
    }
    void createMemoryRadioGroupe(){
        groupeOfMemoryButtons = new ToggleGroup();
        radioMemory1= new RadioButton("Ячейка 1");
        radioMemory2= new RadioButton("Ячейка 2");
        radioMemory3= new RadioButton("Ячейка 3");
        radioMemory1.setUserData(1);
        radioMemory2.setUserData(2);
        radioMemory3.setUserData(3);
        radioMemory1.setToggleGroup(groupeOfMemoryButtons);
        radioMemory2.setToggleGroup(groupeOfMemoryButtons);
        radioMemory3.setToggleGroup(groupeOfMemoryButtons);
        radioMemory1.setSelected(true);
        onMemoryRadioGroupeChange();

    }
    void onMemoryRadioGroupeChange(){
            groupeOfMemoryButtons.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                memoryFlag= (int) groupeOfMemoryButtons.getSelectedToggle().getUserData();

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
