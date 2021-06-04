package client.gui;

import client.controllers.PharmacistController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Drug;
import models.dtos.OrderDTO;

import java.util.Date;
import java.util.List;

public class PharmacistPage {
    private PharmacistController ctrl;
    ObservableList<OrderDTO> tableModelOrders = FXCollections.observableArrayList();

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
        tcOrdersId.setCellValueFactory(new PropertyValueFactory<OrderDTO, Integer>("orderId"));
        tcOrdersDepartment.setCellValueFactory(new PropertyValueFactory<OrderDTO, String>("department"));
        tcOrdersCreated.setCellValueFactory(new PropertyValueFactory<OrderDTO, Date>("createdAt"));
        tcOrdersDelivered.setCellValueFactory(new PropertyValueFactory<OrderDTO, Date>("deliveredAt"));
        tcOrdersPrice.setCellValueFactory(new PropertyValueFactory<OrderDTO, Integer>("totalPrice"));
        tcOrdersStatus.setCellValueFactory(new PropertyValueFactory<OrderDTO, String>("status"));

        tbOrders.setItems(tableModelOrders);
    }

    public void initModel()
    {
        List<OrderDTO> orders = ctrl.GetAllOrders();
        tableModelOrders.setAll(orders);
    }

    public PharmacistPage() {
    }

    public PharmacistPage(PharmacistController ctrl) {
        this.ctrl = ctrl;
    }

    public PharmacistController getCtrl() {
        return ctrl;
    }

    public void setCtrl(PharmacistController ctrl) {
        this.ctrl = ctrl;
        initModel();
    }
}
