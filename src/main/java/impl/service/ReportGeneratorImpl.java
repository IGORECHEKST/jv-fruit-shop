package impl.service;

import db.Storage;
import java.util.Map;
import service.ReportGenerator;

public class ReportGeneratorImpl implements ReportGenerator {
    private static final String CSV_HEADER = "fruit,quantity";

    @Override
    public String generateReport() {
        StringBuilder report = new StringBuilder();
        report.append(CSV_HEADER).append("\n");
        Map<String, Integer> inventory = Storage.getAll();
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            report.append(entry.getKey())
                    .append(",")
                    .append(entry.getValue())
                    .append("\n");
        }
        return report.toString();
    }
}
