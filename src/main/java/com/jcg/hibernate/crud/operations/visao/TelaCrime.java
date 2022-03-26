package com.jcg.hibernate.crud.operations.visao;

import com.jcg.hibernate.crud.operations.modelo.Contato;
import com.jcg.hibernate.crud.operations.modelo.Crime;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCrime extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JTextField txtTipoCrime;
    private JTextField descricaoCrime;
    private JTextField vistoCrime;
    private JTextField localCrime;
    private JTextField dataCrime;
    private JTextField idadeVitima;
    private JTextField cpfVitima;
    private JTextField generoVitima;
    private JTextField nomeVitima;
    private JTextField idadeCriminoso;
    private JTextField cpfCriminoso;
    private JTextField generoCriminoso;
    private JTextField nomeCriminoso;

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
        setBounds(100, 100, 900, 900);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblPesquisar = new JLabel("Pesquisar:");
        lblPesquisar.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
        lblPesquisar.setBounds(10, 31, 109, 14);
        contentPane.add(lblPesquisar);

        JLabel lblTipo = new JLabel("Tipo do crime:");
        lblTipo.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
        lblTipo.setBounds(10, 79, 109, 14);
        contentPane.add(lblTipo);

        JLabel lblDescricao = new JLabel("Descricao do crime:");
        lblDescricao.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
        lblDescricao.setBounds(10, 104, 109, 14);
        contentPane.add(lblDescricao);

        JLabel lblLocal = new JLabel("Local do crime:");
        lblLocal.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
        lblLocal.setBounds(10, 129, 109, 14);
        contentPane.add(lblLocal);

        JLabel lblData = new JLabel("Data do crime:");
        lblData.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
        lblData.setBounds(10, 154, 109, 14);
        contentPane.add(lblData);

        JLabel lblVisto = new JLabel("Visto do crime:");
        lblVisto.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
        lblVisto.setBounds(10, 179, 109, 14);
        contentPane.add(lblVisto);

        JLabel lblVitimaNome = new JLabel("Nome da vítima:");
        lblVitimaNome.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
        lblVitimaNome.setBounds(10, 204, 109, 14);
        contentPane.add(lblVitimaNome);

        JLabel lblVitimaGenero = new JLabel("Genero da vítima:");
        lblVitimaGenero.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
        lblVitimaGenero.setBounds(10, 229, 109, 14);
        contentPane.add(lblVitimaGenero);

        JLabel lblVitimaIdade = new JLabel("Idade da vítima:");
        lblVitimaIdade.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
        lblVitimaIdade.setBounds(10, 254, 109, 14);
        contentPane.add(lblVitimaIdade);

        JLabel lblVitimaCpf = new JLabel("Cpf da vítima:");
        lblVitimaCpf.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
        lblVitimaCpf.setBounds(10, 279, 109, 14);
        contentPane.add(lblVitimaCpf);

        JLabel lblCriminosoNome = new JLabel("Nome do criminoso:");
        lblCriminosoNome.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
        lblCriminosoNome.setBounds(10, 304, 109, 14);
        contentPane.add(lblCriminosoNome);

        JLabel lblCriminosoGenero = new JLabel("Genero do criminoso:");
        lblCriminosoGenero.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
        lblCriminosoGenero.setBounds(10, 329, 109, 14);
        contentPane.add(lblCriminosoGenero);

        JLabel lblCriminosoIdade = new JLabel("Idade do criminoso:");
        lblCriminosoIdade.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
        lblCriminosoIdade.setBounds(10, 354, 109, 14);
        contentPane.add(lblCriminosoIdade);

        JLabel lblCriminosoCpf = new JLabel("Cpf do criminoso:");
        lblCriminosoCpf.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
        lblCriminosoCpf.setBounds(10, 379, 109, 14);
        contentPane.add(lblCriminosoCpf);



        cbPesquisar = new JComboBox();
        cbPesquisar.setEditable(true);
        cbPesquisar.setBounds(129, 28, 283, 20);

       contentPane.add(cbPesquisar);

        txtTipoCrime = new JTextField();
        txtTipoCrime.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
        txtTipoCrime.setBounds(129, 79, 365, 20);
        contentPane.add(txtTipoCrime);
        txtTipoCrime.setColumns(10);

        descricaoCrime = new JTextField();
        descricaoCrime.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
        descricaoCrime.setBounds(129, 104, 365, 20);
        contentPane.add(descricaoCrime);
        descricaoCrime.setColumns(10);

        localCrime = new JTextField();
        localCrime.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
        localCrime.setBounds(129, 129, 365, 20);
        contentPane.add(localCrime);
        localCrime.setColumns(10);

        dataCrime = new JTextField();
        dataCrime.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
        dataCrime.setBounds(129, 154, 365, 20);
        contentPane.add(dataCrime);
        dataCrime.setColumns(10);


        vistoCrime = new JTextField();
        vistoCrime.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
        vistoCrime.setBounds(129, 179, 365, 20);
        contentPane.add(vistoCrime);
        vistoCrime.setColumns(10);


        nomeVitima = new JTextField();
        nomeVitima.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
        nomeVitima.setBounds(129, 204, 365, 20);
        contentPane.add(nomeVitima);
        nomeVitima.setColumns(10);

        generoVitima = new JTextField();
        generoVitima.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
        generoVitima.setBounds(129, 229, 365, 20);
        contentPane.add(generoVitima);
        generoVitima.setColumns(10);

        idadeVitima = new JTextField();
        idadeVitima.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
        idadeVitima.setBounds(129, 254, 365, 20);
        contentPane.add(idadeVitima);
        idadeVitima.setColumns(10);

        cpfVitima = new JTextField();
        cpfVitima.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
        cpfVitima.setBounds(129, 279, 365, 20);
        contentPane.add(cpfVitima);
        cpfVitima.setColumns(10);

        nomeCriminoso = new JTextField();
        nomeCriminoso.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
        nomeCriminoso.setBounds(129, 304, 365, 20);
        contentPane.add(nomeCriminoso);
        nomeCriminoso.setColumns(10);

        generoCriminoso = new JTextField();
        generoCriminoso.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
        generoCriminoso.setBounds(129, 329, 365, 20);
        contentPane.add(generoCriminoso);
        generoCriminoso.setColumns(10);

        idadeCriminoso = new JTextField();
        idadeCriminoso.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
        idadeCriminoso.setBounds(129, 354, 365, 20);
        contentPane.add(idadeCriminoso);
        idadeCriminoso.setColumns(10);

        cpfCriminoso = new JTextField();
        cpfCriminoso.setFont(new Font("Franklin Gothic Book", Font.BOLD, 12));
        cpfCriminoso.setBounds(129, 379, 365, 20);
        contentPane.add(cpfCriminoso);
        cpfCriminoso.setColumns(10);




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
        btnSalvar.setBounds(193, 404, 75, 23);
        btnSalvar.addActionListener(this);
        btnSalvar.setActionCommand("salvar");
        contentPane.add(btnSalvar);

        btnEditar = new JButton("Editar");
        btnEditar.setBounds(280, 404, 75, 23);
        btnEditar.setText("Editar");
        btnEditar.addActionListener(this);
        btnEditar.setActionCommand("editar");
        contentPane.add(btnEditar);

        btnLimpar = new JButton("Limpar");
        btnLimpar.setBounds(360, 404, 75, 23);
        btnLimpar.setText("Limpar");
        btnLimpar.addActionListener(this);
        btnLimpar.setActionCommand("limpar");
        contentPane.add(btnLimpar);

        btnExcluir = new JButton("");
        btnExcluir.setBounds(440, 404, 75, 23);
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
        this.carregaLista();
    }
    public Contato montaContato(){
            //Pega os dados digitados nos campos do formulário e atribui ao objeto da classe Contato;
            Crime crime = new Crime();


             Contato c = new Contato();
             c.setNome(this.txtTipoCrime.getText());
             c.setEndereco(this.descricaoCrime.getText());
             c.setTelefone(this.txtTel.getText());
             return c;
            }
    public Contato editaContato(int i){
        //Pega os dados digitados nos campos do formulário e atribui ao objeto da classe Contato;
        Contato c = new Contato();
        c.setId(i);
        c.setNome(this.txtTipoCrime.getText());
        c.setEndereco(this.descricaoCrime.getText());
        c.setTelefone(this.txtTel.getText());
        return c;
    }
    public void carregaContatonaTela(Contato c2){
//        //Pega os dados digitados nos campos do formulário e atribui ao objeto da classe Contato;
//        this.txtTipoCrime.setText(c2.getNome());
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
    public void carregaLista(){
            //Preenche Combobox com registros do banco de dados
//            controle.ContatoCT mbc = new ContatoCT();
//
//            List<Contato> ContatoBd = mbc.getContatos();
//           cbPesquisar.removeAllItems();
//            for (Contato contato : ContatoBd) {
//                cbPesquisar.addItem(contato.getNome());
//            }
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
                this.carregaLista();
               //Carrega a lista do combobox, atualizando após inserção;
                JOptionPane.showMessageDialog(null, "Contato "+txtTipoCrime.getText()+" cadastrado...");
               //Abre diálogo de mensagem, informando que o cliente foi cadastrado;
              } else
            if(e.getActionCommand().equals(this.btnPesquisar.getActionCommand())){
                //Condicional - se clicar no botão buscar ...
//                controle.ContatoCT cbc = new ContatoCT();
                //Instancia a classe de controle ContatoCT;
                String nomeDigitado = cbPesquisar.getSelectedItem().toString().trim();
//                Contato cbusca = cbc.select(nomeDigitado);
//                if  (cbusca.getNome().equals(nomeDigitado))
//                { JOptionPane.showMessageDialog(null, "Contato encontrado!");
//                    this.carregaContatonaTela(cbusca);}
//                else
//                {      JOptionPane.showMessageDialog(null, "Contato nao cadastrado...");
//
//                }
            } else
            if(e.getActionCommand().equals(this.btnLimpar.getActionCommand())) {
              this.limpaTela();
            } else
            if(e.getActionCommand().equals(this.btnExcluir.getActionCommand())){
                //Condicional - se clicar no botão buscar ...
//                controle.ContatoCT cbc = new ContatoCT();
                //Instancia a classe de controle ContatoCT;

//                Contato cbusca = cbc.select(cbPesquisar.getSelectedItem().toString());
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
//            Contato cbusca = cbc.select(cbPesquisar.getSelectedItem().toString());
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