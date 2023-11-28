package edu.project3.ReportMaker;

import edu.project3.Models.Metric;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AdocReportMaker implements ReportMaker {

    private final static Integer COLUMN_WIDTH = 25;
    private final static String LAYER = " ".repeat(COLUMN_WIDTH + 1);
    private final static String ETC = "...";
    private final static String DIVIDER = "|";
    public static final String CHAPTER = "== ";
    public static final String BORDER = "|===";

    @Override
    public List<String> makeReport(List<Metric> metrics) {
        List<String> tables = new ArrayList<>();
        for (Metric metric: metrics) {
            tables.add(getAdocTable(metric));
        }
        return tables;
    }

    private String getAdocTable(Metric responsesMetric) {
        List<Map.Entry<String, List<String>>> metrics = new ArrayList<>(responsesMetric.metrics().entrySet());

        StringBuilder table = new StringBuilder();

        setBeginning(table, responsesMetric.name());
        setHeader(table, metrics);
        setRows(table, metrics);
        setEnding(table);

        return table.toString();
    }

    private void setHeader(StringBuilder table, List<Map.Entry<String, List<String>>> rows) {
        Map.Entry<String, List<String>> header = rows.get(0);
        table.append(createRow(header));
        createLayer(table, header.getValue().size() + 1);
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

        rowString.append(System.lineSeparator());

        return rowString.toString();
    }

    private void createLayer(StringBuilder table, int size) {
        for (int i = 0; i < size; i++) {
            table.append(LAYER);
        }
        table.append(System.lineSeparator());
    }

    private String createCell(String value) {
        return DIVIDER + String.format("%-" + COLUMN_WIDTH + "s", truncate(value, COLUMN_WIDTH));
    }


    private String truncate(String value, int maxLength) {
        return value.length() > maxLength ? value.substring(0, maxLength - ETC.length()) + ETC : value;
    }

    private void setBeginning(StringBuilder table, String name) {
        table.append(CHAPTER).append(name).append(System.lineSeparator()).append(BORDER)
            .append(System.lineSeparator());
    }

    private void setEnding(StringBuilder table) {
        table.append(BORDER).append(System.lineSeparator());
    }
}
