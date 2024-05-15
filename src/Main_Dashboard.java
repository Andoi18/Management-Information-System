
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */



/**
 *
 * @author Drew
 */
public class Main_Dashboard extends javax.swing.JFrame {

    /**
     * Creates new form Main_Dashboard
     */
    
    //LOCAL VARIABLES
    int userID;
    int active;
    boolean isAdmin;
    boolean accSetup;
    DefaultTableModel myLogsTableModel;
    private RowSorter<? extends TableModel> originalSorter;
    
    ButtonGroup availabilityBtnGroup = new ButtonGroup();
    
    db_connection db = new db_connection();
    GeneralFnc fnc = new GeneralFnc();
    
    public Main_Dashboard(int uID) {
        initComponents();
        
        //db_connection conn = new db_connection();
        //conn.testConnection();
        userID = uID;
        isAdmin = db.getUserData(userID, "users", "admin").toString().equals("1");
        accSetup = db.getUserData(userID, "users", "accSetup").toString().equals("1");
        changeUsernameTf.setText(db.getUserData(userID, "users", "username").toString());
        tabbedPaneBehavior();
        if(isAdmin){
            adminPanelSetup();
        }
        
        if(!isAdmin && accSetup){
            active = Integer.parseInt(db.getUserData(userID, "users", "active").toString());
            userLbl.setText("Welcome " + db.getUserData(userID, "userdata", "firstName"));
            setTimeLog();
            availabilitySetup();
            myLogsSetup();
        }
        
    }
    private void myLogsSetup(){
        myLogsTableModel = db.getLogsTableModel("loggerID", Integer.toString(userID), false);
        userLogs_tbl.setModel(myLogsTableModel);
        ArrayList<String> columns = new ArrayList<String>();
        for(int i = 0; i < myLogsTableModel.getColumnCount(); i++){
            columns.add(myLogsTableModel.getColumnName(i));
        }
        myLogs_cb.setModel(new javax.swing.DefaultComboBoxModel(columns.toArray()));
    } 
    private void adminPanelSetup(){
        DefaultTableModel statusModel = db.getStatusModel(); 
        adminEmployeeStatus_tbl.setModel(statusModel);
        ArrayList<String> columnsStatus = new ArrayList<String>();
        for(int i = 0; i < statusModel.getColumnCount(); i++){
            columnsStatus.add(statusModel.getColumnName(i));
        }
        rowCountStatus_lbl.setText("Row/s Displayed: " + statusModel.getRowCount());
        adminEmployeeStatus_cb.setModel(new javax.swing.DefaultComboBoxModel(columnsStatus.toArray()));
        
        DefaultTableModel logsModel = db.getLogsTableModel("", "", isAdmin); 
        adminEmployeeLogs_tbl.setModel(logsModel);
        ArrayList<String> columnsLogs = new ArrayList<String>();
        for(int i = 0; i < logsModel.getColumnCount(); i++){
            columnsLogs.add(logsModel.getColumnName(i));
        }
        rowCountLogs_lbl.setText("Row/s Displayed: " + logsModel.getRowCount());
        adminEmployeeLogs_cb.setModel(new javax.swing.DefaultComboBoxModel(columnsLogs.toArray()));
    }
    
    private void availabilitySetup(){
        availabilityBtnGroup.add(available_rb);
        availabilityBtnGroup.add(busy_rb);
        //SETS AVAILABITY RADIO BUTTON
        if(db.getUserData(userID, "users", "availability").equals("0")){
            available_rb.setSelected(true);
            busy_rb.setSelected(false);
        }
        else{
            available_rb.setSelected(false);
            busy_rb.setSelected(true);
        }
        
        String message =  db.getUserData(userID, "users", "statusMessage").toString();
        if(!message.isEmpty()){
            statusMessage_textArea.setText(message);
        }
    }
    
    private void setTimeLog(){
        if(active == 1){
            timeLog_pnl.setBackground(Color.red);
            updateTimeIn_btn.setText("Time-in");
        }
        else{
            timeLog_pnl.setBackground(Color.GREEN);
            updateTimeIn_btn.setText("Time-out");
        }
        
    }
    
