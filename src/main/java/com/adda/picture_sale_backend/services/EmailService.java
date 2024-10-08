package com.adda.picture_sale_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Environment env;

    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(env.getProperty("spring.mail.username"));
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }

    public void sendEmailRegisterConfirmCode(String email, String fullName, String path, String code)
    {
        String subject = "Register new account from AmbassadorDayCruise ";
        String body ="Dear " + fullName+ ",\n" +
                "\n" +
                "We are thrilled to welcome you to our AmbassadorCruise Booking System! Thank you for choosing us to be your trusted platform for booking your next unforgettable adventure. We can't wait to help you create amazing memories.\n" +
                "\n" +
                "As a registered user, you now have access to a wide range of exciting travel tours, exclusive deals, and personalized recommendations tailored to your preferences. Whether you're looking for a relaxing beach getaway, an adventurous hiking expedition, or a cultural city tour, we have something for everyone."
        + "\n" +
                "Please confirm code by click link below: http://ambassadordaycruise.com/confirm-code/" + path

        + "\n" +
                "Your register code is: " + code;
        sendEmail(email, subject, body);
    }

    public void sendEmailForgotPassword(String email, String fullName, String code)
    {
        String subject = "Password Reset Request for Your AmbassadorCruise Account";
        String body ="Dear " + fullName+ ",\n" +
                "\n" +
                "We have received a request to reset your password for your Travel Tour account." +
                " We understand that forgetting passwords can be frustrating, but don't worry!" +
                " We are here to help you regain access to your account.\n" +
                "\n" +
                "Please note that the password reset link will expire within 24 hours for security purposes. " +
                "If you don't receive the email within a few minutes, please check your spam or junk folder." +
                " If you still encounter any issues, please contact our customer support team for further assistance."
                + "\n" +
                " Here are code for update your new password: " + code;
        sendEmail(email, subject, body);
    }


    public void sendEmailChangePasswordSuccess(String email, String fullName)
    {
        String subject = "Reset Password Successful";

        String body ="Dear " + fullName+ ",\n" +
                "\n" +
                "We hope this email finds you well. We are writing to inform you that your password has been successfully changed for your Travel Tour System website account. The security of your account is of utmost importance to us, and we want to ensure that your information remains safe and protected." +
                 "\n" +
                "If you did not initiate this password change, please contact our support team immediately at [support email/phone number]. We will investigate the matter further to ensure the security of your account. " +
                "To enhance your account security, we recommend following these best practices:\n" +
                "\n." +
                "1. Create a strong password: Use a combination of uppercase and lowercase letters, numbers, and special characters. Avoid using easily guessable information such as your name or birthdate."
                + "\n" +
                "2. Regularly update your password: Changing your password periodically reduces the risk of unauthorized access to your account."

                 + "\n" +
                        "Thank you for choosing our Travel Tour System website. We remain committed to providing you with a secure and enjoyable experience.\n" +
                        "\n";

        sendEmail(email, subject, body);



    }


    public void sendEmailRegisterSuccess(String email,  String fullName){


        String subject = "Register new  account successful ";

        String body ="Dear " + fullName+ ",\n" +
                "\n" +
                "We are thrilled to welcome you to our AmbassadorCruise Booking System! Thank you for choosing us to be your trusted platform for booking your next unforgettable adventure. We can't wait to help you create amazing memories.\n" +
                "\n" +
                "As a registered user, you now have access to a wide range of exciting travel tours, exclusive deals, and personalized recommendations tailored to your preferences. Whether you're looking for a relaxing beach getaway, an adventurous hiking expedition, or a cultural city tour, we have something for everyone.";


        sendEmail(email, subject, body);
    }



    public void sendBookingConfirmation(String email, String fullName, String bookingCode, String cruiseType){

    String subject = "Ambassador Cruise Reservation has been confirmed!\n";

        String bodyDayCruise ="Dear " + fullName+ ",\n" +
                "We are delighted to inform you that your reservation for Ambassador Cruise has been confirmed.\n"+
                "Your itinerary is now secured for the following:\n"+
                "[Booking Code]:"+
                bookingCode +"\n"+
                "IMPORTANT NOTES\n" +
                "CHECK-IN INSTRUCTIONS\n"+
                "1. Ambassador Day Cruise:\n"+
                "    Boarding Time: 08:30 AM\n"+
                "    Check-out Time: 04:30 PM\n"+
                "To ensure a seamless check-in experience and avoid any late check-in charges, please make sure to arrive at the Ambassador Check-in Lounge 30 minutes before Boarding time.\n" +
                "Kindly note that advanced registration is mandatory. Please provide all passenger information (including full name, gender, date of birth, and nationality) at least 24 hours prior to your departure. Failure to supply this information may result in denial of boarding or a late fee of USD 30 per person.\n"+
                "SPECIAL REQUEST\n"+
                "If you have any special dietary requirements or food allergies, please inform us before your cruise date.\n"+
                "Thank you for choosing to sail with Ambassador Cruises to Halong Bay. We are looking forward to welcoming you aboard and making your trip a truly extraordinary one.\n"+
                "Sincerely,\n"+
                "Ambassador Cruise\n"+
                "\nThis email is intended for [email address], because you have made a reservation through Ambassador Cruise website.\n";

        String bodyDinnerCruise ="Dear " + fullName+ ",\n" +
                "We are delighted to inform you that your reservation for Ambassador Cruise has been confirmed.\n"+
                "Your itinerary is now secured for the following:\n"+
                "[Booking Code]:"+
                bookingCode +"\n"+
                "IMPORTANT NOTES\n" +
                "CHECK-IN INSTRUCTIONS\n"+
                "1.  Ambassador Dinner Cruise:\n"+
                "    Boarding Time: 05:30 PM\n"+
                "    Check-out Time: 09:00 PM\n"+
                "To ensure a seamless check-in experience and avoid any late check-in charges, please make sure to arrive at the Ambassador Check-in Lounge 30 minutes before Boarding time.\n" +
                "Kindly note that advanced registration is mandatory. Please provide all passenger information (including full name, gender, date of birth, and nationality) at least 24 hours prior to your departure. Failure to supply this information may result in denial of boarding or a late fee of USD 30 per person.\n"+
                "SPECIAL REQUEST\n"+
                "If you have any special dietary requirements or food allergies, please inform us before your cruise date.\n"+
                "Thank you for choosing to sail with Ambassador Cruises to Halong Bay. We are looking forward to welcoming you aboard and making your trip a truly extraordinary one.\n"+
                "Sincerely,\n"+
                "Ambassador Cruise\n"+
                "\nThis email is intended for [email address], because you have made a reservation through Ambassador Cruise website.\n";


        if(cruiseType.equals("Day Cruise")) {
            sendEmail(email, subject, bodyDayCruise);
        }else if(cruiseType.equals("Dinner Cruise")){
            sendEmail(email, subject, bodyDinnerCruise);
        }

    }
}