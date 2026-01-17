import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class RailFenceCipherSystem {

    static String encrypt(String text, int rails) {

        if (rails <= 1) return text;

        char[][] arr = new char[rails][text.length()];

        for (int i = 0; i < rails; i++)
            for (int j = 0; j < text.length(); j++)
                arr[i][j] = '\n';

        int row = 0;
        boolean down = false;

        for (int i = 0; i < text.length(); i++) {

            if (row == 0 || row == rails - 1)
                down = !down;

            arr[row][i] = text.charAt(i);

            if (down) row++; //if down is true we move the rows going down 
            else row--;  //if down is false it means we are going upward
        }

        String result = "";

        for (int i = 0; i < rails; i++)
            for (int j = 0; j < text.length(); j++)
                if (arr[i][j] != '\n')
                    result += arr[i][j];

        return result;
    }

    static String decrypt(String cipher, int rails) {

        if (rails <= 1) return cipher;

        char[][] arr = new char[rails][cipher.length()];

        for (int i = 0; i < rails; i++)
            for (int j = 0; j < cipher.length(); j++)
                arr[i][j] = '\n';

        int row = 0;
        boolean down = false;

        for (int i = 0; i < cipher.length(); i++) {

            if (row == 0 || row == rails - 1)
                down = !down;

            arr[row][i] = '*';

            if (down) row++;
            else row--;
        }

        int index = 0;

        for (int i = 0; i < rails; i++)
            for (int j = 0; j < cipher.length(); j++)
                if (arr[i][j] == '*')
                    arr[i][j] = cipher.charAt(index++);

        String original = "";
        row = 0;
        down = false;

        for (int i = 0; i < cipher.length(); i++) {

            if (row == 0 || row == rails - 1)
                down = !down;

            original += arr[row][i];

            if (down) row++;
            else row--;
        }

        return original;
    }

    public static void main(String[] args) {

        Color primary = new Color(0xA7, 0x5F, 0x37);
        Color secondary = new Color(0xCA, 0x8E, 0x82);
        Color bg = new Color(247, 237, 233);

        JFrame frame = new JFrame("Rail Fence Cipher Encryption System");
        frame.setSize(560, 650);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(bg);
        panel.setBorder(new EmptyBorder(20, 25, 20, 25));

        JLabel title = new JLabel("Rail Fence Cipher Encryption System");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(primary);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel desc = new JLabel(
            "<html><center>Rail Fence Cipher is a transposition cipher that encrypts text<br>" +
            "by arranging characters in a zig-zag pattern across multiple rails.</center></html>"
        );
        desc.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField textField = new JTextField();
        textField.setBorder(BorderFactory.createTitledBorder(
                new LineBorder(primary, 2), "Enter Plain Text"));

        JTextField railField = new JTextField();
        railField.setBorder(BorderFactory.createTitledBorder(
                new LineBorder(primary, 2), "Number of Rails"));

        JButton encryptBtn = new JButton("Encrypt");
        encryptBtn.setBackground(primary);
        encryptBtn.setForeground(Color.WHITE);

        JButton decryptBtn = new JButton("Decrypt");
        decryptBtn.setBackground(secondary);
        decryptBtn.setForeground(Color.WHITE);

        JPanel btnPanel = new JPanel(new GridLayout(1, 2, 12, 0));
        btnPanel.setBackground(bg);
        btnPanel.add(encryptBtn);
        btnPanel.add(decryptBtn);

        JTextArea output = new JTextArea(4, 20);
        output.setEditable(false);
        output.setLineWrap(true);
        output.setBorder(BorderFactory.createTitledBorder(
                new LineBorder(secondary, 2), "Encrypted / Decrypted Output"));

        JTextArea steps = new JTextArea(
            "Algorithm Steps:\n" +
            "1. Write text in zig-zag pattern across rails.\n" +
            "2. Read characters row-wise to encrypt.\n" +
            "3. Reverse the process to decrypt."
        );
        steps.setEditable(false);
        steps.setBackground(bg);

        JLabel complexity = new JLabel(
            "Time Complexity: O(n)    Space Complexity: O(n × rails)",
            JLabel.CENTER
        );
        complexity.setForeground(primary);

        JLabel footer = new JLabel(
            "Developed by Hooriyah Amir And Arhama Batool | DSA Project",
            JLabel.CENTER
        );
        footer.setForeground(Color.GRAY);

        panel.add(title);
        panel.add(Box.createVerticalStrut(8));
        panel.add(desc);
        panel.add(Box.createVerticalStrut(15));
        panel.add(textField);
        panel.add(Box.createVerticalStrut(10));
        panel.add(railField);
        panel.add(Box.createVerticalStrut(15));
        panel.add(btnPanel);
        panel.add(Box.createVerticalStrut(15));
        panel.add(new JScrollPane(output));
        panel.add(Box.createVerticalStrut(10));
        panel.add(steps);
        panel.add(Box.createVerticalStrut(8));
        panel.add(complexity);
        panel.add(Box.createVerticalStrut(10));
        panel.add(footer);

        frame.add(panel);
        frame.setVisible(true);

        encryptBtn.addActionListener(e -> {
            try {
                output.setText(
                    encrypt(textField.getText(),
                            Integer.parseInt(railField.getText()))
                );
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid Input");
            }
        });

        decryptBtn.addActionListener(e -> {
            try {
                output.setText(
                    decrypt(output.getText(),
                            Integer.parseInt(railField.getText()))
                );
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid Input");
            }
        });
    }
}