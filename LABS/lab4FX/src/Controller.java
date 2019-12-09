import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class Controller {
    @FXML
    Button btnCreate;
    @FXML
    TextField text;
    @FXML
    ListView<String> listView;

    File file;
    File oldValue;
    String sep = File.separator;

    public void clickCreate(ActionEvent actionEvent) {
        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select file for create");
        fileChooser.setInitialFileName(String.valueOf(file));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text", "*.txt"));
        File tmp = fileChooser.showSaveDialog(stage);

        if(tmp != null){
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(tmp))) {
                bw.write("");
            } catch (IOException e) {
                e.printStackTrace();
            }
            text.setText(String.valueOf(tmp));
            clickGo(null);
        }
    }

    public void clickOpen(ActionEvent actionEvent) throws IOException {
        if(listView.getSelectionModel().getSelectedItem() != null) {
            if(listView.getSelectionModel().getSelectedItem().endsWith(".txt")) {
                text.setText(file + sep + listView.getSelectionModel().getSelectedItem());
            }
        } else {
            Node node = (Node) actionEvent.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select file for open");
            fileChooser.setInitialDirectory(file);
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text", "*.txt"));
            File tmp = fileChooser.showOpenDialog(stage);
            if(tmp!=null){
                text.setText(tmp.toString());
            }
        }
        clickGo(null);
    }

    public void clickDelete(ActionEvent actionEvent) throws IOException {
        if(listView.getSelectionModel().getSelectedItem() != null) {
            if(listView.getSelectionModel().getSelectedItem().endsWith(".txt")) {
                file = new File(file + sep + listView.getSelectionModel().getSelectedItem());
                Files.delete(file.toPath());
                clickBack(null);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Выберете текстовый файл(*.txt) для удаления!");
            alert.showAndWait();
        }
    }

    public void clickGo(ActionEvent actionEvent) {
        try {
            oldValue = file;
            file = new File(text.getText());
            if (file.isDirectory()) {
                ObservableList<String> list = FXCollections.observableList(Arrays.asList(Objects.requireNonNull(file.list())));
                listView.setItems(list);
            } else {
                if (file.isFile() && file.canRead()) {
                    if (file.toString().endsWith(".txt")) {
                        openEditor(file.toString());
                    } else {
                        Desktop.getDesktop().open(file);
                    }
                    clickBack(null);
                } else {
                    file = oldValue;
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information");
                    alert.setHeaderText(null);
                    alert.setContentText("Нет такой папки/файла!");
                    alert.showAndWait();
                }
            }
        } catch (IOException e) {
            file = oldValue;
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Не удалось открыть этот файл! \n" + e.getLocalizedMessage());
            alert.showAndWait();
        }
    }

    public void clickBack(ActionEvent actionEvent) throws IOException {
        if(file.getParent()!=null){
            file = new File(file.getParent());
            text.setText(file.toString());
            clickGo(null);
        }
        /*StringBuilder c = new StringBuilder();
        c.append(file);
        if (c.lastIndexOf(sep) != -1) {
            c.delete(c.lastIndexOf(sep), c.length());
            if (c.toString().endsWith(":")) c.append(sep);
            //file = new File(c.toString());
            text.setText(c.toString());
            clickGo(null);
        }*/
    }

    public void listDoubleClick(MouseEvent mouseEvent) throws IOException {
        if(listView.getSelectionModel().getSelectedItem() != null){
            if (mouseEvent.getButton().equals(MouseButton.PRIMARY) && mouseEvent.getClickCount() == 2) {
                text.setText(file + sep + listView.getSelectionModel().getSelectedItem());
                clickGo(null);
            }
        }
    }

    public void openEditor(String str) {
        Parent root;
        try {
            EditController.file = file.toString();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Editor.fxml"));
            root = loader.load();

            Stage stage = new Stage();
            stage.setTitle(file.toString());

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initOwner(btnCreate.getScene().getWindow());

            stage.showAndWait();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    @FXML
    private void initialize() throws IOException {
        text.setText(System.getProperty("user.dir"));
        clickGo(null);
    }
}