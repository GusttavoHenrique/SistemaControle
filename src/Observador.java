import Jama.Matrix;

public class Observador {

	double A[][] = new double[2][2];
	double B[][] = new double[2][1];
	double C[][] = {{0}, {1}};
	double L[][] = new double[2][1];
	static double G[][] = {{0.999, 0}, {0.000656, 0.999}};
	static double H[][] = {{2.96/Math.pow(10, 2)}, {9.63/Math.pow(10, 6)}};
	double x_chapeu[][]= new double[2][1];
	double x_chapeu_anterior[][] = {{0}, {0}};
	double x_erro_estimacao[][] = new double[2][1];
	double x_erro_estimacao_anterior[][] = {{0}, {0}};
	double x_estado[][] = new double[2][1];
	double x_estado_anterior[][] = {{0}, {0}};
	
	Matrix G_matrix = new Matrix(G);
	Matrix H_matrix = new Matrix(H);
	Matrix C_matrix = new Matrix(C);
	Matrix L_matrix = new Matrix(L);
	Matrix x_estado_matrix = new Matrix(x_estado);
	Matrix x_chapeu_matrix = new Matrix(x_chapeu);
	Matrix x_estado_anterior_matrix = new Matrix(x_estado_anterior);
	Matrix x_erro_estimacao_matrix = new Matrix (x_erro_estimacao);
	Matrix x_erro_estimacao_anterior_matrix= new Matrix (x_erro_estimacao_anterior);
	
	double vp;
	
	
	
	Observador(double l1, double l2){
		
		this.L[0][0] = l1;
		this.L[1][0] = l2;
	}
	
	void calcularObservador(){
		
		this.x_estado =  G_matrix.times(this.x_estado_anterior_matrix).plus(this.H_matrix.times(vp)).getArray();
		this.x_estado_anterior = this.x_estado;
		
		
		this.x_estado_anterior_matrix = new Matrix(this.x_estado);
		this.x_estado_matrix = new Matrix(x_estado);
		
		/*Dinâmica do observador*/
		this.x_chapeu = this.G_matrix.minus(this.L_matrix.times(this.C_matrix)).times(this.x_erro_estimacao_anterior_matrix).getArray();
		
		/*Dinâmica do erro*/
		this.x_erro_estimacao = this.x_estado_matrix.minus(this.x_chapeu_matrix).getArray();
		
		
		this.x_erro_estimacao_anterior = this.x_erro_estimacao; 
		this.x_erro_estimacao_matrix = new Matrix(x_erro_estimacao);
		this.x_erro_estimacao_anterior_matrix = new Matrix(x_erro_estimacao);
		
		
	}	
}
