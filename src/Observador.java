import Jama.Matrix;

public class Observador {
	static double C[][] = {{0, 1}};
	static double Y[][] = new double[1][1];
	static double Y_chapeu[][] = {{0}};
	double L[][] = new double[2][1];
	static double G[][] = {{0.9935, 0}, {0.00656, 0.9935}};
	static double H[][] = {{2.96/Math.pow(10, 2)}, {9.63/Math.pow(10, 5)}};
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
	Matrix x_k_mais_um_matrix = new Matrix(x_k_mais_um);
	Matrix x_chapeu_matrix = new Matrix(x_chapeu);
	Matrix x_chapeu_anterior_matrix = new Matrix(x_chapeu_anterior);
	
	Matrix x_erro_estimacao_matrix = new Matrix (x_erro_estimacao);
	Matrix x_erro_estimacao_anterior_matrix= new Matrix (x_erro_estimacao_anterior);
	Matrix Y_matrix = new Matrix (Y);
	Matrix Y_chapeu_matrix = new Matrix (Y_chapeu);
	
	double vp;
	
	
	
	Observador(double l1, double l2){
		
		this.L[0][0] = l1;
		this.L[1][0] = l2;
		
		System.out.println(l1);
		//System.out.println(l2);
		
		System.out.println("rrrrrrrrrrrr");
	}
	
	void calcularObservador(double PV, double PV_two,  double VP){

		Y[0][0] = PV_two;
		
		Y_matrix = Matrix.constructWithCopy(Y);
	
		
		/*Dinâmica do observador*/
		
		x_chapeu = (G_matrix.times(x_chapeu_anterior_matrix)).
				plus(L_matrix.times((Y_matrix.minus(Y_chapeu_matrix)))).
				plus((H_matrix).times(vp)).getArray();
		Y_chapeu = C_matrix.times(x_chapeu_anterior_matrix).getArray();
		
		
		x_chapeu_anterior = x_chapeu;
		x_chapeu_matrix = Matrix.constructWithCopy(x_chapeu);
		x_chapeu_anterior_matrix = Matrix.constructWithCopy(x_chapeu_anterior);
		Y_chapeu_matrix = Matrix.constructWithCopy(Y_chapeu);
		
		
		
		
		/*Dinâmica do erro*/

		x_k[0][0] = PV;
		x_k[1][0] = PV_two;
		//x_erro_estimacao_matrix = G_matrix.minus(L_matrix.times(C_matrix)).
		//		times(x_erro_estimacao_anterior_matrix);
		
		/*x_erro_estimacao_matrix = 
		x_erro_estimacao_anterior = x_erro_estimacao;*/
		
		Matrix x_k_matrix = new Matrix(x_k);
		x_erro_estimacao_matrix = x_k_matrix.minus(x_chapeu_matrix);
		x_erro_estimacao = x_erro_estimacao_matrix.getArray();
		
		
		//x_erro_estimacao_matrix = Matrix.constructWithCopy(x_erro_estimacao);
		
		/*x_erro_estimacao_matrix = Matrix.constructWithCopy(x_erro_estimacao);
		x_erro_estimacao_anterior_matrix = Matrix.constructWithCopy(x_erro_estimacao_anterior);*/
		
		
		
		
		
		
		
		
	}	
}
