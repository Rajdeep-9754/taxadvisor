package dao;

import db.DBConnection;
import model.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TaxDAO {

    public static boolean saveUser(UserModel user) {
        String sql = "INSERT INTO users (name, age, pan, email, mobile, annual_income, basic_salary, " +
                     "nps_contribution, pf_contribution, health_insurance, professional_tax, other_income) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getName());
            pstmt.setInt(2, user.getAge());
            pstmt.setString(3, user.getPan());
            pstmt.setString(4, user.getEmail());
            pstmt.setString(5, user.getMobile());
            pstmt.setDouble(6, user.getAnnualIncome());
            pstmt.setDouble(7, user.getBasicSalary());

            if (user.getNpsContribution() != 0) {
                pstmt.setDouble(8, user.getNpsContribution());
            } else {
                pstmt.setNull(8, java.sql.Types.DOUBLE);
            }

            if (user.getPfContribution() != 0) {
                pstmt.setDouble(9, user.getPfContribution());
            } else {
                pstmt.setNull(9, java.sql.Types.DOUBLE);
            }

            if (user.getHealthInsurance() != 0) {
                pstmt.setDouble(10, user.getHealthInsurance());
            } else {
                pstmt.setNull(10, java.sql.Types.DOUBLE);
            }

            if (user.getProfessionalTax() != 0) {
                pstmt.setDouble(11, user.getProfessionalTax());
            } else {
                pstmt.setNull(11, java.sql.Types.DOUBLE);
            }

            if (user.getOtherIncome() != 0) {
                pstmt.setDouble(12, user.getOtherIncome());
            } else {
                pstmt.setNull(12, java.sql.Types.DOUBLE);
            }

            int rows = pstmt.executeUpdate();
            System.out.println("Rows inserted: " + rows);
            return rows > 0;

        } catch (SQLException e) {
            System.err.println("Error saving user data:");
            e.printStackTrace();
            return false;
        }
    }

    // Other CRUD methods (like search, delete, fetch) can be added here as per your earlier requirements
}