    private void tabbedPaneBehavior(){
        int accSetup;
        accSetup = Integer.parseInt(String.valueOf(db.getUserData(userID, "users" ,"accSetup")));
        
        
        //ADMIN PARAMS
        if(isAdmin){
            mainDashboard_tabbedPane.remove(employee_pnl);
            mainDashboard_tabbedPane.remove(accountSetup_pnl);
            mainDashboard_tabbedPane.remove(mylogs_pnl);
            return;
        }
        
        //EMPLOYEE PARMAS
        if(accSetup != 1){
            mainDashboard_tabbedPane.remove(accountSetup_pnl);
            mainDashboard_tabbedPane.remove(admin_pnl);
            return;
        }
        else{
            mainDashboard_tabbedPane.remove(employee_pnl);
            mainDashboard_tabbedPane.remove(admin_pnl);
            mainDashboard_tabbedPane.remove(settings_pnl);
            mainDashboard_tabbedPane.remove(mylogs_pnl);
            return;
        }
    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        mainDashboard_tabbedPane = new javax.swing.JTabbedPane();
        employee_pnl = new javax.swing.JPanel();
        timeLog_pnl = new javax.swing.JPanel();
        updateTimeIn_btn = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        timeLabel1 = new Components.TimeLabel();
        employee_status_pnl = new javax.swing.JPanel();
        status_lbl = new javax.swing.JLabel();
        available_rb = new javax.swing.JRadioButton();
        busy_rb = new javax.swing.JRadioButton();
        status_scrollpane = new javax.swing.JScrollPane();
        statusMessage_textArea = new javax.swing.JTextArea();
        statusUpdate_btn = new javax.swing.JButton();
        statusPnl_status_lbl = new javax.swing.JLabel();
        userLbl = new javax.swing.JLabel();
        mylogs_pnl = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        myLogsSearch_tf = new javax.swing.JTextField();
        myLogsSearch_btn = new javax.swing.JButton();
        myLogs_cb = new javax.swing.JComboBox<>();
        myLogsRefresh_btn = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        userLogs_tbl = new javax.swing.JTable();
        accountSetup_pnl = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        setup_lastNameF = new javax.swing.JTextField();
        panel123 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        setup_firstNameF = new javax.swing.JTextField();
        jPanel20 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        setup_middleNameF = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        setup_genderCb = new javax.swing.JComboBox<>();
        jPanel22 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        setup_birthdateDp = new com.toedter.calendar.JDateChooser();
        jPanel27 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        setup_addressF = new javax.swing.JTextField();
        jPanel17 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        setup_contactF = new javax.swing.JTextField();
        jPanel25 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        setup_emailF = new javax.swing.JTextField();
        admin_pnl = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        adminEmployeeStatus_tbl = new javax.swing.JTable();
        jPanel44 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel45 = new javax.swing.JPanel();
        adminEmployeeStatus_tf = new javax.swing.JTextField();
        adminEmployeeStatus_cb = new javax.swing.JComboBox<>();
        adminEmployeeStatusEnter_btn = new javax.swing.JButton();
        rowCountStatus_lbl = new javax.swing.JLabel();
        adminEmployeeStatusRefresh_btn = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        displayStatusSelection_ta = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        adminEmployeeLogs_tbl = new javax.swing.JTable();
        jPanel46 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel50 = new javax.swing.JPanel();
        adminEmployeeLogs_tf = new javax.swing.JTextField();
        adminEmployeeLogs_cb = new javax.swing.JComboBox<>();
        adminEmployeeStatusLogs_btn = new javax.swing.JButton();
        rowCountLogs_lbl = new javax.swing.JLabel();
        adminEmployeeLogsRefresh_btn = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        displayLogsSelection_ta = new javax.swing.JTextArea();
        settings_pnl = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel7 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        changeUsernameTf = new javax.swing.JTextField();
        updateUsername_btn = new javax.swing.JButton();
        jPanel29 = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel31 = new javax.swing.JPanel();
        jPanel32 = new javax.swing.JPanel();
        currentPass_pf = new javax.swing.JPasswordField();
        currentPass_cb = new javax.swing.JCheckBox();
        jPanel33 = new javax.swing.JPanel();
        newPass_pf = new javax.swing.JPasswordField();
        newPass_cb = new javax.swing.JCheckBox();
        confirmPass_pf = new javax.swing.JPasswordField();
        jButton10 = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        deleteAccount_btn = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jButton12 = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Management Information System");
        setResizable(false);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.Y_AXIS));

        jPanel5.setBackground(new java.awt.Color(17, 45, 78));
        jPanel5.setOpaque(false);
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        mainDashboard_tabbedPane.setBackground(new java.awt.Color(63, 114, 175));
        mainDashboard_tabbedPane.setForeground(new java.awt.Color(255, 255, 255));
        mainDashboard_tabbedPane.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        mainDashboard_tabbedPane.setToolTipText("");
        mainDashboard_tabbedPane.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        employee_pnl.setBackground(new java.awt.Color(219, 226, 239));
        employee_pnl.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        timeLog_pnl.setBackground(new java.awt.Color(249, 247, 247));
        timeLog_pnl.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(17, 45, 78), 10, true));
        timeLog_pnl.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        updateTimeIn_btn.setBackground(new java.awt.Color(63, 114, 175));
        updateTimeIn_btn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        updateTimeIn_btn.setForeground(new java.awt.Color(255, 255, 255));
        updateTimeIn_btn.setText("Time-in");
        updateTimeIn_btn.setToolTipText("");
        updateTimeIn_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateTimeIn_btnActionPerformed(evt);
            }
        });
        timeLog_pnl.add(updateTimeIn_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, 130, 30));

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Time-in / Time-out");
        timeLog_pnl.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 170, 32));

        timeLabel1.setForeground(new java.awt.Color(0, 0, 0));
        timeLog_pnl.add(timeLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, -1));

        employee_pnl.add(timeLog_pnl, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, 250, 320));

        employee_status_pnl.setBackground(new java.awt.Color(249, 247, 247));
        employee_status_pnl.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(17, 45, 78), 10, true));

        status_lbl.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        status_lbl.setForeground(new java.awt.Color(0, 0, 0));
        status_lbl.setText("My Status");

        available_rb.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        available_rb.setText("Available");
        available_rb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        busy_rb.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        busy_rb.setText("Busy");
        busy_rb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        busy_rb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                busy_rbActionPerformed(evt);
            }
        });

        statusMessage_textArea.setBackground(new java.awt.Color(219, 226, 239));
        statusMessage_textArea.setColumns(20);
        statusMessage_textArea.setRows(5);
        status_scrollpane.setViewportView(statusMessage_textArea);

        statusUpdate_btn.setBackground(new java.awt.Color(63, 114, 175));
        statusUpdate_btn.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        statusUpdate_btn.setForeground(new java.awt.Color(255, 255, 255));
        statusUpdate_btn.setText("Update");
        statusUpdate_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusUpdate_btnActionPerformed(evt);
            }
        });

        statusPnl_status_lbl.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        statusPnl_status_lbl.setForeground(new java.awt.Color(0, 0, 0));
        statusPnl_status_lbl.setText("Current Status Message:");

        javax.swing.GroupLayout employee_status_pnlLayout = new javax.swing.GroupLayout(employee_status_pnl);
        employee_status_pnl.setLayout(employee_status_pnlLayout);
        employee_status_pnlLayout.setHorizontalGroup(
            employee_status_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(employee_status_pnlLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(employee_status_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(employee_status_pnlLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(available_rb, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(busy_rb, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52))
                    .addGroup(employee_status_pnlLayout.createSequentialGroup()
                        .addComponent(statusUpdate_btn)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(employee_status_pnlLayout.createSequentialGroup()
                .addGroup(employee_status_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(employee_status_pnlLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(status_lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(employee_status_pnlLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(employee_status_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(status_scrollpane, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusPnl_status_lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        employee_status_pnlLayout.setVerticalGroup(
            employee_status_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, employee_status_pnlLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(status_lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(employee_status_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(available_rb)
                    .addComponent(busy_rb))
                .addGap(28, 28, 28)
                .addComponent(statusPnl_status_lbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(status_scrollpane, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(statusUpdate_btn)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        employee_pnl.add(employee_status_pnl, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 90, -1, 320));

        userLbl.setFont(new java.awt.Font("Arial CE", 1, 24)); // NOI18N
        userLbl.setForeground(new java.awt.Color(0, 0, 0));
        userLbl.setText("Hello user!");
        employee_pnl.add(userLbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 373, 30));

        mainDashboard_tabbedPane.addTab("My Dashboard", employee_pnl);

        mylogs_pnl.setLayout(new javax.swing.BoxLayout(mylogs_pnl, javax.swing.BoxLayout.Y_AXIS));

        jPanel8.setBackground(new java.awt.Color(63, 114, 175));
        jPanel8.setForeground(new java.awt.Color(255, 255, 255));
        jPanel8.setPreferredSize(new java.awt.Dimension(838, 50));

        jLabel2.setFont(new java.awt.Font("Arial CE MT Black", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("MY LOGS");

        myLogsSearch_btn.setText("Search");
        myLogsSearch_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myLogsSearch_btnActionPerformed(evt);
            }
        });

        myLogs_cb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        myLogsRefresh_btn.setText("Refresh");
        myLogsRefresh_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myLogsRefresh_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 151, Short.MAX_VALUE)
                .addComponent(myLogsSearch_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(myLogsSearch_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(myLogs_cb, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(myLogsRefresh_btn)
                .addGap(10, 10, 10))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(myLogsSearch_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(myLogsSearch_btn)
                    .addComponent(myLogs_cb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(myLogsRefresh_btn))
                .addGap(9, 9, 9))
        );

        mylogs_pnl.add(jPanel8);

        userLogs_tbl.setForeground(new java.awt.Color(0, 0, 0));
        userLogs_tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        userLogs_tbl.setRequestFocusEnabled(false);
        userLogs_tbl.setUpdateSelectionOnSort(false);
        userLogs_tbl.setVerifyInputWhenFocusTarget(false);
        userLogs_tbl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                userLogs_tblKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                userLogs_tblKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(userLogs_tbl);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 905, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE)
        );

        mylogs_pnl.add(jPanel6);

        mainDashboard_tabbedPane.addTab("My Logs", mylogs_pnl);

        accountSetup_pnl.setPreferredSize(new java.awt.Dimension(840, 487));
        accountSetup_pnl.setLayout(new javax.swing.BoxLayout(accountSetup_pnl, javax.swing.BoxLayout.Y_AXIS));

        jPanel15.setBackground(new java.awt.Color(63, 114, 175));

        jLabel12.setBackground(new java.awt.Color(51, 51, 51));
        jLabel12.setFont(new java.awt.Font("Arial CE MT Black", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("ACCOUNT SETUP");

        jButton11.setBackground(new java.awt.Color(51, 204, 0));
        jButton11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton11.setForeground(new java.awt.Color(0, 0, 0));
        jButton11.setText("Confirm");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 557, Short.MAX_VALUE)
                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        accountSetup_pnl.add(jPanel15);

        jPanel11.setBackground(new java.awt.Color(204, 204, 204));
        jPanel11.setPreferredSize(new java.awt.Dimension(836, 120));
        jPanel11.setLayout(new java.awt.GridLayout(1, 3));

        jPanel18.setBackground(new java.awt.Color(249, 247, 247));

        jLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Last Name");

        setup_lastNameF.setBackground(new java.awt.Color(219, 226, 239));
        setup_lastNameF.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        setup_lastNameF.setForeground(new java.awt.Color(249, 247, 247));
        setup_lastNameF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                setup_lastNameFKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(setup_lastNameF, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(setup_lastNameF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        jPanel11.add(jPanel18);

        panel123.setBackground(new java.awt.Color(249, 247, 247));

        jLabel13.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("First Name");

        setup_firstNameF.setBackground(new java.awt.Color(219, 226, 239));
        setup_firstNameF.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        setup_firstNameF.setForeground(new java.awt.Color(0, 0, 0));
        setup_firstNameF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                setup_firstNameFKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout panel123Layout = new javax.swing.GroupLayout(panel123);
        panel123.setLayout(panel123Layout);
        panel123Layout.setHorizontalGroup(
            panel123Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel123Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(panel123Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(setup_firstNameF, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        panel123Layout.setVerticalGroup(
            panel123Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel123Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(setup_firstNameF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        jPanel11.add(panel123);

        jPanel20.setBackground(new java.awt.Color(249, 247, 247));

        jLabel14.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Middle Name");

        setup_middleNameF.setBackground(new java.awt.Color(219, 226, 239));
        setup_middleNameF.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        setup_middleNameF.setForeground(new java.awt.Color(0, 0, 0));
        setup_middleNameF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                setup_middleNameFKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(setup_middleNameF, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(setup_middleNameF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        jPanel11.add(jPanel20);

        accountSetup_pnl.add(jPanel11);

        jPanel16.setBackground(new java.awt.Color(204, 204, 204));
        jPanel16.setPreferredSize(new java.awt.Dimension(836, 120));
        jPanel16.setLayout(new java.awt.GridLayout(1, 3));

        jPanel21.setBackground(new java.awt.Color(249, 247, 247));

        jLabel16.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 16)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Gender");

        setup_genderCb.setBackground(new java.awt.Color(219, 226, 239));
        setup_genderCb.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        setup_genderCb.setForeground(new java.awt.Color(0, 0, 0));
        setup_genderCb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female", "Non-Binary", "Prefer not to say" }));
        setup_genderCb.setPreferredSize(new java.awt.Dimension(73, 22));

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(setup_genderCb, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(203, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(setup_genderCb, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        jPanel16.add(jPanel21);

        jPanel22.setBackground(new java.awt.Color(249, 247, 247));

        jLabel17.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 16)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Birthdate");

        setup_birthdateDp.setBackground(new java.awt.Color(219, 226, 239));
        setup_birthdateDp.setForeground(new java.awt.Color(0, 0, 0));
        setup_birthdateDp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                setup_birthdateDpKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(setup_birthdateDp, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(203, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(setup_birthdateDp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        jPanel16.add(jPanel22);

        accountSetup_pnl.add(jPanel16);

        jPanel27.setBackground(new java.awt.Color(249, 247, 247));
        jPanel27.setPreferredSize(new java.awt.Dimension(836, 120));

        jLabel22.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 16)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Address");

        setup_addressF.setBackground(new java.awt.Color(219, 226, 239));
        setup_addressF.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        setup_addressF.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(setup_addressF, javax.swing.GroupLayout.PREFERRED_SIZE, 844, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(setup_addressF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        accountSetup_pnl.add(jPanel27);

        jPanel17.setBackground(new java.awt.Color(204, 204, 204));
        jPanel17.setPreferredSize(new java.awt.Dimension(836, 120));
        jPanel17.setLayout(new java.awt.GridLayout(1, 3));

        jPanel24.setBackground(new java.awt.Color(249, 247, 247));

        jLabel19.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 16)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Contact Number");

        setup_contactF.setBackground(new java.awt.Color(219, 226, 239));
        setup_contactF.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        setup_contactF.setForeground(new java.awt.Color(0, 0, 0));
        setup_contactF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                setup_contactFKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(setup_contactF, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(203, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(setup_contactF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        jPanel17.add(jPanel24);

        jPanel25.setBackground(new java.awt.Color(249, 247, 247));

        jLabel20.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 16)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Email Address");

        setup_emailF.setBackground(new java.awt.Color(219, 226, 239));
        setup_emailF.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        setup_emailF.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(setup_emailF, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(203, Short.MAX_VALUE))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(setup_emailF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        jPanel17.add(jPanel25);

        accountSetup_pnl.add(jPanel17);

        mainDashboard_tabbedPane.addTab("Account Setup", accountSetup_pnl);

        jTabbedPane1.setBackground(new java.awt.Color(17, 45, 78));
        jTabbedPane1.setForeground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(249, 247, 247));

        adminEmployeeStatus_tbl.setFont(new java.awt.Font("Arial Light", 0, 12)); // NOI18N
        adminEmployeeStatus_tbl.setForeground(new java.awt.Color(0, 0, 0));
        adminEmployeeStatus_tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Name", "EmployeeID", "Status"
            }
        ));
        adminEmployeeStatus_tbl.setRequestFocusEnabled(false);
        adminEmployeeStatus_tbl.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        adminEmployeeStatus_tbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                adminEmployeeStatus_tblMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(adminEmployeeStatus_tbl);

        jPanel44.setBackground(new java.awt.Color(219, 226, 239));
        jPanel44.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Search: ");
        jPanel44.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 86, -1));

        jLabel6.setBackground(new java.awt.Color(0, 0, 0));
        jLabel6.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("EMPLOYEE STATUSES");
        jPanel44.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 240, 30));

        jPanel45.setBackground(new java.awt.Color(219, 226, 239));
        jPanel45.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 20));

        adminEmployeeStatus_tf.setBackground(new java.awt.Color(249, 247, 247));
        adminEmployeeStatus_tf.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        adminEmployeeStatus_tf.setForeground(new java.awt.Color(0, 0, 0));
        adminEmployeeStatus_tf.setPreferredSize(new java.awt.Dimension(260, 30));
        adminEmployeeStatus_tf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminEmployeeStatus_tfActionPerformed(evt);
            }
        });
        jPanel45.add(adminEmployeeStatus_tf);

        adminEmployeeStatus_cb.setBackground(new java.awt.Color(63, 114, 175));
        adminEmployeeStatus_cb.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        adminEmployeeStatus_cb.setForeground(new java.awt.Color(255, 255, 255));
        adminEmployeeStatus_cb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Log_ID", "Employee_Name", "Type", "Message", " " }));
        adminEmployeeStatus_cb.setPreferredSize(new java.awt.Dimension(165, 30));
        jPanel45.add(adminEmployeeStatus_cb);

        adminEmployeeStatusEnter_btn.setBackground(new java.awt.Color(51, 255, 51));
        adminEmployeeStatusEnter_btn.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        adminEmployeeStatusEnter_btn.setForeground(new java.awt.Color(255, 255, 255));
        adminEmployeeStatusEnter_btn.setText("SEARCH");
        adminEmployeeStatusEnter_btn.setPreferredSize(new java.awt.Dimension(90, 30));
        adminEmployeeStatusEnter_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminEmployeeStatusEnter_btnActionPerformed(evt);
            }
        });
        jPanel45.add(adminEmployeeStatusEnter_btn);

        rowCountStatus_lbl.setBackground(new java.awt.Color(0, 0, 0));
        rowCountStatus_lbl.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        rowCountStatus_lbl.setForeground(new java.awt.Color(0, 0, 0));
        rowCountStatus_lbl.setText("jLabel3");
        jPanel45.add(rowCountStatus_lbl);

        jPanel44.add(jPanel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 280, 140));

        adminEmployeeStatusRefresh_btn.setBackground(new java.awt.Color(63, 114, 175));
        adminEmployeeStatusRefresh_btn.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        adminEmployeeStatusRefresh_btn.setForeground(new java.awt.Color(255, 255, 255));
        adminEmployeeStatusRefresh_btn.setText("REFRESH");
        adminEmployeeStatusRefresh_btn.setPreferredSize(new java.awt.Dimension(90, 30));
        adminEmployeeStatusRefresh_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminEmployeeStatusRefresh_btnActionPerformed(evt);
            }
        });
        jPanel44.add(adminEmployeeStatusRefresh_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 80, 80, 20));

        displayStatusSelection_ta.setColumns(20);
        displayStatusSelection_ta.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        displayStatusSelection_ta.setForeground(new java.awt.Color(255, 255, 255));
        displayStatusSelection_ta.setRows(5);
        displayStatusSelection_ta.setWrapStyleWord(true);
        jScrollPane5.setViewportView(displayStatusSelection_ta);

        jPanel44.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 260, 190));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jPanel44, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanel44, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Employee Statuses", jPanel2);

        jPanel4.setBackground(new java.awt.Color(249, 247, 247));

        adminEmployeeLogs_tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Name", "EmployeeID", "Status"
            }
        ));
        adminEmployeeLogs_tbl.setRequestFocusEnabled(false);
        adminEmployeeLogs_tbl.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        adminEmployeeLogs_tbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                adminEmployeeLogs_tblMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(adminEmployeeLogs_tbl);

        jPanel46.setBackground(new java.awt.Color(219, 226, 239));
        jPanel46.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Search: ");
        jPanel46.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 86, -1));

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("EMPLOYEE STATUSES");
        jPanel46.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 230, 30));

        jPanel50.setBackground(new java.awt.Color(219, 226, 239));
        jPanel50.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 20));

        adminEmployeeLogs_tf.setBackground(new java.awt.Color(249, 247, 247));
        adminEmployeeLogs_tf.setForeground(new java.awt.Color(0, 0, 0));
        adminEmployeeLogs_tf.setPreferredSize(new java.awt.Dimension(260, 30));
        adminEmployeeLogs_tf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminEmployeeLogs_tfActionPerformed(evt);
            }
        });
        jPanel50.add(adminEmployeeLogs_tf);

        adminEmployeeLogs_cb.setBackground(new java.awt.Color(63, 114, 175));
        adminEmployeeLogs_cb.setForeground(new java.awt.Color(255, 255, 255));
        adminEmployeeLogs_cb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Log_ID", "Employee_Name", "Type", "Message", " " }));
        adminEmployeeLogs_cb.setPreferredSize(new java.awt.Dimension(165, 30));
        jPanel50.add(adminEmployeeLogs_cb);

        adminEmployeeStatusLogs_btn.setBackground(new java.awt.Color(51, 255, 51));
        adminEmployeeStatusLogs_btn.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        adminEmployeeStatusLogs_btn.setForeground(new java.awt.Color(255, 255, 255));
        adminEmployeeStatusLogs_btn.setText("SEARCH");
        adminEmployeeStatusLogs_btn.setPreferredSize(new java.awt.Dimension(90, 30));
        adminEmployeeStatusLogs_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminEmployeeStatusLogs_btnActionPerformed(evt);
            }
        });
        jPanel50.add(adminEmployeeStatusLogs_btn);

        rowCountLogs_lbl.setBackground(new java.awt.Color(0, 0, 0));
        rowCountLogs_lbl.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        rowCountLogs_lbl.setForeground(new java.awt.Color(0, 0, 0));
        rowCountLogs_lbl.setText("jLabel3");
        jPanel50.add(rowCountLogs_lbl);

        jPanel46.add(jPanel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 280, 140));

        adminEmployeeLogsRefresh_btn.setBackground(new java.awt.Color(63, 114, 175));
        adminEmployeeLogsRefresh_btn.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        adminEmployeeLogsRefresh_btn.setForeground(new java.awt.Color(255, 255, 255));
        adminEmployeeLogsRefresh_btn.setText("REFRESH");
        adminEmployeeLogsRefresh_btn.setPreferredSize(new java.awt.Dimension(90, 30));
        adminEmployeeLogsRefresh_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminEmployeeLogsRefresh_btnActionPerformed(evt);
            }
        });
        jPanel46.add(adminEmployeeLogsRefresh_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 80, 80, 20));

        displayLogsSelection_ta.setColumns(20);
        displayLogsSelection_ta.setForeground(new java.awt.Color(255, 255, 255));
        displayLogsSelection_ta.setRows(5);
        displayLogsSelection_ta.setWrapStyleWord(true);
        jScrollPane7.setViewportView(displayLogsSelection_ta);

        jPanel46.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 260, 190));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jPanel46, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanel46, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Employee Logs", jPanel4);

        javax.swing.GroupLayout admin_pnlLayout = new javax.swing.GroupLayout(admin_pnl);
        admin_pnl.setLayout(admin_pnlLayout);
        admin_pnlLayout.setHorizontalGroup(
            admin_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, admin_pnlLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1))
        );
        admin_pnlLayout.setVerticalGroup(
            admin_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        mainDashboard_tabbedPane.addTab("Admin Controls", admin_pnl);

        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel7.setLayout(new javax.swing.BoxLayout(jPanel7, javax.swing.BoxLayout.Y_AXIS));

        jPanel10.setBackground(new java.awt.Color(63, 114, 175));
        jPanel10.setPreferredSize(new java.awt.Dimension(838, 50));

        jLabel15.setBackground(new java.awt.Color(255, 255, 255));
        jLabel15.setFont(new java.awt.Font("Arial CE MT Black", 1, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("SETTINGS");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(855, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jPanel7.add(jPanel10);

        jPanel12.setLayout(new javax.swing.BoxLayout(jPanel12, javax.swing.BoxLayout.Y_AXIS));

        jPanel13.setBackground(new java.awt.Color(249, 247, 247));
        jPanel13.setForeground(new java.awt.Color(249, 247, 247));
        jPanel13.setPreferredSize(new java.awt.Dimension(939, 30));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Change Username");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                .addGap(740, 740, 740))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel8))
        );

        jPanel12.add(jPanel13);

        jPanel9.setBackground(new java.awt.Color(249, 247, 247));

        changeUsernameTf.setBackground(new java.awt.Color(219, 226, 239));
        changeUsernameTf.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        changeUsernameTf.setForeground(new java.awt.Color(0, 0, 0));

        updateUsername_btn.setBackground(new java.awt.Color(0, 255, 0));
        updateUsername_btn.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        updateUsername_btn.setForeground(new java.awt.Color(255, 255, 255));
        updateUsername_btn.setText("UPDATE");
        updateUsername_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateUsername_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(changeUsernameTf, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(updateUsername_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(733, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(updateUsername_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(changeUsernameTf, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43))
        );

        jPanel12.add(jPanel9);

        jPanel7.add(jPanel12);

        jPanel29.setPreferredSize(new java.awt.Dimension(1033, 180));
        jPanel29.setLayout(new javax.swing.BoxLayout(jPanel29, javax.swing.BoxLayout.Y_AXIS));

        jPanel30.setBackground(new java.awt.Color(249, 247, 247));
        jPanel30.setPreferredSize(new java.awt.Dimension(939, 30));

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Change Password");

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                .addGap(740, 740, 740))
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel30Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel9))
        );

        jPanel29.add(jPanel30);

        jPanel31.setBackground(new java.awt.Color(249, 247, 247));
        jPanel31.setPreferredSize(new java.awt.Dimension(1033, 151));
        jPanel31.setLayout(new javax.swing.BoxLayout(jPanel31, javax.swing.BoxLayout.X_AXIS));

        jPanel32.setBackground(new java.awt.Color(249, 247, 247));

        currentPass_pf.setBackground(new java.awt.Color(219, 226, 239));
        currentPass_pf.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        currentPass_pf.setForeground(new java.awt.Color(0, 0, 0));
        currentPass_pf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                currentPass_pfActionPerformed(evt);
            }
        });

        currentPass_cb.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        currentPass_cb.setForeground(new java.awt.Color(0, 0, 0));
        currentPass_cb.setText("Show Current Password");
        currentPass_cb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                currentPass_cbActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(currentPass_cb)
                    .addComponent(currentPass_pf, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(currentPass_pf, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(currentPass_cb)
                .addGap(30, 30, 30))
        );

        jPanel31.add(jPanel32);

        jPanel33.setBackground(new java.awt.Color(249, 247, 247));

        newPass_pf.setBackground(new java.awt.Color(219, 226, 239));
        newPass_pf.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        newPass_pf.setForeground(new java.awt.Color(0, 0, 0));

        newPass_cb.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        newPass_cb.setForeground(new java.awt.Color(0, 0, 0));
        newPass_cb.setText("Show New Password");
        newPass_cb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newPass_cbActionPerformed(evt);
            }
        });

        confirmPass_pf.setBackground(new java.awt.Color(219, 226, 239));
        confirmPass_pf.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        confirmPass_pf.setForeground(new java.awt.Color(0, 0, 0));

        jButton10.setBackground(new java.awt.Color(0, 255, 0));
        jButton10.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setText("UPDATE");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(newPass_cb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(newPass_pf, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addComponent(confirmPass_pf, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(292, Short.MAX_VALUE))
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newPass_pf, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(confirmPass_pf, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addComponent(newPass_cb)
                .addGap(30, 30, 30))
        );

        jPanel31.add(jPanel33);

        jPanel29.add(jPanel31);

        jPanel7.add(jPanel29);

        jPanel14.setLayout(new javax.swing.BoxLayout(jPanel14, javax.swing.BoxLayout.Y_AXIS));

        jPanel19.setBackground(new java.awt.Color(249, 247, 247));
        jPanel19.setPreferredSize(new java.awt.Dimension(939, 30));

        jLabel18.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Delete Account");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                .addGap(736, 736, 736))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel18)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel14.add(jPanel19);

        jPanel23.setBackground(new java.awt.Color(249, 247, 247));
        jPanel23.setPreferredSize(new java.awt.Dimension(1056, 103));

        deleteAccount_btn.setBackground(new java.awt.Color(255, 0, 0));
        deleteAccount_btn.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        deleteAccount_btn.setForeground(new java.awt.Color(255, 255, 255));
        deleteAccount_btn.setText("DELETE");
        deleteAccount_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteAccount_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(deleteAccount_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(892, Short.MAX_VALUE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(deleteAccount_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(65, Short.MAX_VALUE))
        );

        jPanel14.add(jPanel23);

        jPanel7.add(jPanel14);

        jScrollPane4.setViewportView(jPanel7);

        javax.swing.GroupLayout settings_pnlLayout = new javax.swing.GroupLayout(settings_pnl);
        settings_pnl.setLayout(settings_pnlLayout);
        settings_pnlLayout.setHorizontalGroup(
            settings_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 905, Short.MAX_VALUE)
        );
        settings_pnlLayout.setVerticalGroup(
            settings_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
        );

        mainDashboard_tabbedPane.addTab("Settings", settings_pnl);

        jPanel5.add(mainDashboard_tabbedPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 52, -1, -1));

        jPanel1.setBackground(new java.awt.Color(17, 45, 78));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton12.setBackground(new java.awt.Color(204, 0, 0));
        jButton12.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton12.setForeground(new java.awt.Color(255, 255, 255));
        jButton12.setText("Logout");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 10, -1, 30));

        jLabel30.setBackground(new java.awt.Color(17, 45, 78));
        jLabel30.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("MANAGEMENT INFORMATION SYSTEM");
        jPanel1.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 470, -1));

        jPanel5.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 60));

        getContentPane().add(jPanel5);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void statusUpdate_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusUpdate_btnActionPerformed
        String msg, message;
        msg = statusMessage_textArea.getText();
        
        if(msg.isBlank() && busy_rb.isSelected()){
            JOptionPane.showMessageDialog(null,  "Status Message cannot be empty when busy", "",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        if(busy_rb.isSelected()){
            db.updateDb(userID, "users", "statusMessage", msg);
            db.updateDb(userID, "users", "availability", "1");
            message = db.getUserData(userID, "userdata", "firstName").toString() + " is Currently Busy - " + msg;
            db.dbLog(userID, "Employee", "Status Update", message);
            return;
        }
        
        if(statusMessage_textArea.getText().isBlank()){
            db.updateDb(userID, "users", "statusMessage", "Currently Idle...");
            db.updateDb(userID, "users", "availability", "0");
            message = db.getUserData(userID, "userdata", "firstName").toString() + " is Now Available";
            db.dbLog(userID, "Employee", "Status Update", message);
            return;
        }
        
    }//GEN-LAST:event_statusUpdate_btnActionPerformed

    private void updateTimeIn_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateTimeIn_btnActionPerformed
        String message = db.getUserData(userID, "userdata", "firstName").toString() + " " + db.getUserData(userID, "userdata", "lastName").toString();
        if(active == 1){
            db.updateDb(userID, "users", "active", "0");
            active = 0;
            message = message + " has timed-in";
        }
        else{
            db.updateDb(userID, "users", "active", "1");
            active = 1;
            message = message + " has timed-out";
        }
        db.dbLog(userID, "Employee", "Attendance", message);
        setTimeLog();
    }//GEN-LAST:event_updateTimeIn_btnActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        Map<String, String> input = new HashMap<String, String>(); 
        input.put("lastName", fnc.capitalizeFirstLetter(setup_lastNameF.getText()));
        input.put("firstName", fnc.capitalizeFirstLetter(setup_firstNameF.getText()));
        input.put("address", fnc.capitalizeFirstLetter(setup_addressF.getText()));
        input.put("contactNumber", setup_contactF.getText());
        input.put("emailAddress", setup_emailF.getText());

        input.forEach((key, value) -> {
            if(input.get(key).equals("")){
                JOptionPane.showMessageDialog(null,  "Please Enter Values in the Required Fields", "Missing Input",JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        });
        
        if(!fnc.isEmail(setup_emailF.getText())){
            JOptionPane.showMessageDialog(null,  "Please Enter a Valid Email Address", "Email Invalid",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        input.put("middleName", fnc.capitalizeFirstLetter(setup_middleNameF.getText()));
        input.put("birthdate", fnc.dateFormatter(setup_birthdateDp.getDate()));
        input.put("gender", fnc.getGenders()[setup_genderCb.getSelectedIndex()]);
        
        fnc.accountSetup(userID, input);
        tabbedPaneBehavior();
    }//GEN-LAST:event_jButton11ActionPerformed

    
    
    
    private void setup_firstNameFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_setup_firstNameFKeyTyped
        fnc.alphaNumericFilter(evt, 2);
    }//GEN-LAST:event_setup_firstNameFKeyTyped

    private void setup_lastNameFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_setup_lastNameFKeyTyped
        fnc.alphaNumericFilter(evt, 2);
    }//GEN-LAST:event_setup_lastNameFKeyTyped

    private void setup_middleNameFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_setup_middleNameFKeyTyped
        fnc.alphaNumericFilter(evt, 2);
    }//GEN-LAST:event_setup_middleNameFKeyTyped

    private void setup_contactFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_setup_contactFKeyTyped
        fnc.alphaNumericFilter(evt, 1);
    }//GEN-LAST:event_setup_contactFKeyTyped

    private void setup_birthdateDpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_setup_birthdateDpKeyPressed
        fnc.alphaNumericFilter(evt, 0);
    }//GEN-LAST:event_setup_birthdateDpKeyPressed

    private void currentPass_pfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_currentPass_pfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_currentPass_pfActionPerformed

    private void busy_rbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_busy_rbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_busy_rbActionPerformed

    private void myLogsSearch_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myLogsSearch_btnActionPerformed
        String input = myLogsSearch_tf.getText();
        if(input.isBlank() || input.isEmpty())userLogs_tbl.setRowSorter(null);
        String filter = myLogs_cb.getSelectedItem().toString();
        fnc.filterMyLogsRows(userLogs_tbl, input, filter);
        
    }//GEN-LAST:event_myLogsSearch_btnActionPerformed

    private void myLogsRefresh_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myLogsRefresh_btnActionPerformed
        userLogs_tbl.setModel(db.getLogsTableModel("loggerID", Integer.toString(userID), false));
        userLogs_tbl.setRowSorter(null);
    }//GEN-LAST:event_myLogsRefresh_btnActionPerformed

    private void updateUsername_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateUsername_btnActionPerformed
        String input = changeUsernameTf.getText();
        if(input.equals(db.getUserData(userID, "users", "username"))){
            return;
        }
        
        if(input.length() < 3){
            JOptionPane.showMessageDialog(null,  "Your username must contain atleast 3 characters", "",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if(db.existsInDb("users", "username", input)){
            JOptionPane.showMessageDialog(null,  "Username already taken", "",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        db.updateDb(userID, "users", "username", input);
        db.dbLog(userID, "Employee", "Account Update", "Account username was changed");
        JOptionPane.showMessageDialog(null,  "Username was updated", "",JOptionPane.INFORMATION_MESSAGE);
        return;
    }//GEN-LAST:event_updateUsername_btnActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        String currentPass = currentPass_pf.getText();
        String newPass = newPass_pf.getText();
        String confirmPass = confirmPass_pf.getText();
        
        if(currentPass.equals("") || newPass.equals("") || confirmPass.equals("")){
            JOptionPane.showMessageDialog(null,  "Please fill up all of the required fields", "",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if(!db.getUserData(userID, "users", "password").toString().equals(currentPass)){
            JOptionPane.showMessageDialog(null,  "Current password is incorrect", "",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if(!confirmPass.equals(newPass)){
            JOptionPane.showMessageDialog(null,  "New password does not match", "",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if(newPass.length() < 8){
            JOptionPane.showMessageDialog(null,  "Your new password must contain atleast 8 characters", "",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        if(currentPass.equals(newPass)){
            JOptionPane.showMessageDialog(null,  "New password cannot be the current password", "",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        db.updateDb(userID, "users", "password", newPass);
        db.dbLog(userID, "Employee", "Account Update", "Account password was changed");
        JOptionPane.showMessageDialog(null,  "Password was updated", "",JOptionPane.INFORMATION_MESSAGE);
        return;
    }//GEN-LAST:event_jButton10ActionPerformed

    private void deleteAccount_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteAccount_btnActionPerformed
        boolean bool = fnc.getConfirmation("Deleting your account would result to loss access to your data, \nare you sure you want to delete your account?", "Confirm Deletion");
        
        if(bool){
            JOptionPane.showMessageDialog(null,  "Account Deleted", "",JOptionPane.INFORMATION_MESSAGE);
            db.updateDb(userID, "users", "archived", "0");
            logout();
        }
    }//GEN-LAST:event_deleteAccount_btnActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        logout();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void userLogs_tblKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_userLogs_tblKeyPressed
        evt.consume();
    }//GEN-LAST:event_userLogs_tblKeyPressed

    private void userLogs_tblKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_userLogs_tblKeyTyped
        evt.consume();
    }//GEN-LAST:event_userLogs_tblKeyTyped

    private void adminEmployeeStatus_tfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminEmployeeStatus_tfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_adminEmployeeStatus_tfActionPerformed

    private void adminEmployeeStatusEnter_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminEmployeeStatusEnter_btnActionPerformed
        String input = adminEmployeeStatus_tf.getText();
        if(input.isBlank() || input.isEmpty())adminEmployeeStatus_tbl.setRowSorter(null);
        String filter = adminEmployeeStatus_cb.getSelectedItem().toString();
        fnc.filterMyLogsRows(adminEmployeeStatus_tbl, input, filter);
        rowCountStatus_lbl.setText("Row/s Displayed: " + adminEmployeeStatus_tbl.getRowCount());
    }//GEN-LAST:event_adminEmployeeStatusEnter_btnActionPerformed

    private void adminEmployeeStatusRefresh_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminEmployeeStatusRefresh_btnActionPerformed
        adminEmployeeStatus_tbl.setRowSorter(null);
        adminEmployeeStatus_tbl.setModel(db.getStatusModel());
    }//GEN-LAST:event_adminEmployeeStatusRefresh_btnActionPerformed

    private void adminEmployeeStatus_tblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adminEmployeeStatus_tblMouseClicked
        adminTextArea(adminEmployeeStatus_tbl, displayStatusSelection_ta);
    }//GEN-LAST:event_adminEmployeeStatus_tblMouseClicked

    private void adminEmployeeLogs_tblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adminEmployeeLogs_tblMouseClicked
        adminTextArea(adminEmployeeLogs_tbl, displayLogsSelection_ta);
    }//GEN-LAST:event_adminEmployeeLogs_tblMouseClicked

    private void adminEmployeeLogs_tfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminEmployeeLogs_tfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_adminEmployeeLogs_tfActionPerformed

    private void adminEmployeeStatusLogs_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminEmployeeStatusLogs_btnActionPerformed
        String input = adminEmployeeLogs_tf.getText();
        if(input.isBlank() || input.isEmpty())adminEmployeeLogs_tbl.setRowSorter(null);
        String filter = adminEmployeeLogs_cb.getSelectedItem().toString();
        fnc.filterMyLogsRows(adminEmployeeLogs_tbl, input, filter);
        rowCountLogs_lbl.setText("Row/s Displayed: " + adminEmployeeLogs_tbl.getRowCount());
    }//GEN-LAST:event_adminEmployeeStatusLogs_btnActionPerformed

    private void adminEmployeeLogsRefresh_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminEmployeeLogsRefresh_btnActionPerformed
        adminEmployeeLogs_tbl.setRowSorter(null);
        adminEmployeeLogs_tbl.setModel(db.getLogsTableModel("", "", isAdmin));
    }//GEN-LAST:event_adminEmployeeLogsRefresh_btnActionPerformed

    private void currentPass_cbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_currentPass_cbActionPerformed
        fnc.passwordFieldBehavior(currentPass_pf, currentPass_cb);
    }//GEN-LAST:event_currentPass_cbActionPerformed

    private void newPass_cbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newPass_cbActionPerformed
        fnc.passwordFieldBehavior(newPass_pf, newPass_cb);
        fnc.passwordFieldBehavior(confirmPass_pf, newPass_cb);
    }//GEN-LAST:event_newPass_cbActionPerformed

    private void adminTextArea(JTable table, JTextArea ta){
        StringBuilder str = new StringBuilder("Selected Row: \n");
        String value = "";
        String column = "";
        for(int i =0; i < table.getColumnCount(); i++){
            if(table.getValueAt(table.getSelectedRow(), i) == null){
                value = "None";
            }
            else{
                value = table.getValueAt(table.getSelectedRow(), i).toString();
            }
            column = table.getColumnName(i).toString();
            str.append(column + ": " + value + "\n");
            //System.out.println(column + ": " + value);
        }
        ta.setText(str.toString());
    }
  
    private void logout(){
        Access acc = new Access();
        acc.setVisible(true);
        this.dispose();
    }
    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel accountSetup_pnl;
    private javax.swing.JButton adminEmployeeLogsRefresh_btn;
    private javax.swing.JComboBox<String> adminEmployeeLogs_cb;
    private javax.swing.JTable adminEmployeeLogs_tbl;
    private javax.swing.JTextField adminEmployeeLogs_tf;
    private javax.swing.JButton adminEmployeeStatusEnter_btn;
    private javax.swing.JButton adminEmployeeStatusLogs_btn;
    private javax.swing.JButton adminEmployeeStatusRefresh_btn;
    private javax.swing.JComboBox<String> adminEmployeeStatus_cb;
    private javax.swing.JTable adminEmployeeStatus_tbl;
    private javax.swing.JTextField adminEmployeeStatus_tf;
    private javax.swing.JPanel admin_pnl;
    private javax.swing.JRadioButton available_rb;
    private javax.swing.JRadioButton busy_rb;
    private javax.swing.JTextField changeUsernameTf;
    private javax.swing.JPasswordField confirmPass_pf;
    private javax.swing.JCheckBox currentPass_cb;
    private javax.swing.JPasswordField currentPass_pf;
    private javax.swing.JButton deleteAccount_btn;
    private javax.swing.JTextArea displayLogsSelection_ta;
    private javax.swing.JTextArea displayStatusSelection_ta;
    private javax.swing.JPanel employee_pnl;
    private javax.swing.JPanel employee_status_pnl;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel50;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane mainDashboard_tabbedPane;
    private javax.swing.JButton myLogsRefresh_btn;
    private javax.swing.JButton myLogsSearch_btn;
    private javax.swing.JTextField myLogsSearch_tf;
    private javax.swing.JComboBox<String> myLogs_cb;
    private javax.swing.JPanel mylogs_pnl;
    private javax.swing.JCheckBox newPass_cb;
    private javax.swing.JPasswordField newPass_pf;
    private javax.swing.JPanel panel123;
    private javax.swing.JLabel rowCountLogs_lbl;
    private javax.swing.JLabel rowCountStatus_lbl;
    private javax.swing.JPanel settings_pnl;
    private javax.swing.JTextField setup_addressF;
    private com.toedter.calendar.JDateChooser setup_birthdateDp;
    private javax.swing.JTextField setup_contactF;
    private javax.swing.JTextField setup_emailF;
    private javax.swing.JTextField setup_firstNameF;
    private javax.swing.JComboBox<String> setup_genderCb;
    private javax.swing.JTextField setup_lastNameF;
    private javax.swing.JTextField setup_middleNameF;
    private javax.swing.JTextArea statusMessage_textArea;
    private javax.swing.JLabel statusPnl_status_lbl;
    private javax.swing.JButton statusUpdate_btn;
    private javax.swing.JLabel status_lbl;
    private javax.swing.JScrollPane status_scrollpane;
    private Components.TimeLabel timeLabel1;
    private javax.swing.JPanel timeLog_pnl;
    private javax.swing.JButton updateTimeIn_btn;
    private javax.swing.JButton updateUsername_btn;
    private javax.swing.JLabel userLbl;
    private javax.swing.JTable userLogs_tbl;
    // End of variables declaration//GEN-END:variables
}
