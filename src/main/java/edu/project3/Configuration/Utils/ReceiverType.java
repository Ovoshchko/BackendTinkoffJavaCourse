package edu.project3.Configuration.Utils;

import edu.project3.LogReceiver.AbstractReceiver;
import edu.project3.LogReceiver.LocalLogReceiver;
import edu.project3.LogReceiver.UrlLogReceiver;

public enum ReceiverType {
    LOCAL {
        @Override
        public AbstractReceiver createReceiver() {
            return new LocalLogReceiver();
        }
    },
    URL {
        @Override
        public AbstractReceiver createReceiver() {
            return new UrlLogReceiver();
        }
    };

    public abstract AbstractReceiver createReceiver();
}
