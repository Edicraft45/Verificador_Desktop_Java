

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class NewJFrame extends javax.swing.JFrame {
    private Dimension size;
    private String codigo = "";
    private int width, height;
    
    public NewJFrame() {
       
        initComponents();
        ventanainicial();
        
    }
    
    public void ventanainicial(){
        this.getContentPane().setBackground(new Color(143, 188, 143));
        size = Toolkit. getDefaultToolkit(). getScreenSize();
        
        width=Toolkit. getDefaultToolkit(). getScreenSize().width;
        height=Toolkit. getDefaultToolkit(). getScreenSize().height;
        
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
                
        this.setLayout(null);
        jLabel3.setText("<html><img src='file:C:\\Users\\Edi\\source\\repos\\VerificadorDePrecios_java\\src\\main\\java\\logo.png' width='305' height='165'>");
        
        jLabel3.setLocation(this.getWidth()/2-jLabel3.getWidth()/2, 10);
 
        jLabel4.setSize(120, 120);
        jLabel4.setIcon(new ImageIcon(new ImageIcon("src/main/java/barcode-scan.gif").getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT)));
        jLabel4.setLocation(this.getWidth()/2-jLabel4.getWidth()/2, this.getHeight()/2 + jLabel3.getHeight() + 10);

        
        jLabel5.setSize(width-200,150);
        jLabel5.setLocation((this.getWidth() / 2 + 100)- jLabel5.getWidth() / 2, this.getHeight() / 2 - 150);
        jLabel5.setText("Favor de escanear el código del producto");
    }

    public void start() {
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                //Do stuff
                ventanainicial();
                t.cancel(); 
                t.cancel();
            }
        }, 4000, 4000);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setSize(Toolkit. getDefaultToolkit(). getScreenSize().width, Toolkit. getDefaultToolkit(). getScreenSize().height);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jLabel3.setText("jLabel3");
        jLabel3.setPreferredSize(new java.awt.Dimension(305, 165));

        jLabel4.setText("jLabel4");
        jLabel4.setMaximumSize(new java.awt.Dimension(1000, 1000));
        jLabel4.setPreferredSize(new java.awt.Dimension(120, 120));

        jLabel5.setFont(new java.awt.Font("Lucida Console", 1, 36)); // NOI18N
        jLabel5.setText("jLabel5");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 684, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(113, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed

        if (evt.getKeyCode()!= 10) {
            codigo += evt.getKeyChar();
        }
        else{
            try{
                Connection con = null;
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/verificador_de_precios","root","");
                Statement stmt = con.createStatement();
                ResultSet rs=stmt.executeQuery("SELECT producto_nombre, producto_precio, producto_cantidad, producto_imagen FROM productos WHERE producto_codigo = "+codigo);

                
                if(rs.next()){
                    jLabel5.setSize(width-200,250);
                    String path = rs.getString(4);
                    URL url = new URL(path);
                    BufferedImage image = ImageIO.read(url);
                    jLabel4.setSize(200, 200);
                    jLabel4.setIcon(new ImageIcon(new ImageIcon(image).getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
                    jLabel4.setLocation((this.getWidth() / 4) - (jLabel4.getWidth() / 2 - 600), (this.getHeight() / 2) - jLabel4.getHeight() / 2 - 20);
                    jLabel5.setText("<html>Nombre: "+rs.getString(1)+"<br>Precio: "+rs.getString(2)+"<br>Cantidad en almacén: "+rs.getString(3));
                    start();
                }else{
                    
                    jLabel5.setSize(width-200,150);
                    jLabel5.setLocation((this.getWidth() / 2 + 300)- jLabel5.getWidth() / 2, this.getHeight() / 2 - 150);
                    jLabel5.setText("Producto no encontrado");
                    
                    jLabel4.setSize(200, 200);
                    jLabel4.setIcon(new ImageIcon(new ImageIcon("src/main/java/error.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
                    jLabel4.setLocation(this.getWidth()/2-jLabel4.getWidth()/2, this.getHeight()/2 + jLabel3.getHeight() - 100);
                    start();
                }
                
                
            }catch(ClassNotFoundException e){
              JOptionPane.showMessageDialog(null, e.toString());  
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Ha ocurrido un error"+e.toString());
                
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e.toString());
            }
            codigo = "";
        }
    }//GEN-LAST:event_formKeyPressed

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
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    // End of variables declaration//GEN-END:variables
}
