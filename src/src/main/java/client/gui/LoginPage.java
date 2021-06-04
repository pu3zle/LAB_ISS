package client.gui;

import client.controllers.AdminController;
import client.controllers.LoginController;
import client.controllers.MedPersController;
import client.controllers.PharmacistController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.User;

import java.io.IOException;

public class LoginPage {
    LoginController ctrl;

    public LoginPage(LoginController ctrl) {
        this.ctrl = ctrl;
    }
    public LoginPage(){
    }
    @FXML
    TextField txtUsername;

    @FXML
    PasswordField txtPassword;

    @FXML
    Button btnLogin;

    @FXML
    public void initialize()
    {
    }
    public void setController(LoginController ctrl)
    {
        this.ctrl = ctrl;
    }

    public FXMLLoader createNewScene(String title, String view)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(view));
            AnchorPane root = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle(title);
            dialogStage.initModality(Modality.WINDOW_MODAL);

            Scene scene = new Scene(root);
            dialogStage.setScene(scene);
            dialogStage.show();

            return loader;

        } catch (IOException e) {
            MessageAlert.showErrorMessage(null, e.getMessage());
        }
        return null;
    }

    public void handleLogin(ActionEvent event)
    {
        try
        {
            User u = ctrl.Login(txtUsername.getText(), txtPassword.getText());
            if(u == null)
            {
                throw new Exception("Invalid user");
            }

            switch (u.getType())
            {
                case MEDICALPERSONNEL:
                    showMedPersPage();
                    break;
                case PHARMACIST:
                    showPharmacistPage();
                    break;
                case ADMIN:
                    showAdminPage();
                    break;
                default:
                    throw new Exception("Internal Error");
            }
        }
        catch (Exception ex)
        {
            MessageAlert.showErrorMessage(null, ex.getMessage());
        }
    }

    private void showAdminPage() {
        FXMLLoader adminPageLoader = createNewScene("Admin Page", "/admPage.fxml");
        AdminPage adminPage = adminPageLoader.getController();
        adminPage.setCtrl(ctrl.createAdminController());
    }

    private void showPharmacistPage() {
        FXMLLoader pharmacistPageLoader = createNewScene("Pharmacist Page", "/pharmacistPage.fxml");
        PharmacistPage pharmacistPage = pharmacistPageLoader.getController();
        pharmacistPage.setCtrl(ctrl.createPharmacistController());
    }

    private void showMedPersPage() {
        FXMLLoader medPersPageLoader = createNewScene("Medical Personnel Page", "/medicalPersonnel.fxml");
        MedPersPage medPersPage = medPersPageLoader.getController();
        medPersPage.setCtrl(ctrl.createMedPersController());
    }

}
