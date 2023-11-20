package edu.project3.Configuration.Utils;

import edu.project3.ReportMaker.AbstractReportMaker;
import edu.project3.ReportMaker.AdocReportMaker;
import edu.project3.ReportMaker.MdReportMaker;

public enum ReportType {
    MD("markdown") {
        @Override
        public AbstractReportMaker createReportMaker() {
            return new MdReportMaker();
        }
    },
    ADOC("adoc") {
        @Override
        public AbstractReportMaker createReportMaker() {
            return new AdocReportMaker();
        }
    };

    public final String format;
    public abstract AbstractReportMaker createReportMaker();

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
