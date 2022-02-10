public class Pessoa {
	private String nome;//Declara��o de cada string
	private Pessoa pai;
	private Pessoa mae;
	
	public Pessoa(String nome, String pai, String mae) {//Declara��o de um m�todo para preencher os nomes, a m�e e o pai.
		this.nome = nome;
		this.pai = new Pessoa(pai);
		this.mae = new Pessoa(mae);
	}
	
	public Pessoa(String nome) {//Declara��o de um m�todo para quando surgir uma new pessoa sem pai e sem m�e, apenas com um o parametro nome.
		this.nome = nome;
		this.pai = null;
		this.mae = null;
	}
	
	public String getNome() {//M�todo para pegar  e mostrar o nome.
		return nome;
	}
	public void setNome(String nome) {//M�todo para colocar o nome da pessoa.
		this.nome = nome;
	}
	
	public Pessoa getPai() {//M�todo para pegar  e mostrar o nome do pai.
		return pai;
	}
	public void setPai(Pessoa pai) {//M�todo para colocar o nome do pai pessoa.
		this.pai = pai;
	}

	public Pessoa getMae() {//M�todo para pegar  e mostrar o nome da m�e.
		return mae;
	}
	public void setMae(Pessoa mae) {//M�todo para colocar o nome da m�e pessoa.
		this.mae = mae;
	}
	
	public void compararPessoas(Pessoa pessoa){// Declara��o de um m�todo para comparar pessoas, ver se elas s�o iguais
		if(this.mae != null && pessoa.mae != null) { //Para n�o acontecer erro durante a execu��o � feito esse if.
			if(this.equals(pessoa) && this.mae.equals(pessoa.mae)) {//comparar se as pessaos s�o as mesmas.
				System.out.println(this.nome+" e "+pessoa.nome+" s�o a mesma pessoa.");
			}
		}
	}
	
	public void verificarIrmas(Pessoa pessoa){//Declara��o de um m�todo para verificar irmandade.
		if(this.pai != null && this.mae != null && pessoa.pai != null && pessoa.mae != null) {//Para n�o acontecer erro durante a execu��o � feito esse if.
			if(this.pai.nome.equals(pessoa.pai.nome) && this.mae.nome.equals(pessoa.mae.nome) && this.nome!=pessoa.nome) {//Caso a pessoa tenha mesma m�e e pai e o nome seja diferente.
				System.out.println(this.nome+" e "+pessoa.nome+" s�o irm�os(�s).");
			}
		}
	}
	
	public void verificarAntecessor(Pessoa pessoa){//M�todo para verificar antecessor.
		if(this.pai != null && this.mae != null) {//Para n�o acontecer erro durante a execu��o � feito esse if.
			if(this.pai.equals(pessoa) || this.mae.equals(pessoa)) {//para ver se a pessoa � pai de algu�m, compara-se os nomes.
				System.out.println(this.nome+" � filho(a) de "+pessoa.nome);
				
			}
			else {
				if(this.pai.pai != null && this.pai.mae != null) {//Para n�o acontecer erro durante a execu��o � feito esse if.
					if(this.pai.pai.nome.equals(pessoa.nome) || this.pai.mae.nome.equals(pessoa.nome)) {//verificar se � o av� e av� de parte de pai
						System.out.println(this.nome+" � neto(a) de "+pessoa.nome);
					}
				}
				else {
					if(this.mae.pai != null && this.mae.mae != null) {//Para n�o acontecer erro durante a execu��o � feito esse if.
						if(this.mae.pai.nome.equals(pessoa.nome) || this.mae.mae.nome.equals(pessoa.nome)) {//verificar se � o av� e av� de parte de m�e
							System.out.println(this.nome+" � neto(a) de "+pessoa.nome);
						}
					}
				}
			}
		}
	}
}