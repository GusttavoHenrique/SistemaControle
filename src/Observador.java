import Jama.Matrix;

public class Observador {

	static double A[][] = new double[2][2];
	static double B[][] = new double[2][1];
	static double C[][] = {{0, 1}};
	static double Y[][] = new double[1][1];
	static double Y_chapeu[][] = {{0}};
	double L[][] = new double[2][1];
	static double G[][] = {{0.999, 0}, {0.000656, 0.999}};
	static double H[][] = {{2.96/Math.pow(10, 2)}, {9.63/Math.pow(10, 6)}};
	static double x_chapeu[][]= new double[2][1];
	static double x_chapeu_anterior[][] = {{0}, {0}};
	static double x_erro_estimacao[][] = new double[2][1];
	static double x_erro_estimacao_anterior[][] = {{0}, {0}};
	static double x_k_mais_um[][] = new double[2][1];
	static double x_k[][] = {{0}, {0}};
	
	double teste[][] = {{2, 0}, {0, 2}};
	Matrix matrix = new Matrix(teste);
	
	
	
	static Matrix G_matrix = new Matrix(G);
	static Matrix H_matrix = new Matrix(H);
	static Matrix C_matrix = new Matrix(C);
	Matrix L_matrix = new Matrix(L);
	static Matrix x_k_mais_um_matrix = new Matrix(x_k_mais_um);
	static Matrix x_chapeu_matrix = new Matrix(x_chapeu);
	Matrix x_chapeu_anterior_matrix = new Matrix(x_chapeu_anterior);
	Matrix x_k_matrix = new Matrix(x_k);
	Matrix x_erro_estimacao_matrix = new Matrix (x_erro_estimacao);
	Matrix x_erro_estimacao_anterior_matrix= new Matrix (x_erro_estimacao_anterior);
	Matrix Y_matrix = new Matrix (Y);
	Matrix Y_chapeu_matrix = new Matrix (Y_chapeu);
	
	double vp;
	
	
	
	Observador(double l1, double l2){
		
		this.L[0][0] = l1;
		this.L[1][0] = l2;
		
	}
	
	void calcularObservador(){
		
		/*Estadoo discretos*/
		
		x_k_mais_um =  G_matrix.times(this.x_k_matrix).plus(H_matrix.times(this.vp)).getArray();
		Y = C_matrix.times(x_k_matrix).getArray();
		
		x_k = x_k_mais_um;
		x_k_mais_um_matrix = new Matrix(x_k_mais_um);
		x_k_matrix = new Matrix (x_k);
		Y_matrix = new Matrix(Y);
	
		
		/*Dinâmica do observador*/
		
		x_chapeu = (G_matrix).times(this.x_chapeu_anterior_matrix).
				plus(L_matrix.times((Y_matrix.minus(Y_chapeu_matrix)))).
				plus(H_matrix).times(vp).getArray();
		Y_chapeu = C_matrix.times(x_chapeu_anterior_matrix).getArray();
		
		
		x_chapeu_anterior = x_chapeu;
		x_chapeu_matrix = new Matrix (x_chapeu);
		x_chapeu_anterior_matrix = new Matrix(x_chapeu_anterior);
		Y_chapeu_matrix = new Matrix(Y_chapeu);
		
		
		
		
		/*Dinâmica do erro*/

		x_erro_estimacao_matrix = G_matrix.minus(L_matrix.times(C_matrix)).
				times(x_erro_estimacao_anterior_matrix);
		
		x_erro_estimacao_anterior = x_erro_estimacao;
		
		x_erro_estimacao_matrix = new Matrix(x_erro_estimacao);
		x_erro_estimacao_anterior_matrix = new Matrix(x_erro_estimacao_anterior);
		
		
		
		
		
		
		
		
	}	
}
