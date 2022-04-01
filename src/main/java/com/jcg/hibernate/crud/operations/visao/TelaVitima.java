package com.jcg.hibernate.crud.operations.visao;

import com.jcg.hibernate.crud.operations.modelo.Vitima;
import com.jcg.hibernate.crud.operations.controle.VitimaCT;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TelaVitima extends JFrame implements ActionListener {
    public VitimaCT controle_vitima = new VitimaCT();

    private JPanel contentPane;
    private JTextField txtNomeVitima;
    private JTextField txtIdadeVitima;
    private JTextField txtCpfVitima;
    private JTextField txtGeneroVitima;


    private JTextField txtTel;
    private String txtID;
    private JComboBox cbPesquisar;
    private ButtonGroup bt = new ButtonGroup();


    private JButton btnSalvar;
    private JButton btnExcluir;
    private JButton btnEditar;
    private JButton btnPesquisar;
    private JButton btnLimpar;

    public TelaVitima() {
        setTitle("Cadastro de Vitimas");
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

        txtNomeVitima = new JTextField();
        txtNomeVitima.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
        txtNomeVitima.setBounds(129, 76, 283, 20);
        contentPane.add(txtNomeVitima);
        txtNomeVitima.setColumns(10);

        txtCpfVitima = new JTextField();
        txtCpfVitima.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
        txtCpfVitima.setBounds(129, 101, 365, 20);
        contentPane.add(txtCpfVitima);
        txtCpfVitima.setColumns(10);

        txtIdadeVitima = new JTextField();
        txtIdadeVitima.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
        txtIdadeVitima.setBounds(129, 126, 365, 20);
        contentPane.add(txtIdadeVitima);
        txtIdadeVitima.setColumns(10);

        txtGeneroVitima = new JTextField();
        txtGeneroVitima.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
        txtGeneroVitima.setBounds(129, 151, 365, 20);
        contentPane.add(txtGeneroVitima);
        txtGeneroVitima.setColumns(10);

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
        this.carregaListaVitima();
    }

    public Vitima montaVitima() {
        //Pega os dados digitados nos campos do formulário e atribui ao objeto da classe Vitima;
        Vitima c = new Vitima();
        c.setNome(this.txtNomeVitima.getText());
        c.setCpf(this.txtCpfVitima.getText());
        c.setIdade(Integer.valueOf(this.txtIdadeVitima.getText()));
        c.setGenero(this.txtGeneroVitima.getText());
        return c;
    }

    private Vitima editaVitima(int id) {
        Vitima c = new Vitima();
        c.setId(id);
        c.setNome(this.txtNomeVitima.getText());
        c.setGenero(this.txtGeneroVitima.getText());
        c.setIdade(Integer.valueOf(this.txtIdadeVitima.getText()));
        c.setCpf(this.txtCpfVitima.getText());
        return c;
    }

    public void carregaVitimanaTela(Vitima c2) {
        this.txtNomeVitima.setText(c2.getNome());
        this.txtGeneroVitima.setText(c2.getGenero());
        this.txtIdadeVitima.setText(String.valueOf(c2.getIdade()));
        this.txtCpfVitima.setText(c2.getCpf());
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


    public void carregaListaVitima() {
        List<Vitima> vitimas = controle_vitima.getVitimas();
        cbPesquisar.removeAllItems();
        for (Vitima vitima : vitimas) {
            cbPesquisar.addItem(vitima.getNome().trim());

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(this.btnSalvar.getActionCommand())) {
            //Condicional - se clicar no botão Salvar ...
            Vitima c = this.montaVitima();
            //Chama o método montaVitima para pegar os dados e gravar no objeto c;
            //Instancia a classe de controle VitimaCT;
            controle_vitima.postVitima(c);
            this.limpaTela();
            //Limpa os campos após inserir/salvar dados no banco;
            this.carregaListaVitima();
            //Carrega a lista do combobox, atualizando após inserção;
            JOptionPane.showMessageDialog(null, "Vitima " + txtNomeVitima.getText() + " cadastrado...");
            //Abre diálogo de mensagem, informando que o cliente foi cadastrado;
        } else if (e.getActionCommand().equals(this.btnPesquisar.getActionCommand())) {
            //Condicional - se clicar no botão buscar ...
            //Instancia a classe de controle VitimaCT;
            String nomeDigitado = cbPesquisar.getSelectedItem().toString().trim();
            Vitima cbusca = controle_vitima.select(nomeDigitado);
            if (cbusca.getNome().contains(nomeDigitado.trim())) {
                JOptionPane.showMessageDialog(null, "Vitima encontrado!");
                this.carregaVitimanaTela(cbusca);
            } else {
                JOptionPane.showMessageDialog(null, "Vitima nao cadastrado...");

            }
        } else if (e.getActionCommand().equals(this.btnLimpar.getActionCommand())) {
            this.limpaTela();
        } else if (e.getActionCommand().equals(this.btnExcluir.getActionCommand())) {


            Vitima cbusca = controle_vitima.select(cbPesquisar.getSelectedItem().toString());
            if (cbusca == null)
                JOptionPane.showMessageDialog(null, "Vítima nao cadastrado...");
            else {
                JOptionPane.showMessageDialog(null, "Vítima excluido!");
                this.carregaVitimanaTela(cbusca);
                controle_vitima.delete(cbusca);
                this.limpaTela();
                this.carregaListaVitima();
            }
        }
        if (e.getActionCommand().equals(this.btnEditar.getActionCommand())) {

            //Instancia a classe de controle VitimaCT;
            Vitima cbusca = controle_vitima.select(cbPesquisar.getSelectedItem().toString());
            if (cbusca == null)
                JOptionPane.showMessageDialog(null, "Vítima nao cadastrada...");
            else {
                JOptionPane.showMessageDialog(null, "Vítima editada!");
                cbusca.setCpf(txtCpfVitima.getText());
                cbusca.setNome(txtNomeVitima.getText());
                cbusca.setGenero(txtGeneroVitima.getText());
                cbusca.setIdade(Integer.valueOf(txtIdadeVitima.getText()));
                controle_vitima.update(this.editaVitima(cbusca.getId()));
                this.limpaTela();
                this.carregaListaVitima();
            }
        }


    }


}