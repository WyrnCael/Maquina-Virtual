package tp.pr5.mv.ins;

import tp.pr5.mv.ins.arithmetic.Add;
import tp.pr5.mv.ins.arithmetic.Div;
import tp.pr5.mv.ins.arithmetic.Mul;
import tp.pr5.mv.ins.arithmetic.Sub;
import tp.pr5.mv.ins.booleans.And;
import tp.pr5.mv.ins.booleans.Or;
import tp.pr5.mv.ins.comparation.EQ;
import tp.pr5.mv.ins.comparation.Flip;
import tp.pr5.mv.ins.comparation.GT;
import tp.pr5.mv.ins.comparation.LE;
import tp.pr5.mv.ins.comparation.LT;
import tp.pr5.mv.ins.halt.Halt;
import tp.pr5.mv.ins.jump.BF;
import tp.pr5.mv.ins.jump.BT;
import tp.pr5.mv.ins.jump.Jump;
import tp.pr5.mv.ins.jump.JumpInd;
import tp.pr5.mv.ins.jump.RBF;
import tp.pr5.mv.ins.jump.RBT;
import tp.pr5.mv.ins.jump.RJump;
import tp.pr5.mv.ins.memory.Store;
import tp.pr5.mv.ins.memory.StoreInd;
import tp.pr5.mv.ins.operandOp.Dup;
import tp.pr5.mv.ins.operandOp.Load;
import tp.pr5.mv.ins.operandOp.LoadInd;
import tp.pr5.mv.ins.operandOp.Neg;
import tp.pr5.mv.ins.operandOp.Not;
import tp.pr5.mv.ins.operandOp.Push;
import tp.pr5.mv.ins.unary.In;
import tp.pr5.mv.ins.unary.Out;
import tp.pr5.mv.ins.unary.Pop;



public class InstructionParser {
	
	private static Instruction[] instructions = { new Push(), new Pop(), new Dup(), new Flip(), new Load(), new Store(), new Add(), new Sub(), new Mul(), new Div(), new Neg(), new LT(), new GT(), new EQ(), new LE(), new And(), new Or(), new Not(), new Jump(), new BT(), new BF(), new RJump(), new RBT(), new RBF(), new Out(), new Halt(), new In(), new JumpInd(), new StoreInd(), new LoadInd() };
	
	public static Instruction parse(String cadena){ 
		Instruction instructionPars = null;
		for (Instruction instr: instructions){
			Instruction instruction = instr.parseOp(cadena);
			if (instruction != null){
				instructionPars = instruction;
			}
		}		
		return instructionPars;
	}
}
