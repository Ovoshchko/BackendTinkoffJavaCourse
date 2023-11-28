package edu.project3.Models;

import java.util.List;
import java.util.Map;

public record Metric(String name, Map<String, List<String>> metrics) {
}
