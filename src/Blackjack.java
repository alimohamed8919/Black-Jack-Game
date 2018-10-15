import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



public class Blackjack {
	
	public int playerScore = 0;
	private int playerScore2 = 0;
	private int dealerScore = 0;
	private int dealerScore2 = 0;
	private int randomPick = 0;
	
	public String suitPick(){
		String[] suit = new String[4];
		suit[0] = "C";
		suit[1] = "D";
		suit[2] = "S";
		suit[3] = "H";
		
		return suit[(int)(Math.random() * 4)];
	}
	
	public int numPick() {
		this.randomPick = 0;
		this.randomPick = (int)(1 + Math.random() * 13);
		
		return this.randomPick;
	}
	
	public void playerCardAdder() {
		
		if(this.randomPick == 11 || this.randomPick == 12 || this.randomPick == 13) {
			this.playerScore += 10;
		}else {
			this.playerScore += this.randomPick;
		}
		
		if(this.randomPick == 11 || this.randomPick == 12 || this.randomPick == 13) {
			this.playerScore2 += 10;
		}else if (this.randomPick == 1) {
			this.playerScore2 += 11;
		}else {
			this.playerScore2 += this.randomPick;
		}
	}
	
	public void dealerCardAdder() {
		
		if(this.randomPick == 11 || this.randomPick == 12 || this.randomPick == 13) {
			this.dealerScore += 10;
		}else {
			this.dealerScore += this.randomPick;
		}
		if(this.randomPick == 11 || this.randomPick == 12 || this.randomPick == 13) {
			this.dealerScore2 += 10;
		}else if (this.randomPick == 1) {
			this.dealerScore2 += 11;
		}else {
			this.dealerScore2 += this.randomPick;
		}
	}
	
	public int playerTotal() {
		if (this.playerScore2 <= 21) {
			return this.playerScore2;
		}
		return this.playerScore;
	}
	
	public int dealerTotal() {
		if (this.dealerScore2 <= 21) {
			return this.dealerScore2;
		}
		return this.dealerScore;
	}
	
	
	public void resetPlayerTotal() {
		this.playerScore = 0;
		this.playerScore2 = 0;
	}
	
