public class ChatFilter {                      
    public static int buildReport(String keyword, String[] logLines, StringBuilder report) { 
        if (keyword == null) { 
            keyword = ""; 
        }
        String lowerKeyword = keyword.toLowerCase(); 
        int count = 0; 

        for (String line : logLines) { 
            String[] parts = line.split(" ", 3); 
            if (parts.length < 3) { 
                continue; 
            }

            String time = parts[0]; 
            String user = parts[1]; 
            String message = parts[2];

            if (message.toLowerCase().contains(lowerKeyword)) { 
                count++; 
                report.append(time) 
                      .append(" ")
                      .append(user) 
                      .append(": ")
                      .append(message) 
                      .append(System.lineSeparator());
            }
        }
        return count;
    }
}
