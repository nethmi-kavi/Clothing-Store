package controller;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

public class ForgetPassword {

    @FXML
    private TextField txtOtp;

    @FXML
    private TextField txtUser;

    // Store OTP to verify later
    private String generatedOtp;

    @FXML
    void btnSend(ActionEvent event) {
        String email = txtUser.getText();

        if (email == null || email.isEmpty()) {
            showAlert("Error", "Please enter an email address.");
            return;
        }

        // Generate OTP
        generatedOtp = generateOTP();

        // Send OTP
        sendOTP(email, generatedOtp);
    }

    @FXML
    void btnVerify(ActionEvent event) throws IOException {
        String enteredOtp = txtOtp.getText();

        if (enteredOtp.equals(generatedOtp)) {
            showAlert("Success", "OTP verified successfully!");


                Stage stage = new Stage();
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/password_change.fxml"))));
                stage.show();
        } else {
            showAlert("Error", "Invalid OTP. Please try again.");
        }
    }

    public static void sendOTP(String toEmail, String otpCode) {
        final String fromEmail = "kavindyarathnayake456@gmail.com"; // Replace with your email
        final String password = "mvza cque ntgs moli";     // Use app-specific password

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Your OTP Code");
            message.setText("Your OTP code is: " + otpCode);

            Transport.send(message);
            System.out.println("OTP sent to " + toEmail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private String generateOTP() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000); // 6-digit number
        return String.valueOf(otp);
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    @FXML
    void btnCancel(ActionEvent event) {
        txtOtp.clear();
        txtUser.clear();

    }
}


