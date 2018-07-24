import java.util.Scanner;

public class OitoRainhas {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		// array que contem quantas casas do tabuleiro ficaram bloqueadas quando a rainha for posicionada em determinada localização
		int[][] bloqueio = new int[8][8];
		int[][] tabuleiro = new int[8][8];
		
		System.out.println("Desafio as 8 rainhas.");
		System.out.println("Esse desafio consiste em posicionar 8 rainhas no mesmo tabuleiro, sem que nenhuma delas elimine nenhuma outra rainha.");
		System.out.println("A abordagem proposta nesse programa usa um tabuleiro-array auxiliar chamado bloqueio, que contem as informações de quantas casas a rainha irá bloquear caso seja"
				+ "posicionada em determinada posição do tabuleiro.\n"
				+ "De acordo com essas informações, o algoritmo determina qual a casa que a rainha será posicionada, sempre escolhendo a casa que irá bloquear menos casas no tabuleiro.\n"
				+ "Após cada rainha ser posicionada, a tabela bloqueio é atualizada, de acordo com o posicionamento da rainha.\n"
				+ "Essa solução não funcionou como o esperado, pois antes de posicoinar a ultima rainha, o tabuleiro já estava todo bloqueado.\n");
		
		preencheBloqueio(bloqueio);
		