	public void resetDealerTotal() {
		this.dealerScore = 0;
		this.dealerScore2 = 0;
	}
	
	
	public static void main(String[] args) {
		
		Blackjack BlkJ = new Blackjack();
		
		// Initialized color and font variables.
		Font Header = new Font("Serif", Font.BOLD, 20);	// Variable to use the Roboto font.
		Color Green = Color.decode("#0E8629");					// Variable to link color to Hex.
		//Color darkGrey = Color.decode("#9E9E9E");				// Variable to link color to Hex.
		
		JFrame gui = new JFrame();								// Container of the game. "Main box"
		
		// Properties of the GUI. 
		gui.setSize(600,500);									// Makes the size of the GUI 800x450.
		gui.setResizable(false);								// Makes the GUI not to be resized.
		gui.setTitle("Blackjack");								// Makes the title of the program "Blackjack".
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		// Exit the program on GUI close.
		gui.setLocationRelativeTo(null);						// Place the GUI at the center of the screen.
		gui.setBackground(Green);
		
		// Container
		Container container = gui.getContentPane();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		//container.add(new JLabel(new ImageIcon("img/Background_Texture.png")));
		
		// Dealer JPanel
		JLabel dealerHeader = new JLabel("Dealer");
		dealerHeader.setAlignmentX(Component.CENTER_ALIGNMENT);
		dealerHeader.setFont(Header);
		
		JPanel dealer = new JPanel();
		//dealer.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		dealer.setBackground(Green);
		
		// Player Panel
		JLabel playerHeader = new JLabel("Player");
		playerHeader.setAlignmentX(Component.CENTER_ALIGNMENT);
		playerHeader.setFont(Header);
		
		JPanel player = new JPanel();
		//player.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		player.setBackground(Green);

		// Button Panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Green);
		
		
		// Scoreboard
		JLabel scoreboard = new JLabel();
		scoreboard.setAlignmentX(Component.CENTER_ALIGNMENT);
		scoreboard.setBackground(Green);
		scoreboard.setFont(Header);
		
		JButton deal  = new JButton("Deal");
		JButton hit = new JButton("Hit");
		JButton stand = new JButton("Stand");
		
		
		hit.setEnabled(false);
		stand.setEnabled(false);
		dealer.add(new JLabel(new ImageIcon("img/FullDeck.png")));
		
		// Action listener for when Deal button is clicked.
		
		deal.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
                System.out.println("deal button is pressed");
                dealer.removeAll();
                 
                dealer.add(new JLabel(new ImageIcon("img/" + BlkJ.suitPick() + BlkJ.numPick() + ".png")));
                BlkJ.dealerCardAdder();
                dealer.add(new JLabel(new ImageIcon("img/FaceDown.png")));
                dealer.updateUI();
                 
                player.add(new JLabel(new ImageIcon("img/" + BlkJ.suitPick() + BlkJ.numPick() + ".png")));
                BlkJ.playerCardAdder();
                player.add(new JLabel(new ImageIcon("img/" + BlkJ.suitPick() + BlkJ.numPick() + ".png")));
                BlkJ.playerCardAdder();
                player.updateUI();
                 
                hit.setEnabled(true);
                stand.setEnabled(true);
                deal.setEnabled(false);
                
                System.out.println("Dealer Total: " + BlkJ.dealerTotal());
                System.out.println("Player Total: " + BlkJ.playerTotal());
                scoreboard.setText("Player: " + BlkJ.playerTotal() + " | " + "Dealer: " + BlkJ.dealerTotal());
                scoreboard.updateUI();
			}
		});
		

		//Test action listener for hit
		hit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("hit button is pressed");
                
                player.add(new JLabel(new ImageIcon("img/" + BlkJ.suitPick() + BlkJ.numPick() + ".png")));
                BlkJ.playerCardAdder();
                player.updateUI();
                   
                System.out.println("Dealer Total: " + BlkJ.dealerTotal());
                System.out.println("Player Total: " + BlkJ.playerTotal());
                scoreboard.setText("Player: " + BlkJ.playerTotal() + " | " + "Dealer: " + BlkJ.dealerTotal());
                scoreboard.updateUI();
                 
                if (BlkJ.playerTotal() > 21) {
                    System.out.println("You Lose!!");
                     
                    JOptionPane.showMessageDialog(gui, "You have: " + BlkJ.playerTotal() + "  You Bust!!", "Lose", JOptionPane.ERROR_MESSAGE);
                     
                    int playAgain = JOptionPane.showConfirmDialog(gui, "Play Again?", "Replay", JOptionPane.YES_NO_OPTION);
                     
                    if(playAgain == JOptionPane.YES_OPTION){
                         
                        player.removeAll();
                        dealer.removeAll();
                        BlkJ.resetDealerTotal();
                        BlkJ.resetPlayerTotal();
                        dealer.updateUI();
                        player.updateUI();
                        
                        scoreboard.setText("Player: " + BlkJ.playerTotal() + " | " + "Dealer: " + BlkJ.dealerTotal());
                        scoreboard.updateUI();
                        
                        dealer.add(new JLabel(new ImageIcon("img/FullDeck.png")));
                         
                        hit.setEnabled(false);
                        stand.setEnabled(false);
                        deal.setEnabled(true);
         
                    }else {
                         
                        System.exit(0);
                    }
                }
            }
		});

		stand.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				hit.setEnabled(false);
                stand.setEnabled(false);
                 
                System.out.println("stand button is pressed");
                dealer.remove(1);
                 
                while(BlkJ.dealerTotal() <= BlkJ.playerTotal()) {
 
                    dealer.add(new JLabel(new ImageIcon("img/" + BlkJ.suitPick() + BlkJ.numPick() + ".png")));
                    BlkJ.dealerCardAdder();
                    dealer.updateUI();
                     
                }
                
                scoreboard.setText("Player: " + BlkJ.playerTotal() + " | " + "Dealer: " + BlkJ.dealerTotal());
                scoreboard.updateUI();
                 
                if (BlkJ.dealerTotal() > BlkJ.playerTotal() && BlkJ.dealerTotal() <=21) {
                     
                    System.out.println("You Lose!!!");
                     
                    JOptionPane.showMessageDialog(gui, "Dealer has: " + BlkJ.dealerTotal() + "  You have: " + BlkJ.playerTotal() + "  You Lose!!", "Lose", JOptionPane.ERROR_MESSAGE);
                     
//                }else if(BlkJ.dealerTotal() == BlkJ.playerTotal()) {
//                     
//                    System.out.println("Draw!!!");
//                     
//                    JOptionPane.showMessageDialog(gui, "Draw!!", "Tied", JOptionPane.INFORMATION_MESSAGE);
//                     
                     
                }else {
                    System.out.println("You Win!!!");
                     
                    JOptionPane.showMessageDialog(gui, "Dealer Bust, You Win!!", "Win", JOptionPane.INFORMATION_MESSAGE);
                     
                }
                 
                int playAgain = JOptionPane.showConfirmDialog(gui, "Play Again?", "Replay", JOptionPane.YES_NO_OPTION);
                 
                if(playAgain == JOptionPane.YES_OPTION){
                     
                    player.removeAll();
                    dealer.removeAll();
                    scoreboard.removeAll();
                    BlkJ.resetDealerTotal();
                    BlkJ.resetPlayerTotal();
                    dealer.updateUI();
                    player.updateUI();
                    
                    scoreboard.setText("Player: " + BlkJ.playerTotal() + " | " + "Dealer: " + BlkJ.dealerTotal());
                    scoreboard.updateUI();
                     
                    dealer.add(new JLabel(new ImageIcon("img/FullDeck.png")));
                     
                    hit.setEnabled(false);
                    stand.setEnabled(false);
                    deal.setEnabled(true);
     
                }else {
                     
                    System.exit(0);
                }
            }
		});
		
		buttonPanel.add(deal);
		buttonPanel.add(hit);
		buttonPanel.add(stand);
		
		
		// Add items to the container.
		container.setBackground(Green);
		container.add(dealerHeader);
		container.add(dealer);
		container.add(playerHeader);
		container.add(player);
		container.add(buttonPanel);
		container.add(scoreboard);
		
		gui.setVisible(true);			// Makes the GUI to be shown.
	}

}
