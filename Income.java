public class Income {
    private double amount;
    private String source;

    public Income(double amount, String source) {
        this.amount = amount;
        this.source = source;
    }

    public double getAmount() {
        return amount;
    }

    public String getSource() {
        return source;
    }
}
