package edu.hw6.Task6;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Метод Map.of ограничен, поэтому пришлось добавить магические числа в статический блок
@SuppressWarnings({"MultipleStringLiterals", "RegexpSinglelineJava", "MagicNumber"})
public class PortScanner {

    private final static Map<Integer, String> PORT_SERVICE_MAP = new HashMap<>();

    static {
        PORT_SERVICE_MAP.put(22, "SSH");
        PORT_SERVICE_MAP.put(23, "Telnet");
        PORT_SERVICE_MAP.put(110, "POP3");
        PORT_SERVICE_MAP.put(123, "NTP");
        PORT_SERVICE_MAP.put(135, "EPMAP");
        PORT_SERVICE_MAP.put(137, "NetBIOS");
        PORT_SERVICE_MAP.put(138, "NetBIOS");
        PORT_SERVICE_MAP.put(443, "VMware vSphere Client");
        PORT_SERVICE_MAP.put(843, "");
        PORT_SERVICE_MAP.put(3452, "");
        PORT_SERVICE_MAP.put(3982, "");
        PORT_SERVICE_MAP.put(5353, "mDNS");
    }

    private final static String SPACE_STRING = "     ";

    public List<PortModel> portScan() {

        List<PortModel> openPorts = new ArrayList<>();

        for (Integer port: PORT_SERVICE_MAP.keySet()) {
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
