package edu.hw6.Task6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.sound.sampled.Port;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class PortScannerTest {

    private final PortScanner portScanner = new PortScanner();

    private final List<PortModel> portAnswers = List.of(
        new PortModel(Protocol.TCP, 22, ""),
        new PortModel(Protocol.TCP, 23, ""),
        new PortModel(Protocol.TCP, 110, ""),
        new PortModel(Protocol.UDP, 123, "NTP"),
        new PortModel(Protocol.TCP, 135, "EPMAP"),
        new PortModel(Protocol.UDP, 137, "NetBIOS"),
        new PortModel(Protocol.UDP, 138, "NetBIOS"),
        new PortModel(Protocol.TCP, 443, "VMware vSphere Client"),
        new PortModel(Protocol.TCP, 843, ""),
        new PortModel(Protocol.TCP, 3452, ""),
        new PortModel(Protocol.TCP, 3982, ""),
        new PortModel(Protocol.UDP, 5353, "mDNS")
    );

    @Test
    @DisplayName("--Port Scanner Test")
    void portScan() {
        List<PortModel> ports = portScanner.portScan();
        assertEquals(portAnswers.size(), ports.size());
    }
}
