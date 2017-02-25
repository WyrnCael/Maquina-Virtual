package tp.pr5.mv.cpu;
import tp.pr5.mv.Constantes;
import tp.pr5.mv.ins.Instruction;



public class ProgramMV {
	
	private Instruction[] instructions;
	private int contador;
	
	public ProgramMV(){
		this.instructions = new Instruction[Constantes.MAX_INSTRUCTIONS];
		this.contador = 0;
	}
	
	public void addInstruction(Instruction instr){
		this.instructions[contador] = instr;
		this.contador++;
	}
	
	public Instruction getInstruction(int pos){
		return this.instructions[pos];
	}
	
	public int nInstrucciones(){
		return this.contador;
	}
	
	public String toString(){
		String cadena = "";
		for (int i = 0; i < this.contador ; i++){
			if (i < 10){ cadena += "   "+i+": "+this.instructions[i].toString()+Constantes.SALTO; }
			else { cadena += "  "+i+": "+this.instructions[i].toString()+Constantes.SALTO; }
		}		
		return cadena;
	}
}
