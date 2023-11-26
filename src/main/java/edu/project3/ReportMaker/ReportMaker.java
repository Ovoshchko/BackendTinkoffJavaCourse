package edu.project3.ReportMaker;

import edu.project3.Models.Metric;
import java.util.List;

public interface ReportMaker {

    List<String> makeReport(List<Metric> logs);
}
