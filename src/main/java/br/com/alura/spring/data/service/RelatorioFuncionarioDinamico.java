package br.com.alura.spring.data.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.specification.SpecificationFuncionario;

@Service
public class RelatorioFuncionarioDinamico {
	
	private final DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private final FuncionarioRepository funcionarioRepository;
	
	public RelatorioFuncionarioDinamico(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}
	
	public void inicial(Scanner scanner) {
		System.out.println("Digite o Nome");
		String nomeString = scanner.next();
		
		if(nomeString.equalsIgnoreCase("Null")) {
			nomeString = null;
		}
		
		System.out.println("Digite o CPF");
		String cpf = scanner.next();
		
		if(cpf.equalsIgnoreCase("Null")) {
			cpf = null;
		}
		
		System.out.println("Digite o Salario");
		Double salario = scanner.nextDouble();
		
		if(salario == 0) {
			salario = null;
		}
		
		System.out.println("Digite a Data de Contratação");
		String data = scanner.next();
		
		LocalDate dataContratacao;
		if(data.equalsIgnoreCase("Null")) {
			dataContratacao = null;
		}else {
			dataContratacao = LocalDate.parse(data, format);
		}
		
		List<Funcionario> funcionarios = funcionarioRepository.
				findAll(Specification.where(SpecificationFuncionario.nome(nomeString))
						.or(SpecificationFuncionario.cpf(cpf))
						.or(SpecificationFuncionario.salario(salario))
						.or(SpecificationFuncionario.dataContratacao(dataContratacao))
						
						);
		
		funcionarios.forEach(System.out::println);
	}

}
