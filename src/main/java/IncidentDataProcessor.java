import org.apache.spark.sql.*;
import static org.apache.spark.sql.functions.*;

/**
 * Incident Data Processor - Spark SQL Application
 * Analyzes industrial incidents from CSV data
 *
 * @author Your Name
 * @version 1.0
 */
public class IncidentDataProcessor {

    private static final String CSV_FILE_PATH = "data/incidents.csv";
    private static final String APP_NAME = "Industrial-Incident-Analyzer";

    public static void main(String[] args) {
        // Initialize Spark Session
        SparkSession sparkSession = initializeSparkSession();

        try {
            // Load and process data
            Dataset<Row> incidentData = loadIncidentData(sparkSession);

            // Display basic data info
            System.out.println("=== INCIDENT DATA ANALYSIS ===");
            System.out.println("Total records loaded: " + incidentData.count());

            // Task 1: Analyze incidents by service
            analyzeIncidentsByService(sparkSession, incidentData);

            // Task 2: Find top years with most incidents
            findTopIncidentYears(sparkSession, incidentData);

        } catch (Exception e) {
            System.err.println("Error processing incident data: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Clean up resources
            sparkSession.close();
        }
    }

    /**
     * Initialize Spark Session with configuration
     */
    private static SparkSession initializeSparkSession() {
        return SparkSession.builder()
                .appName(APP_NAME)
                .master("local[*]")
                .config("spark.sql.adaptive.enabled", "true")
                .getOrCreate();
    }

    /**
     * Load incident data from CSV file
     */
    private static Dataset<Row> loadIncidentData(SparkSession spark) {
        Dataset<Row> data = spark.read()
                .option("header", "true")
                .option("inferSchema", "true")
                .option("timestampFormat", "yyyy-MM-dd")
                .csv(CSV_FILE_PATH);

        // Register as temporary view for SQL queries
        data.createOrReplaceTempView("incident_data");
        return data;
    }

    /**
     * Task 1: Analyze and display incidents grouped by service
     */
    private static void analyzeIncidentsByService(SparkSession spark, Dataset<Row> data) {
        System.out.println("\n=== INCIDENTS BY SERVICE ===");

        Dataset<Row> serviceAnalysis = spark.sql(
                "SELECT service, COUNT(*) as total_incidents " +
                        "FROM incident_data " +
                        "GROUP BY service " +
                        "ORDER BY total_incidents DESC"
        );

        serviceAnalysis.show(false);
    }

    /**
     * Task 2: Find and display the top 2 years with most incidents
     */
    private static void findTopIncidentYears(SparkSession spark, Dataset<Row> data) {
        System.out.println("\n=== TOP YEARS WITH MOST INCIDENTS ===");

        Dataset<Row> yearlyAnalysis = spark.sql(
                "SELECT YEAR(TO_DATE(date)) as incident_year, " +
                        "       COUNT(*) as total_incidents " +
                        "FROM incident_data " +
                        "WHERE date IS NOT NULL " +
                        "GROUP BY incident_year " +
                        "ORDER BY total_incidents DESC " +
                        "LIMIT 2"
        );

        yearlyAnalysis.show(false);
    }
}