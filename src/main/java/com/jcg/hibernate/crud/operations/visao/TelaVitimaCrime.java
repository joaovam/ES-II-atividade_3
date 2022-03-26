package com.jcg.hibernate.crud.operations.visao;

import com.jcg.hibernate.crud.operations.Contato;
import com.jcg.hibernate.crud.operations.Criminoso;
import com.jcg.hibernate.crud.operations.Vitima;
import com.jcg.hibernate.crud.operations.controle.CriminosoVitimaCT;
import com.jcg.hibernate.crud.operations.controle.VitimaCrimeCT;
import com.jcg.hibernate.crud.operations.dbOperations.DbOperations_Crime;
import com.jcg.hibernate.crud.operations.dbOperations.DbOperations_Vitima;
import com.jcg.hibernate.crud.operations.dbOperations.DbOperations_Vitima_Crime;
import com.jcg.hibernate.crud.operations.modelo.Crime;
import com.jcg.hibernate.crud.operations.modelo.VitimaCrime;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TelaVitimaCrime extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JTextField txtNome;
    private JTextField txtIdCrime;


    private String txtID;
    private JComboBox cbPesquisarCrime;
    private JComboBox cbPesquisarVitima;
    private ButtonGroup bt = new ButtonGroup();


    private JButton btnSalvar;
    private JButton btnExcluir;
    private JButton btnEditar;
    private JButton btnPesquisar;
    private JButton btnLimpar;

    public TelaVitimaCrime() {
        setTitle("Associação de criminoso e vitima");
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

        JLabel lblNome = new JLabel("Nome da Vitima:");
        lblNome.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
        lblNome.setBounds(10, 79, 109, 14);
        contentPane.add(lblNome);

        JLabel lblIdCrime = new JLabel("Id do Crime");
        lblIdCrime.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
        lblIdCrime.setBounds(10, 104, 109, 14);
        contentPane.add(lblIdCrime);


        cbPesquisarCrime = new JComboBox();
        cbPesquisarCrime.setEditable(true);
        cbPesquisarCrime.setBounds(129, 28, 283, 20);

        cbPesquisarVitima = new JComboBox();
        cbPesquisarVitima.setEditable(true);
        cbPesquisarVitima.setBounds(520, 28, 283, 20);

       contentPane.add(cbPesquisarCrime);
        contentPane.add(cbPesquisarVitima);
        txtNome = new JTextField();
        txtNome.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
        txtNome.setBounds(129, 76, 283, 20);
        contentPane.add(txtNome);
        txtNome.setColumns(10);

        txtIdCrime = new JTextField();
        txtIdCrime.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
        txtIdCrime.setBounds(129, 101, 365, 20);
        contentPane.add(txtIdCrime);
        txtIdCrime.setColumns(10);




        btnSalvar = new JButton("Incluir");
        btnSalvar.setBounds(193, 327, 75, 23);
        btnSalvar.addActionListener(this);
        btnSalvar.setActionCommand("salvar");
        contentPane.add(btnSalvar);

//        btnEditar = new JButton("Editar");
//        btnEditar.setBounds(280, 327, 75, 23);
//        btnEditar.setText("Editar");
//        btnEditar.addActionListener(this);
//        btnEditar.setActionCommand("editar");
//        contentPane.add(btnEditar);

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
        this.carregaListaVitima();

    }
    public VitimaCrime montaVitimaCrime(){
            //Pega os dados digitados nos campos do formulário e atribui ao objeto da classe Contato;
             VitimaCrime c = new VitimaCrime();
             c.setCrime(DbOperations_Crime.findCrimeById(Integer.parseInt(txtIdCrime.getText())));
             c.setVitima(DbOperations_Vitima.getByName(txtNome.getText()));
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
    public void carregaCriminosonaTela(Contato c2){
//        //Pega os dados digitados nos campos do formulário e atribui ao objeto da classe Contato;
//        this.txtNome.setText(c2.getNome());
//        this.txtEndereco.setText(c2.getEndereco());
//        this.txtTel.setText(c2.getTelefone());

    }
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

    public void carregaListaCrime(){
            //Preenche Combobox com registros do banco de dados
            VitimaCrime mbc = new VitimaCrime();
            List<Crime> CrimeBd = mbc.getCrimes();
                cbPesquisarCrime.removeAllItems();
            for (Criminoso criminoso : CriminosoBd) {
                cbPesquisarCrime.addItem(criminoso.getId() + "-" + criminoso.getNome());
            }
    }
    public void carregaListaVitima(){
            //Preenche Combobox com registros do banco de dados
            CriminosoVitimaCT mbc = new CriminosoVitimaCT();
            List<Vitima> VitimaBd = mbc.getVitimas();
            cbPesquisarVitima.removeAllItems();
            for (Vitima vitima : VitimaBd) {
                cbPesquisarVitima.addItem(vitima.getNome());
            }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals(this.btnSalvar.getActionCommand())){
                //Condicional - se clicar no botão Salvar ...
                 Contato c = this.montaContato();
                 //Chama o método montaContato para pegar os dados e gravar no objeto c;
//                 controle.ContatoCT cbc;
//                 cbc = new ContatoCT();
                //Instancia a classe de controle ContatoCT;
//                 cbc.insert(c);
                //Chama o método insert da classe ContatoCT para inserir os dados do objeto Contato (c) de montaContato no banco;
                this.limpaTela();
                //Limpa os campos após inserir/salvar dados no banco;
                this.carregaListaCriminoso();
                this.carregaListaVitima();
               //Carrega a lista do combobox, atualizando após inserção;
                JOptionPane.showMessageDialog(null, "Contato "+txtNome.getText()+" cadastrado...");
               //Abre diálogo de mensagem, informando que o cliente foi cadastrado;
              } else
            if(e.getActionCommand().equals(this.btnPesquisar.getActionCommand())){
                //Condicional - se clicar no botão buscar ...
                CriminosoVitimaCT criminosoVitimaCT = new CriminosoVitimaCT();
                this.carregaListaCriminoso();
                this.carregaListaVitima();
                //Instancia a classe de controle ContatoCT;
                 String nomeDigitado = cbPesquisarCrime.getSelectedItem().toString().trim();
                 Criminoso cbusca = criminosoVitimaCT.select(nomeDigitado);
                if  (cbusca.getNome().equals(nomeDigitado))
                { JOptionPane.showMessageDialog(null, "Criminoso encontrado!");
                    this.carregaCriminosonaTela(new Criminoso());}
                else
                {      JOptionPane.showMessageDialog(null, "Contato nao cadastrado...");

                }
            } else
            if(e.getActionCommand().equals(this.btnLimpar.getActionCommand())) {
              this.limpaTela();
            } else
            if(e.getActionCommand().equals(this.btnExcluir.getActionCommand())){
                //Condicional - se clicar no botão buscar ...
//                controle.ContatoCT cbc = new ContatoCT();
                //Instancia a classe de controle ContatoCT;

//                Contato cbusca = cbc.select(cbPesquisarCriminoso.getSelectedItem().toString());
//                if  (cbusca==null)
//                    JOptionPane.showMessageDialog(null, "Contato nao cadastrado...");
//                else
//
//                { JOptionPane.showMessageDialog(null, "Contato excluido!");
//                    this.carregaContatonaTela(cbusca);
//                    cbc.delete(cbusca);
//                    this.limpaTela();
//                    this.carregaLista();
//                }
//            }
//        if(e.getActionCommand().equals(this.btnEditar.getActionCommand())){
//            //Condicional - se clicar no botão buscar ...
//            controle.ContatoCT cbc = new ContatoCT();
//            //Instancia a classe de controle ContatoCT;
//
//            Contato cbusca = cbc.select(cbPesquisarCriminoso.getSelectedItem().toString());
//            if  (cbusca==null)
//                JOptionPane.showMessageDialog(null, "Contato nao cadastrado...");
//            else
//
//            { JOptionPane.showMessageDialog(null, "Contato editado!");
//
//                cbc.update(this.editaContato(cbusca.getId()));
//                this.limpaTela();
//                this.carregaLista();
//            }
        }


    }
    

    
}