		for(int rainha = 0; rainha < 8; rainha++){
			System.out.printf("Rainha %d\n", rainha +1);
			
			int linhaRainha = 0, colunaRainha = 0;
			int menosBloqueios = 100;
			
			//Define a casa em que a Rainha será colocada, levando em conta qual a casa que bloqueará um menor numero de casas.
			for(int i = 0; i < tabuleiro.length; i++){
				for(int j = 0; j < tabuleiro[0].length; j++){
					if(bloqueio[i][j] < menosBloqueios && tabuleiro[i][j] == 0){
						menosBloqueios = bloqueio[i][j];
						linhaRainha = i;
						colunaRainha = j;
					}
				}
			}
			
			//linhaRainha = 0;
			//colunaRainha = 0;
			
			//bloqueia as casas horizontais de acordo com a posicao da rainha;
			for(int i = 0; i < tabuleiro.length; i++){
				tabuleiro[linhaRainha][i] = 2;
				if(bloqueio[linhaRainha][i] < 91){
					bloqueio[linhaRainha][i] = rainha + 91;
				}
			}
			
			//bloqueia as casas verticais de acordo com a poscicao da rainha;
			for(int i = 0; i < tabuleiro[0].length; i++){
				tabuleiro[i][colunaRainha] = 2;
				if(bloqueio[i][colunaRainha] < 91){
					bloqueio[i][colunaRainha] = rainha + 91;
				}
			}
			
			//bloqueia as casas diagonais de acordo com a posição da rainha;
			for(int i = 0; i + linhaRainha < tabuleiro.length && i + colunaRainha < tabuleiro[0].length; i++){
				tabuleiro[linhaRainha + i][colunaRainha + i] = 2;
				if(bloqueio[linhaRainha + i][colunaRainha + i] < 91){
					bloqueio[linhaRainha + i][colunaRainha + i] = rainha + 91;
				}
				
			}
			
			for(int i = 0; linhaRainha - i >= 0 && i + colunaRainha < tabuleiro[0].length; i++){
				tabuleiro[linhaRainha - i][colunaRainha + i] = 2;
				if(bloqueio[linhaRainha - i][colunaRainha + i] < 91){
					bloqueio[linhaRainha - i][colunaRainha + i] = rainha + 91;
				}
			}
			
			for(int i = 0; linhaRainha - i >= 0 && colunaRainha - i >= 0; i++ ){
				tabuleiro[linhaRainha - i][colunaRainha - i] = 2;
				if(bloqueio[linhaRainha - i][colunaRainha - i] < 91){
					bloqueio[linhaRainha - i][colunaRainha - i] = rainha + 91;
				}
			}
			
			for(int i = 0; i + linhaRainha < tabuleiro.length && colunaRainha - i >= 0; i++){
				tabuleiro[linhaRainha + i][colunaRainha - i] = 2;
				if(bloqueio[linhaRainha + i][colunaRainha - i] < 91){
					bloqueio[linhaRainha + i][colunaRainha - i] = rainha + 91;
				}
				
			}
			
			//Atualiza os valores da tabela bloqueio
			for(int i = 0; i < tabuleiro.length; i++){
				for(int j = 0; j < tabuleiro[0].length; j++){
					if(tabuleiro[i][j] == 0){
						
						for(int horizontal = 0; horizontal < tabuleiro.length; horizontal++){
							if(tabuleiro[horizontal][j] == 1 || tabuleiro[horizontal][j] == 2 && bloqueio[horizontal][j] == rainha + 91){
								bloqueio[i][j]--;
							}
						}
						
						for(int vertical = 0; vertical < tabuleiro[0].length; vertical++){
							if(tabuleiro[i][vertical] == 1 || tabuleiro[i][vertical] == 2 && bloqueio[i][vertical] == rainha + 91){
								bloqueio[i][j]--;
							}
						}
						
						//esquerda para a direita, para baixo 
						for(int diagonal = 0; diagonal + i < tabuleiro.length && diagonal + j < tabuleiro[0].length; diagonal++){
							if(tabuleiro[i + diagonal][j + diagonal] == 1 || tabuleiro[i + diagonal][j + diagonal] == 2 && bloqueio[i + diagonal][j + diagonal] == rainha + 91){
								bloqueio[i][j]--;
							}
						}
						//esquerda para a diretia, para cima
						for(int diagonal = 0; i - diagonal >= 0 && diagonal + j < tabuleiro[0].length; diagonal++){
							if(tabuleiro[i - diagonal][j + diagonal] == 1 || tabuleiro[i - diagonal][j + diagonal] == 2 && bloqueio[i - diagonal][j + diagonal] == rainha + 91){
								bloqueio[i][j]--;
							}
						}
						//direita para esquerda, para cima
						for(int diagonal = 0; i - diagonal >= 0 && j - diagonal >= 0; diagonal++){
							if(tabuleiro[i - diagonal][j - diagonal] == 1 || tabuleiro[i - diagonal][j - diagonal] == 2 && bloqueio[i - diagonal][j - diagonal] == rainha + 91){
								bloqueio[i][j]--;
							}
						}
						
						//direita para a esquerda. para baixo
						for(int diagonal = 0; i + diagonal < tabuleiro.length && j - diagonal >= 0; diagonal++){
							if(tabuleiro[i + diagonal][j - diagonal] == 1 || tabuleiro[i + diagonal][j - diagonal] == 2 && bloqueio[i + diagonal][j - diagonal] == rainha + 91){
								bloqueio[i][j]--;
							}
						}
					}
				}
			}
			
			
			
			tabuleiro[linhaRainha][colunaRainha] = 1;
			exibeTabuleiro(tabuleiro);
			System.out.println("Pressione Enter para posicionar a próxima rainha.");
			input.nextLine();
			
		}
		System.out.println("Final:");
		exibeTabuleiro(tabuleiro);

		
		
	}
	
	public static void exibeBloqueio(int[][] bloqueio){
		System.out.println("Tabela Bloqueio");
		
		for(int i = 0; i < bloqueio.length; i++){
			for(int j = 0; j < bloqueio[0].length; j++){
				System.out.printf("%d |", bloqueio[i][j]);
			}
			System.out.println();
		}
	}
	
	public static void preencheBloqueio(int[][] bloqueio){
		
		for(int pulo = 0; pulo < 4; pulo++){
			for(int i = 0 + pulo; i < bloqueio.length - pulo; i++){
				for(int j = 0 + pulo; j < bloqueio[0].length - pulo; j++){
					bloqueio[i][j] = 22 + pulo * 2;
				}
			}
		}
	}
	
	public static void exibeTabuleiro(int[][] tabuleiro){
		for(int i = 0; i < tabuleiro.length; i++){
			for(int j = 0; j < tabuleiro[0].length; j++){
				if(tabuleiro[i][j] == 0){
					System.out.printf("  |");
				}
				else if(tabuleiro[i][j] == 1){
					System.out.printf(" R|");
				}
				else if(tabuleiro[i][j] == 2){
					System.out.printf(" *|");
				}
			}
			
			System.out.println();
		}
	}

}
