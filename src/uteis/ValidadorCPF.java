package uteis;

public class ValidadorCPF {
	
	public static boolean isCPFValido (String cpf) {
		
		if(cpf==null || cpf.length()>11) return false;
		if(!cpf.matches("\\d{11}")) return false;
		
		int[] digitos = new int[11];
		int[] v = new int[2];
		
		int i=0;
		for(char d : cpf.toCharArray())  {
			digitos[i] = Integer.parseInt(String.valueOf(d));
			i++;
		}
		
		v[0] = 1 * digitos[0] + 2 * digitos[1] + 3 * digitos[2];
		v[0] += 4 * digitos[3] + 5 * digitos[4] + 6 * digitos[5];
		v[0] += 7 * digitos[6] + 8 * digitos[7] + 9 * digitos[8];
		v[0] = (v[0]%11);
		v[0] = (v[0]%10);
		
		v[1] = 1 *  digitos[1] + 2 * digitos[2] + 3 * digitos[3];
		v[1] += 4 *  digitos[4] + 5 * digitos[5] + 6 * digitos[6];
		v[1] += 7 *  digitos[7] + 8 * digitos[8] + 9 * v[0];
		v[1] = (v[1]%11);
		v[1] = (v[1]%10);
		
		return v[0] == digitos[9] && v[1] == digitos[10];
	}
}