package source;

import java.util.Arrays;

/**
 * @author Lucas Santos, Marcela Caram, Sulamita Costa, Vinicius Pinheiro
 * @since jan 2022.
 */
public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Room outside, theatre, pub, lab, office, porao, sotao;
    /**
     * Boolean utilizado para gerar o "GAME OVER" ou quando voc� ganha o jogo.
     */
    private boolean finalizado = false;
        
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }
    
    /**
     * Esse m�todo serve para criar os quartos(<b>Room</b>) e foi modificado do projeto original.
     * Modificamos o m�todo criador da classe <b>Room</b>.
     * Dessa forma, temos Room(descri��o, detalhamento(procurar), personagens, saidas...).
     * Al�m disso, temos o m�todo "addItem" que adiciona um item ao ArrayList de Item na classe <b>Room</b>.
     */
    public void createRooms()
    {
      
        // create the rooms
        outside = new Room("outside the main entrance of the university", "There are some trees, animals and smells like weed.", null, "", "theatre", "lab", "pub", "", "");
        theatre = new Room("in a lecture theatre", "There are some chairs and ballet dancers. They dressed as warriors.", null, "", "", "", "outside", "", "");
        	theatre.addItem(new Item("Sword", false)); 
        pub = new Room("in the campus pub", "There are a lot of drunk classmates. They are eating strange Mushrooms.", null, "", "outside", "", "", "", "");
        	pub.addItem(new Item("Mushroom", false)); 
        lab = new Room("in a computing lab", "There many broken chemical stuffs and the room are completely empty.", null, "outside", "office", "", "", "", "");
        office = new Room("in the computing admin office", "There are bodies all over the floor and now a dead Troll.", 
        					new Character("Troll", "Troll: URRRRR!!!", true),"", "", "", "lab", "sotao", "porao");
        
        porao = new Room("in the basement", "You can't see nothing, just a candle near to your friend.", 
        					new Character("Classmate", "Classmate: To defeat the Dragon you'll need a sword and a mushroom!", false),"", "", "", "", "office", "");
        sotao = new Room("in the attic", "It's everything on fire, it seems like hell.",  new Character("Dragon", "Dragon: You're gonna die!", true),"", "", "", "", "", "office");

        currentRoom = outside;  
    }

    public void play() 
    {            
        printWelcome();

        
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
            finished = finalizado;
        }
        System.out.println("Thank you for playing.  Good bye.");
    }
    
    /**
     * Modificamos o m�todo para que ele agora imprima as sa�das de forma otimizada.
     */
    public void printWelcome() 
    {
        System.out.println();
        System.out.println("Welcome to Adventure!");
        System.out.println("Adventure is a new, incredibly boring adventure game.\nYour mission is defeat the Dragon.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println("You are " + currentRoom.getDescription());
        System.out.print("Exits: ");
      
        // *** OTIMIZA��O ***
        for(String key : currentRoom.getSaidas().keySet()) {
        	if(!currentRoom.getSaidas().get(key).equals(""))
        		System.out.print(key + " ");
        }

        System.out.println();
    }
    
    /**
     * Alteramos o m�todp para que agora o comando seja verificado classe <b>Command</b>, por meio do m�todo "verificarComando()"
     * @param command
     * @return
     */
    public boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;
        CommandWords comando = new CommandWords(command.getCommandWord());

        if (comando.verificarComando() == 0)
            printHelp();
        else if (comando.verificarComando() == 1)
            goRoom(command);
        else if (comando.verificarComando() == 2)
            wantToQuit = quit(command);
        else if(comando.verificarComando() == 3)
        	procurar();
        else if(comando.verificarComando() == 4)
        	comer();
        else if(comando.verificarComando() == 5) {
        	System.out.println("I don't know what you mean...");
            return false;
        }

        return wantToQuit;
    }

    /**
     * Alteramos o m�todo para que a responsabilidade de verificar as palavras de comando seja apenas da classe PalavrasComando.
     */
    public void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        
        //verifica as palavras de comando.
        System.out.println(Arrays.toString(CommandWords.getValidCommands()));
    }
    
    /**
     * Nesse m�todo, primeiramente, s�o impressos a descri��o e detalhes do quarto e, ap�s,  s�o impressas suas sa�das.
     */
    public void procurar() {
    	 System.out.println("You are " + currentRoom.description+ "\n" + currentRoom.getDetails());
    	 System.out.print("Exits: ");
         for(String key2 : currentRoom.getSaidas().keySet()) {
         	if(!currentRoom.getSaidas().get(key2).equals(""))
         		System.out.print(key2 + " ");
         }
         System.out.println();
    }
    
    /**
     * M�todo para comer.
     * Imprime "Voc� comeu e agora n�o est� mais com fome."
     */
    public void comer() {
    	System.out.println("Voc� comeu e agora n�o est� mais com fome.");
    }
    
    /**
     * Alteramos o m�todo original. Agora verificamos para qual sala vamos da forma otimizada.
     * Ap�s isso, temos a parte onde s�o pegos os <b>Item</b>'s que a nova sala possui.
     * Depois, ocorre a intera��o com <b>Character</b>'s pertencentes a sala. S�o exibidas suas falas e descri��es.
     * Se o <b>Character</b> for um inimigo, � verificado se o jogador possui os itens para derrotar eles, pelos m�todos "defeatMonster()" ou "defeatBoss()".
     * @param command
     */
    public void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();
        
        // *** OTIMIZA��O ***
        
        String nextRoom = "";
        for(String key : currentRoom.getSaidas().keySet()) {
	        if(direction.equals(key)) {
	            nextRoom = currentRoom.getSaidas().get(key);
	
	        	if(nextRoom.equals("outside"))
	        		currentRoom = outside;
	        	if(nextRoom.equals("lab"))
	        		currentRoom = lab;
	        	if(nextRoom.equals("theatre"))
	        		currentRoom = theatre;
	        	if(nextRoom.equals("pub"))
	        		currentRoom = pub;
	        	if(nextRoom.equals("office"))
	        		currentRoom = office;
	        	if(nextRoom.equals("sotao"))
	        		currentRoom = sotao;
	        	if(nextRoom.equals("porao"))
	        		currentRoom = porao;
	            
	            System.out.println("You are " + currentRoom.getDescription());
	            
	            // *** PEGAR ITEM ***
	            for(Item item : currentRoom.getRoomItem()) {
	            	if(item != null) {
		            	if(!item.isCollected()) {
		            		item.setCollected(true);
		            		System.out.println("You have found " + item.getName() 
		            							+ "\n" + item.getName() + " have been collected!");
		            	}
		            }
	            }
	            
	            // *** INTERA��O COM PERSONAGEM ***
	            if(currentRoom.getCharacter() != null && currentRoom.getCharacter().isAlive()) {
	            	System.out.println("You have found a " + currentRoom.getCharacter().getName()
	            						+ "\n -" + currentRoom.getCharacter().getDescricao());
	            	// SE FOR UM INIMIGO
	            	if(currentRoom.getCharacter().isEnemy()) {
	            		// SE FOR UM DRAG�O
	            		if(currentRoom.getCharacter().getName().equals("Dragon")) {
	            			// VERIFICA SE TEMOS OS ITENS NECESS�RIOS PARA DERROT�-LO
	            			if(currentRoom.getCharacter().defeatBoss(theatre.getRoomItem(), pub.getRoomItem())) {
	            				System.out.println("The Dragon is high by your Mushroom,\nso you hit him with your sword."
	            									+ "\nYou defeated the Dragon!!!\nYOU WIN!!!");
	            				currentRoom.getCharacter().setAlive(false);
	            				this.finalizado = true;
	            			}
	            			else {
	            				System.out.println("The Dragon have vaporized you!!!"
	            								+ "\n*** Tip: you need a Sword and a Mushroom ***"
	            								+ "\nGAME OVER!");
	            				this.finalizado = true;
	            			}
	            		}
	            		
	            		// SE FOR UM TROLL
	            		if(currentRoom.getCharacter().getName().equals("Troll")) {
	            			// VERIFICA SE TEMOS O ITEM NECESS�RIO PARA DERROT�-LO
	            			if(currentRoom.getCharacter().defeatMonster(theatre.getRoomItem())) {
	            				System.out.println("The Troll is too slow, so you hit him with your sword!"
	            									+ "\nYou defeated the Troll!!!");
	            				currentRoom.getCharacter().setAlive(false);
	            			}
	            			else {
	            				System.out.println("The Troll have smashed you!!!"
	            								+ "\n*** Tip: you need a Sword ***"
	            								+ "\nGAME OVER!");
	            				this.finalizado = true;
	            			}
	            		}
	            	}
	            }
	        }
         }
        if (nextRoom == "")
            System.out.println("There is no door!");
	        
            System.out.print("Exits: ");
            for(String key2 : currentRoom.getSaidas().keySet()) {
            	if(!currentRoom.getSaidas().get(key2).equals(""))
            		System.out.print(key2 + " ");
            }
            System.out.println();
   }


    public boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else
            return true;  // signal that we want to quit
    }
}
