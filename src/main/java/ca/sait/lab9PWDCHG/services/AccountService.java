package ca.sait.lab9PWDCHG.services;

import ca.sait.lab9PWDCHG.dataaccess.UserDB;
import ca.sait.lab9PWDCHG.models.User;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountService {
    
    public User login(String email, String password, String path) {
        UserDB userDB = new UserDB();
        
        try {
            User user = userDB.get(email);
            if (password.equals(user.getPassword())) {
                return user;
            }
            // substitute the upper 3 lines as below
            /*
            if (password.equals(user.getPassword())) {
                Logger.getLogger(AccountService.class.getName()).log(Level.INFO, "Successful login by {0}", email);
                
                String to = user.getEmail();
                String subject = "Notes App Login";
                String template = path + "/templates/login.html";
                
                HashMap<String, String> tags = new HashMap<>();
                tags.put("firstname", user.getFirstName());
                tags.put("lastname", user.getLastName());
                tags.put("date", (new java.util.Date()).toString());
                
                GmailService.sendMail(to, subject, template, tags);

                return user;
            }
            */
        } catch (Exception e) {
        }
        
        return null;
    }
    
    public void resetPassword(String email, String path, String url) {
        String uuid = UUID.randomUUID().toString();
        UserDB userDB = new UserDB();
        String link = url + "?uuid=" + uuid;
        try {
            User user = userDB.get(email);
            if (user != null) {
                Logger.getLogger(AccountService.class.getName()).log(Level.INFO, "Reset password by {0}", email);
                
                String to = user.getEmail();
                //String subject = "Notes App Reset Password";
                String subject = "Reset Password";
                String template = path + "/templates/resetpassword.html";
                
                HashMap<String, String> tags = new HashMap<>();
                tags.put("firstname", user.getFirstName());
                tags.put("lastname", user.getLastName());
                tags.put("date", (new java.util.Date()).toString());
                tags.put("link", link);
                
                GmailService.sendMail(to, subject, template, tags);
            }
            else {
                throw new Exception("No such user");
            }
        } catch (Exception e) {
            Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, "Unsuccesfull reset request by " + email, e);
        }
    }
    
    // Adjust the doGet() in ResetPasswordServlet to serve resetNewPassword.jsp if there is a UUID parameter.
    
    public boolean changePassword(String uuid, String password) {
       UserDB userDB = new UserDB();
        try {
            User user = userDB.getByUUID(uuid);
            user.setPassword(password);
            user.setResetPasswordUuid(null);
            userDB.update(user);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean updatePassword(String email, String password, String uuid) {
         UserDB userDB = new UserDB();
        try {
            User user = userDB.get(email);
            if (user != null && user.getResetPasswordUuid().equals(uuid)) {
                user.setPassword(password);
                user.setResetPasswordUuid(null);
                userDB.update(user);
                Logger.getLogger(AccountService.class.getName()).log(Level.INFO, "Successful password change by {0}", email);
                return true;
            } else {
                throw new Exception("User not found or UUID does not match");
            } 
        }catch (Exception e){
                    Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, "Unuccessful password change by " + email, e);
                    return false;  
        }
    }
}
