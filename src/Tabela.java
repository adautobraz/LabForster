import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;


public class Tabela {
	
	public int tamGrupo;
	char Tabela[][];
	String arquivo;
	
	public Tabela(){
		tamGrupo=0;
	}
	
	public boolean ehGrupo(){
		boolean ehGrupo = false;
		if(pFechamento(Tabela,tamGrupo)){
			System.out.println("É fechado");
			if(pInversa(Tabela,tamGrupo)){
				System.out.println("É inversa");
				if(pAssociativa(Tabela,tamGrupo)){
					System.out.println("É associativa");
					ehGrupo=true;
				}			
			}
		}
		return ehGrupo;
	}
	
	public void  verTamanhoTabela(String arquivo) throws IOException{
		FileInputStream file = new FileInputStream(arquivo);
		Scanner s = new Scanner(file);		
		while (s.hasNextLine()) {
			String line = s.nextLine();
			tamGrupo++;
		}
		s.close();
		file.close();
		System.out.printf("O tamanho da tabela é %d\n",tamGrupo);
	}
	
	public void readTabela()throws IOException {
		try{
			verTamanhoTabela(arquivo);
			Tabela = new char[tamGrupo][tamGrupo];
			FileInputStream file = new FileInputStream(arquivo);
			Scanner s = new Scanner(file);		
			int i=0;
			while (s.hasNextLine()) {
				String line = s.nextLine();
		        for (int k=0 ;k<tamGrupo; k++){
		        		//System.out.printf("Variáveis no momento: %d, %d, %d\n",i,k,tamanho);
	            	   Tabela[i][k] = line.charAt(k);
	            	   //System.out.println(Tabela[i][k]);
	               }
		        i++;
	           }
		 /*for (int i=0 ;i<tamanho; i++){
               for (int k=0 ;k<tamanho; k++){
            	   System.out.println(Tabela[k][i]);
               }
           }*/
			s.close();
			file.close();
		} catch (IOException e) {
			System.out.println("Deu ruim");
		}
	}

	public void encontrarSubgrupos(String file) throws IOException{
		//preencher tabela
		arquivo = file;
		readTabela();
		
		//Verificar se é grupo
		if(ehGrupo())
			System.out.println("É grupo");
		else
			System.out.println("Não é grupo");
		/*
		//Verificar subgrupos com tamanho crescente
		char Subgrupo[][];
		for(int tamSubgrupo=2; tamSubgrupo < tamGrupo; tamSubgrupo++)
			gerarSubgrupos(tamSubgrupo);
		*/
	}	
	
	/*
	private int fatorial(int x){
		int result=1;
		for(int i=1; i<=x; i++)
			result *= i;
		return result;
	}
	
	private void gerarSubgrupos (int tamanho){
		char Subgrupo[][] = new char[tamanho][tamanho];
		Subgrupo[0][0]='1';
		int indicesEscolhidos[];
		indicesEscolhidos = new int[fatorial(tamGrupo)/(fatorial(tamanho)*fatorial(tamGrupo-tamanho))];
		
		for(int i=0; i<tamGrupo;i++){
			for (int k=0; k<tamGrupo;k++){
				
			}
		}
		
	}*/
		
	private boolean pFechamento(char Subgrupo[][],int size){
		boolean valeFechamento = true;
		boolean igualAAlgumElemento = false;
		for(int i=0;i<size && valeFechamento ;i++){
			for(int k=0;k<size;k++){
				igualAAlgumElemento = false;
				for(int j=0; j<size;j++){
					if(Subgrupo[i][k]== Subgrupo[j][0])
						igualAAlgumElemento = true;
				}
				if (igualAAlgumElemento == false)
					valeFechamento = false;
			}
		}	
		return valeFechamento;
	}
	
	
	private int operacaoXY (char Subgrupo[][], int size, int X, int Y)
	{
		int indice=-1;;
		for(int i=0;i<size;i++){
			if(Subgrupo[X][Y] == Subgrupo[i][0])
				indice = i;
		}
		return indice;
	}
	
	private boolean pAssociativa(char Subgrupo[][],int size ){
		boolean valeAssociativa = true;
		int elemento1, elemento2, elemento3,op1, op2;
		//Para esta função, consideramos que o Subgrupo é fechado
		for(elemento1 = 0 ; elemento1 < size && valeAssociativa; elemento1++){
			for(elemento2 = 0 ; elemento2 < size && valeAssociativa ; elemento2++){
				for(elemento3 = 0 ; elemento3 < size && valeAssociativa; elemento3++ ){
					op1 = operacaoXY(Subgrupo, size, elemento1, elemento2);
					op2 = operacaoXY(Subgrupo, size, elemento2, elemento3);
					if(operacaoXY(Subgrupo,size,op1,elemento3)!= operacaoXY(Subgrupo,size,elemento1,op2)){
						valeAssociativa = false;
						//System.out.printf("Não é associativa para os elementos %d %d %d\n", elemento1,elemento2, elemento3);
					}
				}
			}	
		}
		return valeAssociativa;
	}
	
	private boolean pInversa (char Subgrupo[][], int size){
		boolean valeInversa = true;
		boolean elementoNeutro = false;
		//Tomando tabelas a partir de dois elementos, já contendo o elemento neutro 
		for(int i=1; i<size && valeInversa; i++){
			elementoNeutro = false;
			for(int k=1; k<size && !elementoNeutro; k++){
				if(Subgrupo[i][k] == '1' && Subgrupo[k][i] == '1'){
					elementoNeutro=true;
				}
			}
			if(!elementoNeutro)
			{
				valeInversa = false;
			}
		}
		return valeInversa;
		
	}
	
	public static void main(String args[]) throws IOException
	{
		String arquivo = "C:/CTC-20/table 8.txt";
		Tabela Grupo1 = new Tabela();
		Grupo1.encontrarSubgrupos(arquivo);
	}
	
}

