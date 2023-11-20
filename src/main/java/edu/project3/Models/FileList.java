package edu.project3.Models;

import java.util.List;

public record FileList(List<String> files, List<NginxLog> metrics) {
}
