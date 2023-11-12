import cadastro.model.util.ConecotrBD;
import cadastro.model.util.PessoaFisicaDAO;
import cadastro.model.util.PessoaJuridicaDAO;
import cadastrobd.model.PessoaFisica;
import cadastrobd.model.PessoaJuridica;

import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        menu();


    }

    private static void menu() {
        PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
        PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();

        Scanner entrada = new Scanner(System.in);
        String textoMenu = """
                1 - Adicionar
                2 - Alterar
                3 - Excluir
                4 - Buscar pelo Id
                5 - Exibir Todos
                0 - Fechar Programa
                """;
        int op = 0;

        do {
            System.out.println(textoMenu);
            try {
                op = Integer.parseInt(entrada.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Tente novamente mais tarde...");
                menu();
            }

            switch (op) {
                case 1:
                    System.out.println("Digite F Pessoa Física e J Pessoa Juridica");
                    String tipoDePessoa = entrada.nextLine();

                    if (tipoDePessoa.equals("F")) {

                        System.out.println("Por favor, insira os dados.");
                        System.out.println("Nome:");
                        String nome = entrada.nextLine();
                        System.out.println("CPF:");
                        String cpf = entrada.nextLine();
                        System.out.println("Logradouro:");
                        String logradouro = entrada.nextLine();
                        System.out.println("Cidade:");
                        String cidade = entrada.nextLine();
                        System.out.println("Estado:");
                        String estado = entrada.nextLine();
                        System.out.println("celular:");
                        String celular = entrada.nextLine();
                        System.out.println("email:");
                        String email = entrada.nextLine();
                        PessoaFisica pessoaFisica = new PessoaFisica(00, nome, logradouro, cidade, estado, celular, email, cpf);

                        try {
                            pessoaFisicaDAO.incluir(pessoaFisica);
                        } catch (RuntimeException e) {
                            throw new RuntimeException(e);
                        }
                    } else if (tipoDePessoa.equals("J")) {
                        System.out.println("Insira os dados.");
                        System.out.println("Nome: ");
                        String nome = entrada.nextLine();
                        System.out.println("CNPJ: ");
                        String cnpj = entrada.nextLine();
                        System.out.println("Logradouro: ");
                        String logradouro = entrada.nextLine();
                        System.out.println("Cidade: ");
                        String cidade = entrada.nextLine();
                        System.out.println("Estado: ");
                        String estado = entrada.nextLine();
                        System.out.println("celular: ");
                        String celular = entrada.nextLine();
                        System.out.println("email: ");
                        String email = entrada.nextLine();
                        PessoaJuridica pessoaJuridica = new PessoaJuridica(0, nome, logradouro, cidade, estado, celular, email, cnpj);

                        try {
                            pessoaJuridicaDAO.incluir(pessoaJuridica);
                        } catch (RuntimeException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        System.out.println("Tente novamente mais tarde");
                    }
                    break;

                case 2:
                    System.out.println("Digite F Pessoa Física e J Pessoa Juridica");
                    tipoDePessoa = entrada.nextLine();

                    if (tipoDePessoa.equals("F")) {

                        System.out.println("Id Pessoa: ");
                        int id = 0;
                        try {
                            id = Integer.parseInt(entrada.nextLine());

                        } catch (NumberFormatException e) {
                            System.out.println("Tente novamente mais tarde");
                            menu();
                        }

                        System.out.println("Insira os dados.");
                        System.out.println("Nome: ");
                        String nome = entrada.nextLine();
                        System.out.println("CPF: ");
                        String cpf = entrada.nextLine();
                        System.out.println("Logradouro: ");
                        String logradouro = entrada.nextLine();
                        System.out.println("Cidade: ");
                        String cidade = entrada.nextLine();
                        System.out.println("Estado: ");
                        String estado = entrada.nextLine();
                        System.out.println("celular: ");
                        String celular = entrada.nextLine();
                        System.out.println("email: ");
                        String email = entrada.nextLine();
                        PessoaFisica pessoaFisica = new PessoaFisica(00, nome, logradouro, cidade, estado, celular, email, cpf);
                        pessoaFisicaDAO.alterar(id, pessoaFisica);


                        try {
                            pessoaFisicaDAO.incluir(pessoaFisica);
                        } catch (RuntimeException e) {
                            throw new RuntimeException(e);
                        }

                    } else if (tipoDePessoa.equals("J")) {
                        System.out.println("Id Pessoa: ");
                        int id = 0;
                        try {
                            id = Integer.parseInt(entrada.nextLine());

                        } catch (NumberFormatException e) {
                            System.out.println("Tente novamente mais tarde");
                            menu();
                        }
                        System.out.println("Insira os dados.");
                        System.out.println("Nome: ");
                        String nome = entrada.nextLine();
                        System.out.println("CNPJ: ");
                        String cnpj = entrada.nextLine();
                        System.out.println("Logradouro: ");
                        String logradouro = entrada.nextLine();
                        System.out.println("Cidade: ");
                        String cidade = entrada.nextLine();
                        System.out.println("Estado: ");
                        String estado = entrada.nextLine();
                        System.out.println("celular: ");
                        String celular = entrada.nextLine();
                        System.out.println("email: ");
                        String email = entrada.nextLine();
                        PessoaJuridica pessoaJuridica = new PessoaJuridica(0, nome, logradouro, cidade, estado, celular, email, cnpj);
                        pessoaJuridicaDAO.alterar(id, pessoaJuridica);

                        try {
                            pessoaJuridicaDAO.incluir(pessoaJuridica);
                        } catch (RuntimeException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        System.out.println("Tente novamente mais tarde");
                    }
                    break;

                case 3:
                    System.out.println("Digite F Pessoa Física e J Pessoa Juridica");
                    tipoDePessoa = entrada.nextLine();

                    if (tipoDePessoa.equals("F")) {
                        System.out.println("Id Pessoa: ");
                        int id = 0;
                        try {
                            id = Integer.parseInt(entrada.nextLine());
                            pessoaFisicaDAO.excluir(id);
                        } catch (NumberFormatException e) {
                            System.out.println("Formato invalido");
                            menu();
                        }
                    } else if (tipoDePessoa.equals("J")) {
                        System.out.println("Id Pessoa: ");
                        int id = 0;
                        try {
                            id = Integer.parseInt(entrada.nextLine());
                            pessoaJuridicaDAO.excluir(id);
                        } catch (NumberFormatException e) {
                            System.out.println("Tente novamente mais tarde");
                            menu();
                        }
                    }
                    break;

                case 4:
                    System.out.println("Digite F Pessoa Física e J Pessoa Juridica");

                    tipoDePessoa = entrada.nextLine();

                    if (tipoDePessoa.equals("F")) {
                        System.out.println("Id:");
                        int id;
                        try {
                            id = Integer.parseInt(entrada.nextLine());
                            pessoaFisicaDAO.getPessoa(id).exibir();
                        } catch (NumberFormatException e) {
                            System.out.println("Formato invalido");
                            menu();
                        }
                    } else if (tipoDePessoa.equals("J")) {
                        System.out.println("Id:");
                        int id;
                        try {
                            id = Integer.parseInt(entrada.nextLine());
                            pessoaJuridicaDAO.getPessoa(id).exibir();
                        } catch (NumberFormatException e) {
                            System.out.println("Tente novamente mais tarde");
                            menu();
                        }
                    }
                    break;

                case 5:
                    System.out.println("Digite F Pessoa Física e J Pessoa Juridica");
                    tipoDePessoa = entrada.nextLine();
                    if(tipoDePessoa.equals("F")){
                        pessoaFisicaDAO.getPessoas().forEach(p -> p.exibir());
                    } else if (tipoDePessoa.equals("J")) {
                        pessoaJuridicaDAO.getPessoas().forEach(p -> p.exibir());
                    }
                    break;

            }
        } while (op != 0);
    }
}
