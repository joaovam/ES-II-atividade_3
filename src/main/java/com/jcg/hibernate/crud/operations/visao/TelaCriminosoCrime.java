package com.jcg.hibernate.crud.operations.visao;

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

        JLabel lblid = new JLabel("id do crime:");
        lblid.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
        lblid.setBounds(10, 104, 109, 14);
        contentPane.add(lblid);



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
        //contentPane.add(txtNomeCriminoso);
        txtIdCrime.setColumns(10);

        txtNomeCriminoso = new JTextField();
        txtNomeCriminoso.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
        txtNomeCriminoso.setBounds(129, 101, 365, 20);
        //contentPane.add(txtIdCrime);
        txtNomeCriminoso.setColumns(10);


        btnSalvar = new JButton("Incluir");
        btnSalvar.setBounds(193, 327, 75, 23);
        btnSalvar.addActionListener(this);
        btnSalvar.setActionCommand("salvar");
        contentPane.add(btnSalvar);

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
                cbPesquisarCriminoso.addItem(criminoso.getNome().trim());
            }
    }
    public void carregaListaCrime(){
        //Preenche Combobox com registros do banco de dados
        CriminosoCrimeCT mbc = new CriminosoCrimeCT();
        List<Crime> CrimeBd =  mbc.getCrimes();
        cbPesquisarCrime.removeAllItems();
        for (Crime crime : CrimeBd) {
            cbPesquisarCrime.addItem(crime.getDescricao());
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

                String nomeDigitado = cbPesquisarCrime.getSelectedItem().toString().trim();
                Crime crime = crimeCT.select(nomeDigitado);

                if (!criminoso.getNome().contains(nomeCriminosoDigitado))
                    JOptionPane.showMessageDialog(null, "Criminoso nao cadastrado...");

                if (!crime.getDescricao().contains(nomeDigitado))
                    JOptionPane.showMessageDialog(null, "Crime nao cadastrado...");

                criminosoCrimeCT.createCriminosoCrime(criminoso,crime);



                //Chama o método insert da classe ContatoCT para inserir os dados do objeto Contato (c) de montaContato no banco;
                this.limpaTela();
                //Limpa os campos após inserir/salvar dados no banco;
                this.carregaListaCriminoso();
                this.carregaListaCrime();
                //Carrega a lista do combobox, atualizando após inserção;
                JOptionPane.showMessageDialog(null, "Associação " + nomeCriminosoDigitado +" e" + nomeDigitado +"cadastrada...");
                //Abre diálogo de mensagem, informando que o cliente foi cadastrado;
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Criminoso " + txtNomeCriminoso.getText() + " não associado ao crime...");
            }
        } else if (e.getActionCommand().equals(this.btnPesquisar.getActionCommand())) {
            //Condicional - se clicar no botão buscar ...
            try {
                //Instancia a classe de controle ContatoCT;
                String nomeCriminosoDigitado = cbPesquisarCriminoso.getSelectedItem().toString().trim();
                Criminoso cbusca = criminosoCT.select(nomeCriminosoDigitado);

                String idCrime = cbPesquisarCrime.getSelectedItem().toString().trim();
                Crime crime = crimeCT.select(idCrime);

                CriminosoCrime cv = criminosoCrimeCT.select(cbusca, crime);


                if (!cbusca.getNome().contains(nomeCriminosoDigitado)) {

                    JOptionPane.showMessageDialog(null, "Criminoso nao cadastrado...");

                }
                if (!crime.getDescricao().contains(idCrime)) {

                    JOptionPane.showMessageDialog(null, "Crime nao cadastrada...");
                }
                if (cv.getCriminoso() != null && cv.getCrime() != null) {
                    JOptionPane.showMessageDialog(null, "Relação encontrada! " + cbusca.getNome() + "cometeu um crime cuja descrição foi " + crime.getDescricao());
                } else {
                    JOptionPane.showMessageDialog(null, "Associação não encontrada...");
                }

                this.carregaListaCriminoso();
                this.carregaListaCrime();
            }catch (Exception ex){
                JOptionPane.showMessageDialog(null, "Algo deu errado... - não há associação! ");
            }
        } else if (e.getActionCommand().equals(this.btnLimpar.getActionCommand())) {
            this.limpaTela();
        } else if (e.getActionCommand().equals(this.btnExcluir.getActionCommand())) {


            Crime crime = crimeCT.select(cbPesquisarCrime.getSelectedItem().toString());
            Criminoso criminoso = criminosoCT.select(cbPesquisarCriminoso.getSelectedItem().toString());
            if (criminoso == null  || crime == null)
                JOptionPane.showMessageDialog(null, "Crime e/ou criminoso nao cadastrados...");
            else {
                JOptionPane.showMessageDialog(null, "Relação excluida!");
                criminosoCrimeCT.delete(cbPesquisarCriminoso.getSelectedItem().toString(),cbPesquisarCrime.getSelectedItem().toString());

                this.limpaTela();
                this.carregaListaCriminoso();
                this.carregaListaCrime();
            }

        }


    }
    

    
}