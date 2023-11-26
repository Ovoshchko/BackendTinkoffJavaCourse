package edu.project3.Configuration.Utils;

import edu.project3.LogReceiver.LocalLogReceiver;
import edu.project3.LogReceiver.Receiver;
import edu.project3.LogReceiver.UrlLogReceiver;

public enum ReceiverType {
    LOCAL {
        @Override
        public Receiver createReceiver() {
            return new LocalLogReceiver();
        }
    },
    URL {
        @Override
        public Receiver createReceiver() {
            return new UrlLogReceiver();
        }
    };

    public abstract Receiver createReceiver();
}
