import java.awt.event.KeyEvent;

import javax.swing.JTextField;

@SuppressWarnings("serial")
public final class JTextFieldAlterado extends JTextField{
	
		private int maximoCaracteres = -1;
		private boolean soNumeros = false;
		
		/** Construtor de um JTextField que s� aceita n�meros.*/
		public JTextFieldAlterado(boolean soAceitaNumeros) {		
	        super();
	        
	        soNumeros = soAceitaNumeros;
	        addKeyListener(new java.awt.event.KeyAdapter() {
	            @Override
	            public void keyTyped(KeyEvent evt) {
	            	jTextFieldKeyTyped(evt);
	            }	        	
        	});
    	}

		/** Construtor de um JTextField que n�o aceita n�meros.*/
		public JTextFieldAlterado(int maximo, boolean soAceitaNumeros) {    
		    super();
		    
		    soNumeros = soAceitaNumeros;
		    
		    setMaximoCaracteres(maximo);
		  
			addKeyListener(new java.awt.event.KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent evt) {
					jTextFieldKeyTyped(evt);
				}
			});
		}
		  
	private void jTextFieldKeyTyped(KeyEvent evt) {
		String caracteres = "";
		
		if(soNumeros){
			caracteres = "0987654321";
		}else{
			caracteres = "0987654321.-";
		}
		
		if (!caracteres.contains(evt.getKeyChar() + "")) {
			evt.consume();
		}
		
		if((getText().length() >= getMaximoCaracteres()) && (getMaximoCaracteres() != -1)){
			evt.consume();
			setText(getText().substring(0,getMaximoCaracteres()));
		}
    }

    public int getMaximoCaracteres() {
        return maximoCaracteres;
    }
    public void setMaximoCaracteres(int maximoCaracteres) {
        this.maximoCaracteres = maximoCaracteres;
    }
}

