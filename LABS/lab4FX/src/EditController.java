import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

public class EditController {

    public static String file;
    @FXML
    TextArea textEdit;

    public void saveTextFile(ActionEvent actionEvent) {
        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select directory for save");
        fileChooser.setInitialFileName(file);
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text", "*.txt"));
        File file = fileChooser.showSaveDialog(stage);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write(textEdit.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeTextFile(ActionEvent actionEvent) {
        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void initialize() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(file)))
        {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                textEdit.appendText(sCurrentLine+"\n");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Некорректный формат входного файла!");
        } catch (IOException e) {
            System.out.println("Такого файла не существует!");
        }
    }
}
