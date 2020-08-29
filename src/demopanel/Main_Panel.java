/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demopanel;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ender
 */
public class Main_Panel extends javax.swing.JFrame {

    /**
     * Creates new form Main_Panel
     */
    public Main_Panel() {
        initComponents();
        Show_Products_In_JTable();
        
    }
    
    
    public Connection getConnection()
    {
        Connection con = null;
        
        try {
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/garage","root", "");
            return con;
        } catch (SQLException ex) {
            Logger.getLogger(Main_Panel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Connection Error");
            return null;
        }
    }
    
    //Resize image
    String ImgPath = null;
    int pos = 0;
    
            public ImageIcon Resize(String imagePath, byte[] pic)
            {
                ImageIcon myImage = null;
                
                if(imagePath != null)
                {
                    myImage = new ImageIcon(imagePath);
                }else{
                    myImage = new ImageIcon(pic);
                }
                
                Image img = myImage.getImage();
                Image img2 = img.getScaledInstance(lbl_image.getWidth(), lbl_image.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon image = new ImageIcon(img2);
                return image;
                
            }
            
            public ArrayList<Product> getProductList()
            {
                ArrayList<Product> productList = new ArrayList<>();
                Connection con = getConnection();
                String query = "SELECT  * FROM products";
            
                try {
            
                    Statement st;
                    ResultSet rs;
            
                    st = con.createStatement();
                    rs = st.executeQuery(query);
                    Product product;
                    while(rs.next())
                    {
                        product = new Product(rs.getInt("id"),rs.getString("name"),Float.parseFloat(rs.getString("price")),
                                rs.getString("add_date"),rs.getInt("stock"),rs.getBytes("image"));
                        productList.add(product);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Main_Panel.class.getName()).log(Level.SEVERE, null, ex);
                }
        
                return productList;
        
            }
            
            
            public void Show_Products_In_JTable()
            {
                ArrayList<Product> list = getProductList();
                DefaultTableModel model = (DefaultTableModel)JTable_Products.getModel();
                model.setRowCount(0);
                Object[] row = new Object[5];
                for(int i = 0; i < list.size(); i++)
                {
                    row[0]=list.get(i).getId();
                    row[1]=list.get(i).getName();
                    row[2]=list.get(i).getPrice();
                    row[3]=list.get(i).getAddDate();
                    row[4]=list.get(i).getStock();
                    
                    model.addRow(row);
                }
            }
            
            public void ShowItem(int index)
            {
                txt_id.setText(Integer.toString(getProductList().get(index).getId()));
                txt_name.setText(getProductList().get(index).getName());
                txt_price.setText(Float.toString(getProductList().get(index).getPrice()));
                try {
            
                    Date addDate = null;
                    addDate = new SimpleDateFormat("yyyy-MM-dd").parse((String)getProductList().get(index).getAddDate());
                    txt_addDate.setDate(addDate);
                } catch (ParseException ex) {
                    Logger.getLogger(Main_Panel.class.getName()).log(Level.SEVERE, null, ex);
                }
                txt_stock.setText(Integer.toString(getProductList().get(index).getStock()));
                lbl_image.setIcon(Resize(null, getProductList().get(index).getPicture()));
            }
            

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        datePickerFormatter1 = new org.jdesktop.swingx.calendar.DatePickerFormatter();
        datePickerFormatter2 = new org.jdesktop.swingx.calendar.DatePickerFormatter();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();
        txt_name = new javax.swing.JTextField();
        txt_price = new javax.swing.JTextField();
        txt_stock = new javax.swing.JTextField();
        txt_addDate = new org.jdesktop.swingx.JXDatePicker();
        lbl_image = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTable_Products = new javax.swing.JTable();
        Btn_Choose = new javax.swing.JButton();
        Btn_Update = new javax.swing.JButton();
        Btn_Insert = new javax.swing.JButton();
        Btn_Delete = new javax.swing.JButton();
        Btn_Previous = new javax.swing.JButton();
        Btn_Next = new javax.swing.JButton();
        Btn_Last = new javax.swing.JButton();
        Btn_First = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setBackground(new java.awt.Color(241, 93, 93));
        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setText("ID:");

        jLabel2.setBackground(new java.awt.Color(241, 93, 93));
        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setText("Name:");

        jLabel3.setBackground(new java.awt.Color(241, 93, 93));
        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setText("Price:");

        jLabel4.setBackground(new java.awt.Color(241, 93, 93));
        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setText("Add Date:");

        jLabel5.setBackground(new java.awt.Color(241, 93, 93));
        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setText("Stock:");

        jLabel6.setBackground(new java.awt.Color(241, 93, 93));
        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel6.setText("Image:");

        txt_id.setEditable(false);
        txt_id.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N

        txt_name.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N

        txt_price.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N

        txt_stock.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N

        txt_addDate.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N

        lbl_image.setBackground(new java.awt.Color(255, 255, 255));
        lbl_image.setForeground(new java.awt.Color(255, 255, 255));
        lbl_image.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lbl_image.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        JTable_Products.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Price", "AddDate", "Stock"
            }
        ));
        JTable_Products.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTable_ProductsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTable_Products);

        Btn_Choose.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Btn_Choose.setText("Choose Image");
        Btn_Choose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_ChooseActionPerformed(evt);
            }
        });

        Btn_Update.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Btn_Update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/demo/icons8-update-100.png"))); // NOI18N
        Btn_Update.setText("Update");
        Btn_Update.setIconTextGap(10);
        Btn_Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_UpdateActionPerformed(evt);
            }
        });

        Btn_Insert.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Btn_Insert.setIcon(new javax.swing.ImageIcon(getClass().getResource("/demo/icons8-plus-64.png"))); // NOI18N
        Btn_Insert.setText("Add");
        Btn_Insert.setIconTextGap(10);
        Btn_Insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_InsertActionPerformed(evt);
            }
        });

        Btn_Delete.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Btn_Delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/demo/icons8-delete-64.png"))); // NOI18N
        Btn_Delete.setText("Delete");
        Btn_Delete.setIconTextGap(10);
        Btn_Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_DeleteActionPerformed(evt);
            }
        });

        Btn_Previous.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Btn_Previous.setIcon(new javax.swing.ImageIcon(getClass().getResource("/demo/icons8-back-to-48.png"))); // NOI18N
        Btn_Previous.setText("Previous");
        Btn_Previous.setIconTextGap(10);
        Btn_Previous.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_PreviousActionPerformed(evt);
            }
        });

        Btn_Next.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Btn_Next.setIcon(new javax.swing.ImageIcon(getClass().getResource("/demo/icons8-next-page-64.png"))); // NOI18N
        Btn_Next.setText("Next");
        Btn_Next.setIconTextGap(10);
        Btn_Next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_NextActionPerformed(evt);
            }
        });

        Btn_Last.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Btn_Last.setText("Last");
        Btn_Last.setIconTextGap(10);
        Btn_Last.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_LastActionPerformed(evt);
            }
        });

        Btn_First.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Btn_First.setText("First");
        Btn_First.setIconTextGap(10);
        Btn_First.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_FirstActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel3)
                        .addComponent(jLabel2)
                        .addComponent(jLabel4)
                        .addComponent(jLabel1)
                        .addComponent(jLabel5))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_image, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Btn_Choose, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_price, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_addDate, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_stock, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Btn_First)
                        .addGap(134, 134, 134)
                        .addComponent(Btn_Previous)
                        .addGap(74, 74, 74)
                        .addComponent(Btn_Next)
                        .addGap(132, 132, 132)
                        .addComponent(Btn_Last))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 652, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(Btn_Insert)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Btn_Update)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Btn_Delete)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(txt_price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(txt_addDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_stock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(13, 13, 13)
                                        .addComponent(jLabel6))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(lbl_image, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Btn_Choose)
                                .addGap(86, 86, 86))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Btn_Last, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Btn_First, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Btn_Previous, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Btn_Next, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Btn_Update, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Btn_Insert, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Btn_Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Btn_ChooseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_ChooseActionPerformed
        // TODO add your handling code here:
        String userDir = System.getProperty("user.home");
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(userDir + "/Desktop"));
        
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.image", "jpg","png");
        file.addChoosableFileFilter(filter);
        int result = file.showSaveDialog(null);
        if(result == JFileChooser.APPROVE_OPTION)
        {
            File selectedFile = file.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            lbl_image.setIcon(Resize(path, null));
            ImgPath = path;
        }
        else
        {
            System.out.println("No File Selected");
        }
        
    }//GEN-LAST:event_Btn_ChooseActionPerformed

    
    //Check Input Fields
    
    public boolean checkInputs(){
        if(txt_name.getText() == null
                || txt_price.getText() == null
                || txt_addDate.getDate() == null
                || txt_stock.getText() == null){
            return false;
        }
        else{
            try{
                Float.parseFloat(txt_price.getText());
                return true;
            }catch(Exception ex){
                return false;
            }
        }
    }
    
    
    private void Btn_InsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_InsertActionPerformed
        // TODO add your handling code here:
        if(checkInputs() && ImgPath != null)
        {
            try {
                Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement("INSERT INTO products(name,price,add_date,stock,image)" 
                        + "values(?,?,?,?,?) ");
                ps.setString(1, txt_name.getText());
                ps.setString(2, txt_price.getText());
                SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
                String addDate = dateformat.format(txt_addDate.getDate());
                ps.setString(3, addDate);
                ps.setString(4, txt_stock.getText());
                InputStream img = new FileInputStream(new File(ImgPath));
                ps.setBlob(5, img);
                ps.executeUpdate();
                Show_Products_In_JTable();
                JOptionPane.showMessageDialog(null, "Data Inserted");
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }else{
            JOptionPane.showMessageDialog(null, "One Or More Fields Are Empty");
        }
    }//GEN-LAST:event_Btn_InsertActionPerformed

    private void Btn_UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_UpdateActionPerformed
        // TODO add your handling code here:
        if(checkInputs() && txt_id.getText() != null)
        {
            String UpdateQuery = null;
                PreparedStatement ps = null;
                Connection con = getConnection();
            
            //Update wtihout image
            if(ImgPath == null)
            {
                try {
                    UpdateQuery = "UPDATE products SET name = ?, price = ?"
                        + ", add_date = ?, stock = ? WHERE id = ?";
                    ps = con.prepareStatement(UpdateQuery);
                    
                    ps.setString(1, txt_name.getText());
                    ps.setString(2, txt_price.getText());
                    SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
                    String addDate = dateformat.format(txt_addDate.getDate());
                    ps.setString(3, addDate);
                    ps.setString(4, txt_stock.getText());
                    ps.setInt(5, Integer.parseInt(txt_id.getText()));
                    ps.executeUpdate();
                    Show_Products_In_JTable();
                    JOptionPane.showMessageDialog(null, "Product Updated");
                    
                } catch (SQLException ex) {
                    Logger.getLogger(Main_Panel.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            //Update with image
            
            else{
                try {
                    InputStream img = new FileInputStream(new File(ImgPath));
                    
                    UpdateQuery = "UPDATE products SET name = ?, price = ?"
                        + ", add_date = ?, stock = ?, image = ? WHERE id = ?";
                    
                    ps = con.prepareStatement(UpdateQuery);
                    ps.setString(1, txt_name.getText());
                    ps.setString(2, txt_price.getText());
                    SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
                    String addDate = dateformat.format(txt_addDate.getDate());
                    ps.setString(3, addDate);
                    ps.setString(4, txt_stock.getText());
                    ps.setBlob(5, img);
                    ps.setInt(6, Integer.parseInt(txt_id.getText()));
                    ps.executeUpdate();
                    Show_Products_In_JTable();
                    JOptionPane.showMessageDialog(null, "Product Updated");
                    
                    
                    
                    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                
                
            }
        }
    }//GEN-LAST:event_Btn_UpdateActionPerformed

    private void Btn_DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_DeleteActionPerformed
        // TODO add your handling code here:
        if(!txt_id.getText().equals(""))
        {
            
            try {
                Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement("DELETE FROM products WHERE "
                        +"id = ?");
                int id = Integer.parseInt(txt_id.getText());
                ps.setInt(1, id);
                ps.executeUpdate();
                Show_Products_In_JTable();
                JOptionPane.showMessageDialog(null, "Product Deleted");
                
                
            } catch (SQLException ex) {
                Logger.getLogger(Main_Panel.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Couldn't Find Product ID");
            }
            
        }
    }//GEN-LAST:event_Btn_DeleteActionPerformed

    private void JTable_ProductsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTable_ProductsMouseClicked
        // TODO add your handling code here:
        int index = JTable_Products.getSelectedRow();
        pos = index;
        ShowItem(index);
    }//GEN-LAST:event_JTable_ProductsMouseClicked

    private void Btn_FirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_FirstActionPerformed
        // TODO add your handling code here:
        pos = 0;
        ShowItem(pos);
    }//GEN-LAST:event_Btn_FirstActionPerformed

    private void Btn_LastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_LastActionPerformed
        // TODO add your handling code here:
        pos = getProductList().size()-1;
        ShowItem(pos);
    }//GEN-LAST:event_Btn_LastActionPerformed

    private void Btn_NextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_NextActionPerformed
        // TODO add your handling code here:
        if(pos >= getProductList().size()-1)
        {
            pos = 0;
        }else{
            pos ++; 
        }
        ShowItem(pos);
    }//GEN-LAST:event_Btn_NextActionPerformed

    private void Btn_PreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_PreviousActionPerformed
        // TODO add your handling code here:
        if(pos <= 0)
        {
            pos = getProductList().size()-1;
        }else{
            pos --;
        }
        ShowItem(pos);
    }//GEN-LAST:event_Btn_PreviousActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main_Panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main_Panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main_Panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main_Panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main_Panel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btn_Choose;
    private javax.swing.JButton Btn_Delete;
    private javax.swing.JButton Btn_First;
    private javax.swing.JButton Btn_Insert;
    private javax.swing.JButton Btn_Last;
    private javax.swing.JButton Btn_Next;
    private javax.swing.JButton Btn_Previous;
    private javax.swing.JButton Btn_Update;
    private javax.swing.JTable JTable_Products;
    private org.jdesktop.swingx.calendar.DatePickerFormatter datePickerFormatter1;
    private org.jdesktop.swingx.calendar.DatePickerFormatter datePickerFormatter2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_image;
    private org.jdesktop.swingx.JXDatePicker txt_addDate;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_name;
    private javax.swing.JTextField txt_price;
    private javax.swing.JTextField txt_stock;
    // End of variables declaration//GEN-END:variables
}
