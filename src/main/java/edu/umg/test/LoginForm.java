// LoginForm.java
package edu.umg.test;

import edu.umg.datos.UsuarioDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Clase que representa un formulario de inicio de sesión (Login Form) usando Java Swing.
 */
public class LoginForm extends JFrame {
    private JTextField textFieldUsername;   // Campo de texto para el nombre de usuario
    private JPasswordField passwordField;   // Campo de contraseña
    private JButton loginButton;            // Botón para iniciar sesión
    private JPanel mainPanel;               // Panel principal del formulario

    /**
     * Constructor de la clase. Inicializa y configura los componentes del formulario.
     */
    public LoginForm() {
        setContentPane(mainPanel);
        setTitle("Login Form");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Configurar el ActionListener para el botón de inicio de sesión
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener el nombre de usuario y la contraseña ingresados por el usuario
                String username = textFieldUsername.getText();
                String password = new String(passwordField.getPassword());

                try {
                    // Validar las credenciales del usuario
                    if (validateUser(username, password)) {
                        JOptionPane.showMessageDialog(LoginForm.this, "Login successful!");
                    } else {
                        JOptionPane.showMessageDialog(LoginForm.this, "Invalid username or password");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    /**
     * Método privado para validar las credenciales del usuario utilizando la clase UsuarioDAO.
     *
     * @param username Nombre de usuario ingresado.
     * @param password Contraseña ingresada.
     * @return true si las credenciales son válidas, false en caso contrario.
     * @throws SQLException Si ocurre un error al interactuar con la base de datos.
     */
    private boolean validateUser(String username, String password) throws SQLException {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        return usuarioDAO.validateUser(username, password);
    }

    /**
     * Método principal que crea una instancia del formulario y lo hace visible en la interfaz gráfica.
     *
     * @param args Argumentos de la línea de comandos (no se utilizan en este caso).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginForm().setVisible(true);
            }
        });
    }
}
