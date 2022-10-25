/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Feature;
import model.Role;

/**
 *
 * @author sonnt
 */
public class AccountDBContext extends DBContext<Account> {

    public Account get(String username, String password) {
        try {
            String sql = "SELECT a.Username,a.DisplayName\n"
                    + "       ,r.RoleID,r.RoleName\n"
                    + "       ,f.FeatureID,f.FeatureName,f.URL\n"
                    + "				FROM Account a \n"
                    + "				LEFT JOIN Account_Role ra ON a.Username = ra.Username\n"
                    + "				LEFT JOIN [Role] r ON r.RoleID = ra.RoleID\n"
                    + "				LEFT JOIN Role_Feature rf ON rf.RoleID = r.RoleID\n"
                    + "				LEFT JOIN Feature f ON f.FeatureID = rf.FeatureID\n"
                    + "			WHERE a.Username = ? AND a.[Password] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            Account account = null;
            Role currentRole = new Role();
            currentRole.setRoleID(-1);
            while (rs.next()) {
                if (account == null) {
                    account = new Account();
                    account.setUsername(rs.getString("Username"));
                    account.setDisplayname(rs.getString("DisplayName"));
                }
                int rid = rs.getInt("RoleID");
                if (rid != 0) {
                    if (rid != currentRole.getRoleID()) {
                        currentRole = new Role();
                        currentRole.setRoleID(rs.getInt("RoleID"));
                        currentRole.setRoleName(rs.getString("RoleName"));
                        account.getRoles().add(currentRole);
                    }
                }

                int fid = rs.getInt("FeatureID");
                if (fid != 0) {
                    Feature f = new Feature();
                    f.setFeatureID(rs.getInt("FeatureID"));
                    f.setFeatureName(rs.getString("FeatureName"));
                    f.setUrl(rs.getString("URL"));
                    currentRole.getFeatures().add(f);
                }
            }
            return account;
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    @Override
    public void insert(Account model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Account model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Account model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Account get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Account> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Account get(String code) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
