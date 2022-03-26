package com.jcg.hibernate.crud.operations.visao;

import com.jcg.hibernate.crud.operations.controle.CrimeCT;
import com.jcg.hibernate.crud.operations.modelo.Crime;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TelaCrime extends JFrame implements ActionListener {
    public CrimeCT controle_crime = new CrimeCT();

    private JPanel contentPane;
    private JTextField txtTipoCrime;
    private JTextField txtLocalCrime;
    private JTextField txtDescricaoCrime;
    private JTextField txtDataCrime;
    private JTextField txtVisto;


    private JTextField txtTel;
    private String txtID;
    private JComboBox cbPesquisar;
    private ButtonGroup bt = new ButtonGroup();


    private JButton btnSalvar;
    private JButton btnExcluir;
    private JButton btnEditar;
    private JButton btnPesquisar;
    private JButton btnLimpar;

    public TelaCrime() {
        setTitle("Cadastro de Crimes");
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

        JLabel lblTipo = new JLabel("Tipo:");
        lblTipo.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
        lblTipo.setBounds(10, 79, 109, 14);
        contentPane.add(lblTipo);

        JLabel lblDescricao = new JLabel("Descricao:");
        lblDescricao.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
        lblDescricao.setBounds(10, 104, 109, 14);
        contentPane.add(lblDescricao);

        JLabel lblLocal = new JLabel("Local:");
        lblLocal.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
        lblLocal.setBounds(10, 129, 134, 14);
        contentPane.add(lblLocal);

        JLabel labelData = new JLabel("Data:");
        labelData.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
        labelData.setBounds(10, 154, 159, 14);
        contentPane.add(labelData);

        JLabel labelVisto = new JLabel("Visto:");
        labelVisto.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
        labelVisto.setBounds(10, 176, 184, 14);
        contentPane.add(labelVisto);

        cbPesquisar = new JComboBox();
        cbPesquisar.setEditable(true);
        cbPesquisar.setBounds(129, 28, 283, 20);

        contentPane.add(cbPesquisar);

        txtTipoCrime = new JTextField();
        txtTipoCrime.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
        txtTipoCrime.setBounds(129, 76, 283, 20);
        contentPane.add(txtTipoCrime);
        txtTipoCrime.setColumns(10);

        txtDescricaoCrime = new JTextField();
        txtDescricaoCrime.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
        txtDescricaoCrime.setBounds(129, 101, 365, 20);
        contentPane.add(txtDescricaoCrime);
        txtDescricaoCrime.setColumns(10);

        txtLocalCrime = new JTextField();
        txtLocalCrime.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
        txtLocalCrime.setBounds(129, 126, 365, 20);
        contentPane.add(txtLocalCrime);
        txtLocalCrime.setColumns(10);

        txtDataCrime = new JTextField();
        txtDataCrime.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
        txtDataCrime.setBounds(129, 151, 365, 20);
        contentPane.add(txtDataCrime);
        txtDataCrime.setColumns(10);

        txtVisto = new JTextField();
        txtVisto.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
        txtVisto.setBounds(129, 176, 365, 20);
        contentPane.add(txtVisto);
        txtVisto.setColumns(10);


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
        this.carregaListaCrime();
    }


    public Crime montaCrime() {
        //Pega os dados digitados nos campos do formulário e atribui ao objeto da classe Crime;
        Crime c = new Crime();
        c.setTipo(this.txtTipoCrime.getText());
        c.setDescricao(this.txtDescricaoCrime.getText());
        c.setLocal(this.txtLocalCrime.getText());
        c.setData(this.txtDataCrime.getText());
        c.setVisto(this.getBooleanVisto(this.txtVisto.getText()));
        return c;
    }


    private Crime editaCrime(int id) {
        Crime c = new Crime();
        c.setId(id);
        c.setTipo(this.txtTipoCrime.getText());
        c.setData(this.txtDataCrime.getText());
        c.setLocal(this.txtLocalCrime.getText());
        c.setDescricao(this.txtDescricaoCrime.getText());
        c.setVisto(this.getBooleanVisto(this.txtVisto.getText()));
        return c;
    }

    private String getVistoText(boolean visto){
        return  visto ? "sim" : "não";
    }

    private boolean getBooleanVisto(String visto){
        return  visto.compareToIgnoreCase("sim") == 0 ? true : false;
    }




    public void carregaCrimenaTela(Crime c2) {
        this.txtTipoCrime.setText(c2.getTipo());
        this.txtDataCrime.setText(c2.getData());
        this.txtLocalCrime.setText(String.valueOf(c2.getLocal()));
        this.txtDescricaoCrime.setText(c2.getDescricao());
        this.txtVisto.setText(this.getVistoText(c2.isVisto()));

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


    public void carregaListaCrime() {
        List<Crime> crimes = controle_crime.getCrimes();
        cbPesquisar.removeAllItems();
        for (Crime crime : crimes) {
            cbPesquisar.addItem(crime.getDescricao());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(this.btnSalvar.getActionCommand())) {
            //Condicional - se clicar no botão Salvar ...
            Crime c = this.montaCrime();
            //Chama o método montaContato para pegar os dados e gravar no objeto c;
            //Instancia a classe de controle CrimeCT;
            controle_crime.postCrime(c);
            this.limpaTela();
            //Limpa os campos após inserir/salvar dados no banco;
            this.carregaListaCrime();
            //Carrega a lista do combobox, atualizando após inserção;
            JOptionPane.showMessageDialog(null, "Crime " + txtDescricaoCrime.getText() + " cadastrado...");
            //Abre diálogo de mensagem, informando que o cliente foi cadastrado;
        } else if (e.getActionCommand().equals(this.btnPesquisar.getActionCommand())) {
            //Condicional - se clicar no botão buscar ...
            //Instancia a classe de controle CrimeCT;
            String descricaoDigitada = cbPesquisar.getSelectedItem().toString().trim();
            Crime cbusca = controle_crime.select(descricaoDigitada);
            if (cbusca.getDescricao().contains(descricaoDigitada.trim())) {
                JOptionPane.showMessageDialog(null, "Crime encontrado!");
                this.carregaCrimenaTela(cbusca);
            } else {
                JOptionPane.showMessageDialog(null, "Crime nao cadastrado...");

            }
        } else if (e.getActionCommand().equals(this.btnLimpar.getActionCommand())) {
            this.limpaTela();
        } else if (e.getActionCommand().equals(this.btnExcluir.getActionCommand())) {

            Crime cbusca = controle_crime.select(cbPesquisar.getSelectedItem().toString());
            if (cbusca == null)
                JOptionPane.showMessageDialog(null, "Crime nao cadastrado...");
            else {
                JOptionPane.showMessageDialog(null, "Crime excluido!");
                this.carregaCrimenaTela(cbusca);
                controle_crime.delete(cbusca);
                this.limpaTela();
                this.carregaListaCrime();
            }
        }
        if (e.getActionCommand().equals(this.btnEditar.getActionCommand())) {

            //Instancia a classe de controle CrimeCT;
            Crime cbusca = controle_crime.select(cbPesquisar.getSelectedItem().toString());
            if (cbusca == null)
                JOptionPane.showMessageDialog(null, "Crime nao cadastrado...");
            else {
                JOptionPane.showMessageDialog(null, "Crime editado!");
                cbusca.setDescricao(txtDescricaoCrime.getText());
                cbusca.setTipo(txtTipoCrime.getText());
                cbusca.setData(txtDataCrime.getText());
                cbusca.setLocal(txtLocalCrime.getText());
                cbusca.setVisto(this.getBooleanVisto(txtVisto.getText()));
                controle_crime.update(this.editaCrime(cbusca.getId()));
                this.limpaTela();
                this.carregaListaCrime();
            }
        }


    }


}