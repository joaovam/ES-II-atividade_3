package com.jcg.hibernate.crud.operations.visao;

import com.jcg.hibernate.crud.operations.modelo.Criminoso;
import com.jcg.hibernate.crud.operations.modelo.CriminosoVitima;
import com.jcg.hibernate.crud.operations.modelo.Vitima;
import com.jcg.hibernate.crud.operations.controle.CriminosoCT;
import com.jcg.hibernate.crud.operations.controle.CriminosoVitimaCT;
import com.jcg.hibernate.crud.operations.controle.VitimaCT;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TelaCriminosoVitima extends JFrame implements ActionListener {

    private JPanel contentPane;

    private JTextField txtNomeCriminoso;
    private JTextField txtCpfCriminoso;
    private JTextField txtIdadeCriminoso;
    private JTextField txtGeneroCriminoso;
    private String txtIDCriminoso;

    private JTextField txtNomeVitima;
    private JTextField txtCpfVitima;
    private JTextField txtIdadeVitima;
    private JTextField txtGeneroVitima;
    private String txtIDVitima;


    private JComboBox cbPesquisarCriminoso;
    private JComboBox cbPesquisarVitima;
    private ButtonGroup bt = new ButtonGroup();


    private JButton btnSalvar;
    private JButton btnExcluir;
    private JButton btnEditar;
    private JButton btnPesquisarCriminosoVitima;
    private JButton btnPesquisarVitima;

    private JButton btnLimpar;

    public TelaCriminosoVitima() {
        setTitle("Associação de criminoso e vitima");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 213);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);


        cbPesquisarCriminoso = new JComboBox();
        cbPesquisarCriminoso.setEditable(true);
        cbPesquisarCriminoso.setBounds(129, 28, 283, 20);

        cbPesquisarVitima = new JComboBox();
        cbPesquisarVitima.setEditable(true);
        cbPesquisarVitima.setBounds(420, 28, 283, 20);

        contentPane.add(cbPesquisarCriminoso);
        contentPane.add(cbPesquisarVitima);



        btnSalvar = new JButton("Incluir associação");
        btnSalvar.setBounds(193, 57, 180, 23);
        btnSalvar.addActionListener(this);
        btnSalvar.setActionCommand("salvar");
        contentPane.add(btnSalvar);



        btnLimpar = new JButton("Limpar");
        btnLimpar.setBounds(360, 327, 75, 23);
        btnLimpar.setText("Limpar");
        btnLimpar.addActionListener(this);
        btnLimpar.setActionCommand("limpar");
