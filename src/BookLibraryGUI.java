import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class BookLibraryGUI extends JFrame {

    private ArrayListBooks arrayBooks;
    private LinkedList linkedBooks;

    private JTable bookTable;
    private DefaultTableModel tableModel;

    private JComboBox<String> searchTypeBox;
    private JTextField searchField;

    private JComboBox<String> sortTypeBox;
    private JComboBox<String> orderBox;

    private JLabel statusLabel;
    private JLabel performanceLabel;

    private final Color DARK_BLUE = new Color(36, 63, 87);
    private final Color BLUE = new Color(65, 105, 135);
    private final Color LIGHT_BLUE = new Color(232, 242, 248);
    private final Color CREAM = new Color(250, 248, 242);
    private final Color GOLD = new Color(211, 170, 82);
    private final Color TEXT = new Color(45, 50, 55);

    public BookLibraryGUI() {

        arrayBooks = new ArrayListBooks();
        linkedBooks = new LinkedList();

        setTitle("Book Library Manager");
        setSize(1200, 800);
        setMinimumSize(new Dimension(950, 650));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        createInterface();
    }

    private void createInterface() {

        BookPatternPanel background = new BookPatternPanel();
        background.setLayout(new BorderLayout());

        // -----------------------------
        // HEADER
        // -----------------------------

        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        header.setBorder(BorderFactory.createEmptyBorder(25, 35, 15, 35));

        JLabel title = new JLabel("BOOK LIBRARY MANAGER");
        title.setFont(new Font("Serif", Font.BOLD, 32));
        title.setForeground(DARK_BLUE);

        JLabel subtitle = new JLabel(
            "Search, sort, explore, and manage your collection"
        );
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 14));
        subtitle.setForeground(BLUE);

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BorderLayout());
        titlePanel.setOpaque(false);
        titlePanel.add(title, BorderLayout.NORTH);
        titlePanel.add(subtitle, BorderLayout.SOUTH);

        header.add(titlePanel, BorderLayout.WEST);

        background.add(header, BorderLayout.NORTH);

        // -----------------------------
        // MAIN CONTENT
        // -----------------------------

        JPanel content = new JPanel(new BorderLayout(15, 15));
        content.setOpaque(false);
        content.setBorder(
            BorderFactory.createEmptyBorder(0, 35, 20, 35)
        );

        // -----------------------------
        // CONTROL PANEL
        // -----------------------------

        JPanel controls = createControlsPanel();
        content.add(controls, BorderLayout.NORTH);

        // -----------------------------
        // TABLE
        // -----------------------------

        String[] columns = {
            "Book ID",
            "ISBN",
            "Title",
            "Author(s)",
            "Publication Year",
            "Average Rating"
        };

        tableModel = new DefaultTableModel(columns, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        bookTable = new JTable(tableModel);

        bookTable.setRowHeight(30);
        bookTable.setFont(new Font("SansSerif", Font.PLAIN, 13));
        bookTable.getTableHeader().setFont(
            new Font("SansSerif", Font.BOLD, 13)
        );

        bookTable.getTableHeader().setBackground(DARK_BLUE);
        bookTable.getTableHeader().setForeground(Color.WHITE);

        bookTable.setSelectionBackground(
            new Color(210, 225, 235)
        );

        bookTable.setSelectionForeground(TEXT);

        JScrollPane scrollPane = new JScrollPane(bookTable);
        scrollPane.setBorder(
            BorderFactory.createLineBorder(
                new Color(190, 200, 205),
                1
            )
        );

        content.add(scrollPane, BorderLayout.CENTER);

        // -----------------------------
        // BOTTOM PANEL
        // -----------------------------

        JPanel bottom = createBottomPanel();
        content.add(bottom, BorderLayout.SOUTH);

        background.add(content, BorderLayout.CENTER);

        setContentPane(background);
    }

    // =========================================================
    // CONTROL PANEL
    // =========================================================

    private JPanel createControlsPanel() {

        JPanel outer = new JPanel(new BorderLayout(10, 10));
        outer.setOpaque(false);

        JPanel controls = new JPanel(new GridBagLayout());

        controls.setBackground(
            new Color(
                CREAM.getRed(),
                CREAM.getGreen(),
                CREAM.getBlue(),
                235
            )
        );

        controls.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                    new Color(205, 205, 200),
                    1
                ),
                BorderFactory.createEmptyBorder(
                    15, 18, 15, 18
                )
            )
        );

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5, 8, 5, 8);
        gbc.anchor = GridBagConstraints.WEST;

        // SEARCH LABEL

        JLabel searchLabel = new JLabel("SEARCH");
        searchLabel.setFont(
            new Font("SansSerif", Font.BOLD, 12)
        );
        searchLabel.setForeground(DARK_BLUE);

        gbc.gridx = 0;
        gbc.gridy = 0;
        controls.add(searchLabel, gbc);

        // SEARCH TYPE

        searchTypeBox = new JComboBox<>(
            new String[] {
                "Book ID",
                "ISBN"
            }
        );

        searchTypeBox.setPreferredSize(
            new Dimension(120, 32)
        );

        gbc.gridx = 1;
        controls.add(searchTypeBox, gbc);

        // SEARCH FIELD

        searchField = new JTextField();
        searchField.setPreferredSize(
            new Dimension(200, 32)
        );

        gbc.gridx = 2;
        controls.add(searchField, gbc);

        // SEARCH BUTTON

        JButton searchButton = createButton("Search");

        searchButton.addActionListener(
            e -> searchBooks()
        );

        gbc.gridx = 3;
        controls.add(searchButton, gbc);

        // TOP 10 BUTTON

        JButton topTenButton = createButton("Show Top 10");

        topTenButton.addActionListener(
            e -> showTopTen()
        );

        gbc.gridx = 4;
        controls.add(topTenButton, gbc);

        // SEPARATOR

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 5;

        controls.add(
            new JSeparator(
                SwingConstants.HORIZONTAL
            ),
            gbc
        );

        // SORT LABEL

        JLabel sortLabel = new JLabel("SORT");
        sortLabel.setFont(
            new Font("SansSerif", Font.BOLD, 12)
        );
        sortLabel.setForeground(DARK_BLUE);

        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridx = 0;

        controls.add(sortLabel, gbc);

        // SORT TYPE

        sortTypeBox = new JComboBox<>(
            new String[] {
                "Authors",
                "Publication Year"
            }
        );

        sortTypeBox.setPreferredSize(
            new Dimension(160, 32)
        );

        gbc.gridx = 1;
        controls.add(sortTypeBox, gbc);

        // ORDER

        orderBox = new JComboBox<>(
            new String[] {
                "Ascending",
                "Descending"
            }
        );

        orderBox.setPreferredSize(
            new Dimension(130, 32)
        );

        gbc.gridx = 2;
        controls.add(orderBox, gbc);

        // SORT BUTTON

        JButton sortButton = createButton("Sort Books");

        sortButton.addActionListener(
            e -> sortBooks()
        );

        gbc.gridx = 3;
        controls.add(sortButton, gbc);

        // LOAD BUTTON

        JButton loadButton = createButton("Load Books");

        loadButton.addActionListener(
            e -> loadBooks()
        );

        gbc.gridx = 4;
        controls.add(loadButton, gbc);

        outer.add(controls, BorderLayout.CENTER);

        return outer;
    }

    // =========================================================
    // BOTTOM PANEL
    // =========================================================

    private JPanel createBottomPanel() {

        JPanel bottom = new JPanel(
            new BorderLayout(10, 10)
        );

        bottom.setOpaque(false);

        // LEFT SIDE

        JPanel left = new JPanel(
            new GridBagLayout()
        );

        left.setBackground(
            new Color(
                CREAM.getRed(),
                CREAM.getGreen(),
                CREAM.getBlue(),
                235
            )
        );

        left.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                    new Color(205, 205, 200),
                    1
                ),
                BorderFactory.createEmptyBorder(
                    10, 15, 10, 15
                )
            )
        );

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(4, 5, 4, 5);

        JLabel selectedLabel = new JLabel(
            "Select a book in the table to delete it:"
        );

        selectedLabel.setForeground(TEXT);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        left.add(selectedLabel, gbc);

        JButton deleteButton = createButton(
            "Delete Selected Book"
        );

        deleteButton.addActionListener(
            e -> deleteSelectedBook()
        );

        gbc.gridy = 1;
        gbc.gridwidth = 1;

        left.add(deleteButton, gbc);

        JButton clearButton = createButton(
            "Clear Table"
        );

        clearButton.addActionListener(
            e -> clearTable()
        );

        gbc.gridx = 1;

        left.add(clearButton, gbc);

        // RIGHT SIDE

        JPanel performance = new JPanel(
            new BorderLayout(10, 5)
        );

        performance.setBackground(
            new Color(
                LIGHT_BLUE.getRed(),
                LIGHT_BLUE.getGreen(),
                LIGHT_BLUE.getBlue(),
                235
            )
        );

        performance.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                    new Color(180, 200, 210),
                    1
                ),
                BorderFactory.createEmptyBorder(
                    10, 15, 10, 15
                )
            )
        );

        JLabel performanceTitle = new JLabel(
            "SEARCH PERFORMANCE"
        );

        performanceTitle.setFont(
            new Font(
                "SansSerif",
                Font.BOLD,
                12
            )
        );

        performanceTitle.setForeground(DARK_BLUE);

        performance.add(
            performanceTitle,
            BorderLayout.NORTH
        );

        performanceLabel = new JLabel(
            "Run 1,000 searches to compare algorithms."
        );

        performanceLabel.setForeground(TEXT);

        performance.add(
            performanceLabel,
            BorderLayout.CENTER
        );

        JButton performanceButton = createButton(
            "Run Performance Test"
        );

        performanceButton.addActionListener(
            e -> runPerformanceTest()
        );

        performance.add(
            performanceButton,
            BorderLayout.EAST
        );

        bottom.add(left, BorderLayout.WEST);
        bottom.add(performance, BorderLayout.CENTER);

        statusLabel = new JLabel(
            "Ready. Click 'Load Books' to begin."
        );

        statusLabel.setForeground(BLUE);
        statusLabel.setFont(
            new Font("SansSerif", Font.ITALIC, 12)
        );

        bottom.add(
            statusLabel,
            BorderLayout.SOUTH
        );

        return bottom;
    }

    // =========================================================
    // BUTTON STYLE
    // =========================================================

    private JButton createButton(String text) {

        JButton button = new JButton(text);

        button.setFont(
            new Font(
                "SansSerif",
                Font.BOLD,
                12
            )
        );

        button.setForeground(Color.WHITE);
        button.setBackground(BLUE);

        button.setFocusPainted(false);

        button.setBorder(
            BorderFactory.createEmptyBorder(
                8, 14, 8, 14
            )
        );

        return button;
    }

    // =========================================================
    // LOAD BOOKS
    // =========================================================

    private void loadBooks() {

        try {

            arrayBooks = new ArrayListBooks();

            arrayBooks.loadBooks("books.csv");

            linkedBooks =
                CSV_Reader.loadBooks("books.csv");

            displayBooks(arrayBooks.getBooks());

            statusLabel.setText(
                "Loaded " + arrayBooks.size()
                + " books successfully."
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                this,
                "There was a problem loading books:\n"
                + e.getMessage(),
                "Loading Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // SHOW TOP 10
    // =========================================================

    private void showTopTen() {

        if (!booksLoaded()) {
            return;
        }

        ArrayList<Book> topBooks =
            new ArrayList<>();

        for (
            int i = 0;
            i < Math.min(10, arrayBooks.size());
            i++
        ) {
            topBooks.add(
                arrayBooks.get(i)
            );
        }

        displayBooks(topBooks);

        statusLabel.setText(
            "Showing the top 10 records from the loaded dataset."
        );
    }

    // =========================================================
    // SEARCH
    // =========================================================

    private void searchBooks() {

        if (!booksLoaded()) {
            return;
        }

        String searchText =
            searchField.getText().trim();

        if (searchText.isEmpty()) {

            JOptionPane.showMessageDialog(
                this,
                "Please enter a Book ID or ISBN.",
                "Search",
                JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        String searchType =
            (String) searchTypeBox.getSelectedItem();

        Book found = null;

        if (searchType.equals("Book ID")) {

            try {

                int id =
                    Integer.parseInt(searchText);

                // ArrayList binary search
                arrayBooks.sortByBookId();

                found =
                    arrayBooks.binarySearchById(id);

                statusLabel.setText(
                    "Binary search completed using ArrayList."
                );

            } catch (NumberFormatException e) {

                JOptionPane.showMessageDialog(
                    this,
                    "Book ID must be a number.",
                    "Invalid Book ID",
                    JOptionPane.WARNING_MESSAGE
                );

                return;
            }

        } else {

            arrayBooks.sortByISBN();

            found =
                arrayBooks.binarySearchByISBN(
                    searchText
                );

            statusLabel.setText(
                "ISBN search completed using ArrayList."
            );

        }

        tableModel.setRowCount(0);

        if (found != null) {

            addBookToTable(found);

            statusLabel.setText(
                "Book found successfully."
            );

        } else {

            statusLabel.setText(
                "No matching book was found."
            );

            JOptionPane.showMessageDialog(
                this,
                "No book was found matching:\n"
                + searchText,
                "Search Result",
                JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    // =========================================================
    // SORT
    // =========================================================

    private void sortBooks() {

        if (!booksLoaded()) {
            return;
        }

        String sortType =
            (String) sortTypeBox.getSelectedItem();

        String order =
            (String) orderBox.getSelectedItem();

        boolean ascending =
            order.equals("Ascending");

        if (sortType.equals("Authors")) {

            if (ascending) {

                BookSorter.sortByAuthorAscending(
                    arrayBooks.getBooks()
                );

            } else {

                BookSorter.sortByAuthorDescending(
                    arrayBooks.getBooks()
                );
            }

        } else {

            if (ascending) {

                BookSorter.sortByYearAscending(
                    arrayBooks.getBooks()
                );

            } else {

                BookSorter.sortByYearDescending(
                    arrayBooks.getBooks()
                );
            }
        }

        displayBooks(arrayBooks.getBooks());

        statusLabel.setText(
            "Sorted by " + sortType
            + " (" + order + ")."
        );
    }

    // =========================================================
    // DELETE
    // =========================================================

    private void deleteSelectedBook() {

        int selectedRow =
            bookTable.getSelectedRow();

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(
                this,
                "Please select a book from the table first.",
                "Delete Book",
                JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int bookId =
            (int) tableModel.getValueAt(
                selectedRow,
                0
            );

        String title =
            String.valueOf(
                tableModel.getValueAt(
                    selectedRow,
                    1
                )
            );

        int answer =
            JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete:\n\n"
                + title
                + "\n\nBook ID: "
                + bookId,
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
            );

        if (answer != JOptionPane.YES_OPTION) {
            return;
        }

        boolean arrayDeleted =
            arrayBooks.deleteById(bookId);

        boolean linkedDeleted =
            linkedBooks.deleteById(bookId);

        if (arrayDeleted || linkedDeleted) {

            tableModel.removeRow(selectedRow);

            statusLabel.setText(
                "Book " + bookId
                + " was deleted successfully."
            );

        } else {

            JOptionPane.showMessageDialog(
                this,
                "The selected book could not be deleted.",
                "Delete Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // PERFORMANCE TEST
    // =========================================================

    private void runPerformanceTest() {

        if (!booksLoaded()) {
            return;
        }

        int numberOfSearches = 1000;

        arrayBooks.sortByBookId();

        int[] searchIds =
            new int[numberOfSearches];

        for (int i = 0; i < numberOfSearches; i++) {

            searchIds[i] =
                arrayBooks
                    .get(i * 10)
                    .getBookId();
        }

        // ArrayList linear

        long start =
            System.nanoTime();

        for (int id : searchIds) {
            arrayBooks.findById(id);
        }

        long end =
            System.nanoTime();

        long arrayLinearTime =
            end - start;

        // ArrayList binary

        start =
            System.nanoTime();

        for (int id : searchIds) {
            arrayBooks.binarySearchById(id);
        }

        end =
            System.nanoTime();

        long arrayBinaryTime =
            end - start;

        // LinkedList linear

        start =
            System.nanoTime();

        for (int id : searchIds) {
            linkedBooks.findById(id);
        }

        end =
            System.nanoTime();

        long linkedLinearTime =
            end - start;

        double arrayLinearMs =
            arrayLinearTime / 1_000_000.0;

        double arrayBinaryMs =
            arrayBinaryTime / 1_000_000.0;

        double linkedLinearMs =
            linkedLinearTime / 1_000_000.0;

        performanceLabel.setText(
            String.format(
                "ArrayList Linear: %.3f ms   |   "
                + "ArrayList Binary: %.3f ms   |   "
                + "LinkedList Linear: %.3f ms",
                arrayLinearMs,
                arrayBinaryMs,
                linkedLinearMs
            )
        );

        statusLabel.setText(
            "Performance test completed using "
            + numberOfSearches + " searches."
        );
    }

    // =========================================================
    // DISPLAY BOOKS
    // =========================================================

    private void displayBooks(
        List<Book> books
    ) {

        tableModel.setRowCount(0);

        for (Book book : books) {

            addBookToTable(book);
        }
    }

    private void addBookToTable(Book book) {

        tableModel.addRow(
            new Object[] {
                book.getBookId(),
                book.getIsbn(),
                book.getTitle(),
                book.getAuthors(),
                book.getOriginalPublicationYear(),
                String.format(
                    "%.2f",
                    book.getAverageRating()
                )
            }
        );
    }

    // =========================================================
    // CLEAR TABLE
    // =========================================================

    private void clearTable() {

        tableModel.setRowCount(0);

        statusLabel.setText(
            "Table cleared."
        );
    }

    // =========================================================
    // CHECK IF BOOKS ARE LOADED
    // =========================================================

    private boolean booksLoaded() {

        if (arrayBooks == null
            || arrayBooks.size() == 0) {

            JOptionPane.showMessageDialog(
                this,
                "Please click 'Load Books' first.",
                "No Books Loaded",
                JOptionPane.WARNING_MESSAGE
            );

            return false;
        }

        return true;
    }

    // =========================================================
    // DECORATIVE BOOK PATTERN
    // =========================================================

    private class BookPatternPanel
        extends JPanel {

        public BookPatternPanel() {

            setOpaque(true);
            setBackground(
                new Color(245, 247, 248)
            );
        }

        @Override
        protected void paintComponent(
            Graphics g
        ) {

            super.paintComponent(g);

            Graphics2D g2 =
                (Graphics2D) g.create();

            g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
            );

            // Very subtle decorative book pattern

            Color patternColor =
                new Color(36, 63, 87, 18);

            g2.setColor(patternColor);

            int spacingX = 150;
            int spacingY = 120;

            for (
                int x = -50;
                x < getWidth() + 100;
                x += spacingX
            ) {

                for (
                    int y = 40;
                    y < getHeight() + 100;
                    y += spacingY
                ) {

                    drawBook(
                        g2,
                        x,
                        y
                    );
                }
            }

            g2.dispose();
        }

        private void drawBook(
            Graphics2D g2,
            int x,
            int y
        ) {

            // Book cover

            g2.fillRoundRect(
                x,
                y,
                45,
                65,
                5,
                5
            );

            // Book pages

            g2.setColor(
                new Color(255, 255, 255, 30)
            );

            g2.fillRect(
                x + 6,
                y + 5,
                34,
                55
            );

            // Spine

            g2.setColor(
                new Color(36, 63, 87, 18)
            );

            g2.drawLine(
                x + 8,
                y + 5,
                x + 8,
                y + 60
            );

            // Decorative lines

            g2.drawLine(
                x + 15,
                y + 20,
                x + 35,
                y + 20
            );

            g2.drawLine(
                x + 15,
                y + 27,
                x + 32,
                y + 27
            );

            g2.drawLine(
                x + 15,
                y + 34,
                x + 36,
                y + 34
            );
        }
    }

    // =========================================================
    // MAIN
    // =========================================================

    public static void main(
        String[] args
    ) {

        SwingUtilities.invokeLater(
            () -> {

                BookLibraryGUI gui =
                    new BookLibraryGUI();

                gui.setVisible(true);
            }
        );
    }
}