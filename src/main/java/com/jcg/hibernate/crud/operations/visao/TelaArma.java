package com.jcg.hibernate.crud.operations.visao;

import com.jcg.hibernate.crud.operations.controle.ArmaCT;
import com.jcg.hibernate.crud.operations.modelo.Arma;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TelaArma extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JTextField txtNome;
    private JTextField txtTipo;

    private JTextField txtTel;
    private String txtID;
    private JComboBox<String> cbPesquisarArma;
    private ButtonGroup bt = new ButtonGroup();


    private JButton btnSalvar;
    private JButton btnExcluir;
    private JButton btnEditar;
    private JButton btnPesquisar;
    private JButton btnLimpar;

    public TelaArma() {
        setTitle("Cadastro Arma");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 513);
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

        JLabel lblTipo = new JLabel("Tipo:");
        lblTipo.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
        lblTipo.setBounds(10, 104, 109, 14);
        contentPane.add(lblTipo);

        cbPesquisarArma = new JComboBox<>();
        cbPesquisarArma.setEditable(true);
        cbPesquisarArma.setBounds(129, 28, 283, 20);

        contentPane.add(cbPesquisarArma);
        txtNome = new JTextField();
        txtNome.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
        txtNome.setBounds(129, 76, 283, 20);
        contentPane.add(txtNome);
        txtNome.setColumns(10);

        txtTipo = new JTextField();
        txtTipo.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
        txtTipo.setBounds(129, 101, 365, 20);
        contentPane.add(txtTipo);
        txtTipo.setColumns(10);


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
        this.carregaListaArma();

    }
    public Arma montaArma(){
            //Pega os dados digitados nos campos do formulário e atribui ao objeto da classe Contato;
             Arma c = new Arma();
             c.setNome(this.txtNome.getText());
             c.setTipo(this.txtTipo.getText());
             return c;
            }
    public Arma editaArma(int i){
        //Pega os dados digitados nos campos do formulário e atribui ao objeto da classe Contato;
        Arma c = new Arma();
        c.setId(i);
        c.setNome(this.txtNome.getText());
        c.setTipo(this.txtTipo.getText());
        return c;
    }
    public void carregaArmanaTela(Arma c2){
        //Pega os dados digitados nos campos do formulário e atribui ao objeto da classe Contato;
        this.txtNome.setText(c2.getNome());
        this.txtTipo.setText(c2.getTipo());
    }

    public void limpaTela(){
              for(int i = 0; i < contentPane.getComponentCount(); i++){
                    //laço de repetição percorrendo o contentPane - JPanel, o painel principal do form
                    Component c = contentPane.getComponent(i);
                  //Cria um objeto Component c que recebe o componente na posição i do laço for
                    if(c instanceof JTextField){ //se o componente c for uma instância de JTextField
                JTextField campo = (JTextField) c;
                //cria uma variável JTextField recebendo o componente c com um cast
                         campo.setText(null);
                          //apaga o conteúdo do campo JTextField;
                        }
                  }

            }

    public void carregaListaArma(){
            //Preenche Combobox com registros do banco de dados
            ArmaCT mbc = new ArmaCT();
            List<Arma> armaBd = mbc.getArmas();
            cbPesquisarArma.removeAllItems();
            for (Arma arma : armaBd) {
                cbPesquisarArma.addItem(arma.getNome());
            }
    }

    ArmaCT armaCT = new ArmaCT();

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(this.btnSalvar.getActionCommand())) {
            //Condicional - se clicar no botão Salvar ...
            Arma a = this.montaArma();
            //Chama o método montaContato para pegar os dados e gravar no objeto c;
            //Instancia a classe de controle ArmaCT;
            armaCT.createArma(a.getTipo(), a.getNome());
            this.limpaTela();
            //Limpa os campos após inserir/salvar dados no banco;
            this.carregaListaArma();
            //Carrega a lista do combobox, atualizando após inserção;
            JOptionPane.showMessageDialog(null, "Arma " + txtNome.getText() + " cadastrada...");
            //Abre diálogo de mensagem, informando que o cliente foi cadastrada;
        } else if (e.getActionCommand().equals(this.btnPesquisar.getActionCommand())) {
            //Condicional - se clicar no botão buscar ...
            //Instancia a classe de controle ArmaCT;
            String nomeDigitado = cbPesquisarArma.getSelectedItem().toString().trim();
            Arma abusca = armaCT.select(nomeDigitado);
            if (abusca.getNome().contains(nomeDigitado.trim())) {
                JOptionPane.showMessageDialog(null, "Arma encontrado!");
                this.carregaArmanaTela(abusca);
            } else {
                JOptionPane.showMessageDialog(null, "Arma nao cadastrada...");

            }
        } else if (e.getActionCommand().equals(this.btnLimpar.getActionCommand())) {
            this.limpaTela();
        } else if (e.getActionCommand().equals(this.btnExcluir.getActionCommand())) {


            Arma abusca = armaCT.select(cbPesquisarArma.getSelectedItem().toString());
            if (abusca == null)
                JOptionPane.showMessageDialog(null, "Arma nao cadastrada...");
            else {
                JOptionPane.showMessageDialog(null, "Arma excluido!");
                this.carregaArmanaTela(abusca);
                armaCT.delete(abusca);
                this.limpaTela();
                this.carregaListaArma();
            }
        }
        if (e.getActionCommand().equals(this.btnEditar.getActionCommand())) {

            //Instancia a classe de controle ArmaCT;
            Arma abusca = armaCT.select(cbPesquisarArma.getSelectedItem().toString());
            if (abusca == null)
                JOptionPane.showMessageDialog(null, "Arma nao cadastrada...");
            else {
                JOptionPane.showMessageDialog(null, "Arma editado!");
                abusca.setNome(txtNome.getText());
                abusca.setTipo(txtTipo.getText());
                armaCT.update(this.editaArma(abusca.getId()));
                this.limpaTela();
                this.carregaListaArma();
            }
        }


    }

    
}