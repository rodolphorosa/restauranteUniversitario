import entidades.Turno;


public class Main {
	
	public static void main(String[] args) throws Exception {
		
		//System.out.println(ConsumidorMapper.getInstance().encontrarPorMatricula("42"));
		
		System.out.println(Turno.MANHA.getTipoRefeicao());
		
		/*String nome = "";
		
		if(!nome.matches("[\\w]*[\\W]*[\\p{L} ]+[\\w]*[\\W]*")) {
			System.out.println("Nome inválido");
		} else {
			System.out.println("Nome válido");
		}
		
		System.out.println(Turno.MANHA.getTipoRefeicao());
		
		String data = "07/2015";
		
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		formatoData.setLenient(true);
		try {
			Date date = formatoData.parse(data);
			System.out.println(date);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}*/
		
		
	}
}