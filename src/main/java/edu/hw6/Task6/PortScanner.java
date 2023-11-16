package edu.hw6.Task6;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"MultipleStringLiterals", "RegexpSinglelineJava"})
public class PortScanner {

    private final static int[] PORTS_LIST = {
        22, 23, 110, 123, 135, 137, 138, 443, 843, 3452, 3982, 5353
    };
    private final static Map<Integer, String> PORT_SERVICE_MAP = Map.of(
        22, "SSH",
        23, "Telnet",
        110, "POP3",
        123, "NTP",
        135, "EPMAP",
        137, "NetBIOS",
        138, "NetBIOS",
        443, "VMware vSphere Client",
        5353, "mDNS"
    );
    private final static String SPACE_STRING = "     ";

    public List<PortModel> portScan() {

        List<PortModel> openPorts = new LinkedList<>();

        for (int port: PORTS_LIST) {
            openPorts.add(getPortModel(port));
        }

        return openPorts;
    }

    public void printPorts() {

        List<PortModel> ports = portScan();

        String builder = "Protocol" + SPACE_STRING + "Port" + SPACE_STRING + "Service";

        System.out.println(builder);

        for (PortModel port: ports) {
            System.out.println(port.protocol().getName() + SPACE_STRING + port.port() + SPACE_STRING + port.service());
        }
    }

    private PortModel getPortModel(int port) {

        String service = PORT_SERVICE_MAP.get(port) == null ? "Unknown service" : PORT_SERVICE_MAP.get(port);

        if (service == null) {
            service = "";
        }

        if (isTcpClosed(port)) {
            return new PortModel(Protocol.TCP, port, service);
        }

        if (isUdpClosed(port)) {
            return new PortModel(Protocol.UDP, port, service);
        }

        return new PortModel(Protocol.TCP, port, "");

    }

    private boolean isTcpClosed(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            return false;
        } catch (IOException exception) {
            return true;
        }
    }

    private boolean isUdpClosed(int port) {
        try (DatagramSocket datagramSocket = new DatagramSocket(port)) {
            return false;
        } catch (IOException exception) {
            return true;
        }
    }

}
