package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import main.Driver;

public class LotInfoController {
    @FXML
    TextField

    public void switchToMainView() throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(Driver.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Driver.mainStage.setScene(scene);
    }

    public void switchToBiddersView() throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(Driver.class.getResource("bidders-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Driver.mainStage.setScene(scene);
    }

    public void showEditFields() {
        if (editButton.isSelected()) {
            editFields.setVisible(true);
            nameTextfield.setText(currentBidder.getName());
            phoneTextField.setText(currentBidder.getPhone());
            addressTextField.setText(currentBidder.getAddress());
            emailTextField.setText(currentBidder.getEmail());

        } else editFields.setVisible(false);
    }

    public void editBidder(){
        currentBidder.setName(nameTextfield.getText());
        currentBidder.setPhone(phoneTextField.getText());
        currentBidder.setAddress(addressTextField.getText());
        currentBidder.setEmail(emailTextField.getText());

        nameLabel.setText(currentBidder.getName());
        phoneLabel.setText(currentBidder.getPhone());
        addressLabel.setText(currentBidder.getAddress());
        emailLabel.setText(currentBidder.getEmail());
    }


    public void save(){
        try{
            Driver.save();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void load(){
        try {
            Driver.load();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void clear(){
        Driver.auctionAPI.clear();
    }
}