//        contentPane.add(btnLimpar);

        btnExcluir = new JButton("");
        btnExcluir.setBounds(440, 57, 180, 23);
        btnExcluir.setText("Excluir associação");
        btnExcluir.addActionListener(this);
        btnExcluir.setActionCommand("excluir");
        contentPane.add(btnExcluir);

        btnPesquisarCriminosoVitima = new JButton("Buscar associação");
        btnPesquisarCriminosoVitima.setBounds(730, 22, 180, 23);
        btnPesquisarCriminosoVitima.addActionListener(this);
        btnPesquisarCriminosoVitima.setActionCommand("pesquisar");
        contentPane.add(btnPesquisarCriminosoVitima);
        txtIDCriminoso = "";

        this.carregaListaCriminoso();
        this.carregaListaVitima();

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

    public void carregaListaCriminoso() {
        //Preenche Combobox com registros do banco de dados
        CriminosoVitimaCT mbc = new CriminosoVitimaCT();
        List<Criminoso> CriminosoBd = mbc.getCriminosos();
        cbPesquisarCriminoso.removeAllItems();
        for (Criminoso criminoso : CriminosoBd) {
            cbPesquisarCriminoso.addItem(criminoso.getNome());
        }
    }

    public void carregaListaVitima() {
        //Preenche Combobox com registros do banco de dados
        CriminosoVitimaCT mbc = new CriminosoVitimaCT();
        List<Vitima> VitimaBd = mbc.getVitimas();
        cbPesquisarVitima.removeAllItems();
        for (Vitima vitima : VitimaBd) {
            cbPesquisarVitima.addItem(vitima.getNome());
        }

    }

    CriminosoVitimaCT criminosoVitimaCT = new CriminosoVitimaCT();
    CriminosoCT criminosoCT = new CriminosoCT();
    VitimaCT vitimaCT = new VitimaCT();

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(this.btnSalvar.getActionCommand())) {
            //Condicional - se clicar no botão Salvar ...
            //Instancia a classe de controle ContatoCT;
            try {
                String nomeCriminosoDigitado = cbPesquisarCriminoso.getSelectedItem().toString().trim();
                Criminoso criminoso = criminosoCT.select(nomeCriminosoDigitado);

                String nomeVitimaDigitado = cbPesquisarVitima.getSelectedItem().toString().trim();
                Vitima vitima = vitimaCT.select(nomeVitimaDigitado);

                if (!criminoso.getNome().contains(nomeCriminosoDigitado))
                    JOptionPane.showMessageDialog(null, "Criminoso não cadastrado...");

                if (!vitima.getNome().contains(nomeVitimaDigitado))
                    JOptionPane.showMessageDialog(null, "Vítima não cadastrada...");

                criminosoVitimaCT.createCriminosoVitima(nomeCriminosoDigitado, nomeVitimaDigitado);


                //Chama o método insert da classe ContatoCT para inserir os dados do objeto Contato (c) de montaContato no banco;
                this.limpaTela();
                //Limpa os campos após inserir/salvar dados no banco;
                this.carregaListaCriminoso();
                this.carregaListaVitima();
                //Carrega a lista do combobox, atualizando após inserção;
                JOptionPane.showMessageDialog(null, "Associação " + nomeCriminosoDigitado +" e" + nomeVitimaDigitado +"cadastrada...");
                //Abre diálogo de mensagem, informando que o cliente foi cadastrado;
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Criminoso " + txtNomeCriminoso.getText() + " não associado à vitima...");
            }
        } else if (e.getActionCommand().equals(this.btnPesquisarCriminosoVitima.getActionCommand())) {
            //Condicional - se clicar no botão buscar ...
            try {
                //Instancia a classe de controle ContatoCT;
                String nomeCriminosoDigitado = cbPesquisarCriminoso.getSelectedItem().toString().trim();
                Criminoso cbusca = criminosoCT.select(nomeCriminosoDigitado);

                String nomeVitimaDigitado = cbPesquisarVitima.getSelectedItem().toString().trim();
                Vitima vbusca = vitimaCT.select(nomeVitimaDigitado);

                CriminosoVitima cv = criminosoVitimaCT.select(cbusca, vbusca);


                if (!cbusca.getNome().contains(nomeCriminosoDigitado)) {

                    JOptionPane.showMessageDialog(null, "Criminoso nao cadastrado...");

                }
                if (!vbusca.getNome().contains(nomeVitimaDigitado)) {

                    JOptionPane.showMessageDialog(null, "Vitima nao cadastrada...");
                }
                if (cv.getCriminoso() != null && cv.getVitima() != null) {
                    JOptionPane.showMessageDialog(null, "Relação encontrada! " + cbusca.getNome() + "cometeu um crime cuja vítima foi " + vbusca.getNome());
                } else {
                    JOptionPane.showMessageDialog(null, "Associação não encontrada...");
                }

                this.carregaListaCriminoso();
                this.carregaListaVitima();
            }catch (Exception ex){
                JOptionPane.showMessageDialog(null, "Algo deu errado... - não há associação! ");
            }
        } else if (e.getActionCommand().equals(this.btnLimpar.getActionCommand())) {
            this.limpaTela();
        } else if (e.getActionCommand().equals(this.btnExcluir.getActionCommand())) {


            Vitima vitima = vitimaCT.select(cbPesquisarVitima.getSelectedItem().toString());
            Criminoso criminoso = criminosoCT.select(cbPesquisarCriminoso.getSelectedItem().toString());
            if (criminoso == null  || vitima == null)
                JOptionPane.showMessageDialog(null, "Vítima e/ou criminoso não cadastrados...");
            else {
                JOptionPane.showMessageDialog(null, "Relação excluída!");
                criminosoVitimaCT.delete(cbPesquisarCriminoso.getSelectedItem().toString(), cbPesquisarVitima.getSelectedItem().toString());
                this.limpaTela();
                this.carregaListaCriminoso();
                this.carregaListaVitima();
            }

    }


}


}