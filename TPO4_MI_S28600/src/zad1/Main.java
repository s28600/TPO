package zad1;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ClientUI clientUi = new ClientUI(new Client());
        AdminUI adminUI = new AdminUI(new Administrator());
    }
}
