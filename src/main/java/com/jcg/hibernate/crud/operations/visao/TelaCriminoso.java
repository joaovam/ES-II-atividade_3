package com.jcg.hibernate.crud.operations.visao;

import com.jcg.hibernate.crud.operations.Contato;
import com.jcg.hibernate.crud.operations.Criminoso;
import com.jcg.hibernate.crud.operations.DbOperations_Criminoso;
import com.jcg.hibernate.crud.operations.controle.CriminosoCT;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TelaCriminoso extends JFrame implements ActionListener {
    public CriminosoCT controle_criminoso = new CriminosoCT();

    private JPanel contentPane;
    private JTextField txtNomeCriminoso;
    private JTextField txtIdadeCriminoso;
    private JTextField txtCpfCriminoso;
    private JTextField txtGeneroCriminoso;


    private JTextField txtTel;
    private String txtID;
    private JComboBox cbPesquisar;
    private ButtonGroup bt = new ButtonGroup();


    private JButton btnSalvar;
    private JButton btnExcluir;
    private JButton btnEditar;
    private JButton btnPesquisar;
    private JButton btnLimpar;

    public TelaCriminoso() {
        setTitle("Cadastro de Criminosos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 556, 413);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblPesquisar = new JLabel("Pesquisar:");
        lblPesquisar.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
        lblPesquisar.setBounds(10, 31, 109, 14);
        contentPane.add(lblPesquisar);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
        lblNome.setBounds(10, 79, 109, 14);
        contentPane.add(lblNome);

        JLabel lblCpf = new JLabel("CPF:");
        lblCpf.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
        lblCpf.setBounds(10, 104, 109, 14);
        contentPane.add(lblCpf);

        JLabel lblIdade = new JLabel("Idade:");
        lblIdade.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
        lblIdade.setBounds(10, 129, 109, 14);
        contentPane.add(lblIdade);

        JLabel lelGenero = new JLabel("Genero:");
        lelGenero.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
        lelGenero.setBounds(10, 154, 109, 14);
        contentPane.add(lelGenero);

        cbPesquisar = new JComboBox();
        cbPesquisar.setEditable(true);
        cbPesquisar.setBounds(129, 28, 283, 20);

        contentPane.add(cbPesquisar);

        txtNomeCriminoso = new JTextField();
        txtNomeCriminoso.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
        txtNomeCriminoso.setBounds(129, 76, 283, 20);
        contentPane.add(txtNomeCriminoso);
        txtNomeCriminoso.setColumns(10);

        txtCpfCriminoso = new JTextField();
        txtCpfCriminoso.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
        txtCpfCriminoso.setBounds(129, 101, 365, 20);
        contentPane.add(txtCpfCriminoso);
        txtCpfCriminoso.setColumns(10);

        txtIdadeCriminoso = new JTextField();
        txtIdadeCriminoso.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
        txtIdadeCriminoso.setBounds(129, 126, 365, 20);
        contentPane.add(txtIdadeCriminoso);
        txtIdadeCriminoso.setColumns(10);

        txtGeneroCriminoso = new JTextField();
        txtGeneroCriminoso.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
        txtGeneroCriminoso.setBounds(129, 151, 365, 20);
        contentPane.add(txtGeneroCriminoso);
        txtGeneroCriminoso.setColumns(10);


//        try {
//            txtTel = new JTextField();
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        txtTel.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
//        txtTel.setBounds(129, 126, 143, 20);
//        contentPane.add(txtTel);


        btnSalvar = new JButton("Incluir");
        btnSalvar.setBounds(193, 327, 75, 23);
        btnSalvar.addActionListener(this);
        btnSalvar.setActionCommand("salvar");
        contentPane.add(btnSalvar);

        btnEditar = new JButton("Editar");
        btnEditar.setBounds(280, 327, 75, 23);
        btnEditar.setText("Editar");
        btnEditar.addActionListener(this);
        btnEditar.setActionCommand("editar");
        contentPane.add(btnEditar);

        btnLimpar = new JButton("Limpar");
        btnLimpar.setBounds(360, 327, 75, 23);
        btnLimpar.setText("Limpar");
        btnLimpar.addActionListener(this);
        btnLimpar.setActionCommand("limpar");
        contentPane.add(btnLimpar);

        btnExcluir = new JButton("");
        btnExcluir.setBounds(440, 327, 75, 23);
        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(this);
        btnExcluir.setActionCommand("excluir");
        contentPane.add(btnExcluir);

        btnPesquisar = new JButton("Buscar");
        btnPesquisar.setBounds(422, 22, 80, 23);
        btnPesquisar.addActionListener(this);
        btnPesquisar.setActionCommand("pesquisar");
        contentPane.add(btnPesquisar);
        txtID = "";
        this.carregaListaCriminoso();
    }

    public Contato montaContato() {
        //Pega os dados digitados nos campos do formulário e atribui ao objeto da classe Contato;
        Contato c = new Contato();
        c.setNome(this.txtNomeCriminoso.getText());
        c.setEndereco(this.txtIdadeCriminoso.getText());
        c.setTelefone(this.txtTel.getText());
        return c;
    }

    public Criminoso montaCriminoso() {
        //Pega os dados digitados nos campos do formulário e atribui ao objeto da classe Criminoso;
        Criminoso c = new Criminoso();
        c.setNome(this.txtNomeCriminoso.getText());
        c.setCpf(this.txtCpfCriminoso.getText());
        c.setIdade(Integer.valueOf(this.txtIdadeCriminoso.getText()));
        c.setGenero(this.txtGeneroCriminoso.getText());
        return c;
    }

    public Contato editaContato(int i) {
        //Pega os dados digitados nos campos do formulário e atribui ao objeto da classe Contato;
        Contato c = new Contato();
        c.setId(i);
        c.setNome(this.txtNomeCriminoso.getText());
        c.setEndereco(this.txtIdadeCriminoso.getText());
        c.setTelefone(this.txtTel.getText());
        return c;
    }

    private Criminoso editaCriminoso(int id) {
        Criminoso c = new Criminoso();
        c.setId(id);
        c.setNome(this.txtNomeCriminoso.getText());
        c.setGenero(this.txtGeneroCriminoso.getText());
        c.setIdade(Integer.valueOf(this.txtIdadeCriminoso.getText()));
        c.setCpf(this.txtCpfCriminoso.getText());
        return c;
    }

    public void carregaContatonaTela(Contato c2) {
//        //Pega os dados digitados nos campos do formulário e atribui ao objeto da classe Contato;
//        this.txtNome.setText(c2.getNome());
//        this.txtEndereco.setText(c2.getEndereco());
//        this.txtTel.setText(c2.getTelefone());

    }

    public void carregaCriminosonaTela(Criminoso c2) {
        this.txtNomeCriminoso.setText(c2.getNome());
        this.txtGeneroCriminoso.setText(c2.getGenero());
        this.txtIdadeCriminoso.setText(String.valueOf(c2.getIdade()));
        this.txtCpfCriminoso.setText(c2.getCpf());
    }

    public void limpaTela() {
        for (int i = 0; i < contentPane.getComponentCount(); i++) {
            //laço de repetição percorrendo o contentPane - JPanel, o painel principal do form
            Component c = contentPane.getComponent(i);
            //Cria um objeto Component c que recebe o componente na posição i do laço for
            if (c instanceof JTextField) { //se o componente c for uma instância de JTextField
                JTextField campo = (JTextField) c;
                //cria uma variável JTextField recebendo o componente c com um cast
                campo.setText(null);
                //apaga o conteúdo do campo JTextField;
            }
        }

    }

    public void carregaLista() {
        //Preenche Combobox com registros do banco de dados
//            controle.CriminosoCT mbc = new CriminosoCT();
//
//            List<Contato> ContatoBd = mbc.getContatos();
//           cbPesquisar.removeAllItems();
//            for (Contato contato : ContatoBd) {
//                cbPesquisar.addItem(contato.getNome());
//            }
    }

    public void carregaListaCriminoso() {
        List<Criminoso> criminosos = controle_criminoso.getCriminosos();
        cbPesquisar.removeAllItems();
        for (Criminoso criminoso : criminosos) {
            cbPesquisar.addItem(criminoso.getNome().trim());

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(this.btnSalvar.getActionCommand())) {
            //Condicional - se clicar no botão Salvar ...
            Criminoso c = this.montaCriminoso();
            //Chama o método montaContato para pegar os dados e gravar no objeto c;
            //Instancia a classe de controle CriminosoCT;
            controle_criminoso.postCriminoso(c);
            this.limpaTela();
            //Limpa os campos após inserir/salvar dados no banco;
            this.carregaListaCriminoso();
            //Carrega a lista do combobox, atualizando após inserção;
            JOptionPane.showMessageDialog(null, "Criminoso " + txtNomeCriminoso.getText() + " cadastrado...");
            //Abre diálogo de mensagem, informando que o cliente foi cadastrado;
        } else if (e.getActionCommand().equals(this.btnPesquisar.getActionCommand())) {
            //Condicional - se clicar no botão buscar ...
            //Instancia a classe de controle CriminosoCT;
            String nomeDigitado = cbPesquisar.getSelectedItem().toString().trim();
            Criminoso cbusca = controle_criminoso.select(nomeDigitado);
            if (cbusca.getNome().contains(nomeDigitado.trim())) {
                JOptionPane.showMessageDialog(null, "Criminoso encontrado!");
                this.carregaCriminosonaTela(cbusca);
            } else {
                JOptionPane.showMessageDialog(null, "Criminoso nao cadastrado...");

            }
        } else if (e.getActionCommand().equals(this.btnLimpar.getActionCommand())) {
            this.limpaTela();
        } else if (e.getActionCommand().equals(this.btnExcluir.getActionCommand())) {


            Criminoso cbusca = controle_criminoso.select(cbPesquisar.getSelectedItem().toString());
            if (cbusca == null)
                JOptionPane.showMessageDialog(null, "Criminoso nao cadastrado...");
            else {
                JOptionPane.showMessageDialog(null, "Criminoso excluido!");
                this.carregaCriminosonaTela(cbusca);
                controle_criminoso.delete(cbusca);
                this.limpaTela();
                this.carregaListaCriminoso();
            }
        }
        if (e.getActionCommand().equals(this.btnEditar.getActionCommand())) {

            //Instancia a classe de controle CriminosoCT;
            Criminoso cbusca = controle_criminoso.select(cbPesquisar.getSelectedItem().toString());
            if (cbusca == null)
                JOptionPane.showMessageDialog(null, "Criminoso nao cadastrado...");
            else {
                JOptionPane.showMessageDialog(null, "Criminoso editado!");
                cbusca.setCpf(txtCpfCriminoso.getText());
                cbusca.setNome(txtNomeCriminoso.getText());
                cbusca.setGenero(txtGeneroCriminoso.getText());
                cbusca.setIdade(Integer.valueOf(txtIdadeCriminoso.getText()));
                controle_criminoso.update(this.editaCriminoso(cbusca.getId()));
                this.limpaTela();
                this.carregaListaCriminoso();
            }
        }


    }


}