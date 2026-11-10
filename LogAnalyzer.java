/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author Saba Machavariani.
 * @version    2026.11.09
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer()
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader();
    }
    
    public LogAnalyzer(String filename)
    {
        hourCounts = new int[24];
        reader = new LogfileReader(filename);
    }

    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }

    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        for(int hour = 0; hour < hourCounts.length; hour++) {
            System.out.println(hour + ": " + hourCounts[hour]);
        }
    }
    
    public int numberOfAccesses()
    {
        int total = 0;

        for(int hour = 0; hour < hourCounts.length; hour++) {
            total = total + hourCounts[hour];
        }

        return total;
    }
    
    public int busiestHour()
    {
        int busiest = 0;

        for(int hour = 1; hour < hourCounts.length; hour++) {
            if(hourCounts[hour] > hourCounts[busiest]) {
                busiest = hour;
            }
        }

        return busiest;
    }
    
     public int quietestHour()
    {
        int quietest = 0;

        for(int hour = 1; hour < hourCounts.length; hour++) {
            if(hourCounts[hour] < hourCounts[quietest]) {
                quietest = hour;
            }
        }

        return quietest;
    }
    
    public int busiestTwoHour()
    {
        int busiest = 0;
        int highestCount = 0;

        for(int hour = 0; hour < 24; hour++) {
            int nextHour = (hour + 1) % 24;
            int count = hourCounts[hour] + hourCounts[nextHour];

            if(count > highestCount) {
                highestCount = count;
                busiest = hour;
            }
        }

        return busiest;
    }
    
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
}
