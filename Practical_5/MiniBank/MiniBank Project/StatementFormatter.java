public class StatementFormatter {
    public static String buildStatement(Account account) {
        StringBuilder sb = new StringBuilder();
        sb.append("Account Statement\n");
        sb.append("-----------------\n");
        sb.append("Account Number: ").append(account.getAccountNumber()).append('\n');
        sb.append("Owner: ").append(account.getOwnerName()).append('\n');
        sb.append("Active: ").append(account.isActive() ? "Yes" : "No").append('\n');
        sb.append("Balance: Rs. ").append(account.getBalance()).append('\n');
        sb.append("-----------------\n");
        return sb.toString();
    }
}
