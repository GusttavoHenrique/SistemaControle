import Jama.Matrix;

public class Observador {

	double A[][] = new double[2][2];
	double B[][] = new double[2][1];
	double C[][] = {{0, 1}};
	double Y[][] = new double[1][1];
	double Y_chapeu[][] = {{0}};
	double L[][] = new double[2][1];
	static double G[][] = {{0.999, 0}, {0.000656, 0.999}};
	static double H[][] = {{2.96/Math.pow(10, 2)}, {9.63/Math.pow(10, 6)}};
	double x_chapeu[][]= new double[2][1];
	double x_chapeu_anterior[][] = {{0}, {0}};
	double x_erro_estimacao[][] = new double[2][1];
	double x_erro_estimacao_anterior[][] = {{0}, {0}};
	double x_k_mais_um[][] = new double[2][1];
	double x_k[][] = {{0}, {0}};
	
	double teste[][] = {{2, 0}, {0, 2}};
	Matrix matrix = new Matrix(teste);
	
	
	
	Matrix G_matrix = new Matrix(G);
	Matrix H_matrix = new Matrix(H);
	Matrix C_matrix = new Matrix(C);
	Matrix L_matrix = new Matrix(L);
	Matrix x_k_mais_um_matrix = new Matrix(x_k_mais_um);
	Matrix x_chapeu_matrix = new Matrix(x_chapeu);
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
		
		this.x_k_mais_um =  G_matrix.times(this.x_k_matrix).plus(this.H_matrix.times(vp)).getArray();
		this.Y = C_matrix.times(x_k_matrix).getArray();
		
		this.x_k = this.x_k_mais_um;
		this.x_k_mais_um_matrix = new Matrix(this.x_k_mais_um);
		this.x_k_matrix = new Matrix (this.x_k);
		this.Y_matrix = new Matrix(this.Y);
	
		
		/*Dinâmica do observador*/
		
		this.x_chapeu = (G_matrix).times(this.x_chapeu_anterior_matrix).
				plus(L_matrix.times((Y_matrix.minus(Y_chapeu_matrix)))).
				plus(H_matrix).times(vp).getArray();
		this.Y_chapeu = C_matrix.times(x_chapeu_anterior_matrix).getArray();
		
		
		this.x_chapeu_anterior = this.x_chapeu;
		this.x_chapeu_matrix = new Matrix (x_chapeu);
		this.x_chapeu_anterior_matrix = new Matrix(x_chapeu_anterior);
		this.Y_chapeu_matrix = new Matrix(this.Y_chapeu);
		
		
		
		
		/*Dinâmica do erro*/

		this.x_erro_estimacao_matrix = G_matrix.minus(L_matrix.times(C_matrix)).
				times(x_erro_estimacao_anterior_matrix);
		
		this.x_erro_estimacao_anterior = this.x_erro_estimacao;
		
		this.x_erro_estimacao_matrix = new Matrix(x_erro_estimacao);
		this.x_erro_estimacao_anterior_matrix = new Matrix(x_erro_estimacao_anterior);
		
		
		
		
		
		
		
		
	}	
}
