package edu.project3.ReportMaker;

import edu.project3.Models.Metric;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MdReportMaker implements AbstractReportMaker {

    private final static Integer COLUMN_WIDTH = 25;
    private final static String LAYER = ":" + "-".repeat(COLUMN_WIDTH - 1);

    @Override
    public List<String> makeReport(List<Metric> metrics) {
        List<String> tables = new ArrayList<>();
        for (Metric metric: metrics) {
            tables.add(getMarkdownTable(metric));
        }
        return tables;
    }

    private String getMarkdownTable(Metric responsesMetric) {
        List<Map.Entry<String, List<String>>> metrics = new ArrayList<>(responsesMetric.metrics().entrySet());

        StringBuilder table = new StringBuilder();

        setName(table, responsesMetric);
        setHeader(table, metrics);
        setRows(table, metrics);

        return table.toString();
    }

    private void setName(StringBuilder table, Metric metric) {
        table.append(metric.name()).append(System.lineSeparator()).append(System.lineSeparator());
    }

    private void setHeader(StringBuilder table, List<Map.Entry<String, List<String>>> rows) {
        Map.Entry<String, List<String>> header = rows.get(0);
        table.append(createRow(header));
        for (int i = 0; i < header.getValue().size() + 1; i++) {
            table.append(createCell(LAYER));
        }
        table.append("|").append(System.lineSeparator());

    }

    private void setRows(StringBuilder table, List<Map.Entry<String, List<String>>> metrics) {
        for (int i = 1; i < metrics.size(); i++) {
            table.append(createRow(metrics.get(i)));
        }
    }

    private String createRow(Map.Entry<String, List<String>> row) {
        StringBuilder rowString = new StringBuilder();
        rowString.append(createCell(row.getKey()));

        for (String value: row.getValue()) {
            rowString.append(createCell(value));
        }

        rowString.append("|").append(System.lineSeparator());

        return rowString.toString();
    }

    private String createCell(String value) {
        return "|" + String.format("%-" + COLUMN_WIDTH + "s", truncate(value, COLUMN_WIDTH));
    }


    private String truncate(String value, int maxLength) {
        return value.length() > maxLength ? value.substring(0, maxLength - 3) + "..." : value;
    }
}
