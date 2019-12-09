package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.*;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class Controller {
    @FXML
    private TextField text;
    String labs;
    String[] labPath = new String[6];
    String sep = File.separator;

    public void labExecute(String path, String command) throws IOException {
        if(new File(path).canExecute()){
            Process proc = Runtime.getRuntime().exec(command);
            BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line;
            while((line=br.readLine())!=null){
                System.out.println(line);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Specify the folder LABS");
            alert.show();
        }
    }

    public void openLab1(ActionEvent actionEvent) throws IOException, InterruptedException {
        String path = labs + labPath[0];
        labExecute(path,"java -jar \"" + path + "\" 5");
    }

    public void openLab2(ActionEvent actionEvent) throws IOException {
        String path = labs + labPath[1];
        labExecute(path, "java -jar \"" + path + "\" "+labs+"lab2"+sep+"1.txt "+labs+"lab2"+sep+"out.txt");
    }

    public void openLab3(ActionEvent actionEvent) throws IOException {
        String path = labs + labPath[2];
        labExecute(path, "java -jar \"" + path + "\"");
    }

    public void openLab4FX(ActionEvent actionEvent) throws IOException {
        String path = labs + labPath[3];
        labExecute(path, "java --module-path \"" +
                System.getenv("JAVAFX_HOME") +
                "\" --add-modules javafx.controls,javafx.fxml -jar " +
                "\""+ path +"\"");
    }

    public void openLab5(ActionEvent actionEvent) throws IOException {
        String path = labs + labPath[4];
        labExecute(path, "java -jar \"" + path + "\" " + labs + "lab5" + sep + "1.properties");
    }


    public void openLab6(ActionEvent actionEvent) throws IOException {
        String path = labs + labPath[5];
        labExecute(path, "java -jar \"" + path + "\" " + labs + "lab6_synchronized" + sep + "Lab6InputExample.xml");
    }

    public void openLABS(ActionEvent actionEvent) {
        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        DirectoryChooser dirChooser = new DirectoryChooser();
        dirChooser.setTitle("Select folder LABS");

        labs = String.valueOf(dirChooser.showDialog(stage))+sep;
        text.setText(labs);
    }

    public void initialize(){
        labs = new File(new File("").getAbsolutePath()).getParent()+sep;
        text.setText(labs);
        labPath[0]="lab1"+sep+"out"+sep+"artifacts"+sep+"lab1_jar"+sep+"lab1.jar";
        labPath[1]="lab2"+sep+"out"+sep+"artifacts"+sep+"lab2_jar"+sep+"lab2.jar";
        labPath[2]="lab3"+sep+"out"+sep+"artifacts"+sep+"lab3_jar"+sep+"lab3.jar";
        labPath[3]="lab4FX"+sep+"out"+sep+"artifacts"+sep+"lab4FX"+sep+"lab4FX.jar";
        labPath[4]="lab5"+sep+"out"+sep+"artifacts"+sep+"lab5_jar"+sep+"lab5.jar";
        labPath[5]="lab6_synchronized"+sep+"out"+sep+"artifacts"+sep+"lab6_jar"+sep+"lab6.jar";
    }

    public void onTextChanged(ActionEvent actionEvent) {
        labs = text.getText()+sep;
    }
}