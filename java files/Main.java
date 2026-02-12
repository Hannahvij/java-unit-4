
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.sql.*;

public class Main extends Application {

    TextField name = new TextField();
    TextField email = new TextField();
    TextField dept = new TextField();
    TextArea output = new TextArea();

    @Override
    public void start(Stage stage) {

        name.setPromptText("Name");
        email.setPromptText("Email");
        dept.setPromptText("Department");

        Button insert = new Button("Insert");
        Button view = new Button("View");
        Button update = new Button("Update");
        Button delete = new Button("Delete");

        insert.setOnAction(e -> insertData());
        view.setOnAction(e -> viewData());
        update.setOnAction(e -> updateData());
        delete.setOnAction(e -> deleteData());

        VBox root = new VBox(10, name, email, dept, insert, view, update, delete, output);
        Scene scene = new Scene(root, 400, 500);

        stage.setTitle("Student Management System");
        stage.setScene(scene);
        stage.show();
    }

    void insertData() {
        try {
            Connection con = DBConnection.connect();
            String sql = "INSERT INTO students(name,email,department) VALUES(?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name.getText());
            ps.setString(2, email.getText());
            ps.setString(3, dept.getText());
            ps.execute();
            output.setText("Inserted Successfully");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    void viewData() {
        try {
            Connection con = DBConnection.connect();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM students");
            output.clear();
            while (rs.next()) {
                output.appendText(
                        rs.getInt("id") + " " +
                        rs.getString("name") + " " +
                        rs.getString("email") + " " +
                        rs.getString("department") + "\n"
                );
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    void updateData() {
        try {
            Connection con = DBConnection.connect();
            String sql = "UPDATE students SET department=? WHERE name=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, dept.getText());
            ps.setString(2, name.getText());
            ps.execute();
            output.setText("Updated Successfully");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    void deleteData() {
        try {
            Connection con = DBConnection.connect();
            String sql = "DELETE FROM students WHERE name=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name.getText());
            ps.execute();
            output.setText("Deleted Successfully");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
