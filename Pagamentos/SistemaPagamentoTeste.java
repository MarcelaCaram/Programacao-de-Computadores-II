/*
* CABE�ALHO *

Alunos: Lucas Santos Rodrigues (20213001521)
		Marcela Caram (20213006528)
		Sulamita Costa (20213001568)
		Vin�cius Ferreira (20213005208)
		
		Atividade 4 - Pagamento
		
*/
package principal_LucasSantos_MarcelaCaram_SulamitaCosta_ViniciusPinheiro;
import java.util.Scanner;
public class SistemaPagamentoTeste {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		int menu = 0;
		ControlePagamento empresa = new ControlePagamento();
		
		/*
		 *	Por meio do "while" � gerado um MENU onde o usu�rio pode executar 3 comandos:
		 *	Registrar conta, registrar empregado e listar os pagamentos da empresa.
		*/
		while(menu != 4) {
			System.out.println("O que deseja fazer?"
								+ "\n\t(1) Registrar conta."
								+ "\n\t(2) Registrar empregado."
								+ "\n\t(3) Listar pagamentos."
								+ "\n\t(4) Sair.");
			
			menu = input.nextInt();
			
			/*
			 *	Registrar conta
			 *
			 *	� perguntado o tipo da conta (t�tulo ou concession�ria), sua data de vencimento e seu valor.
			 *	Ap�s obter os dados da conta ela � adicionada no ArrayList do objeto "empresa" pelo m�todo "empresa.gravarConta".
			*/
			if(menu == 1) {
				System.out.println("Deseja adicionar:"
									+ "\n\t(1) Um t�tulo."
									+ "\n\t(2) Uma concession�ria.");
				
				int tipo = input.nextInt();
				
				System.out.println("Qual o dia e m�s de vencimento?");
				int dia = input.nextInt();
				int mes = input.nextInt();
				
				System.out.println("Qual o valor da conta?");
				double valor = input.nextDouble();
				
				empresa.gravarConta(dia, mes, valor, tipo);
			}
			
			/*
			 *	Registrar empregado
			 *
			 *	Analogamente ao procedimento de "Registrar conta", � obtido os dados do funcion�rio:
			 *	nome, sobrenome e complemento (horas trabalhadas, horas extras, valor de vendas).
			 *
			 *	E ap�s isso � adicionado o empregado no ArrayList do objeto "empresa" pelo m�todo "empresa.gravarEmpregado".
			*/
			else {
				if(menu == 2) {
					System.out.println("Deseja adicionar:"
							+ "\n\t(1) Um empregado terceirizado."
							+ "\n\t(2) Um empregado comissionado."
							+ "\n\t(3) Um empregado assalariado."
							+ "\n\t(4) Um empregado assalariado/comissionado.");
					
					int tipo = input.nextInt();
					
					System.out.println("Qual o nome, sobrenome e n�mero de identifica��o do empregado?");
					input.nextLine();
					String nome = input.nextLine();
					String sobrenome = input.nextLine();
					int numIdent = input.nextInt();
					
					System.out.println("Qual o complemento?"
									+ "\n** Complemento: horas trabalhadas, horas extras"
									+ ", valor de vendas.");
					double complemento = input.nextDouble();
					
					empresa.gravarEmpregado(nome, sobrenome, numIdent, complemento, tipo);
				}
				
				/*
				 *	Listar pagamentos
				 *	
				 *	� perguntado o dia e mes do pagaemnto das despesas da empresa.
				 *
				 *	Ap�s isso, � criado um array "despesas" que recebe por meio do m�todo "listarPagamentos"
				 *	o valor total das contas e o valor total dos funcion�rios.
				 *
				 *	Dessa forma, o us�rio pode optar por exibir o valor das contas, dos empregados ou o valor geral das despesas.
				*/
				else {
					if(menu == 3) {
						System.out.println("Qual a data de pagamento das despesas?");
						int diaPagto = input.nextInt();
						int mesPagto = input.nextInt();
						
						double[] despesas = empresa.listarPagamentos(diaPagto, mesPagto);
						
						System.out.println("Deseja consultar:"
									+ "\n\t(1) O valor das contas."
									+ "\n\t(2) O valor dos sal�rios."
									+ "\n\t(3) O valor geral.");
						
						int tipoValor = input.nextInt();
						
						if(tipoValor == 1) {
							System.out.println("Valor das contas: R$" + despesas[0]);
						}
						
						else {
							if(tipoValor == 2) {
								System.out.println("Valor dos sal�rios: R$ " + despesas[1]);
							}
							
							else {
								if(tipoValor == 3) {
									System.out.println("Valor geral: R$ " + (despesas[0] + despesas[1]));
								}
							}
						}
					}
				}
			}
		}
		System.out.println("J� vai? :("
				+ "\nAt� mais!");
		
		
		input.close();
	}
}