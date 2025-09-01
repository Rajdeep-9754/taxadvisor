package service;

import model.UserModel;

public class TaxService {

    public double calculateTax(UserModel user) {
        double taxableIncome = user.getAnnualIncome();

        // Only limited deductions under new tax regime
        double totalDeductions = user.getNpsContribution() + user.getPfContribution();
        taxableIncome -= totalDeductions;

        if (taxableIncome < 0) taxableIncome = 0;

        return computeTaxByNewRegime(taxableIncome);
    }

    private double computeTaxByNewRegime(double income) {
        double tax = 0;

        // Slab-wise calculation (progressive)
        if (income > 1500000) {
            tax += (income - 1500000) * 0.30;
            income = 1500000;
        }
        if (income > 1200000) {
            tax += (income - 1200000) * 0.20;
            income = 1200000;
        }
        if (income > 900000) {
            tax += (income - 900000) * 0.15;
            income = 900000;
        }
        if (income > 600000) {
            tax += (income - 600000) * 0.10;
            income = 600000;
        }
        if (income > 300000) {
            tax += (income - 300000) * 0.05;
            income = 300000;
        }

        // 4% health & education cess
        tax += tax * 0.04;

        return tax;
    }
}
