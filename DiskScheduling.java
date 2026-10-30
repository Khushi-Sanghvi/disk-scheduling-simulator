import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DiskScheduling extends JFrame {

 
    private JTextField cylinderField;
    private JTextField headField;
    private JTextField requestField;
    private JTextArea resultArea;
 
    private final Color navy = new Color(33, 59, 99);
    private final Color blue = new Color(51, 91, 151);
    private final Color pageColor = new Color(237, 243, 250);
    private final Color borderColor = new Color(211, 222, 239);
 
    public DiskScheduling() {
        setTitle("Disk Scheduling Simulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 780);
        setMinimumSize(new Dimension(950, 650));
        setLocationRelativeTo(null);
        createGUI();
    }
 
    private void createGUI() {
        JPanel page = new JPanel(new BorderLayout());
        page.setBackground(pageColor);
        page.setBorder(new EmptyBorder(24, 32, 24, 32));
        setContentPane(page);
 
        JPanel container = new JPanel(new BorderLayout());
        container.setBackground(Color.WHITE);
        page.add(container, BorderLayout.CENTER);
 
        // Header
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setBackground(navy);
        header.setBorder(new EmptyBorder(22, 12, 22, 12));
 
        JLabel title = new JLabel("Disk Scheduling Simulator");
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
 
        JLabel subtitle = new JLabel(
                "Operating System SLA | FCFS vs SSTF");
        subtitle.setFont(new Font("Arial", Font.PLAIN, 16));
        subtitle.setForeground(new Color(231, 238, 250));
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
 
        header.add(title);
        header.add(Box.createVerticalStrut(7));
        header.add(subtitle);
 
        container.add(header, BorderLayout.NORTH);
 
        // Main content
        JPanel content = new JPanel(new GridLayout(1, 2, 22, 0));
        content.setBackground(Color.WHITE);
        content.setBorder(new EmptyBorder(22, 22, 22, 22));
 
        JPanel inputCard = createCard();
        JPanel resultsCard = createCard();
 
        inputCard.add(sectionTitle("Input Details"));
        inputCard.add(Box.createVerticalStrut(18));
 
        cylinderField = new JTextField("100");
        headField = new JTextField("50");
        requestField = new JTextField("45, 21, 67, 90, 4");
 
        addField(inputCard, "Total Cylinders", cylinderField);
        addField(inputCard, "Initial Head Position", headField);
        addField(inputCard, "Request Queue", requestField);
 
        // Buttons
        JPanel buttons = new JPanel(new GridLayout(2, 2, 10, 10));
        buttons.setBackground(Color.WHITE);
        buttons.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 100));
        buttons.setAlignmentX(Component.LEFT_ALIGNMENT);
 
        JButton fcfsButton = createButton("Run FCFS", blue);
        JButton sstfButton = createButton("Run SSTF", blue);
        JButton compareButton = createButton(
                "Compare Both", new Color(23, 137, 111));
        JButton clearButton = createButton(
                "Clear Results", new Color(105, 121, 143));
 
        fcfsButton.addActionListener(e -> runAlgorithm(1));
        sstfButton.addActionListener(e -> runAlgorithm(2));
        compareButton.addActionListener(e -> runAlgorithm(3));
        clearButton.addActionListener(e -> clearResults());
 
        buttons.add(fcfsButton);
        buttons.add(sstfButton);
        buttons.add(compareButton);
        buttons.add(clearButton);
 
        inputCard.add(Box.createVerticalStrut(6));
        inputCard.add(buttons);
 
        // Results panel
        resultsCard.add(sectionTitle("Simulation Results"));
        resultsCard.add(Box.createVerticalStrut(18));
 
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        resultArea.setFont(
                new Font("Monospaced", Font.PLAIN, 14));
        resultArea.setForeground(new Color(38, 48, 65));
        resultArea.setBackground(Color.WHITE);
        resultArea.setMargin(new Insets(2, 2, 2, 2));
 
        resultArea.setText(
                "Enter the disk details and select an algorithm.\n\n"
                + "1. Enter total cylinders.\n"
                + "2. Enter initial head position.\n"
                + "3. Enter requests separated by commas.\n"
                + "4. Select FCFS, SSTF, or Compare Both."
        );
 
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
 
        resultsCard.add(scrollPane);
 
        content.add(inputCard);
        content.add(resultsCard);
 
        container.add(content, BorderLayout.CENTER);
 
        // Footer
        JPanel footer = new JPanel(new BorderLayout());
        footer.setBackground(new Color(239, 243, 249));
        footer.setBorder(new EmptyBorder(14, 8, 14, 8));
 
        JLabel footerText = new JLabel(
                "OS SLA Project | FCFS and SSTF Algorithms",
                SwingConstants.CENTER);
        footerText.setFont(
                new Font("Arial", Font.PLAIN, 15));
        footerText.setForeground(new Color(83, 104, 132));
 
        footer.add(footerText, BorderLayout.CENTER);
        container.add(footer, BorderLayout.SOUTH);
    }
 
    private JPanel createCard() {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(new EmptyBorder(18, 18, 18, 18));
        return card;
    }
 
    private JLabel sectionTitle(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 21));
        label.setForeground(navy);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }
 
    private void addField(
            JPanel panel, String labelText, JTextField field) {
 
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.BOLD, 15));
        label.setForeground(new Color(39, 52, 72));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
 
        field.setFont(new Font("Arial", Font.PLAIN, 15));
        field.setForeground(new Color(35, 45, 60));
        field.setBackground(Color.WHITE);
 
        field.setBorder(new javax.swing.border.CompoundBorder(
                new LineBorder(borderColor, 1, true),
                new EmptyBorder(9, 10, 9, 10)));
 
        field.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 44));
        field.setAlignmentX(Component.LEFT_ALIGNMENT);
 
        panel.add(label);
        panel.add(Box.createVerticalStrut(7));
        panel.add(field);
        panel.add(Box.createVerticalStrut(15));
    }
 
    private JButton createButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(color);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }
 
    // Read and validate user input
    private InputData readInput() {
        int cylinders;
        int head;
 
        try {
            cylinders = Integer.parseInt(
                    cylinderField.getText().trim());
            head = Integer.parseInt(
                    headField.getText().trim());
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException(
                    "Cylinder count and head position must be whole numbers.");
        }
 
        if (cylinders < 1 || cylinders > 100000) {
            throw new IllegalArgumentException(
                    "Cylinder count must be between 1 and 100000.");
        }
 
        if (head < 0 || head >= cylinders) {
            throw new IllegalArgumentException(
                    "Initial head position must be between 0 and "
                    + (cylinders - 1) + ".");
        }
 
        String queueText = requestField.getText().trim();
 
        if (queueText.isEmpty()) {
            throw new IllegalArgumentException(
                    "Request queue cannot be empty.");
        }
 
        String[] parts = queueText.split(",", -1);
 
        if (parts.length > 100) {
            throw new IllegalArgumentException(
                    "Please enter no more than 100 requests.");
        }
 
        List<Integer> requests = new ArrayList<>();
 
        for (int i = 0; i < parts.length; i++) {
 
            if (parts[i].trim().isEmpty()) {
                throw new IllegalArgumentException(
                        "Request " + (i + 1)
                        + " is empty. Separate numbers with commas.");
            }
 
            int request;
 
            try {
                request = Integer.parseInt(parts[i].trim());
            } catch (NumberFormatException ex) {
                throw new IllegalArgumentException(
                        "Every request must be a whole number. Invalid entry: "
                        + parts[i].trim());
            }
 
            if (request < 0 || request >= cylinders) {
                throw new IllegalArgumentException(
                        "Request " + request
                        + " is outside the valid range 0 to "
                        + (cylinders - 1) + ".");
            }
 
            requests.add(request);
        }
 
        return new InputData(cylinders, head, requests);
    }
 
    // FCFS: service requests in entered order
    private int[] calculateFCFS(
            int initialHead, List<Integer> requests) {
 
        int[] sequence = new int[requests.size() + 1];
        sequence[0] = initialHead;
 
        for (int i = 0; i < requests.size(); i++) {
            sequence[i + 1] = requests.get(i);
        }
 
        return sequence;
    }
 
    // SSTF: select the nearest unserviced request
    private int[] calculateSSTF(
            int initialHead, List<Integer> requests) {
 
        int n = requests.size();
        int[] sequence = new int[n + 1];
        boolean[] visited = new boolean[n];
 
        int currentHead = initialHead;
        sequence[0] = initialHead;
 
        for (int step = 0; step < n; step++) {
 
            int nearestIndex = -1;
            int shortestDistance = Integer.MAX_VALUE;
 
            for (int i = 0; i < n; i++) {
 
                if (!visited[i]) {
                    int distance = Math.abs(
                            currentHead - requests.get(i));
 
                    if (distance < shortestDistance) {
                        shortestDistance = distance;
                        nearestIndex = i;
                    }
                }
            }
 
            visited[nearestIndex] = true;
            currentHead = requests.get(nearestIndex);
            sequence[step + 1] = currentHead;
        }
 
        return sequence;
    }
 
    // Calculate total movement between consecutive head positions
    private long calculateTotalMovement(int[] sequence) {
        long total = 0;
 
        for (int i = 1; i < sequence.length; i++) {
            total += Math.abs(
                    (long) sequence[i] - sequence[i - 1]);
        }
 
        return total;
    }
 
    // Format the service sequence
    private String formatSequence(int[] sequence) {
        StringBuilder text = new StringBuilder();
 
        for (int i = 0; i < sequence.length; i++) {
            if (i > 0) {
                text.append(" -> ");
            }
 
            text.append(sequence[i]);
        }
 
        return text.toString();
    }
 
    // Display the result of one algorithm
    private void appendAlgorithmResult(
            StringBuilder output,
            String name,
            int[] sequence,
            long movement) {
 
        double average =
                (double) movement / (sequence.length - 1);
 
        output.append("\n--- ")
                .append(name)
                .append(" ALGORITHM ---\n");
 
        output.append("Seek Sequence:\n");
        output.append(formatSequence(sequence))
                .append("\n\n");
 
        output.append("Total Head Movement: ")
                .append(movement)
                .append(" tracks\n");
 
        output.append(String.format(
                "Average Head Movement: %.2f tracks/request%n%n",
                average));
    }
 
    // Run FCFS, SSTF, or both
    private void runAlgorithm(int choice) {
        try {
            InputData input = readInput();
 
            int[] fcfsSequence =
                    calculateFCFS(input.head, input.requests);
 
            int[] sstfSequence =
                    calculateSSTF(input.head, input.requests);
 
            long fcfsMovement =
                    calculateTotalMovement(fcfsSequence);
 
            long sstfMovement =
                    calculateTotalMovement(sstfSequence);
 
            StringBuilder output = new StringBuilder();
 
            output.append("DISK SCHEDULING SIMULATION\n\n");
 
            output.append("Total Cylinders: ")
                    .append(input.cylinders).append("\n");
 
            output.append("Initial Head Position: ")
                    .append(input.head).append("\n");
 
            output.append("Request Queue: ")
                    .append(input.requests).append("\n\n");
 
            if (choice == 1 || choice == 3) {
                appendAlgorithmResult(
                        output, "FCFS",
                        fcfsSequence, fcfsMovement);
            }
 
            if (choice == 2 || choice == 3) {
                appendAlgorithmResult(
                        output, "SSTF",
                        sstfSequence, sstfMovement);
            }
 
            if (choice == 3) {
                output.append(
                        "--- PERFORMANCE COMPARISON ---\n");
 
                output.append("FCFS Total Movement: ")
                        .append(fcfsMovement)
                        .append(" tracks\n");
 
                output.append("SSTF Total Movement: ")
                        .append(sstfMovement)
                        .append(" tracks\n");
 
                if (fcfsMovement < sstfMovement) {
                    output.append(
                            "Lower movement for this input: FCFS\n");
                } else if (sstfMovement < fcfsMovement) {
                    output.append(
                            "Lower movement for this input: SSTF\n");
                } else {
                    output.append(
                            "Both algorithms have equal movement.\n");
                }
            }
 
            resultArea.setText(output.toString());
            resultArea.setCaretPosition(0);
 
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Invalid Input",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
 
    // Clear the results panel
    private void clearResults() {
        resultArea.setText(
                "Enter the disk details and select an algorithm.\n"
                + "Example request queue: 45, 21, 67, 90, 4");
 
        resultArea.setCaretPosition(0);
    }
 
    // Store validated input
    private static class InputData {
        final int cylinders;
        final int head;
        final List<Integer> requests;
 
        InputData(
                int cylinders,
                int head,
                List<Integer> requests) {
            this.cylinders = cylinders;
            this.head = head;
            this.requests = requests;
        }
    }
 
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
 
            try {
                UIManager.setLookAndFeel(
                        UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ex) {
                // Use the default Swing appearance.
            }
 
            new DiskScheduling().setVisible(true);
        });
    }
}