package edu.project3.LogReceiver;

import edu.project3.Models.DateLimits;
import edu.project3.Models.FileList;
import java.io.IOException;

public interface AbstractReceiver {

    FileList receive(String path, DateLimits dateLimits) throws IOException;
}
