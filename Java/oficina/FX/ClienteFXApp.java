import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ClienteFXApp extends Application {

    @Override
    public void start(Stage stage) {
        TableView<Cliente> tableView = new TableView<>();
        ObservableList<Cliente> data = FXCollections.observableArrayList(Cliente.getAll());

        TableColumn<Cliente, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Cliente, String> nomeColumn = new TableColumn<>("Nome");
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));

        TableColumn<Cliente, String> telefoneColumn = new TableColumn<>("Telefone");
        telefoneColumn.setCellValueFactory(new PropertyValueFactory<>("telefone"));

        tableView.setItems(data);
        tableView.getColumns().addAll(idColumn, nomeColumn, telefoneColumn);

        VBox vbox = new VBox(tableView);
        Scene scene = new Scene(vbox);

        stage.setScene(scene);
        stage.setTitle("Lista de Clientes");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
