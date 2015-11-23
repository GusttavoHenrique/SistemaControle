import Jama.Matrix;

public class Seguidor {
	
	
		static double G[][] = {{0.9935, 0}, {0.00656, 0.9935}};
		static double H[][] = {{2.96/Math.pow(10, 2)}, {9.63/Math.pow(10, 5)}};
		static double C[][] = {{0, 1}};
		static double k1, k21, k22;
		static double vk, ek;
		static double uk;
		static double vk_anterior =0;
		static double seguidor[][] = {{0}, {0}, {0}};
		
		
		Matrix seguidor_mat = new Matrix(seguidor);
		Matrix G_mat = new Matrix(G);
		Matrix H_mat = new Matrix(H);
		Matrix C_mat = new Matrix(C);
	
	
	
	Seguidor(double k1, double k21, double k22){
		
		Seguidor.k1 = k1;
		Seguidor.k21 = k21;
		Seguidor.k22 = k22;
		
	}
	
	public Matrix calcularRef(){
		
		double k2[][]= {{k21, k22}};
		Matrix k2_mat = new Matrix(k2);
		System.out.println(k2);
		
		
		double elemento_ref_l2_col0 = 
			(k2_mat.minus(k2_mat.times(G_mat)).minus(C_mat.times(G_mat).times(k1))).getArray()[0][0];
		double elemento_ref_l2_col1 = 
				(k2_mat.minus(k2_mat.times(G_mat)).minus(C_mat.times(G_mat).times(k1))).getArray()[0][1];
		
		double elemento_ref_l2_col2 =  1 - 
				((k2_mat.times(H_mat)).minus((C_mat.times(H_mat)).times(k1))).getArray()[0][0];
				
		double ref[][] = {{0.9935, 0, 2.96/Math.pow(10, 2)}, {0.00656, 0.9935, 9.63/Math.pow(10, 5)},
				{elemento_ref_l2_col0, elemento_ref_l2_col1, elemento_ref_l2_col2}};
		
		Matrix  ref_mat = new Matrix (ref);
				
		
		return ref_mat;
	}
	public double calcularSeguidor(double PV, double PV_two, double setPoint){

		/*seguidor[0][0] = PV;
		seguidor[1][0] = PV_two;
		
		seguidor_mat = new Matrix(seguidor);
		
		seguidor_mat = calcularRef().times(seguidor_mat);		
		seguidor = seguidor_mat.getArray();*/
		
		ek = setPoint - PV_two;
		vk = vk_anterior + ek;
		vk_anterior = vk;
		
		uk = vk*k1 - (k21*PV + k22*PV_two);
	
		return uk;
		
		
	}
	

}
