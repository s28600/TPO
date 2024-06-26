package zad1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminUI extends JFrame implements ActionListener {
    Administrator admin;
    private DefaultListModel<String> availableTopicsModel;
    private DefaultListModel<String> subscribedTopicsModel;
    private JList<String> availableTopicsList;
    private JTextField newTopicField;

    public AdminUI(Administrator admin) {
        this.admin = admin;
        setTitle("Admin");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        // Available Topics Panel
        JPanel availableTopicsPanel = new JPanel(new BorderLayout());
        JLabel availableLabel = new JLabel("Available Topics");
        availableTopicsModel = new DefaultListModel<>();
        String[] topics = admin.getTopics().split(" ");
        for (var topic : topics){
            availableTopicsModel.addElement(topic);
        }
        availableTopicsList = new JList<>(availableTopicsModel);
        availableTopicsPanel.add(availableLabel, BorderLayout.NORTH);
        availableTopicsPanel.add(new JScrollPane(availableTopicsList), BorderLayout.CENTER);

        // New Topic Panel
        JPanel newTopicPanel = new JPanel(new BorderLayout());
        JLabel newTopicLabel = new JLabel("New Topic");
        newTopicField = new JTextField(15);
        JButton addButton = new JButton("Add");
        addButton.addActionListener(this);
        newTopicPanel.add(newTopicLabel, BorderLayout.WEST);
        newTopicPanel.add(newTopicField, BorderLayout.CENTER);
        newTopicPanel.add(addButton, BorderLayout.EAST);

        // Main Panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(availableTopicsPanel, BorderLayout.CENTER);
        mainPanel.add(newTopicPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Add")) {
            String newTopic = newTopicField.getText().trim();
            if (!newTopic.isEmpty()) {
                admin.addTopic(newTopic);
                newTopicField.setText("");
            }
        }

        try {
            Thread.sleep(5);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }

        String[] subscribed = admin.getTopics().split(" ");
        availableTopicsModel.removeAllElements();
        if (!subscribed[0].equals("NONE")){
            for (var sub : subscribed){
                availableTopicsModel.addElement(sub);
            }
        }
    }
}

