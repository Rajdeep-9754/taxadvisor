package controller;

import dao.TaxDAO;
import model.UserModel;
import service.TaxService;
import util.PaymentGateway;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainController extends JFrame {

    private JTextField nameField, ageField, panField, emailField, mobileField;
    private JTextField incomeField, basicSalaryField, npsField, pfField, healthInsuranceField, professionalTaxField, otherIncomeField;
    private JButton submitButton, searchButton, deleteButton;

    public MainController() {
        setTitle("Smart Tax Advisor");
        setSize(500, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(16, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Full Name:"));
        nameField = new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("Age:"));
        ageField = new JTextField();
        panel.add(ageField);

        panel.add(new JLabel("PAN:"));
        panField = new JTextField();
        panel.add(panField);

        panel.add(new JLabel("Email:"));
        emailField = new JTextField();
        panel.add(emailField);

        panel.add(new JLabel("Mobile:"));
        mobileField = new JTextField();
        panel.add(mobileField);

        panel.add(new JLabel("Annual CTC:"));
        incomeField = new JTextField();
        panel.add(incomeField);

        panel.add(new JLabel("Basic Salary:"));
        basicSalaryField = new JTextField();
        panel.add(basicSalaryField);

        panel.add(new JLabel("NPS Contribution:"));
        npsField = new JTextField();
        panel.add(npsField);

        panel.add(new JLabel("PF Contribution:"));
        pfField = new JTextField();
        panel.add(pfField);

        panel.add(new JLabel("Health Insurance Premium:"));
        healthInsuranceField = new JTextField();
        panel.add(healthInsuranceField);

        panel.add(new JLabel("Professional Tax Paid:"));
        professionalTaxField = new JTextField();
        panel.add(professionalTaxField);

        panel.add(new JLabel("Other Income:"));
        otherIncomeField = new JTextField();
        panel.add(otherIncomeField);

        submitButton = new JButton("Submit");
        panel.add(submitButton);
        panel.add(new JLabel("")); // Placeholder for layout

        searchButton = new JButton("Search Users");
        deleteButton = new JButton("Delete User by ID");

        panel.add(searchButton);
        panel.add(deleteButton);

        add(panel);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSubmit();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSearch();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleDelete();
            }
        });
    }

    private void handleSubmit() {
        try {
            UserModel user = new UserModel();

            String name = nameField.getText();
            if (!name.matches("^[A-Za-z\\s]{3,}$")) {
                throw new Exception("Invalid name. Only letters and spaces allowed, min 3 characters.");
            }
            user.setName(name);

            int age = Integer.parseInt(ageField.getText());
            if (age < 18 || age > 100) {
                throw new Exception("Age must be between 18 and 100.");
            }
            user.setAge(age);

            String pan = panField.getText();
            if (!pan.matches("^[A-Z]{5}[0-9]{4}[A-Z]$")) {
                throw new Exception("Invalid PAN format.");
            }
            user.setPan(pan);

            String email = emailField.getText();
            if (!email.matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$")) {
                throw new Exception("Invalid email format.");
            }
            user.setEmail(email);

            String mobile = mobileField.getText();
            if (!mobile.matches("^[6-9]\\d{9}$")) {
                throw new Exception("Invalid mobile number.");
            }
            user.setMobile(mobile);

            double income = Double.parseDouble(incomeField.getText());
            if (income < 0) {
                throw new Exception("Income must be non-negative.");
            }
            user.setAnnualIncome(income);

            double basic = Double.parseDouble(basicSalaryField.getText());
            if (basic < 0) {
                throw new Exception("Basic salary must be non-negative.");
            }
            user.setBasicSalary(basic);

            try {
                user.setNpsContribution(Double.parseDouble(npsField.getText()));
            } catch (NumberFormatException e) {
                user.setNpsContribution(0);
            }

            try {
                user.setPfContribution(Double.parseDouble(pfField.getText()));
            } catch (NumberFormatException e) {
                user.setPfContribution(0);
            }

            try {
                user.setHealthInsurance(Double.parseDouble(healthInsuranceField.getText()));
            } catch (NumberFormatException e) {
                user.setHealthInsurance(0);
            }

            try {
                user.setProfessionalTax(Double.parseDouble(professionalTaxField.getText()));
            } catch (NumberFormatException e) {
                user.setProfessionalTax(0);
            }

            try {
                user.setOtherIncome(Double.parseDouble(otherIncomeField.getText()));
            } catch (NumberFormatException e) {
                user.setOtherIncome(0);
            }

            TaxService taxService = new TaxService();
            double taxAmount = taxService.calculateTax(user);
            user.setTaxPayable(taxAmount);

            boolean saved = TaxDAO.saveUser(user);

            if (saved) {
                JOptionPane.showMessageDialog(this,
                        "User data saved successfully.\nTax to be paid: ₹" + taxAmount,
                        "Success", JOptionPane.INFORMATION_MESSAGE);

                PaymentGateway payment = new PaymentGateway();
                payment.processPayment(taxAmount);

            } else {
                JOptionPane.showMessageDialog(this, "Failed to save user data.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values where required.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Validation Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleSearch() {
        String keyword = JOptionPane.showInputDialog(this, "Enter name, email, or mobile to search:");
        if (keyword == null || keyword.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Search keyword cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Implement search logic here if desired
        // For now, simple alert
        JOptionPane.showMessageDialog(this, "Search functionality pending implementation.", "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    private void handleDelete() {
        String idStr = JOptionPane.showInputDialog(this, "Enter User ID to delete:");
        if (idStr == null || idStr.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "User ID cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Implement delete logic here if desired
        // For now, simple alert
        JOptionPane.showMessageDialog(this, "Delete functionality pending implementation.", "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainController().setVisible(true);
        });
    }
}
