package adivinApp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdivinApp extends Application {

	private Label comentarioLabel;
	private Button comprobarButton;
	private TextField numeroText;
	private VBox root;
	private Alert alerta;
	private int numeroRandom = (int) (Math.random() * 100 + 1);
	private int contador=0;

	@Override
	public void start(Stage primaryStage) throws Exception {

		// creamos una etiqueta
		comentarioLabel = new Label();
		comentarioLabel.setText("Introduce un numero del 1 al 100");

		// creamos el cuadro de texto
		numeroText = new TextField();
		numeroText.setPromptText("Introduce un numero");
		numeroText.setMaxWidth(150);
		// creamos un botón
		comprobarButton = new Button();
		comprobarButton.setText("Comprobar");
		comprobarButton.setOnAction(e -> onNumeroRandomButtonAction(e));
		comprobarButton.setDefaultButton(true);

		// panel con disposición vertical
		root = new VBox();
		root.setSpacing(15);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(comentarioLabel, numeroText, comprobarButton);

		Scene escena = new Scene(root, 320, 200);

		primaryStage.setScene(escena);
		primaryStage.setTitle("AdivinApp");
		primaryStage.show();
	}

	private void onNumeroRandomButtonAction(ActionEvent e) {
		int numeroUsuario = 0;
		String respuestaUsu = numeroText.getText();
		if (!respuestaUsu.chars().allMatch(Character::isDigit) || respuestaUsu.equals("")) {
			alerta = new Alert(AlertType.ERROR);
			alerta.setTitle("AdivinApp");
			alerta.setHeaderText("Error");
			alerta.setContentText("El número introducido no es valido");
			alerta.showAndWait();
		} else {
			numeroUsuario = Integer.parseInt(respuestaUsu);

			if (numeroUsuario == numeroRandom) {
				alerta = new Alert(AlertType.INFORMATION);
				alerta.setTitle("AdivinApp");
				alerta.setHeaderText("¡Has Ganado!");
				alerta.setContentText("Solo haz necesitado " + contador + " intentos.\n" + "Vuelve a jugar y hazlo mejor");
				alerta.showAndWait();
				numeroRandom = (int) (Math.random() * 100 + 1);
				contador=0;
			} else if (numeroUsuario > numeroRandom) {
				alerta = new Alert(AlertType.WARNING);
				alerta.setTitle("AdivinApp");
				alerta.setHeaderText("¡Has fallado!");
				alerta.setContentText(
						"El número a adivinar es menor que " + numeroUsuario + "." + "\n Vuelve a intentarlo.");
				alerta.showAndWait();
				contador++;
			} else if (numeroUsuario < numeroRandom) {
				alerta = new Alert(AlertType.WARNING);
				alerta.setTitle("AdivinApp");
				alerta.setHeaderText("¡Has fallado!");
				alerta.setContentText(
						"El número a adivinar es mayor que " + numeroUsuario + "." + "\n Vuelve a intentarlo.");
				alerta.showAndWait();
				contador++;
			}
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
