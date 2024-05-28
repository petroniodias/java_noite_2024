import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Formulario extends JFrame {

    private JTextField nomeField;
    private JTextField telefoneField;
    private JTextField emailField;

    public Formulario() {
        super("Formulário de Cadastro");

        // Configurações do layout
        setLayout(new GridLayout(4, 2, 5, 5));

        // Adicionando os componentes do formulário
        add(new JLabel("Nome:"));
        nomeField = new JTextField();
        add(nomeField);

        add(new JLabel("Telefone:"));
        telefoneField = new JTextField();
        add(telefoneField);

        add(new JLabel("E-mail:"));
        emailField = new JTextField();
        add(emailField);

        // Botão de submissão
        JButton submitButton = new JButton("Submeter");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = nomeField.getText();
                String telefone = telefoneField.getText();
                String email = emailField.getText();
                
                // Aqui você pode implementar a lógica de submissão
                // Como salvar os dados em um banco de dados ou exibi-los
                JOptionPane.showMessageDialog(Formulario.this, 
                        "Nome: " + nome + "\nTelefone: " + telefone + "\nE-mail: " + email);
            }
        });
        add(submitButton);

        // Configurações da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Formulario().setVisible(true);
            }
        });
    }
}
