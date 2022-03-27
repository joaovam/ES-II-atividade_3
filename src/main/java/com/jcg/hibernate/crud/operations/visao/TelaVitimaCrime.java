package com.jcg.hibernate.crud.operations.visao;




import com.jcg.hibernate.crud.operations.controle.CrimeCT;
import com.jcg.hibernate.crud.operations.controle.CriminosoVitimaCT;
import com.jcg.hibernate.crud.operations.controle.VitimaCT;
import com.jcg.hibernate.crud.operations.controle.VitimaCrimeCT;
import com.jcg.hibernate.crud.operations.dbOperations.DbOperations_Crime;
import com.jcg.hibernate.crud.operations.dbOperations.DbOperations_Vitima;
import com.jcg.hibernate.crud.operations.dbOperations.DbOperations_Vitima_Crime;
import com.jcg.hibernate.crud.operations.modelo.*;

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




        btnSalvar = new JButton("Incluir associação");
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
        btnExcluir.setText("Excluir associação");
        btnExcluir.addActionListener(this);
        btnExcluir.setActionCommand("excluir");
        contentPane.add(btnExcluir);

        btnPesquisar = new JButton("Buscar associação");
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
            VitimaCrimeCT mbc = new VitimaCrimeCT();
            List<Crime> CrimeBd = mbc.getCrimes();
                cbPesquisarCrime.removeAllItems();
            for (Crime crime : CrimeBd) {
                cbPesquisarCrime.addItem(crime.getId() + "-" + crime.getDescricao());
            }
    }
    public void carregaListaVitima(){
            //Preenche Combobox com registros do banco de dados
            VitimaCrimeCT mbc = new VitimaCrimeCT();
            List<Vitima> VitimaBd = mbc.getVitimas();
            cbPesquisarVitima.removeAllItems();
            for (Vitima vitima : VitimaBd) {
                cbPesquisarVitima.addItem(vitima.getNome());
            }

    }

    VitimaCrimeCT vitimaCrimeCT = new VitimaCrimeCT();
    VitimaCT vitimaCT = new VitimaCT();
    CrimeCT crimeCT = new CrimeCT();

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(this.btnSalvar.getActionCommand())) {
            //Condicional - se clicar no botão Salvar ...
            //Instancia a classe de controle ContatoCT;
            try {
                String nomeCrimeDigitado = cbPesquisarCrime.getSelectedItem().toString().trim();
                Crime crime = crimeCT.select(nomeCrimeDigitado);

                String nomeVitimaDigitado = cbPesquisarVitima.getSelectedItem().toString().trim();
                Vitima vitima = vitimaCT.select(nomeVitimaDigitado);

                if (crime.getId()!=Integer.parseInt(nomeCrimeDigitado))
                    JOptionPane.showMessageDialog(null, "Crime nao cadastrado...");

                if (!vitima.getNome().contains(nomeVitimaDigitado))
                    JOptionPane.showMessageDialog(null, "Vitima nao cadastrada...");

                vitimaCrimeCT.createVitimaCrime(crime,vitima);


                //Chama o método insert da classe ContatoCT para inserir os dados do objeto Contato (c) de montaContato no banco;
                this.limpaTela();
                //Limpa os campos após inserir/salvar dados no banco;
                this.carregaListaCrime();
                this.carregaListaVitima();
                //Carrega a lista do combobox, atualizando após inserção;
                JOptionPane.showMessageDialog(null, "Associação " + nomeCrimeDigitado +" e" + nomeVitimaDigitado +"cadastrada...");
                //Abre diálogo de mensagem, informando que o cliente foi cadastrado;
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Criminoso " + txtIdCrime.getText() + " não associado À vitima...");
            }
        } else if (e.getActionCommand().equals(this.btnPesquisar.getActionCommand())) {
            //Condicional - se clicar no botão buscar ...
            try {
                //Instancia a classe de controle ContatoCT;
                String nomeCrimeDigitado = cbPesquisarCrime.getSelectedItem().toString().trim();
                Crime cbusca = crimeCT.select(Integer.parseInt(nomeCrimeDigitado));

                String nomeVitimaDigitado = cbPesquisarVitima.getSelectedItem().toString().trim();
                Vitima vbusca = vitimaCT.select(nomeVitimaDigitado);

                VitimaCrime cv = vitimaCrimeCT.select(vbusca,cbusca);


                if (cbusca.getId()!=Integer.parseInt(nomeCrimeDigitado)) {

                    JOptionPane.showMessageDialog(null, "Crime nao cadastrado...");

                }
                if (!vbusca.getNome().contains(nomeVitimaDigitado)) {

                    JOptionPane.showMessageDialog(null, "Vitima nao cadastrada...");
                }
                if (cv.getCrime() != null && cv.getVitima() != null) {
                    JOptionPane.showMessageDialog(null, "Relação encontrada! " + vbusca.getNome() + "foi acometido pelo crime " + cbusca.toString());
                } else {
                    JOptionPane.showMessageDialog(null, "Associação não encontrada...");
                }

                this.carregaListaCrime();
                this.carregaListaVitima();
            }catch (Exception ex){
                JOptionPane.showMessageDialog(null, "Algo deu errado... - não há associação! ");
            }
        } else if (e.getActionCommand().equals(this.btnLimpar.getActionCommand())) {
            this.limpaTela();
        } else if (e.getActionCommand().equals(this.btnExcluir.getActionCommand())) {


            Vitima vitima = vitimaCT.select(cbPesquisarVitima.getSelectedItem().toString());
            Crime crime = crimeCT.select(cbPesquisarCrime.getSelectedItem().toString());
            if (crime == null  || vitima == null)
                JOptionPane.showMessageDialog(null, "Vitima e/ou crime nao cadastrados...");
            else {
                JOptionPane.showMessageDialog(null, "Relação excluida!");
                vitimaCrimeCT.delete(Integer.parseInt( cbPesquisarCrime.getSelectedItem().toString()), cbPesquisarVitima.getSelectedItem().toString());
                this.limpaTela();
                this.carregaListaCrime();
                this.carregaListaVitima();
            }

        }


    }
    

    
}