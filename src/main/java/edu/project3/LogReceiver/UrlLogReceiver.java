package edu.project3.LogReceiver;

import edu.project3.LogReader.NginxLogReader;
import edu.project3.Models.DateLimits;
import edu.project3.Models.FileList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class UrlLogReceiver implements AbstractReceiver {

    public UrlLogReceiver() {

    }

    public FileList receive(String link, DateLimits dateLimits) throws IOException {
        List<String> logs = new ArrayList<>();
        NginxLogReader reader = new NginxLogReader();

        try {
            URL url = new URL(link);

            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()))) {
                String nextLine;

                while ((nextLine = bufferedReader.readLine()) != null) {
                    logs.add(nextLine);
                }

            } catch (IOException exception) {
                throw new IOException(exception);
            }
        } catch (MalformedURLException ignore) {
            throw new IOException();
        }

        return new FileList(List.of(link), reader.readLogs(logs, dateLimits));
    }
}
