package source;
/**
 * Essa clsse � utilizada para criar itens nos quartos (<b>Room</b>) e adiministr�-los.
 * 
 * @author Lucas Santos, Marcela Caram, Sulamita Costa, Vinicius Pinheiro
 * @since jan 2022.
 */

public class Item {
	/**
	 * Nome do item.
	 */
	private String name;
	/**
	 * Boolean que verifica se foi coletado ou n�o.
	 */
	private boolean collected;
	
	/**
	 * M�todo construtor sem parametros.
	 */
	public Item() {
		this.name = ("");
		this.collected = false;
	}
	
	/**
	 * M�todo construtor.
	 * @param name
	 * @param collected
	 */
	public Item(String name, boolean collected) {
		this.name = name;
		this.collected = collected;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isCollected() {
		return collected;
	}

	public void setCollected(boolean collected) {
		this.collected = collected;
	}
}
