import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class YalovaGuideGUI {
    private List<Categories> categoryList = new ArrayList<>();
    private List<String> selectedCategories = new ArrayList<>();
    private JPanel checkBoxPanel;
    private JTextField searchField;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new YalovaGuideGUI().GUI());
    }

    private void GUI() {
        JFrame j = new JFrame("Personalized Guide For Yalova");
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        CategoryList();

        JPanel mainPanel = new JPanel(new BorderLayout());

        // searching bar
        searchField = new JTextField();
        searchField.addActionListener(e -> filterCategories());
        mainPanel.add(searchField, BorderLayout.NORTH);

        checkBoxPanel = new JPanel(new GridLayout(categoryList.size(), 1));
        updateCheckBoxes();

        JButton selectButton = new JButton("SELECT");
        selectButton.addActionListener(e -> displaySelected());
        mainPanel.add(checkBoxPanel, BorderLayout.CENTER);
        mainPanel.add(selectButton, BorderLayout.SOUTH);

        j.getContentPane().add(mainPanel);
        j.pack();
        j.setLocationRelativeTo(null);
        j.setVisible(true);
        j.setSize(500, 300);
    }

    private void filterCategories() {
        String searchText = searchField.getText().toLowerCase().trim();

        // when search bar is empty show all the choices
        if (searchText.isEmpty()) {
            updateCheckBoxes();
        } else {

            checkBoxPanel.removeAll();

            boolean foundMatch = false;

            for (Categories category : categoryList) {
                if (category.getCategory().toLowerCase().equals(searchText)) {
                    JCheckBox checkBox = new JCheckBox(category.getCategory());
                    checkBox.addActionListener(e -> {
                        if (checkBox.isSelected()) {
                            selectedCategories.add(category.getCategory());
                        } else {
                            selectedCategories.remove(category.getCategory());
                        }
                    });
                    checkBoxPanel.add(checkBox);
                    foundMatch = true;
                    break;
                }
            }

            if (!foundMatch) {
                JOptionPane.showMessageDialog(null, "no match: " + searchText);
            }

            checkBoxPanel.revalidate();
            checkBoxPanel.repaint();
        }
    }

    private void updateCheckBoxes() {
        checkBoxPanel.removeAll();
        for (Categories category : categoryList) {
            JCheckBox checkBox = new JCheckBox(category.getCategory());
            checkBox.addActionListener(e -> {
                if (checkBox.isSelected()) {
                    selectedCategories.add(category.getCategory());
                } else {
                    selectedCategories.remove(category.getCategory());
                }
            });
            checkBoxPanel.add(checkBox);
        }
        checkBoxPanel.revalidate();
        checkBoxPanel.repaint();
    }

    private void CategoryList() {
        categoryList.add(new Categories("Sports", "Royal Blue gold gym Yalova", "Best way to get in shape",
                "Royal Blue Sitesi, İsmet Paşa Mah. 125/2 Sok. No.:37, 77200 Yalova Merkez/Yalova"));
        categoryList.add(new Categories("Technology", "Teknosa", "Take a look at latest technological products!",
                "Sahil, Özdilek AVM, Yalova İzmit Karayolu Cd No:85, 77600 Çiftlikköy/Yalova"));
        categoryList.add(new Categories("History", "Yürüyen Köşk",
                "Atatürk wanted a small house built for him next to the plane tree. This wooden two-storey house was built in 22 days and completed on September 12, 1929.",
                "İsmet Paşa, Merkez, Sahil Yürüyüş Yolu, 77100 Yalova Merkez/Yalova"));
        categoryList.add(new Categories("Art", "Yalova Sanat Galerisi", "Heart of the culture and art in Yalova",
                "Rüstem Paşa, Dere Sk. No:18 D:C, 77200 Yalova Merkez/Yalova"));
        categoryList.add(new Categories("Food", "Sandal Balık", "Seafood",
                "Rüstem Paşa, Mihenk Sok, Gazi Paşa Cd. No:5, 77200 Yalova Merkez"));
        categoryList.add(new Categories("Fun", "Divan Teras Cafe & Bar", "Every hour is a happy hour",
                "Rüstem Paşa, Kat:3, Gazi Paşa Cd. No:24, 77200 Yalova Merkez/Yalova"));
    }

    private void displaySelected() {
        StringBuilder fr = new StringBuilder("You have selected:\n");

        for (String category : selectedCategories) {
            for (Categories c : categoryList) {
                if (c.getCategory().equals(category)) {
                    fr.append("Category: ").append(c.getCategory()).append("\n");
                    fr.append("Name: ").append(c.getName()).append("\n");
                    fr.append("Information: ").append(c.getInformation()).append("\n");
                    fr.append("Address: ").append(c.getAddress()).append("\n");
                    fr.append("---------------\n");
                }
            }
        }

        JOptionPane.showMessageDialog(null, fr.toString());
    }

    private static class Categories {
        private String category;
        private String name;
        private String information;
        private String address;

        public Categories(String category, String name, String information, String address) {
            this.category = category;
            this.name = name;
            this.information = information;
            this.address = address;
        }

        public String getCategory() {
            return category;
        }

        public String getName() {
            return name;
        }

        public String getInformation() {
            return information;
        }

        public String getAddress() {
            return address;
        }
    }
}
