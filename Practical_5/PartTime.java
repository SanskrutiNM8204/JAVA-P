public class PartTime extends Employee {
    private final double hours;
    private final double rate;

    public PartTime(String name, String id, double hours, double ratePerHour) {
        super(name, id);
        this.hours = hours;
        this.rate = ratePerHour;
    }

    @Override
    public double monthlySalary() {
        return hours * rate;
    }
}
