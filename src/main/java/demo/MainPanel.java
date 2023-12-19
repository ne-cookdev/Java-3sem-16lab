package demo;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.xml.stream.FactoryConfigurationError;
import java.awt.*;
import java.awt.event.*;
import java.util.EventObject;

public class MainPanel {
    private final Item[] items = new Item[15];
    public InternetOrder internetOrder;
    public InternetOrderManager internetOrderManager = new InternetOrderManager();
    public RestaurantOrder restaurantOrder;
    public RestaurantOrderManager restaurantOrderManager = new RestaurantOrderManager();

    String firstName = "Ivan";
    String secondname = "Ivanov";
    String cityName = "Moscow";
    String streetName = "Vernadskogo";
    int buildingNumber = 1;
    int apartmentNumber = 1;
    int zipCode = 123456;
    char buildingLetter = 'A';
    int age = 20;
    Address address = new Address(cityName, streetName, buildingNumber, apartmentNumber, zipCode, buildingLetter);
    public Customer[] customers = new Customer[10];

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainPanel();
            }
        });
    }

    MainPanel() {
        customers[0] = new Customer(firstName, secondname, address, age);
        items[0] = new Dish(DishType.SPAGHETTI, 11);
        items[1] = new Dish(DishType.KHARCHO, 8);
        items[2] = new Dish(DishType.FRIEDRIBS, 17);
        items[3] = new Dish(DishType.KAMCHATKACRABMEAT, 35);
        items[4] = new Dish(DishType.FISHANDCHIPS, 10);
        items[5] = new Dish(DishType.SHRIMPTEMPURA, 1.25f);
        items[6] = new Dish(DishType.SUSHIROLLS, 10);
        items[7] = new Dish(DishType.PIZZA, 15);
        items[8] = new Dish(DishType.CHEESEBURGER, 6);
        items[9] = new Dish(DishType.SHWARMA, 6.4f);
        items[10] = new Drink(DrinkType.COFFEE, 2);
        items[11] = new Drink(DrinkType.SODA, 1.75f);
        items[12] = new Drink(DrinkType.BLACKTEA, 1.75f);
        items[13] = new Drink(DrinkType.BEER, 15);
        items[14] = new Drink(DrinkType.TEQUILA, 8.5f);

        String[][] positions = new String[items.length][3];
        for (int i = 0; i < items.length; i++) {
            positions[i][0] = items[i].getName();
            positions[i][1] = items[i].getDescription();
            positions[i][2] = "$" + items[i].getPrice();
        }

        JFrame jframe = new JFrame("Client Menu");
        jframe.setSize(800, 600);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (int) (screenSize.getWidth() - jframe.getWidth()) / 2;
        int centerY = (int) (screenSize.getHeight() - jframe.getHeight()) / 2;
        jframe.setLocation(centerX, centerY);
        jframe.setVisible(true);
        JPanel tempPanel = new JPanel(new CardLayout());

        JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JPanel panel1 = new JPanel();
        JLabel label1 = new JLabel("Main Menu");
        label1.setFont(new Font("Calibri", Font.BOLD, 30));
        panel1.add(label1);

        JPanel panel2 = new JPanel();
        JButton button1 = new JButton("Make an Order");
        JButton button2 = new JButton("Browse the Menu");
        JButton button3 = new JButton("Cart");
        JButton button4 = new JButton("Exit");

        button1.setPreferredSize(new Dimension(180, 50));
        button2.setPreferredSize(new Dimension(180, 50));
        button3.setPreferredSize(new Dimension(180, 50));
        button4.setPreferredSize(new Dimension(180, 50));
        panel2.add(button1);
        panel2.add(button2);
        panel2.add(button3);
        panel2.add(button4);

        JPanel panel_cust = new JPanel();
        panel_cust.setLayout(new BoxLayout(panel_cust, BoxLayout.Y_AXIS));
        JLabel label_cust = new JLabel("Customer: " + customers[0]);
        JLabel label_addr = new JLabel("Address: " + address);
        panel_cust.add(label_cust);
        panel_cust.add(label_addr);

        JMenuBar menuBar = new JMenuBar();
        JMenu jMenu = new JMenu("Manager / Client");
        menuBar.add(jMenu);
        JMenuItem jMenuItem_1 = new JMenuItem("Manager");
        JMenuItem jMenuItem_2 = new JMenuItem("Client");
        jMenu.add(jMenuItem_1);
        jMenu.addSeparator();
        jMenu.add(jMenuItem_2);
        jframe.setJMenuBar(menuBar);

        JPanel panel3 = new JPanel();
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));
        JLabel label2 = new JLabel("Create new Order?");
        label2.setAlignmentX(Component.CENTER_ALIGNMENT);
        label2.setFont(new Font("Calibri", Font.PLAIN, 30));
        JPanel Btpanel = new JPanel();
        JButton button5 = new JButton("Yes");
        JButton button6 = new JButton("No");
        button5.setPreferredSize(new Dimension(100, 50));
        button6.setPreferredSize(new Dimension(100, 50));
        Btpanel.add(button5);
        Btpanel.add(button6);
        panel3.add(label2);
        panel3.add(Btpanel);
        panel3.setVisible(false);

        JPanel panel4 = new JPanel(new BorderLayout());
        JLabel label3 = new JLabel(
                "In our restaurant, you can taste exquisite dishes from different countries around the world. Our chefs have many years of experience.");
        label3.setHorizontalAlignment(SwingConstants.CENTER);
        label3.setFont(new Font("Calibri", Font.BOLD, 20));
        JPanel pn1 = new JPanel();
        pn1.add(label3);
        JButton button7 = new JButton("Back");
        button7.setPreferredSize(new Dimension(100, 50));
        JPanel pn2 = new JPanel();
        pn2.add(button7);
        String[] columnNames = { "Name", "Description", "Price" };
        JTable table = new JTable(positions, columnNames);
        table.setRowHeight(40);
        for (int i = 0; i < table.getColumnCount(); i++) {
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            Class<?> colClass = table.getColumnClass(i);
            DefaultCellEditor editor = new DefaultCellEditor(new JTextField()) {
                @Override
                public boolean isCellEditable(EventObject e) {
                    return false;
                }
            };
            table.setDefaultEditor(colClass, editor);
        }
        JScrollPane scrollPane = new JScrollPane(table);
        panel4.add(pn1, BorderLayout.NORTH);
        panel4.add(pn2, BorderLayout.SOUTH);
        panel4.add(scrollPane);
        panel4.setVisible(false);

        JPanel panel5 = new JPanel(new BorderLayout());
        JLabel label4 = new JLabel("Menu consists of 15 Items from around the world.");
        label4.setFont(new Font("Calibri", Font.BOLD, 20));
        label4.setOpaque(true);
        label4.setBackground(Color.WHITE);
        JPanel addpanel = new JPanel();
        JButton addbutton = new JButton("Add an item");
        JButton backbutton2 = new JButton("Back");
        addpanel.add(addbutton);
        addpanel.add(backbutton2);
        JTable table1 = new JTable(positions, columnNames);
        table1.setRowHeight(40);
        for (int i = 0; i < table1.getColumnCount(); i++) {
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            table1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            Class<?> colClass = table1.getColumnClass(i);
            DefaultCellEditor editor = new DefaultCellEditor(new JTextField()) {
                @Override
                public boolean isCellEditable(EventObject e) {
                    return false;
                }
            };
            table1.setDefaultEditor(colClass, editor);
        }
        JScrollPane scrollPane1 = new JScrollPane(table1);
        panel5.add(label4, BorderLayout.NORTH);
        panel5.add(scrollPane1);
        panel5.add(addpanel, BorderLayout.SOUTH);
        panel5.setVisible(false);

        JPanel panel6 = new JPanel(new BorderLayout());
        JPanel emptyPanel = new JPanel(new FlowLayout());
        JLabel label5 = new JLabel("Cart is Empty");
        emptyPanel.add(label5);
        emptyPanel.setVisible(false);
        label5.setFont(new Font("Calibri", Font.BOLD, 30));
        JPanel backpanel1 = new JPanel(new FlowLayout());
        JButton backbutton1 = new JButton("Back");
        JButton button8 = new JButton("Pay for the Order");
        JButton button9 = new JButton("Delete the Order");
        backpanel1.add(backbutton1);
        backpanel1.add(button8);
        backpanel1.add(button9);
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setVisible(false);
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn(columnNames[0]);
        model.addColumn(columnNames[1]);
        model.addColumn(columnNames[2]);
        JTable table2 = new JTable(model);
        table2.setRowHeight(40);
        JScrollPane scrollPane2 = new JScrollPane(table2);
        panel6.add(backpanel1, BorderLayout.SOUTH);
        tablePanel.add(scrollPane2);
        panel6.add(emptyPanel, BorderLayout.NORTH);
        panel6.add(tablePanel);
        panel6.setVisible(false);

        addbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table1.getSelectedRow();
                if (selectedRow >= 0) {
                    Object[] rowData = new Object[3];
                    rowData[0] = table1.getValueAt(selectedRow, 0);
                    rowData[1] = table1.getValueAt(selectedRow, 1);
                    rowData[2] = table1.getValueAt(selectedRow, 2);
                    internetOrder.add(items[selectedRow]);
                    model.addRow(rowData);
                }
            }
        });

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel3.setVisible(true);
                mainPanel.setVisible(false);
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel4.setVisible(true);
                mainPanel.setVisible(false);
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel6.setVisible(true);
                mainPanel.setVisible(false);
                if (internetOrder == null || internetOrder.getOrderCount() == 0) {
                    emptyPanel.setVisible(true);
                    tablePanel.setVisible(false);
                    button8.setEnabled(false); // Запретить нажатие кнопки
                    button9.setEnabled(false);

                } else {
                    emptyPanel.setVisible(false);
                    tablePanel.setVisible(true);
                    for (int i = 0; i < table2.getColumnCount(); i++) {
                        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
                        table2.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                        Class<?> colClass = table2.getColumnClass(i);
                        DefaultCellEditor editor = new DefaultCellEditor(new JTextField()) {
                            @Override
                            public boolean isCellEditable(EventObject e) {
                                return false;
                            }
                        };
                        table2.setDefaultEditor(colClass, editor);
                    }
                }
            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Exit");
                frame.setSize(300, 150); // Установка размеров окна
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                int centerX = (int) (screenSize.getWidth() - frame.getWidth()) / 2;
                int centerY = (int) (screenSize.getHeight() - frame.getHeight()) / 2;
                frame.setLocation(centerX, centerY);

                frame.setVisible(true); // Делает видимым само окно
                JLabel label = new JLabel("Are you sure?");
                label.setFont(new Font("Calibri", Font.PLAIN, 25));
                frame.add(label, BorderLayout.NORTH);
                label.setHorizontalAlignment(SwingConstants.CENTER);
                JPanel panel = new JPanel(new FlowLayout());
                JButton button1 = new JButton("Yes");
                JButton button2 = new JButton("No");
                panel.add(button1);
                panel.add(button2);
                frame.add(panel);
                button1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.exit(0);
                    }
                });

                button2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.dispose();
                    }
                });

            }
        });

        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button8.setEnabled(true);
                button9.setEnabled(true);
                panel5.setVisible(true);
                panel3.setVisible(false);
                if (internetOrder == null) {
                    internetOrder = new InternetOrder();
                    try {
                        internetOrderManager.addOrder(customers[0], internetOrder);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel3.setVisible(false);
                mainPanel.setVisible(true);
            }
        });

        button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel4.setVisible(false);
                mainPanel.setVisible(true);
            }
        });

        button8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame1 = new JFrame("Make Order");
                frame1.setSize(700, 350);
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                int centerX = (int) (screenSize.getWidth() - frame1.getWidth()) / 2;
                int centerY = (int) (screenSize.getHeight() - frame1.getHeight()) / 2;
                frame1.setLocation(centerX, centerY);
                frame1.setVisible(true);

                JPanel mainPN = new JPanel();
                JPanel mainPanel1 = new JPanel(new BorderLayout());
                JLabel label1 = new JLabel("Total Price: $" + internetOrder.getTotalPrice());
                label1.setFont(new Font("Calibri", Font.ITALIC, 30));
                JPanel panel1 = new JPanel(new FlowLayout());
                JButton button1 = new JButton("Pay");
                JButton button2 = new JButton("Close");
                button1.setPreferredSize(new Dimension(150, 75));
                button2.setPreferredSize(new Dimension(150, 75));

                JPanel panel2 = new JPanel();
                JLabel label3 = new JLabel();
                panel2.add(label3);
                panel2.setVisible(false);

                if (internetOrder == null || internetOrder.getOrderCount() == 0) {
                    label3.setText("The order has not been completed");
                    mainPanel1.setVisible(false);
                    panel2.setVisible(true);
                }
                label3.setFont(new Font("Calibri", Font.BOLD, 30));
                button1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        label3.setText("Paid");
                        DefaultTableModel model1 = (DefaultTableModel) table2.getModel();
                        model1.setRowCount(0);
                        tablePanel.setVisible(false);
                        emptyPanel.setVisible(true);
                        button8.setEnabled(false);
                        button9.setEnabled(false);
                        mainPanel1.setVisible(false);
                        panel2.setVisible(true);
                        internetOrder = null;
                    }
                });

                button2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame1.dispose();
                    }
                });

                panel1.add(button1);
                panel1.add(button2);
                mainPanel1.add(label1, BorderLayout.NORTH);
                mainPanel1.add(panel1, BorderLayout.CENTER);
                mainPN.add(mainPanel1);
                mainPN.add(panel2);
                frame1.add(mainPN);
            }
        });

        button9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame2 = new JFrame("Deletion");
                frame2.setSize(500, 200);
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                int centerX = (int) (screenSize.getWidth() - frame2.getWidth()) / 2;
                int centerY = (int) (screenSize.getHeight() - frame2.getHeight()) / 2;
                frame2.setLocation(centerX, centerY);
                frame2.setVisible(true);

                JPanel mainpanel2 = new JPanel(new FlowLayout());
                JLabel label1 = new JLabel("Order Deleted");
                label1.setText((internetOrder == null) ? "The Order is not formed" : "Successfully Deleted");
                label1.setFont(new Font("Calibri", Font.BOLD, 20));
                DefaultTableModel model1 = (DefaultTableModel) table2.getModel();
                model1.setRowCount(0);
                internetOrder = null;
                tablePanel.setVisible(false);
                emptyPanel.setVisible(true);
                button8.setEnabled(false);
                button9.setEnabled(false);
                mainpanel2.add(label1);
                frame2.add(mainpanel2);
            }
        });

        backbutton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel6.setVisible(false);
                mainPanel.setVisible(true);
            }
        });

        backbutton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel5.setVisible(false);
                mainPanel.setVisible(true);
            }
        });

        jMenuItem_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame1 = new JFrame("Restaurant Manager");
                frame1.setSize(700, 350);
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                int centerX = (int) (screenSize.getWidth() - frame1.getWidth()) / 2;
                int centerY = (int) (screenSize.getHeight() - frame1.getHeight()) / 2;
                frame1.setLocation(centerX, centerY);
                frame1.setVisible(true);
                JPanel tempPanel1 = new JPanel(new CardLayout());

                JPanel mainPanel1 = new JPanel(new FlowLayout());

                JPanel panel1 = new JPanel();
                JLabel label1 = new JLabel("Insert Password: ");
                label1.setFont(new Font("Arial Black", Font.BOLD, 40));
                panel1.add(label1);

                JPanel panel2 = new JPanel();
                JPasswordField passwordField = new JPasswordField(25);
                passwordField.setPreferredSize(new Dimension(1000, 40));
                JButton button1 = new JButton("Confirm");
                button1.setPreferredSize(new Dimension(150, 40));
                panel2.add(passwordField);
                panel2.add(button1);

                JPanel panel3 = new JPanel();
                panel3.add(new JLabel("Incorrect Password!"));
                panel3.setVisible(false);
                JPanel panel4 = new JPanel();
                panel4.add(new JLabel("Correct Password!"));
                panel4.setVisible(false);

                JPanel panel5 = new JPanel(new FlowLayout());
                JPanel panel6 = new JPanel();
                JPanel panel7 = new JPanel();
                JLabel label2 = new JLabel("Manager Catalog");
                label2.setFont(new Font("Bahnschrift", Font.BOLD, 30));
                JButton button2 = new JButton("<html>Amount of<br>online orders</html>");
                JButton button3 = new JButton("<html>Total amount of<br>online orders</html>");
                JButton button4 = new JButton("<html>Show<br>all online orders</html>");
                JButton button5 = new JButton("<html>Delete all<br>online orders</html>");
                JButton button6 = new JButton("<html>Add new<br>restaurant order</html>");
                JButton button7 = new JButton("<html>Amount of<br>restaurant orders</html>");
                JButton button8 = new JButton("<html>Total amount of<br>restaurant orders</html>");
                JButton button9 = new JButton("<html>Delete all<br>restaurant orders</html>");
                button2.setPreferredSize(new Dimension(150, 50));
                button3.setPreferredSize(new Dimension(150, 50));
                button4.setPreferredSize(new Dimension(150, 50));
                button5.setPreferredSize(new Dimension(150, 50));
                button6.setPreferredSize(new Dimension(150, 50));
                button7.setPreferredSize(new Dimension(150, 50));
                button8.setPreferredSize(new Dimension(150, 50));
                button9.setPreferredSize(new Dimension(150, 50));
                panel5.add(label2);
                panel6.add(button2);
                panel6.add(button3);
                panel6.add(button4);
                panel6.add(button5);
                panel7.add(button6);
                panel7.add(button7);
                panel7.add(button8);
                panel7.add(button9);
                panel5.add(panel6);
                panel5.add(panel7);

                button1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        button5.setEnabled(true);
                        char[] password = passwordField.getPassword();
                        String enteredPassword = new String(password);
                        if (enteredPassword.equals("12")) {
                            panel4.setVisible(true);
                            panel3.setVisible(false);
                            CardLayout cardLayout = (CardLayout) tempPanel1.getLayout();
                            cardLayout.show(tempPanel1, "Panel5");
                        } else {
                            panel3.setVisible(true);
                            panel4.setVisible(false);
                        }
                        passwordField.setText("");
                    }
                });

                JPanel panel_1 = new JPanel();
                panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
                JLabel label_1 = new JLabel();
                label_1.setFont(new Font("Calibri", Font.BOLD, 30));
                JButton button_1 = new JButton("Back");
                panel_1.add(label_1);
                panel_1.add(button_1);
                panel_1.setVisible(false);

                JPanel panel_2 = new JPanel();
                panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
                JLabel label_2 = new JLabel();
                label_2.setFont(new Font("Calibri", Font.BOLD, 30));
                JButton button_2 = new JButton("Back");
                panel_2.add(label_2);
                panel_2.add(button_2);
                panel_2.setVisible(false);

                JPanel panel_3 = new JPanel(new BorderLayout());
                JLabel label_3 = new JLabel();
                label_3.setHorizontalAlignment(SwingConstants.CENTER);
                label_3.setFont(new Font("Calibri", Font.BOLD, 30));
                JButton button_3 = new JButton("Back");
                String[] columnNames_1 = {"Customer", "Order"};
                String[][] orders_table = new String[customers.length][2];
                for (int i = 0; i < customers.length; i++) {
                    if (customers[i] != null){
                        orders_table[i][0] = customers[i].getFirstName() + " " + customers[i].getSecondName();
                        if (internetOrderManager.InternetOrdersAmount() != 0) {
                            orders_table[i][1] = internetOrderManager.getOrder(customers[i]).showItems();
                        }
                        else{
                            orders_table[i][1] = "";
                        }
                    }
                }

                JTable table_1 = new JTable(orders_table, columnNames_1);
                table_1.setRowHeight(30);
                table_1.getColumnModel().getColumn(0).setPreferredWidth(150);
                table_1.getColumnModel().getColumn(1).setPreferredWidth(450);
                for (int i = 0; i < table_1.getColumnCount(); i++) {
                    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                    table_1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                    Class<?> colClass = table_1.getColumnClass(i);
                    DefaultCellEditor editor = new DefaultCellEditor(new JTextField()) {
                        @Override
                        public boolean isCellEditable(EventObject e) {
                            return false;
                        }
                    };
                    table_1.setDefaultEditor(colClass, editor);
                }
                JScrollPane scrollPane_1 = new JScrollPane(table_1);
                scrollPane_1.setPreferredSize(new Dimension(600, 200));
                JPanel pn_3 = new JPanel();
                pn_3.add(button_3);
                JPanel pn_4 = new JPanel();
                pn_4.add(button_3);
                panel_3.add(label_3, BorderLayout.NORTH);
                panel_3.add(pn_4, BorderLayout.SOUTH);
                panel_3.add(scrollPane_1);
                panel_3.setVisible(false);

                button2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        panel_1.setVisible(true);
                        panel4.setVisible(false);
                        panel5.setVisible(false);
                        panel2.setVisible(false);
                        panel1.setVisible(false);
                        if (internetOrderManager.InternetOrdersAmount() == 0) {
                            label_1.setText("No Orders");
                        } else {
                            label_1.setText("Amount of internet orders: " + internetOrderManager.getInternetOrders().length);
                        }
                    }
                });

                button3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        panel_2.setVisible(true);
                        panel4.setVisible(false);
                        panel5.setVisible(false);
                        panel2.setVisible(false);
                        panel1.setVisible(false);
                        if (internetOrderManager.InternetOrdersAmount() == 0) {
                            label_2.setText("No Orders");
                        } else {
                            float _temp = internetOrderManager.getTotalOrdersPrice();
                            label_2.setText("Total Price of internet orders: " + _temp);
                        }
                    }
                });

                button4.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        panel_3.setVisible(true);
                        panel4.setVisible(false);
                        panel5.setVisible(false);
                        panel2.setVisible(false);
                        panel1.setVisible(false);
                        if (internetOrderManager.InternetOrdersAmount() == 0) {
                            label_3.setText("No Orders");
                        } else {
                            label_3.setText("Orders");
                        }
                    }
                });

                button5.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JFrame fr = new JFrame("Deletion Of Orders");
                        fr.setSize(450, 270);
                        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                        int centerX = (int) (screenSize.getWidth() - fr.getWidth()) / 2;
                        int centerY = (int) (screenSize.getHeight() - fr.getHeight()) / 2;
                        fr.setLocation(centerX, centerY);
                        fr.setVisible(true);

                        JPanel pn1 = new JPanel(new FlowLayout());
                        JLabel lb1 = new JLabel("Are you sure?");
                        lb1.setFont(new Font("Calibri", Font.BOLD, 30));
                        JLabel lb2 = new JLabel();
                        lb2.setFont(new Font("Calibri", Font.BOLD, 30));
                        JPanel pn2 = new JPanel();
                        JButton bt1 = new JButton("Yes");
                        bt1.setPreferredSize(new Dimension(125, 40));
                        JButton bt2 = new JButton("No");
                        bt2.setPreferredSize(new Dimension(125, 40));
                        pn1.add(lb1);
                        pn1.add(lb2);
                        pn2.add(bt1);
                        pn2.add(bt2);
                        pn1.add(pn2);
                        bt1.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                lb2.setText("Deleted");
                                lb1.setText("");
                                button5.setEnabled(false);
                                internetOrder = null;
                                internetOrderManager = new InternetOrderManager();
                                pn2.setVisible(false);
                                for (int i = 0; i < customers.length; i++) {
                                    if (customers[i] != null){
                                        orders_table[i][0] = customers[i].getFirstName() + " " + customers[i].getSecondName();
                                        orders_table[i][1] = "";
                                    }
                                }

                                for (int i = 0; i < table_1.getColumnCount(); i++) {
                                    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                                    table_1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                                    Class<?> colClass = table_1.getColumnClass(i);
                                    DefaultCellEditor editor = new DefaultCellEditor(new JTextField()) {
                                        @Override
                                        public boolean isCellEditable(EventObject e) {
                                            return false;
                                        }
                                    };
                                    table_1.setDefaultEditor(colClass, editor);
                                }
                            }
                        });

                        bt2.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                fr.dispose();
                            }
                        });

                        fr.add(pn1);
                    }
                });

                JPanel panel_6 = new JPanel(new BorderLayout());
                JLabel label_6 = new JLabel("Menu consists of 15 Items from around the world.");
                label_6.setFont(new Font("Calibri", Font.BOLD, 20));
                JPanel addpanel_6 = new JPanel();
                JButton addbutton_6 = new JButton("Add an item");
                JButton backbutton_6 = new JButton("Back");
                JLabel label_62 = new JLabel("Choose table: ");

                String[][] tables = new String[10][1];
                for (int i = 0; i < tables.length; i++) tables[i][0] = String.valueOf(i);
                JTable table_tables = new JTable(tables, new String[]{"Table"});
                table_tables.setRowHeight(20);
                for (int i = 0; i < table_tables.getColumnCount(); i++) {
                    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                    centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
                    table_tables.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                    Class<?> colClass = table_tables.getColumnClass(i);
                    DefaultCellEditor editor = new DefaultCellEditor(new JTextField()) {
                        @Override
                        public boolean isCellEditable(EventObject e) {
                            return false;
                        }
                    };
                    table_tables.setDefaultEditor(colClass, editor);
                }
                JScrollPane scrollPane_62 = new JScrollPane(table_tables);
                scrollPane_62.setPreferredSize(new Dimension(100, 40));
                JButton addbutton_62 = new JButton("Create");
                JLabel label_63 = new JLabel();
                addpanel_6.add(addbutton_6);
                addpanel_6.add(backbutton_6);
                addpanel_6.add(label_62);
                addpanel_6.add(scrollPane_62);
                addpanel_6.add(addbutton_62);
                addpanel_6.add(label_63);
                label_63.setVisible(false);
                JTable table_6 = new JTable(positions, columnNames);
                table_6.setRowHeight(40);
                table_6.getColumnModel().getColumn(0).setPreferredWidth(200);
                table_6.getColumnModel().getColumn(1).setPreferredWidth(200);
                table_6.getColumnModel().getColumn(2).setPreferredWidth(200);
                for (int i = 0; i < table_6.getColumnCount(); i++) {
                    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                    centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
                    table_6.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                    Class<?> colClass = table_6.getColumnClass(i);
                    DefaultCellEditor editor = new DefaultCellEditor(new JTextField()) {
                        @Override
                        public boolean isCellEditable(EventObject e) {
                            return false;
                        }
                    };
                    table_6.setDefaultEditor(colClass, editor);
                }
                JScrollPane scrollPane_6 = new JScrollPane(table_6);
                scrollPane_6.setPreferredSize(new Dimension(600, 200));
                panel_6.add(label_6, BorderLayout.NORTH);
                panel_6.add(scrollPane_6);
                panel_6.add(addpanel_6, BorderLayout.SOUTH);
                panel_6.setVisible(false);

                button6.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        button9.setEnabled(true);
                        panel_6.setVisible(true);
                        panel4.setVisible(false);
                        panel5.setVisible(false);
                        panel2.setVisible(false);
                        panel1.setVisible(false);
                        restaurantOrder = new RestaurantOrder();
                    }
                });

                addbutton_6.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int selectedRow = table_6.getSelectedRow();
                        if (selectedRow >= 0) {
                            restaurantOrder.add(items[selectedRow]);
                        }
                    }
                });

                addbutton_62.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            int selectedTable = table_tables.getSelectedRow();
                            if(selectedTable >= 0 && selectedTable <= 9){
                                restaurantOrderManager.addOrder(selectedTable, restaurantOrder);
                                label_63.setText("Order created!");
                                label_63.setVisible(true);
                            }
                            else{
                                label_63.setText("Choose correct table!");
                                label_63.setVisible(true);
                            }
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });

                backbutton_6.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        panel_6.setVisible(false);
                        label_63.setVisible(false);
                        CardLayout cardLayout = (CardLayout) tempPanel1.getLayout();
                        cardLayout.show(tempPanel1, "Panel5");
                        panel5.setVisible(true);
                    }
                });

                JPanel panel_7 = new JPanel();
                panel_7.setLayout(new BoxLayout(panel_7, BoxLayout.Y_AXIS));
                JLabel label_7 = new JLabel();
                label_7.setFont(new Font("Calibri", Font.BOLD, 30));
                JButton button_7 = new JButton("Back");
                panel_7.add(label_7);
                panel_7.add(button_7);
                panel_7.setVisible(false);

                button7.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        panel_7.setVisible(true);
                        panel4.setVisible(false);
                        panel5.setVisible(false);
                        panel2.setVisible(false);
                        panel1.setVisible(false);
                        if (restaurantOrderManager.ordersCount() == 0) {
                            label_7.setText("No Orders");
                        } else {
                            label_7.setText("Amount of restaurant orders: " + restaurantOrderManager.ordersCount());
                        }
                    }
                });

                button_7.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        panel_7.setVisible(false);
                        CardLayout cardLayout = (CardLayout) tempPanel1.getLayout();
                        cardLayout.show(tempPanel1, "Panel5");
                        panel5.setVisible(true);
                    }
                });

                JPanel panel_8 = new JPanel();
                panel_8.setLayout(new BoxLayout(panel_8, BoxLayout.Y_AXIS));
                JLabel label_8 = new JLabel();
                label_8.setFont(new Font("Calibri", Font.BOLD, 30));
                JButton button_8 = new JButton("Back");
                panel_8.add(label_8);
                panel_8.add(button_8);
                panel_8.setVisible(false);

                button8.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        panel_8.setVisible(true);
                        panel4.setVisible(false);
                        panel5.setVisible(false);
                        panel2.setVisible(false);
                        panel1.setVisible(false);
                        if (restaurantOrderManager.ordersCount() == 0) {
                            label_8.setText("No Orders");
                        } else {
                            float _temp = restaurantOrderManager.getTotalOrdersPrice();
                            label_8.setText("Total Price of restaurant orders: " + _temp);
                        }
                    }
                });

                button_8.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        panel_8.setVisible(false);
                        CardLayout cardLayout = (CardLayout) tempPanel1.getLayout();
                        cardLayout.show(tempPanel1, "Panel5");
                        panel5.setVisible(true);
                    }
                });

                button9.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JFrame fr = new JFrame("Deletion Of Orders");
                        fr.setSize(450, 270);
                        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                        int centerX = (int) (screenSize.getWidth() - fr.getWidth()) / 2;
                        int centerY = (int) (screenSize.getHeight() - fr.getHeight()) / 2;
                        fr.setLocation(centerX, centerY);
                        fr.setVisible(true);

                        JPanel pn1 = new JPanel(new FlowLayout());
                        JLabel lb1 = new JLabel("Are you sure?");
                        lb1.setFont(new Font("Calibri", Font.BOLD, 30));
                        JLabel lb2 = new JLabel();
                        lb2.setFont(new Font("Calibri", Font.BOLD, 30));
                        JPanel pn2 = new JPanel();
                        JButton bt1 = new JButton("Yes");
                        bt1.setPreferredSize(new Dimension(125, 40));
                        JButton bt2 = new JButton("No");
                        bt2.setPreferredSize(new Dimension(125, 40));
                        pn1.add(lb1);
                        pn1.add(lb2);
                        pn2.add(bt1);
                        pn2.add(bt2);
                        pn1.add(pn2);
                        bt1.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                lb2.setText("Deleted");
                                lb1.setText("");
                                button9.setEnabled(false);
                                restaurantOrder = null;
                                restaurantOrderManager = new RestaurantOrderManager();
                                pn2.setVisible(false);
                            }
                        });

                        bt2.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                fr.dispose();
                            }
                        });

                        fr.add(pn1);
                    }
                });

                button_1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        panel_1.setVisible(false);
                        CardLayout cardLayout = (CardLayout) tempPanel1.getLayout();
                        cardLayout.show(tempPanel1, "Panel5");
                        panel5.setVisible(true);
                    }
                });

                button_2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        panel_2.setVisible(false);
                        CardLayout cardLayout = (CardLayout) tempPanel1.getLayout();
                        cardLayout.show(tempPanel1, "Panel5");
                        panel5.setVisible(true);
                    }
                });

                button_3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        panel_3.setVisible(false);
                        CardLayout cardLayout = (CardLayout) tempPanel1.getLayout();
                        cardLayout.show(tempPanel1, "Panel5");
                        panel5.setVisible(true);
                    }
                });

                mainPanel1.add(panel1);
                mainPanel1.add(panel2);
                mainPanel1.add(panel3);
                mainPanel1.add(panel4);
                mainPanel1.add(panel_1);
                mainPanel1.add(panel_2);
                mainPanel1.add(panel_3);
                mainPanel1.add(panel_6);
                mainPanel1.add(panel_7);
                mainPanel1.add(panel_8);

                tempPanel1.add(mainPanel1);
                tempPanel1.add(panel5, "Panel5");
                frame1.add(tempPanel1);
            }
        });

        jMenuItem_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) tempPanel.getLayout();
                cardLayout.show(tempPanel, "MainPanel");
            }
        });

        mainPanel.add(panel1);
        mainPanel.add(panel2);
        mainPanel.add(panel_cust);
        tempPanel.add(mainPanel, "MainPanel");
        tempPanel.add(panel3);
        tempPanel.add(panel4);
        tempPanel.add(panel5);
        tempPanel.add(panel6);
        jframe.add(tempPanel, BorderLayout.CENTER);
    }
}
