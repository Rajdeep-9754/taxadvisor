package model;

public class UserModel {
    private String name;
    private int age;
    private String aadhaar;
    private String pan;
    private String email;
    private String mobile;
    private double annualIncome;
    private double basicSalary;
    private double npsContribution;
    private double pfContribution;
    private double healthInsurance;
    private double professionalTax;
    private double otherIncome;

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getAadhaar() { return aadhaar; }
    public void setAadhaar(String aadhaar) { this.aadhaar = aadhaar; }

    public String getPan() { return pan; }
    public void setPan(String pan) { this.pan = pan; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }

    public double getAnnualIncome() { return annualIncome; }
    public void setAnnualIncome(double annualIncome) { this.annualIncome = annualIncome; }

    public double getBasicSalary() { return basicSalary; }
    public void setBasicSalary(double basicSalary) { this.basicSalary = basicSalary; }

    public double getNpsContribution() { return npsContribution; }
    public void setNpsContribution(double npsContribution) { this.npsContribution = npsContribution; }

    public double getPfContribution() { return pfContribution; }
    public void setPfContribution(double pfContribution) { this.pfContribution = pfContribution; }

    public double getHealthInsurance() { return healthInsurance; }
    public void setHealthInsurance(double healthInsurance) { this.healthInsurance = healthInsurance; }

    public double getProfessionalTax() { return professionalTax; }
    public void setProfessionalTax(double professionalTax) { this.professionalTax = professionalTax; }

    public double getOtherIncome() { return otherIncome; }
    public void setOtherIncome(double otherIncome) { this.otherIncome = otherIncome; }
	public void setTaxPayable(double taxAmount) {
		// TODO Auto-generated method stub
		
	}
}
