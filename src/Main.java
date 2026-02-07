import dao.*;
import model.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);

    static AlunoDao alunoDao = new AlunoDao();
    static PlanoDao planoDao = new PlanoDao();
    static MatriculaDao matriculaDao = new MatriculaDao();
    static PagamentoDao pagamentoDao = new PagamentoDao();

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n===== SISTEMA ACADEMIA =====");
            System.out.println("1 - Alunos");
            System.out.println("2 - Planos");
            System.out.println("3 - Matrículas");
            System.out.println("4 - Pagamentos");
            System.out.println("0 - Sair");

            int opcao = sc.nextInt();

            switch (opcao) {
                case 1 -> menuAluno();
                case 2 -> menuPlano();
                case 3 -> menuMatricula();
                case 4 -> menuPagamento();
                case 0 -> {
                    System.out.println("Sistema finalizado.");
                    System.exit(0);
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    // ================= ALUNO =================

    static void menuAluno() {
        System.out.println("\n--- ALUNOS ---");
        System.out.println("1 - Cadastrar");
        System.out.println("2 - Listar");
        System.out.println("3 - Buscar por ID");
        System.out.println("4 - Deletar");

        int op = sc.nextInt();

        switch (op) {
            case 1 -> cadastrarAluno();
            case 2 -> listarAlunos();
            case 3 -> buscarAluno();
            case 4 -> deletarAluno();
        }
    }

    static void cadastrarAluno() {
        sc.nextLine();

        Aluno a = new Aluno();

        System.out.print("Nome: ");
        a.setNome(sc.nextLine());

        System.out.print("Email: ");
        a.setEmail(sc.nextLine());

        System.out.print("Telefone: ");
        a.setTelefone(sc.nextLine());

        System.out.print("Data nascimento (yyyy-mm-dd): ");
        a.setDataNascimento(LocalDate.parse(sc.nextLine()));

        alunoDao.salvar(a);
        System.out.println("Aluno cadastrado com sucesso!");
    }

    static void listarAlunos() {
        List<Aluno> alunos = alunoDao.listarTodos();
        alunos.forEach(a -> {
            System.out.println(a.getId() + " - " + a.getNome() + " - " + a.getEmail());
        });
    }

    static void buscarAluno() {
        System.out.print("ID: ");
        int id = sc.nextInt();
        Aluno a = alunoDao.buscarPorId(id);

        if (a != null) {
            System.out.println(a.getNome() + " - " + a.getEmail());
        } else {
            System.out.println("Aluno não encontrado!");
        }
    }

    static void deletarAluno() {
        System.out.print("ID: ");
        alunoDao.deletar(sc.nextInt());
        System.out.println("Aluno deletado!");
    }

    // ================= PLANO =================

    static void menuPlano() {
        System.out.println("\n--- PLANOS ---");
        System.out.println("1 - Cadastrar");
        System.out.println("2 - Listar");
        System.out.println("3 - Buscar por ID");
        System.out.println("4 - Deletar");

        int op = sc.nextInt();

        switch (op) {
            case 1 -> cadastrarPlano();
            case 2 -> listarPlanos();
            case 3 -> buscarPlano();
            case 4 -> deletarPlano();
        }
    }

    static void cadastrarPlano() {
        sc.nextLine();

        Plano p = new Plano();

        System.out.print("Nome: ");
        p.setNome(sc.nextLine());

        System.out.print("Valor: ");
        p.setValor(sc.nextDouble());

        System.out.print("Duração (meses): ");
        p.setDuracaoMeses(sc.nextInt());

        sc.nextLine();
        System.out.print("Descrição: ");
        p.setDescricao(sc.nextLine());

        planoDao.salvar(p);
        System.out.println("Plano cadastrado!");
    }

    static void listarPlanos() {
        planoDao.listarTodos().forEach(p -> {
            System.out.println(p.getId() + " - " + p.getNome() + " - R$" + p.getValor());
        });
    }

    static void buscarPlano() {
        System.out.print("ID: ");
        Plano p = planoDao.buscarPorId(sc.nextInt());

        if (p != null) {
            System.out.println(p.getNome() + " - R$" + p.getValor());
        } else {
            System.out.println("Plano não encontrado!");
        }
    }

    static void deletarPlano() {
        System.out.print("ID: ");
        planoDao.deletar(sc.nextInt());
        System.out.println("Plano removido!");
    }

    // ================= MATRÍCULA =================

    static void menuMatricula() {
        System.out.println("\n--- MATRÍCULAS ---");
        System.out.println("1 - Criar matrícula");
        System.out.println("2 - Listar por aluno");
        System.out.println("3 - Cancelar matrícula");

        int op = sc.nextInt();

        switch (op) {
            case 1 -> criarMatricula();
            case 2 -> listarMatriculasAluno();
            case 3 -> cancelarMatricula();
        }
    }

    static void criarMatricula() {
        System.out.print("ID do aluno: ");
        int alunoId = sc.nextInt();

        System.out.print("ID do plano: ");
        int planoId = sc.nextInt();

        Plano plano = planoDao.buscarPorId(planoId);

        LocalDate inicio = LocalDate.now();
        LocalDate fim = inicio.plusMonths(plano.getDuracaoMeses());

        Matricula m = new Matricula();
        m.setAlunoId(alunoId);
        m.setPlanoId(planoId);
        m.setDataInicio(inicio);
        m.setDataFim(fim);
        m.setStatus("ATIVA");
        m.setValorContratado(plano.getValor());

        matriculaDao.cadastrar(m);

        System.out.println("Matrícula criada!");
    }

    static void listarMatriculasAluno() {
        System.out.print("ID do aluno: ");
        int alunoId = sc.nextInt();

        matriculaDao.listarPorAluno(alunoId).forEach(m -> {
            System.out.println(m.getId() + " - " + m.getStatus() + " - até " + m.getDataFim());
        });
    }

    static void cancelarMatricula() {
        System.out.print("ID da matrícula: ");
        matriculaDao.cancelar(sc.nextInt());
        System.out.println("Matrícula cancelada!");
    }

    // ================= PAGAMENTO =================

    static void menuPagamento() {
        System.out.println("\n--- PAGAMENTOS ---");
        System.out.println("1 - Registrar pagamento");
        System.out.println("2 - Listar pagamentos");

        int op = sc.nextInt();

        switch (op) {
            case 1 -> registrarPagamento();
            case 2 -> listarPagamentos();
        }
    }

    static void registrarPagamento() {
        System.out.print("ID da matrícula: ");
        int matriculaId = sc.nextInt();

        System.out.print("Valor: ");
        double valor = sc.nextDouble();

        sc.nextLine();
        System.out.print("Forma de pagamento: ");
        String forma = sc.nextLine();

        Pagamento p = new Pagamento();
        p.setMatriculaId(matriculaId);
        p.setValor(valor);
        p.setFormaPagamento(forma);
        p.setStatus("PAGO");
        p.setDataVencimento(LocalDate.now());
        p.setDataPagamento(LocalDate.now());

        pagamentoDao.cadastrar(p);

        System.out.println("Pagamento registrado!");
    }

    static void listarPagamentos() {
        pagamentoDao.Listar().forEach(p -> {
            System.out.println(p.getId() + " - R$" + p.getValor() + " - " + p.getStatus());
        });
    }
}
