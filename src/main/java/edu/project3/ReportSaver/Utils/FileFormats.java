package edu.project3.ReportSaver.Utils;

public enum FileFormats {
    MARKDOWN(".md"),
    ADOC(".adoc");

    private final String extension;

    FileFormats(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }
}
