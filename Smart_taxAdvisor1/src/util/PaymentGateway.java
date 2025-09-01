package util;

import javax.swing.*;

public class PaymentGateway {

    public void processPayment(double taxAmount) {
        String[] options = {"UPI", "Net Banking", "Debit/Credit Card"};
        
        String message = String.format("Amount to Pay: ₹%.2f%nChoose Payment Method:", taxAmount);
        
        int choice = JOptionPane.showOptionDialog(null, message, "💳 Payment Gateway",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, options, options[0]);
        
        if (choice == JOptionPane.CLOSED_OPTION) {
            JOptionPane.showMessageDialog(null, "Payment Cancelled.", "Payment Status", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String paymentMethod = options[choice];
        JOptionPane.showMessageDialog(null, "Payment Successful via " + paymentMethod + ".", "Payment Confirmation", JOptionPane.INFORMATION_MESSAGE);

        JOptionPane.showMessageDialog(null, "Thank you for paying your taxes with Smart Tax Advisor 2526.", "Thank You", JOptionPane.INFORMATION_MESSAGE);
    }
}
