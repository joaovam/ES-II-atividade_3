package com.jcg.hibernate.crud.operations.visao;

import com.jcg.hibernate.crud.operations.*;
import com.jcg.hibernate.crud.operations.controle.*;
import com.jcg.hibernate.crud.operations.dbOperations.DbOperations_Crime;
import com.jcg.hibernate.crud.operations.dbOperations.DbOperations_Criminoso;
import com.jcg.hibernate.crud.operations.modelo.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TelaCriminosoCrime extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JTextField txtIdCrime;
    private JTextField txtNomeCriminoso;

    private JTextField txtTel;
    private String txtID;
    private JComboBox<String> cbPesquisarCriminoso;
    private JComboBox<String> cbPesquisarCrime;
    private ButtonGroup bt = new ButtonGroup();


    private JButton btnSalvar;
    private JButton btnExcluir;
    private JButton btnEditar;
    private JButton btnPesquisar;
    private JButton btnLimpar;

    public TelaCriminosoCrime() {
        setTitle("Associação de criminoso e crime");
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

        JLabel lblNome = new JLabel("Nome do criminoso:");
        lblNome.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
        lblNome.setBounds(10, 79, 109, 14);
        contentPane.add(lblNome);

        JLabel lblEndereo = new JLabel("id do crime:");
        lblEndereo.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
        lblEndereo.setBounds(10, 104, 109, 14);
        contentPane.add(lblEndereo);

        JLabel lblTel = new JLabel("Telefone:");
        lblTel.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
        lblTel.setBounds(10, 129, 109, 14);
        contentPane.add(lblTel);

        cbPesquisarCriminoso = new JComboBox<>();
        cbPesquisarCriminoso.setEditable(true);
        cbPesquisarCriminoso.setBounds(129, 28, 283, 20);

        cbPesquisarCrime = new JComboBox<String>();
        cbPesquisarCrime.setEditable(true);
        cbPesquisarCrime.setBounds(520, 28, 283, 20);

       contentPane.add(cbPesquisarCriminoso);
        contentPane.add(cbPesquisarCrime);
        txtIdCrime = new JTextField();
        txtIdCrime.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
        txtIdCrime.setBounds(129, 76, 283, 20);
        contentPane.add(txtNomeCriminoso);
        txtIdCrime.setColumns(10);

        txtNomeCriminoso = new JTextField();
        txtNomeCriminoso.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
        txtNomeCriminoso.setBounds(129, 101, 365, 20);
        contentPane.add(txtIdCrime);
        txtNomeCriminoso.setColumns(10);



        try {
            txtTel = new JTextField();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        txtTel.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
        txtTel.setBounds(129, 126, 143, 20);
        contentPane.add(txtTel);



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
        this.carregaListaCrime();

    }
    public CriminosoCrime montaCriminosoCrime(){
            //Pega os dados digitados nos campos do formulário e atribui ao objeto da classe Contato;
            CriminosoCrime c = new CriminosoCrime();
             c.setCrime(DbOperations_Crime.findCrimeById(Integer.parseInt(txtIdCrime.getText())));
             c.setCriminoso(DbOperations_Criminoso.getByName(txtNomeCriminoso.getText()));
             return c;
            }
//    public Contato editaContato(int i){
//        //Pega os dados digitados nos campos do formulário e atribui ao objeto da classe Contato;
//        Contato c = new Contato();
//        c.setId(i);
//        c.setNome(this.txtNome.getText());
//        c.setEndereco(this.txtEndereco.getText());
//        c.setTelefone(this.txtTel.getText());
//        return c;
//    }
    //public void carregaCriminosonaTela(Contato c2){
//        //Pega os dados digitados nos campos do formulário e atribui ao objeto da classe Contato;
//        this.txtNome.setText(c2.getNome());
//        this.txtEndereco.setText(c2.getEndereco());
//        this.txtTel.setText(c2.getTelefone());

    //}
    public void carregaCriminosonaTela(Criminoso criminoso){
//        //Pega os dados digitados nos campos do formulário e atribui ao objeto da classe Contato;
//        this.txtNome.setText(c2.getNome());
//        this.txtEndereco.setText(c2.getEndereco());
//        this.txtTel.setText(c2.getTelefone());

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

    public void carregaListaCriminoso(){
            //Preenche Combobox com registros do banco de dados
            CriminosoCrimeCT mbc = new CriminosoCrimeCT();
            List<Criminoso> CriminosoBd = mbc.getCriminosos();
            cbPesquisarCriminoso.removeAllItems();
            for (Criminoso criminoso : CriminosoBd) {
                cbPesquisarCriminoso.addItem(criminoso.getNome());
            }
    }
    public void carregaListaCrime(){
        //Preenche Combobox com registros do banco de dados
        CriminosoCrimeCT mbc = new CriminosoCrimeCT();
        List<Crime> CrimeBd =  mbc.getCrimes();
        cbPesquisarCriminoso.removeAllItems();
        for (Crime crime : CrimeBd) {
            cbPesquisarCriminoso.addItem(crime.getId() + "-" + crime.getDescricao());
        }
    }

    CriminosoCrimeCT criminosoCrimeCT = new CriminosoCrimeCT();
    CriminosoCT criminosoCT = new CriminosoCT();
    CrimeCT crimeCT = new CrimeCT();


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(this.btnSalvar.getActionCommand())) {
            //Condicional - se clicar no botão Salvar ...
            //Instancia a classe de controle ContatoCT;
            try {
                String nomeCriminosoDigitado = cbPesquisarCriminoso.getSelectedItem().toString().trim();
                Criminoso criminoso = criminosoCT.select(nomeCriminosoDigitado);

                int idCrimeDigitado = Integer.parseInt(cbPesquisarCrime.getSelectedItem().toString().trim());
                Crime crime = crimeCT.se

                if (!criminoso.getNome().contains(nomeCriminosoDigitado))
                    JOptionPane.showMessageDialog(null, "Criminoso nao cadastrado...");

                if (!vitima.getNome().contains(nomeVitimaDigitado))
                    JOptionPane.showMessageDialog(null, "Vitima nao cadastrada...");

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
                JOptionPane.showMessageDialog(null, "Criminoso " + txtNomeCriminoso.getText() + " não associado À vitima...");
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
                JOptionPane.showMessageDialog(null, "Vitima e/ou criminoso nao cadastrados...");
            else {
                JOptionPane.showMessageDialog(null, "Relação excluida!");
                criminosoVitimaCT.delete(cbPesquisarCriminoso.getSelectedItem().toString(), cbPesquisarVitima.getSelectedItem().toString());
                this.limpaTela();
                this.carregaListaCriminoso();
                this.carregaListaVitima();
            }

        }


    }
    

    
}