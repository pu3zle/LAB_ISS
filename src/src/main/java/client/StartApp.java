package client;

import client.controllers.LoginController;
import client.gui.LoginPage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import persistence.database.DrugsHibernation;
import persistence.database.OrderItemsHibernation;
import persistence.database.OrdersHibernation;
import persistence.database.UsersHibernation;

public class StartApp extends Application {
    private static String defaultServer = "localhost";
    private static SessionFactory sessionFactory = null;

    private static void initializeSession() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try{
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e)
        {
            System.err.println("Exceptie " + e);
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    static void closeSession(){
            sessionFactory.close();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        try{
            initializeSession();
            UsersHibernation usersHibernation = new UsersHibernation(sessionFactory);
            DrugsHibernation drugsHibernation = new DrugsHibernation(sessionFactory);
            OrderItemsHibernation orderItemsHibernation = new OrderItemsHibernation(sessionFactory);
            OrdersHibernation ordersHibernation = new OrdersHibernation(sessionFactory);
            LoginController ctrl = new LoginController(usersHibernation, drugsHibernation, orderItemsHibernation, ordersHibernation);

            FXMLLoader authenticateLoader = new FXMLLoader(getClass().getClassLoader().getResource("loginPage.fxml"));

            AnchorPane authenticateLayout = authenticateLoader.load();
            primaryStage.setScene(new Scene(authenticateLayout));

            LoginPage authenticatePageController = authenticateLoader.getController();
            authenticatePageController.setController(ctrl);

            primaryStage.setWidth(800);
            primaryStage.sizeToScene();
            primaryStage.show();

        } catch (Exception e) {
            System.err.println("Initialization exception:" + e);
            e.printStackTrace();
        }
        //closeSession();
    }
}