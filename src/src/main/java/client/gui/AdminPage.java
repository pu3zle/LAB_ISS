package client.gui;

import client.controllers.AdminController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Drug;

import java.util.List;

public class AdminPage {
    private AdminController ctrl;
    ObservableList<Drug> tableModel = FXCollections.observableArrayList();

    @FXML
    Button btnAdd;
    @FXML
    Button btnRemove;
    @FXML
    Button btnUpdate;
    @FXML
    TextField txtId;
    @FXML
    TextField txtName;
    @FXML
    TextField txtPrice;
    @FXML
    TextField txtCategory;
    @FXML
    TextArea txtDetails;
    @FXML
    TextField txtSearch;
    @FXML
    TableView<Drug> tbDrugs;
    @FXML
    TableColumn<Drug, Integer> tcId;
    @FXML
    TableColumn<Drug, String> tcName;
    @FXML
    TableColumn<Drug, String> tcCategory;
    @FXML
    TableColumn<Drug, Integer> tcPrice;
    @FXML
    TableColumn<Drug, String> tcDetails;

    @FXML
    public void initialize()
    {
        tcId.setCellValueFactory(new PropertyValueFactory<Drug, Integer>("id"));
        tcName.setCellValueFactory(new PropertyValueFactory<Drug, String>("name"));
        tcCategory.setCellValueFactory(new PropertyValueFactory<Drug, String>("drugCategory"));
        tcPrice.setCellValueFactory(new PropertyValueFactory<Drug, Integer>("price"));
        tcDetails.setCellValueFactory(new PropertyValueFactory<Drug, String>("details"));

        tbDrugs.setItems(tableModel);
    }


    public void initModel()
    {
        List<Drug> drugs = ctrl.GetAllDrugs();
        tableModel.setAll(drugs);
    }

    public AdminPage() {
    }

    public AdminPage(AdminController ctrl) {
        this.ctrl = ctrl;
    }

    public AdminController getCtrl() {
        return ctrl;
    }

    public void setCtrl(AdminController ctrl) {
        this.ctrl = ctrl;
        initModel();
    }

    public void handleAdd(ActionEvent event)
    {
        if(!txtName.getText().isEmpty() &&
           !txtCategory.getText().isEmpty() &&
           !txtPrice.getText().isEmpty() &&
           !txtDetails.getText().isEmpty())
        {
            String name = txtName.getText();
            String category = txtCategory.getText();
            Integer price = Integer.parseInt(txtPrice.getText());
            String details = txtDetails.getText();
            Drug drug = new Drug(name, price, category, details);
            try {
                Drug addedDrug = ctrl.AddDrug(drug);
                initModel();
                ClearAllFields();
            }
            catch (RuntimeException ex)
            {
                MessageAlert.showErrorMessage(null, ex.getMessage());
            }
        }
    }
    public void handleRemove(ActionEvent event)
    {
        Drug drug = tbDrugs.getSelectionModel().getSelectedItem();
        if(drug != null)
        {
            ctrl.DeleteDrug(drug.getId());
            initModel();
        }
    }
    public void handleSearch(ActionEvent event)
    {
        if(txtSearch.getText().isEmpty()){
            initModel();
            return;
        }
        String startsWith = txtSearch.getText();
        List<Drug> drugs = ctrl.GetFilteredDrugs(startsWith);
        tableModel.setAll(drugs);

    }

    private void ClearAllFields()
    {
        txtId.setText("");
        txtName.setText("");
        txtPrice.setText("");
        txtDetails.setText("");
        txtCategory.setText("");
        txtSearch.setText("");
    }
}
