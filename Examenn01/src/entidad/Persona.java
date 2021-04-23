package entidad;


public class Persona {

	private String nombre;
	private int edad;
	private String nss;
	private char sexo;
	private double peso;
	private double altura;
	
	//constante para sexo por DEFAULT
	private final char sexoDefault='H';
	
	//constantes para imc
	private final int faltaPeso=-1;
	private final int pesoNormal=0;
	private final int sobrePeso=1;
	
	//constructor para datos por default de persona
	public Persona() {
		nombre="";
		edad=0;
		sexo=sexoDefault;
		peso=0;
		altura=0;
	}
	
	
	public Persona(String nombre, int edad, char sexo, double peso, double altura) {
		this.nombre = nombre;
		this.edad = edad;
		this.nss = generaNSS();//generar nss
		if(comprobarSexo(sexo)) //si el sexo es mal ingresado, default permanecera como hombre
		this.sexo = sexo;
		else
			this.sexo=sexoDefault;
		this.peso = peso;
		this.altura = altura;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public int getEdad() {
		return edad;
	}


	public void setEdad(int edad) {
		this.edad = edad;
	}


	public String getNss() {
		return nss;
	}


	public char getSexo() {
		return sexo;
	}


	public void setSexo(char sexo) {
		this.sexo = sexo;
	}


	public double getPeso() {
		return peso;
	}


	public void setPeso(double peso) {
		this.peso = peso;
	}


	public double getAltura() {
		return altura;
	}


	public void setAltura(double altura) {
		this.altura = altura;
	}


	public char getSexoDefault() {
		return sexoDefault;
	}
	
	public int calcularIMC() {
		double imc= peso/Math.pow(altura,2);
		if(sexo=='H') 
			return devolverValorImcHombre(imc);
		    return devolverValorImcMujer(imc);
	}
	
	private int devolverValorImcHombre(double imc) {
		if(imc<20)
			return faltaPeso;
		 if(imc<26)
			 return pesoNormal;
		 	 return sobrePeso;
	}
	
	private int devolverValorImcMujer(double imc) {
		if(imc<19)
			return faltaPeso;
		 if(imc<25)
			 return pesoNormal;
		 	 return sobrePeso;
	}
	
	public boolean esMayorDeEdad() {
		return edad>17;
	}
	
	private boolean comprobarSexo(char sexo) {
		return (sexo=='H'||sexo=='M');
	}


	@Override
	public String toString() {
		return "Persona [nombre=" + nombre + ", edad=" + edad + ", nss=" + nss + ", sexo=" + sexo + ", peso=" + peso
				+ ", altura=" + altura+"]";
	}
	

	private String generaNSS() {
		String nss="";
		for(int i=0;i<8;i++) {
			if((int) (Math.random()*2+1)==1) 
				nss+= ""+(int) (Math.random()*9+1);
			else 
				nss+=(char)(int)(Math.random()*(122-97+1)+97);
		}
		return nss;
	}
	
//	public static void main(String[]args) {
//		Persona p= new Persona();
//		System.out.println("nss de la persona es "+p.generaNSS());
//	}
}


