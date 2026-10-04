public class PayrollDemo {
    public static void main(String[] args) {
        Employee[] staff = new Employee[] {
            new FullTime("A", "FT001", 50000),
            new PartTime("B", "PT002", 80, 250),
            new Intern("C", "IN003", 15000),
            new PartTime("D", "PT004", 60, 300),
            new FullTime("E", "FT005", 60000)
        };

        double total = 0.0;
        System.out.println("Payroll report:");
        for (Employee e : staff) {
            double sal = e.monthlySalary();
            total += sal;
            String note = (e instanceof Intern) ? " [Intern]" : "";
            note = (e instanceof PartTime) ? " [Part-time]" : note;
            System.out.printf("%s: Rs. %.2f%s%n", e, sal, note);
        }

        System.out.printf("Total monthly payroll: Rs. %.2f%n", total);
    }
}
