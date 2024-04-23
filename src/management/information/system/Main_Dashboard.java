/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package management.information.system;

/**
 *
 * @author Drew
 */
public class Main_Dashboard extends javax.swing.JFrame {

    /**
     * Creates new form Main_Dashboard
     */
    public Main_Dashboard() {
        initComponents();
        
        //db_connection conn = new db_connection();
        //conn.testConnection();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainDashboard_tabbedPane = new javax.swing.JTabbedPane();
        mainDashboard_employee_pnl = new javax.swing.JPanel();
        employee_timeLog_pnl = new javax.swing.JPanel();
        employee_displayTime = new javax.swing.JLabel();
        timelog_timeIn_rb = new javax.swing.JRadioButton();
        timelog_timeOut_rb = new javax.swing.JRadioButton();
        employee_updateTime_btn = new javax.swing.JButton();
        employee_status_pnl = new javax.swing.JPanel();
        status_lbl = new javax.swing.JLabel();
        status_available_rb = new javax.swing.JRadioButton();
        status_notAvailable_rb = new javax.swing.JRadioButton();
        status_scrollpane = new javax.swing.JScrollPane();
        statusPnl_status_textArea = new javax.swing.JTextArea();
        status_update_btn = new javax.swing.JButton();
        statusPnl_status_lbl = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        employee_attendance = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        mainDashboard_profile_pnl = new javax.swing.JPanel();
        mainDashboard_settings_pnl = new javax.swing.JPanel();
        mainDashboard_admin_pnl = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainDashboard_tabbedPane.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        employee_displayTime.setText("8:30 am");

        timelog_timeIn_rb.setText("Time-in");
        timelog_timeIn_rb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timelog_timeIn_rbActionPerformed(evt);
            }
        });

        timelog_timeOut_rb.setText("Time-out");

        employee_updateTime_btn.setText("Enter");

        javax.swing.GroupLayout employee_timeLog_pnlLayout = new javax.swing.GroupLayout(employee_timeLog_pnl);
        employee_timeLog_pnl.setLayout(employee_timeLog_pnlLayout);
        employee_timeLog_pnlLayout.setHorizontalGroup(
            employee_timeLog_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(employee_timeLog_pnlLayout.createSequentialGroup()
                .addGroup(employee_timeLog_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(employee_timeLog_pnlLayout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(employee_updateTime_btn))
                    .addGroup(employee_timeLog_pnlLayout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addGroup(employee_timeLog_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(timelog_timeIn_rb, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(timelog_timeOut_rb, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(employee_timeLog_pnlLayout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(employee_displayTime, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(69, Short.MAX_VALUE))
        );
        employee_timeLog_pnlLayout.setVerticalGroup(
            employee_timeLog_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, employee_timeLog_pnlLayout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(employee_displayTime, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(timelog_timeIn_rb)
                .addGap(18, 18, 18)
                .addComponent(timelog_timeOut_rb)
                .addGap(31, 31, 31)
                .addComponent(employee_updateTime_btn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        status_lbl.setText("Status");

        status_available_rb.setText("Available");

        status_notAvailable_rb.setText("Not Available");

        statusPnl_status_textArea.setColumns(20);
        statusPnl_status_textArea.setRows(5);
        status_scrollpane.setViewportView(statusPnl_status_textArea);

        status_update_btn.setText("Update");
        status_update_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                status_update_btnActionPerformed(evt);
            }
        });

        statusPnl_status_lbl.setText("Status Message:");

        javax.swing.GroupLayout employee_status_pnlLayout = new javax.swing.GroupLayout(employee_status_pnl);
        employee_status_pnl.setLayout(employee_status_pnlLayout);
        employee_status_pnlLayout.setHorizontalGroup(
            employee_status_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(employee_status_pnlLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(employee_status_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(employee_status_pnlLayout.createSequentialGroup()
                        .addComponent(status_lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, employee_status_pnlLayout.createSequentialGroup()
                        .addGroup(employee_status_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(status_scrollpane, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, employee_status_pnlLayout.createSequentialGroup()
                                .addComponent(status_available_rb, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(status_notAvailable_rb, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(23, 23, 23))
                    .addGroup(employee_status_pnlLayout.createSequentialGroup()
                        .addGroup(employee_status_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(status_update_btn)
                            .addComponent(statusPnl_status_lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        employee_status_pnlLayout.setVerticalGroup(
            employee_status_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, employee_status_pnlLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(status_lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(employee_status_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(status_available_rb)
                    .addComponent(status_notAvailable_rb))
                .addGap(28, 28, 28)
                .addComponent(statusPnl_status_lbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(status_scrollpane, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(status_update_btn)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jLabel1.setText("Hello user!");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        employee_attendance.addTab("tab1", jScrollPane1);

        javax.swing.GroupLayout mainDashboard_employee_pnlLayout = new javax.swing.GroupLayout(mainDashboard_employee_pnl);
        mainDashboard_employee_pnl.setLayout(mainDashboard_employee_pnlLayout);
        mainDashboard_employee_pnlLayout.setHorizontalGroup(
            mainDashboard_employee_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainDashboard_employee_pnlLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(mainDashboard_employee_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(employee_attendance, javax.swing.GroupLayout.PREFERRED_SIZE, 589, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(mainDashboard_employee_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(mainDashboard_employee_pnlLayout.createSequentialGroup()
                            .addComponent(employee_timeLog_pnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(43, 43, 43)
                            .addComponent(employee_status_pnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        mainDashboard_employee_pnlLayout.setVerticalGroup(
            mainDashboard_employee_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainDashboard_employee_pnlLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(mainDashboard_employee_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(employee_status_pnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(employee_timeLog_pnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(employee_attendance, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(319, 319, 319))
        );

        mainDashboard_tabbedPane.addTab("tab2", mainDashboard_employee_pnl);

        javax.swing.GroupLayout mainDashboard_profile_pnlLayout = new javax.swing.GroupLayout(mainDashboard_profile_pnl);
        mainDashboard_profile_pnl.setLayout(mainDashboard_profile_pnlLayout);
        mainDashboard_profile_pnlLayout.setHorizontalGroup(
            mainDashboard_profile_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 731, Short.MAX_VALUE)
        );
        mainDashboard_profile_pnlLayout.setVerticalGroup(
            mainDashboard_profile_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 487, Short.MAX_VALUE)
        );

        mainDashboard_tabbedPane.addTab("tab4", mainDashboard_profile_pnl);

        javax.swing.GroupLayout mainDashboard_settings_pnlLayout = new javax.swing.GroupLayout(mainDashboard_settings_pnl);
        mainDashboard_settings_pnl.setLayout(mainDashboard_settings_pnlLayout);
        mainDashboard_settings_pnlLayout.setHorizontalGroup(
            mainDashboard_settings_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 731, Short.MAX_VALUE)
        );
        mainDashboard_settings_pnlLayout.setVerticalGroup(
            mainDashboard_settings_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 487, Short.MAX_VALUE)
        );

        mainDashboard_tabbedPane.addTab("tab3", mainDashboard_settings_pnl);
        mainDashboard_tabbedPane.addTab("tab5", mainDashboard_admin_pnl);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainDashboard_tabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 784, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainDashboard_tabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void timelog_timeIn_rbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timelog_timeIn_rbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_timelog_timeIn_rbActionPerformed

    private void status_update_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_status_update_btnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_status_update_btnActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane employee_attendance;
    private javax.swing.JLabel employee_displayTime;
    private javax.swing.JPanel employee_status_pnl;
    private javax.swing.JPanel employee_timeLog_pnl;
    private javax.swing.JButton employee_updateTime_btn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JScrollPane mainDashboard_admin_pnl;
    private javax.swing.JPanel mainDashboard_employee_pnl;
    private javax.swing.JPanel mainDashboard_profile_pnl;
    private javax.swing.JPanel mainDashboard_settings_pnl;
    private javax.swing.JTabbedPane mainDashboard_tabbedPane;
    private javax.swing.JLabel statusPnl_status_lbl;
    private javax.swing.JTextArea statusPnl_status_textArea;
    private javax.swing.JRadioButton status_available_rb;
    private javax.swing.JLabel status_lbl;
    private javax.swing.JRadioButton status_notAvailable_rb;
    private javax.swing.JScrollPane status_scrollpane;
    private javax.swing.JButton status_update_btn;
    private javax.swing.JRadioButton timelog_timeIn_rb;
    private javax.swing.JRadioButton timelog_timeOut_rb;
    // End of variables declaration//GEN-END:variables
}
