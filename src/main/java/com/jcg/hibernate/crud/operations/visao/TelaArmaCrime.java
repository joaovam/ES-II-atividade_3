package com.jcg.hibernate.crud.operations.visao;

import com.jcg.hibernate.crud.operations.controle.*;
import com.jcg.hibernate.crud.operations.modelo.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TelaArmaCrime extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JTextField txtNome;
    private JTextField idCrime;


    private String txtID;
    private JComboBox<String> cbPesquisarCrime;
    private JComboBox<String> cbPesquisarArma;
    private ButtonGroup bt = new ButtonGroup();


    private JButton btnSalvar;
    private JButton btnExcluir;
    private JButton btnEditar;
    private JButton btnPesquisar;
    private JButton btnLimpar;

    public TelaArmaCrime() {
        setTitle("Associação de Arma e Crime");
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

//        JLabel lblNome = new JLabel("Nome da Arma:");
//        lblNome.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
//        lblNome.setBounds(10, 79, 109, 14);
//        //contentPane.add(lblNome);
//
//        JLabel lblCrime = new JLabel("ID do crime:");
//        lblCrime.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
//        lblCrime.setBounds(10, 104, 109, 14);
        //contentPane.add(lblCrime);

        cbPesquisarCrime = new JComboBox<>();
        cbPesquisarCrime.setEditable(true);
        cbPesquisarCrime.setBounds(129, 28, 283, 20);

        cbPesquisarArma = new JComboBox<>();
        cbPesquisarArma.setEditable(true);
        cbPesquisarArma.setBounds(520, 28, 283, 20);

       contentPane.add(cbPesquisarCrime);
        contentPane.add(cbPesquisarArma);
        txtNome = new JTextField();
        txtNome.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
        txtNome.setBounds(129, 76, 283, 20);
        //contentPane.add(txtNome);
        txtNome.setColumns(10);

        idCrime = new JTextField();
        idCrime.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
        idCrime.setBounds(129, 101, 365, 20);
        //contentPane.add(idCrime);
        idCrime.setColumns(10);

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
        this.carregaListaArma();
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

    public void carregaListaCrime(){
        //Preenche Combobox com registros do banco de dados
        ArmaCrimeCT mbc = new ArmaCrimeCT();
        List<Crime> CrimeBd =  mbc.getCrimes();
        cbPesquisarCrime.removeAllItems();
        for (Crime crime : CrimeBd) {
            cbPesquisarCrime.addItem(crime.getDescricao());
        }
    }
    public void carregaListaArma(){
            //Preenche Combobox com registros do banco de dados
            ArmaCrimeCT mbc = new ArmaCrimeCT();
            List<Arma> armaBd = mbc.getArma();
            cbPesquisarArma.removeAllItems();
            for (Arma arma : armaBd) {
                cbPesquisarArma.addItem(arma.getNome());
            }

    }

    ArmaCrimeCT armaCrimeCT = new ArmaCrimeCT();
    ArmaCT armaCT = new ArmaCT();
    CrimeCT crimeCT = new CrimeCT();

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(this.btnSalvar.getActionCommand())) {
            //Condicional - se clicar no botão Salvar ...
            //Instancia a classe de controle ContatoCT;
            try {
                String nomeDigitado = cbPesquisarCrime.getSelectedItem().toString().trim();
                Crime crime = crimeCT.select(nomeDigitado);

                String nomeArmaDigitado = cbPesquisarArma.getSelectedItem().toString().trim();
                Arma arma = armaCT.select(nomeArmaDigitado);

                if (!crime.getDescricao().contains(nomeDigitado))
                    JOptionPane.showMessageDialog(null, "Crime nao cadastrado...");

                if (!arma.getNome().contains(nomeArmaDigitado))
                    JOptionPane.showMessageDialog(null, "Vitima nao cadastrada...");

                armaCrimeCT.createArmaCrime(arma,crime);


                //Chama o método insert da classe ContatoCT para inserir os dados do objeto Contato (c) de montaContato no banco;
                this.limpaTela();
                //Limpa os campos após inserir/salvar dados no banco;
                this.carregaListaCrime();
                this.carregaListaArma();
                //Carrega a lista do combobox, atualizando após inserção;
                JOptionPane.showMessageDialog(null, "Associação " + nomeDigitado +" e" + nomeArmaDigitado +"cadastrada...");
                //Abre diálogo de mensagem, informando que o cliente foi cadastrado;
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Crime " + idCrime.getText() + " não associado à arma...");
            }
        } else if (e.getActionCommand().equals(this.btnPesquisar.getActionCommand())) {
            //Condicional - se clicar no botão buscar ...
            try {
                //Instancia a classe de controle ContatoCT;
                String nome = cbPesquisarCrime.getSelectedItem().toString().trim();
                Crime cbusca = crimeCT.select(nome);

                String nomeArmaDigitado = cbPesquisarArma.getSelectedItem().toString().trim();
                Arma vbusca = armaCT.select(nomeArmaDigitado);

                ArmaCrime cv = armaCrimeCT.select(vbusca,cbusca);


                if (!cbusca.getDescricao().contains(nome)) {

                    JOptionPane.showMessageDialog(null, "Crime não cadastrado...");

                }
                if (!vbusca.getNome().contains(nomeArmaDigitado)) {

                    JOptionPane.showMessageDialog(null, "Vitima nao cadastrada...");
                }
                if (cv.getCrime() != null && cv.getArma() != null) {
                    JOptionPane.showMessageDialog(null, "Relação encontrada! Crime de ID" + cbusca.getId() + "foi cometido com arma: " + vbusca.getNome());
                } else {
                    JOptionPane.showMessageDialog(null, "Associação não encontrada...");
                }

                this.carregaListaCrime();
                this.carregaListaArma();
            }catch (Exception ex){
                JOptionPane.showMessageDialog(null, "Algo deu errado... - não há associação! ");
            }
        } else if (e.getActionCommand().equals(this.btnLimpar.getActionCommand())) {
            this.limpaTela();
        } else if (e.getActionCommand().equals(this.btnExcluir.getActionCommand())) {


            Arma arma = armaCT.select(cbPesquisarArma.getSelectedItem().toString());
            Crime crime = crimeCT.select(cbPesquisarCrime.getSelectedItem().toString());
            if (crime == null  || arma == null)
                JOptionPane.showMessageDialog(null, "Arma e/ou Crime nao cadastrados...");
            else {
                JOptionPane.showMessageDialog(null, "Relação excluida!");
                armaCrimeCT.delete(cbPesquisarArma.getSelectedItem().toString(), cbPesquisarCrime.getSelectedItem().toString());
                this.limpaTela();
                this.carregaListaCrime();
                this.carregaListaArma();
            }

        }


    }
    

    
}