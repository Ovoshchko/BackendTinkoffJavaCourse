package edu.project3.LogReceiver;

import java.io.IOException;
import java.util.List;

public interface AbstractReceiver {

    List<?> receive(String path) throws IOException;
}
