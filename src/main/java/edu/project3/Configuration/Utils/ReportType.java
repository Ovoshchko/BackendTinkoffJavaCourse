package edu.project3.Configuration.Utils;

import edu.project3.ReportMaker.AdocReportMaker;
import edu.project3.ReportMaker.MdReportMaker;
import edu.project3.ReportMaker.ReportMaker;

public enum ReportType {
    MD("markdown") {
        @Override
        public ReportMaker createReportMaker() {
            return new MdReportMaker();
        }
    },
    ADOC("adoc") {
        @Override
        public ReportMaker createReportMaker() {
            return new AdocReportMaker();
        }
    };

    public final String format;
    public abstract ReportMaker createReportMaker();

    ReportType(String format) {
        this.format = format;
    }

    public static ReportType getByFormat(String format) {
        for (ReportType type : values()) {
            if (type.format.equalsIgnoreCase(format)) {
                return type;
            }
        }

        throw new IllegalArgumentException("Сожалеем, такого формата нет" + format);
    }
}
