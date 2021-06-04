package client.gui;

import client.controllers.MedPersController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Drug;
import models.Order;
import models.User;
import models.dtos.OrderDTO;

import java.util.Date;
import java.util.List;

public class MedPersPage {
    private MedPersController ctrl;
    private User currentUser;
    ObservableList<Drug> tableModelDrugs = FXCollections.observableArrayList();
    ObservableList<OrderDTO> tableModelOrders = FXCollections.observableArrayList();
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

    // Orders

    @FXML
    TableView<OrderDTO> tbOrders;
    @FXML
    TableColumn<OrderDTO, Integer> tcOrdersId;
    @FXML
    TableColumn<OrderDTO, String> tcOrdersDepartment;
    @FXML
    TableColumn<OrderDTO, Date> tcOrdersCreated;
    @FXML
    TableColumn<OrderDTO, Date> tcOrdersDelivered;
    @FXML
    TableColumn<OrderDTO, Integer> tcOrdersPrice;
    @FXML
    TableColumn<OrderDTO, String> tcOrdersStatus;

    @FXML
    public void initialize()
    {
        tcId.setCellValueFactory(new PropertyValueFactory<Drug, Integer>("id"));
        tcName.setCellValueFactory(new PropertyValueFactory<Drug, String>("name"));
        tcCategory.setCellValueFactory(new PropertyValueFactory<Drug, String>("drugCategory"));
        tcPrice.setCellValueFactory(new PropertyValueFactory<Drug, Integer>("price"));
        tcDetails.setCellValueFactory(new PropertyValueFactory<Drug, String>("details"));

        tcOrdersId.setCellValueFactory(new PropertyValueFactory<OrderDTO, Integer>("orderId"));
        tcOrdersDepartment.setCellValueFactory(new PropertyValueFactory<OrderDTO, String>("department"));
        tcOrdersCreated.setCellValueFactory(new PropertyValueFactory<OrderDTO, Date>("createdAt"));
        tcOrdersDelivered.setCellValueFactory(new PropertyValueFactory<OrderDTO, Date>("deliveredAt"));
        tcOrdersPrice.setCellValueFactory(new PropertyValueFactory<OrderDTO, Integer>("totalPrice"));
        tcOrdersStatus.setCellValueFactory(new PropertyValueFactory<OrderDTO, String>("status"));

        tbDrugs.setItems(tableModelDrugs);
        tbOrders.setItems(tableModelOrders);

    }

    public void initModel()
    {
        List<Drug> drugs = ctrl.GetAllDrugs();
        tableModelDrugs.setAll(drugs);

        List<OrderDTO> orders = ctrl.GetOrdersForUser();
        tableModelOrders.setAll(orders);
    }

    public MedPersPage() {
    }

    public MedPersPage(MedPersController ctrl) {
        this.ctrl = ctrl;
    }

    public MedPersController getCtrl() {
        return ctrl;
    }

    public void setCtrl(MedPersController ctrl) {
        this.ctrl = ctrl;
        initModel();
    }
}
